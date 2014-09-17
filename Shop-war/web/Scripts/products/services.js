angular.module('glisseAngular.productsservices',['ngResource']).factory('productsResource',['$resource','$http',function($resource,$http){
    return {
        getAllProducts : function() {return $http({
                url : '/Shop-war/webresources/product/getAll',
                method: "GET",
            });
        },
        getAllProductsOfFamily : function(famID) {return $http({
                url : '/Shop-war/webresources/product/getAllOfFamily',
                method: "GET",
                params : { famID : famID }
            });
        },
        getAllProductsOfFamilyFiltered : function(famID,possVal,typeCarac) {return $http({
                url : '/Shop-war/webresources/product/getAllOfFamilyFiltered',
                method: "GET",
                params : { famID : famID, possibleValues : possVal, typeCaracs : typeCarac }
            });
        }
    }
}]);