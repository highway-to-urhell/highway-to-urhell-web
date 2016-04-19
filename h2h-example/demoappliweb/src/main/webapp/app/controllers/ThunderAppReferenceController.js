'use strict';

/**
 * ThunderAppReferenceController.js
 * @constructor
 */
var ThunderAppReferenceController = function($scope,$routeParams, $http) {
	$scope.fetchAppList = function() {
        $http.get('api/ThunderApp/findAllThunderApp').success(function(appzList){
            $scope.appReferenceList = appzList;
        });
    };

    $scope.fetchAppList();
  
    $("#menu li").removeClass("active");

    $("#menu_vizu").addClass("active");
    $("#menu_performance").addClass("inactive");
    $("#menu_rls").addClass("inactive");
    $("#menu_configuration").addClass("inactive");
    $("#menu_settings").addClass("inactive");
  
};


