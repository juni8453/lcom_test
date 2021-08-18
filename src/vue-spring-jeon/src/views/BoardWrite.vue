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
                @click="BoardWrite(
                    {
                      bTitle:bTitle, 
                      bContent:bContent, 
                      username:Userinfo.User_Id,
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
import { mapActions, mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

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

  components:{
    BwBar,
    Footer
  },

  methods: {
      ...mapActions(['BoardWrite'])
  }
}
</script>