<template>
    <div class="layout">
        <Row>
            <Menu mode="horizontal" theme="primary" :style="{zIndex:'999'}" active-name="1">
                <Row class="commn-head">
                    <Col span="11">&nbsp;</Col>
                    <Col span="5">QH2.0</Col>
                    <Col span="8">
                        <div class="layout-nav-right">
                            <MenuItem name="2">
                                <Icon type="android-notifications"></Icon>
                            </MenuItem>
                            <Submenu name="3">
                                <template slot="title">
                                    <Avatar icon="person" size="large" />
                                    MS Sales Specialist 1
                                </template>
                                <MenuItem name="3-1">My Profile</MenuItem>
                                <MenuItem name="3-2">Preference</MenuItem>
                                <MenuItem name="3-3">Logout</MenuItem>
                            </Submenu>
                        </div>
                    </Col>
                </Row>
            </Menu>
            <Menu mode="horizontal" theme="light" active-name="1">
                <div class="layout-nav-left">
                    <MenuItem name="1">
                        QUOTATION MANAGEMENT
                    </MenuItem>
                    <MenuItem name="2">
                        SETUP CONVERER
                    </MenuItem>
                    <MenuItem name="3">
                        HELP
                    </MenuItem>
                </div>
            </Menu>
        </Row>
        <div class="main-content">
            <router-view></router-view>
        </div>
    </div>
</template>
<script>
    export default {
        data () {
            return {

            }
        },
        mounted(){
            let vm = this;
            vm.$router.push('/index2/sssourcing')
        },
        methods: {
            
        }
    }
</script>
<style>
    .layout{
        background: #f3f3f3;
    }
    .layout-nav-right{
        float: right;
    }

    .layout-nav-left{
        float: left;
    }

    .commn-head{
        background: #0064cd;
        color: #f3f3f3;
        z-index: 9999
    }
</style>