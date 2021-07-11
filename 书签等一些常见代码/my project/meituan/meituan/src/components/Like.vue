<template>
   <div>
     <div class="guess-like">
         <div class="list">
             <div class="list-in">
                 <div class="store-list">
                     <h3>猜你喜欢</h3>
                     <ul class="list1">
                         <li v-for="item in list">
                            <a href="#" class="react">
                                <div class="dealcard">
                                   <div class="dealcard-img">
                                      <img :src="item.img" alt="" />
                                   </div>
                                   <div class="dealcard-block-right">
                                       <div class="dealcard-brand single-line">{{item.name}}</div>
                                       <div class="title text-block">{{item.introduce}}</div>
                                       <div class="price">
							            <span class="strong">{{item.price}}</span>
							            <span class="strong-color">元</span>
							                <del>{{item.del}}元</del>
								            <span class="line-right">
								                    已售{{item.sale}}
								            </span>
							         </div>
                                   </div>
                                </div>
                            </a>
                         </li>
                     </ul>
                 </div>                 
             </div>
             <div class="db">
                 <a class="react">
                    <div class="more">查看全部团购</div>
                 </a>
             </div>
         </div>
     </div>
   </div>
  
</template>
<script>
  export  default{
      data(){
      	return  {
      		list:[]
      	}
      },
      methods:{
            getData(){
                var that=this
                var url="../src/store.json"
                this.$http.get(url).then(function(res){
                     console.log(res)
                     that.list=res.body.data1

                }) 

            }
        },
        mounted:function(){
           this.getData();
       }

  }


</script>
<style scoped>
   .guess-like {
    border-top: 1px solid #DDD8CE;
    border-bottom: 1px solid #DDD8CE;
    margin-top: 1rem;
    background-color: #fff;
    min-height: 10rem;
    position: relative;
}
 .list{
 	width: 100%;
 	height: 100%;
 	border-bottom: 1px solid #DDD8CE;
 }
 .list-in{
    overflow: hidden;
    position: relative;
 }
 .store-list{
 	padding-left: 1rem;
 }
 .store-list h3{
 	font-size: 1.7rem;
    padding: 1.4rem 0 1rem;
    color: #333;
    border-bottom: 1px solid #DDD8CE;
 }
 .list1 li{
    border-bottom: 1px solid #DDD8CE;
    overflow: hidden;
    position: relative;
 }
.list1 li a.react{
	padding: 1.4rem 1rem 1rem 0;
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
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    color: #333;
}
.dealcard .title{
    font-size: 1.2rem;
    height: 3.4rem;
    margin-bottom: 0.8rem;
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
.db{
    height: 4rem;
    line-height: 4rem;
    font-size: 1.5rem;
}
.db>.react {
    color: #06c1ae;
    padding:0 1rem;
    width: 100%;
    box-sizing: border-box;
    display: block;
    height: 100%;
}
.more {
    position: relative;
    padding-right: 1.5rem;
    width: 100%;
    box-sizing: border-box;
    font-size: 1.5rem;
}
.more:after {
    position: absolute;
    right: .05rem;
    top: 50%;
    display: block;
    content: '';
    width: .6em;
    height: .6em;
    border-left: .04rem solid #06c1ae;
    border-bottom: .04rem solid #06c1ae;
    -webkit-transform: translateY(-50%) scaleY(0.7) rotateZ(-135deg);
    -moz-transform: translateY(-50%) scaleY(0.7) rotateZ(-135deg);
    -ms-transform: translateY(-50%) scaleY(0.7) rotateZ(-135deg);
}
</style>
