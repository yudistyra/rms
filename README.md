# rms
resource management system. CRUD study case

This project built and tested in the following environment:
- Java version : 1.8
- MySQL database version : 10.1.35-MariaDB
- Tomcat version : 8.5
- Maven vesion : 3.5.4

To run this project:
- Create a new MySQL schema named inventory
- import sql file from database folder to your local database server
- Configure the database connection accordingly in `/src/com/yudis/inventory/util/ConnectionPool.java`
- Start your tomcat server
- The application will be available at `http://localhot:8080/webinventory`
- The initial username and password combination for login would be `admin` and `123456` 
