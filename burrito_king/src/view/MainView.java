package view;

import controller.MainController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.FoodItem;
import model.Order;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MainView extends Application {
    private MainController controller;
    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 700;
    private Stage primaryStage;
    private Order currentOrder;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.controller = new MainController(this);
        showLoginScreen();
    }

    public void showLoginScreen() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Login");
        label.getStyleClass().add("label");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("text-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("password-field");

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("button");

        loginButton.setOnAction(e -> controller.login(usernameField.getText(), passwordField.getText()));
        registerButton.setOnAction(e -> showRegisterScreen());

        root.getChildren().addAll(label, usernameField, passwordField, loginButton, registerButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Login");
        primaryStage.show();
    }

    public void showRegisterScreen() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Register");
        label.getStyleClass().add("label");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.getStyleClass().add("text-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("password-field");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.getStyleClass().add("text-field");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.getStyleClass().add("text-field");

        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        registerButton.setOnAction(e -> controller.register(usernameField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText()));
        backButton.setOnAction(e -> showLoginScreen());

        root.getChildren().addAll(label, usernameField, passwordField, firstNameField, lastNameField, registerButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Register");
        primaryStage.show();
    }

    public void showDashboard(User user) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Welcome, " + user.getFirstName() + " " + user.getLastName());
        label.getStyleClass().add("label");

        // Add credits information for VIP users
        if (user.isVIP()) {
            Label creditsLabel = new Label("Total Credits: " + user.getCredits());
            creditsLabel.getStyleClass().add("label");
            root.getChildren().add(creditsLabel);
        }

        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.getStyleClass().add("button");

        Button viewOrdersButton = new Button("View Orders");
        viewOrdersButton.getStyleClass().add("button");

        Button editProfileButton = new Button("Edit Profile");
        editProfileButton.getStyleClass().add("button");

        Button exportOrdersButton = new Button("Export Orders");
        exportOrdersButton.getStyleClass().add("button");

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("button");

        if (!user.isVIP()) {
            Button upgradeButton = new Button("Upgrade to VIP");
            upgradeButton.getStyleClass().add("button");
            upgradeButton.setOnAction(e -> showUpgradeToVIPScreen(user));
            root.getChildren().add(upgradeButton);
        }

        placeOrderButton.setOnAction(e -> showPlaceOrderScreen(user));
        viewOrdersButton.setOnAction(e -> showViewOrdersScreen(user));
        editProfileButton.setOnAction(e -> showEditProfileScreen(user));
        exportOrdersButton.setOnAction(e -> showExportOrdersScreen(user));
        logoutButton.setOnAction(e -> showLoginScreen());

        root.getChildren().addAll(label, placeOrderButton, viewOrdersButton, editProfileButton, exportOrdersButton, logoutButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Dashboard");
        primaryStage.show();
    }

    public void showUpgradeToVIPScreen(User user) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Upgrade to VIP");
        label.getStyleClass().add("label");

        Label promptLabel = new Label("Would you like to receive promotion information via email?");
        promptLabel.getStyleClass().add("label");

        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        emailField.getStyleClass().add("text-field");

        Button upgradeButton = new Button("Upgrade");
        upgradeButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        upgradeButton.setOnAction(e -> {
            String email = emailField.getText();
            if (email != null && !email.isEmpty() && email.contains("@")) {
                controller.upgradeToVIP(user, email);
                showMessage("You are now a VIP user. Please log out and log in again to access VIP functionalities.");
            } else {
                showError("Invalid email address. Please try again.");
            }
        });

        backButton.setOnAction(e -> showDashboard(user));

        root.getChildren().addAll(label, promptLabel, emailField, upgradeButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Upgrade to VIP");
        primaryStage.show();
    }

    public void showPlaceOrderScreen(User user) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Place your order:");
        label.getStyleClass().add("label");

        ComboBox<FoodItem> foodComboBox = new ComboBox<>();
        foodComboBox.getItems().addAll(controller.getMenu());
        foodComboBox.getStyleClass().add("combo-box");

        // Add meal option for VIP users
        if (user.isVIP()) {
            FoodItem meal = new FoodItem("Meal (Burrito, Fries, Soda)", 
                                         controller.getMealPrice()); // Total price without discount
            foodComboBox.getItems().add(meal);
        }

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        quantityField.getStyleClass().add("text-field");

        Button addButton = new Button("Add to Order");
        addButton.getStyleClass().add("button");

        Button updateButton = new Button("Update Order");
        updateButton.getStyleClass().add("button");

        Button removeButton = new Button("Remove from Order");
        removeButton.getStyleClass().add("button");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        ListView<String> cartListView = new ListView<>();
        cartListView.getStyleClass().add("list-view");

        currentOrder = new Order(user);  // Initialize currentOrder

        addButton.setOnAction(e -> {
            try {
                FoodItem selectedItem = foodComboBox.getValue();
                int quantity = Integer.parseInt(quantityField.getText());
                currentOrder.addItem(selectedItem, quantity);
                updateCartListView(cartListView, currentOrder);
                label.setText("Item added to order. Total Price: $" + currentOrder.getTotalPrice());
            } catch (NumberFormatException ex) {
                showError("Invalid quantity. Please enter a valid number.");
            }
        });

        updateButton.setOnAction(e -> {
            try {
                FoodItem selectedItem = foodComboBox.getValue();
                int quantity = Integer.parseInt(quantityField.getText());
                currentOrder.updateItem(selectedItem, quantity);
                updateCartListView(cartListView, currentOrder);
                label.setText("Item updated. Total Price: $" + currentOrder.getTotalPrice());
            } catch (NumberFormatException ex) {
                showError("Invalid quantity. Please enter a valid number.");
            }
        });

        removeButton.setOnAction(e -> {
            FoodItem selectedItem = foodComboBox.getValue();
            currentOrder.removeItem(selectedItem);
            updateCartListView(cartListView, currentOrder);
            label.setText("Item removed. Total Price: $" + currentOrder.getTotalPrice());
        });

        checkoutButton.setOnAction(e -> showCheckoutScreen(user, currentOrder));

        backButton.setOnAction(e -> showDashboard(user));

        root.getChildren().addAll(label, foodComboBox, quantityField, addButton, updateButton, removeButton, cartListView, checkoutButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Place Order");
        primaryStage.show();
    }

    public void updateCartListView(ListView<String> cartListView, Order currentOrder) {
        cartListView.getItems().clear();
        for (Map.Entry<FoodItem, Integer> entry : currentOrder.getItems().entrySet()) {
            cartListView.getItems().add(entry.getKey().getName() + " x" + entry.getValue());
        }
    }

    public void showCheckoutScreen(User user, Order currentOrder) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Checkout");
        label.getStyleClass().add("label");

        Label orderSummary = new Label("Order Summary: " + currentOrder.getItems().toString() + "\nTotal Price: $" + currentOrder.getTotalPrice() + "\nCooking Time: 20 minutes");
        orderSummary.getStyleClass().add("order-summary-label");

        TextField orderTimeField = new TextField();
        orderTimeField.setPromptText("Order Time (dd-MM-yyyy HH:mm)");
        orderTimeField.getStyleClass().add("text-field");

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Card Number");
        cardNumberField.getStyleClass().add("text-field");

        TextField expiryDateField = new TextField();
        expiryDateField.setPromptText("Expiry Date (MM/YY)");
        expiryDateField.getStyleClass().add("text-field");

        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");
        cvvField.getStyleClass().add("text-field");

        TextField redeemField = new TextField();
        redeemField.setPromptText("Redeem Credits");
        redeemField.getStyleClass().add("text-field");

        Button confirmButton = new Button("Confirm Order");
        confirmButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        confirmButton.setOnAction(e -> {
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();
            String orderTime = orderTimeField.getText();
            int creditsToRedeem = redeemField.getText().isEmpty() ? 0 : Integer.parseInt(redeemField.getText());

            boolean isPaymentValid = controller.validatePaymentDetails(cardNumber, expiryDate, cvv);
            boolean isOrderTimeValid = controller.validateOrderTime(orderTime);

            if (isPaymentValid && isOrderTimeValid) {
                if (user.isVIP()) {
                    // Collect credits for all orders
                    int creditsEarned = (int) currentOrder.getTotalPrice();
                    user.addCredits(creditsEarned);

                    // Redeem credits if specified
                    if (creditsToRedeem > 0) {
                        int redeemableAmount = creditsToRedeem / 100;
                        currentOrder.setTotalPrice(currentOrder.getTotalPrice() - redeemableAmount);
                        user.redeemCredits(creditsToRedeem);
                    }
                }

                currentOrder.setOrderNumber(controller.generateUniqueOrderNumber());
                currentOrder.setOrderPlacedTime(LocalDateTime.parse(orderTime, controller.getDateTimeFormatter()));
                user.addOrder(currentOrder);
                currentOrder.setActualPricePaid(currentOrder.getTotalPrice());
                controller.placeOrder(user, currentOrder);  // Place order only once
                showOrderConfirmationScreen(user, currentOrder);
            } else {
                label.setText("Invalid payment or order time details. Please try again.");
            }
        });

        backButton.setOnAction(e -> showPlaceOrderScreen(user));

        root.getChildren().addAll(label, orderSummary, orderTimeField, cardNumberField, expiryDateField, cvvField, redeemField, confirmButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Checkout");
        primaryStage.show();
    }

    public void showOrderConfirmationScreen(User user, Order currentOrder) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Order Confirmed!");
        label.getStyleClass().add("label");

        Label orderSummary = new Label("Order Number: " + currentOrder.getOrderNumber() + "\nItems: " + getOrderItemsString(currentOrder) + "\nTotal Price: $" + currentOrder.getTotalPrice() + "\nCooking Time: 20 minutes");
        orderSummary.getStyleClass().add("order-summary-label");

        Button backButton = new Button("Back to Dashboard");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> showDashboard(user));

        root.getChildren().addAll(label, orderSummary, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Order Confirmed");
        primaryStage.show();
    }

    private String getOrderItemsString(Order order) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
            sb.append(entry.getKey().getName()).append(" x").append(entry.getValue()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    public void showViewOrdersScreen(User user) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("View Orders:");
        label.getStyleClass().add("label");

        List<Order> orders = user.getOrders();
        Collections.sort(orders, Comparator.comparing(Order::getOrderPlacedTime).reversed());

        root.getChildren().add(label); // Add the label before the loop

        for (Order order : orders) {
            VBox orderBox = new VBox(5);
            orderBox.getStyleClass().add("order-box");

            Label orderLabel = new Label("Order Number: " + order.getOrderNumber() + "\nOrder Time: " + order.getOrderPlacedTime().format(controller.getDateTimeFormatter())
                    + "\nItems: " + getOrderItemsString(order) + "\nTotal Price: $" + order.getActualPricePaid() + "\nStatus: " + order.getStatus());
            orderLabel.getStyleClass().add("label");

            Button collectButton = new Button("Collect");
            collectButton.getStyleClass().add("button");

            Button cancelButton = new Button("Cancel");
            cancelButton.getStyleClass().add("button");

            if (order.getStatus() != Order.Status.PLACED) {
                collectButton.setDisable(true);
                cancelButton.setDisable(true);
            } else {
                collectButton.setOnAction(e -> showCollectOrderScreen(user, order));
                cancelButton.setOnAction(e -> {
                    order.cancelOrder();
                    showViewOrdersScreen(user); // Refresh the order view
                });
            }

            orderBox.getChildren().addAll(orderLabel, collectButton, cancelButton);
            root.getChildren().add(orderBox);
        }

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> showDashboard(user));

        root.getChildren().add(backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - View Orders");
        primaryStage.show();
    }

    public void showCollectOrderScreen(User user, Order order) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Collect Order");
        label.getStyleClass().add("label");

        TextField collectTimeField = new TextField();
        collectTimeField.setPromptText("Collect Time (dd-MM-yyyy HH:mm)");
        collectTimeField.getStyleClass().add("text-field");

        Button collectButton = new Button("Collect");
        collectButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        collectButton.setOnAction(e -> {
            String collectTime = collectTimeField.getText();
            LocalDateTime parsedCollectTime;
            try {
                parsedCollectTime = LocalDateTime.parse(collectTime, controller.getDateTimeFormatter());
            } catch (DateTimeParseException ex) {
                label.setText("Invalid collect time. Please try again.");
                return;
            }

            if (parsedCollectTime.isAfter(order.getOrderPlacedTime().plusMinutes(20))) {
                order.collectOrder(parsedCollectTime);
                showViewOrdersScreen(user); // Refresh the order view
            } else {
                label.setText("Collect time must be after the order placed time plus cooking time.");
            }
        });

        backButton.setOnAction(e -> showViewOrdersScreen(user));

        root.getChildren().addAll(label, collectTimeField, collectButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Collect Order");
        primaryStage.show();
    }

    public void showEditProfileScreen(User user) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Edit Profile");
        label.getStyleClass().add("label");

        TextField firstNameField = new TextField(user.getFirstName());
        firstNameField.setPromptText("First Name");
        firstNameField.getStyleClass().add("text-field");

        TextField lastNameField = new TextField(user.getLastName());
        lastNameField.setPromptText("Last Name");
        lastNameField.getStyleClass().add("text-field");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.getStyleClass().add("password-field");

        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        saveButton.setOnAction(e -> {
            user.setFirstName(firstNameField.getText());
            user.setLastName(lastNameField.getText());
            user.setPassword(passwordField.getText());
            controller.updateUser(user);
            label.setText("Profile updated successfully!");
        });

        backButton.setOnAction(e -> showDashboard(user));

        root.getChildren().addAll(label, firstNameField, lastNameField, passwordField, saveButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Edit Profile");
        primaryStage.show();
    }

    public void showExportOrdersScreen(User user) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getStyleClass().add("vbox");

        Label label = new Label("Export Orders:");
        label.getStyleClass().add("label");

        List<Order> orders = user.getOrders();
        Collections.sort(orders, Comparator.comparing(Order::getOrderPlacedTime).reversed());

        CheckBox[] orderCheckBoxes = new CheckBox[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            orderCheckBoxes[i] = new CheckBox("Order Number: " + order.getOrderNumber() + "\nOrder Time: " + order.getOrderPlacedTime().format(controller.getDateTimeFormatter())
                    + "\nTotal Price: $" + order.getActualPricePaid() + "\nStatus: " + order.getStatus());
            orderCheckBoxes[i].setSelected(true); // Select all orders by default
        }

        CheckBox itemsCheckBox = new CheckBox("Include Items");
        itemsCheckBox.setSelected(true);
        CheckBox priceCheckBox = new CheckBox("Include Total Price");
        priceCheckBox.setSelected(true);
        CheckBox statusCheckBox = new CheckBox("Include Status");
        statusCheckBox.setSelected(true);

        Button exportButton = new Button("Export");
        exportButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("button");

        exportButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Orders");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            fileChooser.setInitialFileName("orders.csv");

            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.append("Order Number,Order Time");
                    if (itemsCheckBox.isSelected()) {
                        writer.append(",Items");
                    }
                    if (priceCheckBox.isSelected()) {
                        writer.append(",Total Price");
                    }
                    if (statusCheckBox.isSelected()) {
                        writer.append(",Status");
                    }
                    writer.append("\n");

                    for (int i = 0; i < orders.size(); i++) {
                        if (orderCheckBoxes[i].isSelected()) {
                            Order order = orders.get(i);
                            writer.append(order.getOrderNumber() + "," + order.getOrderPlacedTime().format(controller.getDateTimeFormatter()));
                            if (itemsCheckBox.isSelected()) {
                                String items = order.getItems().entrySet().stream()
                                        .map(entry -> entry.getKey().getName() + " x" + entry.getValue())
                                        .reduce((a, b) -> a + "; " + b)
                                        .orElse("");
                                writer.append(",\"" + items + "\"");
                            }
                            if (priceCheckBox.isSelected()) {
                                writer.append("," + order.getActualPricePaid());
                            }
                            if (statusCheckBox.isSelected()) {
                                writer.append("," + order.getStatus());
                            }
                            writer.append("\n");
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backButton.setOnAction(e -> showDashboard(user));

        root.getChildren().add(label);
        root.getChildren().addAll(orderCheckBoxes);
        root.getChildren().addAll(itemsCheckBox, priceCheckBox, statusCheckBox, exportButton, backButton);

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Burrito King - Export Orders");
        primaryStage.show();
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
