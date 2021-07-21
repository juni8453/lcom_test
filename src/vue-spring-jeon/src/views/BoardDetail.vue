<template>
    <v-container fluid>
        <BwBar></BwBar>
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
                <v-card class="pa-3" outlined tile style="height:600px;" color="White">
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
                                    <th class="text-left">대댓글 작성</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr
                                    v-for="item in commentlist"
                                    :key="item.cId"
                                >
                                <td>{{item.username}}</td>
                                <td v-if="item.cDepth > 0">
                                    [답글] {{ item.cContent }}  
                                </td>   
                                <td v-else>{{item.cContent}}</td> 
                                <td>{{item.cDateTime}}</td>             
                                <td>
                                    <v-icon
                                         v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                                        @click="Show(item)"
                                        color="amber lighten-1"
                                    >                    
                                    mdi-pencil
                                    </v-icon>
                                </td>
                                <td>
                                    <v-icon
                                    v-if="item.username === Userinfo.User_Id || Userinfo.User_auth.includes('ROLE_ADMIN')"
                                    @click="CommentDelete({
                                        bId: item.bId, 
                                        cId: item.cId,
                                        page: page
                                    })"
                                    color="red lighten-3"
                                        fab
                                    >mdi-delete</v-icon>
                                </td>

                                <td>
                                    <v-btn
                                        @click="ShowReply(item)"                                    
                                        color="grey lighten-1"
                                        fab
                                        small
                                        dark
                                        >
                                        <v-icon>mdi-pencil</v-icon>
                                    </v-btn>
                                </td>       

                                <td v-if="item.cShow2 === true">
                                    <v-textarea
                                        auto-grow
                                        label="대댓글을 작성하세요"
                                        name="cContent"
                                        v-model="cContent"
                                    ></v-textarea>
                                </td>
                                <td 
                                v-if="item.cShow2 === true">
                                    <v-icon
                                    @click="CommentReply({
                                        bId: item.bId,
                                        cId: item.cId,
                                        cContent:cContent,
                                        username:Userinfo.User_Id,
                                        page: page,
                                        cGroup:item.cGroup,
                                        cOrder:item.cOrder,
                                        cDepth:item.cDepth
                                    })"
                                    >mdi-file-document-edit</v-icon>
                                </td>

                                <td v-if="item.cShow === true">
                                    <v-textarea
                                        auto-grow
                                        label="댓글을 수정하세요"
                                        name="cContent"
                                        v-model="cContent"
                                    ></v-textarea>
                                </td>
                                <td v-if="item.cShow === true">
                                    <v-icon
                                    @click="CommentEdit({                                        
                                        bId: item.bId,
                                        cId: item.cId,
                                        cContent:cContent,
                                        username:Userinfo.User_Id,
                                        page: page,
                                        cShow: item.cShow
                                    })"
                                    >mdi-file-document-edit</v-icon>
                                </td>
                            </tr>
                                                     
                            </tbody>
                            <tfoot>
                                <v-pagination
                                v-model="page"
                                :length="Pagination.lastPage"
                                circle
                                @input="move({page:page, bId:board.bId , board:board})"
                                >
                                </v-pagination>
                            </tfoot>
                            
                        </template>
                    </v-simple-table>
                </v-card>
            </v-col>
        </v-row>
        
        <v-row>
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="pa-3" outlined tile style="height:100px;" color="White">
                    미구현
                </v-card>
            </v-col>
        </v-row>    

        <v-row dense
            v-if="$store.state.Show === false"
           >
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="pa-3" outlined tile style="height:300px;" color="White">
                    <v-toolbar rounded color="#BBDEFB">
                        <v-toolbar-title>Comment Edit</v-toolbar-title>
                    </v-toolbar>
                    <v-form class="pa-3">
                        <v-textarea
                            outlined
                            auto-grow
                            label="댓글을 입력하세요"
                            name="cContent"
                            v-model="cContent"
                        >
                        </v-textarea>
                        <v-btn small
                            @click="CommentWrite(
                                {
                                    page:page,
                                    cContent:cContent,                                     
                                    username:Userinfo.User_Id,
                                    bId:bId
                                })"
                        >댓글 작성</v-btn>
                    </v-form>
                </v-card>
            </v-col>
        </v-row>
            <v-col>
                <Footer></Footer>
            </v-col>    
    </v-container>
</template>

<script>
import axios from 'axios'
import Route from '../router/index'
import Footer from '../components/Footer.vue'
import BwBar from '../components/BwBar.vue'

