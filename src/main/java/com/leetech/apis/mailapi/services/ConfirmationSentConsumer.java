package com.leetech.apis.mailapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leetech.apis.mailapi.EventProgressTracker;
import com.leetech.apis.models.ConfirmationSentEvent;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Component
public class ConfirmationSentConsumer implements Consumer<Event<ConfirmationSentEvent>> {
	@Autowired
	private EventProgressTracker eventTracker;

	@Override
	public void accept(Event<ConfirmationSentEvent> event) {
		System.out.println("\n \r Email sent \n \r" + event.getData().getEventSourceKey());
		eventTracker.addEventList(event.getData().getEventSourceKey(), event.getData().getClass().getSimpleName());
	}

}
