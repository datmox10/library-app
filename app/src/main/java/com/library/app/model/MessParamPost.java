package com.library.app.model;

import java.util.List;

public class MessParamPost {
    private String model;
    private List<Message> list;
    private float temperature;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
