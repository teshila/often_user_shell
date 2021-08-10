<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">{{title}}算法(交日易收盘15点得出当前列表，共 {{totalCount}} 条)</h3>
		</div>
		<div class="panel-body">
			<div class="contents" ref="foodwrapper">
				<ul class="list-group">
					<li class="list-group-item" v-for="(sts,index) in goods">
						<div class="container-fluid">
							<div class="row clearfix">
								<div class="col-md-4 column">代码：【{{sts.code}}】</div>
								<div class="col-md-4 column">名称：【{{sts.name}}】</div>
								<div class="col-md-4 column">本日收盘价：【{{sts.closePrice}}】 (
									<span @click="myQuery(sts.code,sts.name,index)" :class="{'isBuy':sts.isAddToBuy>0}">{{sts.isAddToBuy>0?'移出买单':'加入买单'}}</span>)
									【<span @click="getKDayLine(sts.code,sts.name)">日线</span>】
									【<span @click="getKWeekLine(sts.code,sts.name)">周线</span>】
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div v-show="isShow" class="dialogs animated" :class="isZomm>0?'zoomIn':''">
				<!-- <chart :code="code" :name="name"  v-on:listenChildEvent="showChildMsg"></chart> -->
				<img :src="dataURL" width="100%" height="100%" /><span class="myClose" @click="closeMyWindows">关闭</span>
			</div>
			<div>
				<ul class="pagination">
					<li v-for="index in indexs" :class="{ 'active': page == index}"><a @click="_loadData(index)">{{index}}</a></li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script>
	import chart from '../charts.vue';
	import { MessageBox } from 'mint-ui';
	import { Toast } from 'mint-ui';
	export default {
		props: ['url', 'title'],
		components: {
			chart
		},
		data() {
			return {
				msg: "",
				goods: [],
				scrollY: 0,
				total: 0,
				page: 1,
				totalCount: 0,
				code: "",
				name: "",
				isShow: false,
				dataURL: "",
				isZomm:""
			}
		},

		computed: {
			indexs: function() {
				let arr = [];
				let left = 1;
				let right = this.total;
				while (left <= right) {
					arr.push(left)
					left++
				}
				return arr;
			}

		},

		methods: {
			_loadData: function(step) {
				this.page = step;
				if(step>0){
					this.isShow = false;
				}
				var url = this.baseURL.apidomain + "/" + this.url + '?rand=' + Math.random();
				this.$ajax.get(url, {
					params: { //请求参数
						pageNum: step
					}
				}).then((response) => {
					this.goods = response.data.pageList;
					this.total = response.data.totalPages;
					this.totalCount = response.data.totalCount;
				}).catch((response) => {
					console.log(response);
				})
			},

			addBuy: function(stockCode, name, index) {

				var url = this.baseURL.apisafe + '/editBuy.do?rand=' + Math.random();
				var params = new URLSearchParams();
				params.append('code', stockCode);
				params.append('name', name);
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url, params).then((response) => {
					this.msg = response.data.msg;
					if (this.msg != "用户未登录") {
						this.goods[index].isAddToBuy = "1";
					} else {
						Toast(this.msg);
					}
				}).catch((response) => {
					console.log(response);
				})
			},
			removeBuy: function(code, index) {
				var url = this.baseURL.apisafe + '/delBuy.do?rand=' + Math.random();
				var params = new URLSearchParams();
				params.append('code', code);
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url, params).then((response) => {
					this.msg = response.data.msg;
					if (this.msg != "用户未登录") {
						this.goods[index].isAddToBuy = "0";
					} else {
						Toast(this.msg);
					}

				}).catch((response) => {
					console.log(response);
				})
			},

			myQuery: function(code, name, index) {
				MessageBox.confirm('确定要操作吗?').then(action => {
					var isBuy = this.goods[index].isAddToBuy;
					if (isBuy > 0) {
						this.removeBuy(code, index);
					} else {
						this.addBuy(code, name, index);
					}
				},()=>{
				});
			},

			getKDayLine: function(code, name) {
				
				this.isZomm=1;
				this.name = name;
				this.code = code;
				this.isShow = true;
				if(code.indexOf("6")==0){
					code = "sh"+code;
				}else{
					code = "sz"+code;
				}
				this.dataURL = "http://image.sinajs.cn/newchart/daily/n/"+code+".gif"
			},
			
			getKWeekLine: function(code, name) {
				
				this.isZomm=1;
				this.name = name;
				this.code = code;
				this.isShow = true;
				if(code.indexOf("6")==0){
					code = "sh"+code;
				}else{
					code = "sz"+code;
				}
				this.dataURL = "http://image.sinajs.cn/newchart/weekly/n/"+code+".gif"
			},
			
			
			
			showChildMsg: function(data) {
				this.isShow = false;
			},
			
			closeMyWindows:function(){
				this.isShow =  false;
			}
		},
		created: function() {
			this._loadData(0);
		}

	}
</script>

<style scoped="scoped">
	.blue {
		color: red;
	}

	.isBuy {
		color: red;
	}

	.contents {
		height: 250px;
		overflow-y: scroll;
	}

	.dialogs {
		position: absolute;
		z-index: 9999;
		height: 44vh;
		width: 56vh;
		top: 47px;
		background: #e6e5e0;
		left: 30px;
	}

	@media screen and (min-width:960px) {
		.dialogs {
			left: 38%;
		}
	}

	.myClose {
		position: absolute;
		left: 0px;
		bottom: 0;
		background: #987979;
		padding: 0 4px;
		color: #000;
	}
</style>

//https://blog.csdn.net/qq_14863671/article/details/54412254
//https://blog.csdn.net/inuyasha1121/article/details/50777116
