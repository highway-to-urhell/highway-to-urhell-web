 'use strict';

angular.module('h2hellwebApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-h2hellwebApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-h2hellwebApp-params')});
                }
                return response;
            }
        };
    });
