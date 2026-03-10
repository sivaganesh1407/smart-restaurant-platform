# Smart Restaurant Platform – Backend

Spring Boot backend for menu management, orders, inventory, and sales analytics.

## Run the application

**With MySQL** (default):

```bash
mvn spring-boot:run
```

Set `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in `src/main/resources/application.properties` for your MySQL instance.

**Without MySQL** (H2 in-memory):

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

- Server: http://localhost:8080  
- H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:restaurant`, user: `sa`, password: empty)

## API overview

### Menu
| Method | Path | Description |
|--------|------|-------------|
| POST | /menu | Add menu item (validated: name, price ≥ 0, category, available) |
| GET | /menu | List all menu items |
| GET | /menu/{id} | Get menu item by id |
| PUT | /menu/{id} | Update menu item |
| DELETE | /menu/{id} | Delete menu item |

### Orders
| Method | Path | Description |
|--------|------|-------------|
| POST | /orders | Create order (body: `{ "items": [ { "menuItemId": 1, "quantity": 2 } ] }`) |
| GET | /orders | List all orders |
| GET | /orders/{id} | Get order by id |
| PATCH | /orders/{id}/status?status=COMPLETED | Update order status (PENDING, CONFIRMED, PREPARING, READY, COMPLETED, CANCELLED) |

### Inventory
| Method | Path | Description |
|--------|------|-------------|
| POST | /inventory | Add inventory item (name, quantity, unit, reorderThreshold) |
| GET | /inventory | List all inventory items |
| GET | /inventory/low-stock | List items at or below reorder threshold |
| GET | /inventory/{id} | Get inventory item by id |
| PATCH | /inventory/{id}?quantity=50 | Update quantity |
| DELETE | /inventory/{id} | Delete inventory item |

### Analytics
| Method | Path | Description |
|--------|------|-------------|
| GET | /analytics/sales?from=2025-01-01&to=2025-01-31 | Sales summary (revenue, order counts) |
| GET | /analytics/top-items?limit=5 | Top selling menu items (completed orders) |

## Tech stack

- Java 11, Spring Boot 2.7, Maven  
- Spring Web, Spring Data JPA, Bean Validation  
- MySQL (production) / H2 (dev profile)  
- Lombok  

## Tests

- `mvn test` — runs unit tests (e.g. MenuServiceTest with JUnit 5 and Mockito).
