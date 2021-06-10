<template>
  <v-container fluid fill-height>
    <v-card width="100vw">
      <v-data-table
        :headers="headers"
        :items="desserts"
        class="elevation-1"
      >
        <template slot="items" slot-scope="props">
          <td>{{ props.item.idx }}</td>
          <td class="text-xs-right">{{props.item.content}}</td>
          <td class="text-xs-right">{{ props.item.writer }}</td>
          <td class="text-xs-right">{{ props.item.date }}</td>
          <td class="text-xs-right">{{ props.item.views }}</td>
        </template>
      </v-data-table>
    </v-card>
  </v-container>  
</template>


<script>
import { mapState } from "vuex"
  export default {
    data () {
      return {
        headers: [
          {
            text: '게시물 번호',
            align: 'left',
            sortable: false,
            value: 'idx'
          },
          { text: '제목', value: 'content' },
          { text: '작성자', value: 'writer' },
          { text: '작성일자', value: 'date' },
          { text: '조회수', value: 'views' }
        ],
        desserts: []
      }
    },
    created() {
      this.$store.dispatch('BoardList')
    },
    computed: {
      ...mapState(["boardlist"]),
    },
    methods: {

    }
  }
</script>