package com.bank.DTO;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CustomerStatement
{
	/*Customer_StatementId, Customer_StatementType, Customer_StatementAmount, Customer_StatementDateTime, Customer_StatementBalanceAmount, customer_AccountNumber*/
	private int Customer_StatementId;
	private String Customer_StatementType;
	private double Customer_StatementAmount;
	private LocalDate Customer_StatementDate;
	private LocalTime Customer_StatementTime;
	private double Customer_StatementBalanceAmount;
	private long customer_AccountNumber;
	
	public CustomerStatement(){}

	public CustomerStatement(int customer_StatementId, String customer_StatementType, double customer_StatementAmount,
			LocalDate customer_StatementDate, LocalTime customer_StatementTime, double customer_StatementBalanceAmount,
			long customer_AccountNumber) {
		super();
		this.Customer_StatementId = customer_StatementId;
		this.Customer_StatementType = customer_StatementType;
		this.Customer_StatementAmount = customer_StatementAmount;
		this.Customer_StatementDate = customer_StatementDate;
		this.Customer_StatementTime = customer_StatementTime;
		this.Customer_StatementBalanceAmount = customer_StatementBalanceAmount;
		this.customer_AccountNumber = customer_AccountNumber;
	}

	public int getCustomer_StatementId() {
		return Customer_StatementId;
	}

	public void setCustomer_StatementId(int customer_StatementId) {
		Customer_StatementId = customer_StatementId;
	}

	public String getCustomer_StatementType() {
		return Customer_StatementType;
	}

	public void setCustomer_StatementType(String customer_StatementType) {
		Customer_StatementType = customer_StatementType;
	}

	public double getCustomer_StatementAmount() {
		return Customer_StatementAmount;
	}

	public void setCustomer_StatementAmount(double customer_StatementAmount) {
		Customer_StatementAmount = customer_StatementAmount;
	}

	public LocalDate getCustomer_StatementDate() {
		return Customer_StatementDate;
	}

	public void setCustomer_StatementDate(LocalDate localDate) {
		Customer_StatementDate = localDate;
	}

	public LocalTime getCustomer_StatementTime() {
		return Customer_StatementTime;
	}

	public void setCustomer_StatementTime(LocalTime localTime) {
		Customer_StatementTime = localTime;
	}

	public double getCustomer_StatementBalanceAmount() {
		return Customer_StatementBalanceAmount;
	}

	public void setCustomer_StatementBalanceAmount(double customer_StatementBalanceAmount) {
		Customer_StatementBalanceAmount = customer_StatementBalanceAmount;
	}

	public long getCustomer_AccountNumber() {
		return customer_AccountNumber;
	}

	public void setCustomer_AccountNumber(long customer_AccountNumber) {
		this.customer_AccountNumber = customer_AccountNumber;
	}

	@Override
	public String toString() {
		return "CustomerStatement [Customer_StatementId=" + Customer_StatementId + ", Customer_StatementType="
				+ Customer_StatementType + ", Customer_StatementAmount=" + Customer_StatementAmount
				+ ", Customer_StatementDate=" + Customer_StatementDate + ", Customer_StatementTime="
				+ Customer_StatementTime + ", Customer_StatementBalanceAmount=" + Customer_StatementBalanceAmount
				+ ", customer_AccountNumber=" + customer_AccountNumber + "]";
	}
	
	
}
