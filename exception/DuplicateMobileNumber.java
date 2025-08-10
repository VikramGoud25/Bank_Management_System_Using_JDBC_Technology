package com.bank.exception;

public class DuplicateMobileNumber extends RuntimeException
{
	@Override
	public String toString() {
		return getClass()+"DuplicateMobileNumber";
	}
}
