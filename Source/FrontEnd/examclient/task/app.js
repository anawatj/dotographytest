var app = angular.module('exam',['ngRoute']);
app.factory('params',function()
	{
		var url =document.URL;
		var arr = url.split('?');
		if(arr.length==2)
		{
			var params= arr[1].split('&');
			var ret = {};
			for(var index=0;index<params.length;index++)
			{
				var key = params[index].split('=')[0];
				var value = params[index].split('=')[1];
				ret[key]=value;
			}
			return ret;
		}else
		{
			return {};
		}

	});
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
app.controller('taskEntryCtrl',function($scope,$http, params)
{
		$scope.model={};
		$scope.init=function()
		{
				var id= params.id;
				if(id==undefined)
				{
					id=0;
				}
				
				$http.get('http://localhost:8484/exam/task/tasks/'+ id)
				.success(function(ret)
				{
						$scope.model=ret;
						if($scope.model.assigner==null || $scope.model.assigner==undefined)
						{
							$scope.model.assigner={};
						}
						if($scope.model.assignee==null || $scope.model.assignee==undefined)
						{
							$scope.model.assignee={};
						}
						$scope.model.taskDate = new Date($scope.model.taskDate);
						$scope.model.assignDate = new Date($scope.model.assignDate);
						$scope.model.finishDate= new Date($scope.model.finishDate);
						if($scope.model.items ==null || $scope.model.items==undefined)
						{
							$scope.model.items=[];
						}
						for(var index=0;index<$scope.model.items.length;index++)
						{
							var item = $scope.model.items[index];
							item..selected=false;
						}

				});
		};
		$scope.save=function()
		{
				for(var index=0;index<$scope.model.items.length;index++)
				{
					var item = $scope.model.items[index];
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
				item.taskId=undefined;
				$scope.model.items.push(item);
		};
		$scope.removeItem=function()
		{
				for(var index=0;index<$scope.model.items.length;index++)
				{
					var item = $scope.model.items[index];
					if(item.selected==true)
					{
						$scope.model.items.splice(index,1);
						index--;
					}
				}
		};
});
