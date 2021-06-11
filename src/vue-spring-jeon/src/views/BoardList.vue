<template>
  <div>  
    <!-- <v-container>
      <v-row align="center">
        <v-col
          class="d-flex"
          cols="12"
          sm="2"
        >
          <v-select
            :items="items"
            label="선택하세요"
            dense
            outlined
          ></v-select>
        </v-col>  
          <v-col
            class="d-flex"
            cols="12"
            sm="5"
          >
            <v-text-field
              v-model="message3"
              filled
              label="입력해주세요"
              clearable
            ></v-text-field>
          </v-col>
      </v-row>
    </v-container> -->
    <v-simple-table style="width:90%"
      dense
    >
    <template v-slot:default>
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
        </tr>
      </tbody>
    </template>
    </v-simple-table>
    <div>
      <v-pagination
      v-model="curPageNum"
      :length="numOfPages" 
    ></v-pagination>
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
      dataPerPage: 10,   // 한 페이지당 보여지는 boardlist 갯수
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
    SearchBoard(keyword){
      let payload = {
        params: {
          keyword:keyword
        }
      }
      this.$store.dispatch('BoardList', payload)  
    }
  },
}  
</script>