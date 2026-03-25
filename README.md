# Smart Restaurant Platform

**Disclaimer:** This is an **independent, personal/educational project**. It is not affiliated with, endorsed by, or connected to any employer or commercial organization.

A backend system for a restaurant management platform that supports **menu management**, **order processing**, **inventory tracking**, and **sales analytics** — simulating a modern restaurant POS backend.

## Features

- **Menu Management** — Full CRUD with validation (add, list, get, update, delete)
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
│   ├── application.properties         # H2 in-memory (default, no DB install)
│   ├── application-mysql.properties   # MySQL (profile `mysql`)
│   └── application-dev.properties     # Legacy no-op profile (see backend README)
└── README.md          # API overview and run instructions
```

## Quick Start

**Run with H2 (default — no database install):**
```bash
cd backend
mvn spring-boot:run
```
- API: http://localhost:8080  
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:restaurant`, user: `sa`, password: empty)

**Run with MySQL:**  
Use the `mysql` Spring profile and set credentials via environment variables (never commit passwords):

```bash
cd backend
export MYSQL_USERNAME=root
export MYSQL_PASSWORD=yourpassword
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

Optional: override the JDBC URL with `MYSQL_URL`. You can also use a local, untracked `application-local.properties` (see `.gitignore`).

See [backend/README.md](backend/README.md) for full API documentation.

**Output & evidence:** Screenshots and a short “work done” summary are in [docs/](docs/) (see [docs/EVIDENCE.md](docs/EVIDENCE.md)). Add your own run screenshots there to show the project working.

## API Overview

| Area        | Examples |
|------------|----------|
| **Menu**   | `POST /menu`, `GET /menu`, `GET /menu/{id}`, `DELETE /menu/{id}` |
| **Orders** | `POST /orders`, `GET /orders`, `PATCH /orders/{id}/status` |
| **Inventory** | `POST /inventory`, `GET /inventory`, `GET /inventory/low-stock` |
| **Analytics** | `GET /analytics/sales`, `GET /analytics/top-items` |

---

*Demonstrates REST APIs, JPA, and layered architecture for learning and portfolio use.*
