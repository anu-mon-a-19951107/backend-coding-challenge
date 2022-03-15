/**
 * 
 */
package de.mobile.repo;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import de.mobile.model.MobileCustomer;

/**
 * @author anuantony_
 *
 */
@Repository
public class CustomerRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRepository.class);

	@Inject
	private MongoTemplate mongoTemplate;

	/**
	 * @param mobileCustomer
	 * @return
	 */
	public Long create(MobileCustomer mobileCustomer) {
		LOGGER.info("Entered CustomerRepository.create()");
		
		MobileCustomer mobileCustomer2 = mongoTemplate.save(mobileCustomer);
		
		return mobileCustomer2.getId();
	}

	/**
	 * @param mobileCustomer
	 * @return
	 */
	public Boolean delete(String email) {
		LOGGER.info("Entered CustomerRepository.delete()");

		//DeleteResult deleteResult = mongoTemplate.remove(mobileCustomer);
		
		DeleteResult deleteResult = mongoTemplate.remove(Query.query(Criteria.where("email").is(email)), MobileCustomer.class);
		
		return deleteResult.wasAcknowledged();
	}
	
	/**
	 * @param id
	 * @return
	 */
	public MobileCustomer view(Long id) {
		LOGGER.info("Entered CustomerRepository.view()");

		return mongoTemplate.findById(id, MobileCustomer.class);
	}
}
