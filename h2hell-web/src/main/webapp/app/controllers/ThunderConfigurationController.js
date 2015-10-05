'use strict';

/**
 * ThunderConfigurationController
 *
 * @constructor
 */
var ThunderConfigurationController = function($scope, $routeParams, $http) {
    $scope.text = "";
    $("#menu li").removeClass("active");
    $("#menu_vizu").addClass("inactive");
    $("#menu_performance").addClass("inactive");
    $("#menu_rls").addClass("inactive");
    $("#menu_configuration").addClass("active");
    $("#menu_settings").addClass("inactive");
};
