package com.beverage.service;

import com.beverage.model.BaseMenuItem;
import java.util.Arrays;
import java.util.Map;

import static com.beverage.constants.Constant.LEMON_TYPE;
import static com.beverage.constants.Constant.MOJITO_TYPE;

public interface Calculable {

    Float calculate(final String order);

    default boolean isValidOrder(BaseMenuItem baseMenuItem, Map<String, Object> config) {

        if (!(Arrays.asList(((String) config.get("menu")).split(",")).contains(baseMenuItem.getType().toUpperCase()))) {
            return false;
        }
        if (baseMenuItem.getType().equalsIgnoreCase(MOJITO_TYPE) || baseMenuItem.getType().equalsIgnoreCase(LEMON_TYPE)) {
            return !(baseMenuItem.isMilk() && baseMenuItem.isSugar() && baseMenuItem.isMint() && baseMenuItem.isSoda());
        } else {
            return !(baseMenuItem.isMilk() && baseMenuItem.isSugar() && baseMenuItem.isWater());

        }
    }
}
