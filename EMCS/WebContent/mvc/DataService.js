/**
 * 
 */
angular.module('servicesModule', []).service(
		'DataService',
		function($http) {

			return {

				getAllSensorDataService : function() {

					var url = BASE_URL + '/rest/sensor-data-all';
					var data = {};
					var config = {};

					return $http.get(url, config);
				},

				getChartDataForTypeService : function(requestType) {

					var url = BASE_URL
							+ '/rest/sensor-data-for-type?requestType='
							+ requestType;
					var data = {};
					var config = {};
					return $http.get(url, config);
				},

				// latest value for gauge
				getLatestValueService : function(requestType) {

					var url = BASE_URL + '/rest/latest-value?requestType='
							+ requestType;
					var data = {};
					var config = {};

					return $http.get(url, config);
				},

				// latest value for gauge
				getChartDataForTypeService : function(requestType) {
					var url = BASE_URL
							+ '/rest/sensor-data-for-type?requestType='
							+ requestType;
					var data = {};
					var config = {};

					return $http.get(url, config);
				},
				// number of sensors saved
				getNoOfSensors : function() {
					var url = BASE_URL + '/rest/sensor-numbers';
					var data = {};
					var config = {};

					return $http.get(url, config);
				},

				// types of sensors saved
				getTypesOfSensors : function() {
					var url = BASE_URL + '/rest/sensor-types';
					var data = {};
					var config = {};

					return $http.get(url, config);
				},
				// get network details
				getNetworkDetailsService : function() {
					var url = BASE_URL + '/rest/network-detail';
					var data = {};
					var config = {};

					return $http.get(url, config);
				},

			};

		});