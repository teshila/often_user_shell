<template>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title" style="font-size: 0.6875rem;">{{title}}算法,共 {{totalCount}} 条，数据更新于{{time}}</h3>
		</div>
		<div class="panel-body">
			<searchBox @searchBox="findStock"></searchBox>
			<div class="contents" ref="foodwrapper">
				<ul class="list-group">
					<li class="list-group-item" v-for="(sts,index) in goods">
						<div class="container-fluid">
							<div class="row clearfix">
								<div class="col-md-3 column hidden-lg">代码：【{{sts.code}}】,名称：【{{sts.name}}】</div>
								<div class="col-md-3 column hidden-lg">地区：【{{sts.diQu}}】<span>,行业：【{{sts.hangye}}】</span><span>,流通股：【{{sts.liuTongGu}}】亿</span><span>,总股本：【{{sts.zongGuBen}}】亿</span></div>
								<div class="col-md-3 column operations hidden-lg">当前价：【{{sts.closePrice}}】 (
									<span @click="myQuery(sts.code,sts.name,index,sts.closePrice)" :class="{'isBuy':sts.isAddToBuy>0}">{{sts.isAddToBuy>0?'移出买单':'加入买单'}}</span>)
									【<span @click="getKDayLine(sts.code,sts.name)">日线</span>】
									【<span @click="getKWeekLine(sts.code,sts.name)">周线</span>】
								</div>

								<div class="col-md-3 column visible-lg-block">
									<img :src="'http://120.78.225.98/soup/api/pic/day/'+sts.code+'.do'" class="myPics">
								</div>
								<div class="col-md-3 column visible-lg-block">
									<img :src="'http://120.78.225.98/soup/api/pic/week/'+sts.code+'.do'" class="myPics">
								</div>
								<div class="col-md-3 column visible-lg-block">
									代码：【{{sts.code}}】,名称：【{{sts.name}}】,
									地区：【{{sts.diQu}}】<span>,行业：【{{sts.hangye}}】</span><span>,流通股：【{{sts.liuTongGu}}】亿</span><span>,总股本：【{{sts.zongGuBen}}】亿</span>
									<div style="color: red;">当前价：【{{sts.closePrice}}】</div>
									<div class="operations">
										<span @click="myQuery(sts.code,sts.name,index,sts.closePrice)" :class="{'isBuy':sts.isAddToBuy>0}">{{sts.isAddToBuy>0?'移出买单':'加入买单'}}</span>
									</div>
									
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
	import searchBox from './searchBox.vue';
	//import chart from '../charts.vue';
	import {
		MessageBox
	} from 'mint-ui';
	import {
		Toast
	} from 'mint-ui';
	export default {
		props: ['url', 'title', 'searchURL', 'getTimeURL'],
		components: {
			/* chart, */
			searchBox
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
				isZomm: "",
				tempList: [],
				tempIndex: "",
				tempTotal: "",
				time: ""
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
			_getLatestTime: function() {
				var url = this.baseURL.apidomain + '/' + this.getTimeURL + '.do?rand=' + Math.random();
				this.$ajax.get(url).then((response) => {
					this.time = response.data.time;
					if (this.time == null) {
						this.time = '17点'
					}
				}).catch((response) => {
					Toast(response);
				})
			},
			findStock: function(code) {
				var reqURL = this.searchURL;
				if (code != "") {
					var url = this.baseURL.apidomain + '/' + reqURL + '.do?code=' + code + '&rand=' + Math.random();
					//var token = this.cookie.getCookie("token");
					//var params = new URLSearchParams();
					this.$ajax.get(url).then((response) => {
						this.goods = response.data.list;
					}).catch((response) => {
						Toast(response);
					})
				} else {
					this.goods = this.tempList;
					this.page = this.tempIndex;
					//this.total = response.data.totalPages;
					//this.totalCount = response.data.totalCount;
					console.log(this.tempList)
				}
			},

			_loadData: function(step) {
				this.page = step;
				var url = this.baseURL.apidomain + "/" + this.url + '?rand=' + Math.random();
				this.$ajax.get(url, {
					params: { //请求参数
						pageNum: step
					}
				}).then((response) => {
					this.tempIndex = step;
					this.goods = response.data.pageList;
					this.total = response.data.totalPages;
					this.totalCount = response.data.totalCount;

					this.tempList = this.goods;
				}).catch((response) => {
					Toast(response);
				})
			},

			addBuy: function(stockCode, name, index, closePrice) {

				var url = this.baseURL.apidomain + '/editTempBuy.do?rand=' + Math.random();
				var params = new URLSearchParams();
				params.append('code', stockCode);
				params.append('name', name);
				var token = this.cookie.getCookie("token");
				params.append('token', token);
				params.append('yuSheBuyPrice', 0);
				this.$ajax.post(url, params).then((response) => {
					this.msg = response.data.msg;
					if (this.msg != "用户未登录") {
						this.goods[index].isAddToBuy = "1";
						Toast(this.msg);
					} else {
						Toast(this.msg);
					}
				}).catch((response) => {
					Toast(response);
				})
			},
			removeBuy: function(code, index) {
				var url = this.baseURL.apidomain + '/delTempBuy.do?rand=' + Math.random();
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
					Toast(response);
				})
			},

			myQuery: function(code, name, index, closePrice) {
				MessageBox.confirm('确定要操作吗?').then(action => {
					var isBuy = this.goods[index].isAddToBuy;
					if (isBuy > 0) {
						this.removeBuy(code, index);
					} else {
						this.addBuy(code, name, index, closePrice);
					}
				}, () => {});
			},

			getKDayLine: function(code, name) {

				this.isZomm = 1;
				this.name = name;
				this.code = code;
				this.isShow = true;
				if (code.indexOf("6") == 0) {
					code = "sh" + code;
				} else {
					code = "sz" + code;
				}
				this.dataURL = "http://image.sinajs.cn/newchart/daily/n/" + code + ".gif"
			},

			getKWeekLine: function(code, name) {

				this.isZomm = 1;
				this.name = name;
				this.code = code;
				this.isShow = true;
				if (code.indexOf("6") == 0) {
					code = "sh" + code;
				} else {
					code = "sz" + code;
				}
				this.dataURL = "http://image.sinajs.cn/newchart/weekly/n/" + code + ".gif"
			},



			showChildMsg: function(data) {
				this.isShow = false;
			},

			closeMyWindows: function() {
				this.isShow = false;
			}
		},
		created: function() {
			this._loadData(1);
			this._getLatestTime();
		}

	}
