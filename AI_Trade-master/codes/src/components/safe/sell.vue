<template>
	<div class="wrap">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">卖编辑</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group">
					<li class="list-group-item">
						<div class="container">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="code" class="col-sm-2 control-label">代码</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" autofocus="autofocus" v-model="inputValue" placeholder="请输入代码" />
												<!-- <query code='code'></query> -->
											</div>
										</div>

										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<a class="btn btn-default" @click="addStock">保存</a>
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
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">当前卖单列表</h3>
			</div>
			<div class="panel-body">
				<ul class="list-group" id="lists">
					<li class="list-group-item" v-for="(sts,index) in list">
						<div class="container-fluid">
							<div class="row clearfix">
								<div class="col-md-2 column">代码：【 {{sts.code }}】</div>
								<div class="col-md-2 column">名称：【{{sts.name }} 】</div>
								<div class="col-md-2 column">本日卖价：【{{ sts.weiTuoTodaySellPrice }} 】</div>
								<div class="col-md-2 column">委托买入价：【{{ sts.weiTuoBuyPrice }} 】</div>
								<div class="col-md-4 column">操作：【
									<a href="javascript:;" @click="deleteBuy(sts.code,index)">删除</a>】</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

</template>

<script>
	import query from './query.vue'
	import {Toast} from 'mint-ui';
	import { MessageBox } from 'mint-ui';
	export default {
		data() {
			return {
				list: [],
				findlist: [],
				inputValue: "",

			}
		},
		components: {
			query
		},

		methods: {
			inital: function() {
				var url = this.baseURL.apisafe + '/sellList.do?rand=' + Math.random();
				var token = this.cookie.getCookie("token");
				var params = new URLSearchParams();
				params.append('token', token);
				this.$ajax.post(url, params).then((response) => {
					this.list = response.data.pageList;
				}).catch((response) => {
					console.log(response);
				})
			},
			deleteBuy: function(code, index) {
				MessageBox.confirm('确定执行此操作?').then(action => {
					var url = this.baseURL.apisafe + '/delSell.do?rand=' + Math.random();
					var token = this.cookie.getCookie("token");
					var params = new URLSearchParams();
					params.append('token', token);
					params.append('code', code);
					this.$ajax.post(url, params).then((response) => {
						this.list.splice(index, 1);
					}).catch((response) => {
						console.log(response);
					})
				},()=>{
					
				});
								
				
			},
			addStock: function() {
				let stockCode = this.inputValue;
				var url = this.baseURL.apisafe + '/editSell.do?rand=' + Math.random();
				var params = new URLSearchParams();
				params.append('code', stockCode);
				params.append('name', name);
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url, params).then((response) => {
					this.msg = response.data.msg;
					Toast(this.msg);
					this.inital();
				}).catch((response) => {
					console.log(response);
				})
			}

		},
		created: function() {
			this.inital();
		}
	}
</script>

<style>

</style>
<!--https://www.jianshu.com/p/99278065d1df 和Watch监听-->

<!--https://www.cnblogs.com/daiwenru/p/6694530.html-->
<!--http://www.vogin.top/view/48-->