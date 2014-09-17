angular.module('glisseAngular.accountcontroller',[]).controller('AccountController',['$scope','accountResource','$routeParams','$cookies','$cookieStore',function($scope,accountResource,$routeParams,$cookies,$cookieStore){


    
    $scope.isLogin = function() {
        var isLog = $cookieStore.get('idSession');
        return isLog;
    };
    
    $scope.getSession = function() {
        return $cookieStore.get('idSession');;
    };
    
    accountResource.createSession = function(account) {
        $cookieStore.put('idSession',account);
    };
    
    $scope.logout = function() {
        $cookieStore.remove('idSession');
    };
    
    
}]);