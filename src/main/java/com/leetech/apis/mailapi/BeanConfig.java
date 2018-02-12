package com.leetech.apis.mailapi;

import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import reactor.Environment;
import reactor.bus.EventBus;

@Configuration
public class BeanConfig {

	@Value("${spring.mail.host}")
	private String mailServerHost;

	@Value("${spring.mail.port}")
	private int mailServerPort;

	@Value("${spring.mail.username}")
	private String mailServerUsername;

	@Value("${spring.mail.password}")
	private String mailServerPassword;

	@Value("${spring.mail.smtp.auth}")
	private boolean enableSMTPAuth;

	@Value("${spring.mail.smtp.starttls.enable}")
	private boolean enableStartTLS;

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(mailServerHost);
		mailSender.setPort(mailServerPort);
		mailSender.setUsername(mailServerUsername);
		mailSender.setPassword(mailServerPassword);
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", enableSMTPAuth);
		props.put("mail.smtp.starttls.enable", enableStartTLS);
		props.put("mail.debug", "false");
		return mailSender;
	}

	@Bean
	@Primary
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://www.masikisiki.co.za",
				"http://www.gym.masikisiki.co.za"));
		configuration.setAllowedHeaders(Arrays.asList("*", "Authorization", "*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
