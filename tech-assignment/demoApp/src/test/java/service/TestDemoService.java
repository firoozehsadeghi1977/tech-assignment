package service;

import com.example.demo.model.Juke;
import com.example.demo.model.JukeComponent;
import com.example.demo.model.Setting;
import com.example.demo.model.SettingResponse;
import com.example.demo.service.DemoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestDemoService {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DemoService underTest;

    ResponseEntity responseEntity = mock(ResponseEntity.class);

    @Test
    public void TestGetListOfJuke() {
        Juke item = new Juke();
        item.setId("5ca94a8a77e20d15a7d16d0a");
        JukeComponent component = new JukeComponent();
        component.setName("pcb");
        item.setComponents(Collections.singletonList(component));
        item.setModel("angelina");

        Setting setting = new Setting();
        setting.setId("67ab1ec7-59b8-42f9-b96c-b261cc2a2ed9");
        setting.setRequires(Collections.singletonList("pcb"));

        ResponseEntity<List<Juke>> myresponse = new ResponseEntity<List<Juke>>(Collections.singletonList(item), HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                "http://localhost:3000/jukes",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Juke>>() {
                })).thenReturn(myresponse);

        SettingResponse settingList = new SettingResponse();
        settingList.setSettings(Collections.singletonList(setting));

        ResponseEntity<SettingResponse> settingResponse = new ResponseEntity<SettingResponse>(settingList, HttpStatus.ACCEPTED);

        when(restTemplate.exchange(
                "http://localhost:3000/settings",
                HttpMethod.GET,
                null,
                SettingResponse.class)).thenReturn(settingResponse);

        List<Juke> result = underTest.getListOfJuke("67ab1ec7-59b8-42f9-b96c-b261cc2a2ed9", null, null, null);

        Assert.assertEquals(result.size(), 1);

        result = underTest.getListOfJuke("67ab1ec7-59b8-42f9-b96c-b261cc2a2ed9", "angelina", null, null);

        Assert.assertEquals(result.size(), 1);

    }
}
