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
              <v-col cols="6" class="text-center">
                상품명
              </v-col>
              <v-col cols="2" class="text-center">
                총 수량
              </v-col>
              <v-col cols="2" class="text-center">
                판매가
              </v-col>
              <v-col cols="2" class="text-center">
                소계
              </v-col>
            </v-row>
            <v-row> <!-- 여기 v-for해서 리스트 돌려뽑기-->
              <v-col cols="6" class="text-center">
                <v-card>
                  {{cartlist[0].listCart[0].pName}}
                </v-card>
              </v-col>
              <v-col cols="2" class="text-center">
                <v-card>
                  미구현
                </v-card>
              </v-col>
              <v-col cols="2" class="text-center">
                <v-card>
                  {{cartlist[0].listCart[0].pPrice}}
                </v-card>
              </v-col>
              <v-col cols="2" class="text-center">
                <v-card>
                  {{cartlist[0].listCart[0].pPrice}}
                </v-card>
              </v-col>
            </v-row>
          </v-card-text>  
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-footer>
          <v-card>
            <v-card-text class="indigo lighten-1 white--text text-center" flat tile>
              <v-row>
                <v-col>
                 
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
import axios from 'axios'
import BwBar from '../components/BwBar.vue'
import Route from '../router/index'

export default {
  data(){
    return{
      
    }
  },
  components:{
    BwBar
  },

  created(){
    console.log('PutCartList Run!')
    console.log('this.Userinfo.User_Id:' + this.$store.state.Userinfo.User_Id)
    // created는 computed 시작 전 제일 먼저 실행되기 때문에 mapState 사용 불가능
    this.$store.dispatch('putCartList')
  },

  computed:{
    ...mapState(['Userinfo', 'cartlist'])
  }
}
</script>