<template>
    <v-container fluid>
        <v-row dense>
            <v-col cols="12" md="12" sm="12">
                <v-card class="text-center pa-3" outlined color="black">
                   <v-row dense align="center" justify="space-around">
                       <v-col cols="2">
                           <v-btn text color="white"
                            router :to="{name:'BoardList'}"
                           >
                                커뮤니티 바로가기
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text color="white">
                                쇼핑몰 바로가기
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text color="white">
                                FAQ
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text color="white">
                                1:1문의
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text color="white">
                                개인결제
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text color="white">
                                사용후기
                            </v-btn>
                       </v-col>
                   </v-row>
                </v-card>

                <v-card class="text-center pa-3" outlined color="white">
                   <v-row dense align="center" justify="space-around">
                       <v-col cols="4">
                            Jeon Shopping Mall
                       </v-col>
                       <v-col cols="2">
                           <v-btn text>
                                인기상품
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text>
                                할인상품
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text>
                                최신상품
                            </v-btn>
                       </v-col>
                       <v-col cols="2">
                           <v-btn text>
                                추천상품
                            </v-btn>
                       </v-col>    
                   </v-row>
                </v-card>   

                <v-card class="text-center pa-3" outlined color="white">
                    <v-row dense align="center" justify="space-around">   
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <v-img
                                    height="250"
                                    src="https://cdn.vuetifyjs.com/images/cards/house.jpg"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <v-img
                                    height="250"
                                    src="https://cdn.vuetifyjs.com/images/cards/house.jpg"
                                ></v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <v-img 
                                    height="250"
                                    :src="`/images/thumb/${myFilename}`">
                                </v-img>
                            </v-card>
                        </v-col>
                        <v-col cols="12" md="6">
                            <v-card class="pd-3">
                                <v-img
                                    height="250"
                                    src="https://cdn.vuetifyjs.com/images/cards/plane.jpg"
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
export default {
    data(){
        return{
            //Test value
            myFilename: "zzz.png",
            // Filename: this.$store.state.setImages,            
        }
    },
   

   
}
</script>