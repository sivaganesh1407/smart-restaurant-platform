# Smart Restaurant Platform

**GitHub:** https://github.com/sivaganesh1407/smart-restaurant-platform

Built a REST API backend for restaurant management using Spring Boot — menu, orders, inventory, and sales analytics — simulating a modern POS backend.

---

## Features

- **Menu management** — Add, list, get, and delete menu items (CRUD)
- **Order processing** — Create orders with line items, update status (PENDING → COMPLETED)
- **Inventory tracking** — Track stock, reorder thresholds, low-stock listing
- **Sales analytics** — Revenue summary and top-selling items over date range
- **H2 in-memory option** — Run and test without MySQL (dev profile)
- **Layered architecture** — Controller → Service → Repository → Model, global exception handling

---

## Tech stack

**Java 11 · Spring Boot 2.7 · Maven · Spring Web · Spring Data JPA · H2 / MySQL · Lombok**

---

## Output / proof

Screenshots below show the app running and the APIs returning data:

| Proof | What it shows |
|-------|----------------|
| **Menu API** | Browser at `http://localhost:8080/menu` — JSON list of menu items (e.g. Burger, Fries, Soda) |
| **H2 database** | H2 Console with `SELECT * FROM MENU_ITEMS` — same data stored in DB |
| **Orders API** | `http://localhost:8080/orders` — orders with line items and totals |
| **Orders in DB** | H2: `SELECT * FROM ORDERS` and `SELECT * FROM ORDER_ITEMS` — 2 orders, 4 line items |
| **Analytics** | `http://localhost:8080/analytics/sales` — total revenue, completed orders |
| **Backend running** | Terminal: `mvn spring-boot:run -Dspring-boot.run.profiles=dev` — "Started RestaurantPlatformApplication" |

*Add your screenshot images to this folder (`docs/`) with names like `01-menu-api.png`, `02-h2-console.png`, etc. Then you can paste 2–3 of them into LinkedIn or your portfolio as proof.*

---

*Use this summary and the screenshots in `docs/` when sharing the project on LinkedIn or your resume.*
