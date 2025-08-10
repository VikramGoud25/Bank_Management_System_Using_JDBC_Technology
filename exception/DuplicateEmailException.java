package com.bank.exception;

public class DuplicateEmailException extends RuntimeException{

	@Override
	public String toString() {
		return getClass()+"DuplicateEmailException";
	}
 
	
}
