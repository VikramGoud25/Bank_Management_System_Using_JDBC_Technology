package com.bank.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.DTO.CustomerDetails;
import com.bank.steps.LoadedClass_Connection;

public class AdminDAO
{
	CustomerDetails customerDetails=new CustomerDetails();
	CustomerDAO customerDAO=new CustomerDAO();	
	private static final String select="SELECT * FROM customer_details";
	private static final String admin_login="select * from admindetails Where AdminEmail=? and AdminPassword=?";
	private static final String customerStatus="select * from customer_details Where Customer_Status=?";
	private static final String accountNumberExisted="select Customer_AccountNumber from customer_details where Customer_AccountNumber=?";
	private static final String emailIsPresents="select * from customer_details where Customer_EmailId=? and Customer_Status='Pending'";
	private static final String cName="select * from customer_details";
	private static final String generateAllAccountNumberPinStatus="select * from customer_details where Customer_Status='Pending'";
	private static final String customerDetailAccountPinStatus="update customer_details set Customer_AccountNumber=?,Customer_Pin=?,Customer_Status=? where Customer_EmailId=? and Customer_Status='Pending'";
	private static final String colseAccount="update customer_details set Customer_Status='colsed' where Customer_AccountNumber=? and Customer_Status='colsing'";

//Select Admin Email and Password
	public boolean selectAdmindetailsByUsingEmailAndpassword(String adminEmail,String adminPassword)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(admin_login);
			preparedStatement.setString(1, adminEmail);
			preparedStatement.setString(2, adminPassword);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//Display All Customer Details
	public void displayAllCustomerDetails()
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(select);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					System.out.println("Customer Id         : "+resultSet.getInt("Customer_Id"));
					System.out.println("Customer Name       : "+resultSet.getString("Customer_Name"));
					System.out.println("Customer_EmailId    : "+resultSet.getString("Customer_EmailId")); 
					System.out.println("Mobile Number       : "+resultSet.getLong("Customer_MobileNumber")); 
					System.out.println("AadharNumber        : "+resultSet.getLong("Customer_AadharNumber")); 
					System.out.println("Customer_Address    : "+resultSet.getString("Customer_Address")); 
					System.out.println("Customer_Gender     : "+resultSet.getString("Customer_Gender")); 
					System.out.println("Customer_Amount     : "+resultSet.getDouble("Customer_Amount")); 
					System.out.println("Customer_Gender     : "+resultSet.getString("Customer_Gender")); 
					System.out.println("Customer_Status     : "+resultSet.getString("Customer_Status")); 
					System.out.println("*******************************************"); 
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
//get All Account pending Details
	public ResultSet getAllAccountRequestDetails(String request)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(customerStatus);
			preparedStatement.setString(1, request);
			ResultSet resultSet=preparedStatement.executeQuery();
			return resultSet;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
//account number existed not
	public boolean accountNumberExistedNot(long accountNumber)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(accountNumberExisted);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//emailIsPresent
	public boolean emailIsPresent(String Customer_EmailId)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(emailIsPresents);
			preparedStatement.setString(1, Customer_EmailId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//Generate Account Number ,pin and Status
	public boolean generateAccountNumberForOnePerson(String name)
	{
		boolean present=true;
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(cName);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					if(resultSet.getString("Customer_Name").equalsIgnoreCase(name) && resultSet.getString("Customer_Status").equalsIgnoreCase("Pending"))
					{
						present=true;
						break;
					}
					else
					{
						present=false;
					}
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			present=false;
		}
		return present;
	}
//insert account number ,pin and status
	public boolean insertCustomerDetailAccountPinStatus(CustomerDetails customerDetails,String Customer_EmailId)
	{
		customerDetails.setEmailId(Customer_EmailId);
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(customerDetailAccountPinStatus);
			preparedStatement.setLong(1,customerDetails.getAccountNumber());
			preparedStatement.setString(2,customerDetails.getPin());
			preparedStatement.setString(3,customerDetails.getStatus());
			preparedStatement.setString(4,customerDetails.getEmailId());
			System.out.println(preparedStatement);
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
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
//Generate All Account Number ,pin and Status
		public List<CustomerDetails> generateAllAccountNumber()
		{
			try {
				Connection connection=LoadedClass_Connection.jdbcSteps();
				PreparedStatement preparedStatement=connection.prepareStatement(generateAllAccountNumberPinStatus);
				ResultSet resultSet=preparedStatement.executeQuery();
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
				}
					return customerarrayList;
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
//insert account number ,pin and status
		public boolean insertgenerateAllAccountNumber(CustomerDetails customerDetails,String Customer_EmailId)
		{
			customerDetails.setEmailId(Customer_EmailId);
			try {
				Connection connection=LoadedClass_Connection.jdbcSteps();
				PreparedStatement preparedStatement=connection.prepareStatement(customerDetailAccountPinStatus);
				preparedStatement.setLong(1,customerDetails.getAccountNumber());
				preparedStatement.setString(2,customerDetails.getPin());
				preparedStatement.setString(3,customerDetails.getStatus());
				preparedStatement.setString(4,customerDetails.getEmailId());
				System.out.println(preparedStatement);
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
				e.printStackTrace();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
//closing account
	public void closingAccount(long Customer_AccountNumber)
	{
		try {
			Connection connection=LoadedClass_Connection.jdbcSteps();
			PreparedStatement preparedStatement=connection.prepareStatement(colseAccount);
			preparedStatement.setLong(1, Customer_AccountNumber);
			int res=preparedStatement.executeUpdate();
			if(res!=0)
			{
				System.out.println("Closed Update");
			}
			else
			{
				System.out.println("Closed UnChange");
			}
			System.out.println(preparedStatement);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
