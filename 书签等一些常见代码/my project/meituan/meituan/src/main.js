import Vue from 'vue'
import App from './App.vue' 
import VueRouter from "vue-router"
import vueResource from "vue-resource"
Vue.use(vueResource)

Vue.use(VueRouter)

import Home from "./components/Home.vue";
import Content from "./components/Content.vue";

import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'

Vue.use(MintUI);

import { Swipe, SwipeItem } from 'mint-ui';

Vue.component(Swipe.name, Swipe);
Vue.component(SwipeItem.name, SwipeItem);


const routes = [
  { path: '/home', component: Home },
  { path: '/content', component: Content },
  { path: '*', redirect:'/home'}
]

  const router = new VueRouter({
	  routes // routes: routes
	})

   const app = new Vue({
                 el:'#app',
                  router,
                 render: h => h(App)   //注册组件
                })


                                                                                  