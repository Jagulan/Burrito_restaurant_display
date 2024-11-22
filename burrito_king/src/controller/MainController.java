package controller;

import model.FoodItem;
import model.Order;
import model.Restaurant;
import model.User;
import view.MainView;
import dao.UserDAO;
import dao.OrderDAO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MainController {
    private MainView view;
    private UserManager userManager;
    private Restaurant restaurant;
    private OrderDAO orderDAO;

    public MainController(MainView view) {
        this.view = view;
        this.userManager = UserManager.getInstance();
        this.restaurant = new Restaurant();
        this.orderDAO = new OrderDAO();
    }

    public List<FoodItem> getMenu() {
        return restaurant.getMenu();
    }

    public double getMealPrice() {
        return restaurant.getMealPrice();
    }

    public void login(String username, String password) {
        User user = userManager.loginUser(username, password);
        if (user != null) {
            view.showDashboard(user);
        } else {
            view.showError("Login failed. Try again.");
            view.showLoginScreen();
        }
    }

    public void register(String username, String password, String firstName, String lastName) {
        if (userManager.registerUser(username, password, firstName, lastName)) {
            view.showMessage("Registration successful!");
            view.showLoginScreen();
        } else {
            view.showError("Username already exists.");
            view.showRegisterScreen();
        }
    }

    public void placeOrder(User user, Order order) {
        try {
            orderDAO.saveOrder(order);
            user.addOrder(order);
            userManager.updateUser(user); // Ensure the user's credits are updated in the database
            view.showMessage("Order placed successfully!");
            view.showDashboard(user);
        } catch (SQLException e) {
            e.printStackTrace();
            view.showError("Failed to place order. Please try again.");
        }
    }

    public void updateUser(User user) {
        userManager.updateUser(user);
    }

    public boolean validatePaymentDetails(String cardNumber, String expiryDate, String cvv) {
        if (cardNumber.length() != 16 || !cardNumber.matches("\\d+")) {
            System.out.println("Card number validation failed");
            return false;
        }

        if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            System.out.println("Expiry date format validation failed");
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiry = YearMonth.parse(expiryDate, formatter);
            if (!expiry.isAfter(YearMonth.now())) {
                System.out.println("Expiry date is not in the future");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Expiry date parsing failed");
            return false;
        }

        if (cvv.length() != 3 || !cvv.matches("\\d+")) {
            System.out.println("CVV validation failed");
            return false;
        }
        return true;
    }

    public boolean validateOrderTime(String orderTime) {
        try {
            LocalDateTime.parse(orderTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Order time parsing failed");
            return false;
        }
    }

    public int generateUniqueOrderNumber() {
        return (int) (Math.random() * 1000000);
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

    public void upgradeToVIP(User user, String email) {
        user.upgradeToVIP(email);
        userManager.updateUser(user);  // Update the user in the database
        view.showMessage("You are now a VIP user! Please log out and log in again to access VIP functionalities.");
    }
}
