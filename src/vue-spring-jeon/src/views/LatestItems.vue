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
import BwBar from '../components/BwBar.vue'
import { mapState } from 'vuex'

export default {
  data(){
    return{
      page:1
    }
  },

  created(){
    this.$store.dispatch('latestItems')
  },

  computed:{
      ...mapState(['productlist'])
    },

  components:{
    BwBar
  }
}
</script>