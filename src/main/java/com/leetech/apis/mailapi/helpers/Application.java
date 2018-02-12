package com.leetech.apis.mailapi.helpers;

import java.time.LocalDateTime;

public class Application {

	public static void main(String[] args) {
		LocalDateTime t = LocalDateTime.now().plusSeconds(15);
		new Waiter().waitUntil(() -> {
			return LocalDateTime.now().isAfter(t);
		}, LocalDateTime.now().plusSeconds(5));

	}

}
