+<template>
  <v-container fluid>
    
    <v-row dense>
      <v-col cols="12" md="12" sm="12">
        <v-card class="text-center pa-3" outlined>
          쇼핑몰 설정 페이지
        </v-card>
      </v-col>
    </v-row>

    <v-row dense>
      <v-col cols="3" md="3">
        <v-card class="pa-6 text-center" outlined height="140">
          첫번째 로고 이미지 설정
          <v-btn class="pa-1 text-center" outlined
           @click="insertLogo({
              fileinput:fileinput
            })"
          >적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 첫번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col cols="6" md="6" class="text-left">
            <v-file-input
              id="uploadFile"
              label="파일을 업로드하세요"
              v-model="fileinput"
            >
            </v-file-input>
          </v-col>
        </v-card>
      </v-col>
    </v-row>

    <v-row dense>
      <v-col cols="3" md="3">
        <v-card class="pa-6 text-center" outlined height="140">
          두번째 로고 이미지 설정  
          <v-btn class="pa-1 text-center" outlined
           @click="insertLogo({
              fileinput:fileinput
            })"
          >적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 두번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col cols="6" md="6" class="text-left">
            <v-file-input
              id="uploadFile"
              label="파일을 업로드하세요"
              v-model="fileinput"
            >
            </v-file-input>
          </v-col>
        </v-card>
      </v-col>
    </v-row>

    <v-row dense>
      <v-col cols="3" md="3">
        <v-card class="pa-6 text-center" outlined height="140">
          세번째 로고 이미지 설정
          <v-btn class="pa-1 text-center" outlined
           @click="insertLogo({
              fileinput:fileinput
            })"
          >적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 세번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col cols="6" md="6" class="text-left">
            <v-file-input
              id="uploadFile"
              label="파일을 업로드하세요"
              v-model="fileinput"
            >
            </v-file-input>
          </v-col>
        </v-card>
      </v-col>
    </v-row>

    <v-row dense>
      <v-col cols="3" md="3">
        <v-card class="pa-6 text-center" outlined height="140">
          네번째 로고 이미지 설정
          <v-btn class="pa-1 text-center" outlined
           @click="insertLogo({
              fileinput:fileinput
            })"
          >적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 네번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col cols="6" md="6" class="text-left">
            <v-file-input
              id="uploadFile"
              label="파일을 업로드하세요"
              v-model="fileinput"
            >
            </v-file-input>
          </v-col>
        </v-card>
      </v-col>
    </v-row>

  </v-container>  
</template>

<script>
import axios from 'axios'
import Route from '../router/index'

  export default {
    data () {
      return {
        fileinput:null
      }
    },
    methods:{
      insertLogo(payload){
        console.log('insetLogo')
        console.log(payload)
        console.log('payload.name:' +payload.fileinput.name)
        console.log('payload.lastModified:' +payload.fileinput.lastModified)
        new Promise((resolve, reject) => {
          let formData = new FormData();                    // 페이지 전환 없이 폼 데이터를 제출 하고 싶을 때 FormData 객체를 사용
          formData.append('uploadFile', payload.fileinput)  // key(uploadFile), value(payload)
          formData.append('iName',payload.fileinput.name)
          formData.append('iPk', payload.fileinput.lastModified)
          axios.post('http://localhost:9000/api/admin/logoupload', formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data',
            'Access-Control-Allow-Origin': '*'
          }
        })
          .then(Response => {
            console.log('Response.data를 받았습니다.')
            console.log(Response.data)
            this.$store.commit('SET_IMAGES_LIST', Response.data)
            Route.push('/')
          })
          .catch(Error => {
            console.log(Error)
            alert('Error 발생 !')
          })
        })
      }
    },
  }
</script>