'use strict';

/**
 * ThunderStatController.js
 * 
 * @constructor
 */
var ThunderStatController = function($scope, $routeParams, $http) {
	$scope.findByToken = function() {
		var tokenTarget = $routeParams.token;
		$http.post('api/ThunderStat/findThunderStatByTokenAndFilter/' + tokenTarget)
				.success(function(message) {
					$scope.ms = message;
				});
	};
	$scope.findAllPaths = function (token){
		$scope.messageConfig='Find All Paths for application ';
		$http.post('api/ThunderApp/findAllPaths/' + token).success(function(message) {
				$scope.ms = message;
				$scope.messageConfig = '';
				$http.post('api/ThunderStat/findThunderStatByTokenAndFilter/' + token)
					.success(function(message) {
						$scope.ms = message;
					});
			});
	};
	$scope.updateThunderStatFalsePositive = function(id, status, methodInput) {
		$scope.messageConfig = 'Update status ...';
		var data = id + '/' + status;
		$http.post('api/ThunderStat/updateThunderStatFalsePositive/' + data)
				.success(
						function(tokenListResult) {
							$scope.tokenlist = tokenListResult;
							$scope.findByToken();
							$scope.messageConfig = 'Status updated for '
									+ methodInput + ' !';
						});

	};

	$scope.popupParameters = function(itemsParameters){
		console.log(itemsParameters);
	};

	$scope.set_color = function(falsePositive,count,drawAnalysis) {
		if(drawAnalysis == false){
			return {
				color: "gray"
			};
		}else {
			if (falsePositive == true) {
				return {
					color: "green"
				};
			}
		}

	};
	$scope.updateToken = function() {
		$scope.messageConfig = 'Update ...';
		var tokenTarget = $routeParams.token;
		$http.post('api/ThunderStat/findThunderStatByTokenAndFilter/' + tokenTarget)
				.success(function(message) {
					$scope.ms = message;
					$scope.messageConfig = null;
				});
	};
	$scope.findByToken();
	
	 console.log("Changing stat of menu");
    $("#menu li").removeClass("active");
    $("#menu_app").addClass("active");
    
};