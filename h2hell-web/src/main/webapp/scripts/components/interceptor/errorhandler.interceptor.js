'use strict';

angular.module('h2hellwebApp')
    .factory('errorHandlerInterceptor', function ($q, $rootScope) {
        return {
            'responseError': function (response) {
                if (!(response.status == 401 && response.data.path.indexOf("/api/account") == 0 )){
	                $rootScope.$emit('h2hellwebApp.httpError', response);
	            }
                return $q.reject(response);
            }
        };
    });