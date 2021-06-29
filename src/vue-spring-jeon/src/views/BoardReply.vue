<template>
<v-container style="max-width:1000px">
    <v-layout>
        <v-flex xs12>
            <v-card class="elevation-12">
                <v-toolbar
                color="#FBC02D"
                dark
                flat
                >
                <v-toolbar-title>
                  답글 작성
                </v-toolbar-title>
                <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                <v-form>
                  <v-text-field
                  label="제목을 입력하세요"
                  name="bTitle"
                  prepend-icon="mdi-account"
                  v-model="bTitle"
                  type="text"
                  ></v-text-field>

                  <v-textarea
                    outlined
                    auto-grow
                    height="600"
                    label="내용을 입력하세요"
                    name="bContent"
                    v-model="bContent"
                  ></v-textarea>
                </v-form>
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="#FBC02D" 
                  @click="BoardReply({
                      bId:bId,
                      bTitle:bTitle, 
                      bContent:bContent, 
                      username:Userinfo.User_Id, 
                      bGroup:bGroup,     
                      bOrder:bOrder,
                      bDepth:bDepth               
                  })">답글 등록
                </v-btn>
                </v-card-actions>
            </v-card>
        </v-flex>
    </v-layout>
</v-container>
</template>

<script>
import {mapState } from 'vuex'
import axios from 'axios'
import Route from '../router/index'

export default {
props:['bId','bGroup', 'bOrder', 'bDepth'],
  data(){
    return {
      bTitle:null,
      bContent:null,
    }
  },
  computed:{
    ...mapState(['Userinfo'])
  },
  methods: {
    BoardReply(payload){ 
            new Promise((resolve, reject) => {
                axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
                axios.post(`http://localhost:9000/api/auth/boardreply`, payload)
                .then(Response => {
                    console.log('답글 작성')            
                    console.log(payload)
                    console.log(Response.data)
                    if(Response.data === "success") {
                        Route.push("/boardlist")
                    }
                })
                .catch(Error => {
                    console.log(Error)
                })
            })
        }, 
  }
}
</script>