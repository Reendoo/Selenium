package sk.peterrendek.advanced.model;

import sk.peterrendek.advanced.enums.SinType;

import java.util.ArrayList;
import java.util.List;

public class Sin {
    private String title;
    private String author;
    private String message;
    private List<SinType> tags;

    private boolean isForgiven;

    public Sin(String title, String author, String message) {
        this.title = title;
        this.author = author;
        this.message = message;
        this.tags = new ArrayList<>();
        isForgiven = false;
    }

    public Sin(String title, String author, String message, List<SinType> tags) {
        this.title = title;
        this.author = author;
        this.message = message;
        this.tags = tags;
        this.isForgiven = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SinType> getTags() {
        return tags;
    }

    public void addTag(SinType tag){
        tags.add(tag);
    }

    public Sin forgive(){
        this.isForgiven = true;
        return this;
    }

    public boolean isForgiven() {
        return isForgiven;
    }

    @Override
    public String toString() {
        return "Sin{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", tags=" + tags +
                ", isForgiven=" + isForgiven +
                '}';
    }
}
