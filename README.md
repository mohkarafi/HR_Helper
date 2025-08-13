# HR-HELPER 🧑‍💼 - Backend Application

HR-HELPER is a Spring Boot backend application designed to help HR departments manage employees, interns, teams, leave requests, payroll, and tasks within an organization efficiently.

---

##  Features

- ✅ **Employee Management**  
  Create, update, delete, and list employees.

- ✅ **Intern Management**  
  Manage interns, assign mentors, and track internship reports.

- ✅ **Team & Department Management**  
  Organize employees into teams and departments with clear hierarchy.

- ✅ **Leave Request System**  
  Employees can request leaves; admins can approve, reject, or track leave statuses.

- ✅ **Task Assignment**  
  Assign tasks to teams or individuals with tracking capabilities.

- ✅ **Payroll System**  
  Generate and manage pay slips for employees.

- ✅ **Report Generation**  
  Create and store performance or HR-related reports.

- ✅ **Notifications**  
  Send system-generated email notifications (e.g., onboarding, leave status, etc.).

- ✅ **Authentication System**  
  Custom authentication and authorization using Spring Security.

---

## 🧱 Tech Stack

| Layer         | Technology             |
|---------------|------------------------|
| Backend       | Spring Boot            |
| Build Tool    | Maven                  |
| Database      | MySQL                  |
| Security      | Spring Security        |
| Email Service | JavaMailSender         |
| API Docs      | Swagger / OpenAPI      |

---

## 🚀 How to Run without Docker

bash
# Clone the repository
git clone https://github.com/mohkarafi/HR_Helper.git

# Navigate into the project directory
cd Employee_Management

# Run the application
./mvnw spring-boot:run

##  🐳 Run with Docker
 # Build Docker image
docker build -t hr-helper-backend .

# Run with Docker Compose
docker-compose up --build

# This will start:

MySQL Database (with configured environment variables)

Spring Boot Application (connected to the database)

3️⃣ Access the application

- API: [http://localhost:8080](http://localhost:8080) **or** `http://<YourHostIPAddress>:8080`
- Swagger Docs: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) **or** `http://<YourHostIPAddress>:8080/swagger-ui.html`


🛡️ Security Notes
All endpoints are secured using Spring Security.

Authentication via JWT tokens .

Access is role-based (Admin, USER).

👤 Author
Linkdin : Mohamed Karafi
GitHub: @mohkarafi


