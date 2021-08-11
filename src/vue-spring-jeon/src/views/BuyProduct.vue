<template>
  <v-container fluid>
    <v-row>
      <BwBar></BwBar>
      <v-col cols="12"> 
        <v-card shaped>
          <v-row>
            <v-col cols="3" class="Center">
              상품이름
            </v-col>
            <v-col cols="9" class="Center">
              [받아온 값]
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              수량
            </v-col>
            <v-col cols="9" class="Center">
              [받아온 값]
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              사이즈
            </v-col>
            <v-col cols="9" class="Center">
              [받아온 값]
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              옵션
            </v-col>
            <v-col cols="9" class="Center">
              [받아온 값]
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
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              집전화
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                label="선택항목입니다."
                placeholder="-제외 입력해주세요."
                filled
                rounded
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              휴대폰 번호
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                label="필수항목입니다."
                placeholder="-제외 입력해주세요."
                filled
                rounded
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
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              집전화
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                label="선택항목입니다."
                placeholder="-제외 입력해주세요."
                filled
                rounded
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              휴대폰 번호
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                label="필수항목입니다."
                placeholder="-제외 입력해주세요."
                filled
                rounded
              ></v-text-field>
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
            <v-col cols="3" class="Center">
              주문 합계금액
            </v-col>
            <v-col cols="9" class="Center">
              [받아온 값]
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="3" class="Center">
              입금자 명
            </v-col>
            <v-col cols="9" class="Center">
              <v-text-field
                placeholder="필수항목입니다."
                filled
                rounded
              ></v-text-field>
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
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data(){
    return{

    }
  },
  components:{
    BwBar,
    Footer
  }
}
</script>