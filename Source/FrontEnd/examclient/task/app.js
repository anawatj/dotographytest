var app = angular.module('exam',['ngRoute']);
app.controller('taskListCtrl',function($scope,$http)
{
		$scope.results=[];
		$scope.init=function()
		{
				$http.get('http://localhost:8484/exam/task/tasks')
				.success(function(results)
				{
							$scope.results=results;
				});
		};
		$scope.edit=function(id)
		{
				window.location="./entry.html?id="+id;
		};
		$scope.remove=function()
		{
				var items = [];
				for(var index=0;index<$scope.results.length;index++)
				{
					var item = $scope.results[index];
					if(item.selected==true)
					{
						delete item.selected;
						items.push(item);
					}


				}
				$http.post('http://localhost:8484/exam/task/removeBatch',items)
				.success(function(ret)
				{
						alert(ret.message);
						location.reload();
				});
		};
		$scope.changeStatus=function(status)
		{
				var items=[];
				for(var index=0;index<$scope.results.length;index++)
				{
						var item = $scope.results[index];
						if(item.selected==undefined)
						{
							item.selected=false;

						}
						if(item.selected)
						{
								item.status=status;
								delete item.selected;
								items.push(item);
						}
				}
				$http.post('http://localhost:8484/exam/task/saveBatch',items)
				.success(function(ret)
				{
						alert(ret.message);
						location.reload();
				});
		};
});
app.controller('taskEntryCtrl',function($scope,$http,$routeParams)
{
		$scope.model={};
		$scope.init=function()
		{
				$http.get('http://localhost:8484/exam/task/tasks/'+$params.id)
				.success(function(ret)
				{
						$scope.model=ret;

				});
		};
		$scope.save=function()
		{
				for(var index=0;index<$scope.model.subtasks.length;index++)
				{
					var item = $scope.model.subtasks[index];
					delete item.selected;
				}
				$http.post('http://localhost:8484/exam/task/save',$scope.model)
				.success(function(ret)
				{
						alert(ret.message);
						location.reload();
				});
		};
		$scope.remove=function()
		{
				$http.post('http://localhost:8484/exam/task/remove',$scope.model)
				.success(function(ret)
				{
						alert(ret.message);
						location.reload();
				});
		};
	   $scope.changeStatus=function()
		{
				$http.post('http://localhost:8484/exam/task/save',$scope.model)
				.success(function(ret)
				{
						alert(ret.message);
						location.reload();
				});
		};
		$scope.addItem=function()
		{
				var item = {};
				item.id=0;
				item.selected=false;
				item.parentId=undefined;
				$scope.model.subtasks.push(item);
		};
		$scope.removeItem=function()
		{
				for(var index=0;index<$scope.model.subtasks.length;index++)
				{
					var item = $scope.model.subtasks[index];
					if(item.selected==true)
					{
						$scope.model.subtasks.splice(index,1);
						index--;
					}
				}
		};
});
