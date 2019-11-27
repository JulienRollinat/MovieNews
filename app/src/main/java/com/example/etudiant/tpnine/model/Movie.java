package com.example.etudiant.tpnine.model;

import java.util.ArrayList;

public class Movie {
    private long id;
    private boolean video;
    private float vote_count;
    private float vote_average;
    private String title;
    private String release_date;
    private String original_language;
    private String original_title;
    ArrayList< Object > genre_ids = new ArrayList < Object > ();
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String poster_path;
    private float popularity;
    private String media_type;


    // Getter Methods

    public long getId() {
        return id;
    }

    public boolean getVideo() {
        return video;
    }

    public float getVote_count() {
        return vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getMedia_type() {
        return media_type;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVote_count(float vote_count) {
        this.vote_count = vote_count;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                '}';
    }
}