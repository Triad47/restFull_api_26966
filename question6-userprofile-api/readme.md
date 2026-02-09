
# 👤 Bonus – User Profile API

## 📌 Project Overview

This project is a **Spring Boot RESTful API** for managing **user profiles**.
It is the **Bonus Question** and uses a **custom API response wrapper** as required.

The API allows:

* Creating, viewing, updating, and deleting user profiles
* Searching users by username, country, and age range
* Activating and deactivating user profiles
* Returning responses wrapped in a custom `ApiResponse<T>` object

⚠️ **Note:**
This project uses **in-memory storage (List)** only.
No database, service, or repository layer is used.

---

## 🛠️ Technologies Used

* Java 17
* Spring Boot
* Spring Web
* Maven
* Postman (for testing)

---

## 📦 Project Details

* **Group:** `auca.ac.rw`
* **Artifact:** `question6-userprofile-api`
* **Project Type:** Maven
* **Packaging:** Jar

---

## 📂 Project Structure

```
question6-userprofile-api/
├─ pom.xml
├─ README.md
└─ src/
   └─ main/
      ├─ java/
      │  └─ auca/ac/rw/question6userprofileapi/
      │     ├─ Project6Application.java
      │     ├─ controller/
      │     │  └─ userprofile/
      │     │     └─ UserProfileController.java
      │     └─ model/
      │        └─ userprofile/
      │           ├─ ApiResponse.java
      │           └─ UserProfile.java
      └─ resources/
         └─ application.properties
```

---

## ▶️ How to Run the Application

1. Open the project in **IntelliJ IDEA** or **VS Code**
2. Ensure **Java 17** is installed
3. Open terminal inside the project folder
4. Run the application:

```bash
mvn spring-boot:run
```

5. Application will start at:

```
http://localhost:8080
```

---

## 📌 API Response Wrapper

All responses are wrapped in the following structure:

```json
{
  "success": true,
  "message": "Operation message",
  "data": { }
}
```

---

## 📌 API Endpoints

---

### 1️⃣ Create user profile

**POST** `/api/users`

Request Body:

```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "age": 24,
  "country": "Rwanda",
  "bio": "Software developer",
  "active": true
}
```

Response:

* `201 Created`

---

### 2️⃣ Get all user profiles

**GET** `/api/users`

Response:

* `200 OK`

---

### 3️⃣ Get user profile by ID

**GET** `/api/users/{userId}`

Example:

```
GET http://localhost:8080/api/users/1
```

Responses:

* `200 OK`
* `404 Not Found`

---

### 4️⃣ Update user profile

**PUT** `/api/users/{userId}`

Example:

```json
{
  "userId": 1,
  "username": "john_doe",
  "email": "john.updated@example.com",
  "fullName": "John Doe",
  "age": 25,
  "country": "Rwanda",
  "bio": "Senior developer",
  "active": true
}
```

Response:

* `200 OK`

---

### 5️⃣ Delete user profile

**DELETE** `/api/users/{userId}`

Example:

```
DELETE http://localhost:8080/api/users/2
```

Responses:

* `204 No Content`
* `404 Not Found`

---

### 6️⃣ Search user by username

**GET** `/api/users/search/username?username=john_doe`

Response:

* `200 OK`

---

### 7️⃣ Search users by country

**GET** `/api/users/search/country?country=Rwanda`

Response:

* `200 OK`

---

### 8️⃣ Search users by age range

**GET** `/api/users/search/age?min=18&max=30`

Response:

* `200 OK`

---

### 9️⃣ Activate user profile

**PATCH** `/api/users/{userId}/activate`

Response:

* `200 OK`

---

### 🔟 Deactivate user profile

**PATCH** `/api/users/{userId}/deactivate`

Response:

* `200 OK`

---

## 🧪 Testing

* GET requests tested in browser
* POST, PUT, PATCH, DELETE tested using **Postman**

---

## ✅ Features Implemented

* Full CRUD operations
* Search by username, country, and age range
* Activate / deactivate user profiles
* Custom response wrapper (`ApiResponse<T>`)
* Proper HTTP status codes (200, 201, 204, 404)
* Clean package structure (`controller` and `model`)
* Beginner-friendly implementation
