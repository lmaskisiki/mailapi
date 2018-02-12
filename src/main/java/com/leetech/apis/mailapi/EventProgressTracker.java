package com.leetech.apis.mailapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class EventProgressTracker {

	List<String> events = new ArrayList<>();

	public List<String> getEventList() {
		return events;
	}

	public boolean contains(UUID eventSourceKey, String eventClass) {
		return events.contains(eventClass + "<" + eventSourceKey + ">");
	}

	public void addEventList(UUID eventSourceKey, String eventClass) {
		System.out.println("Now adding ::" + eventClass + "<" + eventSourceKey + ">");
		events.add(eventClass + "<" + eventSourceKey + ">");
		events.forEach(i -> System.out.println(i));
	}

}
