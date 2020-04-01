package com.beverage.model;

import com.beverage.config.Config;

import java.util.Map;

public class BananaSmoothie  {

    private float price = 6;

    BananaSmoothie () {

    }


    public float calculatePrice(BaseMenuItem baseMenuItem, Config config) {
        float totalPrice = price;
        final Map<String,Object> valueMap =  config.getIngredient();
        if(baseMenuItem.isWater()){
            totalPrice -=  Float.parseFloat((String)valueMap.get("water"));
        }
        if(baseMenuItem.isSugar()){
            totalPrice -=   Float.parseFloat((String)valueMap.get("sugar"));
        }
        if(baseMenuItem.isMilk()){

            totalPrice -=   Float.parseFloat((String)valueMap.get("milk"));
        }
        return totalPrice;

    }
}
