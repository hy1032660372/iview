import Vue from 'vue';
import iView from 'iview';
import VueRouter from 'vue-router';
import Routers from './router';
import Vuex from 'vuex';
import Util from './libs/util';
import App from './app.vue';
import axios from 'axios';
import 'iview/dist/styles/iview.css';
import VueCookies from 'vue-cookies';
import jquery from 'jquery'

import VueI18n from 'vue-i18n';
import Locales from './locale';
import zhLocale from 'iview/src/locale/lang/zh-CN';
import enLocale from 'iview/src/locale/lang/en-US';

Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(VueI18n);
Vue.use(iView);
Vue.use(VueCookies);

// 自动设置语言
const navLang = navigator.language;
const localLang = (navLang === 'zh-CN' || navLang === 'en-US') ? navLang : false;
const lang = window.localStorage.getItem('language') || localLang || 'zh-CN';

Vue.config.lang = lang;

// 多语言配置
const locales = Locales;
const mergeZH = Object.assign(zhLocale, locales['zh-CN']);
const mergeEN = Object.assign(enLocale, locales['en-US']);
Vue.locale('zh-CN', mergeZH);
Vue.locale('en-US', mergeEN);

window.currentUser = {};
Vue.prototype.$http = axios
Vue.prototype.jquery = jquery
Vue.prototype.server_auth = "/auth"
Vue.prototype.server_account = "/account"
Vue.prototype.server_util = "/util"
Vue.prototype.domainUrl = "localhost"

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    //Vue.prototype.$Spin.show();
    if("/auth/oauth/token" != config.url){
        let token = Vue.prototype.$cookies.get("iView-token");
        let userRole =Vue.prototype.$cookies.get("user_role");
        if(token){
            config.headers['Authorization'] = 'bearer '+ token;
        }
    }
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    //Vue.prototype.$Spin.hide();
    let data = response.data;
    if(response.data.data == undefined){
        switch(data.messageCode){
            case 0:
                Vue.prototype.$Message.success(data.message);
                break;
            case 1:
                Vue.prototype.$Message.info(data.message);
                break;
            case 2:
                Vue.prototype.$Message.warning(data.message);
                break;
            case 3:
                Vue.prototype.$Message.error(data.message);
                break;
            default:
                break;
        }
    }
    return response;
}, function (error) {
    // 对响应错误做点什么
    //Vue.prototype.$Spin.hide();
    // window.$cookies.set("iView-token",'',-1);
    // window.$cookies.set("refresh-iView-token",'',-1);
    // router.replace({
    //     path: 'login',
    //     query: {redirect: router.currentRoute.fullPath}//登录成功后跳入浏览的当前页面
    // });
    if (error.response) {
        switch (error.response.status) {
            // 这里写清除token的代码
            case 400:
                break;
            case 401:
                Vue.prototype.$Message.error("No Permission");
                break;
            case 504:
                Vue.prototype.$Message.error("Server Error");
                break;
            default:
                console.log(error.response.data);
        }
    }
    //return Promise.reject(error.response.data)
    return Promise.reject(error);
});

// 路由配置
const RouterConfig = {
    mode: 'history',
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    Util.title(to.meta.title);
    next();
});

router.afterEach(() => {
    iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});

const store = new Vuex.Store({
    state: {

    },
    getters: {

    },
    mutations: {

    },
    actions: {

    }
});

new Vue({
    el: '#app',
    router: router,
    store: store,
    render: h => h(App)
});