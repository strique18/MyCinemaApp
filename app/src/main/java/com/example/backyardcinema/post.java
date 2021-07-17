package com.example.backyardcinema;

import java.time.LocalDateTime;

public class post {
    private  String id;
    private  String name; //Organisation
    private LocalDateTime dateTime;
    private String summary;
    private String textContent;
    private String imageContent;
    private String videoContent;
    private String type; //type, video, image


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "post{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", summary='" + summary + '\'' +
                ", textContent='" + textContent + '\'' +
                ", imageContent='" + imageContent + '\'' +
                ", videoContent='" + videoContent + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
