<template>
  <div>
     <div class="navbar">
         <div class="nav-wrap-left">
             <router-link to="/home" class="back">
                <i class="text-icon icon-back"></i>
             </router-link> 
         </div>
         <span class="nav-header">分类</span>
         <div class="nav-wrap-right">
             <a class="react">
                <span class="nav-btn"><i class="text-icon"></i>我的</span>
             </a>
             <a class="react headSearch">
                <span class="nav-btn">
                    <i class="text-icon"></i>搜索
                </span>
            </a>
         </div>
     </div>
     <div class="carousel">
         <img src="../../img/1111.png" alt="" />
     </div>
     <div class="nav-bar">
         <ul class="nav">
            <li class="dropdown-toggle caret category" data-index="1" @click="click" :class="num == 1 ? 'active':'' ">
               <span class="nav-head-name" data-index="1">美食</span>
            </li>
            <li data-index="2" class="dropdown-toggle caret biz subway" @click="click"  :class="num == 2 ? 'active':'' ">
                <span class="nav-head-name" data-index="2">全城</span>
            </li>
            <li data-index="3" class="dropdown-toggle caret sort" @click="click" :class="num == 3 ? 'active':'' ">
                <span class="nav-head-name" data-index="3">默认排序</span>
            </li>
            <li data-index="4" class="dropdown-toggle caret csp" @click="click" :class="num == 4 ? 'active':'' ">
                <span class="nav-head-name" data-index="4">筛选</span>
            </li>
         </ul>
         <div class="dropdown-wrapper">
         	
         	  <div class="dropdown-module" >
         	  	 <div class="sub-nav" :class="num == 2 ? 'active':'' ">
                <ul class="tab">
                    <li class="active" data-subnav="biz">商区</li>
                    <li>地铁站</li>
                </ul>
               </div>
         	  	 <div class="scroller-wrapper">
         	  	 	  <div class="dropdown-scroller">
         	  	 	  	<ul>
         	  	 	  		<li class="category-wrapper" :class="num == 1 ? 'active':'' ">
         	  	 	  			<ul class="dropdown-list">
	         	  	 	  				<li class="right-arrow-point-right active">
	         	  	 	  					 <span>美食</span>
	         	  	 	  					 <span class="quantity"><b>19522</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>今日新单</span>
	         	  	 	  					 <span class="quantity"><b>497</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>猫眼电影</span>
	         	  	 	  					 <span class="quantity"><b>1</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>全部分类</span>
	         	  	 	  					 <span class="quantity"><b>152775</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>酒店</span>
	         	  	 	  					 <span class="quantity"><b>1903</b></span>
	         	  	 	  				</li>
	         	  	 	  				
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>周边游</span>
	         	  	 	  					 <span class="quantity"><b>1522</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>休闲娱乐</span>
	         	  	 	  					 <span class="quantity"><b>1452</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>购物</span>
	         	  	 	  					 <span class="quantity"><b>156822</b></span>
	         	  	 	  				</li>
	         	  	 	  				<li class="right-arrow-point-right">
	         	  	 	  					 <span>度假出行</span>
	         	  	 	  					 <span class="quantity"><b>1422</b></span>
	         	  	 	  				</li>
         	  	 	  			</ul>
         	  	 	  		</li>
         	  	 	  		<li class="biz-wrapper" :class="num == 2 ? 'active':'' ">
         	  	 	  			 <ul class="dropdown-list">
         	  	 	  			 	  <li class="active">
         	  	 	  			 	  	 <span>全城</span>
         	  	 	  			 	  	 <span class="quantity"><b>44118</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>福田区</span>
         	  	 	  			 	  	 <span class="quantity"><b>3543</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>罗湖区</span>
         	  	 	  			 	  	 <span class="quantity"><b>8118</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>南山区</span>
         	  	 	  			 	  	 <span class="quantity"><b>4599</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>宝安区</span>
         	  	 	  			 	  	 <span class="quantity"><b>2567</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>龙岗区</span>
         	  	 	  			 	  	 <span class="quantity"><b>7918</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>龙华新区</span>
         	  	 	  			 	  	 <span class="quantity"><b>4678</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 	  <li class="right-arrow-point-right ">
         	  	 	  			 	  	 <span>盐田区</span>
         	  	 	  			 	  	 <span class="quantity"><b>54118</b></span>
         	  	 	  			 	  </li>
         	  	 	  			 </ul>
         	  	 	  		</li>
         	  	 	  	  <li class="sort-wrapper" :class="num == 3 ? 'active':'' ">
         	  	 	  	  	 <ul class="dropdown-list">
         	  	 	  	  	 	 <li class="active">
         	  	 	  	  	 	 	  <span>默认排序</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 	 <li class="active">
         	  	 	  	  	 	 	  <span>离我最近</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 	 <li>
         	  	 	  	  	 	 	  <span>评价最高</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 	 <li>
         	  	 	  	  	 	 	  <span>最新发布</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 	 <li>
         	  	 	  	  	 	 	  <span>人气最高</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 	 <li>
         	  	 	  	  	 	 	  <span>价格最低</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 	 <li>
         	  	 	  	  	 	 	  <span>价格最高</span>
         	  	 	  	  	 	 	  <span class="quantity"></span>
         	  	 	  	  	 	 </li>
         	  	 	  	  	 </ul>
         	  	 	  	  </li>
         	  	 	  	  <li class="csp-wrapper" :class="num == 4 ? 'active':'' ">
         	  	 	  	  	  <div>
         	  	 	  	  	  	<dl class="list">
         	  	 	  	  	  		 <dd class="checkbox">
         	  	 	  	  	  		 	  <label class="react">
         	  	 	  	  	  		 	  	<div class="kv-line-r">
         	  	 	  	  	  		 	  		<h6>只看免预约</h6>
         	  	 	  	  	  		 	  		<p><input type="checkbox" class="mt"></p>
         	  	 	  	  	  		 	  	</div>
         	  	 	  	  	  		 	  </label>
         	  	 	  	  	  		 </dd>
         	  	 	  	  	  		 <dd class="checkbox">
         	  	 	  	  	  		 	  <label class="react">
         	  	 	  	  	  		 	  	<div class="kv-line-r">
         	  	 	  	  	  		 	  		<h6>节假日可用</h6>
         	  	 	  	  	  		 	  		<p><input type="checkbox" class="mt"></p>
         	  	 	  	  	  		 	  	</div>
         	  	 	  	  	  		 	  </label>
         	  	 	  	  	  		 </dd>
         	  	 	  	  	  		 <dd class="dd-padding checklist">
         	  	 	  	  	  		 	  <h6>推荐分类</h6>
         	  	 	  	  	  		 	  <div class="radioarea">
         	  	 	  	  	  		 	  	<label class="btn btn-weak">不限
         	  	 	  	  	  		 	  		<input type="radio" name="poi_attr_20070">
         	  	 	  	  	  		 	  	</label>
         	  	 	  	  	  		 	  	<label class="btn btn-weak">品质优选
         	  	 	  	  	  		 	  		<input type="radio" name="poi_attr_20070" value="20292">
         	  	 	  	  	  		 	  	</label>
         	  	 	  	  	  		 	  </div>
         	  	 	  	  	  		 </dd>
         	  	 	  	  	  	   <dd class="dd-padding checklist">
         	  	 	  	  	  	   	 <h6>用餐人数</h6>
         	  	 	  	  	  	   	 <div class="radioarea" >
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">不限
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">单人餐
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="115">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">双人餐
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="117">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">3~4人餐
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="111">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">5~10人餐
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="112">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">10人以上
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="110">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">代金券
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="114">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 	 <label class="btn btn-weak">其它
         	  	 	  	  	  	   	 	 	<input type="radio" name="deal_attr_25" value="116">
         	  	 	  	  	  	   	 	 </label>
         	  	 	  	  	  	   	 </div>
         	  	 	  	  	  	   </dd>
         	  	 	  	  	  	</dl>
         	  	 	  	  	  </div>
         	  	 	  	  	  <div class="footer">
         	  	 	  	  	  	<a class="btn btn-large btn-link btn-reset">重置</a>
         	  	 	  	  	  	<a class="pull-right btn-link btn-large btn btn-save">保存</a>
         	  	 	  	  	  </div>
         	  	 	  	  </li>
         	  	 	  	</ul>
         	  	 	  </div>
         	  	 	  <div class="dropdown-sub-scroller" :class="num == 1 ? 'active':'' ">
         	  	 	  	 <ul class="dropdown-list">
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">全部</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">19522</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">代金卷</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">9102</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">甜点饮品</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">6762</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">生日蛋糕</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">5355</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">火锅</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">2884</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">自助餐</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">4522</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">小吃快餐</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">122</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">日韩料理</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">15522</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 	  <li>
         	  	 	  	 	  	 <div>
         	  	 	  	 	  	 	  <span class="sub-name">西餐</span>
         	  	 	  	 	  	 	  <span class="sub-quantity">1522</span>
         	  	 	  	 	  	 </div>
         	  	 	  	 	  </li>
         	  	 	  	 </ul>
         	  	 	  </div>
         	  	 </div>
         	  </div>
         	 
         </div>
     </div>
     <v-store ref="store"></v-store>
     <div id="nav-bread" class="nav-bread">
		当前位置：
    			<a>深圳团购</a>
	    			&gt;
    			<a>美食团购</a>
      </div>
     <v-footer></v-footer>
    <v-fixed></v-fixed>
  </div>
