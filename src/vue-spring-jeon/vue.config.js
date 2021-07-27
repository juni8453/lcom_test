module.exports = {
  outputDir : "../main/resources/static",
  "transpileDependencies": [
    "vuetify"
  ],

  // devServer: {
  //   proxy: {
  //     '/v1/payment/ready': {
  //       target: 'https://kapi.kakao.com',
  //       changeOrigin: true,
  //       ws: true,
  //       hotOnly: true,
  //     },
  //   }
  // }

  // devServer: {
    // proxy: {
    //   '/api':{
    //     "target":'https://kapi.kakao.com',
    //     //"pathRewrite":{'^/':''},
    //     "changeOrigin":true,
    //     //"secure":false
    //   }
    // }
  // }
}    