package com.beverage.controller;

import com.beverage.service.OrderValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BeverageController {

    @Autowired
    private OrderValuationService orderValuationService;

    @GetMapping("/beverage/{orderValue}")
    public float getBeveragevalue(@PathVariable String orderValue) {
        return orderValuationService.performCalculation(orderValue);
    }

}
