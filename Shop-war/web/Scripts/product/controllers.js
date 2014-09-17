/**
 * Created by Sandeep on 01/06/14.
 */
angular.module('glisseAngular.productcontroller',[]).controller('ProductController',['$scope','productResource','$routeParams','cartResource',function($scope,productResource,$routeParams,cartResource){
    var prodID = $routeParams.id;
    $scope.loading=true;
    $scope.onstart = true;
    $scope.possibleRefs = [];
    $scope.countRefs = 1;
    currentMediaIndex = 0;
    $scope.cart = cartResource;
    animating = false;
    
    productResource.getProduct(prodID).then(function(data) {    
        $scope.product = data.data;
        $scope.currentMedia = $scope.product.Medias[currentMediaIndex];
        $scope.loading=false;
        updateArrows();
    });
    
    resetAllExcept = function(item) {
        for(var j = 0; j < $scope.product.TypeCaracs.length; j++)
        {
            if($scope.product.TypeCaracs[j] !== item) {
                for(var i = 0; i < $scope.product.TypeCaracs[j].Values.length; i++)
                    $scope.product.TypeCaracs[j].Values[i].selected = null;
                
            }
        }
    }
    
    MrGaetanCasseCouille = function() {
        for(var j = 0; j < $scope.product.TypeCaracs.length; j++)
        {
                for(var i = 0; i < $scope.product.TypeCaracs[j].Values.length; i++)
                    if($scope.possibleRefs.length == 1 && $scope.testAvailable($scope.product.TypeCaracs[j].Values[i]))
                        $scope.product.TypeCaracs[j].Values[i].selected = "checked";
                
        }
    }
    
    $scope.changeCarac = function(carac,value) {
        
        for(var i = 0; i < carac.Values.length; i++)
            carac.Values[i].selected = null;
        
        value.selected = "checked";
        
        newPossible = [];

        for(var i = 0; i < value.References.length; i++) {
            if($scope.possibleRefs.indexOf(value.References[i]) >= 0)
                newPossible.push(value.References[i]);
        }
        
        if(newPossible.length == 0)
        {
            newPossible = value.References;
            resetAllExcept(carac);
        }
        $scope.possibleRefs = newPossible;
        $scope.onstart = false;
        
        MrGaetanCasseCouille();
        
    }
    
    $scope.testAvailable = function(value) {
        for(var i = 0; i < value.References.length; i++) {
            if($scope.possibleRefs.indexOf(value.References[i]) >= 0)
                return true;
        }
        return false;
    }
    
    $scope.changeMedia = function(mediaId) {
        if(animating)
            return;
        animating = true;
        var goal = currentMediaIndex - mediaId;
        currentMediaIndex = mediaId;
        
        var cl = '.media-'+mediaId;
        $('.product-item').animate({
            width: "50px",
            height : "50"
            }, { duration: 500, queue: false });
        
        $(cl).animate({
            width: "75px",
            height : "75px"
            },{ duration: 500, queue: false });
            
        $( ".product-media-big" ).animate({
            left: "+="+goal*250,
            },{ duration: 500, queue: false, complete : function() {
                animating = false;
            } });
        updateArrows();
    }
    
    updateArrows = function() {
        if(currentMediaIndex+1 > $scope.product.Medias.length-1)
        {
            $(".rightArrow").hide();
        }
        else
            $(".rightArrow").show();
        
         if(currentMediaIndex-1 < 0) {
             $(".leftArrow").hide();
         }
         else
             $(".leftArrow").show();
    }
    
    $scope.goRight = function() {
        if(animating)
            return;
        $scope.changeMedia(currentMediaIndex-1);
        updateArrows();
        
    }
    $scope.goLeft = function() {
        if(animating)
            return;
       $scope.changeMedia(currentMediaIndex+1);
        updateArrows();
    }
    
    $scope.addToCart = function() {
        $scope.cart.addItem($scope.possibleRefs[0],$scope.countRefs,$scope.product);
    }
}]);