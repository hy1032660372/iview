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
                    <MenuItem name="1">
                        <Icon type="ios-navigate"></Icon>
                        Item 1
                    </MenuItem>
                    <MenuItem name="2">
                        <Icon type="ios-keypad"></Icon>
                        Item 2
                    </MenuItem>
                    <MenuItem name="1">
                        <Icon type="ios-paper"></Icon>
                        内容管理
                    </MenuItem>
                    <MenuItem name="2">
                        <Icon type="ios-people"></Icon>
                        用户管理
                    </MenuItem>
                    <Submenu name="3">
                        <template slot="title">
                            <Icon type="stats-bars"></Icon>
                            统计分析
                        </template>
                        <MenuGroup title="使用">
                            <MenuItem name="3-1">新增和启动</MenuItem>
                            <MenuItem name="3-2">活跃分析</MenuItem>
                            <MenuItem name="3-3">时段分析</MenuItem>
                        </MenuGroup>
                        <MenuGroup title="留存">
                            <MenuItem name="3-4">用户留存</MenuItem>
                            <MenuItem name="3-5">流失用户</MenuItem>
                        </MenuGroup>
                    </Submenu>
                </div>
            </Menu>
            <Layout :style="{padding: '60px 0 0 0',minHeight: '100vh'}">
                <Sider collapsible :collapsed-width="100" v-model="isCollapsed">
                    <Menu active-name="first" theme="dark" width="auto" :open-names="['1']" :class="menuitemClasses" @on-select="toOtherPage">
                        <MenuItem name="1-4">
                            <Icon type="ios-navigate"></Icon>
                            <span>Option 4</span>
                        </MenuItem>
                        <Submenu name="1">
                            <template slot="title">
                                <Icon type="ios-navigate"></Icon>
                                <span>Item 1</span>
                            </template>
                            <MenuItem name="first"><span>Option</span>first</MenuItem>
                            <MenuItem name="second"><span>Option</span>second</MenuItem>
                            <MenuItem name="1-3"><span>Option</span>3</MenuItem>
                        </Submenu>
                        <Submenu name="2">
                            <template slot="title">
                                <Icon type="ios-keypad"></Icon>
                                <span>Item 2</span>
                            </template>
                            <MenuItem name="2-1"><span>Option</span>1</MenuItem>
                            <MenuItem name="2-2"><span>Option</span>2</MenuItem>
                        </Submenu>
                        <Submenu name="3">
                            <template slot="title">
                                <Icon type="ios-analytics"></Icon>
                                <span>Item 3</span>
                            </template>
                            <MenuItem name="3-1"><span>Option</span>1</MenuItem>
                            <MenuItem name="3-2"><span>Option</span>2</MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Content :style="{height:'100%'}">
                    <Row>
                        <Col span="24" class="demo-tabs-style1" style="background: #e3e8ee;padding:16px;">
                            <Tabs type="card" closable @on-tab-remove="handleTabRemove" v-model="activeTabName">
                                <TabPane v-for="tab in tabs" :key="tab" :label="tab" :name="tab">
                                    <router-view></router-view>
                                </TabPane>
                            </Tabs>
                        </Col>
                    </Row>
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
                tabs: [],
                activeTabName: ""
            };
        },
        computed: {
            menuitemClasses: function () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        },
        mounted(){
            let vm = this;
            if(vm.tabs.length == 0){
                vm.activeTabName = 'first';
                vm.tabs.push(vm.activeTabName);
            }
            vm.$router.push("/home/first");
        },
        methods: {
            toOtherPage (val) {
                let vm = this;
                //vm.$router.push("/"+val);
                if(this.tabs.indexOf(val) == -1){
                    this.tabs.push(val);
                }
                this.activeTabName = val;
            },
            handleTabRemove (name) {
                let vm = this;
                let num = vm.tabs.indexOf(name);
                if(vm.tabs.length == 1){
                    return;
                }else if(num != 0){
                    vm.activeTabName = vm.tabs[num-1];
                }else{
                    vm.activeTabName = vm.tabs[num+1];
                }
                vm.tabs.splice(num,1);
            }
        }
    }
</script>