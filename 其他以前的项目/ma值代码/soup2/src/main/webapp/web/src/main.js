// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import axios from 'axios'
Vue.prototype.$ajax = axios

import '../static/bt/css/bootstrap.min.css'
import '../static/mycompone.css'
import '../static/common.css'

import common from './common/api.js';
Vue.prototype.baseURL = common;

import mycookie from './common/cookieUtil.js';
Vue.prototype.cookie = mycookie;



import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'

Vue.use(MintUI)

//https://segmentfault.com/a/1190000011275595
//https://blog.csdn.net/fiona_lms/article/details/80075227
//https://www.jb51.net/article/119692.htm
/*import {getCookie} from './common/cookieUtil.js'*/
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
})

//https://www.cnblogs.com/xiaohuochai/p/7372665.html
//https://www.cnblogs.com/hai-cheng/p/7813562.html
//https://www.jianshu.com/p/a3721fe0605a
//https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&rsv_idx=1&tn=baidu&wd=vue%20%E5%85%AC%E5%85%B1%E6%96%B9%E6%B3%95&oq=document.cookie%2520%25E8%25AE%25BE%25E7%25BD%25AE&rsv_pq=aa198390000426e9&rsv_t=0487Y5I%2BUJIj3%2BK8780KUqhBF48w9NSAuFVO0YGIafSRRW3hRpxT1lemOz8&rqlang=cn&rsv_enter=1&inputT=3332&rsv_sug3=20&rsv_sug1=20&rsv_sug7=100&rsv_sug2=1&prefixsug=vue%2520gong&rsp=0&rsv_sug4=3332