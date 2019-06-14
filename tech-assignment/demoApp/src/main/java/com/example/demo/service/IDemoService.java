package com.example.demo.service;

import com.example.demo.model.Juke;

import java.util.List;

public interface IDemoService {

    List<Juke> getListOfJuke(String settingId, String model, Integer pageSize, Integer offset);
}
