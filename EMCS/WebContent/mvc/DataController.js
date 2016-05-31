/**
 * 
 */
var injectParams = [ '$scope', '$interval', '$filter', '$http', 'DataService' ];

var DataController = function($scope, $interval, $filter, $http, DataService) {

	$interval(function() {
		console.log("Hi : " + $scope.countNumber);
		$scope.countNumber++;

		$scope.getLatestValueForHumidity();
		$scope.getLatestValueForTemperature();
		$scope.getLatestValueForMoisture();
		$scope.getChartDataForTemperature();
		$scope.getChartDataForHumidity();
	}, 5000);

	$scope.init = function() {
		$scope.id = "Hi";
		$scope.name = "Supun";
		$scope.countNumber = 0;

		$scope.LatestTemperature = 23;
		$scope.LatestHumidity = .50;
		$scope.LatestMoisture = .50;

		$scope.dataseriesTemperature = [];
		$scope.dataseriesHumidity = [];

		console.log("Hi");

		$scope.stats = [ {
			value : 30,
			key : new Date("2011/12/20")
		}, {
			value : 50,
			key : new Date("2011/12/21")
		}, {
			value : 45,
			key : new Date("2011/12/22")
		}, {
			value : 40,
			key : new Date("2011/12/23")
		}, {
			value : 35,
			key : new Date("2011/12/24")
		}, {
			value : 40,
			key : new Date("2011/12/25")
		}, {
			value : 42,
			key : new Date("2011/12/26")
		}, {
			value : 40,
			key : new Date("2011/12/27")
		}, {
			value : 35,
			key : new Date("2011/12/28")
		}, {
			value : 43,
			key : new Date("2011/12/29")
		} ];

		$scope.temperatureLineChart($scope.stats);
		$scope.humidityLineChart($scope.stats);
		$scope.createTemperatureGauge($scope.LatestTemperature);
		$scope.createHumidityGauge($scope.LatestHumidity);
		$scope.createMoistureGauge($scope.LatestMoisture);

		$scope.getNumberOfSensors();
		$scope.getTypesOfSensors();
		$scope.getNetworkDetails();

	};

	$scope.loadData = function() {
		DataService.getAllSensorDataService().success(function(data) {
			$scope.newDataList = data;
		}).error(function(data) {
			console.log(data);
		});

	}
	// populate gauges
	$scope.getLatestValueForTemperature = function() {

		DataService.getLatestValueService("Temperature").success(
				function(data) {
					$scope.LatestTemperature = data.value;
					$scope.createTemperatureGauge($scope.LatestTemperature);
				}).error(function(data) {
			console.log(data);
		});
	}

	$scope.getLatestValueForHumidity = function() {
		DataService.getLatestValueService("Humidity").success(function(data) {
			$scope.LatestHumidity = data.value;
			$scope.createHumidityGauge($scope.LatestHumidity);
		}).error(function(data) {
			console.log(data);
		});
	}

	$scope.getLatestValueForMoisture = function() {
		DataService.getLatestValueService("Moisture").success(function(data) {
			$scope.LatestMoisture = data.value;
			$scope.createMoistureGauge($scope.LatestMoisture);
		}).error(function(data) {
			console.log(data);
		});
	}

	// populate charts
	$scope.getChartDataForTemperature = function() {
		DataService.getChartDataForTypeService("Temperature").success(
				function(data) {
					$scope.stats = data;

					angular.forEach($scope.stats, function(item) {

						$scope.newDate = new Date(item.key);
						$scope.dataseriesTemperature.push({
							key : new Date(item.key),
							value : item.value,
						});
					})

					$scope.temperatureLineChart($scope.dataseriesTemperature);

				}).error(function(data) {
			console.log(data);
		});

	}

	$scope.getChartDataForHumidity = function() {
		DataService.getChartDataForTypeService("Humidity").success(
				function(data) {
					$scope.stats = data;

					angular.forEach($scope.stats, function(item) {

						$scope.newDate = new Date(item.key);
						$scope.dataseriesHumidity.push({
							key : new Date(item.key),
							value : item.value,
						});
					})

					$scope.humidityLineChart($scope.dataseriesHumidity);

				}).error(function(data) {
			console.log(data);
		});
	}

	/* network details */
	$scope.getNumberOfSensors = function() {
		DataService.getNoOfSensors().success(function(data) {
			$scope.noOfSensors = data;
		}).error(function(data) {
			console.log(data);
		});
	}
	$scope.getTypesOfSensors = function() {
		DataService.getTypesOfSensors().success(function(data) {
			$scope.typesOfSensors = data;
		}).error(function(data) {
			console.log(data);
		});
	}

	$scope.getNetworkDetails = function() {
		DataService.getNetworkDetailsService().success(function(data) {
			$scope.networkDetailObject = data;
		}).error(function(data) {
			console.log(data);
		});
	}

	/* charts */
	$scope.temperatureLineChart = function(stats) {
		$("#temperatureChart").kendoChart({
			title : {
				text : "Temperature"
			},
			dataSource : {
				data : stats
			},
			series : [ {
				type : "line",
				aggregate : "avg",
				field : "value",
				categoryField : "key"
			} ],
			categoryAxis : {
				baseUnit : "hours",
				majorGridLines : {
					visible : false
				},
			},
			tooltip : {
				visible : true,
				format : "{0}",
				template : "#= value #"
			}
		});
	};

	$scope.humidityLineChart = function(stats) {

		$("#humidityChart").kendoChart({
			title : {
				text : "Humidity"
			},
			dataSource : {
				data : stats
			},
			series : [ {
				type : "line",
				aggregate : "avg",
				field : "value",
				categoryField : "key"
			} ],
			categoryAxis : {
				baseUnit : "hours",
				majorGridLines : {
					visible : false
				},
			},
			tooltip : {
				visible : true,
				format : "{0}",
				template : "#= value #"
			}
		});
	}

	/* Gauges */

	$scope.createTemperatureGauge = function(meterValue) {

		$("#temperatureGauge").kendoRadialGauge({

			pointer : {
				value : meterValue
			},

			scale : {
				minorUnit : 1,
				startAngle : -30,
				endAngle : 210,
				max : 50,
				labels : {
					position : "inside"
				},
				ranges : [ {
					from : 30,
					to : 35,
					color : "#ffc700"
				}, {
					from : 35,
					to : 40,
					color : "#ff7a00"
				}, {
					from : 40,
					to : 50,
					color : "#c20000"
				} ]
			}
		});
	}

	$scope.createHumidityGauge = function(meterValue) {

		$("#humidityGauge").kendoRadialGauge({

			pointer : {
				value : meterValue
			},

			scale : {
				minorUnit : 1,
				startAngle : -30,
				endAngle : 210,
				max : 100,
				labels : {
					position : "inside"
				},
				ranges : [ /*
							 * { from : .2, to : .8, color : "#ffc700" },
							 */{
					from : 0,
					to : .2,
					color : "#ff7a00"
				}, {
					from : .8,
					to : 1,
					color : "#c20000"
				} ]
			}
		});
	}

	$scope.createMoistureGauge = function(meterValue) {

		$("#moistureGauge").kendoRadialGauge({

			pointer : {
				value : meterValue
			},

			scale : {
				minorUnit : .1,
				startAngle : -30,
				endAngle : 210,
				max : 1,
				labels : {
					position : "inside"
				},
				ranges : [ /*
							 * { from : .2, to : .8, color : "#ffc700" },
							 */{
					from : 0,
					to : .2,
					color : "#ff7a00"
				}, {
					from : .8,
					to : 1,
					color : "#c20000"
				} ]
			}
		});
	}

};

DataController.$inject = injectParams;
angular.module('controllersModule', []).controller('DataController',
		DataController);