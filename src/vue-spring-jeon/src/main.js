import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import IMP from 'vue-iamport'
import jQuery from 'jquery'

Vue.config.productionTip = false
window.jQuery = jQuery

export default store

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')

Vue.use(IMP, 'imp65697324') 
Vue.IMP().load()

