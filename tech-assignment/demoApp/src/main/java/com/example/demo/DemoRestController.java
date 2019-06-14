package com.example.demo;

import com.example.demo.model.Juke;
import com.example.demo.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoRestController {

    @Autowired
    private IDemoService demoService;

    @GetMapping(path = "/findJukes", produces = "application/json")
    public List<Juke> findJukes(@RequestParam(required = true) String settingId, @RequestParam(required = false) String model,
                               @RequestParam(required = false) Integer offset, @RequestParam(required = false) Integer pageSize) {
        return demoService.getListOfJuke(settingId, model, offset, pageSize);
    }


}
