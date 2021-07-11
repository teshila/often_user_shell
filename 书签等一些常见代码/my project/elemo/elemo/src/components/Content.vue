<template>
    <div>
        <div class="notice-container">
           <h2>{{article.title}}</h2>


			<div v-html="article.content">
				
			</div>
        </div>
    </div>
</template>
<script>
   export default{

        data(){

			return{
				article:''
			}
		},methods:{
			getData(){

				var _that=this;

				var aid=_that.$route.params.aid;

				var  url='http://www.phonegap100.com/appapi.php?a=getPortalArticle&aid='+aid;

				_that.$http.jsonp(url).then(function(res){

						//console.log(JSON.parse(res.bodyText));

						var articel=JSON.parse(res.bodyText)

						_that.article=articel.result[0];

				})


			}
		},
		mounted(){

				this.getData();
		}

   }
</script>
<style scoped>
    .notice-container{
	    width: 65%;
	    margin: 0 auto;
	    padding: 100px 0 30px;
	    font-size: 18px;
	    color: #1e1e1e;
	    box-sizing: border-box;
}
.notice-container h2 {
    text-align: center;
    font-size: 32px;
    color: #0068b7;
}
</style>