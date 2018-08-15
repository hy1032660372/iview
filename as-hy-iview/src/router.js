const routers = [{
    path: '/',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/login.vue'], resolve)
}, {
    path: '/login',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/login.vue'], resolve)
}, {
    path: '/home',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/index.vue'], resolve),
    children: [
        {
            path: 'user',
            name: 'user',
            meta: {
                title: 'first'
            },
            component: (resolve) => require(['./views/home/user.vue'], resolve)
        }, {
            path: 'second',
            name: 'second',
            meta: {
                title: 'second'
            },
            component: (resolve) => require(['./views/home/second.vue'], resolve)
        }, {
            path: 'permission',
            name: 'permission',
            meta: {
                title: 'third'
            },
            component: (resolve) => require(['./views/home/permission.vue'], resolve)
        },{
            path: 'menuConfig',
            name: 'menuConfig',
            meta: {
                title: 'menuConfig'
            },
            component: (resolve) => require(['./views/home/menuConfig.vue'], resolve)
        }, {
            path: '*',
            name: 'error-404',
            meta: {
                title: '404-页面不存在'
            },
            component: (resolve) => require(['./views/404.vue'], resolve)
        }]
}, {
    path: '/*',
    name: 'error404',
    meta: {
        title: '404-页面不存在'
    },
    component: (resolve) => require(['./views/404.vue'], resolve)
}];
export default routers;