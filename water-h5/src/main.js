import Vue from 'vue'
import './libs/rem'
import App from './App.vue'
import Fastclick from 'fastclick';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
import 'normalize.css/normalize.css'
import VueRouter from 'vue-router'
import Routers from './router/router'
import VueI18n from 'vue-i18n';
Vue.use(VueI18n);
const i18n = new VueI18n({
  locale: 'en',
  messages: {
    'zh': require('@/assets/lang/zh.js'),
    'en': require('@/assets/lang/en.js')
  }
});
const RouterConfig = {
  mode: 'history',
  routes: Routers
}
Vue.use(VueRouter)
const router = new VueRouter(RouterConfig);
router.afterEach(() => {
  window.scrollTo(0, 0)
})
import { Locale } from 'vant';
import enUS from 'vant/es/locale/lang/en-US';

Locale.use('en-US', enUS);
import filters from './filters/index';
Fastclick.attach(document.body);
new Vue({
  el: '#app',
  filters: filters,
  i18n:i18n,
  router: router,
  render: h => h(App),
}).$mount('#app')
