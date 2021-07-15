<template>
  <v-container fluid>
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <BwBar></BwBar>  
        <v-card class="text-center" outlined>
          <v-row>
            <v-col cols="6">
              <v-img
                height="350"
                :src="`/images/thumb/${item.listImages[0].iPk}${item.listImages[0].iName}`"
              >
              </v-img>
            </v-col>
            <v-col cols="6">
            <v-card outlined>
              <v-row class="pa-7">
                <v-col>제품 이름 //pName // {{item.pName}}</v-col>
              </v-row>
            </v-card>
            <v-card outlined>  
              <v-row class="pa-8">
                <v-col>제품 브랜드 // {{item.pBrand}}</v-col>
              </v-row>
            </v-card>
            <v-card outlined>  
              <v-row class="pa-8">
                <v-col>제품 가격 // {{item.pPrice}}</v-col>
              </v-row>
            </v-card>
            <v-card outlined>  
              <v-row class="pa-8">
                <v-col>제품 원산지 // {{item.pFrom}}</v-col>
              </v-row>
            </v-card>  
            </v-col>
          </v-row>
        </v-card>
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
import { mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Route from '../router/index'
import axios from 'axios'
export default {
  props:['pId'],
  data(){
    return{
      item:{}
    }
  },
  
  computed:{
    ...mapState(['productlist'])
  },

  created(){
    console.log('ItemDetail Created Run!')
    console.log('받아온 props pId:'+this.pId)
    new Promise((resolve, reject) => {
      axios.get(`http://localhost:9000/api/auth/itemdetail/${this.pId}`)
      .then(Response => {
        console.log(Response.data)
        this.item = Response.data  
      })
      .catch(Error => {
          console.log(Error)
          alert('새로고침 이후에는 홈으로 이동합니다.')
          Route.push("/")
      })
    })
  },

  components:{
    BwBar
  }
}
</script>