<template>
   <div>
   	 
       <div class="deal-container">
       	   <div class="max" v-show="ifshow"></div>
           <div class="list" v-for="item in list">
                <div class="poi-list-item comm">
                    <a class="react">
                        <span class="poiname">{{item.title}}</span>
                        <span class="dealtype-icon">团</span>
                        <span class="dealtype-icon dealcard-waimai">外</span>
                        <div class="kv-line-r">
                           <h6>
                              <span class="stars">
                                 <i class="text-icon icon-star"></i>
                                 <i class="text-icon icon-star"></i>
                                 <i class="text-icon icon-star"></i>
                                 <i class="text-icon icon-star"></i>
                                 <i class="text-icon icon-star"></i>
                                 <em class="star-text">5.0</em>
                              </span>
                              <span class="gg">广告</span>
	                      </h6>
                        </div>
                    </a>
                    <p>
	        		   <span></span>
	                   <a>{{item.place}}</a>
	       	        </p>
                </div>
                <div class="store-detail comm" v-for="i in item.content">
                    <div class="list1">
                        <div class="list-detail">
                            <a class="goods">
                               <div class="dealcard dealcard-poi">
                                   <div class="dealcard-img">
                                      <img :src="i.img" alt="" />
                                   </div>
                                   <div class="dealcard-block-right">
                                       <div class="title text-block">{{i.name}}</div>
                                       <div class="price">
							            <span class="strong">{{i.price}}</span>
							            <span class="strong-color">元</span>
							                <del>{{i.del}}</del>
								            <span class="line-right">
								                    已售{{i.sale}}
								            </span>
							         </div>
                                   </div>
                               </div>
                            </a>
                        </div>
                    </div>
                </div>
                <dd class="db">
            		<a class="react loadmore">
            			<div class="more more-down">查看其他{{item.num}}条团购</div>
            		</a>
               	</dd>
           </div>
           <div class="list">
           	   <div class="pager">
					<a class="btn btn-weak btn-disabled">上一页</a>
					    <span class="pager-current">1</span>
					<a class="btn btn-weak">下一页</a>
				</div>
           	
           </div>
       </div>
   </div>

