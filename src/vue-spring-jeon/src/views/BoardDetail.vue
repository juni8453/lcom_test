<template>
    <v-container fluid>
        <v-row dense>
            <v-col v-for="n in 1" :key="n" cols="12" md="12" sm="12">
                <v-card class="text-center pa-3" outlined tile style="height: 40px;" color="White">
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
                    Comment List
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>


<script>
import axios from 'axios'

export default {
    props: ['bid'],
    // params로 key 받을 때 소문자로 적을 것
    data(){
        return{
            board: null
        }
    },
    created(){
        new Promise((resolve, reject) => {
            axios.defaults.headers.common['Authorization'] = `Bearer ${this.$store.state.Userinfo.User_token}`
            axios.get(`http://localhost:9000/api/auth/boarddetail/${this.bid}`)
            .then(Response => {
                console.log('return board vo')
                console.log(Response.data)
                this.board = Response.data
            })
            .catch(Error => {
                console.log(Error)
            })
        })
    },
    methods:{
        
        }
    }

</script>