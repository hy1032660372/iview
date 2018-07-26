<style scoped>
    .layout-con{
        height: 100%;
        width: 100%;
    }
    .menu-item span{
        display: inline-block;
        overflow: hidden;
        width: 69px;
        text-overflow: ellipsis;
        white-space: nowrap;
        vertical-align: bottom;
        transition: width .2s ease .2s;
    }
    .menu-item i{
        transform: translateX(0px);
        transition: font-size .2s ease, transform .2s ease;
        vertical-align: middle;
        font-size: 16px;
    }
    .collapsed-menu span{
        width: 0px;
        transition: width .2s ease;
    }
    .collapsed-menu i{
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }
    .layout-nav-right{
        float: right;
    }
    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-content {
        height: 120px;
        margin-top: -16px;
    }

    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-content > .ivu-tabs-tabpane {
        background: #fff;
        padding: 16px;
    }

    .demo-tabs-style1 > .ivu-tabs.ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab {
        border-color: transparent;
    }

    .demo-tabs-style1 > .ivu-tabs-card > .ivu-tabs-bar .ivu-tabs-tab-active {
        border-color: #fff;
    }
</style>
<template>
    <div class="layout">
        <Layout>
            <Menu mode="horizontal" theme="dark" active-name="1" :style="{position:'fixed', width: '100%',zIndex:'9999'}">
                <div class="layout-logo"></div>
                <div class="layout-nav">
                    <MenuItem name="1">
                        <img width="50px" src="../images/The_death.png"/>
                    </MenuItem>
                </div>
                <div class="layout-nav-right">
                    <MenuItem v-for="headMenu in headMenuList" :key="headMenu.id" :name="headMenu.name">
                        <span v-if="headMenu.type=='0'">
                            <Icon type="ios-people"></Icon>
                            {{headMenu.name}}
                        </span>
                        <span v-else-if="headMenu.type=='1'">
                            <Submenu :name="headMenu.name">
                                <template slot="title">
                                    <Icon type="stats-bars"></Icon>
                                    {{headMenu.name}}
                                </template>
                                <MenuGroup v-for="con in headMenu.child" :key="con.id" :title="con.title">
                                    <MenuItem v-for="c in con.content" :key="c.id" :name="c.name">{{c.name}}</MenuItem>
                                </MenuGroup>
                            </Submenu>
                        </span>
                    </MenuItem>
                </div>
            </Menu>
            <Layout :style="{padding: '60px 0 0 0',minHeight: '100vh'}">
                <Sider collapsible :collapsed-width="100" v-model="isCollapsed">
                    <Menu :active-name="nowPage.name" theme="dark" width="auto" :open-names="openNames" :class="menuItemClasses" @on-select="toOtherPage">
                        <Submenu v-for="(menu) in menuList" :key="menu.id" :name="menu.name">
                            <template slot="title">
                                <Icon type="ios-navigate"></Icon>
                                <span>{{menu.name}}</span>
                            </template>
                            <MenuItem v-for='(ch,index) in menu.child' :key='ch.id' :name="ch.name"><span>{{ch.name}}</span>{{index}}</MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Content :style="{height:'100%'}">
                    <router-view></router-view>
                    <!--<Row>
                        <Col span="24" class="demo-tabs-style1" style="background: #e3e8ee;padding:16px;">
                            <Tabs type="card" :closable='tabs.length>1' @on-tab-remove="handleTabRemove" @on-click="toOtherPage" :value="nowPage.name">
                                <TabPane v-for="tab in tabs" :key="tab.id" :label="tab.name" :name="tab.name">

                                </TabPane>
                            </Tabs>
                        </Col>
                    </Row>-->
                </Content>
            </Layout>
        </Layout>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                isCollapsed: false,
                tabs:[],
                openNames:[],
                nowPage:{},
                userInfo:{},
                menuList: [{id:'1a',name:'system',url:'',child:[
                        {id:'1-1',name:'user',url:'/home/first',parentId:'1'},
                        {id:'1-2',name:'role',url:'/home/second',parentId:'1'},
                        {id:'1-3',name:'permission',url:'/home/third',parentId:'1'},
                        {id:'1-4',name:'forth',url:'/home/forth',parentId:'1'},
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
                ],
                headMenuList:[{id:'h1',name:'one',url:'url',type:'0'},
                    {id:'h2',name:'two',url:'url',type:'0'},
                    {id:'h3',name:'three',url:'url',type:'1',child:[
                        {id:'h31',parentId:'h31',title:'title1',content:[
                            {id:'h311',name:'h31'},
                            {id:'h312',name:'h31'},
                            {id:'h313',name:'h31'},
                            {id:'h314',name:'h31'}
                        ]},
                        {id:'h313',parentId:'h31',type:'1',title:'title2',content:[
                            {id:'h3133',name:'h312'},
                            {id:'h3144',name:'h313'}
                        ]},
                    ]}]
            };
        },
        computed: {
            menuItemClasses: function () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
        mounted(){
            let vm = this;
            vm.verificationToken();
            if(vm.tabs.length == 0){
                let tab = {
                    id:vm.menuList[0].child[0].id,
                    name:vm.menuList[0].child[0].name,
                    url:vm.menuList[0].child[0].url,
                };
                vm.nowPage = tab;
                vm.tabs.push(tab);
                vm.$router.push(tab.url);
            }
            vm.openNames.push(vm.menuList[0].name);
        },
        methods: {
            verificationToken(){
                let vm = this;
                let token = window.getCookie("token");
                let userRole = window.getCookie("user_role");
                vm.getUserInfo();
                // if(token){
                //     vm.$http.post(vm.server_auth+"/oauth/check_token?token="+token).then(function(data){
                //         console.log(data);
                //         vm.getUserInfo();
                //     });
                // }else{
                //     vm.$router.push('/login')
                // }
            },
            getUserInfo(){
                let vm = this;
                vm.$http.get(vm.server_auth+"/users/current").then(function(data){
                    vm.userInfo = data.data.principal;
                    vm.headMenuList[2].name = vm.userInfo.username;
                }).catch(function (error) {
                    vm.$Message.success('Error!');
                    vm.$router.push('/login')
                });
            },
            toOtherPage(val){
                let vm = this;
                let isGet = false;
                for(let menu of vm.menuList){
                    for(let ch of menu.child){
                        if(ch.name == val){
                            vm.nowPage = {
                                id:ch.id,
                                name:ch.name,
                                url:ch.url,
                            };
                            isGet = true;
                            break;
                        }
                    }
                    if(isGet){
                        break;
                    }
                }
                vm.$router.push(vm.nowPage.url);
            },
            /*toOtherPage (val) {
                let vm = this;
                let isExit = false;
                for(let tab of vm.tabs){
                    if(tab.name == val ){
                        isExit = true;
                        vm.nowPage = {
                            id:tab.id,
                            name:tab.name,
                            url:tab.url
                        };
                        break;
                    }
                }
                if(!isExit){
                    for(let menue of vm.menueList){
                        for(let ch of menue.child){
                            if(ch.name == val){
                                vm.nowPage = {
                                    id:ch.id,
                                    name:ch.name,
                                    url:ch.url,
                                };
                                vm.tabs.push(vm.nowPage);
                                break;
                            }
                        }
                    }
                }
                vm.$router.push(vm.nowPage.url);
            },*/
            /*handleTabRemove (name) {
                let vm = this;
                let num = vm.tabs.indexOf(name);
                for(let n = 0; n < vm.tabs.length; n++){
                    if(vm.tabs[n].name == name){
                        num = n;
                    }
                }
                if(vm.nowPage.name == name){
                    if(vm.tabs.length == 1){
                        return;
                    }else if(num != 0){
                        vm.nowPage = {
                            id:vm.tabs[num-1].id,
                            name:vm.tabs[num-1].name,
                            url:vm.tabs[num-1].url
                        };
                    }else{
                        vm.nowPage = {
                            id:vm.tabs[num+1].id,
                            name:vm.tabs[num+1].name,
                            url:vm.tabs[num+1].url
                        };
                    }
                }
                vm.tabs.splice(num,1);
                vm.$router.push(vm.nowPage.url);
            }*/
        }
    }
</script>