package com.example.practiceapp05;

public class Movie {
    private String MovieName=null;
    private int MoviePoster = 0;

    public Movie(String MovieName,int MoviePoster){
        this.MovieName = MovieName;
        this.MoviePoster = MoviePoster;

    }

    public void setMoviePoster(int moviePoster) {
        MoviePoster = moviePoster;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public int getMoviePoster() {
        return MoviePoster;
    }
}
