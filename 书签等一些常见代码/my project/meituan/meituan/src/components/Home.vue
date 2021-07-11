

<template>
   <div>     
      <div class="navbar">
          <div class="nav-wrap-left">
                <a href="#" class="react">
                    <span class="nav-city">深圳<span class="space"></span><i class="text-icon icon-downarrow"></i></span>
                </a>
            </div>
            <div class="box-search">
                <a class="react" href="#">
                  <i class="icon-search text-icon"></i>
                  <span class="single-line">
                        输入商家/品类/商圈
                  </span>
                </a>
            </div>
            <div class="nav-wrap-right">
                <a class="react" href="#">
                  <span class="nav-btn">
                      <i class="text-icon"></i>我的
                  </span>
                </a>
            </div>
      </div>
      <div class="carousel">
         <img src="../../img/1111.png" alt="" />
      </div>
      <div class="list list-in">
           <mt-swipe :auto="0" :show-indicators='true'>
              <mt-swipe-item>
                  <ul class="icon-list">
                      <li>
                           <router-link to="/content">
                              <span class="new-icon-circle">
                                 <img src="../../img/icon-1.png" alt="" />
                              </span>
                              <span>美食</span> 
                            </router-link>
                      </li>
                       <li v-for="item in list1">
                            <span class="new-icon-circle">
                               <img :src="item.img" alt="" />
                            </span>
                            <span>{{item.title}}</span> 
                      </li>
                  </ul>
              </mt-swipe-item>
              <mt-swipe-item>
                   <ul class="icon-list">
                      <li v-for="item in list2">
                         <span class="new-icon-circle">
                            <img :src="item.img" alt="" />
                         </span>
                         <span>{{item.title}}</span> 
                      </li>
                  </ul>
              </mt-swipe-item>
          </mt-swipe>
      </div>
      <div class="daogou">
         <div class="dd-padding">
            <div class="left">
                <span class="info">
                    <strong>我们约吧</strong>
                    <small>恋人家人好朋友</small>
                </span>
                <span class="imgbox">
                   <img src="../../img/we.png" alt="" />
                </span>
            </div>
            <div class="right">
                <div class="top">
                    <span class="info">
                        <strong>低价超值</strong>
                        <small>十元惠生活</small>
                    </span>
                    <span class="imgbox">
                       <img src="../../img/ham.jpg" alt="" />
                    </span>
                </div>
                <div class="bottom">
                    <a class="react scense" href="#">
                      <span class="info">
                          <strong>工作简餐</strong>
                          <small>实惠方便选择多</small>
                      </span>
                      <span class="imgbox">
                        <img src="../../img/cake.png" alt="" />
                      </span>
                  </a>
                  <a class="react mingdian">
                      <span class="info">
                        <strong>名店抢购</strong>
                        <small class="time-desc">距离结束</small>
                        <span class="time-panel">
                          <span class="span-time time-hour">07</span>:
                          <span class="span-time time-min">39</span>:
                          <span class="span-time time-sec">35</span>
                        </span>
                    </span>
                  </a>
                </div>
            </div>
         </div>
      </div>
      <v-like></v-like>
      
      <v-footer></v-footer>
      
      <v-fixed></v-fixed>
      
   </div>
</template>

