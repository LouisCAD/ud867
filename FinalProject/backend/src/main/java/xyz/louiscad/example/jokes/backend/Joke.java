package xyz.louiscad.example.jokes.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class Joke {

    private String body;
    private String author;

    public Joke() {
    }

    public Joke(String body) {
        this.body = body;
        author = "unknown";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}