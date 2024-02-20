import Vue from 'vue';
import App from './App.vue';
import store from './store';
import global from './assets/global.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router';
import router from './router';
Vue.use(VueRouter);
Vue.use(ElementUI);
import axios from 'axios';
Vue.prototype.$axios = axios;
Vue.config.productionTip = false
//将地址设置为全局
Vue.prototype.$httpUrl='http://localhost:8088'
new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
