package com.beverage.service.impl;

import com.beverage.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class OrderCalculableImplTest {

    @InjectMocks
    private OrderCalculableImpl orderCalculableImpl;


    Config config = new Config();

    final Map<String, Object> ingredients = new HashMap<>();

    @Before
    public void setup() {

        ingredients.put("sugar", "0.5");
        ingredients.put("milk", "1");
        ingredients.put("soda", "0.5");
        ingredients.put("mint", "0.5");
        ingredients.put("water", "0.5");
        ingredients.put("menu", "COFFEE,CHAI,TEA,MOJITO,LEMON,STRAWBERRY SHAKE,BANANA SMOOTHIE,BANANA,STRAWBERRIES");


    }

    @Test
    public void positiveTestForSingleOrder() {
        config.setIngredient(ingredients);
        ReflectionTestUtils.setField(orderCalculableImpl, "config", config);
        float orderValue = orderCalculableImpl.calculate("chai");
        Assert.assertNotNull(orderValue);
        Assert.assertEquals(4, orderValue, 0);
    }

    @Test
    public void positiveTestForSingleWithMinusMenuOrder() {
        config.setIngredient(ingredients);
        ReflectionTestUtils.setField(orderCalculableImpl, "config", config);
        float orderValue = orderCalculableImpl.calculate("chai,-Sugar");
        Assert.assertNotNull(orderValue);
        Assert.assertEquals(4, orderValue, 0.5);
    }

    @Test
    public void positiveMultipleOrder() {
        config.setIngredient(ingredients);
        ReflectionTestUtils.setField(orderCalculableImpl, "config", config);
        float orderValue = orderCalculableImpl.calculate("chai,-SUGAR,banana");
        Assert.assertNotNull(orderValue);
        Assert.assertEquals(10, orderValue, 0.5);
    }

    @Test(expected = RuntimeException.class)
    public void negativeWithExceptionInvalidOrder() {
        config.setIngredient(ingredients);
        ReflectionTestUtils.setField(orderCalculableImpl, "config", config);
        float orderValue = orderCalculableImpl.calculate("-sugar");
    }

    @Test(expected = RuntimeException.class)
    public void negativeWithExceptionAllMinusMenu() {
        config.setIngredient(ingredients);
        ReflectionTestUtils.setField(orderCalculableImpl, "config", config);
        orderCalculableImpl.calculate("chai,-sugar,-water,-milk");
    }


}
