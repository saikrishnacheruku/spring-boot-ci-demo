# Spring Boot Basic CRUD & REST API Demo

A fast, lightweight Spring Boot application created at `D:\projects\springboot-crud-demo`.

## 🚀 How to Run the Application

Navigate to project folder in PowerShell / Terminal:
```powershell
cd D:\projects\springboot-crud-demo
mvn spring-boot:run
```
Or using the Maven wrapper:
```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on port `8080`: `http://localhost:8080`

---

## 📌 Available Endpoints

### 1. Simple String Return Endpoints (GET)

| Method | Endpoint | Description | Sample Output |
| :--- | :--- | :--- | :--- |
| **GET** | `/` | Root Welcome message | `"Welcome to Spring Boot Application! Endpoints available..."` |
| **GET** | `/api/hello` | Simple Hello string | `"Hello! Spring Boot basic GET endpoint is working perfectly."` |
| **GET** | `/api/status` | Current server status & time | `"Application Status: UP & Running smoothly at ..."` |
| **GET** | `/api/greet?name=YourName` | Greeting with parameter | `"Namaste / Welcome, YourName! Have a great day!"` |

---

### 2. Basic CRUD Endpoints (`/api/users`)

| Method | Endpoint | Description | Request Body |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/users` | Get all users | None |
| **GET** | `/api/users/{id}` | Get user by ID | None |
| **POST** | `/api/users` | Add a new user | `{"name":"Ravi","email":"ravi@example.com"}` |
| **PUT** | `/api/users/{id}` | Update existing user | `{"name":"Ravi Teja","email":"raviteja@example.com"}` |
| **DELETE** | `/api/users/{id}` | Delete user by ID | None |

---

## 🧪 Testing the APIs

You can quickly test all endpoints at once by running the included script:
```powershell
powershell -ExecutionPolicy Bypass -File .\test_api.ps1
```

Or using `curl`:
```bash
# Test Hello
curl http://localhost:8080/api/hello

# Test Status
curl http://localhost:8080/api/status

# Get all users
curl http://localhost:8080/api/users

# Add new user
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d "{\"name\":\"Arun\",\"email\":\"arun@example.com\"}"

# Delete user
curl -X DELETE http://localhost:8080/api/users/1
```

---

## 🐳 Docker Deployment

### Prerequisites
Make sure **Docker Desktop** is running.

```powershell
# 1. Package the application
.\mvnw.cmd clean package -DskipTests

# 2. Generate / Build the image
docker build -t springboot-crud-demo:latest .

# 3. Run the container
docker run -d -p 8080:8080 --name springboot-crud-app springboot-crud-demo:latest

# 4. Stop and remove container
docker stop springboot-crud-app
docker rm springboot-crud-app
```


