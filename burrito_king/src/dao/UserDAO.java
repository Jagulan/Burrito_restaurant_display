package dao;

import model.User;
import model.Order;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password, first_name, last_name, email, is_vip, credits) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getFirstName());
                stmt.setString(4, user.getLastName());
                stmt.setString(5, user.getEmail());
                stmt.setBoolean(6, user.isVIP());
                stmt.setInt(7, user.getCredits());
                stmt.executeUpdate();
            }
        }
    }

    public void updateUser(User user) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE users SET password = ?, first_name = ?, last_name = ?, email = ?, is_vip = ?, credits = ? WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getPassword());
                stmt.setString(2, user.getFirstName());
                stmt.setString(3, user.getLastName());
                stmt.setString(4, user.getEmail());
                stmt.setBoolean(5, user.isVIP());
                stmt.setInt(6, user.getCredits());
                stmt.setString(7, user.getUsername());
                stmt.executeUpdate();
            }
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        user = new User(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("first_name"),
                            rs.getString("last_name")
                        );
                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setVIP(rs.getBoolean("is_vip"));
                        user.setCredits(rs.getInt("credits"));
                    }
                }
            }
        }
        if (user != null) {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.getOrdersByUser(user);
            for (Order order : orders) {
                user.addOrder(order);
            }
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                    );
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setVIP(rs.getBoolean("is_vip"));
                    user.setCredits(rs.getInt("credits"));
                    users.add(user);
                }
            }
        }
        return users;
    }

    public void deleteUser(String username) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.executeUpdate();
            }
        }
    }
}
