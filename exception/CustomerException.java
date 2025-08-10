package com.bank.exception;

public class CustomerException extends RuntimeException
{
	private String exceptionMsg;
	
	public CustomerException() {}

	public CustomerException(String exceptionMsg) {
		super();
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return "CustomerException [exceptionMsg=" + exceptionMsg + "]";
	}
	
}
