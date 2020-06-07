import Vue from 'vue'
import App from './app.vue'
import router from './router'
import axios from 'axios'


Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;

/**
 * axios拦截器
 *
 * */
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
