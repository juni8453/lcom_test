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
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in calData"
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
  height:100%;
  text-align: center;
}
</style>

<script>
import { mapState, mapActions } from "vuex"
export default {
  data(){
    return{
      curPageNum: 1,    // 현재 UI에 보여지고 있는 페이지 숫자
      dataPerPage: 20,   // 한 페이지당 보여지는 UserList 갯수
      search:''
    }
  },

  created() {
    this.$store.dispatch('admin')
  },

  computed: {
      ...mapState(["UserList"]),
      startOfset(){
        return((this.curPageNum-1)*this.dataPerPage)
      },
      endOfset(){
        return (this.startOfset + this.dataPerPage)
      },
      numOfPages(){
        return Math.ceil(this.UserList.length / this.dataPerPage)
      },
      calData(){
        return this.UserList.slice(this.startOfset, this.endOfset)
      },
  },

  methods: {
    Search(keyword){
      console.log('Search run')
      let payload = {
        params: {
          keyword: keyword
        }
      }; // params로 넘겨주는 방법
      this.$store.dispatch('admin', payload)
      //  let tmp = {params: {ID: 12345}} 예시

    }
  },
}
</script>