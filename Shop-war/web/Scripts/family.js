
angular.module('glisseAngular.familycontroller',[]).controller('FamilyController',['$scope','$location',function($scope,$location){
        var famID = ($location.search()).famID;
    if(famID) {
        productsResource.getAllProductsOfFamily(famID).then(function(data) {    
            $scope.products = data.data;
        });
    }
    else
    {
        productsResource.getAllProducts().then(function(data) {    
            $scope.products = data.data;
        });
    }
}]);