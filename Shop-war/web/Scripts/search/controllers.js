/**
 * Created by Sandeep on 01/06/14.
 */
angular.module('glisseAngular.searchcontroller',[]).controller('SearchController',['$scope','$rootScope',function($scope,$rootScope){
    $scope.word = "";
    $scope.refreshSearch = function() {
        $rootScope.$broadcast("refresh", {keyword: $scope.word });
    }
}]);