'use strict';

/**
 * MetricsViewController.js
 * @constructor
 */
var internal ;

var MetricsViewController = function($scope, $routeParams, $http) {
    initData($scope,$routeParams);
    drawDynamic('init');
    /*$http.post('api/ThunderApp/findThunderAppByToken/' + $routeParams.token).success(function (thResult) {
            $scope.th = thResult;
            $http.post('api/MetricsLog/findMetricsInit/' + $routeParams.token).success(function (messageMetricsData) {
                dynamicLoadData(messageMetricsData, $http, $routeParams, $scope);
            });
    });*/
    $scope.launchAnalysis = function(token) {
        if($scope.th.analysis){
            $scope.messageConfig = 'Analysis Running is already active ! ';
        }else {
            $scope.messageConfig = 'Analysis Running for application ';
            $http.post('api/ThunderApp/launchAnalysis/' + token).success(function (message) {
                $scope.messageConfig = message;
            });
        }
    };
    $scope.reloadDynamicData = function(){
        var chart = $('#dynamic').highcharts();
        //remove data
        while(chart.series.length > 0) {
            chart.series[0].remove();
        }
        drawDynamic('init');
        $http.post('api/ThunderApp/findThunderAppByToken/' + $routeParams.token).success(function (thResult) {
            $scope.th = thResult;
            $http.post('api/MetricsLog/findMetricsInit/' + $routeParams.token).success(function (messageMetricsData) {
                dynamicLoadData(messageMetricsData, $http, $routeParams, $scope);
            });
        });
    };
    $scope.applyFilter = function(){
        //stop the interval
        clearInterval(internal);
        //send data

        $http.post('api/MetricsLog/findMetrics/',$scope.filterModel).success(function (messageMetricsDataResult) {
            var chart = $('#dynamic').highcharts();
            //remove data
            while(chart.series.length > 0) {
                chart.series[0].remove();
            }
            var chartRedraw= drawDynamic('none');
            //add data
            for(var i = 0; i<messageMetricsDataResult.listMetrics.length; i++){
                var x = messageMetricsDataResult.listMetrics[i].dateIncoming;
                var y = messageMetricsDataResult.listMetrics[i].timeExec;
                var cm = messageMetricsDataResult.listMetrics[i].pathClassMethodName+'<br/>'+
                    'Proc:'+messageMetricsDataResult.listMetrics[i].cpuLoadProcess+'%'+
                    'Sys:'+messageMetricsDataResult.listMetrics[i].cpuLoadSystem+'%';
                console.log('cm '+cm +' add x : '+x +' y '+y);
                chartRedraw.series[0].addPoint({x : x ,y : y, total : cm}, true, false);
            }
            chartRedraw.redraw();
        });
    };

}

function initData($scope,$routeParams){
    $scope.filterModel = {};
    $scope.filterModel.token = $routeParams.token;
    $scope.filterModel.responseTime = 2000;
    $scope.filterModel.nbItems = 100;

}


function dynamicLoadData(messageMetricsData, $http,$routeParams,$scope){
    $scope.rc = messageMetricsData.lastInc;
    internal = setInterval(function () {
            if($scope.rc)
            var chart = $('#dynamic').highcharts();
            $http.post('api/MetricsLog/findMetricsFromLastInc/'+ $routeParams.token+'/'+$scope.rc).success(function(messageMetricsDataResult) {
                //clean the first value
                for(var i = 0; i<messageMetricsDataResult.listMetrics.length; i++){
                    var x = messageMetricsDataResult.listMetrics[i].dateIncoming;
                    var y = messageMetricsDataResult.listMetrics[i].timeExec;
                    var cm = messageMetricsDataResult.listMetrics[i].pathClassMethodName+'<br/>'+
                        'Proc:'+messageMetricsDataResult.listMetrics[i].cpuLoadProcess+'%'+
                        'Sys:'+messageMetricsDataResult.listMetrics[i].cpuLoadSystem+'%';
                    console.log('cm '+cm +' add x : '+x +' y '+y);
                    chart.series[0].addPoint({x : x ,y : y, total : cm}, true, false);
                }
                chart.redraw();
                $scope.rc=messageMetricsDataResult.lastInc;
            });
        }
        , 20000);
}


function call(){
    alert('here');
}

function drawDynamic(arg){
    if(arg === 'init') {
        var tabPush = [];
        tabPush.push({x: (new Date()).getTime(), y: 10, total: 'init'});
    }
    $('#dynamic').highcharts({
        chart: {
            type: 'scatter',
            zoomType: 'xy'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Response time'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            scatter: {
                marker: {
                    radius: 5,
                    states: {
                        hover: {
                            enabled: true,
                            lineColor: 'rgb(100,100,100)'
                        }
                    }
                },
                states: {
                    hover: {
                        marker: {
                            enabled: false
                        }
                    }
                },
                tooltip: {
                    headerFormat: '<b>{point.total}</b><br>'
                }
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.total + '</b><br/>' +
                    Highcharts.dateFormat('%d-%m-%Y %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 0)+'ms'+'<a class=\"btn\" ng-click=\"call()\" >';
            }
        },
        series: [{
            name: 'Data',
            color: 'rgba(223, 83, 83, .5)',
            data: tabPush
        }]
    });
    return $('#dynamic').highcharts();
}

