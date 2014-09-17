angular.module('glisseAngular.registercontroller',[]).controller('registerController',['$scope','registerResource','$routeParams',function($scope,registerResource,$routeParams){
    $scope.mail = "";
    $scope.name = "";
    $scope.surname = "";
    $scope.adress = "";
    $scope.pwd = "";
    $scope.pwd2 = "";
    $scope.register = function() {
        registerResource.setAccount($scope.name,$scope.surname,$scope.adress,$scope.mail,$scope.pwd,$scope.pwd2)
                .then(function(data) { 
                        $scope.account = data.data;
                        debugger;
        })  
    };
}]);