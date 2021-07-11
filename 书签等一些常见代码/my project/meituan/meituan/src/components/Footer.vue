
<template>
    <div>
       <div class="footer">
          <div class="footer-bar">
              <div class="pull-right">
                  <span>城市:</span>
                  <span class="space"></span>
                  <a href="#" class="btn btn-weak footer-citybtn">深圳</a>
              </div>
              <a href="#" class="btn btn-weak">登录</a>
              <span class="space"></span>
              <a href="#" class="btn btn-weak">注册</a>
         </div>
         <div class="footer-nav">
            <ul>
                <li>
                   <a class="react" href="#">首页</a>
                </li>
                <li>
                   <a class="react" href="#">我的</a>
                </li>
                <li>
                   <a class="react" href="#">美团下载</a>
                </li>
                <li>
                   <a class="react" href="#">电脑版</a>
                </li>
                <li>
                  <a class="react" href="#">帮助</a>
                </li>
            </ul>
        </div>
        <div class="footer-links">
        	<span>友情链接：</span>
        	<div class="flex1">
        		<a href="#">猫眼电影</a>
	            <a href="#">大众点评</a>
	            <a href="#">大众点评下载</a>
        	</div>
           
        </div>
        <div class="footer-copyright">
            <div class="hr"></div>
            <span class="footer-copyright-text">©2017 美团网 
               <a href="#">京ICP证070791号</a>
            </span>
        </div>
      </div>
     
      <div class="index-bottom"></div>
    </div>
</template>
<script>
   export default{
   	   data(){
   	   	  return {
   	   	  	 "msg":"<h2>�ײ������</h2>"
   	   	  }
   	   }
   }
</script>
<style scoped>
 .footer {
    margin-top: 1.5rem;
    padding: 0 1rem 2rem;
    background-color: #f0efed;
}
.footer-bar {
    font-size: 1.4rem;
    margin-bottom: 2rem;
}
.pull-right {
    float: right!important;
}
.footer-citybtn, .footer-minebtn {
    width: 9rem;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.btn-weak {
    border: .1rem solid #06c1ae;
    padding: 0 1.5rem;
    background: 0;
    color: #06c1ae;
}
.btn {
    display: inline-block;
    text-align: center;
    height: 3rem;
    border-radius: .3rem;
    font-size: 1.4rem;
    vertical-align: middle;
    line-height: 3rem;
    box-sizing: border-box;
    cursor: pointer;
}
.footer-nav {
    margin-bottom: .9rem;
}
.footer-nav ul {
    padding: .9rem 0;
    font-size: 1.2rem;
    line-height: 1.2rem;
    line-height: 1.2rem;
    white-space: nowrap;
    display: flex;
}
.footer-nav li {
    flex: 1;
    border-right: .1rem solid #666;
    text-align: center;
}
.footer-nav li:last-child{
    border-right: 0;
}
.footer-nav li>a {
    color: #06c1ae;
}
.footer-links {
    font-size: 1.2rem;
    line-height: 1.2rem;
    margin: 1.5rem 1rem;
    text-align: center;
    display: flex;
}
.footer-links a {
    color: #06c1ae;
}
.footer-links .flex1{
    flex: 1;
    display: flex;
}
.footer-links .flex1 a{
	flex:1;
	text-align: left;
	white-space: nowrap;
}
.footer-copyright {
    color: #999;
    font-size: 1.1rem;
    text-align: center;
    position: relative;
}
.footer-copyright .hr {
    display: block;
    content: "";
    width: 100%;
    position: absolute;
    top: 50%;
    border-top: 1px solid #999;
}
.footer-copyright-text {
    position: relative;
    padding: 0 1.5rem;
    background-color: #f0efed;
}
.footer-copyright a {
    color: #999;
}
.index-bottom{
   height: 7rem;
}

</style>