<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">自动化涨算法列表【系统在交易日每天17点自动更新】</h3>
		</div>
		<div class="panel-body">
			<ul class="list-group">
				<li class="list-group-item" v-for="(sts,index) in list">
					<div class="container-fluid">
						<div class="row clearfix">
							<div class="col-md-4 column">代码：【{{sts.code}}】</div>
							<div class="col-md-4 column">名称：【{{sts.name}}】</div>
							<div class="col-md-4 column">本日收盘价：【{{sts.closePrice}}】 (
								<span @click="onClick(sts.code,index)" :class="{'isBuy':sts.isAddToBuy>0}">{{sts.isAddToBuy>0?'加入买单':'移出买单'}}</span>)
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
		data() {
			return {
				list: [],
				msg: "",
			}
		},
		methods: {

			initMenu: function() {
				var url = this.baseURL.apidomain + '/changxianList.do?rand=' + Math.random();
				this.$ajax.get(url).then((response) => {
					this.list = response.data.pageList;
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
						this.list[index].isAddToBuy = "1";
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
						this.list[index].isAddToBuy = "0";
					} else {
						alert(this.msg)
					}

				}).catch((response) => {
					console.log(response);
				})
			},

			onClick: function(code, index) {
				var isBuy = this.list[index].isAddToBuy;
				if(isBuy > 0) {
					this.removeBuy(code, index);

				} else {
					this.addBuy(code, index);
				}

			}
		},
		created: function() {

			this.initMenu();
		}

	}
</script>

<style>
	.isBuy {
		color: red;
	}
	
	.list-group span:hover {
		color: #14C760
	}
	
	.list-group span {
		cursor: pointer;
	}
	
	
</style>
<!--http://www.imooc.com/wenda/detail/458825-->

