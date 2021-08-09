<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-card outlined>  
          <v-card-text>
            <v-row>
              <v-col cols="2" class="Center">
                제품 랭킹
              </v-col>
              <v-col cols="4" class="Center">
                상품 사진
              </v-col>
              <v-col cols="4" class="Center">
                상품명
              </v-col>
              <v-col cols="2" class="Center">
                추천수
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="2" class="Center">
                {{itemdetaillist.list[0]}}
              </v-col>
              <v-col cols="4" class="Center">
                Img
              </v-col>             
              <v-col cols="4" class="Center">
                
              </v-col>
              <v-col cols="2" class="Center">
                
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

export default {
  data(){
    return{
     
    }
  },
  computed:{
    ...mapState(['itemdetaillist'])
  },

  created(){
    console.log('ProductRank Created Run!')
    this.$store.dispatch('productRank')
  },

  components:{
    BwBar,
    Footer
  }
}
</script>  