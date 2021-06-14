<template>
  <div>
    <v-container>
      <v-row no-gutters justify="left">  
        <v-simple-table style="width:90%"
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
                수정
              </th>
             <th class="text-center">
                삭제
              </th>      
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="item in calData"
              :key="item.bId"
              >
              <td>{{ item.bId }}</td>
              <td>{{ item.bTitle }}</td>        
              <td>{{ item.username }}</td>
              <td>{{ item.bDateTime}}</td>
              <td>{{ item.bViews}}</td>
              <td>
                <v-btn
                  router :to="{name:'BoardDetail'}"
                >
                  <v-icon>mdi-note-search</v-icon>
                </v-btn>
              </td>
              <td>
                <v-btn>
                  <v-icon>mdi-file-document-edit</v-icon>
                </v-btn>
              </td>
              <td>
                <v-btn>
                  <v-icon
                  @click="BoardDelete({bId:item.bId})"
                  >mdi-delete</v-icon>
                </v-btn>
              </td>  
            </tr>
          </tbody>
        </template>
        </v-simple-table>
      </v-row>
    </v-container>  
  <div>
      <v-container>  
        <v-row no-gutters justify="center" class="pa-8"
        ><v-pagination
          v-model="curPageNum"
          :length="numOfPages" 
          ></v-pagination>
        </v-row>
      </v-container>  
    </div>  
  </div>
</template>
<style scoped>

div {
  width:100%;
 /* height:100%;*/
  text-align: center;
}

</style>

<script>
import { mapState, mapActions } from "vuex"
export default {
  data(){
    return{
      curPageNum: 1,    // 현재 UI에 보여지고 있는 페이지 숫자
      dataPerPage: 5,   // 한 페이지당 보여지는 boardlist 갯수
      search: '',
      // items: ['제목', '아이디'],
      // message3: 'Hey!',
    }
  },
    created() {
      this.$store.dispatch('BoardList')
    },
    computed: {
      ...mapState(["boardlist"]),
      startOfset(){
        return((this.curPageNum-1)*this.dataPerPage)
      },
      endOfset(){
        return((this.startOfset + this.dataPerPage))
      },
      numOfPages(){
        return Math.ceil(this.boardlist.length / this.dataPerPage)
      },
      calData(){
        return this.boardlist.slice(this.startOfset, this.endOfset)
      }
    },
  methods: {
    BoardDelete(payload){
      if(confirm('정말로 글을 삭제하시겠습니까?')===true){
        this.$store.dispatch('BoardDelete', payload)
      }
    }
  },
}  
</script>