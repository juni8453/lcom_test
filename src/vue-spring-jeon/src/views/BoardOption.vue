+<template>
  <!-- <v-simple-table>
    <template v-slot:default>
      <tbody>
        <tr>
          <th rowspan="2">상단로고이미지</th>
          <td colspan="4">쇼핑몰 상단로고를 직접 올릴 수 있습니다.</td>
        </tr>
        <tr>
          <td>
            <v-file-input
              id="uploadFile"
              label="클릭해서 파일을 업로드하세요"
              v-model="fileinput"
              ></v-file-input>
            선택된 파일 없음
          </td>
          <td>
            <v-btn small>
              삭제
            </v-btn>
          </td>
          <td>
            <v-btn small
            type="button"
            @click="insertLogo({
              fileinput:fileinput
            })">
              등록
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table> -->
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
          <v-btn class="pa-1 text-center" outlined>적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 두번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col class="text-left">
            <v-btn>파일 선택</v-btn>
          </v-col>
        </v-card>
      </v-col>
    </v-row>
    <v-row dense>
      <v-col cols="3" md="3">
        <v-card class="pa-6 text-center" outlined height="140">
          세번째 로고 이미지 설정
          <v-btn class="pa-1 text-center" outlined>적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 세번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col class="text-left">
            <v-btn>파일 선택</v-btn>
          </v-col>
        </v-card>
      </v-col>
    </v-row>
    <v-row dense>
      <v-col cols="3" md="3">
        <v-card class="pa-6 text-center" outlined height="140">
          네번째 로고 이미지 설정
          <v-btn class="pa-1 text-center" outlined>적용</v-btn>
        </v-card>
      </v-col>
      <v-col cols="9" md="9">
        <v-card class="pa-3 text-center" outlined height="140">
          <v-col class="text-left">
            쇼핑몰 네번째 로고를 직접 올릴 수 있습니다. 이미지 파일만 가능합니다.
          </v-col>
          <v-col class="text-left">
            <v-btn>파일 선택</v-btn>
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

      // insertLogo(payload) {
      //   console.log('payload를 받았습니다.')
      //   console.log(payload)
      //   this.$store.dispatch('insertLogo', payload)
      // }

      insertLogo(payload){
        console.log('insetLogo')
        console.log(payload)
        console.log('payload.name:' +payload.fileinput.name)
        console.log('payload.lastModified:' +payload.fileinput.lastModified)
        new Promise((resolve, reject) => {
          let formData = new FormData(); // 페이지 전환 없이 폼 데이터를 제출 하고 싶을 때 FormData 객체를 사용
          formData.append('uploadFile', payload.fileinput) // key(uploadFile), value(payload)
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
            console.log('fileinput'+payload.fileinput)
            // Route.push("/")
            if(Response.data === "success"){
              this.$store.commit('SET_IMAGES', payload.fileinput)
              Route.push("/")
            }

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