angular.module('glisseAngular.cartservices',['ngResource']).factory('cartResource',['$resource','$http','$cookies','$cookieStore',function($resource,$http,$cookies,$cookieStore){
    
        var cart = $cookieStore.get('cartShop');
        if(!cart)
            $cookieStore.put('cartShop',{items : []});
        return $cookieStore.get('cartShop');
        
}]);