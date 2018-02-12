package com.leetech.apis.mailapi.services;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean send(String toAdress, String fromAddress, String subject, String messageBody) {
		try {
			MimeMessagePreparator preparedMail = this.prepareMail(toAdress, fromAddress, subject, messageBody);
			javaMailSender.send(preparedMail);
			return true;
		} catch (Exception e) {
			System.out.println("Something went wrong! " + e.getMessage());
		}
		return false;
	}

	private MimeMessagePreparator prepareMail(String toAdress, String fromAddress, String subject, String messageBody) {
		return new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage message) throws Exception {
				message.setFrom(fromAddress);
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAdress));
				message.setSubject(subject);
				message.setText(messageBody);
			}
		};
	}
}
