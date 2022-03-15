package de.mobile.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.mobile.model.Ad;
import de.mobile.model.Customer;
import de.mobile.model.MobileAd;
import de.mobile.model.MobileCustomer;
import de.mobile.repo.AdRepository;
import de.mobile.repo.CustomerRepository;

@Service
public class AdService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdService.class);

	/**
	 * adRepository
	 */
	private final AdRepository adRepository;

	/**
	 * customerRepository
	 */
	private final CustomerRepository customerRepository;

	private final SequenceGenerator sequenceGenerator;

	private final CustomerService customerService;

	/**
	 * @param adRepository
	 */
	@Inject
	public AdService(AdRepository adRepository, SequenceGenerator sequenceGenerator,
			CustomerRepository customerRepository, CustomerService customerService) {

		this.adRepository = adRepository;
		this.customerRepository = customerRepository;
		this.sequenceGenerator = sequenceGenerator;
		this.customerService = customerService;
	}

	/**
	 * @param adData
	 * @return adId
	 */
	public Long create(Ad adData) {
		LOGGER.info("Entered AdService.create()");

		MobileAd ad = inbound(adData);

		return adRepository.create(ad);

	}

	/**
	 * @param adId
	 * @return Ad
	 */
	public Ad get(Long adId) {
		LOGGER.info("Entered AdService.get()");

		try {
			MobileAd mobileAd = adRepository.get(adId);
			Ad adData = outbound(mobileAd);
			
			/** fetch and set customer data of the corresponding ad **/
			MobileCustomer mobileCustomer = customerRepository.view(mobileAd.getCustomerId());
			Customer customer = customerService.outbound(mobileCustomer);
			
			adData.setCustomer(customer);
			return adData;
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage());
			throw new NullPointerException("Entered Invalid Data");
		}
	}

	/**
	 * @return ad list
	 */
	public List<Ad> list() {
		LOGGER.info("Entered AdService.list()");

		List<Ad> listAds = null;
		try {
			listAds = adRepository.list().stream().map(this::outbound).collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return listAds;
	}

	/**
	 * @param mobileAd
	 * @return Ad
	 */
	private Ad outbound(MobileAd mobileAd) {

		Ad adData = new Ad();
		adData.setId(mobileAd.getId());
		adData.setCategory(mobileAd.getCategory());
		adData.setMake(mobileAd.getMake());
		adData.setModel(mobileAd.getModel());
		adData.setDescription(mobileAd.getDescription());
		adData.setPrice(mobileAd.getPrice());
		return adData;
	}

	/**
	 * @param adData
	 * @return MobileAd
	 */
	private MobileAd inbound(Ad adData) {

		MobileAd mobileAd = new MobileAd();
		mobileAd.setId(sequenceGenerator.generateSequence(MobileAd.SEQUENCE_NAME));
		mobileAd.setCategory(adData.getCategory());
		mobileAd.setMake(adData.getMake());
		mobileAd.setModel(adData.getModel());
		mobileAd.setDescription(adData.getDescription());
		mobileAd.setPrice(adData.getPrice());
		mobileAd.setCustomerId(adData.getCustomer().getId());
		return mobileAd;
	}

}
