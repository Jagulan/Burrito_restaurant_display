# 🌯 Burrito King Restaurant Application

![JavaFX Badge](https://img.shields.io/badge/JavaFX-86B202?style=for-the-badge&logo=javafx&logoColor=white)
![Java Badge](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![JDBC Badge](https://img.shields.io/badge/JDBC-E44D26?style=for-the-badge&logo=oracle&logoColor=white)
![MVC Badge](https://img.shields.io/badge/Design%20Pattern-MVC-blue?style=for-the-badge)
![SOLID Principles Badge](https://img.shields.io/badge/Design%20Principle-SOLID-lightgrey?style=for-the-badge)
![Singleton Badge](https://img.shields.io/badge/Design%20Pattern-Singleton-orange?style=for-the-badge)

## 💡 Overview

This project is a robust desktop application for "Burrito King Restaurant," developed using **Java SE 17** and **JavaFX**. Designed to provide a seamless ordering and account management experience, it supports multiple users with unique profiles, allowing them to browse menus, place orders, manage their accounts, and utilize special VIP functionalities[cite: 16, 49].

The application adheres to strong Object-Oriented Programming (OOP) principles and incorporates advanced design patterns like MVC and Singleton, along with data persistence via JDBC. This project was a core component of my "Further Programming" unit at RMIT University, demonstrating the ability to translate complex requirements into a functional and user-friendly software solution[cite: 2, 3, 59].

## ✨ Features

This application implements a wide range of functionalities for both standard and VIP users:

### User Management
* **User Registration:** Users can create profiles with a unique username, password, first, and last name[cite: 16].
* **Secure Login:** Registered users can log in[cite: 17].
* **Profile Editing:** Users can change their first name, last name, and password[cite: 20].

### Ordering System
* **Interactive Menu:** Users can add food items (burritos, fries, soda) to a shopping basket and specify quantities[cite: 21].
* **Dynamic Shopping Basket:** Users can update the shopping basket (e.g., removing food items, updating quantity) until checkout[cite: 22].
* **Order Placement:** Users are notified of the total price and waiting time before placing an order[cite: 23].
* **Credit Card Payment:** Supports simulated payment with validation for 16-digit card numbers, future expiry dates, and 3-digit CVVs[cite: 25, 26].
* **Unique Order Generation:** A unique order number is generated and assigned upon order placement[cite: 28].
* **Order Status Tracking:** Orders can be "placed", "collected", or "cancelled"[cite: 31].
* **Order Collection:** Users can change an order status from "placed" to "collected", with a customizable (fake) collection time[cite: 33, 34].
* **Order Cancellation:** Users can cancel orders that have not been collected[cite: 37].

### Reporting & Persistence
* **View All Orders:** Users can view details of all orders, including date, time placed, total price, and status[cite: 30, 55].
* **Order Export (CSV):** Allows users to export historical orders to a CSV file, with customizable content and destination[cite: 39, 40, 41, 42, 43].
* **Data Persistence:** Utilizes JDBC to store program data, maintaining the application state across executions[cite: 76].

### VIP User Enhancements
* **VIP Upgrade Option:** Non-VIP users can upgrade by agreeing to receive promotions and providing a valid email[cite: 45, 47].
* **Discounted Meal Ordering:** VIP users receive a $3 discount on a standard meal (burrito, fries, soda)[cite: 49].
* **Credit System:** VIP users earn one credit for each dollar spent[cite: 50, 56].
* **Credit Redemption:** VIP users can redeem credits (100 credits = $1) when paying for orders[cite: 51, 53].

## 💻 Skills & Concepts Demonstrated

* **Programming Languages:** Java SE 17, JavaFX [cite: 1, 2]
* **Database Integration:** JDBC (Java Database Connectivity) for SQL database interaction and data persistence[cite: 76].
* **Object-Oriented Programming (OOP):** Applied core OOP principles, including encapsulation, data structures, and proper class design[cite: 77].
* **Design Patterns:**
    * **MVC (Model-View-Controller):** Structured the application for clear separation of concerns between data, presentation, and logic[cite: 94].
    * **Singleton Pattern:** Implemented for `UserManager` to ensure a single, globally accessible instance for managing user data[cite: 95].
* **SOLID Principles:** Applied to enhance maintainability, extensibility, and reduce coupling[cite: 97].
* **Data Structures:** Utilized appropriate data structures to optimize program efficiency[cite: 98].
* **User Interface Design:** Designed an easy-to-navigate and visually consistent GUI with clear user feedback[cite: 87, 88, 89, 90, 91].
* **Input Validation & Error Handling:** Robust validation for user inputs (e.g., payment details, order times) and comprehensive error handling[cite: 25, 26, 35, 89].
* **File Handling:** Implemented CSV export functionality for historical order data[cite: 39, 43].
* **Version Control:** Regular commitments to GitHub demonstrate a transparent and iterative development process[cite: 100].

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
    *(Assuming `App.java` is your main entry point in the `view` package, or similar setup with `MainView` being launched. If not, adjust `mainClass` in `pom.xml` or use your IDE's run configuration.)*
    ```bash
    mvn javafx:run
    ```
## 📞 Contact

Feel free to reach out if you have any questions or want to discuss this project further!

* **LinkedIn:** [linkedin.com/in/jagulans](https://linkedin.com/in/jagulans)
* **Email:** `gsjagulan@outlook.com`
