<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">

              <v-card class="text-center pa-3" outlined color="black">
                  <v-row dense align="center" justify="space-around">
                      <v-col cols="2">
                          <v-btn text color="white"
                          router :to="{name:'BoardList'}"
                          >
                              커뮤니티 바로가기
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text color="white"
                          router :to="{name:'Home'}"
                          >
                              쇼핑몰 바로가기
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text color="white">
                              FAQ
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text color="white">
                              1:1문의
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text color="white">
                              사용후기
                          </v-btn>
                      </v-col>
                  </v-row>
              </v-card>

              <v-card class="text-center pa-3" outlined color="white">
                  <v-row dense align="center" justify="space-around">
                      <v-col cols="4">
                          Jeon Shopping Mall
                      </v-col>
                      <v-col cols="2">
                          <v-btn text
                          router :to="{name:'HotItems'}">
                              인기상품
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text
                          router :to="{name:'SaleItems'}"
                          >
                              할인상품
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text
                          router :to="{name:'LatestItems'}"
                          >
                              최신상품
                          </v-btn>
                      </v-col>
                      <v-col cols="2">
                          <v-btn text>
                              추천상품
                          </v-btn>
                      </v-col>    
                  </v-row>
              </v-card> 

          </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
  
}
</script>
    
                