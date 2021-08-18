import Vue from 'vue'
import store from '../store/index'
import Route from '/src/router/index'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import admin from '../views/admin.vue'
import BoardList from '../views/BoardList.vue'
import BoardWrite from '../views/BoardWrite'
import UserDetail from '../views/UserDetail'
import BoardDetail from '../views/BoardDetail'
import BoardEdit from '../views/BoardEdit'
import BoardReply from '../views/BoardReply'
import HotItems from '../views/HotItems'
import BwBar from '../components/BwBar'
import ProductInsert from '../views/ProductInsert'
import LatestItems from '../views/LatestItems'
import ItemDetail from '../views/ItemDetail'
import PutCart from '../views/PutCart'
import ProductRank from '../views/ProductRank'
import MultiUploadTest from '../views/MultiUploadTest'
import BuyProduct from '../views/BuyProduct'
import OrderList from '../views/OrderList'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
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
    component: BoardList,
    beforeEnter(to, from, next){
      if(store.state.isLogin === false){
        alert('로그인이 필요합니다.')
        Route.push('/login')
      } else {
        next()
      }
    }
  },
  {
    path: '/admin',
    name: 'admin',
    component: admin
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
    path: '/hotitems',
    name: 'HotItems',
    component: HotItems,
    props: true,
    beforeEnter(to, from, next){
      if(store.state.isLogin === false){
        alert('로그인이 필요합니다.')
        Route.push('/login')
      } else {
        next()
      }
    }
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
    props: true,
    beforeEnter(to, from, next){
      if(store.state.isLogin === false){
        alert('로그인이 필요합니다.')
        Route.push('/login')
      } else {
        next()
      }
    }
  },
  {
    path: '/itemdetail',
    name: 'ItemDetail',
    component: ItemDetail,
    props: true
  },
  {
    path: '/putcart',
    name: 'PutCart',
    component: PutCart,
    props: true,
    beforeEnter(to, from, next){
      if(store.state.isLogin === false){
        alert('로그인이 필요합니다.')
        Route.push('/login')
      } else {
        next()
      }
    }
  },
  {
    path: '/productrank',
    name: 'ProductRank',
    component: ProductRank,
    props: true,
    beforeEnter(to, from, next){
      if(store.state.Userinfo.User_auth[0] !== 'ROLE_ADMIN'){
        alert('관리자 아이디 이외에는 해당 도메인 접속이 불가합니다.')
        Route.push('/login')
      } else {
        next()
      }
    }
  },
  {
    path: '/multiuploadtest',
    name: 'MultiUploadTest',
    component: MultiUploadTest,
    props: true,
    beforeEnter(to, from, next){
      if(store.state.Userinfo.User_auth[0] !== 'ROLE_ADMIN'){
        alert('관리자 아이디 이외에는 해당 도메인 접속이 불가합니다.')
        Route.push('/login')
      } else {
        next()
      }
    }
  },
  {
    path: '/buyproduct',
    name: 'BuyProduct',
    component: BuyProduct,
    props: true
  },
  {
    path: '/orderlist',
    name: 'OrderList',
    component: OrderList,
    props: true
  },
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router