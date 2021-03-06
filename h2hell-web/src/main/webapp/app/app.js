'use strict';

var AngularSpringApp = {};

var App = angular.module('thunderWebApp', []);

// Declare app level module which depends on filters, and services
App.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/thunderstat/:token', {
        templateUrl: 'app/views/thunderstat.html',
        controller: 'ThunderStatController'
    });
    $routeProvider.when('/breaker/:token', {
        templateUrl: 'app/views/breaker.html',
        controller: 'BreakerController'
    });
    $routeProvider.when('/thundersource/:token/:pathClassMethodName', {
        templateUrl: 'app/views/thundersource.html',
        controller: 'ThundersourceController'
    });
    $routeProvider.when('/metrics/:token', {
        templateUrl: 'app/views/metrics.html',
        controller: 'MetricsController'
    });
    $routeProvider.when('/metricsView/:token', {
        templateUrl: 'app/views/metricsview.html',
        controller: 'MetricsViewController'
    });
    $routeProvider.when('/adminh2h', {
        templateUrl: 'app/views/adminh2h.html',
        controller: 'ThunderAdminController'
    });
    $routeProvider.when('/apps', {
        templateUrl: 'app/views/app.html',
        controller: 'ThunderAppReferenceController'
    });
    $routeProvider.when('/performance', {
        templateUrl: 'app/views/performance.html',
        controller: 'ThunderPerformanceController'
    });
    $routeProvider.when('/rls', {
        templateUrl: 'app/views/rls.html',
        controller: 'ThunderrlsController'
    });
    $routeProvider.when('/configuration', {
        templateUrl: 'app/views/configuration.html',
        controller: 'ThunderConfigurationController'
    });
    $routeProvider.when('/home', {
        templateUrl: 'app/views/home.html',
        controller: 'ThunderHomeReferenceController'
    });
    $routeProvider.when('/bq', {
        templateUrl: 'app/views/bq.html',
        controller: 'BqController'
    });
    $routeProvider.when('/plugins', {
        templateUrl: 'app/views/plugins.html',
        controller: 'PluginsController'
    });
    $routeProvider.when('/launchAnalysis/:token', {
        templateUrl: 'app/views/launchanalysis.html',
        controller: 'LaunchAnalysisController'
    })
    $routeProvider.otherwise({redirectTo: '/home'});
}]);

cheet('↑ ↑ ↓ ↓ ← → ← →', function () {
	  window.location = "#/bq"
	});
