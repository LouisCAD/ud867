package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import xyz.louiscad.example.jokes.backend.myApi.MyApi;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private OnJokeLoadedListener mListener;

    public MainActivityFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AutoLoadingAdView.loadAdIfNotPaid(root);
        root.findViewById(R.id.tellJokeButton).setOnClickListener(this);
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = ((OnJokeLoadedListener) context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tellJokeButton:
                new JokeFetchAsyncTask() {
                    @Override
                    protected void onPostExecute(String joke) {
                        mListener.onJokeLoaded(joke);
                    }
                }.execute();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnJokeLoadedListener {
        void onJokeLoaded(String joke);
    }

    public abstract static class JokeFetchAsyncTask extends AsyncTask<Void, Void, String> {

        private static MyApi sApiService = null;
        private static final String ROOT_URL_USB_HOTSPOT = "http://192.168.42.220:8080/_ah/api/";
        private static final String ROOT_URL_HOME = "http://192.168.86.35:8080/_ah/api/";
        private static final String ROOT_URL_EMULATOR = "http://10.0.2.2:8080/_ah/api/";

        @Override
        protected String doInBackground(Void... params) {
            if (sApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        AndroidJsonFactory.getDefaultInstance(), null)
                        .setRootUrl(ROOT_URL_EMULATOR)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                                request.setDisableGZipContent(true);
                            }
                        });
                sApiService = builder.build();
            }
            try {
                return sApiService.randomJoke().execute().getBody();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected abstract void onPostExecute(String joke);
    }
}
