package com.bank.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bank.DTO.CustomerDetails;
import com.bank.DTO.CustomerStatement;
import com.bank.steps.LoadedClass_Connection;

public class CustomerDAO
{
	private final String insertCustomerDetails="insert into customer_details (Customer_Name, Customer_EmailId, Customer_MobileNumber, Customer_AadharNumber, Customer_Address, Customer_Gender,Customer_Amount,Customer_Status) values (?,?,?,?,?,?,?,?)";
	private final String customerLogin="select * from customer_details where Customer_AccountNumber=? and Customer_Pin=? and Customer_Status='Accept'";
	private final String select="Select * from customer_details";
	private final String depositAmmount="update customer_details set Customer_Amount=? where Customer_AccountNumber=? and Customer_Pin=?";
	private final String withDrawAmmount="update customer_details set Customer_Amount=? where Customer_AccountNumber=? and Customer_Pin=?";
	private final String updatepin="update customer_details set Customer_Pin=? where Customer_AccountNumber=? and Customer_Pin=?";
	private final String colseAccount="update customer_details set Customer_Status='colsing' where Customer_AccountNumber=? and Customer_Pin=?";
	private final String statementInsert="insert into customer_statement (customer_AccountNumber, Customer_StatementType, Customer_StatementAmount,Customer_StatementBalanceAmount, Customer_StatementDate, Customer_StatementTime) values (?,?,?,?,?,?)";
	private final String statementDetails="Select * from customer_statement where customer_AccountNumber=?";

