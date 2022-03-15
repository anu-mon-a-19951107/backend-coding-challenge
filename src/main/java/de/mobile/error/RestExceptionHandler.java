/**
 * 
 */
package de.mobile.error;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author anuantony_
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	/**
	 * @param ex
	 * @return 500 Internal Server Error
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleGeneralExceptions(Exception ex) {
		LOGGER.info("Entered RestExceptionHandler.handleGeneralExceptions()");
		
		ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(),
				ex.getMessage());
		
		return new ResponseEntity<>(apiException, apiException.getHttpStatus());
	}

	/**
	 * @param ex
	 * @return 404 Error
	 */
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleDataNotFoundException(Exception ex) {
		LOGGER.info("Entered RestExceptionHandler.handleDataNotFoundException()");

		ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
		
		return new ResponseEntity<>(apiException, apiException.getHttpStatus());
	}
}
