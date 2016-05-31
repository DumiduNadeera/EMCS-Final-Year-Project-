package it.emcs.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.emcs.dao.DataService;
import it.emcs.model.KeyValuePair;
import it.emcs.model.NetworkDetail;
import it.emcs.model.SensorNumber;

@RestController
@RequestMapping("/rest")
public class SystemDetailsController {

	Logger logger = Logger.getLogger(SystemDetailsController.class.getName());

	@Autowired
	DataService dataService;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/sensor-types", method = RequestMethod.GET)
	public ResponseEntity<List<KeyValuePair>> getSensorTypes() {

		List<KeyValuePair> responseList = dataService.getSensorTypes();
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}

	@RequestMapping(value = "/sensor-numbers", method = RequestMethod.GET)
	public ResponseEntity<List<SensorNumber>> getNoOfSensors() {

		List<SensorNumber> responseNumber = dataService.getNumberOfSensors();
		logger.info("Sensor number response : " + responseNumber.size());
		return new ResponseEntity<>(responseNumber, HttpStatus.OK);
	}

	@RequestMapping(value = "/network-detail", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<NetworkDetail> getNetworkDetails() {

		NetworkDetail responseDetail = dataService.getNetworkDetail();
		return new ResponseEntity<>(responseDetail, HttpStatus.OK);
	}

}
