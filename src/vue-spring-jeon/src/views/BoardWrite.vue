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
                  @click="
                  [
                    submitFile(fileinput),

                    BoardWrite(
                      {
                        bTitle:bTitle, 
                        bContent:bContent, 
                        username:Userinfo.User_Id,
                      }
                    )
                  ]"
                >등록
                </v-btn>
                </v-card-actions>
            </v-card>
        </v-flex>
    </v-layout>
</v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import axios from 'axios';
import Route from '../router/index'

export default {
  data(){
    return {
      bTitle : null,
      bContent : null,
      username: null,
      fileinput : null
    }
  },
  computed:{
    ...mapState(['Userinfo'])
  },

  methods: {
      ...mapActions(['BoardWrite']),
    
    submitFile(payload) {
      if(payload !== null){ // 파일 업로드 없을 때 실행 x
        console.log('payload를 받았습니다.')
        console.log(payload)
        console.log('submitFile Run')
        console.log("actinos"+payload)
        let formData = new FormData(); // 페이지 전환 없이 폼 데이터를 제출 하고 싶을 때 FormData 객체를 사용
        formData.append('uploadFile', payload); // key(file), value(payload)

        // ----- 여기까지 진행 후 BoardWrite 실행

        return new Promise((resolve, reject) => {
          // axios.defaults.headers.common['Authorization'] = `Bearer ${state.Userinfo.User_token}` 
          // 로그인 시 토큰 확인 후 저장했기 때문에 굳이 필요없는 식 (다른 곳도 마찬가지)
          axios.post('http://localhost:9000/api/auth/upload',
            formData,
            {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Access-Control-Allow-Origin': '*'
            }
          }
        )
        .then(Response => {
          console.log('submitFile의 Response.data를 받았습니다.')
          console.log(Response.data) // Response.data = success
          // Route.push("/fileupload")
          
        })
        .catch(Error => {
            console.log('에러가 발생했습니다.')
            console.log('error')
        })
        })
      }
    }
  }
}
</script>