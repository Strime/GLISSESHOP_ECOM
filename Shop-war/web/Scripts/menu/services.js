angular.module('glisseAngular.menuservices',['ngResource']).factory('menuResource',['$resource','$http',function($resource,$http){
    return {
        getAllFamilies : function() {return $http({
                url : '/Shop-war/webresources/family/getAll',
                method: "GET",
            });
        }
        /*plop : function(_id) {return $http({
                url : '/Shop-war/webresources/rest/getJson',
                method: "POST",
                params : {id: _id}
            });
        }*/
    
    }
}]);