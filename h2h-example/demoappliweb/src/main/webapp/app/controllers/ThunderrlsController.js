'use strict';

/**
 * ThunderrlsController
 *
 * @constructor
 */
var ThunderrlsController = function($scope, $routeParams, $http) {
    $scope.text = "";
    $("#menu li").removeClass("active");
    $("#menu_vizu").addClass("inactive");
    $("#menu_performance").addClass("inactive");
    $("#menu_rls").addClass("active");
    $("#menu_configuration").addClass("inactive");
    $("#menu_settings").addClass("inactive");
};
