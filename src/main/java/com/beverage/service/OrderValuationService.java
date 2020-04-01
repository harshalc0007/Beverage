package com.beverage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderValuationService {

    @Autowired
    Calculable orderCalculableImpl;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderValuationService.class);

    public float performCalculation(final String order) {
        LOGGER.info("Order received with =>{}",order);
        if(order.isEmpty()) {
            throw new RuntimeException("Invalid Order found");
        }
        return orderCalculableImpl.calculate(order);

    }


}
