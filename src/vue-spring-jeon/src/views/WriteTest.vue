<template>
<v-container>
  <BwBar></BwBar>
    <v-layout>
        <v-flex xs12>
          <v-card class="elevation-12">
              <v-toolbar
              color="#FBC02D"
              dark
              flat
              >
              <v-toolbar-title>
                게시판 글 작성
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

                <v-file-input
                  id="uploadFile"
                  label="클릭해서 파일을 업로드하세요"
                  v-model="fileinput"
                  multiple
                ></v-file-input>      

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
                @click="WriteTest(
                    {
                      bTitle:bTitle, 
                      bContent:bContent, 
                      username:username,
                      fileinput: fileinput
                    }
                  )"
              >등록
              </v-btn>
              </v-card-actions>
          </v-card>
        </v-flex>
    </v-layout>
    <Footer></Footer>
</v-container>
</template>

<script>
import axios from 'axios'
import Route from '../router/index'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
  data(){
    return {
      bTitle : null,
      bContent : null,
      username : this.$store.state.Userinfo.User_Id,
      fileinput : null
    }
  },

  // computed:{
  //   ...mapState(['Userinfo'])
  // },

  components:{
    BwBar,
    Footer
  },

  methods: {
    WriteTest(payload){
      console.log(payload)
      console.log(payload.fileinput)
      if(payload.fileinput === null){
        new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/auth/boardwrite', payload)
            .then(Response => {
              console.log(Response.data)
              if(Response.data === "success") {
                Route.push("/boardlist")
              }
          })
          .catch(Error => {
              console.log('error')
              reject(Error)
              alert("Error!")
              Route.push("/boardlist")
            })
          })
        }
    
    else{
      new Promise((resolve, reject) => {
        console.log('WriteTest Run')
        let formData = new FormData();
        formData.append('bTitle', payload.bTitle)               
        formData.append('bContent', payload.bContent)
        formData.append('username', payload.username)

        for(let i=0; i<payload.fileinput.length; i++){          
          let file = payload.fileinput[i]
          console.log(file)

          formData.append('uploadFile', file) 
        }   
        axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`  
        axios.post('http://localhost:9000/api/auth/writetest', formData,
          {
            headers: {
              'Content-Type': 'multipart/form-data',
              'Access-Control-Allow-Origin': '*'
          }
        })
          .then(Response => {
            console.log('Resonse.data를 받았습니다.')
                  
            if(Response.data === "success") {
              Route.push("/boardlist")
            } 
        })
        .catch(Error => {
            console.log('error')
            reject(Error)
            alert("Error!")
            Route.push("/")
          })
        })
      }
    }, // 글 업로드 테스트
  }
}
</script>