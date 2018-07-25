const routers = [{
    path: '/',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/login.vue'], resolve)
},{
    path: '/login',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/login.vue'], resolve)
},{
    path: '/home',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/index.vue'], resolve),
    children: [ 
　　　  { 
　　　　 path: 'first',
        name: 'first',
        meta: {
            title: 'first'
        },
        component: (resolve) => require(['./views/home/first.vue'], resolve)
　　    },{
        path: 'second',
        name: 'second',
        meta: {
            title: 'second'
        },
        component: (resolve) => require(['./views/home/second.vue'], resolve)
    },{
        path: 'third',
        name: 'third',
        meta: {
            title: 'third'
        },
        component: (resolve) => require(['./views/home/third.vue'], resolve)
    },{
        path: '*',
        name: 'error-404',
        meta: {
            title: '404-页面不存在'
        },
        component: (resolve) => require(['./views/404.vue'], resolve)
    }]
},{
    path: '/*',
    name: 'error404',
    meta: {
        title: '404-页面不存在'
    },
    component: (resolve) => require(['./views/404.vue'], resolve)
}];
export default routers;