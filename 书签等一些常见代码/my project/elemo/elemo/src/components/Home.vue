<template>
    <div>
        <div class="lunbo">
            <img :src="src" alt="" />
           
        </div>
        <div class="icon-list">
             <div class="icon"> 
                <img :src="url1" alt="" />
                <p>通知公告</p>
             </div>
             <div class="icon">
                <img :src="url2" alt="" />
                <p>贡献榜</p>
             </div>
             <div class="icon">
                 <img :src="url3" alt="" />
                 <p>礼品兑换</p>
             </div>
        </div>
        <div class="content">
             <div class="box">
                <h2>致谢</h2>
                <p>截至目前，贡献最多的前 3 位白帽子是:</p>
                <p>x、Graves、。</p>
                <p class="contribution-gratitude">感谢他们对饿了么安全作出的贡献！</p>
                <img :src="url4" alt="" class="message" />
             </div>
             <div class="box">
                 <img :src="url5" alt="" class="wx"  />
                 <p>欢迎关注 ESRC 微信公众号</p>
                 <img :src="url6" alt="" class="upload-button"  />
                 <img :src="url7" alt="" class="load-button"  />
             </div>
        </div>
    </div>  
    
</template>
<script>



    export default{
       data(){
          return {
             src:"../../img/lunbo.png",
             url1:"../../img/tz.png",
             url2:"../../img/cn.png",
             url3:"../../img/gift.png",
             url4:"../../img/mes.png",
             url5:"../../img/wx.png",
             url6:"../../img/bg.png",
             url7:"../../img/dj.png",
          }
       },
       methods:{
          
       }
      

    }

</script>
<style scoped>
  .lunbo{
    width: 100%;
  }
   .lunbo img{
      width:100%;
      margin:0 auto;
   }
   .icon-list{
       width:100%;
       padding:50px 0 35px;
       text-align: center;
   }
   .icon{
      display:inline-block;
      margin:0 100px;
      cursor:pointer;
   }
   .icon img{
      width:80px;
      height:80px;
   }
   .content{
      width:100%;
      text-align:center;
      padding-bottom:10px;
   }
   .box{
      width:530px;
      height:256px;
      background:#f0f4f7;
      display: inline-block;
      position: relative;
      padding: 40px 24px 0;
      box-sizing: border-box;
      text-align: left;
      vertical-align: top;
   }
   .box:first-child {
       margin-right: 15px;
    }
   .box:nth-child(2) {
       padding: 70px 70px 0;
   }
   .box h2{
      color: #191919;
      font-size: 32px;
      font-weight: 100;
      line-height: 32px;
      margin:0;
      margin-bottom: 15px;
  }
  .box p{
      line-height: 28px;
      color: #848484;
      font-size: 16px;
      margin:0;
  }
  .box p:nth-child(3){
    color: #0068b7;
  }
  .message{
    width: 190px;
    position: absolute;
    top: 70px;
    right: 44px;
  }
  .box .wx{
    position: static;
    top: 70px;
    right: 44px;
    width: 172px;
    margin-left: 7px;
    margin-top: -40px;
  }
  .upload-button{
    width: 190px;
    position: absolute;
    top: 70px;
    cursor: pointer;
    right: 70px;
}
.load-button{
   top: 150px;
   cursor: pointer;
   right: 70px;
   width: 190px;
   position: absolute;
}
  
</style>


