const routers = [{
    path: '/',
    meta: {
        title: ''
    },
    component: (resolve) => require(['./views/index.vue'], resolve)
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
            component: (resolve) => require(['./views/first.vue'], resolve)
　　  },{
            path: 'second',
            name: 'second',
            meta: {
                title: 'second'
            },
            component: (resolve) => require(['./views/second.vue'], resolve)
        }]
},{
    path: '/*',
    name: 'error-404',
    meta: {
        title: '404-页面不存在'
    },
    component: (resolve) => require(['./views/404.vue'], resolve)
}];
export default routers;