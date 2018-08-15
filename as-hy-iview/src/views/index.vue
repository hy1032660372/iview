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
            <Menu mode="horizontal" theme="dark" active-name="1" :style="{position:'fixed', width: '100%',zIndex:'9999'}" @on-select="headMenuClick">
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
                                    <Icon type="stats-bars"></Icon>
                                    {{headMenu.name}}
                                </template>
                                <MenuGroup v-for="con in headMenu.child" :key="con.id" :title="con.title">
                                    <MenuItem v-for="c in con.content" :key="c.id" :name="c.id">{{c.name}}</MenuItem>
                                </MenuGroup>
                            </Submenu>
                        </span>
                    </MenuItem>
                </div>
            </Menu>
            <Layout :style="{padding: '60px 0 0 0',minHeight: '100vh'}">
                <Sider collapsible :collapsed-width="100" v-model="isCollapsed">
                    <Menu :active-name="nowPage.name" theme="dark" width="auto" :class="menuItemClasses" active-name="activeName" @on-select="toOtherPage">
                        <Submenu v-for="menu in menuList" :key="menu.id" :name="menu.id">
                            <template slot="title">
                                <Icon type="ios-navigate"></Icon>
                                <span>{{menu.name}}</span>
                            </template>
                            <MenuItem v-for='(ch,index) in menu.child' :key='ch.id' :name="ch.id"><span>{{ch.name}}</span>{{index}}</MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Content style="height:100%">
                    <router-view></router-view>
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
                nowPage:{},
                userInfo:{},
                menuList: [],
                headMenuList:[]
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
            vm.menuList = window.menuList;
            vm.headMenuList = window.headMenuList;
            vm.toOtherPage(vm.menuList[0].child[0].id);
        },
        methods: {
            verificationToken(){
                let vm = this;
                let token = vm.$cookies.get("iView-token");
                let userRole = vm.$cookies.get("user_role");
                if(token){
                    vm.$http.post(vm.server_auth+"/oauth/check_token?token="+token).then(function(data){
                        vm.getUserInfo();
                    });
                }else{
                    vm.$router.push('/login')
                }
            },
            getUserInfo(){
                let vm = this;
                vm.$http.get(vm.server_auth+"/users/current").then(function(data){
                    vm.userInfo = data.data.principal;
                    window.currentUser = data.data.principal;
                    vm.headMenuList[2].name = vm.userInfo.username;
                }).catch(function (error) {
                    vm.$Message.success('Error!');
                    vm.$router.push('/login')
                });
            },
            toOtherPage(val){
                let vm = this;
                let isGet = false;
                //vm.activeName = val;
                for(let menu of vm.menuList){
                    for(let ch of menu.child){
                        if(ch.id == val){
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
            changeUserRole(str){
                let vm = this;
                vm.$http.get(vm.server_account+"/accounts/changeUserRole/"+str).then(function(data){
                    console.log(data);
                }).catch(function (error) {
                    vm.$Message.success('Error!');
                    vm.$Message.success('Error!');
                });
            },
            headMenuClick(data){
                let vm = this;
                switch (data) {
                    case "h3144":
                        vm.logout();
                        break;
                    case "admin":
                        vm.changeUserRole(data);
                        break;
                    default:
                        console.log("other");
                }
                console.log(data);
            },
            logout(){
                let vm = this;
                vm.$http.get(vm.server_auth+"/users/logout").then(function(data){
                    vm.$cookies.set("iView-token",'');
                    vm.$cookies.set("refresh-iView-token",'');
                    sessionStorage.setItem("token_key", '');
                    vm.$router.push('/login')
                }).catch(function (error) {
                    vm.$Message.Error("Error!");
                });
            }
        },
        beforeRouteEnter(to, from, next) {
            next(function (vm) {
                vm.verificationToken();
            })
        }
    }
</script>