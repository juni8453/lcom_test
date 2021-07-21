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
              <v-col cols="1" class="Center">
                <v-checkbox
                  v-model="boxArray"    
                  value="Jack"                                                 
                ></v-checkbox> <!-- checkbox Test-->
              </v-col>
              <v-col cols="2" class="Center">
                상품 사진 {{boxArray}}
              </v-col>
              <v-col cols="3" class="Center">
                상품명
              </v-col>
              <v-col cols="2" class="Center">
                총 수량
              </v-col>
              <v-col cols="2" class="Center">
                판매가
              </v-col>
              <v-col cols="2" class="Center">
                소계
              </v-col>
            </v-row>
            <v-row v-for="item in cartlist" :key="item.ctId"> <!-- 여기 v-for해서 리스트 돌려뽑기-->
               <v-col cols="1" class="Center">
                <v-checkbox
                  
                ></v-checkbox>
              </v-col>
              <v-col cols="2">
                <v-card outlined>
                   <router-link :to="{name:'ItemDetail',
                        params:{
                          pId:item.pId
                        }
                      }"
                    >
                    <v-img
                    contain
                    :src="`/images/thumb/${item.listCart[0].listImages[0].iPk}${item.listCart[0].listImages[0].iName}`"                  
                    >
                    </v-img>
                   </router-link>  
                </v-card>
              </v-col>
              <v-col cols="3" md="3" sm="3" class="Center">
                {{item.listCart[0].pName}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                미구현
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                {{item.listCart[0].pPrice}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                {{item.listCart[0].pPrice}}
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
      boxArray: []
    }
  },
  components:{
    BwBar,
    Footer
  },

  created(){
    console.log('PutCartList Run!')
    console.log('this.Userinfo.User_Id:' + this.$store.state.Userinfo.User_Id)
    this.$store.dispatch('putCartList')
  },

  computed:{
    ...mapState(['Userinfo', 'cartlist'])
  }
}
</script>