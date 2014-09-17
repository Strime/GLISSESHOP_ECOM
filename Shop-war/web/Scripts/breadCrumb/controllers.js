angular.module('glisseAngular.breadcrumbcontroller',[])
        .controller('BreadCrumbController',
            ['$scope','breadCrumbResource','$routeParams','$location','$rootScope',
                function($scope,breadCrumbResource,$routeParams,$location,$rootScope){
    
    $rootScope.$on('$routeChangeSuccess', function(e, current, pre) {
        $scope.loc = $location.path().split('/');
        if($scope.loc[1] === "family") {
            var famID = $routeParams.id;
            if(famID) {
                breadCrumbResource.getFamily(famID).then(function(data) {    
                    $scope.family = data.data;
                });
            }
        } else if($scope.loc[1] === "product") {
            var prodID = $routeParams.id;
            if(prodID) {
                breadCrumbResource.getProduct(prodID).then(function(data) {    
                    $scope.product = data.data;
                });
            }
        }
        
    });
    
}]);