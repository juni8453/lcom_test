<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <BwBar></BwBar>
                <v-card class="text-center pa-3" outlined color="white">
                    <v-row dense align="center" justify="space-around">   
                        <v-col cols="12" md="6">
                            <v-card class="pd-3"
                            @click="FirstHomeImg()">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216163216262242969992.PNG`"
                                ></v-img> 
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3"
                            @click="SeconedHomeImg()">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216165216262243202644.PNG`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3"
                            @click="ThirdHomeImg()">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216165216262243202644.PNG`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3"
                            @click="FourthHomeImg()">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216164316262243097033.png`"
                                ></v-img>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-card>  
            </v-col>
           <v-row>
               <v-col>
                   <Footer></Footer>
               </v-col>
           </v-row>
        </v-row>
    </v-container>
</template>

<script>
import {mapState} from 'vuex'
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

export default {
    data(){
        return{      
        }
    },

    created(){
        console.log('쇼핑몰 홈페이지입니다.')
        this.$store.dispatch('homePage')
    },

    computed:{
        ...mapState(['Userinfo'])
    },

    components:{
        BwBar,
        Footer
    },

    methods:{
        FirstHomeImg(){
            console.log('FirstHomeImg Run')
            if(this.Userinfo.User_auth[0] === 'ROLE_ADMIN'){
                if(confirm('이미지를 변경하시겠습니까?')===true){

                }
            }
            if(this.Userinfo.User_auth[0] === 'ROLE_USER'){
                alert('유저 아이디입니다.')
            }
        },

        SeconedHomeImg(){
            console.log('SecondHomeImg Run')
        },

        ThirdHomeImg(){
            console.log('ThirdHomeImg Run')
        },
        FourthHomeImg(){
            console.log('FourtHomeImg Run')
        }
    }
}
</script>