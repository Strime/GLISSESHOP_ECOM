angular.module('glisseAngular.orderservice',['ngResource']).factory('orderResource',['$resource','$http',function($resource,$http){
        return {
        confirmOrder : function(hash,mail,idRef) {
                return $http({
                url : '/Shop-war/webresources/product/confirmOrder',
                method: "POST",
                params : {hash : hash, mail : mail, idRef : idRef}
            });
        }
    };
}]);