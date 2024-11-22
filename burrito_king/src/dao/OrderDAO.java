package dao;

import model.Order;
import model.User;
import model.FoodItem;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDAO {

    public void saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, order_number, order_placed_time, total_price, actual_price_paid, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, order.getUser().getId());
            stmt.setInt(2, order.getOrderNumber());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(order.getOrderPlacedTime()));
            stmt.setDouble(4, order.getTotalPrice());
            stmt.setDouble(5, order.getActualPricePaid());
            stmt.setString(6, order.getStatus().toString());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setId(generatedKeys.getInt(1));
                    }
                }
                saveOrderItems(order); // Save the order items after getting the order ID
            }
        }
    }

    private void saveOrderItems(Order order) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, food_item, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
                stmt.setInt(1, order.getId());
                stmt.setString(2, entry.getKey().getName());
                stmt.setInt(3, entry.getValue());
                stmt.setDouble(4, entry.getKey().getPrice());

                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }

    public List<Order> getOrdersByUser(User user) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(user);
                    order.setId(rs.getInt("id"));
                    order.setOrderNumber(rs.getInt("order_number"));
                    order.setOrderPlacedTime(rs.getTimestamp("order_placed_time").toLocalDateTime());
                    order.setTotalPrice(rs.getDouble("total_price"));
                    order.setActualPricePaid(rs.getDouble("actual_price_paid"));
                    order.setStatus(Order.Status.valueOf(rs.getString("status")));
                    orders.add(order);
                }
            }
        }

        return orders;
    }

    public void updateOrderStatus(Order order) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getStatus().toString());
            stmt.setInt(2, order.getId());

            stmt.executeUpdate();
        }
    }
}
