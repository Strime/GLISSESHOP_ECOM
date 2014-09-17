angular.module('glisseAngular.registerservices',['ngResource']).factory('registerResource',['$resource','$http',function($resource,$http){
    return {
        setAccount : function(name,surname,adress,mail,password) {return $http({
                url : '/Shop-war/webresources/account/setAccount',
                method: "POST",
                params : {name:name, surname:surname, adress:adress, mail : mail, password : password}
            });
        } 
    };
}]);