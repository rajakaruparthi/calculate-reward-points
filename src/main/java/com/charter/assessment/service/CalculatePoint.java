package com.charter.assessment.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public interface CalculatePoint {

    @RequestMapping(method = RequestMethod.GET, path = "/cal-points")
    HashMap<String, Float> calculatePoints();
}
