package com.leetech.apis.mailapi.helpers;

import java.time.LocalDateTime;

public class Waiter {
	public boolean waitUntil(Runnable runnable, LocalDateTime time) {
		boolean occured = false;
		while (LocalDateTime.now().isBefore(time) && occured == false) {
			occured = runnable.run();
		}
		return occured;

	}
}
