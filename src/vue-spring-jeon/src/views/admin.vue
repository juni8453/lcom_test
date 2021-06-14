<template>
  <div>    
    <div style="height:10%">
      <v-text-field
      label="유저를 검색하세요"
      v-model="search"
      clearable
      style="width:28%"
      append-icon="mdi-magnify"
      @click:append="Search(search)"
      > 
      </v-text-field>
    </div>
    <v-simple-table style="width:90%"
      dense
    >
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-center">
            ID
          </th>
          <th class="text-center">
            NAME
          </th>
          <th class="text-center">
            PHONE
          </th>
          <th class="text-center">
            AUTHORITY
          </th>
          <th class="text-center">
            DETALE IMPORMAION      
          </th>
          <th class="text-center">
            PATCH      
          </th>
          <th class="text-center">
            DELETE      
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in UserList"
          :key="item.username"
        >
          <td>{{ item.username }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.phone }}</td>
          <td>
            <span
              v-for="(items, index) in item.authorities" 
              :key="index"
            >
              {{ items.authority }}
            </span>
          </td>
          <td>
            <v-btn
              router :to="{name:'UserDetail'}"
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
               @click="UserDelete({username:item.username})"
              >mdi-delete</v-icon>
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
    </v-simple-table>
    <div class="text-center">
      <v-pagination
         v-model="Pagination.page"
         :length="Pagination.lastPage"
         circle
      ></v-pagination>
    </div>
  </div>
</template>
<style scoped>

div {
  width:100%;
  height:100%;
  text-align: center;
}
</style>

<script>
import { mapState, mapActions } from "vuex"
export default {
  data(){
    return{
      page: 1, // 변동값 들어가야함
      search:'',
      pageUnit:5, // 한 페이지에 출력될 페이지네이션 갯수 (1,2,3,4,5)
      perPage:5   // 한 페이지에 출력하고 싶은 게시물 갯수 (5개)
    }
  },

  created() {
    this.$store.dispatch('admin', {page: this.page})
    console.log(this.Pagination.page)
  },

  computed: {
      ...mapState(["UserList", "Pagination"])
  },

  methods: {
    ...mapActions(['UserDelete']), 
    // mapActions와 같은 헬퍼 사용 시 payload 정의 안해줘도 store에서 사용 가능
    UserDelete(payload){
      if(confirm('정말로 유저를 삭제하시겠습니까?')===true){
        this.$store.dispatch('UserDelete', payload)
      }
    }
  },
}
</script>