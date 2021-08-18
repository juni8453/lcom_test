<template>
  <v-container fluid>
    <v-row>
      <BwBar></BwBar>
      <v-col cols="12"> 
        <v-card shaped>
          <v-row>
            <v-col cols="3" class="Center mt-3">
              상품이름
            </v-col>
            <v-col cols="9" class="Center mt-3">
              {{pName}}
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-card outlined>
              </v-card>     
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="3" class="Center">
              <strong>배송자 정보</strong>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              받는사람 이름
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
                v-model="rName"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              배송지 주소
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
                v-model="oAddress"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              배송지 상세주소
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
                v-model="oDetailAddress"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="12">
              <v-card outlined>
              </v-card>     
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="3" class="Center">
              <strong>주문자 정보</strong>              
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              이메일 주소
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
                v-model="oEmail"
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              주문자 아이디
            </v-col>
            <v-col cols="9" class="Center">
              {{Userinfo.User_Id}}
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center mt-7">
              주문자 핸드폰 번호
            </v-col>
            <v-col cols="9" class="Center mt-5">
              {{Userinfo.User_Phone}}
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              기타 요구사항
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
                v-model="oDemand"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>

    <v-row class="mt-10 mb-10">
      <v-col cols="12">
        <v-card shaped>
          <v-row>
            <v-col cols="3" class="Center">
              <strong>결재정보</strong>
            </v-col>
          </v-row>
           <v-row>
            <v-col cols="3" class="Center mt-5">
              주문금액
            </v-col>
            <v-col cols="9" class="Center mt-2">
              {{pPrice | comma}}원
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              입금자 명
            </v-col>
            <v-col cols="9" class="Center mt-3">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
                v-model="oName"
              ></v-text-field>
            </v-col>  
          </v-row>
          <v-row>
            <v-col cols="6" class="Center">
              <v-btn large
                @click="buyProduct({
                  rName:rName,
                  oAddress:oAddress,
                  oDetailAddress:oDetailAddress,
                  oEmail:oEmail,
                  oDemand:oDemand,
                  oName:oName,
                  pName:pName,
                  pPrice:pPrice,
                  pQuantity:pQuantity,
                  username:Userinfo.User_Id
                })"
              > 주문 완료 {{pQuantity}}
                <v-icon>mdi-cash</v-icon>
              </v-btn>  
            </v-col>
            <v-col cols="6" class="Center">
              <v-btn large
                router :to="{name:'ItemDetail',
                  params:{
                    pName:pName
                  }
                }"
              > 되돌아가기
                <v-icon>mdi-undo</v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
    <Footer></Footer>
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
import axios from 'axios'
import Route from '../router/index'
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  props:['pName','pPrice','pQuantity'],

  data(){
    return{
      rName:null,
      oAddress:null,
      oDetailAddress:null,
      oEmail:null,
      oDemand:null,
      oName:null
    }
  },

  computed:{
    ...mapState(['Userinfo'])
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

  methods:{
    buyProduct(payload){
      console.log('buyProduct Run!')
      console.log(payload)
      if(confirm('주문하겠습니까?')===true){
        new Promise((resolve, reject) => {
          axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
          axios.post(`http://localhost:9000/api/auth/buyproduct`, payload)
          .then(Response => {
            console.log(Response.data)
            if(Response.data === 'success'){
              alert('주문이 완료되었습니다.')
              if(confirm('주문내역을 확인하시겠습니까?') === true){
                Route.push('/orderlist')
              }
            }
          })
          .catch(Error => {
            console.log(Error)
            alert('Error !')
          })
        })      
      }
    }
  }
}
</script>