</script>

<style scoped="scoped">
	li {
		font-family: "宋体";
		font-size: 12px;
	}

	li:hover {
		/* background: #F7E1B5; */
		 background: #f6f6f6; 
		/* background: #CFD9DB; */
	}

	.blue {
		color: red;
	}

	.isBuy {
		color: #272323;
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


.operations span {
		cursor: pointer;
		display: inline-block;
		padding: 2px 4px;
	}
	
	.operations span:hover {
		color: #4CAE4C;
		font-weight: bold;
	}
	

@media screen  and (max-width:1200px) {
		.dialogs {
			left: 6%;
		}
		.contents {
			height: 250px;
			overflow-y: scroll;
		}
	}
	
@media screen and (min-width:1200px) and (max-height:1080px){
		.contents {
			height: 450px;
			overflow-y: scroll;
		}
		
		.operations span {
			padding: 4px 10px;
			background: #222222;
			color: #fff;
		}
		
	}
	
@media screen and (min-width:1200px) and (min-height:1080px){
		.contents {
			height: 900px;
			overflow-y: scroll;
		}
		
		.operations span {
			padding: 4px 10px;
			background: #222222;
			color: #fff;
		}
		
}	

.myClose {
		position: absolute;
		left: 0px;
		bottom: 0;
		background: #987979;
		width: 5rem;
		height: 5rem;
		line-height: 5rem;
		text-align: center;
		color: #000;
	}

	
	
	
	
	.myPics{
		width: 100%;
	}
</style>

//https://blog.csdn.net/qq_14863671/article/details/54412254
//https://blog.csdn.net/inuyasha1121/article/details/50777116
//https://blog.csdn.net/wy_Blog/article/details/55808656

