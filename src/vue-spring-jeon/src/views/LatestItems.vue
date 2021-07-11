<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <BwBar></BwBar>  
                
                <v-card class="text-center">
                  <v-row>
                    <v-col cols="3" v-for="item in productlist" :key="item.pId">
                      <v-card>
                        <v-row>
                          <v-col>
                            <v-col>
                              <v-card>
                                {{item.pId}}번째 상품
                              </v-card>
                            </v-col>    
                            <v-col>
                              <v-card>
                                {{item.pName}}
                              </v-card>
                            </v-col>
                            <v-col>
                              <v-card>
                                <v-img
                                    height="250"
                                    :src="`/images/thumb/2.jpg`"
                                ></v-img> 
                                <!-- src부분에는 item.iImag가 들어가야한다. 
                                  즉 state.productlist에 image가 있어야함-->
                              </v-card>
                            </v-col>    

                          </v-col>    
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
                </v-card>
              
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