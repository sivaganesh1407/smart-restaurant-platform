# Project output & evidence

This folder holds **screenshots and notes** that show the Smart Restaurant Platform backend running and the outputs we got.

## Suggested screenshots to add

Put your screenshot images in this folder. Use these names (or similar) so the evidence doc can reference them:

| File name | What it shows |
|-----------|----------------|
| `01-menu-api.png` | Browser at `http://localhost:8080/menu` showing JSON list of menu items (Burger, Fries, Soda) |
| `02-h2-console.png` | H2 Console connected to `jdbc:h2:mem:restaurant` with `SELECT * FROM MENU_ITEMS` results (3 rows) |
| `03-orders-api.png` | Browser at `http://localhost:8080/orders` showing order(s) in JSON |
| `04-orders-h2.png` | H2 Console with `SELECT * FROM ORDERS` and `SELECT * FROM ORDER_ITEMS` results |
| `05-analytics.png` | Browser at `http://localhost:8080/analytics/sales` or `/analytics/top-items` showing summary JSON |
| `06-backend-running.png` | Terminal with `mvn spring-boot:run -Dspring-boot.run.profiles=dev` and "Started RestaurantPlatformApplication" |

## How to capture

1. **API / browser:** Open the URL, take a screenshot (e.g. Cmd+Shift+4 on Mac, or browser dev tools).
2. **H2:** Run the SQL in the console, then screenshot the result area.
3. **Terminal:** Run the app, wait for "Started...", then screenshot the terminal.

## After adding screenshots

Update [EVIDENCE.md](EVIDENCE.md) if you use different file names, then commit and push:

```bash
git add docs/
git commit -m "Add output screenshots and project evidence"
git push origin main
```