import { mapState } from 'vuex'

export default {
    props: ['bId'],
    data(){
        return{
            board: {}, // Object Type은 null 대신 {} 객체로 받아야함
            cContent: null,
            username:null,
            page:1,
            pageUnit:5,
            perPage:5,
            
        }
    },
    created(){
        new Promise((resolve, reject) => {
            axios.get(`http://localhost:9000/api/auth/boarddetail/${this.bId}/${this.page}`)
            .then(Response => {
                console.log('return board vo')
                console.log(Response.data)
                this.board = Response.data
                this.$store.commit('READ_COMMENT_LIST', Response.data)
                this.$store.commit('InitData') // boarddetail page 접속 시 항상 Show 값 false 고정
                //state의 Pagination 값을 변화시키고 사용할 수 있게 값 셋팅  
            })
            .catch(Error => {
                console.log(Error)
                alert('새로고침 이후에는 홈으로 이동합니다.')
                Route.push("/")
            })
        })
    },

    methods: {
        CommentReply(payload){
            console.log('CommentReply Run')
            console.log(payload)
            new Promise((resolve,reject) => {
            axios.post(`http://localhost:9000/api/auth/commentwrite/${payload.page}`, payload)
            .then(Response => {
                console.log("Response Data를 받았습니다")
                console.log(Response.data)
                this.$store.commit('READ_COMMENT_LIST', Response.data)
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
                alert("Error!")
            })
            })

        },
        
        CommentEdit(payload){ // payload = {bId, cId, page, username, cContent, cShow}
            console.log('payload를 받았습니다.')
            console.log(payload.cShow)
            console.log(payload)
            payload.cShow =! payload.cShow //cShow 다시 true > false로 조정
            console.log(payload.cShow)
            new Promise((resolve,reject) => {
            axios.post(`http://localhost:9000/api/auth/commentedit/${payload.page}`, payload)
            .then(Response => {
                console.log("Response Data를 받았습니다")
                console.log(Response.data)
                this.$store.commit('READ_COMMENT_LIST', Response.data)
                this.$store.commit('SET_SHOW', payload.cShow) 
                this.cContent = null // 댓글 수정 후 cContent 초기화
            })
            .catch(Error => {
                console.log('error')
                reject(Error)
                alert("Error!")
            })
            })
        },

        CommentWrite(payload) {
            new Promise((resolve, reject) => {
                axios.post(`http://localhost:9000/api/auth/commentwrite/${payload.page}`, payload)
                .then(Response => {
                    console.log('CommentWrite Run')
                    console.log(payload)
                    console.log(Response.data)          // 새로 작성된 댓글 insert 이후 Commentlist
                    console.log(Response.data.commentList)
                    this.$store.commit('READ_COMMENT_LIST', Response.data)
                    this.cContent = null // 댓글 입력 후 입력 창 초기화
                })
                .catch(Error => {
                    console.log('error')
                    reject(Error)
                    alert("Error!")
                })
                })
            },
        move(payload){
            console.log('next')
            console.log(payload)
            console.log(payload.page)
            console.log(payload.bId) //bId를 그냥 보내면 계속 초기화되기 때문에 payload로 넘겨줌
            this.$store.dispatch('CommentPaginationList', payload)
        },

        CommentDelete(payload){
            if(confirm('정말로 글을 삭제하시겠습니까?')===true){
                new Promise((resolve, reject) => {
                    axios.post(`http://localhost:9000/api/auth/commentdelete/${payload.page}`, payload)
                    .then(Response => {
                        console.log('payload')
                        console.log(payload)
                        console.log(Response.data)
                        this.$store.commit('READ_COMMENT_LIST', Response.data)
                    })
                    .catch(Error => {
                        console.log('error')
                        reject(Error)
                        alert("Error!")
                    
                    })
                })
             }
        },

        Show(comment){ //commentlist의 배열 인덱스 item
            comment.cShow =! comment.cShow
            console.log(comment)
            console.log(comment.cShow)
            this.$store.commit('SET_SHOW', comment.cShow)
        },

        ShowReply(comment){ 
            comment.cShow2 =! comment.cShow2
            console.log(comment)
            this.$store.commit('SET_SHOW', comment.cShow2)
        },
    },

    computed:{
        ...mapState(['Userinfo', 'Pagination','commentlist'])
    },

    components:{
        Footer,
        BwBar
    }
}    
</script>