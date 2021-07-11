<template>
	<div class="wrap" ref="foodwrapper">
		<ul class="list-group">
			<li class="list-group-item" v-for="(sts,index) in goods">
				<div class="container-fluid">
					<div class="row clearfix">
						<div class="col-md-4 column">代码：【{{sts.code}}】</div>
						<div class="col-md-4 column">名称：【{{sts.name}}】</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</template>

<script>
	import BScroll from 'better-scroll';
	export default {
		props: ['code'],
		data() {
			return {
				goods: []
			}
		},
		methods: {
			_loadData: function() {
				var url = this.baseURL.apidomain + '/duanxianList.do?rand=' + Math.random();
				this.$ajax.get(url, {
					params: { //请求参数
						pageNum: 0
					}
				}).then((response) => {
					this.goods = response.data.pageList;
					this.totalPages = response.data.totalPages;
					this.$nextTick(() => {
						this._initScroll();
						//计算高度
					})
				}).catch((response) => {
					console.log(response);
				})
			},
			_initScroll: function() {
				this.foodScroll = new BScroll(this.$refs.foodwrapper, {
					click: true,
					//探针作用，实时监测滚动位置
					probeType: 3
				});
				console.log(this.foodScroll)
				//设置监听滚动位置
				this.foodScroll.on('scroll', (pos) => {
					//scrollY接收变量
					this.scrollY = Math.abs(Math.round(pos.y));
					let ht = this.scrollY;
					let foodList = this.$refs.foodwrapper.getElementsByClassName('list-group-item');
					let myheight = foodList.length * foodList[0].clientHeight;

					console.log(this.scrollY)

				});
			}

		},
		created: function() {
			this._loadData();
		}


	}
</script>

<style scoped="scoped">
	.wrap {
		overflow: hidden;
		height: 50vh;
	}
</style>
