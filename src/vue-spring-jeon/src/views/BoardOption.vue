<template>
  <v-simple-table>
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
            @click="insertLogo({
              fileinput:fileinput
            })">
              등록
            </v-btn>
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
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
        return new Promise((resolve, reject) => {
          let formData = new FormData(); // 페이지 전환 없이 폼 데이터를 제출 하고 싶을 때 FormData 객체를 사용
          formData.append('uploadFile', payload.fileinput) // key(uploadFile), value(payload)
          axios.post('http://localhost:9000/api/auth/upload', formData,
            {
            headers: {
              'Content-Type': 'multipart/form-data',
              'Access-Control-Allow-Origin': '*'
            }
          })
          .then(Response => {
            console.log(Response.data)
            console.log(payload)
            if(Response.data === "success"){
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