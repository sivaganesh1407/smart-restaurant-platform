# Smart Restaurant Platform

A backend system for a restaurant management platform that supports **menu management**, **order processing**, **inventory tracking**, and **sales analytics** — simulating a modern restaurant POS backend.

## Features

- **Menu Management** — Add, list, get, and delete menu items
- **Order Processing** — Create orders with line items, update order status (PENDING → COMPLETED)
- **Inventory Tracking** — Track stock, reorder thresholds, and low-stock alerts
- **Sales Analytics** — Revenue summary and top-selling items over a date range

## Tech Stack

- **Java 11** · **Spring Boot 2.7** · **Maven**
- **Spring Web** · **Spring Data JPA**
- **MySQL** (production) / **H2** (development)
- **Lombok**

## Project Structure

```
backend/
├── src/main/java/com/restaurant/platform/
│   ├── controller/   # REST APIs (Menu, Orders, Inventory, Analytics)
│   ├── service/      # Business logic
│   ├── repository/   # JPA repositories
│   ├── model/        # Entities and DTOs
│   └── config/       # App config and exception handling
├── src/main/resources/
│   ├── application.properties      # MySQL (default)
│   └── application-dev.properties  # H2 in-memory (no MySQL needed)
└── README.md          # API overview and run instructions
```

## Quick Start

**Run with H2 (no database setup):**
```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
- API: http://localhost:8080  
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:restaurant`, user: `sa`, password: empty)

**Run with MySQL:**  
Set `spring.datasource.url`, `username`, and `password` in `backend/src/main/resources/application.properties`, then:
```bash
cd backend
mvn spring-boot:run
```

See [backend/README.md](backend/README.md) for full API documentation.

## API Overview

| Area        | Examples |
|------------|----------|
| **Menu**   | `POST /menu`, `GET /menu`, `GET /menu/{id}`, `DELETE /menu/{id}` |
| **Orders** | `POST /orders`, `GET /orders`, `PATCH /orders/{id}/status` |
| **Inventory** | `POST /inventory`, `GET /inventory`, `GET /inventory/low-stock` |
| **Analytics** | `GET /analytics/sales`, `GET /analytics/top-items` |

---

*Built as a portfolio project demonstrating REST APIs, JPA, and layered architecture.*
