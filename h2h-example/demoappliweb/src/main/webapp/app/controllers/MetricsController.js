'use strict';

/**
 * MetricsController.js
 * @constructor
 */
var MetricsController = function($scope, $routeParams, $http) {

    $scope.findByToken = function(token) {
        $http.post('api/MetricsLog/findMetricsByToken/'+token).success(function(metricsListResult){
            $scope.metricsList = metricsListResult;
        });
    };

    $scope.popupParameters = function(itemsParameters){
        console.log(itemsParameters);
    };

    $scope.updateToken = function() {
        $scope.messageConfig = 'Update ...';
        var tokenTarget = $routeParams.token;
        $http.post('api/MetricsLog/findMetricsByToken/'+tokenTarget).success(function(metricsListResult){
                $scope.metricsList = metricsListResult;
                $scope.messageConfig = null;
            });
    };

    $scope.launchAnalysis = function(token) {
        if($scope.metricsList){
            $scope.messageConfig = 'Analysis Running is already active ! ';
        }else {
            console.log('gni');
            $scope.messageConfig = 'Analysis Running for application ';
            var dataFilter ={
                'token' : token,
                'filter': 'false',
                'packageOnly' : 'false',
                'classOnly' : 'false',
                'classMethod' : 'false',
                'listFilter' : []
            };
            $http.post('api/ThunderApp/launchAnalysis/',dataFilter).success(function (message) {
                $scope.messageConfig = message;
            });
        }
    };

    $scope.findByToken($routeParams.token);

};