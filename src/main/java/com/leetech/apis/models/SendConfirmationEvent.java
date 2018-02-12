package com.leetech.apis.models;

import com.google.gson.JsonObject;

public class SendConfirmationEvent extends EventModel {

	private int accountKey;
	private String emailAddress;
	private String names;
	public int getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(int accountKey) {
		this.accountKey = accountKey;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public SendConfirmationEvent(int accountKey, String emailAddress, String names) {
		super();
		this.accountKey = accountKey;
		this.emailAddress = emailAddress;
		this.names = names;
	}
	
	

}
