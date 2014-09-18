/**
 * Created by Sandeep on 01/06/14.
 */
angular

angular.module('glisseAngular.searchcontroller',[]).controller('SearchController',['$scope','$rootScope','$location',function($scope,$rootScope,$location){
    $scope.word = "";
    $scope.refreshSearch = function() {
        $rootScope.$broadcast("refresh", {keyword: $scope.word });
        $location.path('/');
    }
}]).directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});