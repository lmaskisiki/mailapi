package com.leetech.apis.models;

import java.util.UUID;

public class EventModel {
	private UUID eventSourceKey;

	public UUID getEventSourceKey() {
		return eventSourceKey;
	}

	public void setEventSourceKey(UUID eventSourceKey) {
		this.eventSourceKey = eventSourceKey;
	}

	public EventModel() {
		this.eventSourceKey = UUID.randomUUID();
	}
}
