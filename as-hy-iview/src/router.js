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
    path: '/system',
    meta: {
        title: 'system'
    },
    component: (resolve) => require(['./views/index.vue'], resolve),
    children: [
        {
            path: 'roleConfig',
            name: 'role-config',
            meta: {
                parent:'system',
                title: 'roleConfig'
            },
            component: (resolve) => require(['./views/system/roleConfig.vue'], resolve)
        },{
            path: 'menuConfig',
            name: 'menu-config',
            meta: {
                parent:'system',
                title: 'menuConfig'
            },
            component: (resolve) => require(['./views/system/menuConfig.vue'], resolve)
        }, {
            path: '*',
            name: 'error-404',
            meta: {
                title: '404-页面不存在'
            },
            component: (resolve) => require(['./views/404.vue'], resolve)
        }]
},{
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