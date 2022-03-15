package de.mobile.repo;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import de.mobile.model.MobileAd;

/**
 * @author anuantony_
 *
 */
@Repository
public class AdRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdRepository.class);

	@Inject
	private MongoTemplate mongoTemplate;

	/**
	 * @param ad
	 * @return created adId
	 */
	public Long create(MobileAd ad) {
		LOGGER.info("Entered AdRepository.create()");
		
		MobileAd mobileAd = mongoTemplate.save(ad);
		
		return mobileAd.getId();
	}

	/**
	 * @param adId
	 * @return ad based on the given adId
	 */
	public MobileAd get(Long adId) {
		LOGGER.info("Entered AdRepository.get()");

		return mongoTemplate.findById(adId, MobileAd.class);
	}

	/**
	 * @return list of ads
	 */
	public List<MobileAd> list() {
		LOGGER.info("Entered AdRepository.list()");
		
		return mongoTemplate.findAll(MobileAd.class);
	}
}
