package com.firstspring.First.Spring;

public class Books {
    long id;
    String name;
    String author;

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Books(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
