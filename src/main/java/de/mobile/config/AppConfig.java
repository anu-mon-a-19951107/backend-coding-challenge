package de.mobile.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import de.mobile.error.ApiException;
import de.mobile.util.LocaleProvider;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "de.mobile" })
public class AppConfig {

	@Bean
	public LocaleProvider localeProvider() {
		return new LocaleProvider() {
			@Override
			public Locale get() {
				return LocaleContextHolder.getLocale();
			}
		};
	}

	@Bean
	public ApiException apiException() {
		return new ApiException();
	}

}
