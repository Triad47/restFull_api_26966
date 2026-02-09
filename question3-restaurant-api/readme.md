
# 🍽️ Question 3 – Restaurant Menu API

## 📌 Project Overview

This project is a **Spring Boot RESTful API** for managing a restaurant menu system.
It allows users to:

* View all menu items
* View a menu item by ID
* Filter menu items by category
* Get only available menu items
* Search menu items by name
* Add a new menu item
* Toggle menu item availability
* Delete a menu item

⚠️ **Note:**
This project uses **in-memory storage (List)** only.
No database, service layer, or repository is used as required.

---

## 🛠️ Technologies Used

* Java 17
* Spring Boot
* Spring Web
* Maven
* Postman (Testing)

---

## 📦 Project Details

* **Group:** `auca.ac.rw`
* **Artifact:** `question3-restaurant-api`
* **Project Type:** Maven
* **Packaging:** Jar

---

## 📂 Project Structure

```
question3-restaurant-api/
├─ pom.xml
├─ README.md
└─ src/
   └─ main/
      ├─ java/
      │  └─ auca/ac/rw/question3restaurantapi/
      │     ├─ Question3RestaurantApiApplication.java
      │     ├─ controller/
      │     │  └─ menu/
      │     │     └─ MenuController.java
      │     └─ model/
      │        └─ menu/
      │           └─ MenuItem.java
      └─ resources/
         └─ application.properties
```

---

## ▶️ How to Run the Application

1. Open the project in **IntelliJ IDEA** or **VS Code**
2. Make sure **Java 17** is installed
3. Open terminal inside the project folder
4. Run the project using:

```bash
mvn spring-boot:run
```

5. The project will run on:

```
http://localhost:8080
```

---

## 📌 API Endpoints

### 1️⃣ Get all menu items

**GET** `/api/menu`

Example:

```
GET http://localhost:8080/api/menu
```

Response: `200 OK`

---

### 2️⃣ Get menu item by ID

**GET** `/api/menu/{id}`

Example:

```
GET http://localhost:8080/api/menu/1
```

Responses:

* `200 OK` → item found
* `404 Not Found` → item not found

---

### 3️⃣ Get menu items by category

**GET** `/api/menu/category/{category}`

Example:

```
GET http://localhost:8080/api/menu/category/Main%20Course
```

Response: `200 OK`

---

### 4️⃣ Get menu items by availability

**GET** `/api/menu/available?available=true`

Example:

```
GET http://localhost:8080/api/menu/available?available=true
```

Responses:

* `200 OK`

---

### 5️⃣ Search menu items by name

**GET** `/api/menu/search?name={name}`

Example:

```
GET http://localhost:8080/api/menu/search?name=chicken
```

Response: `200 OK`

---

### 6️⃣ Add a new menu item

**POST** `/api/menu`

Example:

```
POST http://localhost:8080/api/menu
```

Request Body (JSON):

```json
{
  "name": "Burger",
  "description": "Cheese burger with fries",
  "price": 4500,
  "category": "Main Course",
  "available": true
}
```

Response:

* `201 Created`

---

### 7️⃣ Toggle menu item availability

**PUT** `/api/menu/{id}/availability`

Example:

```
PUT http://localhost:8080/api/menu/4/availability
```

Responses:

* `200 OK` → availability updated
* `404 Not Found` → item not found

---

### 8️⃣ Delete a menu item

**DELETE** `/api/menu/{id}`

Example:

```
DELETE http://localhost:8080/api/menu/2
```

Responses:

* `204 No Content` → deleted successfully
* `404 Not Found` → item not found

---

## 🧪 Testing

All endpoints can be tested using:

* Web Browser (GET endpoints)
* Postman (POST, PUT, DELETE)

---

## ✅ Features Implemented

* RESTful API design
* Proper HTTP methods (GET, POST, PUT, DELETE)
* Proper status codes (200, 201, 204, 404)
* In-memory list storage
* At least 8 menu items across categories
* Clean package structure (controller + model)

---

-