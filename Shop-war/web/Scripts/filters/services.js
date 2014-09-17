angular.module('glisseAngular.filtersservices',['ngResource']).factory('filtersResource',['$resource','$http',function($resource,$http){
    return {
        getFamily : function(famID) {return $http({
                url : '/Shop-war/webresources/family/getFamily',
                method: "GET",
                params : { famID : famID }
            });
        }
    }
}]);