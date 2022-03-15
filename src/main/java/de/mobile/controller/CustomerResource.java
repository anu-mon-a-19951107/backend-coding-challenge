package de.mobile.controller;

import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.mobile.error.ApiException;
import de.mobile.model.Customer;
import de.mobile.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResource.class);

	private final CustomerService customerService;
	private final ApiException apiException;
	private static final String emailRegexPattern = "^(.+)@(\\S+)$";

	/**
	 * @param customerService
	 * @param apiException
	 */
	@Inject
	public CustomerResource(CustomerService customerService, ApiException apiException) {
		this.customerService = customerService;
		this.apiException = apiException;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/loadCreateCustomer", method = RequestMethod.GET)
	public ModelAndView loadCreateCustomerPage() {
		Customer customer = new Customer();
		ModelAndView modelAndView = new ModelAndView("createCustomer");
		modelAndView.addObject("customer", customer);
		return modelAndView;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/loadDeleteCustomer")
	public ModelAndView loadDeleteCustomerPage() {
		Customer customer = new Customer();
		ModelAndView modelAndView = new ModelAndView("deleteCustomer");
		modelAndView.addObject("customer", customer);
		return modelAndView;
	}

	/**
	 * @param customer
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("customer") Customer customer) {
		LOGGER.info("Entered CustomerResource.create()");

		ModelAndView modelAndView = new ModelAndView("createCustomer");
		if (validateCustomer(customer)) {
			customerService.create(customer);
			modelAndView.addObject("successMessage", "Customer created succcessfully");
			return loadCreateCustomerPage();
		} else {
			modelAndView.addObject("customer", customer);
			modelAndView.addObject("errorMessage", apiException.getMessage());
		}
		return modelAndView;
	}

	/**
	 * @param custId
	 */
	@RequestMapping(value = "/delete{email}")
	public ModelAndView delete(@ModelAttribute("customer") Customer customer) {
		LOGGER.info("Entered CustomerResource.delete()");

		ModelAndView modelAndView = new ModelAndView("deleteCustomer");
		if (customer.getEmail() != null && !customer.getEmail().isEmpty()
				&& Pattern.compile(emailRegexPattern).matcher(customer.getEmail()).matches()) {

			Boolean wasAcknowledged = customerService.delete(customer.getEmail());
			if (wasAcknowledged) {
				modelAndView.addObject("successMessage", "Customer deleted successfully");
				return loadDeleteCustomerPage();
			}
		} else {
			apiException.setMessage("Customer email is either empty or wrong");
			modelAndView.addObject("errorMessage",apiException.getMessage());
		}
		return modelAndView;

	}

	/**
	 * @param customer
	 * @param errorMessages
	 * @return boolean
	 */
	private boolean validateCustomer(Customer customer) {
		if (customer.getFirstName() == null
				|| customer.getFirstName().isEmpty() && !customer.getFirstName().matches("^[a-zA-Z0-9]*$")) {

			apiException.setMessage("Customer 'First Name' is empty or contains special characters");
			return false;
		}
		if (customer.getLastName() == null
				|| customer.getLastName().isEmpty() && customer.getLastName().matches("^[a-zA-Z0-9]*$")) {

			apiException.setMessage("Customer 'Last  Name' is empty or contains special characters");
			return false;
		}
		if (customer.getEmail() == null || customer.getEmail().isEmpty()) {

			apiException.setMessage("Email Id is empty");
			return false;
		}
		if (customer.getEmail() != null && !customer.getEmail().isEmpty() && !Pattern.compile(emailRegexPattern).//
				matcher(customer.getEmail()).//
				matches()) {

			apiException.setMessage("Invalid Email");
			return false;
		}
		return true;
	}
}
