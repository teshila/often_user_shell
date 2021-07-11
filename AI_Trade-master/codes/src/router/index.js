import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
import login from '@/components/login'
import weiHu from '@/components/safe/weiHu'
import duanxian from '@/components/safe/duanxian'
import changxian from '@/components/safe/changxian'
import buy from '@/components/safe/buy'
import sell from '@/components/safe/sell'
import chart from '@/components/charts'

Vue.use(Router)

export default new Router({
  routes: [
		{
		  path: '/',
		  name: 'index',
		  component: index
		},
     {
      path: '/login',
      name: 'login',
      component: login
    },
    
    {
      path: '/weiHu',
      name: 'weiHu',
      component: weiHu,
      /* meta: { requiresAuth: true }*/
    },
    
     {
      path: '/duanxian',
      name: 'duanxian',
      component: duanxian
    },
    {
      path: '/changxian',
      name: 'changxian',
      component: changxian
    }, {
      path: '/buy',
      name: 'buy',
      component: buy
    },
    , {
      path: '/sell',
      name: 'sell',
      component: sell
    }, {
      path: '/chart',
      name: 'chart',
      component: chart
    }
  ]
	
})


//https://blog.csdn.net/Fabulous1111/article/details/78848971