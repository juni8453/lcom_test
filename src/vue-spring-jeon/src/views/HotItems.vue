<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>  
        <v-card class="text-center">
          <v-row>
            <v-col cols="3" v-for="item in productlist" :key="item.pId">
              <v-card>
                <v-row>
                  <v-col>
                    <v-card outlined>
                      <v-card-text>
                        <strong>
                          {{item.pName}}
                        </strong>  
                      </v-card-text>  
                    </v-card>
                    <v-card outlined
                     v-if="Userinfo.User_auth.includes('ROLE_ADMIN')"
                     @click="deleteProduct(
                      {
                        pId:item.pId,
                        iId:item.listImages[0].iId
                      }
                     )">
                      <v-icon>mdi-delete</v-icon>
                    </v-card>
                    <v-card
                     >
                      <router-link :to="{name:'ItemDetail',
                        params:{
                          pName:item.pName
                        }
                      }"
                      >
                        <v-img
                            contain
                            height="250"
                            :src="`/images/thumb/${item.listImages[0].iName}`"
                        ></v-img> 
                      </router-link> 
                    </v-card>
                  </v-col>    
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
        <infinite-loading @infinite="infiniteHandler"></infinite-loading>
      </v-col>
      <v-row>
        <v-col>
          <Footer></Footer>
        </v-col>
      </v-row>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import Route from '../router/index'
import { mapState } from 'vuex'
import InfiniteLoading from 'vue-infinite-loading'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data() {
    return {
      limit:0,
      pageOpt:8,
    }
  },

  created(){
    console.log('created 시작')
    this.$store.dispatch('hotItems', {limit:this.limit, username:this.$store.state.Userinfo.User_Id}) 
  },

  computed:{
      ...mapState(['productlist','Userinfo','heartlist'])
  },

  components: {
    InfiniteLoading,
    BwBar,
    Footer
  },

  methods: { 
    infiniteHandler($state){
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('limit+pageOpt?'+ this.limit + this.pageOpt)
      axios.get(`http://${dev}:9000/api/auth/hotitems/${this.limit + this.pageOpt}/${this.$store.state.Userinfo.User_Id}`)
      .then(Response => {
        console.log('infiniteHandler Response.data를 받았습니다.')
        console.log('Response.data:', JSON.stringify(Response.data))
        console.log('Response.data.list:', JSON.stringify(Response.data.list)) // 받아온 나머지 데이터
        console.log('Response.data.list.length:', JSON.stringify(Response.data.list.length)) // 나머지 데이터 길이

        setTimeout(() => {
          if(Response.data.list.length) {
            console.log('현재 productlist' + JSON.stringify(this.productlist))
            console.log('현재 productlist 길이' + JSON.stringify(this.productlist.length))
            console.log(Response.data.list)
            this.$store.commit('SET_PRODUCT_LIST', Response.data)
            $state.loaded()
            this.limit += 8

            if(this.productlist.length / this.pageOpt == 0) {  
              $state.complete()
            }

          } else {
            $state.complete()
          }
        },1000)
      })
      .catch(error => {
        console.log(error)
      })
    },

    deleteProduct(payload){
      let dev = 'localhost'
      let sev = '3.38.87.14'

      if(confirm('정말로 글을 삭제하시겠습니까?')===true){
        console.log('deleteProduct Run')
        console.log('deleteProduct의 payload =' + JSON.stringify(payload))
        new Promise((resolve, reject) => {
          axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
          axios.post(`http://${dev}:9000/api/admin/deleteproduct`, payload)
          .then(Response => {
              console.log(Response.data)
              // this.$store.commit('READ_PRODUCT_LIST', Response.data)
              if(Response.data === "success"){
                console.log('deleteProduct 메서드가 성공적으로 실행되었습니다.')
                /* Route.push('latestitems') 같은 페이지로 다시 push할 수 없기 때문에
                  새로고침 개념을 가진 Route.go(Route.currentRoute) 사용*/
                Route.go(Route.currentRoute)
              }
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
              alert("Error!")        
          })
        })
      }
    }
  }
}

</script>