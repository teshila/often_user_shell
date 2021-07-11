

<template>
   <div>
       <div class="gift-list-container">
          <ul>
             <li v-for="item in list">
                <img :src='item.src' alt=""  />
                <div class="gift-info"> 
                	<p>京东卡{{item.title}}</p> 
                	<p>需要饿币: 
                		<span>{{item.num1}}</span>
                	</p> 
                	<p >已兑换: 
                		<span>{{item.num2}}</span> 个
                	</p>
                	<p>还有库存 
                		<span>{{item.num3}}</span> 件
                	</p> 
                </div>
             </li>
          </ul>
       </div>
       <v-footer></v-footer>
   </div>
</template>

<script>
    import Footer from './Footer.vue'
    export default{
        data(){
           return {
              list:[
                     {
                     	src:'../../img/xhloz.jpg',
                     	title:"京东卡200",
                     	num1:20,
                     	num2:17,
                     	num3:16
                     },
                     {
                     	src:'../../img/eksku.jpg',
                     	title:"京东卡500",
                     	num1:50,
                     	num2:16,
                     	num3:0
                     },
                     {
                     	src:'../../img/heiqo.jpg',
                     	title:"京东卡1000",
                     	num1:100,
                     	num2:10,
                     	num3:0
                     },
                     {
                     	src:'../../img/xswzc.jpg',
                     	title:"饿了么玩偶1个",
                     	num1:4,
                     	num2:5,
                     	num3:48
                     },
                     {
                     	src:'../../img/zqpiv.jpg',
                     	title:"饿了么书包(2016纪念版)",
                     	num1:7,
                     	num2:5,
                     	num3:12
                     },
                     {
                     	src:'../../img/pbpks.jpg',
                     	title:"现金100元",
                     	num1:10,
                     	num2:148,
                     	num3:0
                     },
                     {
                     	src:'../../img/efedh.png',
                     	title:"饿了么会员卡（月卡）",
                     	num1:2,
                     	num2:11,
                     	num3:39
                     },
                     {
                     	src:'../../img/njfwb.jpg',
                     	title:"欧莱雅水能保湿温润乳+控油炭爽净亮洁面膏",
                     	num1:11,
                     	num2:0,
                     	num3:10
                     },
                     {
                     	src:'../../img/raaqm.jpg',
                     	title:"韩国可莱丝针剂水库面膜10片",
                     	num1:10,
                     	num2:0,
                     	num3:21
                     },
                     {
                     	src:'../../img/qaked.jpg',
                     	title:"500元中银通卡",
                     	num1:50,
                     	num2:0,
                     	num3:8
                     },
                     {
                     	src:'../../img/lapdq.jpg',
                     	title:"1000元中银通卡",
                     	num1:100,
                     	num2:0,
                     	num3:8
                     }
              
              ]
           }
        },
         components:{
        	"v-footer":Footer
        }
        

    }
</script>
<style scoped>
*{
	margin: 0;
	padding: 0;
}
ul,li{
	list-style: none;
}
.gift-list-container {
    padding-top: 90px;
    margin: 0 auto;
    overflow: hidden;
}
.gift-list-container ul li {
    float: left;
    width: 25%;
    text-align: center;
    cursor: pointer;
}
.gift-list-container ul li img {
    width: 176px;
    height: 176px;
    border-radius: 10px;
}

.gift-info p {
    font-size: 14px;
    line-height: 16px;

}
.gift-info p:first-child {
    font-size: 18px;
    line-height: 20px;
    margin-bottom: 10px;
}
.gift-info span{
    color: #ffab7e;
}
</style>