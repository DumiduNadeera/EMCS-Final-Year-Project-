package it.emcs.dao;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Service
public class DBConnection {

	Logger logger = Logger.getLogger(DBConnection.class);

	private static MongoClient mongoClient;

	private static MongoDbFactory mongoDbFactory;

	private static MongoTemplate mongoTemplate;

	private static MongoOperations mongoOperation;

	public DBConnection() {

		try {

			mongoClient = new MongoClient("127.0.0.1", 27017);
			mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "emcsdb");
			mongoTemplate = new MongoTemplate(mongoDbFactory);
			mongoOperation = (MongoOperations) mongoTemplate;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MongoOperations getMongoOperation() {
		return mongoOperation;
	}

	public MongoClient getMongoClient() throws Exception {
		return mongoClient;
	}

	public DB getDatabase() {
		DB db = mongoClient.getDB("emcsdb");
		return db;
	}

	public void closeMongoClient() {
		mongoClient.close();
	}
}
