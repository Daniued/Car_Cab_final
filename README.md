Online Vehicle Reservation System
Project Overview
The Online Vehicle Reservation System is a web-based platform that allows customers to book vehicles, drivers to manage rides, and administrators to oversee bookings, accounts, and revenue. The system ensures efficiency, ease of use, and reliability for all stakeholders.
Features
•	Customer Module: User registration, quick & planned booking, booking history.
•	Driver Module: View ride requests, accept/reject bookings, view ride history, check earnings.
•	Admin Module: Manage customers & drivers, view revenue, monitor bookings, generate reports.
•	Booking Management: Real-time ride requests, driver assignment, and payment tracking.
•	Reports & Insights: Generate detailed reports on rides, revenue, and user activities.
________________________________________
Setup & Installation
Prerequisites
•	Java (JDK 8+)
•	Apache Tomcat Server
•	MySQL Database
•	Eclipse/IntelliJ (Optional for development)
•	Git (For version control)
Installation Steps
1.	Clone the Repository: 
2.	git clone https://github.com/Daniued/Car_Cab_final.git
3.	Import Project: 
o	Open Eclipse/IntelliJ and import as a Dynamic Web Project.
4.	Configure Database: 
o	Import database.sql into MySQL.
o	Update database credentials in DBConnection.java.
5.	Run the Project: 
o	Deploy the project on Apache Tomcat.
o	Open http://localhost:8080/CarCabs in the browser.
________________________________________
Usage Guide
Customer
•	Register/Login.
•	Book a ride (Quick or Planned Booking).
•	View booking history.
•	Cancel or modify bookings.
Driver
•	Login to the dashboard.
•	View & accept ride requests.
•	Update ride status (Ongoing/Completed).
•	View earnings & ride history.
Admin
•	Manage customer & driver accounts.
•	View & monitor all bookings.
•	Generate revenue & ride reports.
•	Manage payments & system settings.
________________________________________
Project Structure
/CarCabs
│── /src
│   ├── /servlets        # Java Servlets handling requests
│   ├── /dao            # Data Access Objects for database operations
│   ├── /bean           # JavaBeans for object representation
│   ├── /test           # JUnit test cases for functionalities
│── /WebContent
│   ├── /styles         # CSS files for UI styling
│   ├── /jsp            # JSP files for frontend views
│   ├── index.jsp       # Login page
│── pom.xml (if using Maven)
│── .gitignore
│── database.sql        # MySQL database schema
________________________________________
Contribution Guidelines
We welcome contributions! Follow these steps:
1.	Fork the repository.
2.	Create a feature branch: 
3.	git checkout -b feature-branch
4.	Make changes & commit: 
5.	git commit -m "Added new feature"
6.	Push changes: 
7.	git push origin feature-branch
8.	Submit a Pull Request for review.
________________________________________
Version Control & Deployment
•	Git Repository: The project is version-controlled with multiple commits, tracking each feature & update.
•	Branching Strategy: Main branch for production, feature branches for development.
•	Deployment: The system is deployed on a local Apache Tomcat server with MySQL as the backend.
________________________________________
License
This project is open-source under the MIT License.
________________________________________
Contact
For queries or support, contact R.J. Daniued at kdaniued@gmail.com.

