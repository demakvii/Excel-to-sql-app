var app = angular.module('myMain', ['ngMaterial','ngMessages']);
 
app.controller('myMainController', ['$scope',function($scope){
	/*$scope.myName="Mayur";*/
	$scope.isBtnDisabled = true;

	$scope.inputfilePopup = function () {
			$('#inputfileLoader').trigger("click");
	};

	$scope.outputfilePopup = function () {
			$('#outputfileLoader').trigger("click");
	};
}]);