<script>
   import Like from './Like.vue'
   import Footer from "./Footer.vue"
   import Fixed from "./fixed.vue"
    export default{
        data(){
           return {
              list1:[
                 
                  {
                    "img":"../../img/icon-2.png",
                    "title":"猫眼电影"
                  },
                  {
                    "img":"../../img/icon-3.png",
                    "title":"酒店"
                  },
                  {
                    "img":"../../img/icon-4.png",
                    "title":"休闲娱乐"
                  },
                  {
                    "img":"../../img/icon-5.png",
                    "title":"外卖"
                  },
                  {
                    "img":"../../img/icon-6.png",
                    "title":"KTV"
                  },
                  {
                    "img":"../../img/icon-7.png",
                    "title":"周边游"
                  },
                  {
                    "img":"../../img/icon-8.png",
                    "title":"丽人"
                  },
                  {
                    "img":"../../img/icon-9.png",
                    "title":"小吃快餐"
                  },
                  {
                    "img":"../../img/icon-10.png",
                    "title":"机票/火车票"
                  }
              ],
              list2:[
                  {
                    "img":"../../img/icons-1.png",
                    "title":"生活服务"
                  }, 
                  {
                    "img":"../../img/icons-2.png",
                    "title":"今日新单"
                  },
                  {
                    "img":"../../img/icons-3.png",
                    "title":"足疗按摩"
                  },
                  {
                    "img":"../../img/icons-4.png",
                    "title":"甜点饮品"
                  },
                  {
                    "img":"../../img/icons-5.png",
                    "title":"婚纱摄影"
                  },
                  {
                    "img":"../../img/icons-6.png",
                    "title":"母婴亲子"
                  },
                  {
                    "img":"../../img/icons-7.png",
                    "title":"学习培训"
                  },
                  {
                    "img":"../../img/icons-8.png",
                    "title":"结婚"
                  },
                  {
                    "img":"../../img/icons-9.png",
                    "title":"家装"
                  },
                  {
                    "img":"../../img/icons-10.png",
                    "title":"全部分类"
                  }
              ]
           }
        },
        methods:{
          
        },
        components:{
          "v-like":Like,
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
      width: 6.7rem;
    }
    a.react {
      display: block;
      color: #fff;
      height: 100%;
     
    }
    .navbar .nav-wrap-left  a.react{
       background: rgba(0,0,0,.1);
    }
    .navbar .nav-city {
      width: 4.7rem;
      line-height: 1.6rem;
      display: inline-block;
      vertical-align: middle;
      text-align: left;
      margin-right: 1rem;
      margin-left: 1rem;
      font-size: 1.6rem;
   }
   .icon-downarrow {
    position: relative;
   }
   .text-icon {
    font-family: base_icon;
    display: inline-block;
    vertical-align: middle;
    font-style: normal;
  }  
  .space {
      display: inline-block;
      width: .6rem;
  }
   .icon-downarrow:after {
    content: '';
    display: inline-block;
    position: relative;
    top: -.2em;
    width: .5em;
    height: .5em;
    border-bottom: .03rem solid #fff;
    border-left: .03rem solid #fff;
    transform: scaleX(0.7) rotateZ(-45deg);
   }

   .navbar .box-search {
    -webkit-box-flex: 1;
    -ms-flex: 1;
    flex: 1;
    border-radius: .3rem;
    background: rgba(0,0,0,.15);
    height: 3.2rem;
    line-height: 3.2rem;
    -webkit-box-sizing: border-box;
    position: relative;
    margin-top: 1rem;
}
 
.navbar .icon-search {
    width: 1.3rem;
    height: 3.2rem;
    position: absolute;
    left: 1rem;
    color: #fff;
    background: url(../../img/fdj.png) no-repeat center;
    background-size: contain;
}

.navbar .box-search span {
    font-size: 1.3rem;
    color: #68dbce;
    padding-left: 3rem;
    box-sizing: border-box;
    width: 100%;
    display: block;
}
.single-line {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
    .navbar .nav-wrap-right {
      height: 100%;
      width: 4.9rem;
      margin-right: .2rem;
    }


.nav-wrap-right a {
    display: inline-block;
    height: 100%;
    line-height: 5rem;
    text-align: center;
    width: 4.7rem;
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
    background: url(../../img/user.png) no-repeat center;
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
    div.list{
      background-color: #fff;
      width: 100%;
      height: 18rem;
      border-bottom: 1px solid #DDD8CE;
      position: relative;
      
    }
    .mint-swipe{
       width: 100%;
       height: 18rem;
       overflow:hidden;
    }
    .mint-swipe-item{
       width: 100%;
       height: 16.2rem;
     
    }
   /* .mint-swipe-indicator{
     background: rgba(0,0,0,.2);   
    }
   .mint-swipe .mint-swipe-indicator.is-active {
       background: #06c1ae;
    }*/
  
    .icon-list{
       width: 100%;
       height: 16.2rem;
       display: flex;
       flex-wrap: wrap;
       align-content: stretch;    
    }
    .icon-list li{
       display: flex;
       width: 20%;
       height: 8.1rem;    
       align-items:center;
       justify-content: center;
       flex-wrap: wrap;
       flex-direction:column;
    }
    .icon-list li a{
       display: block;
       width: 100%;
       height: 100%;
       text-align: center;
       display: flex;
       align-items:center;
       justify-content: center;
       flex-wrap: wrap;
       flex-direction:column;
    }





    .icon-list li span.new-icon-circle{
        margin-bottom: .7rem;
    }
    .icon-list li span{
       font-size: 1.2rem;
       line-height: 1.2rem;
       white-space: nowrap;
    }
    .daogou{
        border-top: 1px solid #DDD8CE;
        border-bottom: 1px solid #DDD8CE;
        margin-top: 1rem;
        background-color: #fff;
        height: 16rem;
    }
   
    .daogou .dd-padding {
      padding: .6rem;
      display: flex;
      overflow: hidden;
      text-align: center;
      width: 100%;
      height: 100%;
      box-sizing: border-box;
      display: -webkit-flex;
}
 .daogou .dd-padding .left{
    border-right: 1px solid #ddd8ce;
    width: 33.3%
 }
 .daogou .dd-padding .right{
    flex:1;
 }
 .daogou .info {
    vertical-align: top;
    width: 8.5rem;
    text-align: left;
    display: inline-block;
}
.daogou .info strong {
    display: block;   
    font-size: 1.5rem;
    line-height: 1.5rem;
    margin-top: 1.5rem;
    margin-bottom: 1.25rem;
}
.daogou .left .info strong{
  color: #55a40f;
  font-weight: 600;
}
small {
    color: #666;
    font-size: 1.2rem;
    line-height: 1.2rem;
}
.daogou .left .imgbox {
    width: 8.8rem;
    height: 8.8rem;
    margin: auto;
    display: block;
}
.daogou .left .imgbox img{
    width: 8.8rem;
    height: 8.8rem;
}
.daogou .top{
    border-bottom: 1px solid #ddd8ce;
    height: 7.3rem;
}
.daogou .right .info strong{
  color: #ff3f0d;
  font-weight: 600;
}
.daogou .right .imgbox{
    width: 6.5rem;
    height: 6.5rem;
    display: inline-block;
}
.daogou .right .imgbox img{
    width: 8.5rem;
    height: 6.5rem;
}
.daogou .right .bottom{
   display: flex;
   height: 7.4rem;
}
.daogou .right .bottom .react{
   flex: 1;

}
.daogou .bottom .info strong {
    margin: .6rem 0 0.7rem;
}
.daogou .scense .info strong {
    color: #f742a0;
}
.daogou .bottom .imgbox {
    width: 4rem;
    height: 4rem;
    display: block;
    margin:0 auto;
}
.daogou .bottom .imgbox img{
    width: 4rem;
    height: 4rem;
}
.daogou .right .bottom .mingdian{
   border-left: 1px solid #ddd8ce;
}
.daogou .mingdian .info strong {
    color: #ff8601;
}
.daogou .mingdian small {
    display: block;
    margin-bottom: 1rem;
    margin-top: 1rem;
}
.mingdian .time-panel{
  color: rgb(51, 51, 51);
  font-size: 1.4rem;
  text-align: left;
  line-height: 1.4rem;
}
.mingdian .time-panel .span-time {
    display: inline-block;
    background-color: #4C4C4C;
    width: 2rem;
    height: 1.5rem;
    border-radius: .3rem;
    text-align: center;
    line-height: 1.5rem;
    font-size: 1.2rem;
    color: #fff;
}





</style>