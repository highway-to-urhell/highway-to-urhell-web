
'use strict';

/**
 * ThunderPerformanceController.jsmenu_settings
 * @constructor
 */
var ThunderPerformanceController = function($scope,$routeParams, $http) {
    $scope.fetchAppList = function() {
        $http.get('api/ThunderApp/findAllThunderApp').success(function(appzList){
            $scope.appReferenceList = appzList;
        });
    };

    $scope.fetchAppList();

    $("#menu li").removeClass("active");
    $("#menu_vizu").addClass("inactive");
    $("#menu_performance").addClass("active");
    $("#menu_rls").addClass("inactive");
    $("#menu_configuration").addClass("inactive");
    $("#menu_settings").addClass("inactive");

};