</template>
<script>
   import Store from "./Store.vue"
   import Footer from "./Footer.vue"
   import Fixed from "./fixed.vue"
   export default{
   	  data(){
   	  	 return {
            "num":0
   	  	 }
   	  },
   	  methods:{
   	  	click(e){
   	  		
   	  		this.num=e.target.dataset.index
   	  		this.$refs.store.ifshow=true;
   	  	}
   	  },
   	  components:{
   	  	 "v-store":Store,
   	  	 "v-footer":Footer,
   	  	 "v-fixed":Fixed
   	  }
   }
</script>
<style scoped>
  .navbar{
      width: 100%;
      height: 5rem;
      background: #06c1ae;
      border-bottom: 1px solid #21897d;
      display: flex;
    }
    .navbar .nav-wrap-left {
	    height: 5rem;
	    line-height: 5rem;
   }
   .nav-wrap-left a.back {
	    height: 5rem;
	    width: 2.25rem;
	    padding: 0 1.5rem;
	    display: block;
  }
  .text-icon.icon-back {
  	display: inline-block;
    width: 2.25rem;
    height: 2.25rem;
    vertical-align: middle;
    position: relative;
}
.text-icon.icon-back:before {
    content: '';
    display: block;
    position: absolute;
    left: .5rem;
    top: 0;
    width: 2rem;
    height: 2rem;
    border-bottom: .2rem solid #fff;
    border-left: .2rem solid #fff;
    -webkit-transform: scaleY(0.8) rotateZ(45deg);
    -moz-transform: scaleY(0.8) rotateZ(45deg);
    -ms-transform: scaleY(0.8) rotateZ(45deg);
}

 .navbar .nav-header {
    display: block;
    flex: 1;
    -ms-flex: 1;
    font-size: 1.8rem;
    font-weight: 400;
    text-align: center;
    line-height: 5rem;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    color: #fff;
}
.navbar .nav-wrap-right {
	width: 10rem;
    height: 100%;
}
.nav-wrap-right a {
    display: inline-block;
    height: 100%;
    line-height: 5rem;
    text-align: center;
    width: 4.7rem;
    color: #fff;
}
.nav-btn {
    display: inline-block;
    font-size: 1rem;
    line-height: 1rem;
    vertical-align: middle;
}
.nav-btn .text-icon {
	width: 2rem;
	height: 2rem;
    display: block;
    margin: auto;
    margin-bottom: .6rem;
    font-size: 2rem;
    vertical-align: middle;
    background: url(../../img/user.png) no-repeat center;
    background-size: contain;
}
.headSearch .text-icon{
	background: url(../../img/sousuo.jpg) no-repeat center;
	background-size: contain;
}
.carousel{
        width: 100%;
        height: 5rem;
    }
