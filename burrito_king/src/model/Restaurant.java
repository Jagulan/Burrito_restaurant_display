package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private List<FoodItem> menu;

    public Restaurant() {
        menu = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        menu.add(new FoodItem("Burrito", 8.5));
        menu.add(new FoodItem("Fries", 3.5));
        menu.add(new FoodItem("Soda", 2.0));
    }

    public List<FoodItem> getMenu() {
        return menu;
    }

    public double getMealPrice() {
        double burritoPrice = menu.stream().filter(item -> item.getName().equals("Burrito")).findFirst().map(FoodItem::getPrice).orElse(0.0);
        double friesPrice = menu.stream().filter(item -> item.getName().equals("Fries")).findFirst().map(FoodItem::getPrice).orElse(0.0);
        double sodaPrice = menu.stream().filter(item -> item.getName().equals("Soda")).findFirst().map(FoodItem::getPrice).orElse(0.0);
        return burritoPrice + friesPrice + sodaPrice - 3.0; // Apply $3 discount for the meal
    }
}
