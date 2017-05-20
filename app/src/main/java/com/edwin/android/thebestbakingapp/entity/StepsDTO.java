package com.edwin.android.thebestbakingapp.entity;

public class StepsDTO {
    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("shortDescription")
    private String shortDescription;
    @com.google.gson.annotations.SerializedName("description")
    private String description;
    @com.google.gson.annotations.SerializedName("videoURL")
    private String videoUrl;
    @com.google.gson.annotations.SerializedName("thumbnailURL")
    private String thumbnailUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepsDTO steps = (StepsDTO) o;

        return id == steps.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "StepsDTO{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
