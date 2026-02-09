
# 📚 Question 1 – Library Book Management API

## Project Overview

This project is a **Spring Boot RESTful API** for managing library books.
It is part of the **Spring Boot RESTful API Practical Questions (Module 1–3)**.

The API allows users to:

* View all books
* View a book by ID
* Search books by title
* Add a new book
* Delete a book

⚠️ **Note:**
This project uses **in-memory data (List)** only.
No database, service layer, or repository is used, as required.

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
* **Artifact:** `question1-library-api`
* **Project Type:** Maven
* **Packaging:** Jar

---

## 📂 Project Structure

```
question1-library-api/
├─ pom.xml
├─ README.md
└─ src/
   └─ main/
      ├─ java/
      │  └─ auca/ac/rw/question1libraryapi/
      │     ├─ Question1LibraryApiApplication.java
      │     ├─ controller/
      │     │  └─ library/
      │     │     └─ BookController.java
      │     └─ model/
      │        └─ library/
      │           └─ Book.java
      └─ resources/
         └─ application.properties
```

---

## ▶️ How to Run the Application

1. Open the project in **IntelliJ IDEA** or **VS Code**
2. Make sure Java 17 is installed
3. Open terminal inside the project folder
4. Run the following command:

```bash
mvn spring-boot:run
```

5. The application will start on:

```
http://localhost:8080
```

---

## 📌 API Endpoints

### 1️⃣ Get all books

**GET** `/api/books`

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
```

---

### 2️⃣ Get book by ID

**GET** `/api/books/{id}`

Example:

```
GET /api/books/1
```

**Responses:**

* `200 OK` – Book found
* `404 Not Found` – Book not found

---

### 3️⃣ Search books by title

**GET** `/api/books/search?title={title}`

Example:

```
GET /api/books/search?title=clean
```

**Response (200 OK):**

```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "author": "Robert Martin",
    "isbn": "978-0132350884",
    "publicationYear": 2008
  }
]
```

---

### 4️⃣ Add a new book

**POST** `/api/books`

**Request Body (JSON):**

```json
{
  "title": "The Pragmatic Programmer",
  "author": "Andrew Hunt",
  "isbn": "978-0201616224",
  "publicationYear": 1999
}
```

**Response:**

* `201 Created`

---

### 5️⃣ Delete a book by ID

**DELETE** `/api/books/{id}`

Example:

```
DELETE /api/books/2
```

**Responses:**

* `204 No Content` – Book deleted
* `404 Not Found` – Book not found

---

## 🧪 Testing

All endpoints were tested using **Postman** and a web browser.

---

## ✅ Features Implemented

* RESTful endpoints
* Proper HTTP methods (GET, POST, DELETE)
* Proper HTTP status codes (200, 201, 204, 404)
* Clean package structure
* Beginner-friendly code

---

## 👩‍🎓 Author

**Student Name:** *(Your Name)*
**Student ID:** *(Your ID)*
**Institution:** AUCA

---

