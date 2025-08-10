package com.bank.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import com.bank.DAO.AdminDAO;
import com.bank.DAO.CustomerDAO;
import com.bank.DTO.CustomerDetails;
public class AdminServiceLayer
{
	Scanner sc=new Scanner(System.in);
	AdminDAO adminDAO=new AdminDAO();
	CustomerDAO customerDAO=new CustomerDAO();
	CustomerDetails customerDetails=new CustomerDetails();
	static Random rd=new Random();
	public void adminLogin()
	{
//email Validation
		System.out.println("Enter a Customer EmailId");
		String emailId=sc.next();
//password Validation
		System.out.println("Enter Password");
		String password=sc.next();
		if(adminDAO.selectAdmindetailsByUsingEmailAndpassword(emailId, password))
		{
			boolean s=true;
			while(s)
			{
				System.out.println(" 1. To Get All Customer Details \n 2. To Get All Account Request Details Pending \n 3. To Get All Account Request Details Colsing Requset \n 4. Exited");
				int number1;
				while(true)
				{
					try
					{
						number1=sc.nextInt();
						break;
					}
					catch (InputMismatchException e) {
						System.out.println(" 1. To Get All Customer Details \n 2. To Get All Account Request Details Pending \n 3. To Get All Account Request Details Colsing Requset \n 4. Exited");
						sc.nextLine();
					}
				}
				switch (number1) {
				case 1:
					System.out.println("To Get All Customer Details");
					adminDAO.displayAllCustomerDetails();
					break;
				case 2:
					System.out.println("To Get All Account Request Details Pending");
					requsetDetails("Pending");
					System.out.println(" 1. To Generate Account Number For One Person\n 2. To Select all to Generate Account Number");
					int number;
					while(true)
					{
						try
						{
							number=sc.nextInt();
							break;
						}
						catch (InputMismatchException e) {
							System.out.println(" 1. To Generate Account Number For One Person \n 2. To Select all to Generate Account Number");
							sc.nextLine();
						}
					}
					switch (number)
					{
					case 1:
						System.out.println("<===== To Generate Account Number For One Person =====>");
						System.out.println("Enter Customer Email");
						String Customer_EmailId=sc.next();
						String pin=pinNumber();
						String status="Accept";
						if(adminDAO.emailIsPresent(Customer_EmailId))
						{
							while(true)
							{
								long accountNumber=accountNumberGenerate();
								if(adminDAO.accountNumberExistedNot(accountNumber))
								{
									System.out.println("Account number al reddy exited");
								}
								else
								{
									customerDetails.setStatus(status);
									customerDetails.setAccountNumber(accountNumber);
									customerDetails.setPin(pin);
									if(adminDAO.insertCustomerDetailAccountPinStatus(customerDetails, Customer_EmailId))
									{
										System.out.println("Data Inserted");
										break;
									}
								}
							}
						}
						else
						{
							System.err.println("Enter the Email is not present");							
						}
						break;
					case 2:
						System.out.println("To Select all to Generate Account Number");
						for(CustomerDetails cd:adminDAO.generateAllAccountNumber())
						{
							CustomerDetails customerDetail=new CustomerDetails();
							String pin1=pinNumber();
							String status1="Accept";
							while(true)
							{
								long accountNumber=accountNumberGenerate();
								if(adminDAO.accountNumberExistedNot(accountNumber))
								{
									System.out.println("Account number all reddy exited");
								}
								else
								{
									customerDetail.setStatus(status1);
									customerDetail.setAccountNumber(accountNumber);
									customerDetail.setPin(pin1);
									System.out.println("Data Inserted............");
									if(adminDAO.insertgenerateAllAccountNumber(customerDetail,cd.getEmailId()))
									{
										System.out.println("Data Inserted");
										break;
									}
								}
							}
						}
						break;
					default:
						break;
					}
					break;
				case 3:
					requsetDetails("colsing");
					System.out.println("To Close The Closeing Request Account");
					System.out.println("Enter Customer_AccountNumber");
					long Customer_AccountNumber=sc.nextLong();
					adminDAO.closingAccount(Customer_AccountNumber);
					break;
				case 4:
					s=false;
					break;
				default:
					System.err.println("Invalid Request");
					break;
				}
			}
		}
		else
		{
			System.out.println("Invalid Login");
		}
	}
//Pin generate
	public static String pinNumber()
	{
		int firstPin=rd.nextInt(4);
		int remainingPin= (int) (100 +(rd.nextDouble() * 899));
		return ""+firstPin + remainingPin;
	}
//Account Number Generate
	public static long accountNumberGenerate()
	{
		int firstDigit=6+rd.nextInt(4);
		long remainingDigits = 1000000L + (long)(rd.nextDouble() * 8999999L);
		return firstDigit * 10000000L + remainingDigits;
	}
//requset Details
	public void requsetDetails(String requset)
	{
		ResultSet resultSet=adminDAO.getAllAccountRequestDetails(requset);
		try {
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					System.out.println("Customer Id             : "+resultSet.getInt("Customer_Id"));
					System.out.println("Customer Account Number : "+resultSet.getLong("Customer_AccountNumber")); 
					System.out.println("Customer Name           : "+resultSet.getString("Customer_Name"));
					System.out.println("Customer EmailId        : "+resultSet.getString("Customer_EmailId")); 
					System.out.println("Mobile Number           : "+resultSet.getLong("Customer_MobileNumber")); 
					System.out.println("Customer Status         : "+resultSet.getString("Customer_Status")); 
					System.out.println("*******************************************"); 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
