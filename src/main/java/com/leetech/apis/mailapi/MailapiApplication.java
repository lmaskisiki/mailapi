package com.leetech.apis.mailapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.leetech.apis.mailapi.services.MailConsumber;

import reactor.Environment;
import reactor.bus.EventBus;
import static reactor.bus.selector.Selectors.$;

@SpringBootApplication
@EnableAutoConfiguration
public class MailapiApplication implements CommandLineRunner {

	@Autowired
	private EventBus eventBus;

	@Autowired
	private MailConsumber MailConsumber;

	@Bean
	Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@Bean
	EventBus createEventBus(Environment env) {
		return EventBus.create(env, Environment.THREAD_POOL);
	}

	public static void main(String[] args) {
		SpringApplication.run(MailapiApplication.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		eventBus.on($("mailConsumer"), MailConsumber);
	}
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//	    mailSender.setHost("smtp.gmail.com");
//	    mailSender.setPort(587);
//	     
//	    mailSender.setUsername("my.gmail@gmail.com");
//	    mailSender.setPassword("password");
//	     
//	    Properties props = mailSender.getJavaMailProperties();
//	    props.put("mail.transport.protocol", "smtp");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.starttls.enable", "true");
//	    props.put("mail.debug", "true");
//	     
//	    return mailSender;
//	}
}
