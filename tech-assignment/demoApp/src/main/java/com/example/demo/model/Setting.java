package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collection;


public class Setting  implements Serializable {

    private String id;

    @JsonProperty("requires")
    private Collection<String> requires;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<String> getRequires() {
        return requires;
    }

    public void setRequires(Collection<String> requires) {
        this.requires = requires;
    }
}
