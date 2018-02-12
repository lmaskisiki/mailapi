package com.leetech.apis.models;

public class SendConfirmationEvent extends EventModel {
 
	private int accountKey;
	private String accountEmail;
	private String firstName;
	public int getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}
	public String getAccountEmail() {
		return accountEmail;
	}
	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public SendConfirmationEvent(int accountKey, String accountEmail, String firstName) {
		super();
		this.accountKey = accountKey;
		this.accountEmail = accountEmail;
		this.firstName = firstName;
	}

 

}
