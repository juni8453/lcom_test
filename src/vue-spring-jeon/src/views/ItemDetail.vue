<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>  
        <v-card class="text-center" outlined>
          <v-row>
            <v-col cols="12" md="12" sm="12">
            <v-sheet
                class="mx-auto"
                elevation="8"
                max-width="800"
              >
                <v-slide-group
                  v-model="model"
                  class="pa-4 Center"
                  active-class="success"
                  show-arrows
                >
                  <v-slide-item
                    v-for="item in itemdetaillist.listImages" :key="item.iName"
                    v-slot="{ active, toggle }"
                  >
                    <v-card
                      :color="active ? undefined : 'grey lighten-1'"
                      class="ma-4 Center"
                      height="500"
                      width="300"
                      @click="toggle"
                    >
                    <v-img
                      contain
                      :src="`/images/thumb/${item.iName}`"
                    >
                    </v-img>
                      <v-row
                        class="fill-height"
                        align="center"
                        justify="center"
                      >
                        <v-scale-transition>
                          <v-icon
                            v-if="active"
                            color="white"
                            size="48"
                            v-text="'mdi-close-circle-outline'"
                          ></v-icon>
                        </v-scale-transition>
                      </v-row>
                    </v-card>
                  </v-slide-item>
                </v-slide-group>
              </v-sheet>  
            </v-col>
            <v-col>
              
            </v-col>  
            <v-col cols="12" md="12" sm="12">
              <h2 class="mb-10 mt-6 text-center">
                Product Introduce
                <v-btn class="ml-3" small
                @click="buyProduct({
                  pId:itemdetaillist.pId,
                  pName:itemdetaillist.pName,
                  pPrice:itemdetaillist.pPrice,
                  pBrand:itemdetaillist.pBrand,
                  pFrom:itemdetaillist.pFrom,
                })">
                  구매하기
                  <v-icon>
                    mdi-cash
                  </v-icon>
                </v-btn>
                 <v-btn class="ml-3" small
                 @click="putCart({
                  pId: itemdetaillist.pId,
                  username: Userinfo.User_Id,
                  })">
                  장바구니
                  <v-icon>
                    mdi-cart-arrow-down
                  </v-icon>
                 </v-btn>
              </h2>

              <v-card class="mb-4">
                <v-row>
                  <v-card-text>
                    <v-row>
                      <v-col cols="3"  sm="3">
                        Product Name
                      </v-col>
                      <v-col cols="3" class="ml-8" sm="7">
                        {{itemdetaillist.pName}}
                      </v-col>
                    </v-row>              
                  </v-card-text>
                </v-row>
              </v-card>

              <v-card class="mb-4">
                <v-row>
                  <v-card-text>
                    <v-row>
                      <v-col cols="3" class="mt-1" sm="3">
                        Price
                      </v-col>
                      <v-col cols="3" class="mt-1 ml-8" sm="7">
                        {{itemdetaillist.pPrice | comma}}
                      </v-col>
                    </v-row>              
                  </v-card-text>
                </v-row>
              </v-card>

              <v-card class="mb-4">
                <v-row>
                  <v-card-text>
                    <v-row>
                      <v-col cols="3" class="mt-1" sm="3">
                        Brand
                      </v-col>
                      <v-col cols="3" class="mt-1 ml-8" sm="7">
                        {{itemdetaillist.pBrand}}
                      </v-col>
                    </v-row>              
                  </v-card-text>
                </v-row>
              </v-card>

              <v-card class="mb-4">
                <v-row>
                  <v-card-text>
                    <v-row>
                      <v-col cols="3" class="mt-1" sm="3">
                        From
                      </v-col>
                      <v-col cols="3" class="mt-1 ml-8" sm="7">
                        {{itemdetaillist.pFrom}}
                      </v-col>
                    </v-row>              
                  </v-card-text>
                </v-row>
              </v-card>

              <v-card class="mb-4">
                <v-row>
                  <v-card-text> 
                    <v-row>
                      <v-col cols="3" sm="3" class="mt-1">
                        전체 추천수
                      </v-col>
                      <v-col cols="3" class="mt-1 ml-8" sm="7">
                        {{itemdetaillist.pLike}}  
                      </v-col>
                    </v-row>              
                  </v-card-text>
                </v-row>
              </v-card>

            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
        <v-col>
          <Footer></Footer>
        </v-col>
      </v-row>
  </v-container>
</template>

<style>
  .Center { 
  display: grid;
  align-items: center;      /* 수직 중앙 정렬*/
  justify-content: center;  /* 수평 중앙 정렬*/
}
</style>

<script>
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'
import Route from '../router/index'
import axios from 'axios'

export default {
  
  props:['pId','pName'],
  data(){
    return{
      // item:{listImages:[]} // []를 초기화 시켜줘야 [0]을 못찾는다는 오류가 안뜸
      
    }
  },
  
  computed:{
    ...mapState(['productlist', 'itemdetaillist', 'Userinfo', 'heart'])
  },

  created(){
    console.log('ItemDetail Created Run!')
    // console.log('받아온 props pId:'+this.pId)
    new Promise((resolve, reject) => {
      console.log(this.$store.state.Userinfo.User_Id)
      axios.get(`http://localhost:9000/api/auth/itemdetail/${this.pName}`)
      .then(Response => {
        console.log('ItemDetail created Response data')
        console.log(Response.data)
        // this.item = Response.data  
        this.$store.commit('SET_ITEMDETAIL_LIST', Response.data)
      })
      .catch(Error => {
          console.log(Error)
          alert('새로고침 이후에는 홈으로 이동합니다.')
          Route.push("/")
      })
    })
  },

  methods:{

    buyProduct(payload){     
      console.log('buyProduct Run!')
      console.log(payload) // payload = {pName, pPrice, pBrand, pFrom, pId}
      new Promise((resolve, reject) => {
        axios.post('http://localhost:9000/api/auth/kakaopay', payload)
        .then(Response => {
          console.log(Response.data)
          console.log(Response.data.tid)
          window.open(Response.data.next_redirect_pc_url)
        })
        .catch(Error => {
          console.log('Error 발생')
          alert(Error)
        })
      })
    },

    putCart(payload){
      console.log('putCart Run')
      console.log(payload)
      new Promise((resolve, reject) => {
        axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
        axios.post(`http://localhost:9000/api/auth/putcart/${payload.username}`, payload)
        .then(Response => {
          console.log('putCart Response.data를 받았습니다.')
          console.log(Response.data)
          if(Response.data === 'success'){
            alert('제품을 장바구니에 담았습니다.')
          }
        })
        .catch(Error => {
          console.log(Error)
          alert('Error !')
        })
      })
    },
  },

  components:{
    BwBar,
    Footer
  },
  
  filters:{
    comma(val){
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },  
}
</script>