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

    $scope.findByToken($routeParams.token);

};