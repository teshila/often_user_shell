<template>
    <section class="charts">
        <h3 ><span @click="sendMsgToParent">【关闭窗口】</span>	{{code}}{{name}}</h3>
        <vue-highcharts   :highcharts="Highcharts" :options="options" ref="drilldownChart"></vue-highcharts> 
    </section>
</template>

<script>
import VueHighcharts from 'vue2-highcharts'
import Highcharts from 'highcharts/highstock'
import { data } from '../assets/stockData.js'

const stockData = data.map(d => d.slice(0.2))

export default {
	props:['code','name'],
  components: {
    VueHighcharts,
  },
	methods:{
		sendMsgToParent:function(){
			 this.$emit('listenChildEvent','close');
		}
		
		
	},
  data() {
    return {
			isDisplay:false,
      Highcharts: Highcharts,
      options: {
        rangeSelector: {
          selected: 2,
        },
        title: {
          text: '平安银行历史股价',
        },
        plotOptions: {
          series: {
            showInLegend: true,
          },
        },
        tooltip: {
          split: false,
          shared: true,
        },
        series: [
          {
            // type: 'line',
            id: '000001',
            name: '平安银行',
            data: stockData,
          },
        ],
      },
    }
  },
}
</script>
<style scoped="scoped">
	h3{
		font-family: "宋体";
		font-size: 12px;
	}
	
</style>
//https://github.com/superman66/vue-highcharts/blob/0a2ac236b0a31a617576d4ddb1f852cfa923db18/README_zh.md
//https://www.jianshu.com/p/8cd08866fbc8