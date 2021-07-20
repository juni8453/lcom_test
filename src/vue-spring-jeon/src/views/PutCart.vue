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
            <v-row v-for="item in cartlist" :key="item.ctId"> <!-- 여기 v-for해서 리스트 돌려뽑기-->
              <v-col cols="6" class="text-center">
                <v-card outlined>
                  {{item.listCart[0].pName}}
                </v-card>
              </v-col>
              <v-col cols="2" class="text-center">
                <v-card outlined>
                  미구현
                </v-card>
              </v-col>
              <v-col cols="2" class="text-center">
                <v-card outlined>
                  {{item.listCart[0].pPrice}}
                </v-card>
              </v-col>
              <v-col cols="2" class="text-center">
                <v-card outlined>
                  {{item.listCart[0].pPrice}}
                </v-card>
              </v-col>
            </v-row>
          </v-card-text>  
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-card outlined>
          <v-row>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text class="indigo lighten-1 white--text text-center">
                CS CENTER
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text v-card-text class="indigo lighten-1 white--text text-center">
                DELIVERY
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text v-card-text class="indigo lighten-1 white--text text-center">
                BANK ACCOUNT
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text>
                1599-3120 <br>
                MON-FRI : 10:00 ~ 17:00 <br>
                LUNCH: 13:00 ~ 14:00 <br>
                SAT, SUN, HOLIDAY OFF
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text>
                반품주소 <br>
                대구광역시 동구 신서동 446-3
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text>
                예금주: (주)ABC <br>
                우리 1005-123-123456 <br>
                농협 351-3333-4444-55 <br>
                기업 114-12342-13-333 <br>
                국민 594401-22-323232 <br>
                신한 140-333-13423414 <br>
              </v-card-text>
              <v-divider></v-divider>
              <v-col>
                {{new Date().getFullYear()}}Year
              </v-col>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>      
</template>

<script>
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'

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