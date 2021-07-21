<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <BwBar></BwBar>
                <v-row dense>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 1-1
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 1-2
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 1-3
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 1-4
                    </v-card>
                  </v-col>
                </v-row>

                <v-row dense>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 2-1
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 2-2
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 2-3
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 2-4
                    </v-card>
                  </v-col>
                </v-row>

                 <v-row dense>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 3-1
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 3-2
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 3-3
                    </v-card>
                  </v-col>
                  <v-col cols="3" md="3" sm="3">
                    <v-card class="text-center pa-3" outlined>
                      상품 3-4
                    </v-card>
                  </v-col>
                </v-row>

                <v-row dense>
                  <v-col cols="12" md="12">
                    <v-card class="text-center pa-3">
                      <v-pagination
                      v-model="page"
                      :length="5"
                      circle
                      ></v-pagination>
                    </v-card>
                  </v-col>
                </v-row>
            </v-col>
            <v-row>
              <v-col>
                <Footer></Footer>
              </v-col>
            </v-row>
        </v-row>
    </v-container>
</template>

<script>
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'
export default {
  data(){
    return{
      page:1
    }
  },
  components:{
    BwBar,
    Footer
  }
}
</script>