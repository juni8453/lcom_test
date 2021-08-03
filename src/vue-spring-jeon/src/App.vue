<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-item 
          router :to="{name:'Home'}"
          >
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>쇼핑몰 홈</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item 
          v-if="isLogin === false"
          router :to="{name:'Login'}"
          >
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Login</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item
          v-if="isLogin === true && Userinfo.User_auth.includes('ROLE_ADMIN')" 
          router :to="{name:'admin'}"
          >
          <v-list-item-action>
            <v-icon>mdi-card-text-outline</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>유저 목록 / 관리자 전용
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item 
          router :to="{name:'BoardList'}"
          @click="BoardList()"
          >
          <v-list-item-action>
            <v-icon>mdi-card-text-outline</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>자유게시판</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <v-list-item 
          router :to="{name:'BoardWrite'}"
          @click="BoardWrite()"
          >
          <v-list-item-action>
            <v-icon>mdi-card-text-outline</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>글 작성</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <!--
        <v-list-item 
          router :to="{name:'BoardOption'}"
          >
          <v-list-item-action>
            <v-icon>mdi-cog</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>설정</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        -->
        <v-list-item 
          router :to="{name:'ProductInsert'}"
          >
          <v-list-item-action>
            <v-icon>mdi-cog</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>상품등록</v-list-item-title>
          </v-list-item-content>
        </v-list-item>  
      
      </v-list>
      
    </v-navigation-drawer>

    <v-app-bar
      app
      color="primary"
      dark
    >
      <v-app-bar-nav-icon 
      @click.stop="drawer = !drawer"
      v-if="isLogin === true && Userinfo.User_auth.includes('ROLE_ADMIN')" 
      >
      </v-app-bar-nav-icon>
      <v-toolbar-title>Jeon Board Page</v-toolbar-title>
      <v-spacer></v-spacer>
      <div
       v-if="isLogin === true"
      >
        {{Userinfo.User_Id}}님 환영합니다.
      </div>  
      <v-btn class="ml-5"
       v-if="isLogin === true"
       @click="logout()"
       small
      >
        로그아웃
      </v-btn>
      <div class="text-center">
        <v-menu offset-y>
          <template>
          <!--  <v-btn
              color="primary"
              dark
              v-bind="attrs"
              v-on="on"
            >상세 메뉴
            </v-btn> -->
          <!--  <v-btn 
              class="ma-2" 
              outlined 
              href="/images/thumb/ㅁㅁㅁ.png"
              download>
             테스트용 첨부파일 다운로드
          </v-btn> -->
          </template>

          <v-list>
            <v-list-item
              v-for="(item,index) in items"
              :key="index"
            >
              <v-list-item-title
              >
                {{item.title}}
              </v-list-item-title>
            </v-list-item>
          </v-list>

        </v-menu>
      </div>
    </v-app-bar>

    <v-main>
      <v-container
        class="fill-height"
        fluid
      >
        <router-view/>
      </v-container>
    </v-main>
    <v-footer
      color="primary"
      app
    >
      <span class="white--text">&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>
import {mapMutations, mapState} from "vuex"
import Route from '../src/router/index'

  export default {
    data: () => ({
      drawer: null,
      items:[
        {title: '마이페이지'},
        {title: '로그아웃'}
      ]
    }),
    computed: {
      ...mapState(["Userinfo", 'isLogin', 'isLoginError']),
    },
    methods: {
      ...mapMutations(["logout"]),
      BoardList(){
         if(this.isLogin === false){
          alert('로그인이 필요한 서비스입니다.')
          Route.push("/login")
         }
      },
      BoardWrite(){
        if(this.isLogin === false){
          alert('로그인이 필요한 서비스입니다.')
          Route.push("/login")
        }
      },
    },
    created() {
      console.log('App.vue 실행')
      if(this.Userinfo.User_token === null && localStorage.getItem("token") !== null) {
        // 저장된 token이 없고, 로컬에 저장된 토큰이 있을 때 if 실행
        this.$store.commit("INSERT_TOKEN"),
        this.$store.dispatch('UnpackToken')
      }
      if(localStorage.getItem('token') != null){
        console.log('localStorage에 토큰이 있습니다.')
      }
      if(localStorage.getItem('token') == null){
        console.log('localStorage에 토큰이 없습니다.')
      }
    }
  }
</script>
