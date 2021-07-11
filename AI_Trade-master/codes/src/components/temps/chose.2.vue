<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">{{title}}算法(交日易收盘15点得出当前列表，共 {{totalCount}} 条)</h3>
		</div>
		<div class="panel-body">
			<div class="contents" ref="foodwrapper">
				<ul class="list-group">
					<li class="list-group-item" v-for="(sts,index) in goods"  @click="selectMenu(sts.code,{handle:1})">
						<div class="container-fluid">
							<div class="row clearfix">
								<div class="col-md-4 column">代码：【{{sts.code}}】</div>
								<div class="col-md-4 column">名称：【{{sts.name}}】</div>
								<div class="col-md-4 column">本日收盘价：【{{sts.closePrice}}】 (
									<span :class="{'isBuy':sts.isAddToBuy>0}">{{sts.isAddToBuy>0?'移出买单':'加入买单'}}</span>)
								</div>
							</div>
						</div>
					</li>
				</ul>
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
	import BScroll from 'better-scroll';
	export default {
		props: ['url', 'title'],
		data() {
			return {
				msg: "",
				goods: [],
				scrollY: 0,
				total: 0,
				page: 1,
				totalCount: 0
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
				console.log(step)
				this.page = step;
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

			addBuy: function(stockCode, index) {

				var url = this.baseURL.apisafe + '/editBuy.do?rand=' + Math.random();
				var params = new URLSearchParams();
				params.append('code', stockCode);
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				this.$ajax.post(url, params).then((response) => {
					this.msg = response.data.msg;
					if (this.msg != "用户未登录") {
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
					if (this.msg != "用户未登录") {
						this.goods[index].isAddToBuy = "0";
					} else {
						alert(this.msg)
					}

				}).catch((response) => {
					console.log(response);
				})
			},

			selectMenu: function(index, code) {
				
				
				var isBuy = this.goods[index].isAddToBuy;
				if (isBuy > 0) {
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

	.contents {
		height: 250px;
		overflow: hidden;
	}
</style>

//https://blog.csdn.net/qq_14863671/article/details/54412254
