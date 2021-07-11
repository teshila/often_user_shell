<template>
  <div id="app">
    <div class="max" v-if="ifshow"></div>
     <transition name="fade">
       <div class="d-dialog " v-if="ifshow">
		    	<div class="d-dialog-header"> 
		    		<span class="d-dialog-title">微信登录</span> 
		    		<div class="d-dialog-header-buttons"> 
		    			<i class="d-dialog-close" @click="close">×</i> 
		    		</div> 
		    	</div>
		      <div class="d-dialog-body">
		      	<div class="login-container">
		      		<div class="title">微信登录</div>
		      		<div>
		      			<div class="wrp_code">
		      				<img class="qrcode lightBorder" src="../img/ewm.jpg">
		      			</div>
		      			<div class="info">
									<div class="status">
						          <p>请使用微信扫描二维码登录</p>
			                <p>“饿了么安全应急响应中心”</p>
						     </div>
		      		</div>
		      		</div>
		      	 </div>
		      </div>
		    </div>
    </transition>

    <div class="header">
             <div class="nav">
                  <img :src="src" alt="" />
                  <div class="login"><span @click="click">登录 </span></div>
                  <div class="header-nav">
                       <router-link to="/" class="v-link-active">首页</router-link>
                       <a @click="click">漏洞/情报上报</a>
                       <router-link to="/notice">通知公告</router-link>
                       <router-link to="/contribution">贡献榜</router-link>
                       <router-link to="/gift">礼品兑换</router-link>
                  </div>
             </div>                       
        </div>
  
      <router-view></router-view>
  </div>
</template>

<script>
 

export default {
  name: 'app',
  data () {
    return {
       src:"../../img/logo.png",
       ifshow:false
    }
  },
  methods:{
      click(){
         this.ifshow=true
      },
      close(){
         this.ifshow=false
      }
  }
}
</script>

<style scoped>
     *{
      margin:0;
      padding:0;
     }
    html,body{
       width: 100%;
       height: 100%;
       position: absolute;     
    }
    .max{
      width: 100%;
      height: 100%;
      background: black;
      opacity: 0.5;
      position: fixed;
      z-index: 1;
      overflow-x: hidden;
    }
     a{
       text-decoration:none;
     }
    .header{
        height:60px;
    }
    .nav{
       width:1080px;
       margin:0 auto;
    }
    .nav img{
        width:200px;
        height:50px;
        margin-top:3px;
        cursor:pointer;
        float:left;
    }
    .login{
        float:right;       
    }
    .login span{
        display:block;
        width:90px;
        height:30px;
        text-align:center;
        background:#009fff;
        margin-top:15px;
        cursor:pointer;
        line-height:30px;
        color:#fff;
        font-size:16px;
    }
    .header-nav{
       margin-left:20px;
       width:505px;
       float:left;
    }
    .header-nav a{
    	cursor: pointer;
       display:inline-block;
       margin-right:30px;
       line-height:60px;
       text-aligin:center;
       font-size:16px;
       color:#0068b7;
    }
 .d-dialog {
    position: fixed;
    background: #fff;
    border-radius: 4px;
    width: 320px;
    z-index: 3;
    left: 38%;
    top: 25%;
}

.d-dialog-header {
    border-bottom: 1px solid #e5e5e5;
    padding: 10px 8px;
    box-sizing: border-box;
}
.d-dialog-title {
    font-weight: 700;
}
.d-dialog-header-buttons {
    float: right;
}
.d-dialog-close{
	width: 16px;
	height: 16px;
	font-style: normal;
	cursor: pointer;
}
.d-dialog-body {
    padding: 10px;
    overflow: auto;
}
.title {
    text-align: center;
    font-size: 20px;
}
  .qrcode {
    width: 280px;
    margin-top: 15px;
    border: 1px solid #E2E2E2;
    
}
.info {
    width: 280px;
    margin: 0 auto;
}
.status {
    padding: 7px 14px;
    text-align: center;
    font-size: 14px;
}

 .fade-enter-active{

            animation: enterActive .3s;
        }
.fade-leave-active{

    animation: leaveActive .3s;
}


    @keyframes enterActive {

        0%{
           transform: scale(0.5);

        }
        100%{
            transform: scale(1);
        }

    }

    @keyframes leaveActive {

        0%{
            transform: scale(1);

        }
        100%{
            transform: scale(0.5);

        }

    }


</style>
