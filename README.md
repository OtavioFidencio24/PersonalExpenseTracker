
# **Expense Management System**

This project is an Expense Management System built with **Java** using **Spring Boot**. It allows users to manage their expenses, categorize them, and track them by day, month, and category.

## **Features**

- **CRUD Operations**: Create, Read, Update, and Delete expenses.
- **Filters**: Fetch expenses by date, category, and month.
- **Expense Categories**: View all distinct expense categories.
- **JSON Data Loader**: Load and manage expense data from a JSON file (when in the `json` profile).
- **Database Integration**: Supports database integration (when in the `db` profile).

## **Technologies Used**

- **Java 17+**: Modern version of Java for backend development.
- **Spring Boot**: Framework used to simplify backend application development.
  - **Spring Data JPA**: Provides data persistence and database management with JPA.
  - **Spring Web**: RESTful APIs for handling HTTP requests.
  - **Spring Profiles**: Separate profiles for JSON-based data (dev) and database-based data (prod).
- **Jackson**: Used for parsing JSON files and converting Java objects to JSON format and vice versa.
- **H2 Database** (if used in the `db` profile): Lightweight, in-memory database for development and testing purposes.
- **Lombok**: A library that reduces boilerplate code for getters, setters, and constructors.

## **Packages and Libraries Used**

- **Spring Boot Dependencies**
  - `spring-boot-starter-web`: To create a web application with RESTful APIs.
  - `spring-boot-starter-data-jpa`: For data access and managing entities in the database.
  - `spring-boot-starter-test`: To test the application with unit tests (JUnit, Mockito).
  - `spring-boot-starter-validation`: To enable validation of request data.
  - `spring-boot-devtools`: For automatic restarts and live reload during development.
  
- **Jackson**
  - `jackson-databind`: For working with JSON, including reading from and writing to JSON files.

- **Lombok**
  - `lombok`: For simplifying code by generating boilerplate code like getters, setters, `toString()`, and constructors automatically.

- **H2 Database** (optional based on the profile used):
  - `h2`: In-memory database for testing or lightweight storage.

## **How to Run the Project**

### 1. Clone the repository:
```bash
git clone https://github.com/yourusername/ExpenseManagementSystem.git
cd ExpenseManagementSystem
```

### 2. Build the project with Maven:
```bash
mvn clean install
```

### 3. Run the project:
To run the application in the **JSON profile** (uses hardcoded data from `expenses.json`):
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=json
```

To run the application in the **DB profile** (uses a database for storage):
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=db
```

### 4. Access the API:
Once the application is running, you can access the following endpoints via `http://localhost:8080`:

- **GET** `/expenses`: Fetch all expenses.
- **GET** `/expenses/id/{id}`: Fetch a specific expense by its ID.
- **GET** `/expenses/categories`: Fetch all distinct expense categories.
- **GET** `/expenses/day/{day}`: Fetch expenses by specific day.
- **GET** `/expenses/category/{category}/month?month={month}`: Fetch expenses by category and month.
- **POST** `/expenses`: Add a new expense.
- **PUT** `/expenses/{id}`: Update an existing expense by its ID.
- **DELETE** `/expenses/{id}`: Delete an expense by its ID.

## **Project Structure**

- **`com.CreateUrlShortner.model`**: Contains entity classes (e.g., `Expense`).
- **`com.CreateUrlShortner.controller`**: Contains REST controllers for handling API requests.
- **`com.CreateUrlShortner.service`**: Contains service layer to manage business logic.
- **`com.CreateUrlShortner.repository`**: Contains repository interfaces for database operations.
- **`com.CreateUrlShortner.utils`**: Contains utility classes like `ExpenseDataLoader` for JSON data loading.
- **`resources/expenses.json`**: A sample JSON file for loading expenses (in the `json` profile).

## **How to Contribute**

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a pull request.

---

## **Contact**

Feel free to reach out if you have any questions or suggestions for improvement!

---

### 🚀 **Conclusion**

This project demonstrates your ability to work with **modern Java technologies** such as **Spring Boot**, **Spring Data JPA**, **Jackson**, and **Lombok**, showcasing your proficiency in backend development, including handling both **database-driven** and **JSON-based data**. It also reflects your understanding of **best practices** like **RESTful APIs**, **dependency injection**, and **profiles management** in Spring.
