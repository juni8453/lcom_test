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
                      <v-icon>
                        mdi-delete
                      </v-icon>
                    </v-card>
                    <v-card
                     >
                     {{item.pId}}
                     <!-- router-link에 @click.native로 메서드 설정-->
                      <router-link 
                      @click.native="clickLink({item})"
                      :to="{name:'ItemDetail',
                        params:{
                          pId:item.pId,
                          pName:item.pName
                        }
                      }"
                      >
                        <v-img
                            v-if="item.pQuantity !== 0"
                            contain
                            height="250"
                            :src="`/images/thumb/${item.listImages[0].iName}`"
                        ></v-img>
                        <v-img
                            v-else
                            contain
                            height="250"
                            :src="`/images/thumb/${item.listImages[0].iName}`"
                        >
                          <!-- 이미지 내 다른 이미지 삽입 (매진되었을 때)-->
                          <v-img
                            contain
                            height="100"
                            :src="`/images/thumb/soldout.png`"
                          >
                          </v-img>
                        </v-img> 
                      </router-link> 
                    </v-card>
                     <v-card outlined>
                      <v-icon
                        v-if="item.listHeart.find(heart => heart.username !== Userinfo.User_Id)"
                        @click="likeProduct(
                          {
                            pId: item.pId,
                            username: Userinfo.User_Id,
                          })"
                      >mdi-heart-outline</v-icon>
                      <v-icon
                        v-else
                        @click="cancelLike({
                          pId: item.pId,
                          username: Userinfo.User_Id
                        })"
                      >mdi-heart</v-icon> 
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
    this.$store.dispatch('latestItems', {limit:this.limit, username:this.$store.state.Userinfo.User_Id}) 
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
    clickLink(payload){
      console.log('link의 payload는?')
      console.log(payload.item.pQuantity)
      if(payload.item.pQuantity === 0){
        alert('해당 제품은 매진되었습니다.')
        Route.push('/latestitems')
        Route.go(Route.currentRoute)
        // push 이후 페이지 동작 안되서 새로고침 넣음
      }
    },

    likeProduct(payload){ // payload = {pId, username}
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('likeProduct Run')
      console.log(payload)
      if(confirm('제품을 추천하시겠습니까?')===true){
        axios.post(`http://${dev}:9000/api/auth/likeproduct/${payload.username}` ,payload)
        .then(Response => {         
          console.log(Response.data)
          this.$store.commit('READ_PRODUCT_LIST', Response.data)
          console.log('likeProduct 메서드가 성공적으로 실행되었습니다.')
          /* 좋아요 현황을 즉각 반영하기 위해 succss를 return 받지 않고 insertHeart 이후의 리스트를 다시 SELECT 하여 
            최신 리스트를 다시 뽑아준다. */
        })
        .catch(Error => {
          console.log('error')
          console.log(Error)
        })
      }
    }, 

    cancelLike(payload){
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('cancleLike Run')
      console.log(payload)
      if(confirm('제품 추천을 취소하시겠습니까?')===true){
        axios.post(`http://${dev}:9000/api/auth/cancellike/${payload.username}` ,payload)
        .then(Response => {         
          console.log(Response.data)
          this.$store.commit('READ_PRODUCT_LIST', Response.data)
          console.log('cancelLike 메서드가 성공적으로 실행되었습니다.')
        })
        .catch(Error => {
          console.log('error')
          console.log(Error)
        })
      }
    }, 

    infiniteHandler($state){ 
      let dev = 'localhost'
      let sev = '3.38.87.14'

      console.log('limit+pageOpt?'+ this.limit + this.pageOpt)
      axios.get(`http://${dev}:9000/api/auth/latestitems/${this.limit + this.pageOpt}/${this.$store.state.Userinfo.User_Id}`)
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