.carousel img{
  margin: 0 auto;
  width: 100%;
  height: 5rem;
}
.nav-bar {
    position: relative;
    z-index: 30;
}
.nav-bar .nav {
	display: flex;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    background: #FFF;
    border-bottom: 1px solid #c6c0b3;
    text-align: center;
    height: 4.2rem;
    line-height: 4.2rem;
}
.dropdown-toggle {
    -webkit-box-flex: 1;
    -moz-box-flex: 1;
    -ms-flex: 1;
    border-left: 1px solid #c6c0b3;
    width: 100%;
    text-align: center;
    color: #666;
    font-size: 1.4rem;

}
.dropdown-toggle:first-child {
    border: 0;
}
.nav-head-name {
    display: inline-block;
    white-space: nowrap;
    overflow: hidden;
    max-width: 80%;
    vertical-align: middle;
    text-overflow: ellipsis;
}
.dropdown-toggle.caret:after {
    content: "";
    width: 0;
    height: 0;
    display: inline-block;
    border-top: .75rem solid #B7B7B7;
    border-left: .45rem solid transparent;
    border-right: .45rem solid transparent;
    border-bottom: 0;
    margin-left: .6rem;
}
.dropdown-toggle.active.caret:after{
  -webkit-transform: rotateZ(180deg);
  -moz-transform: rotateZ(180deg);
  -ms-transform: rotateZ(180deg);
	border-top-color: #06C1AE;
	
}
.nav-bread {
    padding: 1.5rem 1rem 0;
    margin-bottom: 1.5rem;
    color: #06c1ae;
    font-size: 1.2rem;
}
.nav-bread a {
    color: #06c1ae;
}
.dropdown-wrapper {
    position: relative;
   
}
.dropdown-module {
    position: absolute;
    width: 100%;
    overflow: hidden;
    -webkit-transition: height .3s;
    
}



