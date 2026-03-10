# Work done & output evidence

This document summarizes what was implemented and how to verify it with the screenshots in this folder.

---

## 1. What we built

- **Menu management** — Add/list/get/delete menu items via REST API and store in DB.
- **Order processing** — Create orders with line items (menu item + quantity), list orders, update status (PENDING → COMPLETED).
- **Inventory** — Track items (name, quantity, unit, reorder threshold), list low-stock items.
- **Sales analytics** — Revenue and order counts over a date range; top-selling menu items.

**Stack:** Java 11, Spring Boot 2.7, Maven, Spring Data JPA, H2 (dev) / MySQL.

---

## 2. How we ran it

1. Started the backend with H2 (no MySQL needed):
   ```bash
   cd backend
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```
2. Opened H2 Console at `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:mem:restaurant`, user `sa`, no password.
3. Inserted sample menu items in H2, then called the APIs (browser and curl).

**Evidence:** See `06-backend-running.png` (app started) and `02-h2-console.png` (DB data).

---

## 3. Outputs we got

| Check | What we did | Screenshot |
|-------|-------------|------------|
| Menu in DB | Inserted Burger, Fries, Soda in H2; ran `SELECT * FROM MENU_ITEMS` | `02-h2-console.png` |
| Menu API | Opened `http://localhost:8080/menu` in browser — same 3 items as JSON | `01-menu-api.png` |
| Orders | Created order via API; listed at `http://localhost:8080/orders` | `03-orders-api.png` |
| Orders in DB | Ran `SELECT * FROM ORDERS` and `SELECT * FROM ORDER_ITEMS` in H2 | `04-orders-h2.png` |
| Analytics | Opened `http://localhost:8080/analytics/sales` (revenue, order count) | `05-analytics.png` |

*(If your screenshot names differ, edit this table to match.)*

---

## 4. Code changes (what’s in the repo)

- **`backend/`** — Full Spring Boot app: `controller`, `service`, `repository`, `model`, `config`.
- **Root `README.md`** — Project overview, tech stack, quick start.
- **`backend/README.md`** — API list and run instructions.

All of this is committed and pushed to the GitHub repo.

---

*Add your screenshot files to this folder, then commit and push to show the working output on your profile.*
