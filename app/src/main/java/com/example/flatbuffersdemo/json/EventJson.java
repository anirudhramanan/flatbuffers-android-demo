package com.example.flatbuffersdemo.json;

import com.google.gson.annotations.SerializedName;

public class EventJson {

    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;
    @SerializedName("location")
    public String location;
    @SerializedName("date")
    public String date;

    public EventJson(long id, String name, String location, String date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
    }

    @Override
    public String toString() {
        return "EventJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
