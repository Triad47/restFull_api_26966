
# 🛒 Question 4 – E-Commerce Product API

## 📌 Project Overview

This project is a **Spring Boot RESTful API** for managing an **E-Commerce Product Catalog**.

The API allows users to:

* View all products (with optional pagination)
* View product details by ID
* Filter products by category
* Filter products by brand
* Search products by keyword (name or description)
* Filter products by price range
* View only products that are in stock
* Add a new product
* Update product details
* Update stock quantity
* Delete a product

⚠️ **Note:**
This project uses **in-memory data storage (List)**.
No database, repository, or service layer is used as required.

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
* **Artifact:** `question4-ecommerce-api`
* **Project Type:** Maven
* **Packaging:** Jar

---

## 📂 Project Structure

```
question4-ecommerce-api/
├─ pom.xml
├─ README.md
└─ src/
   └─ main/
      ├─ java/
      │  └─ auca/ac/rw/question4ecommerceapi/
      │     ├─ Project4Application.java
      │     ├─ controller/
      │     │  └─ ecommerce/
      │     │     └─ ProductController.java
      │     └─ model/
      │        └─ ecommerce/
      │           └─ Product.java
      └─ resources/
         └─ application.properties
```

---

## ▶️ How to Run the Application

1. Open the project in **IntelliJ IDEA** or **VS Code**
2. Ensure Java 17 is installed
3. Open terminal inside the project folder
4. Run:

```bash
mvn spring-boot:run
```

5. Application will run on:

```
http://localhost:8080
```

---

## 📌 API Endpoints

---

### 1️⃣ Get all products

**GET** `/api/products`

Example:

```
GET http://localhost:8080/api/products
```

Response:

* `200 OK`

---

### 2️⃣ Get all products with pagination

**GET** `/api/products?page={page}&limit={limit}`

Example:

```
GET http://localhost:8080/api/products?page=1&limit=3
```

Response:

* `200 OK`

---

### 3️⃣ Get product by ID

**GET** `/api/products/{productId}`

Example:

```
GET http://localhost:8080/api/products/1
```

Responses:

* `200 OK` → product found
* `404 Not Found` → product not found

---

### 4️⃣ Get products by category

**GET** `/api/products/category/{category}`

Example:

```
GET http://localhost:8080/api/products/category/Electronics
```

Response:

* `200 OK`

---

### 5️⃣ Get products by brand

**GET** `/api/products/brand/{brand}`

Example:

```
GET http://localhost:8080/api/products/brand/Apple
```

Response:

* `200 OK`

---

### 6️⃣ Search products by keyword

**GET** `/api/products/search?keyword={keyword}`

Example:

```
GET http://localhost:8080/api/products/search?keyword=laptop
```

Response:

* `200 OK`

---

### 7️⃣ Get products in price range

**GET** `/api/products/price-range?min={min}&max={max}`

Example:

```
GET http://localhost:8080/api/products/price-range?min=200&max=1000
```

Response:

* `200 OK`

---

### 8️⃣ Get products in stock

**GET** `/api/products/in-stock`

Example:

```
GET http://localhost:8080/api/products/in-stock
```

Response:

* `200 OK`

---

### 9️⃣ Add a new product

**POST** `/api/products`

Example:

```
POST http://localhost:8080/api/products
```

Request Body (JSON):

```json
{
  "name": "MacBook Pro",
  "description": "Apple laptop computer",
  "price": 2500,
  "category": "Computers",
  "stockQuantity": 5,
  "brand": "Apple"
}
```

Response:

* `201 Created`

---

### 🔟 Update product details

**PUT** `/api/products/{productId}`

Example:

```
PUT http://localhost:8080/api/products/1
```

Request Body (JSON):

```json
{
  "productId": 1,
  "name": "iPhone 14 Pro",
  "description": "Updated Apple smartphone",
  "price": 1400,
  "category": "Electronics",
  "stockQuantity": 20,
  "brand": "Apple"
}
```

Responses:

* `200 OK` → updated successfully
* `404 Not Found` → product not found

---

### 1️⃣1️⃣ Update stock quantity

**PATCH** `/api/products/{productId}/stock?quantity={quantity}`

Example:

```
PATCH http://localhost:8080/api/products/1/stock?quantity=50
```

Responses:

* `200 OK` → stock updated
* `404 Not Found` → product not found

---

### 1️⃣2️⃣ Delete a product

**DELETE** `/api/products/{productId}`

Example:

```
DELETE http://localhost:8080/api/products/3
```

Responses:

* `204 No Content` → deleted successfully
* `404 Not Found` → product not found

---

## 🧪 Testing

All endpoints were tested using:

* Browser (GET requests)
* Postman (POST, PUT, PATCH, DELETE)

---

## ✅ Features Implemented

* REST API endpoints for product management
* Filtering by category, brand, price range
* Searching by keyword
* Pagination support
* Stock management
* Proper HTTP status codes (200, 201, 204, 404)
* Clean package structure (`controller` and `model`)
* In-memory list storage with at least 10 products

---
