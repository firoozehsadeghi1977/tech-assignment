package com.example.demo.model;

import java.util.List;

public class Juke {
    private String id;
    private String model;
    private List<JukeComponent> components;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<JukeComponent> getComponents() {
        return components;
    }

    public void setComponents(List<JukeComponent> components) {
        this.components = components;
    }
}
