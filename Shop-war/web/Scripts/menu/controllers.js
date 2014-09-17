/**
 * Created by Sandeep on 01/06/14.
 */
angular.module('glisseAngular.menucontroller',[]).controller('MenuController',['$scope','menuResource','accountResource',function($scope,menuResource,accountResource){
    menuResource.getAllFamilies().then(function(data) {    
        $scope.families = data.data;
    });
    
    $scope.isLogged = function() {
        return accountResource.getSession() !== null;
    };
    
}]);