<template>
    <v-container fluid>
        <v-row dense>
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="text-center pa-3" outlined shaped style="height: 40px;" color="White">
                    {{board.bTitle}}
                </v-card>
            </v-col>
        </v-row>
        <v-row dense> 
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="text-center pa-3" outlined tile style="height: 600px;" color="Whtie">
                    {{board.bContent}}
                </v-card>
            </v-col>
        </v-row>
        <v-row dense>
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="pa-3" outlined tile style="height:600px;" color="Withe">
                    <v-toolbar color="#BBDEFB" rounded>
                        <v-toolbar-title>Comment List</v-toolbar-title>
                    </v-toolbar>
                     <v-simple-table>
                        <template v-slot:default>

                            <thead>
                                <tr>
                                    <th class="text-left">댓글 번호</th>
                                    <th class="text-left">ID</th>
                                    <th class="text-left">Content</th>
                                    <th class="text-left">작성일자</th>
                                    <th class="text-left">수정</th>
                                    <th class="text-left">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr
                                    v-for="item in commentlist"
                                    :key="item.cId"
                                >
                                <td>{{item.cId}}</td>
                                <td>{{item.username}}</td>
                                <td>{{item.cContent}}</td>
                                <td>{{item.cDateTime}}</td>
                                <td>
                                    <v-btn small>수정</v-btn>
                                </td>
                                <td>
                                    <v-btn small>삭제</v-btn>
                                </td>
                            </tr>
                            </tbody>
                            
                        </template>
                    </v-simple-table>
                </v-card>
            </v-col>
        </v-row>
        <v-row dense>
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="pa-3" outlined tile style="heght:600px;" color="Withe">
                    <v-toolbar rounded color="#BBDEFB">
                        <v-toolbar-title>Comment Edit</v-toolbar-title>
                    </v-toolbar>
                    <v-form class="pa-3">
                        <v-textarea
                            outlined
                            auto-grow
                            height="50"
                            label="내용을 입력하세요"
                            name="cContent"
                            v-model="cContent"
                        >
                        </v-textarea>
                        <v-btn small
                            @click="CommentWrite(
                                {
                                    cContent:cContent,                                     
                                    username:Userinfo.User_Id,
                                    bId:bId
                                })"
                        >댓글 작성</v-btn>
                    </v-form>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
import Route from '../router/index'
import { mapState } from 'vuex'

export default {
    props: ['bId'],
    data(){
        return{
            board: {}, // Object Type은 null 대신 {} 객체로 받아야함
            cContent: null,
            username:null
        }
    },
    created(){
        new Promise((resolve, reject) => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
            axios.get(`http://localhost:9000/api/auth/boarddetail/${this.bId}`)
            .then(Response => {
                console.log('return board vo')
                console.log(Response.data)
                Response.data === this.board
                this.board = Response.data
            })
            .catch(Error => {
                console.log(Error)
                alert('새로고침 이후에는 홈으로 이동합니다.')
                Route.push("/")
            })
        })
        new Promise((resolve, reject) =>{
            axios.get('http://localhost:9000/api/auth/commentlist')
            .then(Response => {
                console.log('CommentList 뿌리기')
                console.log(Response.data)
                this.$store.commit('READ_COMMENT_LIST', Response.data)
            })
            .catch(Error => {
                console.log('Error')
                alert('에러 발생!')
                Route.push("/")
            })
        })
    },

    methods: {
        CommentWrite(payload) {
            new Promise((resolve, reject) => {
                axios.defaults.headers.common['Authoriztion'] = `Bearer ${this.$store.state.Userinfo.User_token}`
                axios.post('http://localhost:9000/api/auth/commentwrite', payload)
                .then(Response => {
                    console.log('CommentWrite Run')
                    console.log(payload)
                    console.log(Response.data)
                    if(Response.data === "success") {
                        // this.$store.commit('READ_COMMENT_LIST', payload)
                        Route.push("/")
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
        },
    computed:{
        ...mapState(['Userinfo', 'commentlist'])
    }
}    
</script>