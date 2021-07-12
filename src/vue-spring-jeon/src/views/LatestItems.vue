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
                              <v-card outlined>
                                {{item.pId}}
                              </v-card>
                            </v-col>    
                            <v-col>
                              <v-card outlined>
                                {{item.pName}}
                              </v-card>
                            </v-col>
                            <v-col>
                              <v-card>
                                <v-img
                                    height="250"
                                    :src="`/images/thumb/${item.iPk}${item.iName}`"
                                ></v-img> 
                                <!--productlist Array에 pName, pBrand 등 안뽑혀나옴 -->
                              </v-card>
                            </v-col>    

                          </v-col>    
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
                </v-card>
                <!-- <v-card>
                  {{new Date().getFullYear() + new Date().getMonth() + "/" + new Date().getDate()}}
                </v-card> -->
              
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
      page:1,
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
  },
  methods:{
    // Time(){
    //   let date = new Date()
    //   let year = date.getFullYear
    //   let month = date.getMonth() + 1 
    //   month = month >= 10 ? month : '0' + month
    //   let day = date.getDate()
    //   day = day >= 10 ? day : '0' + day
    //   let hour = date.getHours()
    //   hour = hour >= 10 ? hour : '0' + hour
    //   let min = date.getMinutes()
    //   let sec = date.getSeconds()
    //   sec = sec >= 10 ? sec : '0' + sec

    //   let purchaseDay = year + month + day + "_" + hour + min + sec
    // }
  }
}
</script>