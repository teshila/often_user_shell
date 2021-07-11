<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">股东信息维护</h3>
		</div>
		<div class="panel-body">
			<ul class="list-group" id="lists">
				<li class="list-group-item" v-for="account in list">
				<div class="container-fluid">
						<div class="row clearfix">
							<div class="col-md-4 column">股东账号：【{{account.account}}】</div>
							<div class="col-md-4 column">名称：【{{account.name}}】</div>
							<div class="col-md-4 column">账户可用金额：【{{account.keYongZiJing}}】</div>
							<div class="col-md-4 column">
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label for="code" class="col-sm-2 control-label">登录凭证</label>
										<div class="col-sm-10">
											<input type="text" class="form-control"   autofocus="autofocus">
											<a class="btn btn-default" @click="doSubmit(account.account)">保存</a>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'HelloWorld',
		data() {
			return {
				list: [],
				gudong:"",
			}
		},
		methods: {
			initMenu: function() {
				var url = this.baseURL.apisafe+ '/gudongList.do?rand='+Math.random();
				var params = new URLSearchParams();
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url,params).then((response) => {
					 this.list=response.data.list;
				}).catch((response) => {
					console.log(response);
				})
			},
			doSubmit:function(acc){
				var url = this.baseURL.apisafe+ '/updateGudongInfo.do?rand='+Math.random();
				var params = new URLSearchParams();
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url,params).then((response) => {
					console.log(response.data)
				}).catch((response) => {
					console.log(response);
				})
				
			}
		},
		created: function() {
			this.initMenu();
		}

	}
	//https://www.cnblogs.com/posthxl/p/8126601.html
	//https://blog.csdn.net/dahuzix/article/details/56009454
	//https://www.cnblogs.com/guomin/p/9073042.html
	//https://segmentfault.com/q/1010000006890647
</script>

<style scoped="scoped">
	/*@import url("../../../static/bt/css/bootstrap.min.css");*/
</style>