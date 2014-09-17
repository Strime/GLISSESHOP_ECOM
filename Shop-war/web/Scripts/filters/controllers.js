/**
 * Created by Sandeep on 01/06/14.
 */
angular.module('glisseAngular.filterscontroller',[]).controller('FiltersController',['$scope','$rootScope','filtersResource','$routeParams',function($scope,$rootScope,filtersResource,$routeParams){
    var famID = $routeParams.id;
    if(famID) {
        $scope.loading=true;
        filtersResource.getFamily(famID).then(function(data) {    
            var family = data.data;
            $scope.filters = [];
            
            for(var i = 0; i < family.TypeCaracs.length; i++) {
                var valueslist = [];
                for(var j = 0; j < family.TypeCaracs[i].Values.length; j++)
                {
                    valueslist.push({text : family.TypeCaracs[i].Values[j].Value, selected : false});
                }
                $scope.filters.push({name : family.TypeCaracs[i].Label, values : valueslist, id : family.TypeCaracs[i].id})
            }
            $scope.loading=false;
        });
    }
    else
    {
        
    }
    
    $scope.refreshFilters = function() {
        var compactFilters = {possibleValues : [], typeCaracs : []};
        for(var i = 0; i < $scope.filters.length; i++)
        {
            var used = false;
            var type = $scope.filters[i];
            
            var values = [];
            for(var j = 0; j < type.values.length; j++) {
                if(type.values[j].selected) {
                compactFilters.possibleValues.push(type.values[j].text);
                used = true;
                }
            }
            if(used)
                compactFilters.typeCaracs.push(type.id);
            //compactFilters.items.push({type : type.id, values : values});
        }
        $rootScope.$broadcast("refresh", {filters: compactFilters });
    }
    
}]);