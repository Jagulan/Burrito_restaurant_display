package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private int orderNumber;
    private LocalDateTime orderPlacedTime;
    private double totalPrice;
    private double actualPricePaid;
    private User user;
    private Map<FoodItem, Integer> items;
    private Status status;

    public enum Status {
        PLACED, COLLECTED, CANCELLED
    }

    public Order(User user) {
        this.user = user;
        this.items = new HashMap<>();
        this.status = Status.PLACED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getOrderPlacedTime() {
        return orderPlacedTime;
    }

    public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
        this.orderPlacedTime = orderPlacedTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getActualPricePaid() {
        return actualPricePaid;
    }

    public void setActualPricePaid(double actualPricePaid) {
        this.actualPricePaid = actualPricePaid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }

    public void setItems(Map<FoodItem, Integer> items) {
        this.items = items;
    }

    public void addItem(FoodItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
        totalPrice += item.getPrice() * quantity;
    }

    public void removeItem(FoodItem item) {
        if (items.containsKey(item)) {
            totalPrice -= item.getPrice() * items.get(item);
            items.remove(item);
        }
    }

    public void updateItem(FoodItem item, int quantity) {
        if (items.containsKey(item)) {
            totalPrice -= item.getPrice() * items.get(item);
        }
        items.put(item, quantity);
        totalPrice += item.getPrice() * quantity;
    }

    public User getUser() {
        return user;
    }

    public void cancelOrder() {
        this.status = Status.CANCELLED;
    }

    public void collectOrder(LocalDateTime collectTime) {
        this.status = Status.COLLECTED;
    }
}

