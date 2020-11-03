package com.example.slidenews;

public class News {
    String heading, author, content, url;

    public News(String h, String a, String c, String url) {
        this.heading = h;
        this.author = a;
        this.content = c;
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
