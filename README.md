# 🌯 Burrito King Restaurant Application

![JavaFX Badge](https://img.shields.io/badge/JavaFX-86B202?style=for-the-badge&logo=javafx&logoColor=white)
![Java Badge](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![JDBC Badge](https://img.shields.io/badge/JDBC-E44D26?style=for-the-badge&logo=oracle&logoColor=white)
![MVC Badge](https://img.shields.io/badge/Design%20Pattern-MVC-blue?style=for-the-badge)
![SOLID Principles Badge](https://img.shields.io/badge/Design%20Principle-SOLID-lightgrey?style=for-the-badge)
![Singleton Badge](https://img.shields.io/badge/Design%20Pattern-Singleton-orange?style=for-the-badge)

## 💡 Overview

This project is a robust desktop application for "Burrito King Restaurant," developed using **Java SE 17** and **JavaFX**. Designed to provide a seamless ordering and account management experience, it supports multiple users with unique profiles, allowing them to browse menus, place orders, manage their accounts, and utilize special VIP functionalities.

The application adheres to strong Object-Oriented Programming (OOP) principles and incorporates advanced design patterns like MVC and Singleton, along with data persistence via JDBC. This project was a core component of my "Further Programming" unit at RMIT University, demonstrating the ability to translate complex requirements into a functional and user-friendly software solution.

## ✨ Features

This application implements a wide range of functionalities for both standard and VIP users:

### User Management
* **User Registration:** Users can create profiles with a unique username, password, first, and last name.
* **Secure Login:** Registered users can log in.
* **Profile Editing:** Users can change their first name, last name, and password.

### Ordering System
* **Interactive Menu:** Users can add food items (burritos, fries, soda) to a shopping basket and specify quantities.
* **Dynamic Shopping Basket:** Users can update the shopping basket (e.g., removing food items, updating quantity) until checkout.
* **Order Placement:** Users are notified of the total price and waiting time before placing an order.
* **Credit Card Payment:** Supports simulated payment with validation for 16-digit card numbers, future expiry dates, and 3-digit CVVs.
* **Unique Order Generation:** A unique order number is generated and assigned upon order placement.
* **Order Status Tracking:** Orders can be "placed", "collected", or "cancelled".
* **Order Collection:** Users can change an order status from "placed" to "collected", with a customizable (fake) collection time.
* **Order Cancellation:** Users can cancel orders that have not been collected.

### Reporting & Persistence
* **View All Orders:** Users can view details of all orders, including date, time placed, total price, and status.
* **Order Export (CSV):** Allows users to export historical orders to a CSV file, with customizable content and destination.
* **Data Persistence:** Utilizes JDBC to store program data, maintaining the application state across executions.

### VIP User Enhancements
* **VIP Upgrade Option:** Non-VIP users can upgrade by agreeing to receive promotions and providing a valid email.
* **Discounted Meal Ordering:** VIP users receive a $3 discount on a standard meal (burrito, fries, soda).
* **Credit System:** VIP users earn one credit for each dollar spent.
* **Credit Redemption:** VIP users can redeem credits (100 credits = $1) when paying for orders.

## 💻 Skills & Concepts Demonstrated

* **Programming Languages:** Java SE 17, JavaFX
* **Database Integration:** JDBC (Java Database Connectivity) for SQL database interaction and data persistence.
* **Object-Oriented Programming (OOP):** Applied core OOP principles, including encapsulation, data structures, and proper class design.
* **Design Patterns:**
    * **MVC (Model-View-Controller):** Structured the application for clear separation of concerns between data, presentation, and logic.
    * **Singleton Pattern:** Implemented for `UserManager` to ensure a single, globally accessible instance for managing user data.
* **SOLID Principles:** Applied to enhance maintainability, extensibility, and reduce coupling.
* **Data Structures:** Utilized appropriate data structures to optimize program efficiency.
* **User Interface Design:** Designed an easy-to-navigate and visually consistent GUI with clear user feedback.
* **Input Validation & Error Handling:** Robust validation for user inputs (e.g., payment details, order times) and comprehensive error handling.
* **File Handling:** Implemented CSV export functionality for historical order data.
* **Version Control:** Regular commitments to GitHub demonstrate a transparent and iterative development process.

## 🛠️ Installation & Setup

To run the Burrito King Restaurant Application locally:

### Prerequisites

* **Java Development Kit (JDK) 17 or later.**
* **Apache Maven.**
* **H2 Database:** This project uses an H2 file-based database for persistence.
* **H2 JDBC Driver:** The `pom.xml` should include the H2 JDBC driver dependency.

### Database Setup

The application automatically creates the necessary database schema (`users`, `orders`, `order_items` tables) if they don't exist, using the `DatabaseConnection.java` utility class. No manual setup is required beyond running the application's main entry point.

### Running the Application

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Jagulan/Burrito_restaurant_display.git](https://github.com/Jagulan/Burrito_restaurant_display.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd Burrito_restaurant_display
    ```
3.  **Build the project with Maven:**
    ```bash
    mvn clean install
    ```
4.  **Run the application:**
    ```bash
    mvn javafx:run
    ```
## 📞 Contact

Feel free to reach out if you have any questions or want to discuss this project further!

* **LinkedIn:** [linkedin.com/in/jagulans](https://linkedin.com/in/jagulans)
* **Email:** `gsjagulan@outlook.com`
