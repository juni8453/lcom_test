<template>
    <v-container>
      <BwBar></BwBar>
      <v-row no-gutters>  
        <v-simple-table style="width:100%"
          dense
        >
        <template 
         v-slot:default
        >
          <thead>
            <tr>
              <th class="text-center">
                번호
              </th>
              <th class="text-center">
                제목
              </th>
              <th class="text-center">
                작성자
              </th>
             <th class="text-center">
                작성일자
             </th>
              <th class="text-center">
                조회수
              </th>
             <th class="text-center">
                글 상세보기
              </th>
              <th class="text-center">
                대댓글 작성  
              </th>      
             <th class="text-center">
                수정
              </th>
              <th class="text-center">
                삭제
              </th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="item in boardlist"
              :key="item.bId"
              >
              <td>{{ item.bId }}</td>

              <td v-if="item.bDepth > 0">
                [답글] {{ item.bTitle }}  
              </td>       
        
              <td v-else>{{item.bTitle}}</td> 
              <td>{{ item.username }}</td>
              <td>{{ item.bDateTime}}</td>
              <td>{{ item.bViews}}</td>             
              <td>
                <v-btn
                  router :to="{name:'BoardDetail', 
                  params: {bId:item.bId} }"
                >
                  <v-icon
                  >mdi-note-search</v-icon>
                </v-btn>
              </td>

              <td>
                <v-btn
                  router :to="{name:'BoardReply',
                  params: {
                    bId:item.bId, 
                    bGroup: item.bGroup, 
                    bOrder: item.bOrder, 
                    bDepth: item.bDepth
                    } 
                  }"
                >
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>  
              </td>  

              <td>
                <v-btn
                  v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                  router :to="{name:'BoardEdit',
                  params: {bId:item.bId} }"
                >
                  <v-icon>mdi-file-document-edit</v-icon>
                </v-btn>
              </td>

              <td>
                <v-btn
                v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                >
                  <v-icon
                    @click="BoardDelete({
                    bId: item.bId, 
                    page: page
                    })"
                  >mdi-delete</v-icon>
                </v-btn>
              </td>              
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-row>
    <v-row>
      <v-col cols="12" md="12" sm="12">
       
          <v-pagination
            v-model="page"
            :length="Pagination.lastPage" 
            circle
            @input="move({page:page})"
          ></v-pagination>
       
      </v-col>
    </v-row>
    <!-- <v-row>
      <v-col>
        <Footer></Footer>
      </v-col>
    </v-row> -->
  </v-container> 
</template>
<style scoped>
div {
  width:100%;
 /* height:100%;*/
  text-align: center;
}
</style>

<script>
import { mapState } from "vuex"
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data(){
    return{
      search: '',
      page:1,
      pageUnit:5,
      perPage:5,
    }
  },

    created() {
      this.$store.dispatch('BoardList', {page:this.page})
    },

    computed: {
      ...mapState(["boardlist", 'Pagination', 'Userinfo']),
    },

    components:{
      Footer,
      BwBar
    },

    methods: {
      BoardDelete(payload){
        if(confirm('정말로 글을 삭제하시겠습니까?')===true){
          this.$store.dispatch('BoardDelete', payload)
        }
      },

      move(payload){
        console.log('next')
        console.log(payload)
        this.$store.dispatch('BoardList', payload)
      }
    }
  }  
</script>