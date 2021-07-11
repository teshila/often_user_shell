<template>
  <div id="app">
      <router-view></router-view>
  </div>
</template>

<script>
 

export default {
  name: 'app',
  data () {
    return {
      
    }
  },
  methods:{
     
  }
}
</script>

<style>
html,body,ul,li,ol,dl,dd,dt,p,h1,h2,h3,h4,h5,h6,form,img{margin:0;padding:0;}
ul,ol,li{list-style:none;}
a,u{text-decoration:none;color: #000;}
img{border:0;display:block;}
.clearfix:after{content:".";display:block;clear:both;height:0;overflow:hidden;visibility:hidden;}
h1,h2,h3,h4,h5,h6{font-weight:100;font-size:16px;}
em,i{font-style:normal;}
b,strong{font-weight:100;}
.fl{float: left;}
.fr{float: right;}
html{
  font-size: 62.5%;
}
body{
     color: #333;
     background: #f0efed;
  }
      
</style>
