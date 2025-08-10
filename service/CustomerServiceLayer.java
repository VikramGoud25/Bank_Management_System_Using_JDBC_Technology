package com.bank.service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import com.bank.DAO.CustomerDAO;
import com.bank.DTO.CustomerDetails;
import com.bank.DTO.CustomerStatement;
import com.bank.exception.CustomerException;
import com.bank.exception.DuplicateEmailException;
import com.bank.exception.DuplicateMobileNumber;

public class CustomerServiceLayer
{
	Scanner sc=new Scanner(System.in);
	CustomerDAO customerDAO=new CustomerDAO();
	CustomerDetails customerDetails=new CustomerDetails();
//Customer Registration
	public void customerRegistration()
	{
//Name Validation
		String nameValidation="[a-zA-Z]+";
		System.out.println("Enter a Customer Name");
		while(true)
		{
			String name=sc.nextLine();
			try
			{
				if(name.matches(nameValidation))
				{
					customerDetails.setName(name);
					break;
				}
				else
				{
					throw new CustomerException("Invalid Name");		
				}
			}
			catch (CustomerException c) {
				System.err.println("re-enter Name");
			}
		}
//Email Validation
		String emailValidation="[a-z 0-9]+[@]+[a-z]+[.][a-z]{2,3}";
		System.out.println("Enter a Customer EmailId");
		while(true)
		{
			try
			{
				String emailId=sc.next();
				if(emailId.matches(emailValidation))
				{
					boolean uniqueEmail=true;
					for(CustomerDetails cal:customerDAO.getAllCustomerDetails())
					{
						if(cal.getEmailId().equalsIgnoreCase(emailId))
						{
							uniqueEmail=false;
							break;
						}		
					}
					if(uniqueEmail)
					{
						customerDetails.setEmailId(emailId);
						break;
					}
					else
					{
						throw new DuplicateEmailException();
					}
				}
				else
				{
					throw new CustomerException("Invalid Email");
				}
			}
			catch (CustomerException c)
			{
				System.err.println("re-Enter Email");
				sc.nextLine();
			}
			catch(DuplicateEmailException | InputMismatchException a)
			{
				System.err.println("Email Id Already Exists");
			}
		}
//Mobile Number Validation
		String mobileValidation="[6-9][0-9]{9}";
		System.out.println("Enter a Customer Mobile Number");
		sc.nextLine();
		while(true)
		{
			try
			{
				long mobileNumber=sc.nextLong();
				String mn=String.valueOf(mobileNumber);
				if(mn.matches(mobileValidation))
				{
					boolean status=true;
					for(CustomerDetails cd:customerDAO.getAllCustomerDetails())
					{
						if(cd.getMobileNumber()==mobileNumber)
						{
							status=false;
							break;
						}
					}
					if(status)
					{
						customerDetails.setMobileNumber(mobileNumber);
						break;
					}
					else
					{
						throw new DuplicateMobileNumber();
					}
				}
				else
				{
					throw new CustomerException("Invalid Mobile Number");			
				}
			}
			catch (CustomerException | InputMismatchException c) {
				System.out.println("re-Enter Mobile Number");
				sc.nextLine();
			}
			catch(DuplicateMobileNumber dmn)
			{
				System.err.println("MobileNumber is allready exists");
			}
		}
//Aadhar Number validation
		String aadhar="[1-9]{12}+";
		System.out.println("Enter a Customer Aadhar Number");
		sc.nextLine();
		while(true)
		{
			try
			{
				long aadharNumber=sc.nextLong();
				String aadharNumberValidation=String.valueOf(aadharNumber);
				if(aadharNumberValidation.matches(aadhar))
				{
					boolean aadharn=true;
					for(CustomerDetails cd:customerDAO.getAllCustomerDetails())
					{
						if(cd.getAadharNumber()==aadharNumber)
						{
							aadharn=false;
							break;
						}
					}
					if(aadharn)
					{
						customerDetails.setAadharNumber(aadharNumber);
						break;
					}
					else
					{
						throw new DuplicateMobileNumber();
					}
				}
				else
				{
					throw new CustomerException("Invalid Aadhar Number");			
				}
			}
			catch (CustomerException | InputMismatchException c)
			{
				System.err.println("re-enter Aadhar Number");
				sc.nextLine();
			}
			catch (DuplicateMobileNumber dmn)
			{
				System.err.println("All ready Aadhar Number is Exists");
				sc.nextLine();
			}
		}
//Address Validation
		String addressValidation="[a-zA-Z0-9- ]+";
		sc.nextLine();
		System.out.println("Enter a Customer Address");
		while(true)
		{
			String address=sc.nextLine();
			try
			{
				if(address.matches(addressValidation))
				{
					customerDetails.setAddress(address);
					break;
				}
				else
				{
					throw new CustomerException("Invalid Address");
				}
			}
			catch (Exception e) {
				System.err.println("re-enter Address");
				sc.nextLine();
			}
		}
//Gender validation
		String genderValidation="[MALE FEMALE male female]+";
		System.out.println("Enter a Customer Gender");
		while(true)
		{
			String gender=sc.next();
			try
			{
				if(gender.toUpperCase().matches(genderValidation))
				{
					customerDetails.setGender(gender);
					break;
				}
				else
				{
					throw new CustomerException("Invalid Gender");
				}
			}
			catch (Exception e) {
				System.err.println("re-Enter Gender");
				sc.nextLine();
			}
		}
//Amount validation
		System.out.println("Enter a Customer amount");
		double amount=sc.nextDouble();
		sc.nextLine();
		while(true)
		{
			try
			{
				if(amount>0)
				{
					customerDetails.setAmount(amount);
					break;
				}
				else
				{
					throw new CustomerException("Invalid Amount");			
				}
			}
			catch (CustomerException | InputMismatchException am)
			{
				System.err.println("re-enter amount");
			}
		}
//get it from the CustomerDAO by making CustomerDAO HAS-A-REALTIONSHIP with CustomerService
		if(customerDAO.insertCustomerDetails(customerDetails))
		{
			System.out.println("Data Inserted");
		}
		else
		{
			System.out.println("Data Not Inserted");
		}
	}
	
//Customer login validation
	public void customerLogin()
	{
		System.out.println("Enter Customer Account");
		long accountNumber = 0;
		while(true)
		{
			try
			{
				accountNumber=sc.nextLong();	
				break;
			}
			catch (CustomerException | InputMismatchException c) {
				System.out.println("re-Enter Account Number");
				sc.nextLine();
			}
		}
		System.out.println("Enter Customer Pin");
		String pin=sc.next();
		CustomerDetails customerdao=customerDAO.selectCustomerDetailsByUsingEmailorAccountAndPin(accountNumber, pin);
		if(customerdao!=null)
		{
			String cap=captchaGenerate();
			System.out.println("Captcha : "+cap);
			System.out.println("Enter Captcha");
			String captcha=sc.next();
			if(cap.equals(captcha))
			{
				System.out.println("Customer Login Sccful");
				String maleFemale=customerdao.getGender().equalsIgnoreCase("female")?"MS":"MR";
				System.out.println("Hello "+maleFemale+" "+customerdao.getName());
				System.out.println("------------------------------");
				customerOperation(accountNumber,pin);
			}
			else
			{
				System.out.println("Invalid Customer Login");
			}
		}
		else
		{
			System.out.println("Invalid Login");
		}
	}
//Customer Operation
	public void customerOperation(long accountNumber,String pin)
	{
		boolean status=true;
		while(status)
		{
			System.out.println("1. For Credit\n2. For Debit\n3. For Check Statment\n4. For Check Balance\n5. Update Pin\n6. Close Account\n7. Exit");
			CustomerDetails customerdao=customerDAO.selectCustomerDetailsByUsingEmailorAccountAndPin(accountNumber, pin);
			int number;
			while(true)
			{
				try
				{
					number=sc.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					System.out.println("1. For Credit \n2. For Debit \n3. For Check Statment \n4. For Check Balance \n5. Update Pin \n6. Close Account \n7. Exit");
					sc.nextLine();
				}
			}
			switch(number)
			{
			case 1:
			{
				System.out.println(customerdao.getAmount()+">>");
				System.out.println("Credit");
				System.out.println("Enter Deposite Ammount");
				long ammount=0;
				while(true)
				{
					try
					{
						ammount=sc.nextLong();	
						break;
					}
					catch (CustomerException | InputMismatchException c) {
						System.out.println("re-Enter ammount Number");
						sc.nextLine();
					}
				}
				if(ammount>0)
				{
					double ammountTotal=customerdao.getAmount()+ammount;
					customerDAO.deposit(accountNumber, pin, ammountTotal);
					statement(accountNumber, "Credit", ammount, ammountTotal);
				}
				else
				{
					System.err.println("Invalid Ammount\n");
				}
			}
			break;
			case 2:
			{
				System.out.println("Debit");
				System.out.println("Enter Debit Ammount");
				long ammount=sc.nextLong();
				while(true)
				{
					try
					{
						ammount=sc.nextLong();	
						break;
					}
					catch (CustomerException | InputMismatchException c) {
						System.out.println("re-Enter ammount Number");
						sc.nextLine();
					}
				}
				if(ammount<customerdao.getAmount())
				{
					double ammountTotal=customerdao.getAmount()-ammount;
					customerDAO.withDraw(accountNumber, pin, ammountTotal);
					statement(accountNumber, "Debit", ammount, ammountTotal);
				}
				else
				{
					System.out.println("In Suffecent Amount");
				}
			}
			break;
			case 3:
				System.out.println("Check Statment");
				customerDAO.statementDetails(accountNumber);
				break;
			case 4:
				System.out.println("Check Balance");
				System.out.println("Your Account Balance is : "+customerdao.getAmount());
				System.out.println("\n------------------------------");
				break;
			case 5:
				System.out.println("Update Pin");
				System.out.println("Enter New Pin");
				String newPin=sc.next();
				customerDAO.updatingPin(accountNumber,pin,newPin);
				break;
			case 6:
				System.out.println("Close Account");
				String cap=captchaGenerate();
				System.out.println("Captcha : "+cap);
				System.out.println("Enter Captcha");
				String captcha=sc.next();
				if(cap.equals(captcha))
				{
					customerDAO.closingAccount(accountNumber,pin);					
				}
				else
				{
					System.out.println("Invalid Customer Login");
				}
				break;
			case 7:
				System.out.println("Exit");
				status=false;
				break;
			default :
			System.out.println("Invalid Operation");
			}
		}
	}
//captcha Generate
	public String captchaGenerate()
	{
		String arr[]= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U","V", "W", "X", "Y", "Z",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u","v", "w", "x", "y", "z","1","2","3","4","5","6","7","8","9","0"};
		String cap="";
		Random ran=new Random();
		for(int i=1;i<=4;i++)
		{
			int num=ran.nextInt(arr.length);
			cap=cap+arr[num];
		}
		return cap;
	}
//statement
	public static void statement(long accountNumber,String status,long ammount,double ammountTotal)
	{
		CustomerDAO customerDAO =new CustomerDAO();
		CustomerStatement customerStatement=new CustomerStatement();
		customerStatement.setCustomer_AccountNumber(accountNumber);
		customerStatement.setCustomer_StatementType(status);
		customerStatement.setCustomer_StatementAmount(ammount);
		customerStatement.setCustomer_StatementBalanceAmount(ammountTotal);
		customerStatement.setCustomer_StatementDate(LocalDate.now());
		customerStatement.setCustomer_StatementTime(LocalTime.now());
		customerDAO.statementInsert(customerStatement);
	}
}
