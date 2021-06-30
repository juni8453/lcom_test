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
                                    <!-- <th class="text-left">댓글 번호</th> -->
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
                                <!-- <td>{{item.cId}}</td> -->
                                <td>{{item.username}}</td>
                                <td>{{item.cContent}}</td>
                                <td>{{item.cDateTime}}</td>
                                <!-- <td>{{item.cShow}}</td> -->
                                <td>
                                    <v-icon
                                        @click="Show(item)"
                                    >                    
                                    mdi-pencil
                                    </v-icon>
                                </td>
                                <td>
                                    <v-icon
                                    @click="CommentDelete({
                                        bId: item.bId, 
                                        cId: item.cId,
                                        page: page
                                    })"
                                    >mdi-delete</v-icon>
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
                                        page: page
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
        <v-row dense
           >
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="pa-3" outlined tile style="heght:600px;" color="Withe">
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
            username:null,
            page:1,
            pageUnit:5,
            perPage:5,
            show:false,
            // commentList: null,
            
        }
    },
    created(){
        new Promise((resolve, reject) => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
            axios.get(`http://localhost:9000/api/auth/boarddetail/${this.bId}/${this.page}`)
            .then(Response => {
                console.log('return board vo')
                console.log(Response.data)
                this.board = Response.data
                this.$store.commit('READ_COMMENT_LIST', Response.data)
                //state의 Pagination 값을 변화시키고 사용할 수 있게 값 셋팅  
            })
            .catch(Error => {
                console.log(Error)
                alert('새로고침 이후에는 홈으로 이동합니다.')
                Route.push("/")
            })
        })

        // new Promise((resolve, reject) =>{
        //     axios.get(`http://localhost:9000/api/auth/commentlist/${this.bId}`)
        //     .then(Response => {
        //         console.log('CommentList 뿌리기')
        //         console.log(this.bId)
        //         console.log(Response.data)
        //         this.$store.commit('READ_COMMENT_LIST', Response.data)
        //         //state의 Pagination 값을 변화시키고 사용할 수 있게 값 셋팅            
        //     })
        //     .catch(Error => {
        //         console.log('Error')
        //     })
        // })

    },

    methods: {
        CommentEdit(payload){ // payload = {bId, cId, page, username, cContent}
            console.log(payload)
            new Promise((resolve,reject) => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
            axios.post(`http://localhost:9000/api/auth/commentedit/${payload.page}`, payload)
            .then(Response => {
                console.log("Response Data를 받았습니다")
                console.log(Response.data)
                this.$store.commit('READ_COMMENT_LIST', Response.data)
                // this.$store.commit('SET_SHOW', Response.data)
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
                axios.defaults.headers.common['Authoriztion'] = `Bearer ${this.$store.state.Userinfo.User_token}`
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

        // Show(payload){
        //     console.log(payload)
        //     this.show = !this.show            
        // },

        CommentDelete(payload){
            if(confirm('정말로 글을 삭제하시겠습니까?')===true){
                new Promise((resolve, reject) => {
                    axios.defaults.headers.common['Authoriztion'] = `Bearer ${this.$store.state.Userinfo.User_token}`
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
            // this.$store.commit('SET_SHOW', comment)
        },

        // CommentEdit(payload){ // payload = {bId, cId, page, username, cContent}
        //     console.log(payload)
        //     new Promise((resolve,reject) => {
        //     axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
        //     axios.post(`http://localhost:9000/api/auth/commentedit/${payload.page}`, payload)
        //     .then(Response => {
        //         console.log("Response Data를 받았습니다")
        //         console.log(Response.data)
        //         // this.$store.commit('READ_COMMENT_LIST', Response.data)
        //     })
        //     .catch(Error => {
        //         console.log('error')
        //         reject(Error)
        //         alert("Error!")
        //     })
        //     })
        // },
    },

    computed:{
        ...mapState(['Userinfo', 'Pagination','commentlist'])
    }
}    
</script>