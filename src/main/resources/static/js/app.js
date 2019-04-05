var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/login',{
            templateUrl: '/views/login.html',
            controller: 'loginController'
        })
        .when('/register',{
            templateUrl: '/views/register.html',
            controller: 'registerController'
        })
        .when('/todoList',{
            templateUrl: '/views/todoList.html',
            controller: 'todoListController'
        })
        .when('/addToDo',{
            templateUrl: '/views/addToDo.html',
            controller: 'addToDoController'
        })
        .when('/editToDo/:id',{
            templateUrl: '/views/editToDo.html',
            controller: 'editToDoController'
        })
        .when('/logout',{
        	 templateUrl: '/views/login.html',
            controller: 'logoutController'
        })

        .otherwise(
            { redirectTo: '/'}
        );
});