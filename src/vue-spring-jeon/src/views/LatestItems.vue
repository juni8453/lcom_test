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
                    <v-card outlined>
                      {{item.pId}}
                    </v-card>
                    <v-card outlined>
                      {{item.pName}}
                    </v-card>
                    <v-card>
                      <router-link :to="{name:'ItemDetail',
                        params:{
                          pId:item.pId
                        }
                      }"
                      >
                        <v-img
                            aspect-ratio="0.78"
                            :src="`/images/thumb/${item.iPk}${item.iName}`"
                        ></v-img> 
                      </router-link>  
                    </v-card>
                  </v-col>    
                </v-row>
              </v-card>
            </v-col>
          </v-row>
        </v-card>
        <infinite-loading @infinite="infiniteHandler"></infinite-loading>
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
import Route from '../router/index'

export default {
data() {
  return {
    limit:0,
    pageOpt:8,
  }
},

created(){
  console.log('created 시작')
  this.$store.dispatch('latestItems', {limit:this.limit}) 
},

computed:{
    ...mapState(['productlist'])
    // name:{
    //   set: function() {
    //   },
    //   get: function() {
    //     return this.$store.state.productlist
    //   }
    //   Computed property was assigned to but it has no setter 오류 때문에 setter 추가
    //   state 값은 mutation을 거쳐서 값을 수정해야하는데, 직접 수정이 이뤄진다면 setter가 없다는 오류 발생
},

methods: {
  infiniteHandler($state){
    axios.get(`http://localhost:9000/api/auth/latestitems/${this.limit + this.pageOpt}`)
    .then(Response => {

      console.log('infiniteHandler Response.data를 받았습니다.')
      console.log('Response.data:', JSON.stringify(Response.data))
      console.log('Response.data.list:', JSON.stringify(Response.data.list)) // 받아온 나머지 데이터
      console.log('Response.data.list.length:', JSON.stringify(Response.data.list.length)) // 나머지 데이터 길이

      setTimeout(() => {
        if(Response.data.list.length) {
          console.log('현재 productlist' + JSON.stringify(this.productlist))
          console.log('현재 productlist 길이' + JSON.stringify(this.productlist.length))
          console.log(Response.data.list)
          this.$store.commit('SET_PRODUCT_LIST', Response.data)
          $state.loaded()
          this.limit += 8

          if(this.productlist.length / this.pageOpt == 0) {  
            $state.complete()
          }

        } else {
          $state.complete()
        }
      },1000)
    })
    
    .catch(error => {
      console.log(error)
    })
  },

  itemDetail(payload){
    console.log('itemDetail method Run!')
    console.log('iId, pId :' + JSON.stringify(payload))
    Route.push('/itemdetail')
  }
},

components: {
  InfiniteLoading,
  BwBar
},

}

</script>