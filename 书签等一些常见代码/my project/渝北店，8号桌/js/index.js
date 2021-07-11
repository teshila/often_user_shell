angular.module("app", ["ngRoute"])

	.factory("shopCart", function() {
		return [];
	})

	.config(["$routeProvider", function($routeProvider) {

		$routeProvider

			.when("/index", {

				templateUrl: "index",
				controller: "firstCtrl"

			})
			.when("/dinner", {

				templateUrl: "dinner",
				controller: "secondCtrl"
			})

			.otherwise('/index')

	}])

	.controller("firstCtrl", ["$scope", "$http", "shopCart", function($scope, $http, shopCart) {

		$scope.list = [];
		$scope.arr = [];
		$scope.current = 0;
		$scope.totalNumber = 0;
		$scope.show = function($index) {
			$scope.current = $index;
			//console.log($index)
			$scope.arr = $scope.list[$index].items;

		}

		$scope.goCart = function() {

			window.location.href = "#/dinner";
		}

		$http.get("json/groups.json").then(function(res) {
			//console.log(res);
			angular.forEach(res.data, function(item, key) {
				//console.log(item);
				//item表示最外层的对象
				//item.items表示最外层里面的items这个数组
				angular.forEach(item.items, function(j, key) {
					//console.log(j)
					//j表示菜品对象
					j.number = 0;

					if(shopCart.length) {
                        console.log(shopCart)
						angular.forEach(shopCart, function(i, k) {

							//console.log(i.number);

							if(j.id == i.id) {

								item.items[key] = shopCart[k];

								//计算商品数量
								$scope.totalNumber += i.number;

							}

						})

					}

				})

			})

			$scope.list = res.data;
			$scope.arr = res.data[0].items;

		})

		//点击加法
		$scope.add = function(index) {
			//console.log(index)
			//当前菜品的数量
			++$scope.arr[index].number;
			//总的数量
			++$scope.totalNumber;

			var flag = false;
			//循环购物车里面的每一条数据，判断要添加的数据，是否在数组中

			angular.forEach(shopCart, function(item, key) {
				//console.log(item)
				if(item.id == $scope.arr[index].id) {
					flag = true;
				}

			})

			if(!flag) {
				shopCart.push($scope.arr[index]);
				//console.log(shopCart)
			}

		}
		//点击减法
		$scope.mius = function(index) {
			//当前菜品的数量
			--$scope.arr[index].number;
			//总的数量
			--$scope.totalNumber;
		}

	}])

	.controller("secondCtrl", ["$scope", "shopCart", function($scope, shopCart) {

		$scope.back = function() {
			window.location.href = "#/index"
		}

		$scope.cart = shopCart;
		$scope.totalNumber = 0;

		angular.forEach(shopCart, function(item, index) {

			$scope.totalNumber += item.number;

			$scope.add = function(index) {

				++$scope.cart[index].number;

				//总数加
				++$scope.totalNumber;

			}

			//点击减法
			$scope.mius = function(index) {
				--$scope.cart[index].number;

				//总数加
				--$scope.totalNumber;
			}

		})

	}])