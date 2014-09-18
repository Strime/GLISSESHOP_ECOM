angular.module('glisseAngular.oldordercontroller',[]).controller('OldOrderController',['$scope','$routeParams','$cookies','$cookieStore',function($scope,$routeParams,$cookies,$cookieStore){
        $scope.account = $cookieStore.get('idSession');
        
        
        $scope.isEmpty = function () {
            return ($scope.account.Orders === null);
        };
        $scope.emptyOrders = $scope.isEmpty();
        debugger;
}]);