angular.module('glisseAngular.productservices',['ngResource']).factory('productResource',['$resource','$http',function($resource,$http){
    return {
        getProduct : function(prodID) {return $http({
                url : '/Shop-war/webresources/product/getProduct',
                method: "GET",
                params : { prodID : prodID }
            });
        }
    }
}]);