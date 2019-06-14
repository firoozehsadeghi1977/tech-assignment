package com.example.demo.model;

import java.util.List;

public class SettingResponse {
    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

    List<Setting> settings;

}
