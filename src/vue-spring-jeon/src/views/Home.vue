<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <BwBar></BwBar>
                <v-card class="text-center pa-3" outlined color="white">
                    <v-row dense align="center" justify="space-around">   
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216163216262242969992.PNG`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216165216262243202644.PNG`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                 <v-img
                                    height="350"
                                    contain
                                    :src="`/images/thumb/20216165216262243202644.PNG`"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
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
import BwBar from '../components/BwBar.vue'
import Footer from '../components/Footer.vue'

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