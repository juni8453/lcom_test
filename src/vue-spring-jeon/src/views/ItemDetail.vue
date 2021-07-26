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
import Vue from 'vue'

export default {
  
  props:['pId'],
  data(){
    return{
      // item:{listImages:[]} // []를 초기화 시켜줘야 [0]을 못찾는다는 오류가 안뜸
      
    }
  },
  
  computed:{
    ...mapState(['productlist', 'itemdetaillist', 'Userinfo'])
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
    // buyProduct(){
    //   console.log('buyProduct Run')
      // Vue.IMP().request_pay(
      // {
      //   pg : 'kakao', // 사용할 pg사 (필수)
      //   pay_method: 'card', // 결제수단 (필수)
      //   merchant_uid: 'merchand_' + new Date().getTime(), // 가맹점에서 생성, 관하는 고유 주문번호(필수)
      //   name: '주문명 : 결제테스트',  // 주문명 (선택)
      //   amount: '100', // 결제할 금액 (필수)
      //   buyer_email: "juni8453@naver.com", // 구매자 이메일 (선택)
      //   buyer_name: "전병준", // 구매자 이름 (선택)
      //   buyer_tel: "010-5592-9710", // 구매자 연락처 (필수)
      //   buyer_addr: "대구광역시 동구 신서동 446-3", // 구매자 주소 (선택)
      //   buyer_postcode: "123-456" // 주문자 우편번호 (선택)
      // }, rsp => {
      //   if(rsp.success) { // 결제처리가 성공적이었다면,
      //     console.log('결제 요청이 성공적일때 Response는 ?')
      //     console.log(rsp)
      //     axios.post('https://www.myservice.com/payments/complete', // 가맹점 서버 URL
      //     {
      //       headers: {
      //         'Content-Type': 'application/json',
      //         'Access-Control-Allow-Origin': '*'
      //       },
      //       data: {
      //         imp_uid: rsp.imp_uid,           // rsp.imp_uid : imp_400492023457 (아임포트 거래건 당 고유 번호)
      //         merchant_uid: rsp.merchant_uid  // res.merchant_uid : merchand_1627287491924 (고유 주문번호)
      //       }
      //     })  
      //     .then(rsp => {
      //       // 가맹점 서버 결제 API 성공시 로직 추가
      //       let msg = "결제가 완료되었습니다."
      //       msg += '고유ID : ' + rsp.imp_uid;
      //       msg += '상점 거래ID : ' + rsp.merchant_uid;
      //       msg += '결제 금액 : ' + rsp.paid_amount; // 실제 결제승인된 금액 or 가상계좌 입금예정 금액
      //       msg += '카드 승인번호 : ' + rsp.apply_num; // 카드사 승인번호 (신용카드결제에 한해 제공)
      //       alert(msg);
      //       console.log('msg는?')
      //       console.log(msg)
      //     }) 
      //   }else {
      //     alert('결제에 실패하였습니다. 에러내용:'+rsp.error_msg) // 상세 에러 메세지
      //   }
      // }   
      // )
      // },
    
    buyProduct(){
      console.log('buyProduct Run!')
      const adminKey = '9ebd839a995a6df19b86b9dd787e0b87'
      new Promise((resolve, reject) => {  
        axios.post('/v1/payment/ready',
        // vue.config.js proxy 설정 > http://kapi.kakao.com 설정했기 때문에 뒷 URL만 작성
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
              'Access-Control-Allow-Origin': '*',
              'Authrozation': 'KakaoAK {adminKey}'
            }
          },
          {
            params: {
              cid:"TC0ONETIME",
              partner_order_id:"partner_order_id",
              partner_user_id:"partner_user_id",
              item_name:"테스트 상품",
              quantity:1,
              total_amount:30000,
              tax_free_amount:0,
              approval_url:"https://developers.kakao.com/success",
              fail_url:"https://developers.kakao.com/fail",
              cancel_url:"https://developers.kakao.com/cancel"
            },
          }
        )
        .then(Reponse => {
          console.log(Reponse.data)
        })
        .catch(Error => {
          console.log(Error)
          alert("error 발생")
          Route.push("/")
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
    BwBar
  }
}
</script>/