<template>
  <v-data-table
    :headers="headers"
    :items="desserts"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>
</template>

<script>
  export default {
    data () {
      return {
        headers: [
          {
            text: 'ID',
            align: 'start',
            sortable: false,
            value: 'userid',
          },
          { text: 'NAME', value: 'name' },
          { text: 'PHONE', value: 'phone' },
          { text: 'AUTH', value: 'auth' },
        ],

        desserts: [
          {
            userid: 'jbj',
            name: '관리자', //value
            phone: '010-0000-0000',
            auth: 'ROLE_ADMIN'
          },
          {
            userid: 'juni8453',
            name: '홍길동',
            phone: '010-1111-2222',
            auth: 'ROLE_USER'
          },
        ],
      }
    },
  }
</script>

