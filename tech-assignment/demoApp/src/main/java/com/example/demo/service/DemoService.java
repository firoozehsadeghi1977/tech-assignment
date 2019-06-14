package com.example.demo.service;

import com.example.demo.model.Juke;
import com.example.demo.model.JukeComponent;
import com.example.demo.model.Setting;
import com.example.demo.model.SettingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class DemoService implements IDemoService {

    @Value(value = "${mockServer.url}")
    private String mockServerUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Juke> getListOfJuke(String settingId, String model, Integer pageSize, Integer offset) {
        Setting settingFounded = findSettingBySettingId(settingId);
        List<Juke> jukes = getListOfJukes();
        List<Juke> jukeStream = new ArrayList<Juke>();
        if (nonNull(settingFounded) && !CollectionUtils.isEmpty(jukes)) {
            jukeStream = getJukesContainsRequiredComponent(jukes, settingFounded);
            if (nonNull(model)) {
                jukeStream = jukeStream.stream().filter(juke -> juke.getModel().equals(model)).collect(Collectors.toList());
            }
            if (nonNull(pageSize) && nonNull(offset) && offset <= jukeStream.size()) {
                jukeStream = jukeStream.subList(offset, offset + pageSize < jukeStream.size() ? offset + pageSize : jukeStream.size());

            }

        }

        return jukeStream;
    }

    private List<Juke> getJukesContainsRequiredComponent(List<Juke> jukes, Setting item) {
        return jukes.stream().filter(juke ->
        {
            List<String> names =
                    juke.getComponents().stream()
                            .map(JukeComponent::getName)
                            .collect(Collectors.toList());
            if (item.getRequires().size() <= names.size() && names.containsAll(item.getRequires()))
                return true;
            else if (item.getRequires().size() > names.size() && item.getRequires().containsAll(names))
                return true;
            else
                return false;
        }).collect(Collectors.toList());
    }

    private List<Juke> getListOfJukes() {
        ResponseEntity<List<Juke>> response1 = restTemplate.exchange(
                mockServerUrl+"/jukes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Juke>>() {
                });
        return response1.getBody();
    }


    private Setting findSettingBySettingId(String settingId) {
        ResponseEntity<SettingResponse> response = restTemplate.exchange(
                mockServerUrl+"/settings",
                HttpMethod.GET,
                null,
                SettingResponse.class);
        SettingResponse settingResponse = response.getBody();
        return settingResponse.getSettings().stream().filter(setting -> setting.getId().equals(settingId)).findFirst().orElse(null);

    }
}
