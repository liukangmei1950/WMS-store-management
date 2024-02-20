import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
/*  {
    path: '/',
    name: 'home',
    component: HomeView
  },*/
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: function () {
      return import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
  },
  {
    path: '/',
    name:"login",
    component: function () {
      return import('../components/Login')
    }
  },
  {
    path: '/index',
    name:"index",
   // component:()=>import('../components/index'),
    component: function () {
      return import('../components/index')
    },
    children:[
      {
        path: '/Home',
        name:"home",
        meta:{
          title:'首页'
        },
        component: function () {
          return import('../components/Home')
        }
      },
      {
        path: '/Admin',
        name:"admin",
        meta:{
          title:'管理员管理'
        },
        component: function () {
          return import('../components/admin/AdminManage')
        }
      },
      {
        path: '/User',
        name:"user",
        meta:{
          title:'用户管理'
        },
        component: function () {
          return import('../components/user/UserManage.vue')
        }
      }
    ]


  }

]

const router = new VueRouter({
  mode:'history',
  routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
export default router
