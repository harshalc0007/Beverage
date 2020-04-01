package com.beverage.model;

import com.beverage.config.Config;

import java.util.Map;

public class Mojito  {

    private float price = (float) 7.5;

    public float calculatePrice(BaseMenuItem baseMenuItem, Config config) {
        float totalPrice = price;
        final Map<String,Object> valueMap =  config.getIngredient();
        if(baseMenuItem.isWater()){
            totalPrice -=  (float)valueMap.get("water");
        }
        if(baseMenuItem.isMint()){
            totalPrice -= (float) valueMap.get("mint");
        }
        if(baseMenuItem.isSoda()){
            totalPrice -= (float) valueMap.get("mint");
        }

        if(baseMenuItem.isSugar()){
            totalPrice -=  (float)valueMap.get("sugar");
        }
        return totalPrice;

    }
}
