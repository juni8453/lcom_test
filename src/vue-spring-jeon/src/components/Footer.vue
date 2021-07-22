<template>
  <v-container>    
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
              <v-card-text class="indigo lighten-1 white--text text-center">
                DELIVERY
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text class="indigo lighten-1 white--text text-center">
                BANK ACCOUNT
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text>
                1599 - 3120 <br>
                MON-FRI : 10:00 ~ 17:00 <br>
                LUNCH: 13:00 ~ 14:00 <br>
                SAT, SUN, HOLIDAY OFF
              </v-card-text>
            </v-col>
            <v-col cols="4" md="4" sm="4" class="text-center">
              <v-card-text>
                반품주소 <br>
                경기도 성남시 분당구 불정로 6 (우)13561
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