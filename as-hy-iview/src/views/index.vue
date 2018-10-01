<style scoped>
    .layout-con{
        height: 100%;
        width: 100%;
    }
    .layout .head-menu{
        position:fixed;
        width: 100%;
        z-index:999;
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
    .button-class{background:#eee;}
</style>
<template>
    <div class="layout">
        <Layout>
            <Menu mode="horizontal" theme="dark" class="head-menu" @on-select="headMenuClick">
                <div class="layout-logo"></div>
                <div class="layout-nav">
                    <MenuItem name="1">
                        <img width="50px" src="../images/The_death.png"/>
                    </MenuItem>
                </div>
                <div class="layout-nav-right">
                    <MenuItem v-for="headMenu in headMenuList" :key="headMenu.id" :name="headMenu.id">
                        <span v-if="headMenu.type=='0'">
                            <Icon type="ios-people"></Icon>
                            {{headMenu.name}}
                        </span>
                        <span v-else-if="headMenu.type=='1'">
                            <Submenu :name="headMenu.id">
                                <template slot="title">
                                    <Icon type="person"></Icon>
                                    {{headMenu.name}}
                                </template>
                                <MenuGroup v-for="con in headMenu.child" :key="con.id" :title="con.title">
                                    <MenuItem v-for="c in con.content" :class="userInfo.currentRole == c.name?'button-class':''" :key="c.id" :name="c.id">{{c.name}}</MenuItem>
                                </MenuGroup>
                            </Submenu>
                        </span>
                    </MenuItem>
                </div>
            </Menu>
            <Layout :style="{padding: '60px 0 0 0',minHeight: '100vh'}">
                <Sider collapsible :collapsed-width="100" v-model="isCollapsed">
                    <Menu theme="dark" width="auto" :class="menuItemClasses" @on-select="toOtherPage">
                        <Submenu v-for="menu in menuList" :key="menu.code" :name="menu.code">
                            <template slot="title">
                                <Icon :type="menu.icon"></Icon>
                                <span>{{menu.title}}</span>
                            </template>
                            <MenuItem v-for='ch in menu.children' :key='ch.code' :name="ch.code">
                                <Icon :type="ch.icon"></Icon>
                                <span>{{ch.title}}</span>
                            </MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Content style="height:100%">
                    <router-view></router-view>
                </Content>
            </Layout>
        </Layout>
        <Modal
                v-model="viewUserInfoModel"
                title="User Info">
            <Form :model="userInfo" label-position="right" :label-width="100">
                <FormItem label="User Name: ">
                    {{userInfo.username}}
                </FormItem>
                <FormItem label="Current Role: ">
                    {{userInfo.currentRole}}
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                isCollapsed: false,
                nowPage:{},
                userInfo:{
                    username:"",
                    currentRole:""
                },
                roleCode:[],
                menuList: [],
                headMenuList:[],
                currentUPage:{},
                viewUserInfoModel:false,
                buttonClass:"button-class"
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
            /*vm.$nextTick(function(){
                
            })*/
        },
        methods: {
            verificationToken(){
                let vm = this;
                let token = vm.$cookies.get("iView-token");
                let userRole = vm.$cookies.get("user-role");
                if(token){
                    vm.$http.post(vm.server_auth+"/oauth/check_token?token="+token).then(function(data){
                        vm.getUserInfo();
                    }).catch(function (error) {
                        vm.$Message.error('Error!');
                        vm.$router.push('/login')
                    });
                }else{
                    vm.$router.push('/login')
                }
            },
            getUserInfo(){
                let vm = this;
                vm.$http.get(vm.server_auth+"/users/current").then(function(data){
<<<<<<< Updated upstream
                    vm.$cookies.set("iView-token",data.data.details.tokenValue,"1d",null,window.domainUrl);
=======
                    vm.$cookies.set("iView-token",data.data.details.tokenValue,"1d",null,domainUrl);
>>>>>>> Stashed changes
                    vm.userInfo.username = data.data.principal.username;
                    vm.userInfo.currentRole = data.data.principal.currentRole.title;
                    window.currentUser = data.data.principal;
                    vm.getMenuList();
                    vm.getHeadMenuList();
                }).catch(function (error) {
                    vm.$Message.success('Error!');
                });
            },
            toOtherPage(val){
                let vm = this;
                let isGet = false;
                //vm.activeName = val;
                for(let menu of vm.menuList){
                    for(let ch of menu.children){
                        if(ch.code == val){
                            vm.nowPage = {
                                code:ch.code,
                                title:ch.title,
                                menuUrl:ch.menuUrl,
                                name:ch.name
                            };
                            isGet = true;
                            break;
                        }
                    }
                    if(isGet){
                        break;
                    }
                }
                vm.$router.push(vm.nowPage.menuUrl);
            },
            changeUserRole(str){
                let vm = this;
                vm.$http.get(vm.server_account+"/accounts/changeUserRole/"+str).then(function(data){

                }).catch(function (error) {
                    vm.$Message.Error('Error!');
                });
            },
            headMenuClick(data){
                let vm = this;
                switch (data) {
                    case "logout":
                        vm.logout();
                        break;
                    case "admin":
                        vm.changeUserRole(data);
                        break;
                    case "userInfo":
                        vm.viewUserInfoModel = true;
                        break;
                    default:
                        console.log("other");
                }
            },
            logout(){
                let vm = this;
                vm.$http.get(vm.server_auth+"/users/logout").then(function(data){
                    vm.$cookies.set("iView-token",'',-1);
                    vm.$cookies.set("refresh-iView-token",'',-1);
                    sessionStorage.clear();
                    vm.$router.push('/login');
                }).catch(function (error) {
                    vm.$Message.Error("Error!");
                });
            },
            getMenuList(){
                let vm = this;
                vm.$http.get(vm.server_account+"/roleAndMenu/getTreeMenuByCurrentRole").then(function(response){
                    vm.menuList = response.data.data == null?[]:response.data.data.children;
                    window.menuList = vm.menuList.length == 0 ?[]:response.data.data.children;
                    if(vm.currentUPage.parent){
                        vm.nowPage.name = vm.currentUPage.title;
                    }else if(vm.menuList.length > 0){
                        vm.toOtherPage(vm.menuList[0].children[0].code);
                    }
                });
            },
            getHeadMenuList(){
                let vm = this;
                vm.headMenuList = window.headMenuList;
                window.headMenuList[2].child[0].content = [];
                vm.headMenuList[2].name = vm.userInfo.username;
                let role = {};
                _.each(window.currentUser.roleCode,function(roleData){
                    role = {
                        id:roleData.roleCode,
                        name:roleData.title,
                    };
                    window.headMenuList[2].child[0].content.push(role);
                });
            },
        },
        beforeRouteEnter(to, from, next) {
            next(function (vm) {
                vm.currentUPage = to.meta;
                vm.verificationToken();
            })
        }
    }
</script>