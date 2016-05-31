package it.emcs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import it.emcs.dao.DataService;
import it.emcs.model.KeyValuePair;
import it.emcs.model.NetworkDetail;
import it.emcs.model.SensorResponse;
import it.emcs.model.request.SensorRequest;

@RestController
@RequestMapping("/rest")
public class DataImportController {

	Logger logger = Logger.getLogger(DataImportController.class.getName());

	@Autowired
	DataService dataService;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/sensor-data", method = RequestMethod.POST)
	public ResponseEntity<String> getSensorData(@RequestBody SensorRequest request) {

		logger.info("Getting Sensor data");
		Date dateNew = new Date(System.currentTimeMillis());
		for (Iterator<KeyValuePair> iterator = request.getDataList().iterator(); iterator.hasNext();) {
			KeyValuePair keyValuePair = (KeyValuePair) iterator.next();
			SensorResponse response = new SensorResponse(request.getNetworkId(), request.getNodeId(), dateNew,
					keyValuePair.getKey(), keyValuePair.getValue());
			dataService.saveObject(response);
		}
		logger.info("data saved");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/sensor-data-request", method = RequestMethod.GET)
	public ResponseEntity<SensorRequest> getrequestModel(SensorRequest request) {

		Date dateNew;
		dateNew = new Date(System.currentTimeMillis());

		for (Iterator<KeyValuePair> iterator = request.getDataList().iterator(); iterator.hasNext();) {
			KeyValuePair keyValuePair = (KeyValuePair) iterator.next();

			SensorResponse response = new SensorResponse(request.getNetworkId(), request.getNodeId(), dateNew,
					keyValuePair.getKey(), keyValuePair.getValue());
			dataService.saveObject(response);
		}
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	@RequestMapping(value = "/sensor-data-update", method = RequestMethod.GET)
	public ResponseEntity<SensorRequest> updateSensorData() {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://169.254.1.2:5000/rest/sensor-data";
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		// MappingJacksonHttpMessageConverter map = new
		// MappingJacksonHttpMessageConverter();

		MappingJackson2HttpMessageConverter map = new MappingJackson2HttpMessageConverter();
		messageConverters.add(map);
		restTemplate.setMessageConverters(messageConverters);
		SensorRequest sensorRequest = restTemplate.getForObject(url, SensorRequest.class);
		logger.info("The object received from REST call : " + sensorRequest);
		ArrayList<KeyValuePair> sensorDataList = sensorRequest.getDataList();

		Date dateNew = new Date(System.currentTimeMillis());
		for (Iterator<KeyValuePair> iterator = sensorDataList.iterator(); iterator.hasNext();) {
			KeyValuePair keyValuePair = (KeyValuePair) iterator.next();
			SensorResponse response = new SensorResponse(sensorRequest.getNetworkId(), sensorRequest.getNodeId(),
					dateNew, keyValuePair.getKey(), keyValuePair.getValue());
			dataService.saveObject(response);
		}
		return new ResponseEntity<>(sensorRequest, HttpStatus.OK);
	}

	@RequestMapping(value = "/network-detail", method = RequestMethod.GET)
	public ResponseEntity<NetworkDetail> getLatestValueForGivenType(@RequestParam("owner") String owner,
			@RequestParam("location") String location, @RequestParam("businessType") String businessType) {

		logger.info("import Network detail " + owner + " : " + location + " : " + " : " + businessType);

		NetworkDetail networkDetail = new NetworkDetail();
		networkDetail.setBusinessType(businessType);
		networkDetail.setLocation(location);
		networkDetail.setOwner(owner);

		dataService.saveNetworkDetail(networkDetail);
		return new ResponseEntity<>(networkDetail, HttpStatus.OK);
	}

}
