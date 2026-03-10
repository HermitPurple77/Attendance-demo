# Device Status Monitor — Setup Guide
## Gas Turbine Research Establishment

---

## What This App Does
A real-time IP address ping monitor. The backend pings every device in the
Oracle database every 10 seconds and marks it UP / DOWN / ERROR.
The Angular frontend polls the backend every 10 seconds and displays
a live status dashboard.

---

## Prerequisites — Install These First (Windows)

### 1. Java JDK 17
- Download from: https://adoptium.net
- Choose: Temurin JDK 17, Windows x64 .msi
- Run installer (sets PATH automatically)
- Verify: open CMD → type: java -version

### 2. Maven
- Download from: https://maven.apache.org/download.cgi
- Download: Binary zip archive
- Extract to: C:\Program Files\Maven\
- Add to PATH: C:\Program Files\Maven\apache-maven-3.x.x\bin
- Verify: open CMD → type: mvn -version

### 3. Node.js (for Angular frontend)
- Download from: https://nodejs.org (LTS version)
- Run installer
- Verify: open CMD → type: node -v

### 4. Angular CLI
- After Node.js is installed, run in CMD:
  npm install -g @angular/cli
- Verify: ng version

### 5. VSCode + Extensions
- Download from: https://code.visualstudio.com
- Install extensions:
  - "Extension Pack for Java" by Microsoft
  - "Spring Boot Extension Pack" by VMware
  - "Angular Language Service" by Angular

---

## File Order — Create Files In This Order

### BACKEND (backend/)
```
1.  pom.xml                                         ← Maven config
2.  src/main/resources/application.properties       ← DB connection
3.  src/main/java/landing/AttendanceApplication.java
4.  src/main/java/landing/ServletInitializer.java
5.  src/main/java/landing/WebSocketConfig.java
6.  src/main/java/landing/model/IpAddress.java
7.  src/main/java/landing/repository/IpAddressRepository.java
8.  src/main/java/landing/controller/IpAddressController.java
9.  src/main/java/landing/service/PingService.java
10. src/main/java/landing/service/PingWebSocketService.java
11. src/main/java/landing/service/PingScheduler.java
```

### FRONTEND (frontend/)
```
12. src/index.html
13. src/main.ts
14. src/styles.css
15. src/app/app.module.ts
16. src/app/app-routing.module.ts
17. src/app/app.component.ts
18. src/app/app.component.html
19. src/app/app.component.css
20. src/app/header/header.component.ts
21. src/app/header/header.component.html
22. src/app/header/header.component.css
23. src/app/device/device.component.ts
24. src/app/device/device.component.html
25. src/app/device/device.component.css
```

---

## Running the App

### Step 1 — Start the Backend
Open CMD in the backend/ folder:
  mvn spring-boot:run

You should see: "Started AttendanceApplication on port 8080"
First run downloads all dependencies (needs internet, takes ~2 mins).

### Step 2 — Start the Frontend
Open a NEW CMD window in the frontend/ folder:
  ng serve

You should see: "Angular Live Development Server is listening on localhost:4200"

### Step 3 — Open in Browser
Go to: http://localhost:4200

---

## How They Connect

Browser (Angular) ──── HTTP GET every 10s ────► Spring Boot :8080
                                                      │
                                              JPA/Hibernate
                                                      │
                                              Oracle DB :1600
                                                      ▲
                                              PingScheduler
                                              (runs every 10s)

---

## Important Notes

1. NETWORK: Make sure you are on the same network as the Oracle DB
   at 172.66.150.5:1600 or the backend will fail to connect.

2. LOGO: Place your logo image at:
   frontend/src/assets/images/logo.jpg

3. API URL: If your backend runs on a different port, update this line in
   device.component.ts:
   private readonly API_URL = 'http://localhost:8080/api/ips';

4. CORS: Backend is configured to allow requests from any origin (*)
   which is fine for internal/development use.

---

## Ports Used
- Frontend (Angular):      http://localhost:4200
- Backend (Spring Boot):   http://localhost:8080
- Database (Oracle):       172.66.150.5:1600

---

## Troubleshooting

| Problem                          | Fix                                          |
|----------------------------------|----------------------------------------------|
| Backend won't start              | Check DB is reachable on the network         |
| Frontend shows "Loading..."      | Check backend is running on port 8080        |
| CORS errors in browser console   | Backend CORS config already allows all (*)   |
| mvn command not found            | Add Maven bin folder to Windows PATH         |
| ng command not found             | Run: npm install -g @angular/cli             |
