/**
 * Created by Sandeep on 01/06/14.
 */
angular.module('glisseAngular.productscontroller',[]).controller('ProductsController',['$scope','$rootScope','productsResource','$routeParams',function($scope,$rootScope,productsResource,$routeParams){

    var famID = $routeParams.id;
    
    if(famID) {
        $scope.loading=true;
        productsResource.getAllProductsOfFamily(famID).then(function(data) {    
            $scope.products = data.data;
            $scope.loading=false;
        });
    }
    else
    {
        $scope.loading=true;
        productsResource.getAllProducts().then(function(data) {    
            $scope.products = data.data;
            $scope.loading=false;
        });
    }
    $rootScope.$on("refresh", function (event, args) {
        if(args.filters) {
            if(args.filters.possibleValues.length > 0) {
                    $scope.loading=true;
                    productsResource.getAllProductsOfFamilyFiltered(famID,args.filters.possibleValues,args.filters.typeCaracs).then(function(data) {    
                    $scope.products = data.data;
                    $scope.loading = false;
                });
            } 
        }
        else
        {
            $scope.loading=true;
            productsResource.getAllProductsByKeyword(args.keyword).then(function(data) {    
                    $scope.products = data.data;
                    $scope.loading = false;
                });
        }
    });
}]);