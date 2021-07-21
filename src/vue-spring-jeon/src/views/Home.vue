<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <BwBar></BwBar>
                <v-card class="text-center pa-3" outlined color="white">
                    <v-row dense align="center" justify="space-around">   
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <!-- <v-img
                                    height="250"
                                    :src="`/images/thumb/${imageslist.list[0].iPk}${imageslist.list[0].iName}`"
                                ></v-img> -->
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/202161691626134851400예시사진1.png`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <!-- <v-img
                                    height="250"
                                    :src="`/images/thumb/${imageslist.list[1].iPk}${imageslist.list[1].iName}`"
                                ></v-img> -->
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/202161961626158901268쇼핑몰 예시 3.png`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <!-- <v-img 
                                    height="250"
                                    :src="`/images/thumb/${imageslist.list[2].iPk}${imageslist.list[2].iName}`"
                                >
                                </v-img> -->
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/2021619271626158952165쇼핑몰 예시 6.png`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <!-- <v-img
                                    height="250"
                                    :src="`/images/thumb/${imageslist.list[3].iPk}${imageslist.list[3].iName}`"
                                ></v-img> -->
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
import Footer from '../components/Footer.vue'
import BwBar from '../components/BwBar.vue'
export default {
    data(){
        return{      
        }
    },
    components:{
        BwBar,
        Footer
    },
}
</script>