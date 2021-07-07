
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
    commentlist:[],
    board_detail:[],
    UserList:[],
    board:[],
    Pagination:
    {
      page:null, // 현재 페이지
      lastPage:null,    // 총 페이지 사이즈
      // count:null,
      // startPage: null,
      // endPage: null,
      // nextPage:null,
      // prevPage:null,
      // pageNum:null
    },
    Show: false,
    isLogin: false,
    isLoginError: false,
    setImages: {},
    
  },
  mutations: {
    NewUsers: (state,payload) => {
      state.UserList.push(payload)
      Route.push("/login")
    },
    InitData(state){
      state.Show = false
    },
    SET_PAGINATION(state,payload){
      state.Pagination.page = payload
    },
    SET_USER(state, data) {
      state.Userinfo.User_Id = data.username
      state.Userinfo.User_Name = data.name
      state.Userinfo.User_auth = data.roles
      state.Userinfo.User_token = data.token
      Route.push("/")
   },
   READ_USER_LIST(state,data) {
    state.UserList = data.list
    state.Pagination = data.pagination
   },
   READ_BOARD_LIST(state,data) {
    state.boardlist = data.list
    state.Pagination = data.pagination
   },
   READ_COMMENT_LIST(state, data){
    state.commentlist = data.commentList
    state.Pagination = data.pagination
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
   SET_SHOW(state,data){
    state.Show = data
   },
   loginSuccess(state){
    state.isLoginError = false
    state.isLogin = true
   },
   loginError(state){
     state.isLogin = false
     state.isLoginError = true
   },
   SET_BOARD(state, data){
     state.board = data
   },
   SET_IMAGES(state, data){
    state.setImages = data
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
                console.log(payload)
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
  admin({commit,state}, payload) {
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
      axios.get(`http://localhost:9000/api/admin/adminPage/${payload.page}`)
          .then(Response => {
            console.log(payload) 
            console.log(Response.data)
            // console.log(Response.data.userlist)
            console.log(Response.data.list)
            console.log(Response.data.pagination)
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
  BoardList({commit, state}, payload){
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
      axios.get(`http://localhost:9000/api/auth/boardlist/${payload.page}`)
      .then(Response => {
        console.log(payload)
        console.log(Response.data)
        // console.log(Response.data.boardlist)
        console.log('boarlist data를 받았습니다')
        console.log(Response.data.list)
        console.log(Response.data.pagination)
        commit('READ_BOARD_LIST', Response.data)
        console.log('정상적으로 BoardList가 작동되었습니다')
      })
      .catch(Error => {
        console.log(Error)
        Route.push("/")
      })
    })
  },

  CommentPaginationList({commit, state}, payload){
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
      axios.get(`http://localhost:9000/api/auth/boarddetail/${payload.bId}/${payload.page}`)
      .then(Response => {
        console.log(Response.data)
        
        console.log('commentlist data를 받았습니다')
        console.log(Response.data.commentList)
        console.log(Response.data.pagination)

        commit('READ_COMMENT_LIST', Response.data)
        console.log('코멘트리스트')
        console.log(Response.data.commentList)
      })
      .catch(Error => {
        console.log(Error)
        Route.push("/")
      })
    })
  },

  BoardWrite({commit, state}, payload){
    console.log('BoardWrite Run')
    console.log('payload를 받았습니다')
    console.log(payload)

    // 파일 업로드 X
    if(payload.fileinput === null){
      return new Promise((resolve, reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
        axios.post('http://localhost:9000/api/auth/boardwrite', payload)
          .then(Response => {
            console.log(Response.data)
            if(Response.data === "success") {
              Route.push("/boardlist")
            }
        })
        .catch(Error => {
            console.log('error')
            reject(Error)
            alert("Error!")
            Route.push("/boardlist")
          })
        })
      }
      
      // 파일 업로드 O
      else {
        return new Promise((resolve, reject) => {
          let formData = new FormData(); // 페이지 전환 없이 폼 데이터를 제출 하고 싶을 때 FormData 객체를 사용
          formData.append('bTitle', payload.bTitle)
          formData.append('bContent', payload.bContent)
          formData.append('username', payload.username)
          formData.append('uploadFile', payload.fileinput) // key(uploadFile), value(payload)
          axios.post('http://localhost:9000/api/auth/upload', formData,
            {
            headers: {
              'Content-Type': 'multipart/form-data',
              'Access-Control-Allow-Origin': '*'
            }
          })
          .then(Response => {
            console.log(Response.data)
            console.log(payload)
            if(Response.data === "success"){
              Route.push("/boardlist")
            }
          })
          .catch(Error => {
            console.log(Error)
            alert('Error가 발생했습니다!')
          })
        })
      }
    },

    UserDelete({commit, state}, payload){
      return new Promise((resolve,reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
        axios.post(`http://localhost:9000/api/admin/userdelete/${payload.page}`)
          .then(Response => {
            console.log(payload)
            console.log(payload.page)
            console.log(payload.username)
            console.log(Response.data)
            commit('READ_USER_LIST', Response.data)
              alert('유저가 삭제되었습니다.')
          })
          .catch(Error => {
            console.log('error')
            Route.push("/")
          })
      })
    },
    
    BoardDelete({commit, state}, payload){
      return new Promise((resolve, reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
        axios.post(`http://localhost:9000/api/auth/boarddelete/${payload.page}`, payload)
        .then(Response => {
          console.log(payload)
          console.log(payload.page)
          console.log(payload.bId)
          console.log(Response.data)
          commit('READ_BOARD_LIST', Response.data)
          alert('글이 삭제되었습니다.')
        })
        .catch(Error => {
          console.log('error')
          Route.push('/')
        })
      })
    },
   
    // insertLogo(payload){
    //   console.log('insetLogo')
    //   console.log('payload를 받았습니다.')
    //   console.log('payload.name:'+payload.fileinput.name)
    //   new Promise((resolve, reject) => {
    //     let formData = new FormData(); // 페이지 전환 없이 폼 데이터를 제출 하고 싶을 때 FormData 객체를 사용
    //     formData.append('uploadFile', payload.fileinput) // key(uploadFile), value(payload)
    //     formData.append('iName',payload.fileinput.name)
    //     axios.post('http://localhost:9000/api/admin/logoupload', formData)
    //     .then(Response => {
    //       console.log(Response.data)
    //       console.log(payload)
    //       if(Response.data === "success"){
    //         this.$store.commit('SET_IMAGES', payload)
    //         Route.push("/")
    //       }
    //     })
    //     .catch(Error => {
    //       console.log(Error)
    //       alert('Error 발생 !')
    //     })
    //   })
    // },
    
  }
})

