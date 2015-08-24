package com.ciandt.worldwonders.model;

import java.io.Serializable;

/**
 * Created by nlopes on 8/24/15.
 */
public class Wonder implements Serializable{

    private int id;
    private String name;
    private String description;
    private String url;
    private String photo;
    private double latitude;
    private double longitude;

    public Wonder() {

    }

    public Wonder(int id, String name, String description, String url, String photo, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.photo = photo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
