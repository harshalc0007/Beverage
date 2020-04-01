package com.beverage.model;


import com.beverage.config.Config;

import static com.beverage.constants.Constant.*;

public class BaseMenuItem {

    private boolean milk;

    private boolean sugar;

    private boolean water;

    private String type;

    private boolean soda;

    private boolean mint;


    BaseMenuItem() {

    }

    BaseMenuItem(boolean isMilkMinus, boolean isSugarMinus, boolean isWaterMinus, boolean isSodaMinus, boolean isMintMinus, String type) {
        this.milk = isMilkMinus;
        this.sugar = isSugarMinus;
        this.water = isWaterMinus;
        this.soda = isSodaMinus;
        this.mint = isMintMinus;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isSoda() {
        return soda;
    }

    public void setSoda(boolean soda) {
        this.soda = soda;
    }

    public boolean isMint() {
        return mint;
    }

    public void setMint(boolean mint) {
        this.mint = mint;
    }

    public float getCost(BaseMenuItem baseMenuItem, Config config) {
        final String type = getType();
        if(CHAI_TYPE.equalsIgnoreCase(type) || TEA_TYPE.equalsIgnoreCase(type)) {
           return new Chai().calculatePrice(baseMenuItem, config);
        }
        if(BANANA_TYPE.equalsIgnoreCase(type) || SMOOTHIE.equalsIgnoreCase(type) ) {
            return  new BananaSmoothie().calculatePrice(baseMenuItem,config);
        }
        if(COFFEE_TYPE.equalsIgnoreCase(type)) {
            return  new Coffee().calculatePrice(baseMenuItem, config);
        }
        if(MOJITO_TYPE.equalsIgnoreCase(type) || LEMON_TYPE.equalsIgnoreCase(type)) {
            return  new Mojito().calculatePrice(baseMenuItem, config);
        }

        if(STRAWBERRY_TYPE.equalsIgnoreCase(type) || S_BERRIES.equalsIgnoreCase(type)) {
            return new Strawberry().calculatePrice(baseMenuItem, config);
        }
        return 0;

    }
}
