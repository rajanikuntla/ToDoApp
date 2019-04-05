app.controller('loginController', loginController);

function loginController($http, $scope, $location, $rootScope){
	
	$scope.login = login;
	
	function login(){
		
		var credentials = {userName : $scope.username, password : $scope.password};
		$http.post('/login', credentials).then(function (response) { 
			if(response.status ==  200){
				console.log(response)
				$rootScope.loggedIn = true;
				$rootScope.userId = response.data;
				$location.path("/todoList");
			} else {
				$scope.message ="Invalid UserName or Password";
			}
			
		});
	};
};

app.controller('registerController', registerController);

function registerController($http, $scope, $location) {
	
	$scope.register = register;
	
	function register() {
		var credentials = {userName : $scope.username, password : $scope.password};
		$http.post('/register', credentials).then(function (data) { 
			if(data.status == 201){
				$scope.message ="Successfully Registered";
				$location.path("/login");
			} else if(data.status == 226) {
				$scope.message ="UserName already exists";
			}
		});
		
	};
};


var compareTo = function($parse) {
    return {
    	require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
};
 
app.directive("compareTo", compareTo);


app.controller('todoListController', todoListController);

function todoListController($scope, $rootScope, $http, $location){
	var userId =$rootScope.userId;
	$http.get('/todoList/'+userId).then(function (response) {
		$scope.todoList = response.data;
		
	});
	
	
	$scope.deleteToDo = function(id){
		$http.post('/deleteToDo/'+id).then(function (response) { 
			$http.get('/todoList/'+userId).then(function (response) {
				$scope.todoList = response.data;
				
			});
		});
	};
	
	$scope.editToDo = function(id) {
		$rootScope.id =id;
		$location.path("/editToDo/"+$rootScope.id);
	};
	
};

app.controller('addToDoController', addToDoController);

function addToDoController($scope, $http, $rootScope, $location){
	$scope.showInput = false;
	$scope.toDoList = [];
	$scope.addWorkItem = function(){
		$scope.showInput = true;
	}
	
	$scope.addToDo = function(){
		
	 var obj = {};
	 obj.name = $scope.name;
	 obj.workItems = [];
	 
	 for(var i=0;i<$scope.toDoList.length;i++){
		 var dummyObj = {};
		 dummyObj.workItemName = $scope.toDoList[i];
		 obj.workItems.push(dummyObj);
	 }
	 userId = $rootScope.userId;
	 $http.post('/addToDo/'+userId, obj).then(function (response) { 
		 $location.path("/todoList");
	 });
	 
	}
	
	$scope.saveWorkItem = function(){
		$scope.toDoList.push($scope.workItem);
		$scope.workItem = '';
		$scope.showInput = false;
	}
	
	
	
};

app.controller('editToDoController', editToDoController);

function editToDoController($http, $scope, $rootScope, $location){
	
	$scope.showInput = false;
	$scope.toDoList = [];
	
	$http.get('/editToDo/'+ $rootScope.id).then(function (response) {
		$scope.todo = response.data;
	});
	
	$scope.addWorkItem = function(){
		$scope.showInput = true;
	}
	
	$scope.editToDo = function(){
		 
		 userId = $rootScope.userId;
		 console.log($scope.todo.workItems)
		 $http.patch('/editToDo', $scope.todo).then(function (response) { 
			 $location.path("/todoList");
		 });
		 
		}
		
		$scope.saveWorkItem = function(){
			var obj ={};
			obj.workItemName = $scope.workItem;
			$scope.todo.workItems.push(obj);
			$scope.workItem = '';
			$scope.showInput = false;
		}
}

app.controller('logoutController', logoutController);

function logoutController($rootScope, $location){
	$rootScope.loggedIn = false;
	$rootScope.userId='';
	$rootScope.id='';
	$location.path('/login');
	
}

