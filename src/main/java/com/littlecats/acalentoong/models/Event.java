package com.littlecats.acalentoong.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.UUID;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UUID id;
    private String name;
    private String date;
    private String time;
    private String settlement;
    private int max_capacity;
    private String description;

    public Event(String name, String date, String time, String settlement, int max_capacity, String description) {
        this.date = date;
        this.time = time;
        this.id = UUID.randomUUID();
        this.name = name;
        this.settlement = settlement;
        this.max_capacity = max_capacity;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String  settlement ) {
        this.settlement = settlement;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event that = (Event) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
