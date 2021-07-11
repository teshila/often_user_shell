
angular.module("app",["ionic"])

.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

		$stateProvider

			 .state('index', {
                url: '/index',
                templateUrl: 'index.html',
                controller: 'indexCtrl'
            })

			.state('login', {
				url: '/login',
				templateUrl: 'login.html',
				controller: 'loginCtrl'
			})
			
        $urlRouterProvider.otherwise('/index')   
			

	}])

.service('jianshu', ['$http','$q',function($http,$q){
    //http://www.jianshu.com/mobile/trending/now?page=1&count=10

    this.getList = function(number,str){
        str = str || '';
        var defer = $q.defer();

        fetch('http://www.jianshu.com/mobile/trending/now?page='+number+'&count=10'+ str).then((res) => res.json()).then((res) => defer.resolve(res)).catch( (err) => defer.reject(err));

        return defer.promise;
    }
    
    this.getData=function(obj){
    	
    	var defer = $q.defer();
    	
    	$http.post('http://www.jianshu.com/mobile/sessions',obj)
                .then( (res) => console.log(res.data), (err) => alert(err.data.error[0].message))
    }
 
}])

.controller("indexCtrl",["$scope","jianshu",function($scope,jianshu){	
	
	var number = 1;
    jianshu.getList(number).then( (res) => $scope.list = res , (err) => alert('请求出错'));
	
	//加载更多
    $scope.click = function(){
        var obj = '';
        angular.forEach($scope.list,function(item,key){
            obj += '&note_ids[]='+item.id
        })
        ++number;
        jianshu.getList(number,obj).then( (res) => $scope.list=$scope.list.concat(res) , (err) => alert('请求出错'));

        $scope.$broadcast('scroll.refreshComplete');
    }
	
	
	
	
	$scope.login=function(){
		console.log(1)
		window.location.href="#/login"
	}

}])


.controller("loginCtrl",["$scope","jianshu",function($scope,jianshu){
	
	 $scope.user = {};

                jianshu.getData().then( (res) =>{
                	console.log(res);
                	$scope.user.getCode = res;
                } )

                $scope.refreash = function(){
                   // alert(1);
                    jianshu.getData.then( (res) => $scope.user.getCode = res);
                    
                }


//              $scope.submit = function(){
//                  var obj = {
//                      captcha:{
//                          id: $scope.user.getCode.id,
//                          code: $scope.user.yzm
//                      },
//                      password: $scope.user.psd,
//                      sign_in_id: $scope.user.name
//
//
//                  }
//                  yzm.submitNews(obj)
//              }
	
	
	
}])
