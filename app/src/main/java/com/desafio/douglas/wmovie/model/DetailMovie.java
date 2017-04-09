package com.desafio.douglas.wmovie.model;

import com.desafio.douglas.wmovie.dao.EntityBase;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by worlo on 06/04/2017.
 */

@DatabaseTable(tableName = "tb_datail_movie")
public class DetailMovie extends EntityBase implements Serializable {

    @DatabaseField(id = true, columnName = "imdb_id")
    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @DatabaseField(columnName = "title")
    @SerializedName("Title")
    @Expose
    private String title;

    @DatabaseField(columnName = "year")
    @SerializedName("Year")
    @Expose
    private String year;

    @DatabaseField(columnName = "released")
    @SerializedName("Released")
    @Expose
    private String released;

    @DatabaseField(columnName = "runtime")
    @SerializedName("Runtime")
    @Expose
    private String runtime;

    @DatabaseField(columnName = "genre")
    @SerializedName("Genre")
    @Expose
    private String genre;

    @DatabaseField(columnName = "director")
    @SerializedName("Director")
    @Expose
    private String director;

    @DatabaseField(columnName = "actors")
    @SerializedName("Actors")
    @Expose
    private String actors;

    @DatabaseField(columnName = "plot")
    @SerializedName("Plot")
    @Expose
    private String plot;

    @DatabaseField(columnName = "awards")
    @SerializedName("Awards")
    @Expose
    private String awards;

    @DatabaseField(columnName = "poster")
    @SerializedName("Poster")
    @Expose
    private String poster;

    @DatabaseField(columnName = "metascore")
    @SerializedName("Metascore")
    @Expose
    private String metascore;

    @DatabaseField(columnName = "imdb_rating")
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;

    @DatabaseField(columnName = "imdb_votes")
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;

    @DatabaseField(columnName = "type")
    @SerializedName("Type")
    @Expose
    private String type;

    @DatabaseField(columnName = "production")
    @SerializedName("Production")
    @Expose
    private String production;

    @DatabaseField(columnName = "web_site")
    @SerializedName("Website")
    @Expose
    private String website;

    private final static long serialVersionUID = -7030044158061130016L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}