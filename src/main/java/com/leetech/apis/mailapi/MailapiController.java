package com.leetech.apis.mailapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leetech.apis.models.MailItem;

import reactor.bus.Event;
import reactor.bus.EventBus;

@RestController
public class MailapiController {
	@Autowired
	private EventBus eventBus;

	@GetMapping("/home")
	public String home() {
		MailItem mail = new MailItem("", "", "");
		eventBus.notify("mailConsumer", Event.wrap(mail));
		return "welcome";
	}
}
