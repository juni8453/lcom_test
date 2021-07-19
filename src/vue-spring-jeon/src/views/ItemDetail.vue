<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>  
        <v-card class="text-center" outlined>
          <v-row>
            <v-col cols="6" md="6" sm="12">
              <!-- <v-img
                height=""
                :src="`/images/thumb/${item.listImages[0].iPk}${item.listImages[0].iName}`"
              > 
              </v-img> -->
              <v-img
                contain
                :src="`/images/thumb/${itemdetaillist.listImages[0].iPk}${itemdetaillist.listImages[0].iName}`"
              >
              </v-img>
            </v-col>
            <v-col cols="6" md="6" sm="12">
              <h2 class="mb-10 mt-6 text-center">
                Product Introduce
                <v-btn class="ml-3" small
                @click="buyProduct()">
                  구매하기
                  <v-icon>
                    mdi-cash
                  </v-icon>
                </v-btn>
                 <v-btn class="ml-3" small
                 @click="putCart({
                  pId: itemdetaillist.pId
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
                      <v-col cols="3" class="mt-1" sm="3">
                        Product Name
                      </v-col>
                      <v-col cols="3" class="mt-1 ml-8" sm="7">
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
                        {{itemdetaillist.pPrice}}
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
  
            </v-col>
          </v-row>
        </v-card>
      </v-col>
      <v-footer>
          <v-card>
              <v-card-text class="indigo lighten-1 white--text text-center" flat tile>
                  <v-row>
                      <v-col>
                        Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet. Mauris cursus commodo interdum. Praesent ut risus eget metus luctus accumsan id ultrices nunc. Sed at orci sed massa consectetur dignissim a sit amet dui. Duis commodo vitae velit et faucibus. Morbi vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices, cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor vel ut orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
                      </v-col>
                  </v-row>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-text class="black--text">
                  {{ new Date().getFullYear() }} — <strong>Jeon Shopping Mall</strong>
              </v-card-text>
          </v-card>
      </v-footer>   
    </v-row>
  </v-container>
</template>
<script>
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Route from '../router/index'
import axios from 'axios'

export default {
  
  props:['pId'],
  data(){
    return{
      // item:{listImages:[]} // []를 초기화 시켜줘야 [0]을 못찾는다는 오류가 안뜸
    }
  },
  
  computed:{
    ...mapState(['productlist', 'itemdetaillist'])
  },

  created(){
    console.log('ItemDetail Created Run!')
    console.log('받아온 props pId:'+this.pId)
    new Promise((resolve, reject) => {
      axios.get(`http://localhost:9000/api/auth/itemdetail/${this.pId}`)
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
    buyProduct(){
      console.log('buyProduct Run')
    },

    putCart(payload){
      console.log('putCart Run')
      console.log(payload)
      new Promise((resolve, reject => {
        axios.post(`http://localhost:9000/api/auth/putcart`, payload)
        .then(Response => {
          console.log('putCart Response.data를 받았습니다.')
          console.log(Response.data)
          this.$store.commit('SET_CART_LIST', Response.data)
          alert('제품을 장바구니에 담았습니다.')
        })
        .catch(Error => {
          console.log(Error)
          alert('Error !')
        })
      }))
      if(confirm('제품을 장바구니에 담았습니다. 장바구니 사이트로 이동하시겠습니까?')===true){
        Route.push('/putcart')
      }  
    }
  },

  components:{
    BwBar
  }
}
</script>