</template>
<script>
   export  default{
      data(){
      	return  {
      		list:[ 	
                {
                   	"id": "1",
	                "title":"幸福西饼蛋糕（购物公园店）",
	                "place":"CBD中心区",
	                "num":38,
	                "content":
	                [
	                      {					
							"img": "img/d1.jpg",
							"name":"蛋糕2选1,约2磅，方形,百分百新鲜制作，2小时免费配送",
							"price":"159",
							"del":"门市价：256",
							"sale":"336"
						},
						{
							"img": "img/d2.jpg",
							"name":"提拉米苏1个，约4磅，方形",				
							"price":"248",
							"del":"门市价：432",
							"sale":"108"
						}
	                ]	

                },
                {
                   	"id": "2",
	                "title":"真功夫（集信店）",
	                "place":"松岗",
	                 "num":17,
	                "content":
	                [
	                      {					
							"img": "img/d3.jpg",
							"name":"排骨拼鸡腿肉饭	+	西兰花	+	四季猪骨汤	+	Q弹鱼饼1份",
							"price":"30.5",
							"del":"门市价：44",
							"sale":"15247"
						},
						{
							"img": "img/d3.jpg",
							"name":"新枸杞红枣露1份",				
							"price":"6.5",
							"del":"门市价：8",
							"sale":"1023"
						}
	                ]	

                },
                {
                   	"id": "3",
	                "title":"幸福西饼蛋糕（布心店）",
	                "place":"布心/太白路",
	                 "num":29,
	                "content":
	                [
	                      {					
							"img": "img/d5.jpg",
							"name":"蛋糕2选1,约2磅",
							"price":"168",
							"del":"门市价：276",
							"sale":"788"
						},
						{
							"img": "img/d6.jpg",
							"name":"盛夏光年1个，约6磅，圆形,生日蛋糕，百分百新鲜制作",				
							"price":"298",
							"del":"门市价：576",
							"sale":"686"
						}
	                ]	

                }, 
                 {
                   	"id": "4",
	                "title":"红荔村肠粉（欧景店）",
	                "place":"龙岗中心城",
	                 "num":2,
	                "content":
	                [
	                      {					
							"img": "img/d7.jpg",
							"name":"600元代金券1张，可叠加",
							"price":"510",
							"del":"门市价：600",
							"sale":"691"
						},
						{
							"img": "img/d7.jpg",
							"name":"400元代金券1张，可叠加",				
							"price":"340",
							"del":"门市价：400",
							"sale":"1179"
						}
	                ]	

                }, 
                {
                   	"id": "5",
	                "title":"御蝶坊（西荟城店）",
	                "place":"沙井",
	                 "num":17,
	                "content":
	                [
	                      {					
							"img": "img/d8.jpg",
							"name":"榴莲相思1个，约2磅，方形",
							"price":"138",
							"del":"门市价：188",
							"sale":"513"
						},
						{
							"img": "img/d9.jpg",
							"name":"夏日抹茶蛋糕1个，约2磅，方形",				
							"price":"138",
							"del":"门市价：188",
							"sale":"352"
						}
	                ]	

                }, 
                {
                   	"id": "6",
	                "title":"老乡吧农家菜",
	                "place":"华南城/奥特莱斯",
	                 "num":8,
	                "content":
	                [
	                      {					
							"img": "img/d11.jpg",
							"name":"聚餐，建议10人使用",
							"price":"298",
							"del":"门市价：607",
							"sale":"3"
						},
						{
							"img": "img/d12.jpg",
							"name":"功夫烤鱼，建议2人使用",				
							"price":"48",
							"del":"门市价：67",
							"sale":"225"
						}
	                ]	

                },
                {
                   	"id": "7",
	                "title":"汉拿山(怡景中心城店)",
	                "place":"CBD中心区",
	                 "num":8,
	                "content":
	                [
	                      {					
							"img": "img/d18.jpg",
							"name":"110元代金券1张，可叠加",
							"price":"99",
							"del":"门市价：110",
							"sale":"3"
						},
						{
							"img": "img/d19.jpg",
							"name":"50元代金券1张，可叠加4张用",				
							"price":"38",
							"del":"门市价：50",
							"sale":"225"
						}
	                ]	

                }
												
			],
      	    ifshow:false
      	}
      }

  }
</script>
<style scoped>
 .deal-container {
    position: relative;
   }
   .max{
   	width: 100%;
   	height: 100%;
   	margin-top: -1rem;
   	position: absolute;
   	left: 0;
   	top: 0;
   	bottom: 0;
   	right: 0;
   	background: rgba(0,0,0,.5);
   	z-index: 10;
   }
   
 div.list {
    border-top: 1px solid #DDD8CE;
    border-bottom: 1px solid #DDD8CE;
    margin-top: 1rem;
    background-color: #fff;
}
div.list:first-child {
    margin: 0;
    border-top: 0;
}
div.list .poi-list-item{
	border-bottom: 1px solid #DDD8CE;
}
 div.list .comm {
    margin: 0;   
    overflow: hidden;
    font-weight: 400;
    position: relative;
}
div.list .poi-list-item>.react {
	display: block;
    padding: 1rem;
    height: 3.7rem;

}
.poi-list-item .poiname{
    font-size: 1.5rem;
    line-height: 1.5rem;
}
.dealtype-icon {
    display: inline-block;
    background-color: #06c1ae;
    color: #fff;
    font-size: 1.2rem;
    padding: 0.25rem;
    border-radius: .3rem;
    vertical-align: text-bottom;
    line-height: 1.2rem;
}
.dealtype-icon.dealcard-waimai {
    background-color: #e95200;
}
.kv-line-r{
	margin-top: .5rem;
}
.poi-list-item h6{
    overflow: hidden;
    text-overflow: ellipsis;
    margin-right: 1rem;
    display: block;
    margin:-0.28rem 0;
}
.stars {
    display: inline-block;
    color: #f90;
}
.stars i {
	display: inline-block;
	height: 2rem;
	width: 1.5rem;
    margin-right: .28rem;
    vertical-align: 0rem;

}
.text-icon {
	font-family: base_icon;   
}
.text-icon.icon-star:after {
    content: '★';
}
.stars em {
    margin-left: .55em;
    vertical-align: middle;
    font-size: 1.4rem;
    line-height: 1.05rem;
}
.gg{
    border: 0.1rem solid #ccc;
    color: #888;
    display: inline-block;
    padding: 0 0.5rem;
    font-size: 1.1rem;
    line-height: 1.75rem;
    border-radius: 0.3rem;
}
.poi-list-item p {
    font-size: 1.2rem;
    color: #666;
    position: absolute;
    right: 1rem;
    bottom: 1rem;
    line-height: 1.7rem;
    text-align: justify;
    margin: -.24em 0;
}
.poi-list-item p a{
	color: #666;
}
.list1{
	padding-left: 1rem;
}
.list1 .list-detail{
    border-bottom: 1px solid #DDD8CE;
    overflow: hidden;
    position: relative;
}
 .list .store-detail:last-child .list-detail{
	border:0;
}
.goods{
	height: 7.4rem;
	padding:1.4rem 1rem 1.4rem 0;
	display: block;
}

