module.exports = {
  outputDir : "../main/resources/static",
  "transpileDependencies": [
    "vuetify"
  ],

  devServer: {
    proxy: {
      '/':{
        "target":'https://kapi.kakao.com',
        "pathRewrite":{'^/':''},
        "changeOrigin":true,
        "secure":false
      }
    }
  }
}    