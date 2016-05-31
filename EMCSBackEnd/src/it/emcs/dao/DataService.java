package it.emcs.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import it.emcs.model.KeyValuePair;
import it.emcs.model.NetworkDetail;
import it.emcs.model.SensorNumber;
import it.emcs.model.SensorResponse;

@Service
public class DataService {

	@Autowired
	DBConnection dbConnection;

	Logger logger = Logger.getLogger(DataService.class);

	public void saveObject(Object object) {

		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		mongoOperation.save(object);
		logger.info(" user : " + object);
	};

	public List<SensorResponse> getAllResponses() {
		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		List<SensorResponse> sensorResponseList = (List<SensorResponse>) mongoOperation.findAll(SensorResponse.class);
		return sensorResponseList;
	}

	public List<KeyValuePair> getSensorTypes() {

		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		Aggregation agg = newAggregation(group("key").count().as("value"),
				project("value").and("key").previousOperation());
		AggregationResults<KeyValuePair> groupResults = mongoOperation.aggregate(agg, SensorResponse.class,
				KeyValuePair.class);
		List<KeyValuePair> result = groupResults.getMappedResults();

		return result;
	}

	public List<SensorResponse> getSensorDataForGivenType(String requestType) {
		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		Query query = new Query();
		query.addCriteria(Criteria.where("key").is(requestType));
		List<SensorResponse> result = mongoOperation.find(query, SensorResponse.class);

		return result;
	}

	public List<SensorResponse> getSensorDataForGivenTypeForLastDay(String requestType) {

		Date startDate = new Date(System.currentTimeMillis());

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, -1);

		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		Query query = new Query();

		startDate = cal.getTime();

		// DBObject query =
		// QueryBuilder.start().put("date").greaterThanEquals(startDate).get();
		query.addCriteria(Criteria.where("key").is(requestType));

		query.addCriteria(Criteria.where("date").gte(startDate));
		List<SensorResponse> result = mongoOperation.find(query, SensorResponse.class);

		return result;
	}

	public SensorResponse getLatestValue(String requestType) {
		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		Query query = new Query();
		query.limit(1);
		query.addCriteria(Criteria.where("key").is(requestType));
		query.with(new Sort(Sort.Direction.DESC, "date"));
		List<SensorResponse> resultList = mongoOperation.find(query, SensorResponse.class);

		if (resultList.size() > 0) {
			return resultList.get(0);
		} else {

			return null;
		}

	}

	public List<SensorNumber> getNumberOfSensors() {
		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		Aggregation agg = newAggregation(group("nodeId").count().as("value"),
				project("value").and("nodeId").previousOperation());
		AggregationResults<SensorNumber> groupResults = mongoOperation.aggregate(agg, SensorResponse.class,
				SensorNumber.class);
		List<SensorNumber> result = groupResults.getMappedResults();
		return result;
	}

	public NetworkDetail getNetworkDetail() {
		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		List<NetworkDetail> resultList = mongoOperation.findAll(NetworkDetail.class);
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	public void saveNetworkDetail(NetworkDetail networkDetail) {
		MongoOperations mongoOperation = dbConnection.getMongoOperation();
		mongoOperation.dropCollection(NetworkDetail.class);
		// mongoOperation.dropCollection(SensorResponse.class);
		mongoOperation.save(networkDetail);

	}

}
