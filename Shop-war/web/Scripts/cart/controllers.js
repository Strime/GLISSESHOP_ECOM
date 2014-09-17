angular.module('glisseAngular.cartcontroller',[]).controller('CartController',['$scope','cartResource','$routeParams','$cookies','$cookieStore',function($scope,cartResource,$routeParams,$cookies,$cookieStore){
    $scope.state = 'cart';
    $scope.adress = '';
    $scope.postcode = '';
    $scope.city = ''; 
    $scope.payment = '';
    
    $scope.cart = cartResource;
    $scope.cart.addItem = function(refID,count,product) {
        var found = false;
        for(var i = 0; i < $scope.cart.items.length; i++)
        {
            if($scope.cart.items[i].id == refID) {
                found = true;
                $scope.cart.items[i].count += count;
            }
        }
        if(!found)
            $scope.cart.items.push({id : refID, count : count, product : product});
    
        $cookieStore.put('cartShop',$scope.cart);
    };
    $scope.cart.removeItem = function(refID) {
        for(var i = 0; i < $scope.cart.items.length; i++)
        {
            if($scope.cart.items[i].id == refID) {
                $scope.cart.items.splice(i,1);
                $cookieStore.put('cartShop',$scope.cart);
                return;
            }
        }
    };
    
    $scope.getTotal = function() {
        var total = 0;
        for(var i = 0; i < $scope.cart.items.length; i++)
        {
            var itm = $scope.cart.items[i];
            total += (itm.product.Price * itm.count);
        }
        return total;  
    };
    
    $scope.confirmCart = function() {
                        debugger;
        
        $scope.cart.confirmOrder($scope.cart.items).then(function(data) { 
                        debugger;
                        $scope.account = data.data;
        });
        $scope.cart.items = [];
        $cookieStore.put('cartShop',$scope.cart);
    };
    
}]);