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
                      username:Userinfo.User_Id
                    }
                  )">등록
                </v-btn>
                </v-card-actions>
            </v-card>
        </v-flex>
    </v-layout>
</v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'

export default {
  data(){
    return {
      bTitle : null,
      bContent : null,
      username: null
    }
  },
  computed:{
    ...mapState(['Userinfo'])
  },
  methods: {
    ...mapActions(['BoardWrite']),
  }
}
</script>