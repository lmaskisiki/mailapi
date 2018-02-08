package com.leetech.apis.models;

public class MailItem {
	private String subject;
	private String messageBody;
	private String mailTo;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public MailItem(String subject, String messageBody, String mailTo) {
		super();
		this.subject = subject;
		this.messageBody = messageBody;
		this.mailTo = mailTo;
	}

	
}
