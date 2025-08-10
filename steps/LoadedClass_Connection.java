package com.bank.steps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoadedClass_Connection
{
	public static Connection jdbcSteps() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_management_system?user=root&password=Vikramgoud25@");
		return connection;
	}
}
