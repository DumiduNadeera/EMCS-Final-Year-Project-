package it.emcs.controller;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DB;
import it.emcs.dao.MongoService;

@RestController
@RequestMapping("/rest")
public class MongoDBController {

	Logger logger = Logger.getLogger(MongoDBController.class.getName());

	@Autowired
	MongoService dataService;

	@RequestMapping(value = "/mongo/collections", method = RequestMethod.GET)
	public ResponseEntity<String> getSensorData() {

		logger.info("Collections request");

		DB db = dataService.getDataBase();

		logger.info(db.getName());

		Set<String> colls = db.getCollectionNames();

		for (Iterator<String> iterator = colls.iterator(); iterator.hasNext();) {
			iterator.next();
		}

		return new ResponseEntity<>(db.toString(), HttpStatus.OK);
	}

}
