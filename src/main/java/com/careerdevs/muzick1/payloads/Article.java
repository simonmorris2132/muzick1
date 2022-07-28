package com.careerdevs.muzick1.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Article {
    
    private class Source {
        
        private String name;

        public Source() {
        }

        public Source(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

}

    private Source source;
    private String author;
    private String title;
    private String urlToImage;
    private String description;
    private String url;
    private String likes;

    public Article() {
    }

    public Article(Source source, String author, String title, String urlToImage, String description, String url) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.urlToImage = urlToImage;
        this.description = description;
        this.url = url;
        this.likes = likes;
    }

    public Source getSource() {
        return this.source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return this.urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public String getSourceName() {
        return source.name;
    }

    public String getLikes() {
        return this.likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

}
