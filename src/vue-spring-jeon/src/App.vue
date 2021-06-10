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
            <v-list-item-title>Home</v-list-item-title>
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
          >
          <v-list-item-action>
            <v-icon>mdi-card-text-outline</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>게시판 목록</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      
    </v-navigation-drawer>

    <v-app-bar
      app
      color="primary"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Jeon Board Page</v-toolbar-title>
      <v-spacer></v-spacer>
      <div
       v-if="isLogin === true"
      >
        {{Userinfo.User_Name}}님 환영합니다.
      </div>  
      <v-btn
       v-if="isLogin === true"
       @click="logout()"
       small
      >
        로그아웃
      </v-btn>
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

  export default {
    data: () => ({
      boardvo : null,
      drawer: null,
    }),
    computed: {
      ...mapState(["Userinfo", 'isLogin']),
    },
    methods: {
      ...mapMutations(["logout"]),
       
     
    },
    created() {
      if(this.Userinfo.User_token === null && localStorage.getItem("token") !== null) {
        this.$store.commit("INSERT_TOKEN"),
        this.$store.dispatch('UnpackToken')
      }
    }
  }
</script>
