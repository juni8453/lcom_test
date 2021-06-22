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
                                    <th class="text-left">ID</th>
                                    <th class="text-left">Content</th>
                                    <th class="text-left">작성일자</th>
                                    <th class="text-left">수정</th>
                                    <th class="text-left">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                <td>jbj</td>
                                <td>Content Form</td>
                                <td>2021-06-21</td>
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
                        <v-btn small>댓글 작성</v-btn>
                    </v-form>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>


<script>
import axios from 'axios'
import Route from '../router/index'

export default {
    props: ['bId'],
    // params로 key 받을 때 소문자로 적을 것
    data(){
        return{
            board: {} // Object Type은 null 대신 {} 객체로 받아야함
        }
    },
    created(){
        new Promise((resolve, reject) => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
            axios.get(`http://localhost:9000/api/auth/boarddetail/${this.bId}`)
            .then(Response => {
                console.log('return board vo')
                console.log(Response.data)
                this.board = Response.data
                console.log(this.board)
            })
            .catch(Error => {
                console.log(Error)
                alert('새로고침 이후에는 홈으로 이동합니다.')
                Route.push("/")
            })
        })
    },
    methods: {
    }
}

</script>