/**
 * 
 */
package de.mobile.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.mobile.model.Customer;
import de.mobile.model.MobileCustomer;
import de.mobile.repo.CustomerRepository;

/**
 * @author anuantony_
 *
 */
@Service
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	private final CustomerRepository customerRepository;
	private final SequenceGenerator sequenceGenerator;

	/**
	 * @param customerRepository
	 * @param sequenceGenerator
	 */
	@Inject
	public CustomerService(CustomerRepository customerRepository, SequenceGenerator sequenceGenerator) {
		super();
		this.customerRepository = customerRepository;
		this.sequenceGenerator = sequenceGenerator;
	}

	/**
	 * @param customer
	 * @return
	 */
	public Long create(Customer customer) {
		LOGGER.info("Entered CustomerService.create()");

		MobileCustomer mobileCustomer = inbound(customer);

		Long customerId = customerRepository.create(mobileCustomer);

		return customerId;
	}

	/**
	 * @param customerId
	 * @return
	 */
	public Boolean delete(String email) {
		LOGGER.info("Entered CustomerService.view()");
		try {
			Boolean deleteAcknwledge = customerRepository.delete(email);
			return deleteAcknwledge;
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			throw new NullPointerException("Entered Invalid Data");
		}

	}

	/**
	 * @param mobileCustomer
	 * @return
	 */
	public Customer outbound(MobileCustomer mobileCustomer) {
		Customer customer = new Customer();
		customer.setId(mobileCustomer.getId());
		customer.setFirstName(mobileCustomer.getFirstName());
		customer.setLastName(mobileCustomer.getLastName());
		customer.setEmail(mobileCustomer.getEmail());
		customer.setCompanyName(mobileCustomer.getCompanyName());
		return customer;
	}

	/**
	 * @param customer
	 * @return
	 */
	public MobileCustomer inbound(Customer customer) {
		MobileCustomer mobileCustomer = new MobileCustomer();
		mobileCustomer.setId(sequenceGenerator.generateSequence(MobileCustomer.SEQUENCE_NAME));
		mobileCustomer.setFirstName(customer.getFirstName());
		mobileCustomer.setLastName(customer.getLastName());
		mobileCustomer.setEmail(customer.getEmail());
		mobileCustomer.setCompanyName(customer.getCompanyName());
		return mobileCustomer;
	}
}
