import Vue from 'vue'
import Router from 'vue-router'
import index from '@/components/index'
import login from '@/components/login'
import weiHu from '@/components/safe/weiHu'
import buy from '@/components/safe/buy'
import specialBuy from '@/components/safe/specialBuy'
import tempBuy from '@/components/safe/tempBuy'
import sell from '@/components/safe/sell'
import zhouShouYang from '@/components/safe/zhouShouYang'
import zhouAndDayShouYang from '@/components/safe/zhouAndDayShouYang'
import shouYangOneList from '@/components/safe/shouYangOneList'
import shouYangTwoList from '@/components/safe/shouYangTwoList'
import shouYangThreeList from '@/components/safe/shouYangThreeList'
import tradingTwoDayUp from '@/components/safe/tradingTwoDayUp'
import holderDayUp from '@/components/safe/holderDayUp'
import setting from '@/components/safe/setting'
import email from '@/components/poster/email'
import sms from '@/components/poster/sms'


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
      path: '/zhouShouYang',
      name: 'zhouShouYang',
      component: zhouShouYang
    },
    {
      path: '/buy',
      name: 'buy',
      component: buy
    },
    {
      path: '/specialBuy',
      name: 'specialBuy',
      component: specialBuy
    },
		 {
		  path: '/tempBuy',
		  name: 'tempBuy',
		  component: tempBuy
		},
    {
      path: '/sell',
      name: 'sell',
      component: sell
    }
		
		, {
		  path: '/shouYangThreeList',
		  name: 'shouYangThreeList',
		  component: shouYangThreeList
		}, {
		  path: '/zhouAndDayShouYang',
		  name: 'zhouAndDayShouYang',
		  component: zhouAndDayShouYang
		},
		{
		  path: '/setting',
		  name: 'setting',
		  component: setting
		},
			
		{
		  path: '/shouYangOneList',
		  name: 'shouYangOneList',
		  component: shouYangOneList
		},
			{
		  path: '/sms',
		  name: 'sms',
		  component: sms
		},
			{
		  path: '/email',
		  name: 'email',
		  component: email
		},
			{
		  path: '/shouYangTwoList',
		  name: 'shouYangTwoList',
		  component: shouYangTwoList
		},
		{
		  path: '/tradingTwoDayUp',
		  name: 'tradingTwoDayUp',
		  component: tradingTwoDayUp
		},
		{
		  path: '/holderDayUp',
		  name: 'holderDayUp',
		  component: holderDayUp
		},
		
		
  ]
	
})


//https://blog.csdn.net/Fabulous1111/article/details/78848971

