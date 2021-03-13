package com.emarsys.controller;

import com.emarsys.model.TimeRequest;
import com.emarsys.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ResolveController {

    @Autowired
    private TimeService timeService;

    @PostMapping(value = "/resolve")
    public long getResolvedTime(@RequestBody TimeRequest timeRequest) {
        return timeService.calculateDueDate(timeRequest);
    }
}