.dealcard{
	margin:-0.4rem 0;
	box-sizing: border-box;
	display: flex;
}
.dealcard-img{
    width: 9rem;
    height: 8.2rem;
    text-align: center;
    overflow: hidden;
    margin-right:1rem;
}
.dealcard-img img{
	width: 9rem;
    height: 8.2rem;
}
.dealcard-block-right{
	position: relative;
	flex: 1
}
.dealcard .dealcard-brand{
    padding-top: .5rem;
    margin-bottom: .6rem;
    font-size: 1.5rem;
}
.single-line {
    color: #333;
}
.dealcard .title{
    font-size: 1.5rem;
    height: 4rem;
    margin-bottom: 1.6rem;
    color: #666;
    text-overflow: ellipsis;
    overflow: hidden;
}
.dealcard .price {
    position: absolute;
    width: 100%;
    bottom: 0;
    color: #666;
}
.dealcard .price .strong{
	font-size: 1.9rem;
	line-height: 1.9rem;
    color: #06c1ae;
}
.dealcard .price>.strong-color{
    font-size: 1.3rem;
    line-height: 1.3rem;
    color: #06c1ae;
}
.dealcard .price del {
    font-size: 1.2rem;
    line-height: 1.2rem;
    text-decoration: none;
}
.dealcard .line-right{
    position: absolute;
    top: .2rem;
    right: 0;
    font-size: 1.2rem;
    line-height: 1.2rem;
}

div.list .db {
    height: 4rem;
    line-height: 4rem;
    font-size: 1.5rem;
}
div.list .db>.react {
    color: #06c1ae;
    padding: 0 1rem;
    display: block;
}
.loadmore .more {
    padding-right: 2.25rem;
    display: inline-block;
    width: auto;
     position: relative;
}
.loadmore {
    text-align: center;
   
    box-sizing:border-box;
}
.more:after {
    position: absolute;
    right: .25rem;
    top: 50%;
    display: block;
    content: '';
    width: .7rem;
    height: .7rem;
    border-left: .2rem solid #06c1ae;
    border-bottom: .2rem solid #06c1ae;
    -webkit-transform: translateY(-50%) scaleY(0.7) rotateZ(-45deg);
    -moz-transform: translateY(-50%) scaleY(0.7) rotateZ(-45deg);
    -ms-transform: translateY(-50%) scaleY(0.7) rotateZ(-45deg);
}
.pager {
    padding: .4rem .5rem .4rem;
}
.btn.btn-disabled {
    background-color: #dcdcdc;
    color: #999;
    border: 0;
}
.btn-weak {
    border: .1rem solid #06c1ae;
    padding: 0 .75rem;
    background: 0;
    color: #06c1ae;
}
.btn {
    display: inline-block;
    margin: 0;
    text-align: center;
    height: 3rem;
    padding: 0 1.5rem;
    border-radius: .3rem;
    color: #fff;
    border: 0;
    background-color: #06c1ae;
    font-size: 1.4
    rem;
    vertical-align: middle;
    line-height: 3rem;
    box-sizing: border-box;
    cursor: pointer;
}
.pager-current {
    color: #666;
    display: inline-block;
    width: 1.1rem;
    text-align: center;
}

</style>