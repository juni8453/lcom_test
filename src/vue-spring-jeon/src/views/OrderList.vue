<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-card outlined>  
          <v-card-text>
            <v-row>
              <v-col cols="3" class="Center">
                상품 사진
              </v-col>
              <v-col cols="3" class="Center">
                상품명
              </v-col>
              <v-col cols="3" class="Center">
                주문일자
              </v-col>
              <v-col cols="3" class="Center">
                비고
              </v-col>
            </v-row>

            <v-row v-for="item in orderlist" :key="item.oNum"> <!-- 여기 v-for해서 리스트 돌려뽑기-->
              <v-col cols="3" v-for="img in item.listImages" :key="img.iId">
                <router-link :to="{name:'ItemDetail',
                        params:{
                          pName:item.pName
                        }
                      }"
                    >
                  <v-card outlined>
                    <v-img
                      contain
                      :src="`/images/thumb/${img.iName}`"                  
                      >
                      </v-img>
                  </v-card>
                </router-link>
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.pName}}
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.oDate}}
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.oDemand}}
              </v-col>
            </v-row>

          </v-card-text>  
        </v-card>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" md="12" sm="12">
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
import axios from 'axios'

export default {
  data(){
    return{
      checkedAll:false,
      post:3000,
      limit:0,
      pageOpt:8,
      model:null
    }
  },

  filters:{
    comma(val){
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  },

  components:{
    BwBar,
    Footer
  },

  created(){
    console.log('OrderList Run!')
    this.$store.dispatch('orderList')
  },
  
  methods:{
    deleteCart(payload){
      console.log('deleteCart Run')
      console.log(payload)
      this.$store.dispatch('deleteCart', payload)
    }
  },

  computed:{
    ...mapState(['Userinfo', 'cartlist','orderlist'])
  },
  
}
</script>