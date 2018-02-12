package com.leetech.apis.mailapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.leetech.apis.mailapi.EventProgressTracker;
import com.leetech.apis.models.MailItem;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class MailConsumer implements Consumer<Event<MailItem>> {

	@Autowired
	private MailSender mailSender;

	@Value("${mail.sources.accounts}")
	private String accountsMail;

	@Value("${mail.sources.accounts}")
	private String infoMail;

	@Override
	public void accept(Event<MailItem> mailEvent) {
		MailItem mail = mailEvent.getData();
		if (mail == null)
			throw new NullPointerException("Event object cannot be null");
		mailSender.send(mail.getMailTo(), infoMail, mail.getSubject(), mail.getMessageBody());
	}

}
