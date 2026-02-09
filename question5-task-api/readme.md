# ✅ Question 5 – Task Management API

## 📌 Project Overview

This project is a **Spring Boot RESTful API** for managing tasks (to-do list).

The API allows users to:

* View all tasks
* View a task by ID
* Filter tasks by completion status
* Filter tasks by priority
* Create a new task
* Update a task
* Mark a task as completed
* Delete a task

⚠️ **Note:**
This project uses **in-memory storage (List)** only.
No database, service, or repository layer is used, as required.

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
* **Artifact:** `question5-task-api`
* **Project Type:** Maven
* **Packaging:** Jar

---

## 📂 Project Structure

```
question5-task-api/
├─ pom.xml
├─ README.md
└─ src/
   └─ main/
      ├─ java/
      │  └─ auca/ac/rw/question5taskapi/
      │     ├─ Project5Application.java
      │     ├─ controller/
      │     │  └─ task/
      │     │     └─ TaskController.java
      │     └─ model/
      │        └─ task/
      │           └─ Task.java
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

## 📌 API Endpoints

---

### 1️⃣ Get all tasks

**GET** `/api/tasks`

Example:

```
GET http://localhost:8080/api/tasks
```

Response:

* `200 OK`

---

### 2️⃣ Get task by ID

**GET** `/api/tasks/{taskId}`

Example:

```
GET http://localhost:8080/api/tasks/1
```

Responses:

* `200 OK`
* `404 Not Found`

---

### 3️⃣ Get tasks by completion status

**GET** `/api/tasks/status?completed={true|false}`

Example:

```
GET http://localhost:8080/api/tasks/status?completed=false
```

Response:

* `200 OK`

---

### 4️⃣ Get tasks by priority

**GET** `/api/tasks/priority/{priority}`

Example:

```
GET http://localhost:8080/api/tasks/priority/HIGH
```

Response:

* `200 OK`

---

### 5️⃣ Create a new task

**POST** `/api/tasks`

Example:

```
POST http://localhost:8080/api/tasks
```

Request Body (JSON):

```json
{
  "title": "Read notes",
  "description": "Read Spring Boot notes",
  "completed": false,
  "priority": "MEDIUM",
  "dueDate": "2026-02-20"
}
```

Response:

* `201 Created`

---

### 6️⃣ Update a task

**PUT** `/api/tasks/{taskId}`

Example:

```
PUT http://localhost:8080/api/tasks/1
```

Request Body (JSON):

```json
{
  "taskId": 1,
  "title": "Read notes",
  "description": "Updated description",
  "completed": false,
  "priority": "HIGH",
  "dueDate": "2026-02-22"
}
```

Responses:

* `200 OK`
* `404 Not Found`

---

### 7️⃣ Mark task as completed

**PATCH** `/api/tasks/{taskId}/complete`

Example:

```
PATCH http://localhost:8080/api/tasks/1/complete
```

Response:

* `200 OK`

---

### 8️⃣ Delete a task

**DELETE** `/api/tasks/{taskId}`

Example:

```
DELETE http://localhost:8080/api/tasks/3
```

Responses:

* `204 No Content`
* `404 Not Found`

---

## 🧪 Testing

All endpoints were tested using:

* Browser (GET requests)
* Postman (POST, PUT, PATCH, DELETE)

---

## ✅ Features Implemented

* RESTful API for task management
* Proper HTTP methods (GET, POST, PUT, PATCH, DELETE)
* Proper HTTP status codes (200, 201, 204, 404)
* Filtering by status and priority
* In-memory data storage
* Clean package structure (`controller` and `model`)
* Beginner-friendly code

