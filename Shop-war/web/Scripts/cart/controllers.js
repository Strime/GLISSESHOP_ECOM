angular.module('glisseAngular.cartcontroller',[]).controller('CartController',['$scope','cartResource','$routeParams','$cookies','$cookieStore','orderResource','accountResource',function($scope,cartResource,$routeParams,$cookies,$cookieStore,orderResource,accountResource){
    $scope.state = 'cart';
    $scope.adress = '';
    $scope.postcode = '';
    $scope.city = ''; 
    $scope.payment = '';
    
    $scope.compte = accountResource;
    $scope.acc = $cookieStore.get('idSession');
    $scope.cart = cartResource;
    for(var v = 0; v < $scope.cart.items.length; v++)
    {
        var item = $scope.cart.items[v];
        var product = item.product;
        item.caracs = [];
        for(var j = 0; j < product.TypeCaracs.length; j++)
        {
            var type = product.TypeCaracs[j];
            var newType = {Label : type.Label, Value : ''};
            for(var value = 0; value < type.Values.length; value++)
            {
                for(var ref = 0; ref < type.Values[value].References.length; ref++)
                {
                    if(type.Values[value].References[ref] === item.id)
                   newType.Value = type.Values[value].Value;
                }
               
            }
            item.caracs.push(newType);
        
        }
    }

    $scope.orderResource = orderResource;
    
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
    
    $scope.cart.isEmpty = function() {
        return ($cookieStore.get('cartShop').items.length < 1);
    };
    
    $scope.emptyCart = $scope.cart.isEmpty();

    $scope.cart.clearCart = function() {
        $cookieStore.put('cartShop',{items : []});
        $scope.cart.items = [];
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
        var c = $cookieStore.get('idSession');
        var items = [];
        for(var i = 0; i < $scope.cart.items.length; i++)
        {
            var item = $scope.cart.items[i];
            items.push({idRef : item.id, Count : item.count});
        }
        
        $scope.orderResource.confirmOrder(c.Hash,c.Mail,JSON.stringify(items)).then(function(data) { 
                        $scope.confirmOrder = data.data;
                        $scope.cart.clearCart();
        });
        
    };
    
}]);