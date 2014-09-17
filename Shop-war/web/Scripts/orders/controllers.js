angular.module('glisseAngular.oldordercontroller',[]).controller('OldOrderController',['$scope','$routeParams','$cookies','$cookieStore',function($scope,$routeParams,$cookies,$cookieStore){
    $scope.account = $cookieStore.get('idSession');

}]);