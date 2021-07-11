import Vue from 'vue'
import App from './App.vue' 
import vueResource from "vue-resource"
import VueRouter from "vue-router"

Vue.use(vueResource)
Vue.use(VueRouter)

import Home from "./components/Home.vue";
import Loophole from "./components/Loophole.vue";
import Notice from "./components/Notice.vue";
import Contribution from "./components/Contribution.vue";
import Gift from "./components/Gift.vue";
import Content from"./components/Content.vue";

const routes = [
  { path: '/home', component: Home },
  { path: '/loophole', component: Loophole },
  { path: '/notice', component: Notice },
  { path: '/contribution', component: Contribution },
  { path: '/gift', component: Gift },
  { path: '/content/:aid', component:Content },
  { path: '*', redirect:'/home'}
]

  const router = new VueRouter({
	  routes // 锛堢缉鍐欙級鐩稿綋浜?routes: routes
	})

   const app = new Vue({
                 el:'#app',
                  router,
                 render: h => h(App)   //注册组件
                })

// new Vue({
//   el: '#app',
//   render: h => h(App)
// })
                                                                                  