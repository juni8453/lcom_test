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
                                    height="250"
                                    :src="`/images/thumb/1.jpg`"
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
                                    height="250"
                                    :src="`/images/thumb/2.jpg`"
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
                                    height="250"
                                    :src="`/images/thumb/3.jpg`"
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
                                    height="250"
                                    :src="`/images/thumb/4.png`"
                                ></v-img>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-card>  

            </v-col>
            <v-footer>
                <v-card>
                    <v-card-text class="indigo lighten-1 white--text text-center" flat tile>
                       <v-row>
                           <v-col>
                                Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet. Mauris cursus commodo interdum. Praesent ut risus eget metus luctus accumsan id ultrices nunc. Sed at orci sed massa consectetur dignissim a sit amet dui. Duis commodo vitae velit et faucibus. Morbi vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices, cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor vel ut orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.
                           </v-col>
                       </v-row>
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-text class="black--text">
                        {{ new Date().getFullYear() }} — <strong>Jeon Shopping Mall</strong>
                    </v-card-text>
                </v-card>
            </v-footer>   
        </v-row>
    </v-container>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import BwBar from '../components/BwBar.vue'
export default {
    data(){
        return{      
        }
    },
    components:{
        BwBar
    },
    created() {
        // new Promise((resolve, reject) => {
        // axios.get(`http://localhost:9000/api/auth/home`)
        // .then(Response => {
        //     console.log('Home의 created Response.data를 받았습니다.')
        //     console.log(Response.data)
        //     this.$store.commit('SET_IMAGES_LIST', Response.data)
        // })
        // .catch(Error => {
        //     console.log(Error)
        // })
        // })
    },
    
    // computed:{
    //     ...mapState(['imageslist'])
    // },

}
</script>