'use strict';

angular.module('h2hellwebApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


