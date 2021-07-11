<template>
	<div>
		<div class="pop-content i2app-pop">
           <img class="mt-downclient-img" src="../../img/logo.png" alt="" />
           <div class="mt-downclient-title">9.9元看电影<br>快来美团手机客户端</div> 
          <a class="btn mt-downclient-btn mj-down btn-weak">点击下载</a>
      </div>

      <div class="top-btn">
        <a class="react">
          <i class="text-icon"></i>
        </a>
      </div>
	</div>
	
</template>
<style scoped>
.pop-content {
    z-index: 99;
    background: rgba(0,0,0,0.8);
    box-sizing: border-box;
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 7rem;
    padding: 1rem 0 1rem 2.2rem;
    overflow: hidden;
    display: flex;
}
.mt-downclient-title {
    color: #fff;
    font-size: 1.4rem;
    line-height: 2.1rem;
    flex:1;
}
.mt-downclient-img {
    width: 5rem;
    height: 5rem;
    margin-right:1rem;
}
.pop-content .btn{
  margin:.8rem 1.5rem 0 0;
}	
.top-btn {
    position: fixed;
    right: 1.5rem;
    bottom: 7.5rem;
    width: 4.3rem;
    height: 4.3rem;
    line-height: 4.3rem;
    background: #06c1ae;
    z-index: 130;
    border-radius: 50%;
    color: #fff;
    overflow: hidden;
    text-align: center;
}
 
.top-btn .text-icon{
  margin:0 auto;
  display: block;
  width: 2.5rem;
  height: 4.3rem;
  background: url(../../img/top.png) no-repeat center;
  background-size: contain;
}	
</style>