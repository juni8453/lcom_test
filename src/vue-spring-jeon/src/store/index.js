
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
    // setImages: null,
    imageslist:[],
    productlist:[],
    itemdetaillist:[],
    cartlist:[]
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
    READ_PRODUCT_LIST(state, data){
      state.productlist = data.list
    },
    READ_CART_LIST(state, data){
      state.cartlist = data.list
    },
    SET_PRODUCT_LIST(state, data){
      state.productlist = state.productlist.concat(data.list)
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
    SET_ITEMDETAIL_LIST(state, data){
      state.itemdetaillist = data
    },
  },
  
  actions: {
    loginProcess({ commit }, payload) {
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/auth/signin', payload)
              .then(Response => {
                  console.log(Response.data)
                  if (Response.data.username != null) {
                      axios.defaults.headers.common['Authorization'] = `Bearer ${Response.data.token}`
                      localStorage.setItem("token",Response.data.token)
                      /* localStorage에 얻어온 token 저장 > 이후 getItem으로 저장된 토큰 사용 가능
                         새로고침시 state에는 token이 저장되어있지만 Authorization이 날라가서 인증안됨
                         토큰 인증이 필요한 action의 경우에는
                         axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}` 삽입 필요
                      */
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
  UnpackToken({commit}) { // 새로고침 방지 메서드
    return new Promise((resolve, reject) => {
      axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`
      // localStorage에 저장된 token 사용
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

  BoardList({commit, state}, payload){
    return new Promise((resolve, reject) => {
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

  BoardWrite({commit, state}, payload){
    console.log('BoardWrite Run')
    console.log('payload를 받았습니다')
    console.log(payload)

    // 파일 업로드 X
    if(payload.fileinput === null){
      return new Promise((resolve, reject) => {
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

    BoardDelete({commit, state}, payload){
      return new Promise((resolve, reject) => {
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

    CommentPaginationList({commit, state}, payload){
      return new Promise((resolve, reject) => {
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
   
    insertProduct({commit, state},payload){
      console.log('produtInsert Run')
      console.log('payload를 받았습니다')
      console.log(payload)
      console.log('payload.name:' +payload.fileinput.name)
      console.log('payload.lastModified:' +payload.fileinput.lastModified)

      let date = new Date()
      let iPk = `${date.getFullYear()}${date.getMonth()}${date.getDate()}${date.getSeconds()}`+ payload.fileinput.lastModified 
      console.log('iPk는?'+iPk)

      return new Promise((resolve, reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
        // 
        let formData = new FormData();
        formData.append('uploadFile', payload.fileinput)
        formData.append('pName', payload.pName)                   // 제품 이름
        formData.append('pPrice', payload.pPrice)                 // 제품 가격
        formData.append('pFrom', payload.pFrom)                   // 제품 원산지
        formData.append('pBrand', payload.pBrand)                 // 제품 브랜드
        formData.append('iName',payload.fileinput.name)           // 이미지 이름
        formData.append('iPk', iPk)    // 이미지 고유번호
  
        axios.post('http://localhost:9000/api/admin/insertproduct', formData,
          {
            headers: {
              'Content-Type': 'multipart/form-data',
              'Access-Control-Allow-Origin': '*'
          }
        })
          .then(Response => {
                  console.log('Resonse.data를 받았습니다.')
                  
            if(Response.data === "success") {
              Route.push("/latestitems")
            } // 제품 등록 후 latestitems의 creted hook 실행을 위해 이동
        })
        .catch(Error => {
            console.log('error')
            reject(Error)
            alert("Error!")
            Route.push("/")
          })
        })
      }, // 제품 등록

    latestItems({commit}, payload){
      return new Promise((resolve, reject) => {
        console.log('payload:' + payload.limit)
        axios.get(`http://localhost:9000/api/auth/latestitems/${payload.limit}`)
        
        .then(Response => {
          console.log('Response data를 받았습니다.')
          console.log(Response.data)
          console.log('Items data를 받았습니다')
          console.log(Response.data.list)
          // console.log(Response.data.pagination)
          commit('READ_PRODUCT_LIST', Response.data)
          console.log('정상적으로 latestItems가 작동되었습니다.')
        })
        .catch(Error => {
          console.log(Error)
          Route.push("/")
        })
      })
    }, // 최신 상품 리스트 Test

    putCartList({commit,state}){
      return new Promise((resolve, reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}`
        axios.get(`http://localhost:9000/api/auth/putcartlist/${state.Userinfo.User_Id}`)
        .then(Response => {
          console.log('PutCartList의 Response.data를 받았습니다.')
          console.log(Response.data.list)
          // this.cartlist = Response.data.list
          commit('READ_CART_LIST', Response.data)  
          console.log('정상적으로 putCartList가 작동되었습니다.')
        })
        .catch(Error => {
          console.log(Error)
          alert('Error!')
          Route.push("/")
        })
      })
    },

  }
})

