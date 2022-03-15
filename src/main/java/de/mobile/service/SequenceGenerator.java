/**
 * 
 */
package de.mobile.service;

import java.util.Objects;

import javax.inject.Inject;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import de.mobile.model.DataBaseSequences;

/**
 * @author anuantony_
 *
 */
@Service
public class SequenceGenerator {

	private final MongoOperations mongoOperations;

	/**
	 * @param mongoOperations
	 */
	@Inject
	public SequenceGenerator(MongoOperations mongoOperations) {
		super();
		this.mongoOperations = mongoOperations;
	}

	public long generateSequence(String seqName) {
		
		DataBaseSequences counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DataBaseSequences.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}
}
