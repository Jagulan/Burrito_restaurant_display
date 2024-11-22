package controller;

import model.User;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class UserManager {
    private static UserManager instance;
    private List<User> users;
    private UserDAO userDAO;

    private UserManager() {
        users = new ArrayList<>();
        userDAO = new UserDAO();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User loginUser(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerUser(String username, String password, String firstName, String lastName) {
        try {
            if (userDAO.getUserByUsername(username) != null) {
                return false;
            }
            User user = new User(username, password, firstName, lastName);
            userDAO.addUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void deleteUser(String username) {
        try {
            userDAO.deleteUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
