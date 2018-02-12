package com.leetech.apis.mailapi.rest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.leetech.apis.mailapi.EventProgressTracker;
import com.leetech.apis.mailapi.helpers.Waiter;
import com.leetech.apis.models.ConfirmationSentEvent;
import com.leetech.apis.models.MailItem;
import com.leetech.apis.models.SendConfirmationEvent;

import reactor.bus.Event;
import reactor.bus.EventBus;

@RestController
public class MailapiController {
	@Autowired
	private EventBus eventBus;
	@Autowired
	private EventProgressTracker progressTracker;
	private Gson gson = new Gson();
	JsonParser parser = new JsonParser();

	@GetMapping("/")
	public String home() {
		return "welcome";
	}

	@PostMapping("/sendmail")
	public void sendMail(@RequestBody String mailData) {
		MailItem mail = new MailItem("", "", "");
		eventBus.notify("mailConsumer", Event.wrap(mail));

	}

	@PostMapping("/newaccount")
	@Consumes("application/json")
	public boolean newAccount(@RequestBody String newAccountData) {
		try {
			SendConfirmationEvent sendConfirmationEvent = gson.fromJson(newAccountData, SendConfirmationEvent.class);
			eventBus.notify("sendConfirmationEvent", Event.wrap(sendConfirmationEvent));
			return new Waiter().waitUntil(() -> {
				return progressTracker.contains(sendConfirmationEvent.getEventSourceKey(),
						ConfirmationSentEvent.class.getSimpleName());
			}, LocalDateTime.now().plusSeconds(10));
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
}
