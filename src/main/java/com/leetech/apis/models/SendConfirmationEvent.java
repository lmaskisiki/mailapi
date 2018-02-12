package com.leetech.apis.models;

public class SendConfirmationEvent extends EventModel {
 
	private int accouuntKey;
	private String accountEmail;
	private String firstName;

	public int getAccouuntKey() {
		return accouuntKey;
	}

	public void setAccouuntKey(int accouuntKey) {
		this.accouuntKey = accouuntKey;
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

	public SendConfirmationEvent(int accouuntKey, String accountEmail, String firstName) {
		super();
		this.accouuntKey = accouuntKey;
		this.accountEmail = accountEmail;
		this.firstName = firstName;
	}

}
