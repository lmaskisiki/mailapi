package com.leetech.apis.mailapi;

import static reactor.bus.selector.Selectors.$;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leetech.apis.mailapi.helpers.Waiter;
import com.leetech.apis.mailapi.services.ConfirmationSentConsumer;
import com.leetech.apis.mailapi.services.MailConsumer;
import com.leetech.apis.mailapi.services.SendConfirmationConsumer;
import com.leetech.apis.models.ConfirmationSentEvent;

import reactor.bus.Event;
import reactor.bus.EventBus;

@SpringBootApplication
@EnableAutoConfiguration
public class MailapiApplication implements CommandLineRunner {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private MailConsumer MailConsumer;
	@Autowired
	private SendConfirmationConsumer sendConfirmationConsumer;

	@Autowired
	private ConfirmationSentConsumer confirmationSentConsumer;

	@Override
	public void run(String... args) throws Exception {
		eventBus.on($("mailConsumer"), MailConsumer);
		eventBus.on($("sendConfirmationEvent"), sendConfirmationConsumer);
		eventBus.on($("confirmationSentEvent"), confirmationSentConsumer);
	}

	public static void main(String[] args) {
		SpringApplication.run(MailapiApplication.class, args);
	}
}
