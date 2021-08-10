<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">自动化涨算法列表【系统在交易日每天17点自动更新】</h3>
		</div>
		<div class="panel-body">
			<div class="wrap" ref="foodwrapper">
				<ul class="list-group">
					<li class="list-group-item" v-for="(sts,index) in goods">
						<div class="container-fluid">
							<div class="row clearfix">
								<div class="col-md-4 column">代码：【{{sts.code}}】</div>
								<div class="col-md-4 column">名称：【{{sts.name}}】</div>
								<div class="col-md-4 column">本日收盘价：【{{sts.closePrice}}】 (
									<span @click="onClick(sts.code,index)" :class="{'isBuy':sts.isAddToBuy>0}">{{sts.isAddToBuy>0?'移出买单':'加入买单'}}</span>)
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<ul class="pagination pagination-lg">
				<li v-if="cur>1">
					<a v-on:click="cur--,pageClick()">&laquo;</a>
				</li>
				<li v-if="cur==1">
					<a class="banclick">&laquo;</a>
				</li>

				<li v-for="index in indexs" v-bind:class="{ 'active': cur == index}">
					<a v-on:click="btnClick(index)">{{ index }}</a>
				</li>

				<li v-if="cur!=all">
					<a v-on:click="cur++,pageClick()">&raquo;</a>
				</li>
				<li v-if="cur == all">
					<a class="banclick">&raquo;</a>
				</li>
				 <li><a>共<i>{{all}}</i>页</a></li>
			</ul>

		</div>
	</div>
</template>

<script>
	//https://www.cnblogs.com/grove009/p/8000391.html
	//https://www.jianshu.com/p/640e4209d2c8
	//https://segmentfault.com/q/1010000013829012
	import BScroll from 'better-scroll';
	export default {
		data() {
			return {
				msg: "",
				goods: [],
				scrollY: 0,
				all: 0, //总页数
				cur: 1 //当前页码

			}
		},

		computed: {
			indexs: function() {
				var left = 1;
				var right = this.all;
				var ar = [];
				if(this.all >= 5) {
					if(this.cur > 3 && this.cur < this.all - 2) {
						left = this.cur - 2
						right = this.cur + 2
					} else {
						if(this.cur <= 3) {
							left = 1
							right = 5
						} else {
							right = this.all
							left = this.all - 4
						}
					}
				}
				while(left <= right) {
					ar.push(left)
					left++
				}
				return ar
			}

		},

		methods: {
			btnClick: function(data) { //页码点击事件
				if(data != this.cur) {
					this.cur = data
				}
				this._loadData(this.cur);
			},
			pageClick: function() {
				//console.log('现在在' + this.cur + '页');
				this._loadData(this.cur);
			},

			_loadData: function(step) {
				var url = this.baseURL.apidomain + '/changxianList.do?rand=' + Math.random();
				this.$ajax.get(url, {
					params: { //请求参数
						pageNum: step
					}
				}).then((response) => {
					this.goods = response.data.pageList;
					this.all = response.data.totalPages;
					/*this.$nextTick(() => {

						//计算高度
					});*/
					
					
					
				}).catch((response) => {
					console.log(response);
				})
			},

			addBuy: function(stockCode, index) {
				var url = this.baseURL.apisafe + '/editBuy.do?rand=' + Math.random();
				var params = new URLSearchParams();
				params.append('code', stockCode);
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url, params).then((response) => {
					this.msg = response.data.msg;
					if(this.msg != "用户未登录") {
						this.goods[index].isAddToBuy = "1";
					} else {
						alert(this.msg)
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
					if(this.msg != "用户未登录") {
						this.goods[index].isAddToBuy = "0";
					} else {
						alert(this.msg)
					}

				}).catch((response) => {
					console.log(response);
				})
			},

			onClick: function(code, index) {
				console.log(code + "  " + index)
				var isBuy = this.goods[index].isAddToBuy;
				if(isBuy > 0) {
					this.removeBuy(code, index);

				} else {
					this.addBuy(code, index);
				}

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
	
	.pagination a.banclick {
		cursor: not-allowed;
	}
</style>

//https://www.cnblogs.com/MrsQiu/p/7083299.html //https://blog.csdn.net/llx18042693031/article/details/54849002 //http://www.imooc.com/wenda/detail/458825 //https://segmentfault.com/q/1010000008289620