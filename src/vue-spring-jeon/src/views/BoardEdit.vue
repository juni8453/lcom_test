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
                  v-model="board.bTitle"
                  type="text"
                  ></v-text-field>

                  <v-textarea
                    outlined
                    auto-grow
                    height="600"
                    label="내용을 입력하세요"
                    name="bContent"
                    v-model="board.bContent"
                  ></v-textarea>
                </v-form>
                </v-card-text>
                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="#FBC02D" 
                  @click="BoardEdit(board)"
                >
                  수정
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
import { mapActions, mapState } from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
    props:['bId'], //BoardList.vue에서 params로 넘긴 값 (item.bId)
    data(){
        return {
            board: {}
        }
    },
    created(){
        console.log('BoardEdit Run')
        console.log(this.bId)       // props에서 bId 잘 가지고 오는지 확인
        this.getBoard()             // 수정하기전 원래 쓰여있던 제목, 내용 불러오기위한 메서드
        console.log('BoardEidt Run end')

    },
    computed:{
        ...mapState(['Userinfo'])
    },
    components:{
        BwBar,
        Footer
    },
    methods: {
        BoardEdit(payload){ 
            console.log('BoardEdit Run')
            console.log(payload)
            new Promise((resolve, reject) => {
                axios.post(`http://localhost:9000/api/auth/boardedit`, payload)
                .then(Response => {
                    console.log('return board vo')
                    console.log(payload)
                    console.log(Response.data)
                    Route.push('/boardlist')
                })
                .catch(Error => {
                    console.log(Error)
                })
            })
        }, 
        getBoard() {
            new Promise((resolve,reject)=> {
                axios.get(`http://localhost:9000/api/auth/boarddetail/${this.bId}`) // boarddetail 소스 재활용
                .then(Response => {
                    console.log(Response.data)
                    this.board = Response.data
                    console.dir(this.board)
                })
                .catch(Error => {
                    console.log(Error)
                })
            })
        }
    }
}
</script>