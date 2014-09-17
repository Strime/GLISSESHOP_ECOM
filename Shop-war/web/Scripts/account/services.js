angular.module('glisseAngular.accountservices',['ngResource']).factory('accountResource',['$resource','$cookies','$cookieStore','$http',function($resource,$http,$cookies,$cookieStore){
        return {
        getAccount : function(mail,password) {return $http({
                url : '/Shop-war/webresources/account/getAccount',
                method: "GET",
                params : {mail : mail, password : password}
            });
        },
        

        isLogin : function() {
            var isLog = $cookieStore.get('idSession');
            return isLog;
        },
    
        getSession : function() {
            return $cookieStore.get('idSession');;
        },
    
        createSession : function(account) {
            $cookieStore.put('idSession',account);
        },
    
        logout : function() {
            $cookieStore.remove('idSession');
        }
    };
}]);

