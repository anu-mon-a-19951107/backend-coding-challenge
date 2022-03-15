/**
 * 
 */
package de.mobile.error;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * @author anuantony_
 *
 */
public class ApiException {

	private HttpStatus httpStatus;
	private LocalDateTime localDateTime;
	private String message;
	private List<String> errorMessages;
	
	/**
	 * ApiException
	 */
	public ApiException() {}
	
	/**
	 * @param httpStatus
	 * @param localDateTime
	 * @param message
	 */
	public ApiException(HttpStatus httpStatus, LocalDateTime localDateTime, String message) {
		super();
		this.httpStatus = httpStatus;
		this.localDateTime = localDateTime;
		this.message = message;

	}

	/**
	 * @param httpStatus
	 * @param localDateTime
	 * @param message
	 * @param throwable
	 */
	public ApiException(HttpStatus httpStatus, LocalDateTime localDateTime, String message, Throwable throwable) {
		super();
		this.httpStatus = httpStatus;
		this.localDateTime = localDateTime;
		this.message = message;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the localDateTime
	 */
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	/**
	 * @param localDateTime the localDateTime to set
	 */
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
