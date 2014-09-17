angular.module('glisseAngular.signinservices',['ngResource']).factory('signInResource',['$resource','$http',function($resource,$http){
    return {
        getAccount : function(mail,password) {return $http({
                url : '/Shop-war/webresources/account/getAccount',
                method: "GET",
                params : {mail : mail, password : password}
            });
        } 
    };
}]);