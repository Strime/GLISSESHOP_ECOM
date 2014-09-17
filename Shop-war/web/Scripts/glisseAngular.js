angular.module('glisseAngular',
    ['ngRoute',
     'ngCookies',
        'glisseAngular.menucontroller','glisseAngular.menuservices',
        'glisseAngular.productscontroller','glisseAngular.productsservices',
        'glisseAngular.productcontroller','glisseAngular.productservices',
        'glisseAngular.breadcrumbcontroller','glisseAngular.breadcrumbservices',
        'glisseAngular.cartcontroller','glisseAngular.cartservices',
        'glisseAngular.accountcontroller','glisseAngular.accountservices',
        'glisseAngular.signincontroller','glisseAngular.signinservices',
        'glisseAngular.registercontroller','glisseAngular.registerservices',
        'glisseAngular.filterscontroller','glisseAngular.filtersservices',
        'glisseAngular.oldordercontroller', 'glisseAngular.orderservice'
        'glisseAngular.searchcontroller'
    ]).config(function ($routeProvider) {
        //configure the routing rules here
        $routeProvider.when('/product/:id', {
            templateUrl: 'Views/product.html',
            controller: 'ProductController'
            
        }).when('/family/:id', {
            templateUrl: 'Views/products.html',
            controller: 'ProductsController'
            
        }).when('/cart', {
            templateUrl: 'Views/cart.html',
            controller: 'CartController'
            
        }).when('/account', {
            templateUrl: 'Views/account.html',
            controller: 'AccountController'
            
        }).when('/signIn', {
            templateUrl: 'Views/signIn.html',
            controller: 'signInController'
            
        }).when('/register', {
            templateUrl: 'Views/register.html',
            controller: 'registerController'
            
        })/*.otherwise({
            templateUrl: 'Views/products.html',
            controller: 'ProductsController'
      })*/;
    
        
});
