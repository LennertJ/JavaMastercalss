package com.crm.miniCRM.dto;

import java.time.LocalTime;

public class EventDto {
    private Long id;
    private String description;
    private String date;

    public EventDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventDto(Long id, String description, String date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
