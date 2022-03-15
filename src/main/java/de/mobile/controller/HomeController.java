/**
 * 
 */
package de.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anuantony_
 *
 */
@Controller
public class HomeController {

	/**
	 * @return
	 */
	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}
}
