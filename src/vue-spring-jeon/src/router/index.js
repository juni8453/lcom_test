import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import User from '../views/User.vue'
import admin from '../views/admin.vue'
import BoardList from '../views/BoardList.vue'
import TestPage from '../views/TestPage'
import BoardWrite from '../views/BoardWrite'
import UserDetail from '../views/UserDetail'
import BoardDetail from '../views/BoardDetail'
import BoardEdit from '../views/BoardEdit'
import BoardReply from '../views/BoardReply'
import BoardOption from '../views/BoardOption'
import HotItems from '../views/HotItems'
import SaleItems from '../views/SaleItems'
import BwBar from '../components/BwBar'
import ProductInsert from '../views/ProductInsert'
import LatestItems from '../views/LatestItems'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/boardlist',
    name: 'BoardList',
    component: BoardList
  },
  {
    path: '/user',
    name: 'User',
    component: User
  },
  {
    path: '/admin',
    name: 'admin',
    component: admin
  },
  {
    path: '/test',
    name: 'test',
    component: TestPage
  },
  {
    path: '/boardwrite',
    name: 'BoardWrite',
    component: BoardWrite
  },
  {
    path: '/userdetail',
    name: 'UserDetail',
    component: UserDetail
  },
  {
    path: '/boarddetail',
    name: 'BoardDetail',
    component: BoardDetail,
    props: true
     // params로 데이터 전달할 경우 props:ture 설정 필수
  },
  {
    path: '/boardedit',
    name: 'BoardEdit',
    component: BoardEdit,
    props: true
    // params로 데이터 전달할 경우 props:ture 설정 필수
  },
  {
    path: '/boardreply',
    name: 'BoardReply',
    component: BoardReply,
    props: true
  },
  {
    path: '/boardoption',
    name: 'BoardOption',
    component: BoardOption,
    props: true
  },
  {
    path: '/hotitems',
    name: 'HotItems',
    component: HotItems,
    props: true
  },
  {
    path: '/saleitems',
    name: 'SaleItems',
    component: SaleItems,
    props: true
  },
  {
    path: '/bwbar',
    name: 'BwBar',
    component: BwBar,
    props: true
  },
  {
    path: '/productinsert',
    name: 'ProductInsert',
    component: ProductInsert,
    props: true
  },
  {
    path: '/latestitems',
    name: 'LatestItems',
    component: LatestItems,
    props: true
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router