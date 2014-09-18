angular.module('glisseAngular.registercontroller',[]).controller('registerController',['$scope','registerResource','$routeParams',function($scope,registerResource,$routeParams){
    $scope.mail = "";
    $scope.name = "";
    $scope.surname = "";
    $scope.adress = "";
    $scope.pwd = "";
    $scope.pwd2 = "";
    $scope.isValid = function() {
        var res = ($scope.pwd===$scope.pwd2 && $scope.pwd.length > 0 && $scope.name.length > 0 && $scope.surname.length > 0 && $scope.adress.length > 0 && $scope.mail.length > 0);
        
        if(!res) {
            if(!($scope.pwd===$scope.pwd2)) {
                $scope.errorTest = "Les mots de passe ne correspondent pas";
            }
            else
            {
                $scope.errorTest = "Des champs requis sont vides";
            }
            
        }
        else
            $scope.errorTest = undefined;
        
        
        if(!res)
            return "disabled";
        else
            return null;
    }
    $scope.register = function() {
        registerResource.setAccount($scope.name,$scope.surname,$scope.adress,$scope.mail,$scope.pwd,$scope.pwd2)
                .then(function(data) { 
                        $scope.account = data.data;
        })  
    };
}]);