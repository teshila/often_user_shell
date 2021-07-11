angular.module("app", ["ionic"])

	.config(['$stateProvider', '$urlRouterProvider', '$ionicConfigProvider', function($stateProvider, $urlRouterProvider, $ionicConfigProvider) {

		$ionicConfigProvider.platform.android.tabs.position("bottom");
		$ionicConfigProvider.tabs.style('standard');
		$ionicConfigProvider.navBar.alignTitle('center');

		$stateProvider
			//tab选项卡的路由
			.state('tab', {
				url: '/tab',
				abstract: true,
				templateUrl: 'tab.html'
			})
			//首页的路由
			.state('tab.index', {
				url: '/index',
				cache: false,
				views: {
					"home": {
						templateUrl: 'home.html',
						controller: 'homeCtrl'
					}
				}

			})
			//新闻页的路由
			.state('tab.news', {
				url: '/news',
				cache: false,
				views: {
					"news": {
						templateUrl: 'news.html',
						controller: 'newsCtrl'
					}
				}

			})
			//新闻详情页的路由
			.state('tab.news1', {
				url: '/news1/:id',
				cache: false,
				views: {
					"news": {
						templateUrl: 'news1.html',
						controller: 'news1Ctrl'
					}
				}

			})
			//社区页的路由
			.state('tab.commticy', {
				url: '/commticy',
				cache: false,
				views: {
					"commticy": {
						templateUrl: 'commticy.html',
						controller: 'commticyCtrl'
					}
				}

			})
			//社区详情页的路由
			.state('tab.commticy1', {
				url: '/commticy1/:id',
				cache: false,
				views: {
					"commticy": {
						templateUrl: 'commticy1.html',
						controller: 'commticy1Ctrl'
					}
				}

			})
			//设置页的路由
			.state('tab.setting', {
				url: '/setting',
				views: {
					"setting": {
						templateUrl: 'setting.html',
						controller: 'settingCtrl'
					}
				}

			})
		//设置默认页面
		$urlRouterProvider.otherwise('/tab/index')

	}])
    //新闻的数据请求
	.service("common", ["$http", "$q", function($http, $q) {
		//获取文章分类的
		this.getData = function() {
			var defer = $q.defer();
			$http.get("http://www.phonegap100.com/appapi.php?", {
					params: {
						a: 'getPortalCate'
					}
				})
				.then(function(res) {
					//console.log(res.data.result);
					defer.resolve(res.data.result);
				}, function(err) {
					defer.reject(err)
				})

			return defer.promise;
		}
		//获取文章列表的
		this.getList = function(card, number) {
			var defer = $q.defer();
			$http.get('http://www.phonegap100.com/appapi.php?', {
					params: {
						a: 'getPortalList',
						catid: card,
						page: number
					}
				})
				.then((res) => defer.resolve(res.data.result));
			return defer.promise;
		}
		//获取文章详情的
		this.detailList = function(num) {
			var defer = $q.defer();
			$http.get('http://www.phonegap100.com/appapi.php?', {
					params: {
						a: 'getPortalArticle',
						aid: num
					}
				})
				.then((res) => defer.resolve(res.data.result));
			return defer.promise;
		}
	}])
	
	//帖子相关的数据请求
	.service("tie",["$http","$q",function($http,$q){
			//获取帖子分类的
		this.artData = function() {
			var defer = $q.defer();
			$http.get('http://www.phonegap100.com/appapi.php?', {
					params: {
						a: 'getThreadCate'
					}
				})
				.then((res) => defer.resolve(res.data.result));
			return defer.promise;
		}
		//获取帖子列表的
		this.artList = function(fid, pag) {
			var defer = $q.defer();
			$http.get('http://www.phonegap100.com/appapi.php?', {
					params: {
						a: 'getThreadList',
						fid: fid,
						page: pag
					}
				})
				.then((res) => defer.resolve(res.data.result));
			return defer.promise;
		}
		//获取帖子详情的
		this.detailData = function(num) {
			var defer = $q.defer();
			$http.get('http://www.phonegap100.com/appapi.php?', {
					params: {
						a: 'getThreadContent',
						tid: num
					}
				})
				.then((res) => defer.resolve(res.data.result));
			return defer.promise;
		}
	}])
	
	
	//首页的控制器
	.controller('homeCtrl', ['$scope', function($scope) {
		$scope.cur = 0;
		$scope.Change = function(index) {
			// console.log(index)                       
			$scope.cur = index;
		};

	}])
	//设置页面的控制器
	.controller('settingCtrl', ['$scope', function($scope) {

	}])
	//新闻列表页的控制器
	.controller('newsCtrl', ['$scope', "common",'$ionicLoading', function($scope, common,$ionicLoading) {
		
		$ionicLoading.show({
			template: '程序员拼命撸代码....'
		});

		var catid = 0;
		var pageNumber = 0;

		$scope.ifLoad = false;
		$scope.listData = [];

		//调用获取文章分类的函数
		common.getData().then((res) => {
			$scope.list = res;
			//页面第一次进入的时候，我要拿到第一个分类的catId
			catid = res[0].catid;
			$scope.ifLoad = true;
		});

		//分类点击事件
		$scope.click = function(catId) {
			catid = catId;
			$scope.ifLoad = true;
			pageNumber = 1;
			// console.log(catId);
			// 当我们去点击分类的时候，要加载的数据就是第一页的数据
			common.getList(catId, pageNumber).then((res) => $scope.listData = res)
		}

		//上拉加载所要执行的函数
		$scope.more = function() {

			++pageNumber;

			common.getList(catid, pageNumber).then((res) => {
				res.length ? $scope.listData = $scope.listData.concat(res) : $scope.ifLoad = false;
				$scope.$broadcast('scroll.infiniteScrollComplete');
				$ionicLoading.hide();
			});
		}
	}])
	//新闻详情页的控制器
	.controller('news1Ctrl', ['$scope', "common", '$stateParams', function($scope, common, $stateParams) {
		//console.log($stateParams.id)
		$scope.arr = [];
		common.detailList($stateParams.id).then(function(res) {
			console.log(res);
			$scope.arr = res[0];
		})
	}])
	//社区页的控制器
	.controller('commticyCtrl', ['$scope', "tie","$ionicLoading",function($scope, tie,$ionicLoading) {
		
		$ionicLoading.show({
			template:"正在加载中..."
		})
        
        $scope.ifLoad=false;
        var pageNumber=0;
        var fid=0;
        $scope.arrData=[];

		tie.artData().then(function(res) {
			//console.log(res)
			$scope.data = res[0].subcate;
			fid=res[0].subcate[0].fid;
			$scope.ifLoad=true;
		})

        $scope.change = function(fid){
        	fid = fid;
            pageNumber = 1;
        	$scope.ifLoad = true;        	
        	tie.artList(fid,pageNumber).then((res) => $scope.arrData = res)       	       	
        }

        $scope.more = function(){
        	++pageNumber;
        	tie.artList(fid,pageNumber).then((res) => {
        		res.length ? $scope.arrData = $scope.arrData.concat(res) : $scope.ifLoad = false;
        		$scope.$broadcast("scroll.infiniteScrollComplete");
        		$ionicLoading.hide();
        	})
        	
        }
	}])
	//社区详情页的控制器
	.controller('commticy1Ctrl', ['$scope', "tie", "$stateParams", function($scope, tie, $stateParams) {
		//console.log($stateParams.id)
		$scope.liData = [];
		tie.detailData($stateParams.id).then(function(res) {
			//console.log(res)
			$scope.liData = res;
		})

	}])