# E-Commerce Backend API

A Spring Boot REST API for an e-commerce platform, built following the **Backend API Development Workflow with IBM BOB/KIRO** capstone project guide.

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java 21 |
| Framework | Spring Boot 3.3.5 |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Documentation | SpringDoc OpenAPI / Swagger UI |
| Build Tool | Maven |

---

## Project Structure

```
src/
└── main/
    ├── java/com/ecommerce/api/
    │   ├── EcommerceApiApplication.java
    │   ├── config/
    │   │   ├── AppConfig.java          # BCrypt password encoder bean
    │   │   ├── OpenApiConfig.java      # Swagger/OpenAPI configuration
    │   │   └── SecurityConfig.java     # Spring Security (stateless)
    │   ├── controller/
    │   │   ├── ProductController.java  # GET, POST, PUT, DELETE /api/v1/products
    │   │   ├── UserController.java     # GET, POST, PUT, DELETE /api/v1/users
    │   │   ├── OrderController.java    # GET, POST, PUT, DELETE /api/v1/orders
    │   │   └── CartController.java     # GET, POST, PUT, DELETE /api/v1/users/{id}/cart
    │   ├── dto/
    │   │   ├── ProductRequest.java / ProductResponse.java
    │   │   ├── UserRequest.java / UserResponse.java
    │   │   ├── OrderRequest.java / OrderResponse.java / OrderStatusRequest.java
    │   │   └── CartItemRequest.java / CartResponse.java
    │   ├── entity/
    │   │   ├── Product.java
    │   │   ├── User.java
    │   │   ├── Order.java / OrderItem.java
    │   │   └── Cart.java / CartItem.java
    │   ├── exception/
    │   │   ├── ResourceNotFoundException.java
    │   │   ├── BadRequestException.java
    │   │   └── GlobalExceptionHandler.java
    │   ├── repository/
    │   │   ├── ProductRepository.java
    │   │   ├── UserRepository.java
    │   │   ├── OrderRepository.java
    │   │   ├── CartRepository.java
    │   │   └── CartItemRepository.java
    │   └── service/
    │       ├── ProductService.java
    │       ├── UserService.java
    │       ├── OrderService.java
    │       └── CartService.java
    └── resources/
        └── application.properties
```

---

## Step 6: Configure Database

### Install PostgreSQL

Download and install from [https://www.postgresql.org/download/](https://www.postgresql.org/download/)

### Create the database

```sql
CREATE DATABASE ecommerce_db;
CREATE USER ecommerce_user WITH PASSWORD 'your_secure_password';
GRANT ALL PRIVILEGES ON DATABASE ecommerce_db TO ecommerce_user;
```

### Set environment variables (NEVER hardcode credentials)

```bash
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=ecommerce_db
export DB_USERNAME=ecommerce_user
export DB_PASSWORD=your_secure_password
```

The `application.properties` reads these via `${DB_HOST:localhost}` syntax.

---

## Step 7: Run Application

```bash
# Build the project
./mvnw clean install

# Start the application
./mvnw spring-boot:run

# Verify it started
# → http://localhost:8080/swagger-ui.html
```

---

## Step 8: Test APIs

### Swagger UI (recommended)
Open: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Insomnia / curl examples

**Create a product:**
```bash
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","description":"15-inch laptop","price":999.99,"stock":50,"category":"Electronics"}'
```

**Get all products (with search & filter):**
```bash
curl "http://localhost:8080/api/v1/products?search=laptop&category=Electronics&page=0&size=10"
```

**Register a user:**
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Doe","email":"jane@example.com","password":"SecurePass123!"}'
```

**Create an order:**
```bash
curl -X POST http://localhost:8080/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"items":[{"productId":1,"quantity":2}]}'
```

**Add item to cart:**
```bash
curl -X POST http://localhost:8080/api/v1/users/1/cart \
  -H "Content-Type: application/json" \
  -d '{"productId":1,"quantity":1}'
```

### API Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/v1/products` | List/search/filter products |
| GET | `/api/v1/products/{id}` | Get product by ID |
| POST | `/api/v1/products` | Create product |
| PUT | `/api/v1/products/{id}` | Update product |
| DELETE | `/api/v1/products/{id}` | Delete product |
| GET | `/api/v1/users` | List all users |
| GET | `/api/v1/users/{id}` | Get user by ID |
| POST | `/api/v1/users` | Register user |
| PUT | `/api/v1/users/{id}` | Update user |
| DELETE | `/api/v1/users/{id}` | Delete user |
| GET | `/api/v1/orders` | List all orders |
| GET | `/api/v1/orders/{id}` | Get order by ID |
| GET | `/api/v1/orders/user/{userId}` | Get orders by user |
| POST | `/api/v1/orders` | Create order |
| PUT | `/api/v1/orders/{id}` | Update order status |
| DELETE | `/api/v1/orders/{id}` | Cancel order |
| GET | `/api/v1/users/{userId}/cart` | Get cart |
| POST | `/api/v1/users/{userId}/cart` | Add item to cart |
| PUT | `/api/v1/users/{userId}/cart/{itemId}` | Update cart item |
| DELETE | `/api/v1/users/{userId}/cart/{itemId}` | Remove cart item |
| DELETE | `/api/v1/users/{userId}/cart` | Clear cart |

---

## Step 9: Git Check-in

```bash
# Initialize git (if new project)
git init

# Create feature branch
git checkout -b feature/api-implementation

# Stage all files
git add .

# Commit
git commit -m "Implement Spring Boot API for e-commerce"

# Push
git push origin feature/api-implementation
```

Then create a Pull Request with this README as API documentation.
