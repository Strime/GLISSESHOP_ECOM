angular.module('glisseAngular.breadcrumbservices',['ngResource']).factory('breadCrumbResource',['$resource','$http',function($resource,$http){
    return {
        getFamily : function(famID) {return $http({
                url : '/Shop-war/webresources/family/getFamily',
                method: "GET",
                params : {famID : famID}
            });
        },
        getProduct : function(prodID) {return $http({
                url : '/Shop-war/webresources/product/getProduct',
                method: "GET",
                params : { prodID : prodID }
            });
        }
    };
}]);