	CustomerDetails customerDetails=new CustomerDetails();
		
//insertCustomerDetails
	public boolean insertCustomerDetails(CustomerDetails customerDetails)
	{
		customerDetails.setStatus("Pending");
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(insertCustomerDetails);
			preparedStatement.setString(1,customerDetails.getName());
			preparedStatement.setString(2,customerDetails.getEmailId());
			preparedStatement.setLong(3,customerDetails.getMobileNumber());
			preparedStatement.setLong(4,customerDetails.getAadharNumber());
			preparedStatement.setString(5,customerDetails.getAddress());
			preparedStatement.setString(6,customerDetails.getGender());
			preparedStatement.setDouble(7,customerDetails.getAmount());
			preparedStatement.setString(8,customerDetails.getStatus());
			int restul=preparedStatement.executeUpdate();
			if(restul!=0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
//getAllCustomer Details
	public List<CustomerDetails> getAllCustomerDetails()
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparStatement = connection.prepareStatement(select);
			ResultSet resultSet=preparStatement.executeQuery();
			ArrayList<CustomerDetails> customerarrayList=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setName(resultSet.getString("Customer_Name"));
					customerDetails.setEmailId(resultSet.getString("Customer_EmailId"));
					customerDetails.setMobileNumber((resultSet.getLong("Customer_MobileNumber")));
					customerDetails.setAadharNumber(resultSet.getLong("Customer_AadharNumber"));
					customerDetails.setAddress(resultSet.getString("Customer_Address"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setAccountNumber(resultSet.getLong("Customer_AccountNumber"));
					customerDetails.setAmount(resultSet.getLong("Customer_Amount"));
					customerDetails.setPin(resultSet.getString("Customer_Pin"));
					customerDetails.setStatus(resultSet.getString("Customer_Status"));
					customerarrayList.add(customerDetails);
				}
				return customerarrayList;
			}
			else
			{
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
//Status
//	public boolean displayBankStatus(String status)
//	{
//		String status1="SELECT * FROM customer_details where Customer_Status=?";
//
//		try {
//			Connection connection=LoadedClass_Connection.jdbcSteps();
//			PreparedStatement preparedStatement=connection.prepareStatement(status1);
//			preparedStatement.setString(1, status);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			if(resultSet.isBeforeFirst())
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//	}
//Select Customer login Using Email and pin
	public CustomerDetails selectCustomerDetailsByUsingEmailorAccountAndPin(long accountNumber,String pin)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(customerLogin);
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.setString(2, pin);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				if(resultSet.next())
				{
					CustomerDetails customerDetails=new CustomerDetails();
					String name=resultSet.getString("Customer_Name");
					String emailId=resultSet.getString("Customer_EmailId");
					long mobile=resultSet.getLong("Customer_MobileNumber");
					long aadharNumber=resultSet.getLong("Customer_AadharNumber");
					String address=resultSet.getString("Customer_Address");
					String gender=resultSet.getString("Customer_Gender");
					long accoountNumber=resultSet.getLong("Customer_AccountNumber");
					long ammount=resultSet.getLong("Customer_Amount");
					String password=resultSet.getString("Customer_Pin");
					String status=resultSet.getString("Customer_Status");
					customerDetails.setName(name);
					customerDetails.setEmailId(emailId);
					customerDetails.setMobileNumber(mobile);
					customerDetails.setAadharNumber(aadharNumber);
					customerDetails.setAddress(address);
					customerDetails.setGender(gender);
					customerDetails.setAccountNumber(accoountNumber);
					customerDetails.setAmount(ammount);
					customerDetails.setPin(password);
					customerDetails.setStatus(status);
					return customerDetails;
				}
			}
			else
			{
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return customerDetails;
	}
//Customer Login
//	public boolean customerLogin(String email,String pin)
//	{
//		boolean logn=false;
//		try {
//			Connection connection=LoadedClass_Connection.jdbcSteps();
//			PreparedStatement preparedStatement=connection.prepareStatement(select);
//			ResultSet resultSet=preparedStatement.executeQuery();
//			if(resultSet.isBeforeFirst())
//			{
//				while(resultSet.next())
//				{
//					if(resultSet.getString("Customer_EmailId").equalsIgnoreCase(email) && resultSet.getString("Customer_Pin").equalsIgnoreCase(pin) )
//					{
//						logn= true;
//						break;
//					}
//				}
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logn= false;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logn= false;
//		}
//		return logn;
//	}
//Deposit Method
	public void deposit(long accountNumber,String pin,double amount)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(depositAmmount);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setLong(2, accountNumber);
			preparedStatement.setString(3, pin);
			int res=preparedStatement.executeUpdate();
			if(res!=0)
			{
				System.out.println("Ammount deposit");
			}
			else
			{
				System.out.println("Ammount not deposit");
			}
			System.out.println(preparedStatement);
			int[] batch=preparedStatement.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//withDraw Method
	public void withDraw(long accountNumber,String pin,double amount)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(withDrawAmmount);
			preparedStatement.setDouble(1, amount);
			preparedStatement.setLong(2, accountNumber);
			preparedStatement.setString(3, pin);
			int res=preparedStatement.executeUpdate();
			if(res!=0)
			{
				System.out.println("Ammount withDraw");
			}
			else
			{
				System.out.println("Ammount not withDraw");
			}
			System.out.println(preparedStatement);
			int[] batch=preparedStatement.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Update pin
	public void updatingPin(long accountNumber,String pin,String newPin)
	{
		getAllCustomerDetails();
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(updatepin);
			preparedStatement.setString(1, newPin);
			preparedStatement.setLong(2, accountNumber);
			preparedStatement.setString(3, pin);
			int res=preparedStatement.executeUpdate();
			if(res!=0)
			{
				System.out.println("Pin Update");
			}
			else
			{
				System.out.println("Pin UnChange");
			}
			System.out.println(preparedStatement);
			int[] batch=preparedStatement.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Closing account
	public void closingAccount(long accountNumber,String pin)
	{
		getAllCustomerDetails();
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(colseAccount);
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.setString(2, pin);
			int res=preparedStatement.executeUpdate();
			if(res!=0)
			{
				System.out.println("Closing Update");
			}
			else
			{
				System.out.println("Closing UnChange");
			}
			System.out.println(preparedStatement);
			int[] batch=preparedStatement.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Customer Statement
	public void statementInsert(CustomerStatement customerStatement)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(statementInsert);
			preparedStatement.setLong(1, customerStatement.getCustomer_AccountNumber());
			preparedStatement.setString(2, customerStatement.getCustomer_StatementType());
			preparedStatement.setDouble(3, customerStatement.getCustomer_StatementAmount());
			preparedStatement.setDouble(4, customerStatement.getCustomer_StatementBalanceAmount());
			preparedStatement.setDate(5, Date.valueOf(customerStatement.getCustomer_StatementDate()));
			preparedStatement.setTime(6, Time.valueOf(customerStatement.getCustomer_StatementTime()));
			int res=preparedStatement.executeUpdate();
			if(res!=0)
			{
				System.out.println("Statement Update");
			}
			else
			{
				System.out.println("Statement UnChange");
			}
			System.out.println(preparedStatement);
			int[] batch=preparedStatement.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Statement Details
	public void statementDetails(long accountNumber)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(statementDetails);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.printf("%-15s %-15s %-20s %-20s %-15s %-10s\n", "Account Number", "Statement Type", "Statement Amount","Balance Amount","Statement Date","Statement Time");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{

				     // Table Data
				     System.out.printf("%-15s %-15s %-20s %-20s %-15s %-10s\n",resultSet.getLong("customer_AccountNumber"),resultSet.getString("Customer_StatementType"),resultSet.getDouble("Customer_StatementAmount"),resultSet.getLong("Customer_StatementBalanceAmount"),resultSet.getDate("Customer_StatementDate"),resultSet.getTime("Customer_StatementTime"));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
