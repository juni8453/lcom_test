import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import BoardList from '../views/BoardList.vue'
import SignUp from '../views/SignUp.vue'
import User from '../views/User.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: () => import( '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import( '../views/Login.vue')
  },
  {
    path: '/boardlist',
    name: 'BoardList',
    component: () => import( '../views/BoardList.vue')
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: () => import( '../views/SignUp.vue')
  },
  {
    path: '/user',
    name: 'User',
    component: () => import( '../views/User.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
