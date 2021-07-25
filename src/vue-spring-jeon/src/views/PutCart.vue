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
                  v-model="checkedAll"
                  @click="selectAllProduct({item:cartlist})"
                > 전체선택
                </v-checkbox>
              </v-col>
              <v-col cols="2" class="Center">
                상품 사진
              </v-col>
              <v-col cols="3" class="Center">
                상품명
              </v-col>
              <v-col cols="2" class="Center">
                판매가
              </v-col>
              <v-col cols="2" class="Center">
                소계
              </v-col>
              <v-col cols="2" class="Center">
                비고 
              </v-col>
            </v-row>

            <v-row v-for="item in cartlist" :key="item.ctId"> <!-- 여기 v-for해서 리스트 돌려뽑기-->
              <v-col cols="1" class="Center">
                <v-checkbox
                  v-model="item.check"
                  @click="selectOneProduct({item:item, listCart:item.listCart[0]})"
                >
                </v-checkbox>
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
                {{item.listCart[0].pName}} <br>
                <!-- cartlist(item) check = {{item.check}} <br> -->
              
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                {{item.listCart[0].pPrice}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                {{item.listCart[0].pPrice}}
              </v-col>
              <v-col cols="2" md="2" sm="2" class="Center">
                <v-btn small
                
                >
                  개별 구매
                  <v-icon>
                    mdi-cash
                  </v-icon>
                </v-btn>
                <v-btn small
                  @click="deleteCart({
                    ctId:item.ctId,
                  })"
                >
                  장바구니 삭제
                  <v-icon>
                    mdi-delete
                  </v-icon>
                </v-btn>
              </v-col>
            </v-row>
            
          </v-card-text>  
        </v-card>
      <v-row>
      </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
        <v-card outlined>
        <v-card-text>
          <v-row>
            <v-col cols="3" class="text-center">
              총 상품금액
            </v-col>
            <v-col cols="1" class="text-center">
              +
            </v-col>
            <v-col cols="3" class="text-center">
              기본 배송비
            </v-col>
            <v-col cols="1" class="text-center">
              =
            </v-col>
            <v-col cols="4" class="text-center">
              총 결제가격
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="text-center">
              {{totalprice}}원
            </v-col>
            <v-col cols="1" class="text-center">
              +
            </v-col>
            <v-col cols="3" class="text-center">
              {{post}}원
            </v-col>
            <v-col cols="1" class="text-center">
              = 
            </v-col>
            <v-col cols="4" class="text-center">
             {{totalprice + post}}원
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
      checkedAll:false,
      post:3000,
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
  
  methods:{
    deleteCart(payload){
      console.log('deleteCart Run')
      console.log(payload)
      this.$store.dispatch('deleteCart', payload)
    },

    selectAllProduct(payload){ // payload = {item:cartlist}
      console.log('payload.item은?')
      console.log(payload.item) 
      let sum = 0

      for(let i=0; i<payload.item.length; i++){
        if(payload.item[i].check == false){
          payload.item[i].check = true
          console.log('payload.item[i].check는?'+ payload.item[i].check)

          if(payload.item[i].check === true){ // 모든 제품의 check = true 상태며,
            if(this.totalprice === 0){ // 아무런 제품도 선택되지 않았을 때              
            console.log('pPrice:'+payload.item[i].listCart[0].pPrice)
            // console.log(sum += payload.item[i].listCart[0].pPrice)
            sum += payload.item[i].listCart[0].pPrice
            console.log('모든 제품의 가격 합계는?'+sum)
            
            }
          }
        } else {
          payload.item[i].check = false
          console.log('payload.item[i].check는?'+ payload.item[i].check)
          // let sum 값이 0이 되어 SET_TOTALPRICE로 넘어가기 때문에 state.totalprice 값은 0이다.
        }
      }
      this.$store.commit('SET_TOTALPRICE',sum)
    },
    
    selectOneProduct(payload){ // payload = {item:item(cartlist), listCart:item.listCart[0]}
      console.log(payload)

      if(payload.item.check === true){ // 제품 가격 더하는 if
        console.log('check 확인 결과 true 입니다.')
        if(this.totalprice === 0){ // 아무 제품도 선택되어있지 않는 상태일 때,
          this.post = 3000
          this.$store.commit('SET_TOTALPRICE', payload.listCart.pPrice)
        }
        else { // 제품이 하나 이상 선택되어 값이 들어가 있을 때,
          this.$store.commit('SET_TOTALPRICE',this.totalprice + payload.listCart.pPrice) 
        }

      } 
      if(payload.item.check === false){ // 제품 가격 빼는 if
        console.log('check 확인 결과 false 입니다.')        
        this.$store.commit('SET_TOTALPRICE', this.totalprice - payload.listCart.pPrice)
      }
    },
  },

  computed:{
    ...mapState(['Userinfo', 'cartlist','Check', 'totalprice'])
  }
}
</script>