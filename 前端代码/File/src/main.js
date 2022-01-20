import Vue from 'vue'
import App from './App.vue'
import router from "./router/index"
import VueRouter from "vue-router"
import axios from "axios"
Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.prototype.$axios = axios

new Vue({
  router: router,
  render: h => h(App),
}).$mount('#app')
