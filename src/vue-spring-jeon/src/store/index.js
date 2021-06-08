import Vue from 'vue'
import Vuex from 'vuex'
import Route from '../router/index'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'


Vue.use(Vuex)

export default new Vuex.Store({
  plugins:[
    createPersistedState()
  ],
  
  state: {
    Userinfo:
    {
      User_Id:null,
      User_Name:null,
      User_auth:null,
      User_token:null
    },
    boardlist:[],
    board_detail:[],
    UserList:[],
    isLogin: false,
    isLoginError: false,
  },
  mutations: {
    NewUsers: (state,payload) => {
      state.UserList.push(payload)
      Route.push("/login")
    },
    SET_USER(state, data) {
      state.Userinfo.User_Id = data.username
      state.Userinfo.User_Name = data.name
      state.Userinfo.User_auth = data.roles
      state.Userinfo.User_token = data.token
      Route.push("/user")
   },
   READ_USER_LIST(state,data) {
    state.UserList = data
   },
   INSERT_TOKEN(state) {
     state.Userinfo.User_token = localStorage.getItem("token")
   },
   SET_USER_REFRESH(state,data) {
    state.Userinfo.User_Id = data.username
    state.Userinfo.User_Name = data.name
    state.Userinfo.User_auth = data.authorities
    state.Userinfo.User_token = data.token
   },
   logout(state) {
    state.Userinfo.User_Id = null
    state.Userinfo.User_Name = null
    state.Userinfo.User_auth = null
    state.Userinfo.User_token = null
    state.isLogin = false,
    state.isLoginError = false
    localStorage.removeItem("token")
    console.log(state.Userinfo)
    console.log("로그아웃됐니?"+localStorage.getItem("token"))
    Route.push('/login')
   },
   loginSuccess(state){
    state.isLoginError = false
    state.isLogin = true
   },
   loginError(state){
     state.isLogin = false
     state.isLoginError = true
   },

  },
  actions: {
    loginProcess({ commit }, payload) {
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/auth/signin', payload)
              .then(Response => {
                  console.log(Response.data)
                  //console.log(Response.data.roles[0])
                  if (Response.data.username != null) {
                      axios.defaults.headers.common['Authorization'] = `Bearer ${Response.data.token}`
                      localStorage.setItem("token",Response.data.token)
                      commit('SET_USER', Response.data)  
                      commit('loginSuccess')    
                  }
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
                  commit('loginError')
              })
      })
   },
   SignUp({commit},payload) {
    console.log(payload)
    return new Promise((resolve, reject) => {
        axios.post('http://localhost:9000/api/auth/signup', payload)
            .then(Response => {
                console.log(Response.data)
                if(Response.data === "success") {
                  Route.push("/login")
                }
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
                alert("아이디가 중복")
                Route.push("/signup")
            })
    })
   },
  admin({commit,state}) {
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
      axios.get('http://localhost:9000/api/admin/adminPage')
          .then(Response => {
            console.log(Response.data)
             commit('READ_USER_LIST',Response.data)
          })
          .catch(Error => {
            // console.log(Error)
              console.log('admin_error')
              Route.push("/")
          })
      })
  },
  UnpackToken({commit}) {
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`
      axios.get('http://localhost:9000/api/auth/unpackToken')
          .then(Response => {
            console.log(Response.data)
            commit('SET_USER_REFRESH',Response.data)
          })
          .catch(Error => {
            console.log(Error)
              console.log('unpackToken_error')
          })
    })
  },
}
})