package com.leetech.apis.mailapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.leetech.apis.mailapi.EventProgressTracker;
import com.leetech.apis.models.ConfirmationSentEvent;
import com.leetech.apis.models.SendConfirmationEvent;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

@Service
public class SendConfirmationConsumer implements Consumer<Event<SendConfirmationEvent>> {
	@Autowired
	private MailSender mailSender;

	@Value("${mail.messages.welcome}")
	private String messageBody;

	@Value("${mail.sources.accounts}")
	private String mailSource;

	@Autowired
	private EventBus eventBus;

	@Autowired
	private EventProgressTracker eventTracker;

	@Override
	public void accept(Event<SendConfirmationEvent> event) {
		// fire confirmation event

		// fire welcome event and instruction on how to use the app

		if (event != null) {
			String newMessage = messageBody.replace("<client>", event.getData().getFirstName());
			if (mailSender.send(event.getData().getAccountEmail(), mailSource, "Account Activation", newMessage)) {
				eventTracker.addEventList(event.getData().getEventSourceKey(),
						event.getData().getClass().getSimpleName());
				ConfirmationSentEvent confirmationSentEvent = new ConfirmationSentEvent();
				confirmationSentEvent.setEventSourceKey(event.getData().getEventSourceKey());
				eventBus.notify("confirmationSentEvent", Event.wrap(confirmationSentEvent));
			} else {
				System.out.println("Mail sending failed..");
			}
		}
	}

}
