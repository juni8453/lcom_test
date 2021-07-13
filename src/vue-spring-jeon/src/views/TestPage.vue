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
                              </v-card>
                            </v-col>    

                          </v-col>    
                        </v-row>
                      </v-card>
                    </v-col>
                  </v-row>
                </v-card>
                <infinite-loading @infinite="infiniteHandler"></infinite-loading>
                <!-- <v-card>
                  {{new Date().getFullYear() + new Date().getMonth() + "/" + new Date().getDate()}}
                </v-card> -->
              
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
import InfiniteLoading from 'vue-infinite-loading'
import BwBar from '../components/BwBar.vue'
import { mapState } from 'vuex'
import axios from 'axios'

export default {
data() {
  return {
    limit:0,
    testArray:[]
  }
},

created(){
  console.log('created 시작')
  this.$store.dispatch('test', {limit:this.limit}) // mutation으로 productlist에 저장 해줘야함
},

computed:{
    ...mapState(['productlist'])
},

methods: {
  infiniteHandler($state){
    axios.get(`http://localhost:9000/api/auth/test/${this.limit + 8}`)
    .then(Response => {
      console.log('infiniteHandler Response.data를 받았습니다.')
      console.log('Return 값'+Response.data) // limit 8,8 인 Response.data
      console.log('Return 값 길이'+Response.data.list.length)
      setTimeout(() => {
        if(Response.data.list.length) {
          this.productlist = this.productlist.concat(Response.data.list) // 기존 productlist에 Response.data 합치기
          console.log('this.productlist:'+this.productlist)
          this.$store.commit('READ_PRODUCT_LIST', this.productlist)
          $state.loaded()
          if(this.productlist.list.length / 8 == 0) {
            $state.complete()
          }
        } else {
          $state.complete()
        }
      }, 1000)
    })
    .catch(error => {
      console.log(error)
    })
  }
},

components: {
  InfiniteLoading,
  BwBar
},

}

</script>