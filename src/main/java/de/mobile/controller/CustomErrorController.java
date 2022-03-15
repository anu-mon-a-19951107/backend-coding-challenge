/**
 * 
 */
package de.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author anuantony_
 *
 */
@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	@ResponseBody
	public String error(HttpServletRequest request) {
		return "Some error occcurred , Please check the site address and try again";
	}
}