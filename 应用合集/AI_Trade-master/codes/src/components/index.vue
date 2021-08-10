<template>
	<div class="hello">
		<ul class="mainmenu">
			<li>
				<router-link :to="{path:'weiHu'}" ><b><img src="../assets/index/1.png" /></b><span>股东维护</span></router-link>
			</li>
			<li>
				<router-link :to="{path:'dayUpList'}"  ><b><img src="../assets/index/2.png" /></b><span>短信通知</span></router-link>
			</li>
			<li>
				<router-link  :to="{path:'selllist'}"  ><b><img src="../assets/index/3.png" /></b><span>连续三天涨停</span></router-link>
			</li>
			<li>
				<router-link  :to="{path:'sms'}" ><b><img src="../assets/index/4.png" /></b><span>短信配置</span></router-link>
			</li>
			<li>
				<router-link  :to="{path:'dayList'}" ><b><img src="../assets/index/5.png" /></b><span>列表</span></router-link>
			</li>
			<li>
				<router-link  :to="{path:'/byhand'}" ><b><img src="../assets/index/6.png" /></b><span>手动关注</span></router-link>
			</li>
			<li>
				<router-link  :to="{path:'/buy'}" ><b><img src="../assets/index/7.png" /></b><span>添加修改买单</span></router-link>
			</li>
			<li>
				<router-link :to="{path:'sell'}" ><b><img src="../assets/index/8.png" /></b><span>添加修改卖单</span></router-link>
			</li>
			<li>
				<router-link :to="{path:'changxian'}" ><b><img src="../assets/index/1.png" /></b><span>中长线算法涨</span></router-link>
			</li>
			<li>
				<router-link  :to="{path:'duanxian'}" ><b><img src="../assets/index/2.png" /></b><span>短线算法涨</span></router-link>
			</li>
			<li>
				<router-link :to="{path:'login'}" ><b><img src="../assets/index/1.png" /></b><span>用户登录</span></router-link>
			</li>
		</ul>
	</div>
</template>

<script>
	export default {
		name: 'HelloWorld',
		data() {
			return {
				msg: 'Welcome to Your Vue.js App'
			}
		}
	}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
	
	
	html,
	body {
		color: #222;
		font-family: Microsoft YaHei, Helvitica, Verdana, Tohoma, Arial, san-serif;
		margin: 0;
		padding: 0;
		text-decoration: none;
	}
	
	img {
		border: 0;
	}
	
	ul {
		list-style: none outside none;
		margin: 0;
		padding: 0;
	}
	
	body {
		background-color: #eee;
	}
	
	body .mainmenu:after {
		clear: both;
		content: " ";
		display: block;
	}
	
	body .mainmenu li {
		float: left;
		margin-left: 2.5%;
		margin-top: 2.5%;
		width: 30%;
		border-radius: 3px;
		overflow: hidden;
	}
	
	body .mainmenu li a {
		display: block;
		color: #FFF;
		text-align: center
	}
	
	body .mainmenu li a b {
		display: block;
		height: 80px;
	}
	
	body .mainmenu li a img {
		margin: 15px auto 15px;
		width: 50px;
		height: 50px;
	}
	
	body .mainmenu li a span {
		display: block;
		height: 30px;
		line-height: 30px;
		background-color: #FFF;
		color: #999;
		font-size: 14px;
	}
	
	body .mainmenu li:nth-child(8n+1) {
		background-color: #36A1DB
	}
	
	body .mainmenu li:nth-child(8n+2) {
		background-color: #678ce1
	}
	
	body .mainmenu li:nth-child(8n+3) {
		background-color: #8c67df
	}
	
	body .mainmenu li:nth-child(8n+4) {
		background-color: #84d018
	}
	
	body .mainmenu li:nth-child(8n+5) {
		background-color: #14c760
	}
	
	body .mainmenu li:nth-child(8n+6) {
		background-color: #f3b613
	}
	
	body .mainmenu li:nth-child(8n+7) {
		background-color: #ff8a4a
	}
	
	body .mainmenu li:nth-child(8n+8) {
		background-color: #fc5366
	}
</style>