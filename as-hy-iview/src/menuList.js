window.menuList = [];
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