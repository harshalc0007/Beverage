package com.beverage.service.impl;

import com.beverage.config.Config;
import com.beverage.model.BaseMenuItem;
import com.beverage.service.Calculable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderCalculableImpl implements Calculable {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCalculableImpl.class);

    @Autowired
    Config config;


    @Override
    public Float calculate(String order) {

        LOGGER.info("Order for Beverage =>{}", order);
        final List<String> dataList = Arrays.asList(order.split(","));
        if (dataList.size() == 1) {
            BaseMenuItem baseMenuItem = buildBaseMenuItem(dataList.get(0), new HashMap<>());
            if (!isValidOrder(baseMenuItem, config.getIngredient())) {
                throw new RuntimeException("Invalid order found");
            }
            return baseMenuItem.getCost(baseMenuItem, config);
        } else {
            return calculateForMultipleOrder(dataList);
        }

    }

    private Float calculateForMultipleOrder(final List<String> dataList) {
        final List<BaseMenuItem> menuItemList = getMenuItemList(dataList);
        menuItemList.stream().forEach(mentItem -> {
            if(!isValidOrder(mentItem, config.getIngredient())) {
                throw  new RuntimeException("Invalid order found");
            }
        });
        final AtomicReference<Float> costValue = new AtomicReference<>((float) 0);
        menuItemList.stream().forEach(menu->{
            costValue.updateAndGet(v -> new Float((float) (v + menu.getCost(menu, config))));
        });
        LOGGER.info("final Cost value  is => {}", costValue.get());
        return costValue.get();
    }

    private List<BaseMenuItem> getMenuItemList(final List<String> dataList) {
        final List<String> menuItems = Arrays.asList(((String) config.getIngredient().get("menu")).split(","));
        final List<String> mainMenu = dataList.stream().filter(menu-> menuItems.contains(menu.toUpperCase())).map(menu -> menu.toUpperCase()).collect(Collectors.toList());
        final List<BaseMenuItem> menuItemList = new ArrayList<>();
        for (int i = 1; i < dataList.size(); i++) {
            final String type = dataList.get(i - 1);
            final Map<String, Boolean> dataMap = new HashMap<>();
            LOGGER.info("next order found =>{}",type);
            for (int j = i; j < dataList.size() && !menuItems.contains(dataList.get(j).toUpperCase()); j++) {
                dataMap.put(dataList.get(j).replace("-", "").toLowerCase(), true);
                i++;
            }
            menuItemList.add(buildBaseMenuItem(type, dataMap));
        }
        if(!(mainMenu.size() == menuItemList.size())){
            menuItemList.stream().forEach(menuItem -> mainMenu.remove(menuItem.getType().toUpperCase()));
            mainMenu.stream().forEach(menu -> menuItemList.add(buildBaseMenuItem(menu, new HashMap<>())));
        }
        return menuItemList;
    }

    private BaseMenuItem buildBaseMenuItem(String type, Map<String, Boolean> dataMap) {
        final ObjectMapper mapper = new ObjectMapper();
        final BaseMenuItem baseMenuItem = mapper.convertValue(dataMap, BaseMenuItem.class);
        baseMenuItem.setType(type);
        return baseMenuItem;
    }


}
