package de.mobile.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.mobile.error.ApiException;
import de.mobile.model.Ad;
import de.mobile.model.Customer;
import de.mobile.service.AdService;
import de.mobile.service.CustomerService;

@Controller
@RequestMapping("ad")
public class AdResource {

	private final AdService adService;
	private final CustomerService customerService;
	private final ApiException apiException;

	@Inject
	public AdResource(AdService adService, CustomerService customerService, ApiException apiException) {

		this.adService = adService;
		this.customerService = customerService;
		this.apiException = apiException;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(AdResource.class);


	/**
	 * @return
	 */
	@RequestMapping(value = "/loadCreateAd")
	public ModelAndView loadCreateAdPage() {
		Ad ad = new Ad();
		ModelAndView modelAndView = new ModelAndView("createAd");
		modelAndView.addObject("ad", ad);
		return modelAndView;
	}

	/**
	 * @param adId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView get(@RequestParam("id") Long adId) throws Exception {
		LOGGER.info("Entered AdResource.get()");

		Ad ad = adService.get(adId);
		ModelAndView modelAndView = new ModelAndView("viewAd");
		modelAndView.addObject("ad", ad);
		return modelAndView;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/viewAll", method = RequestMethod.GET)
	public ModelAndView list() {
		LOGGER.info("Entered AdResource.list()");

		ModelAndView modelAndView = new ModelAndView("viewAllAd");
		List<Ad> adList = adService.list();
		modelAndView.addObject("allAds", adList);
		return modelAndView;
	}

	/**
	 * @param ad
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createAd(@ModelAttribute("ad") Ad ad) {
		LOGGER.info("Entered AdResource.createAd()");

		ModelAndView modelAndView = new ModelAndView("createAd");
		;
		if (validateAd(ad)) {

			adService.create(ad);
			return list();
		} else {
			modelAndView.addObject("errorMessage", apiException.getMessage());
			modelAndView.addObject("ad", ad);
		}
		return modelAndView;
	}

	private Long createCustomer(Customer customer) {

		return customerService.create(customer);
	}

	/**
	 * @param adData
	 * @return boolean
	 */
	private boolean validateAd(Ad adData) {
		if (adData.getMake() == null || adData.getMake().isEmpty()) {

			apiException.setMessage("'Make name' data not found");
			return false;
		}
		if (adData.getCategory() == null) {

			apiException.setMessage("Invalid Category name / Category value null");
			return false;
		}
		if (adData.getMake() == null || adData.getModel().isEmpty()) {

			apiException.setMessage("'Model name data not found");
			return false;
		}
		if (validateCustomer(adData.getCustomer())) {

			adData.getCustomer().setId(createCustomer(adData.getCustomer()));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param customer
	 * @return boolean
	 */
	private boolean validateCustomer(Customer customer) {
		String emailRegexPattern = "^(.+)@(\\S+)$";
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
