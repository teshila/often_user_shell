

<template>
   <div>
        <div class="content">
            <ul>
                <li v-for="item in list">
                	<router-link :to="'/content/'+item.aid">{{item.title}}</router-link>
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
             list:[]
           }
        },
        methods:{
            getData(){
                var that=this
                var url="http://www.phonegap100.com/appapi.php?a=getPortalList&catid=20&page=1"
                this.$http.jsonp(url).then(function(res){
                     //console.log(res.body.result)
                     that.list=res.body.result

                }) 

            }
        },
        components:{
        	"v-footer":Footer
        },
        mounted:function(){
           this.getData();
       }

    }
</script>
<style scoped>
   ul,li{
   	list-style: none;
   }
   a{
   	 text-decoration: none;
   }
   .content{
      padding-top: 100px;
      padding-bottom: 20px;
      width: 800px;
      margin: 0 auto;
      box-sizing: border-box;
   }
   .content ul{
   	  margin:0 auto 20px;
   }
   .content ul li{
   	text-align: left;
    font-size: 16px;
    cursor: pointer;
    line-height: 42px;
    border-bottom: 1px solid #e2e2e2;
   }
   .content ul li a{
   	 color: #333;
   }
  
</style>