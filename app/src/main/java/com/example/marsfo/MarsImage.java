package com.example.marsfo;

import java.net.URL;

public class MarsImage {
    String solDay;
    String cameraName;
    String fullCameraName;
    URL image_url;
    String earthDate;
    String roverName;

    public MarsImage(String solDay, String cameraName, String fullCameraName, URL image_url, String earthDate, String roverName) {
        this.solDay = solDay;
        this.cameraName = cameraName;
        this.fullCameraName = fullCameraName;
        this.image_url = image_url;
        this.earthDate = earthDate;
        this.roverName = roverName;
    }

    public String getSolDay() {
        return solDay;
    }

    public void setSolDay(String solDay) {
        this.solDay = solDay;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getFullCameraName() {
        return fullCameraName;
    }

    public void setFullCameraName(String fullCameraName) {
        this.fullCameraName = fullCameraName;
    }

    public URL getImage_url() {
        return image_url;
    }

    public void setImage_url(URL image_url) {
        this.image_url = image_url;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    public String getRoverName() {
        return roverName;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }




}
