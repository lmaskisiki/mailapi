package com.leetech.apis.mailapi.services;

import org.springframework.stereotype.Service;

import com.leetech.apis.models.MailItem;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class MailConsumber implements Consumer<Event<MailItem>> {

	@Override
	public void accept(Event<MailItem> mailEvent) {
		System.out.println("Mail recieved");
		MailItem mail = mailEvent.getData();
	}

}
