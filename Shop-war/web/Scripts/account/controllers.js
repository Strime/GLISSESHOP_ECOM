angular.module('glisseAngular.accountcontroller',[]).controller('AccountController',['$scope','accountResource','$routeParams','$cookies','$cookieStore','$location',function($scope,accountResource,$routeParams,$cookies,$cookieStore,$location){

    $scope.account = $cookieStore.get('idSession'); 
    
    $scope.isLogin = function() {
        return !($scope.account === undefined);
    };
    
    $scope.getSession = function() {
        return $cookieStore.get('idSession');
    };
    
    accountResource.createSession = function(account) {
       $cookieStore.put('idSession',account);
       $scope.account = $cookieStore.get('idSession');
    };
    
    $scope.logout = function() {
        $cookieStore.remove('idSession');
        $scope.account = $cookieStore.get('idSession');
    };
    
    
}]);
