package com.example.flatbuffersdemo.json;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by anirudh.r on 24/07/16.
 */

public class EventListJson {

    @SerializedName("event")
    public ArrayList<EventJson> eventJsonArrayList;

    @Override
    public String toString() {
        return "EventListJson{" +
                "eventJsonArrayList=" + eventJsonArrayList +
                '}';
    }
}
