<template>
  <BwBar></BwBar>

</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import BwBar from '../components/BwBar.vue'

export default {
  data(){
    return{

    }
  },
  components:{
    BwBar
  },

  created(){
    console.log('PutCart Run!')
  },

  computed:{
    ...mapState['Userinfo']
  }
}
</script>