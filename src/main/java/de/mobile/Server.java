package de.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.mobile.config.AppConfig;

@SpringBootApplication
public class Server {

	private static final Logger LOGGER = LoggerFactory.getLogger(Class.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(AppConfig.class, args);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
