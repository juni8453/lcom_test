<template>
   <v-container fluid>
    
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <v-card class="text-center pa-3" outlined>
          <v-card-text>
            INSERT PRODUCT
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-row dense>
      <v-col cols="3" md="3">
        <v-row>
          <v-col>
            <v-card class="pa-3 text-center" outlined height="150">
              <v-file-input
                  id="uploadFile"
                  label="클릭해서 이미지를 업로드하세요."
                  v-model="fileinput"
                  multiple
                > 
                </v-file-input>
                <v-btn outlined 
                @click="insertProductTest({
                  pName: pName,
                  pPrice: pPrice,
                  pFrom: pFrom,
                  pBrand: pBrand,
                  fileinput: fileinput
                })"
                >등록</v-btn>
            </v-card>
          </v-col>
        </v-row>  
      </v-col>
      <v-col cols="9" md="9" sm="9">
        <v-card class="pa-3 text-center" outlined>
          <v-row>
            <v-col>
                <v-row>
                  <v-col cols="4" md="4" sm="4" class="pa-8">
                    <v-card outlined>
                      <v-card-text>
                        제품 이름
                      </v-card-text>
                    </v-card>
                  </v-col>
                  <v-col cols="5" md="5" sm="5">
                       <v-text-field
                        label="제품 이름을 입력하세요."
                        name="pName"
                        v-model="pName"
                        type="text"
                  ></v-text-field>
                  </v-col>
                </v-row>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
                <v-row>
                  <v-col cols="4" md="4" sm="4" class="pa-8">
                    <v-card outlined>
                      <v-card-text>
                        판매 가격
                      </v-card-text>
                    </v-card>
                  </v-col>
                  <v-col cols="5" md="55" sm="5">
                      <v-text-field
                        label="판매가격을 입력하세요."
                        name="pPrice"
                        v-model="pPrice"
                        type="text"
                  ></v-text-field> 
                  </v-col>
                </v-row>
            </v-col>
          </v-row>

          <v-row>
            <v-col>
                <v-row>
                  <v-col cols="4" md="4" sm="4" class="pa-8">
                    <v-card outlined>
                      <v-card-text>
                        원산지
                      </v-card-text>
                    </v-card>
                  </v-col>
                  <v-col cols="5" md="5" sm="5">
                    <v-text-field
                        label="원산지를 입력하세요."
                        name="pFrom"
                        v-model="pFrom"
                        type="text"
                  ></v-text-field>
                  </v-col>
                </v-row>
            </v-col>
          </v-row>

         <v-row>
            <v-col>            
                <v-row>
                  <v-col cols="4" md="4" sm="4" class="pa-8">
                    <v-card outlined>
                      <v-card-text>
                        브랜드
                      </v-card-text>
                    </v-card>
                  </v-col>
                  <v-col cols="5" md="5" sm="5">
                    <v-text-field
                        label="브랜드를 입력하세요."
                        name="pBrand"
                        v-model="pBrand"
                        type="text"
                  ></v-text-field>
                  </v-col>
                </v-row>

            </v-col>
          </v-row>
        </v-card>
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
import axios from 'axios'
import {mapState,mapActions } from 'vuex'
import Route from '../router/index'

export default {
  data(){
    return{
      pName: null,
      pPrice: null,
      pFrom: null,
      pBrand: null,
      fileinput: null
    }
  },

  methods:{
    ...mapActions(['insertProductTest']),
    
  },

  computed:{
    ...mapState(['Userinfo'])
  },
  
}
</script>