# 🏦 Bank Management System (Java + JDBC + MySQL)

## 📌 Overview
This is a **console-based Bank Management System** developed in Java using **JDBC** to connect to a MySQL database.  
It supports **Customer Registration**, **Customer Login**, and **Admin Login**, along with proper exception handling and database operations.

---

## ✨ Features
- **Customer Registration**
- **Customer Login**
- **Admin Login**
- **Validation for Duplicate Email and Mobile Numbers**
- **Custom Exceptions for Better Error Handling**
- **MySQL Database Integration with JDBC**
- **Menu-driven Console Interface**

---

## 📂 Project Structure

```
src/
 ├── com.bank.DAO/
 │     ├── AdminDAO.java
 │     ├── CustomerDAO.java
 │
 ├── com.bank.DTO/
 │     ├── CustomerDetails.java
 │     ├── CustomerStatement.java
 │
 ├── com.bank.exception/
 │     ├── CustomerException.java
 │     ├── DuplicateEmailException.java
 │     ├── DuplicateMobileNumber.java
 │
 ├── com.bank.main/
 │     ├── BankMainClass.java
 │
 ├── com.bank.service/
 │     ├── AdminServiceLayer.java  
 │     ├── CustomerServiceLayer.java
 │
 ├── com.bank.steps/
 │     ├── LoadedClass_Connection.java
 │
```

---

## 🛠 Technologies Used
- **JDBC API**

---

## ⚙️ Setup Instructions

### 1️⃣ Install Required Software
- **MySQL Server**
- **MySQL Connector/J (JDBC Driver)**

---

### 2️⃣ Database Setup
1. **Create a Database**
```sql
CREATE DATABASE bank_management_system;
```

2. **Use the Database**
```sql
USE bank_management_system;
```

3. **Create Necessary Tables**  
*(Example: customer_details Table)*
```sql
CREATE TABLE customer_details (
    Customer_Id int AI PK 
	Customer_Name varchar(45) 
	Customer_EmailId varchar(45) 
	Customer_MobileNumber bigint 
	Customer_AadharNumber bigint 
	Customer_Address varchar(45) 
	Customer_Gender varchar(45) 
	Customer_AccountNumber bigint 
	Customer_Amount decimal(10,0) 
	Customer_Pin varchar(4) 
	Customer_Status varchar(45)
);
```

---

### 3️⃣ Configure Database Connection
Update **`LoadedClass_Connection.java`** with your database username & password:
```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/bank_management_system?user=root&password=YOUR_PASSWORD"
);
```

---

### 4️⃣ Compile & Run the Project
```bash
# Compile
javac -d bin src/com/bank/**/*.java

# Run
java -cp bin com.bank.main.BankMainClass
```

---

## 📜 Exception Classes
- **CustomerException** → For general customer-related issues.
- **DuplicateEmailException** → Thrown when a duplicate email is found during registration.
- **DuplicateMobileNumber** → Thrown when a duplicate mobile number is found.

---

## 🖥 Sample Output

```
Enter 
 1. For Customer Registration 
 2. For Customer Login 
 3. Admin Login 
 4. For Close
------------------------------------------------
Customer Registration
------------------------------------------------
Customer Login
------------------------------------------------
Admin Login
------------------------------------------------
====== Thank you ======
```

---

## 🚀 Future Enhancements
- Add **Funds Transfer** feature between accounts.

---

## 👨‍💻 Author
**Vikram**  
💼 MCA Graduate | Java & SQL Developer
