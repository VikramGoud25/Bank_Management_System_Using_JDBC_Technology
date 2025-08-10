package com.bank.main;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.bank.service.AdminServiceLayer;
import com.bank.service.CustomerServiceLayer;

public class BankMainClass
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		CustomerServiceLayer customerServiceLayer=new CustomerServiceLayer();
		AdminServiceLayer adminServiceLayer=new AdminServiceLayer();
		boolean status=true;
		int number;
		while(status)
		{
			System.out.println("Enter \n 1. For Customer Registration \n 2. For Customer Login \n 3. Admin Login \n 4. For Close\n------------------------------------------------");
			while(true)
			{
				try
				{
					number=sc.nextInt();
					break;
				}
				catch (InputMismatchException e) {
					System.out.println("Enter \n 1. For Customer Registration \n 2. For Customer Login \n 3. Admin Login \n 4. For Close\n------------------------------------------------");
					sc.nextLine();
				}
			}
			switch (number) {
			case 1:
				System.out.println("Customer Registration");
				customerServiceLayer.customerRegistration();
				System.out.println("------------------------------------------------");
				break;
			case 2:
				System.out.println("Customer Login");
				customerServiceLayer.customerLogin();
				System.out.println("------------------------------------------------");
				break;
			case 3:
				System.out.println("Admin Login");
				adminServiceLayer.adminLogin();
				System.out.println("------------------------------------------------");
				break;
			case 4:
				status=false;
				System.out.println("====== Thank you ======");
				break;
			default:
				System.out.println("Invalid request");
				break;
			}
		}
	}
}
