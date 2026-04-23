# Patient Management Microservices

This workspace is organized into:

- `backend/eureka-server` - Eureka service registry (`8761`)
- `backend/ms2` - Patient Service (MongoDB CRUD, `8082`)
- `backend/demo` - Appointment Service (`8081`)
- `backend/records-service` - Medical Record Service (`8083`)
- `frontend` - React + Vite UI (`5173`)

## Prerequisites

- Java 17+
- Maven Wrapper (already included in each backend service)
- Node.js 18+
- MongoDB running locally **or** MongoDB Atlas URI

## Configure MongoDB

Set `MONGODB_URI` before running patient service:

PowerShell:

```powershell
$env:MONGODB_URI="mongodb+srv://<user>:<password>@<cluster>/<db>?retryWrites=true&w=majority"
```

If not provided, service falls back to `mongodb://localhost:27017/patientdb`.

## Run Backend

Start each service in a separate terminal in this order:

1. `cd backend/eureka-server && ./mvnw spring-boot:run`
2. `cd backend/ms2 && ./mvnw spring-boot:run`
3. `cd backend/demo && ./mvnw spring-boot:run`
4. `cd backend/records-service && ./mvnw spring-boot:run`

## Run Frontend

```powershell
cd frontend
npm install
npm run dev
```

## Run With Docker

From project root:

```powershell
cd fms-project
docker compose up --build
```

Services:

- Frontend: `http://localhost:5173`
- Eureka dashboard: `http://localhost:8761`
- Appointment service: `http://localhost:8081`
- Patient service: `http://localhost:8082`
- Medical record service: `http://localhost:8083`

Notes:

- Backend services use `EUREKA_DEFAULT_ZONE` in containers and default to localhost outside Docker.
- You can override each service MongoDB URI with `MONGODB_URI` environment variable.
