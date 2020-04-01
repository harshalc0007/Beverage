package com.beverage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

import java.util.Map;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("beverage")
public class Config {

    private Map<String,Object> ingredient = new HashMap<>();

    public Map<String, Object> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Map<String, Object> ingredient) {
        this.ingredient = ingredient;
    }

}
