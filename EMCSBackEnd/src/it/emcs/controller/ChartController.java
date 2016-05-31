package it.emcs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.emcs.dao.DataService;
import it.emcs.model.KeyValuePair;
import it.emcs.model.SensorResponse;
import it.emcs.model.response.SensorChartResponse;

@RestController
@RequestMapping("/rest")
public class ChartController {

	Logger logger = Logger.getLogger(ChartController.class.getName());

	@Autowired
	DataService dataService;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/sensor-data-all", method = RequestMethod.GET)
	public ResponseEntity<List<SensorResponse>> getAllData() {

		List<SensorResponse> responseList = dataService.getAllResponses();
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}

	@RequestMapping(value = "/sensor-data-for-type", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<KeyValuePair>> getSensorDataOfGivenType(
			@RequestParam("requestType") String requestType) {

		SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		logger.info(requestType + " : ");
		// List<SensorResponse> resultList =
		// dataService.getSensorDataForGivenType(requestType);

		List<SensorResponse> resultList = dataService.getSensorDataForGivenType(requestType);

		ArrayList<KeyValuePair> responseList = new ArrayList<>();

		for (Iterator<SensorResponse> iterator = resultList.iterator(); iterator.hasNext();) {
			SensorResponse sensorResponse = (SensorResponse) iterator.next();

			logger.info(" : " + sensorResponse.getDate());

			KeyValuePair keyValuePair = new KeyValuePair();

			keyValuePair.setKey(fullDateFormat.format(sensorResponse.getDate()));
			keyValuePair.setValue(sensorResponse.getValue());

			responseList.add(keyValuePair);

		}
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}

	@RequestMapping(value = "/sensor-data-for-type-last-day/", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<KeyValuePair>> getSensorDataOfGivenTypeForGivenDateRange(
			@RequestParam("requestType") String requestType) {

		SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		logger.info(requestType + " : ");
		List<SensorResponse> resultList = dataService.getSensorDataForGivenTypeForLastDay(requestType);

		ArrayList<KeyValuePair> responseList = new ArrayList<>();

		for (Iterator<SensorResponse> iterator = resultList.iterator(); iterator.hasNext();) {
			SensorResponse sensorResponse = (SensorResponse) iterator.next();

			logger.info(" : " + sensorResponse.getDate());

			KeyValuePair keyValuePair = new KeyValuePair();

			keyValuePair.setKey(fullDateFormat.format(sensorResponse.getDate()));
			keyValuePair.setValue(sensorResponse.getValue());

			responseList.add(keyValuePair);

		}
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}

	@RequestMapping(value = "/latest-value", method = RequestMethod.GET)
	public ResponseEntity<SensorChartResponse> getLatestValueForGivenType(
			@RequestParam("requestType") String requestType) {

		logger.info("send latest " + requestType);
		SensorResponse resultResponse = dataService.getLatestValue(requestType);
		if (resultResponse != null) {
			SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SensorChartResponse response = new SensorChartResponse();
			response.setCheckedTime(fullDateFormat.format(resultResponse.getDate()));
			response.setKey(resultResponse.getKey());
			response.setNetworkId(resultResponse.getNetworkId());
			response.setNodeId(resultResponse.getNodeId());
			response.setValue(resultResponse.getValue());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.OK);
		}

	}
}
