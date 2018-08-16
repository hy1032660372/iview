window.menuList = [{id:'1a',name:'system',url:'',child:[
        {id:'1-1',name:'user',url:'/home/user',parentId:'1'},
        {id:'1-2',name:'role',url:'/home/second',parentId:'1'},
        {id:'1-3',name:'permission',url:'/home/permission',parentId:'1'},
        {id:'1-4',name:'menuConfig',url:'/home/menuConfig',parentId:'1'},
    ]},{id:'2a',name:'secondParent',url:'',child:[
        {id:'2-1',name:'fifth',url:'/home/first',parentId:'2'},
        {id:'2-2',name:'sixth',url:'/home/first',parentId:'2'},
        {id:'2-3',name:'seventh',url:'/home/first',parentId:'2'},
        {id:'2-4',name:'eighth',url:'/home/first',parentId:'2'},
    ]},{id:'3a',name:'thirdParent',url:'',child:[
        {id:'3-1',name:'nineth',url:'/home/first',parentId:'3'},
        {id:'3-2',name:'tenth',url:'/home/first',parentId:'3'},
        {id:'3-3',name:'eleven',url:'/home/first',parentId:'3'},
        {id:'3-4',name:'twlveth',url:'/home/first',parentId:'3'},
    ]}
];
window.headMenuList = [
    {id:'h1',name:'one',url:'url',type:'0'},
    {id:'h2',name:'two',url:'url',type:'0'},
    {id:'h3',name:'three',url:'url',type:'1',child:[
            {id:'userRole',parentId:'h31',title:'UserRole',content:[
                    {id:'admin',name:'Admin'},
                    {id:'user',name:'User'}
                ]},
            {id:'h313',parentId:'h31',type:'1',title:'System',content:[
                    {id:'h3133',name:'UserInfo'},
                    {id:'h3144',name:'Logout'}
                ]},
        ]}];