.scroller-wrapper {
    position: relative;
    
}
.scroller-wrapper .dropdown-scroller {
    width: 100%;
    background: #f6f4f0;
}
.scroller-wrapper .dropdown-sub-scroller {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    border-left: 1px solid #ddd8cd;
    background: #f6f4f0;
    overflow: hidden;
    height: 40.76rem;
    left: 16rem;
    display: none;
}
.scroller-wrapper .dropdown-sub-scroller.active{
	 display: block;
}
.scroller-wrapper .dropdown-sub-scroller ul{
	  transition-property: -webkit-transform;
    transform-origin: 0px 0px 0px;
    transform: translate3d(0px, 0px, 0px);
}
.scroller-wrapper .dropdown-scroller ul {
    width: 100%;
    background: #FFF;
    transition-property: -webkit-transform;
    transform-origin: 0px 0px 0px;
    transform: translate3d(0px, 0px, 0px);
}
.category-wrapper.active, .biz-wrapper.active, .brand-wrapper.active, .sort-wrapper.active, .csp-wrapper.active, .subway-wrapper.active, .floor-wrapper.active {
    display: block;
}
.category-wrapper, .biz-wrapper, .brand-wrapper, .sort-wrapper, .csp-wrapper, .subway-wrapper, .floor-wrapper {
    display: none;
}
.dropdown-list{
	width: 100%;
}
.dropdown-list>li.active {
    background: #f6f4f0;
}
.dropdown-list>li {
    border-bottom: 1px solid #ddd8cd;
    line-height: 4.35rem;
    height: 4.35rem;
    position: relative;
    color: #666;
}
.dropdown-list span {
    text-align: left;
    margin: 0;
    margin-left: 2rem;
    font-size: 1.4rem;
}
.dropdown-list .quantity {
    display: inline-block;
    text-align: right;
    margin-right: .8rem;
    font-size: 1.1rem;
    position: absolute;
    right: 0;
    top: 1.5rem;
}
.dropdown-list .quantity>b {
    font-weight: 400;
    background: #CCC;
    border-radius: .4rem;
    padding: 0 .7rem;
    color: #FFF;
    line-height: 1.4rem;
    font-size: 1.2rem;
    height: 1.4rem;
    float: right;
}
.right-arrow-point-right .quantity>b:after {
    content: '>';
    padding-left: .35rem;
    font-family: simsun;
}
.dropdown-sub-scroller .sub-name {
    display: block;
    margin-right: 3rem;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.dropdown-sub-scroller .sub-quantity {
    color: #B4B4B4;
    position: absolute;
    margin-right: .8rem;
    right: 0;
    top: 0;
}
.sub-nav {
    background: #FEFEFE;
    height: 4rem;
    line-height: 4rem;
    border-bottom: 1px solid #c6c0b3;
    display: none;
    text-align: center;
}
.sub-nav.active{
	display: block;
}
ul.tab {
    display: inline-block;
    height: 3rem;
    line-height: 3rem;
    border-radius: .2rem;
    border: 1px solid #666;
}
.tab li.active {
    background: #666;
    color: #fff;
}
.tab li {
	
    display: inline-block;
    text-align: center;
    width: 12rem;
    border-right: 1px solid #666;
}
.tab li:last-child {
    border: 0;
    color: #333;
}
.sort-wrapper li.active {
    color: #06c1ae;
}
.dropdown-toggle.active {
    color: #06c1ae;
}
.csp-wrapper .footer {
    background-color: #f6f4f0;
}
.btn-large.btn-link {
    font-size: 1.5rem;
}
.btn-link {
    padding: 0 1.5rem;
    background: 0;
    color: #06c1ae;
}
.btn-large {
    height: 4rem;
    line-height: 4rem;
}
.btn {
    display: inline-block;
    margin: 0;
    text-align: center;  
    border-radius: .3rem;
    border: 0; 
    vertical-align: middle;
    box-sizing: border-box;
    cursor: pointer;
    font-size: 1.4rem;
    line-height: 3rem;
}
.pull-right {
    float: right!important;
}
dl.list { 
    border-bottom: 1px solid #DDD8CE;
    background-color: #fff;
}
dl.list dd {
    margin: 0;
    border-bottom: 1px solid #DDD8CE;
    overflow: hidden;
    font-size: inherit;
    position: relative;
}
dl.list dd>.react {
    padding: 1.4rem 1rem;
    display: block;
    height: 100%;
    }
    .csp-wrapper .kv-line-r {
    margin: 0;
    display: flex;
}
.kv-line-r>h6 {
    -webkit-flex: 1;
    -moz-flex: 1;
    -ms-flex: 1;
    font-size: 1.4rem;
    font-weight: 400;
    margin-right: 1rem;
    display: block;
}
h6 {
    line-height: 1rem;
    text-align: justify;
    word-break: break-all;
}
.kv-line-r>p {
    display: block;
    line-height: 2rem;
    text-align: justify;
    margin: -.28rem 0;
    word-break: break-all;
}
input.mt{
    border-radius: .3rem;
}
input.mt{
    -webkit-appearance: none;
    width: 2.25rem;
    height: 2.25rem;
    margin: -.35rem 0;
    border: 1px solid #DDD8CE;
    text-align: center;
    vertical-align: middle;
    line-height: 2.25rem;
    outline: 0;
}
dl.list .dd-padding{
    padding: 1.4rem 1rem;
}
.csp-wrapper .checklist h6 {
    font-size: 1.4rem;
    font-weight: 400;
    margin-bottom: 1.5rem;
}
.csp-wrapper .radioarea {
    margin:0 -.3rem -1rem;
}
.csp-wrapper .dd-padding label.btn {
    margin: 0;
    margin-left: .3rem;
    margin-right: .3rem;
    margin-bottom: 1rem;
    min-width: 7rem;
    padding: 0 1rem;
}
.btn-weak {
    border: 1px solid #06c1ae;
    background: 0;
    color: #06c1ae;
}
label.btn input {
    display: none;
}
</style>