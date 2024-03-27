package com.library.app.model;

import java.util.List;

public class MessResponse {
    private  String id;
    private String object;
    private String created;
    private String model;

    private List<Choices> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choices> getList() {
        return list;
    }

    public void setList(List<Choices> list) {
        this.list = list;
    }
}
