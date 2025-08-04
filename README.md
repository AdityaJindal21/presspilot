# 🗞️ PressPilot — Newspaper Management System

PressPilot is a full-featured **Java desktop application** designed to manage newspaper distribution operations efficiently. It offers intuitive tools for handling customers, hawkers, subscriptions, billing, analytics, and report exports — all through a modern JavaFX GUI.

---

## 🚀 What It Does

- 📋 **Customer Management** – Add, update, delete, and track customer data, preferences, and subscriptions  
- 🚴 **Hawker & Area Management** – Assign delivery zones, manage hawker info, and coverage areas  
- 🧾 **Billing System** – Automatically calculate and generate bills based on subscription data  
- 📈 **Analytics & Charts** – Visualize trends with interactive Pie, Bar, and Line charts  
- 📤 **Excel Export** – Generate customer and billing reports  
- 🗄️ **Database Integration** – Store and retrieve data via MySQL using JDBC  

---

## 💡 Inspiration

Managing newspaper logistics manually can be inefficient and error-prone. This system was inspired by real-world distribution challenges — especially for small publishers — and aims to **digitize and simplify** operations from subscriptions to billing and reporting.

---

## 🛠️ How I Built It

- **UI Layer**: JavaFX (FXML) with SceneBuilder for drag-and-drop layout building  
- **Backend**: Java with JDBC for database communication  
- **Database**: MySQL relational storage  
- **Charts**: JavaFX built-in chart libraries (Pie, Bar, Line)  
- **Excel Export**: Apache POI to generate downloadable reports

---

## ⚙️ Challenges Faced

- Designing a clean, user-friendly UI in JavaFX while managing multiple form states  
- Handling SQL joins and data integrity across multiple tables  
- Implementing dynamic Excel export with Apache POI  
- Creating real-time visualizations from live data  

---

## ✅ Accomplishments

- Built a complete Java desktop app with fully functional CRUD operations  
- Designed a responsive and intuitive UI using JavaFX + SceneBuilder  
- Implemented real-time data visualization through charts and reports  
- Enabled offline data access via Excel report generation  

---

## 📚 What I Learned

- Advanced Java concepts like multi-threading and MVC in desktop applications  
- Effective use of **SceneBuilder** for scalable UI design  
- Working with **JDBC** and **MySQL** for persistent data storage  
- How to implement **data export** functionality with Apache POI  
- Structuring a medium-sized JavaFX project with modular packages  

---

## 🧰 Tech Stack

| Layer         | Tools / Technologies                     |
|---------------|------------------------------------------|
| 🖥️ Language      | Java                                   |
| 🎨 UI Framework  | JavaFX with SceneBuilder               |
| 🔗 Backend       | JDBC                                   |
| 🗄️ Database      | MySQL                                  |
| 📊 Charts        | JavaFX Chart Library                   |
| 📤 Exporting     | Apache POI *(optional)*                |

---

## 🧪 Instructions for Running Locally

> ⚠️ Requirements: Java 8+, JavaFX SDK, MySQL, SceneBuilder (optional), and an IDE (like IntelliJ or Eclipse)

### 1. Clone the Repository

```bash
git clone https://github.com/AdityaJindal21/presspilot.git
cd presspilot
```

### 2. Setup MySQL Database

- Download MySQL and setup the database
- Update your database credentials in the MySQLDBConnection file

```java
String url = "jdbc:mysql://localhost:3306/presspilot_db";
String user = "your_mysql_user";
String password = "your_mysql_password";
```

### 3. Add Required JARs

- Add MySQL Connector JAR (for JDBC)

> You can find this in the `/lib/` folder or download them from official sources.

### 4. Launch the App

- Open the project in your IDE
- Run the `HelloApplication.java` file
- Enjoy 🚀

## 📬 Contact

Built with ❤️ by **Aditya Jindal**  
Open to feedback, contributions, and collaboration!

