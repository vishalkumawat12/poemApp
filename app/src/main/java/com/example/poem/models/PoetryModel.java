package com.example.poem.models;

public class PoetryModel {
    int id;
    String poetry_data;
    String poet_name;
    String date_time;

    public PoetryModel(int id, String poetry_data, String poet_name, String date_time) {
        this.id = id;
        this.poetry_data = poetry_data;
        this.poet_name = poet_name;
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetry_data() {
        return poetry_data;
    }

    public void setPoetry_data(String poetry_data) {
        this.poetry_data = poetry_data;
    }

    public String getPoet_name() {
        return poet_name;
    }

    public void setPoet_name(String poet_name) {
        this.poet_name = poet_name;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}


