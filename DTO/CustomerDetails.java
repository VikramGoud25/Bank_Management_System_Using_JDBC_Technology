package com.bank.DTO;

public class CustomerDetails
{
	private String name;
	private String emailId;
	private long mobileNumber;
	private long aadharNumber;
	private String address;
	private String gender;
	private long accountNumber;
	private double amount;
	private String pin;
	private String status;
	
	public CustomerDetails() {}

	public CustomerDetails(String name, String emailId, long mobileNumber, long aadharNumber, String address,
			String gender, long accountNumber, double amount, String pin,String status)
	{
		this.name = name;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.aadharNumber = aadharNumber;
		this.address = address;
		this.gender = gender;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.pin = pin;
		this.status=status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(long aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDetails [name=" + name + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber
				+ ", aadharNumber=" + aadharNumber + ", address=" + address + ", gender=" + gender + ", accountNumber="
				+ accountNumber + ", amount=" + amount + ", pin=" + pin + ", status=" + status + "]";
	}

}
