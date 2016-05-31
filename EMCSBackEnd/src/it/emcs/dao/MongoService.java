package it.emcs.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Service
public class MongoService {

	@Autowired
	DBConnection dbConnection;

	Logger logger = Logger.getLogger(MongoService.class);

	public DB getDataBase() {
		DB db = dbConnection.getDatabase();

		return db;

	}

	public MongoClient getMongoClient() {
		MongoClient mongoClient;
		try {
			mongoClient = dbConnection.getMongoClient();
			return mongoClient;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
