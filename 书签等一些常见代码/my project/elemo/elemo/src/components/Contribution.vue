

<template>
   <div>     
      <div class="contribution-container">
          <div class="aside">
              <span class="contribution-title">ESRC英雄榜及感谢名单</span>
              <img src="../../img/contribution.png" alt="" />
              <p class="contribution-content">
              	<span _v-433251dd="">ESRC 成立以来</span>   ，有
              	<span class="content-highlight">61</span> 位白帽子通过 ESRC 平台上报了
              	<span class="content-highlight">511</span> 个饿了么漏洞，ESRC 对这些白帽子的付出表示由衷的感谢！ 
              </p>
          </div>
          <div class="main">
          	<form> 
          		<label for="year-select" >年份</label> 
          		<select id="year-select"> 
          			<option  value="全部">全部</option>
          			<option  value="2016">2016</option>
          			<option  value="2017">2017</option> 
          		</select> 
          		<label for="month-select" >月份</label> 
          		<select id="month-select" > 
          			<option  value="全部">全部</option>
          		</select> 
          	</form>
          	<div class="d-grid ">
          		<table cellspacing=0 cellpadding=0>
          			<thead>
          				<tr>
          					<th></th>
          					<th>排名</th>
          					<th>团队</th>
          					<th>贡献值</th>
          				</tr>
          				<tr>
          					<td>1</td>
          					<td>x</td>
          					<td>233</td>
          					<td>1290</td>
          				</tr>
          				<tr>
          					<td>2</td>
          					<td>Graves</td>
          					<td>网络攻防小组</td>
          					<td>1104</td>
          				</tr>
          			</thead>
          		</table>
          	</div>
          </div>
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
  .contribution-container{
    width: 100%;
    margin: 0 auto;
    padding: 100px 0 30px;
    box-sizing: border-box;
    overflow: hidden;
}
.aside {
    float: left;
    width: 420px;
}


.contribution-title{
    display: inline-block;
    width: 262px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: 20px;
    color: #fff;
    background-color: #fc9317;
}
.contribution-container img{
    display: block;
    width: 80%;
    margin-top: 30px;
}
.contribution-content {
    font-size: 16px;
    color: #343434;
}
.contribution-content .content-highlight{
    font-size: 20px;
    color: #fc9317;
}
.main{
    margin-left: 450px;
}
.main form {
    height: 30px;
}
.main label {
    margin-right: 8px;
    color: #666;
}
.main select{
    width: 250px;
    height: 30px;
    background-color: #fff;
    border: 1px solid #888;
    margin-right: 50px;
    outline: none;
}
option {
    font-weight: normal;
    display: block;
    white-space: pre;
    min-height: 1.2em;
    padding: 0px 2px 1px;
}
.main .d-grid {
    margin-top: 20px;
}
table{
	width: 648px;
	border: 1px solid #ccc;	
}
table tr{
	height: 40px;
}
table tr th{
	width: 195px;
    background-color: #fd566b;
    color: #fff;
    text-align: center;
    border-right: 1px solid #e7eaec;
}
table tr th:nth-child(1),table tr td:nth-child(1){
	width: 65px;
}
table tr td{
	width: 195px;
    background-color: #fff;
    color: #333;
    text-align: center;
    border-right: 1px solid #e7eaec;
}
table tr td:nth-child(2){
	color: #009FFF;
}

</style>