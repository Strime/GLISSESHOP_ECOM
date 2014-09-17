angular.module('glisseAngular.signincontroller',[]).controller('signInController',['$scope','signInResource','$routeParams','accountResource','$location',function($scope,signInResource,$routeParams,accountResource,$location){
    $scope.mail = "";
    $scope.pwd = "";
    
    $scope.signIn = function() {
        signInResource.getAccount($scope.mail,$scope.pwd).then(function(data) { 
            $scope.account = data.data;
            if(!$scope.account.error){
                accountResource.createSession($scope.account);
                $location.path("/account");
            }
        })  
    };
    
    $scope.loginSuccess = function() {
        $scope.cart.addItem($scope.possibleRefs[0],$scope.countRefs,$scope.product);
    }
}]);