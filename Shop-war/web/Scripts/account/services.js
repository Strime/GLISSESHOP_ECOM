angular.module('glisseAngular.accountservices',['ngResource']).factory('accountResource',['$resource','$cookies','$cookieStore','$http',function($resource,$http,$cookies,$cookieStore){
    return {
        getAccount : function(mail,password) {return $http({
                url : '/Shop-war/webresources/account/getAccount',
                method: "GET",
                params : {mail : mail, password : password}
            });
        }
    };
}]);

