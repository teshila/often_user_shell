<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">长线算法(交日易收盘16点得出当前列表，共 {{totalCount}} 条)</h3>
		</div>
		<div class="panel-body">
			<div class="contents">
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
			<div>
				<ul class="pagination">
					<li v-for="index in indexs" :class="{ 'active': page == index}"><a @click="_loadData(index)">{{index}}</a></li>
				</ul>
			</div>

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
				this.page = step;
				var url = this.baseURL.apidomain + '/changxianList.do?rand=' + Math.random();
				this.$ajax.get(url, {
					params: { //请求参数
						pageNum: step
					}
				}).then((response) => {
					this.goods = response.data.pageList;
					this.all = response.data.totalPages;
					this.total = response.data.totalPages;
					this.totalCount = response.data.totalCount;
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

			onClick: function(code, index) {
				console.log(code + "  " + index)
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
		min-height: 41rem;
	}
</style>

//https://www.cnblogs.com/MrsQiu/p/7083299.html //https://blog.csdn.net/llx18042693031/article/details/54849002
//http://www.imooc.com/wenda/detail/458825 //https://segmentfault.com/q/1010000008289620
