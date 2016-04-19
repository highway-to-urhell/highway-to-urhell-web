'use strict';

/**
 * LaunchAnalysisController.js
 * 
 * @constructor
 */
var LaunchAnalysisController = function($scope, $routeParams, $http) {
	$scope.findByToken = function() {
		var tokenTarget = $routeParams.token;
		$http.post('api/ThunderApp/findAllPaths/' + tokenTarget).success(function(message) {
			$scope.ms = message;
			$http.post('api/ThunderStat/findThunderStatByTokenAndFilter/' + tokenTarget)
				.success(function(message) {
					$scope.ms = message;
				});
		});
	};
	$scope.launchAnalysis = function(token) {
		if($scope.ms.analysis){
			$scope.messageConfig = 'Analysis Running is already active ! ';
		}else {
			$scope.messageConfig = 'Analysis Running for application ';
			var sendData ={
				'token' : token,
				'listTs': $scope.ms.listThunderStat
			};
			$http.post('api/ThunderApp/launchAnalysis/',sendData).success(function (message) {
				$scope.messageConfig = message;
			});
		}
	};

	$scope.set_color = function(falsePositive, count) {
		// alert(falsePositive);
		if (falsePositive == true) {
			return {
				color : "green"
			};
		}
	};

	$scope.findByToken();
	
	 console.log("Changing stat of menu");
    $("#menu li").removeClass("active");
    $("#menu_app").addClass("active");
    
};