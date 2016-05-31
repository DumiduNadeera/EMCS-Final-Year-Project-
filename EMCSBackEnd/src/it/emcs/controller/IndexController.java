package it.emcs.controller;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.emcs.dao.DataService;

@RestController
public class IndexController {

	Logger logger = Logger.getLogger(IndexController.class.getName());

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	DataService dataService;

	@RequestMapping("/")
	public String message() {

		logger.info("Respond to site load");
		/*
		 * Date date = new Date(System.currentTimeMillis());
		 * 
		 * Random r = new Random(); double randomValue = 10 + (20) *
		 * r.nextDouble();
		 * 
		 * KeyValuePair pair = new KeyValuePair(); pair.setKey("Temperature");
		 * pair.setValue(randomValue); SensorResponse response = new
		 * SensorResponse(1, date, pair.getKey(), pair.getValue());
		 * dataService.saveObject(response);
		 * 
		 * randomValue = r.nextDouble(); pair = new KeyValuePair();
		 * pair.setKey("Humidity"); pair.setValue(randomValue); response = new
		 * SensorResponse(1, date, pair.getKey(), pair.getValue());
		 * dataService.saveObject(response);
		 * 
		 * randomValue = r.nextDouble(); pair = new KeyValuePair();
		 * pair.setKey("Moisture"); pair.setValue(randomValue); response = new
		 * SensorResponse(1, date, pair.getKey(), pair.getValue());
		 * dataService.saveObject(response);
		 */
		return "Welcome to EMCS";
	}

}
