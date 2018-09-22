<style scoped>
    .layout{background: #fff;height: 650px;}
    .layout .content-frame{background:#eee;height: 650px}
    .layout .content-frame .tree-frame{padding: 10px 5px 10px 10px;}
    .layout .content-frame .tree-class{height: 590px}
    .layout .content-frame .table-frame{padding: 10px 10px 10px 5px;height: 590px}
    .layout .content-frame .table-frame .table-head{padding-bottom: 20px;}
    .layout .content-frame .table-frame .table-content{height: 547px;}
</style>
<template>
    <div class="layout">
        <Content>
            <Breadcrumb :style="{padding: '14px'}">
                <BreadcrumbItem>System</BreadcrumbItem>
                <BreadcrumbItem>Role</BreadcrumbItem>
            </Breadcrumb>
            <Row class="content-frame">
                <Col span="6" class="tree-frame">
                    <Card :bordered="false">
                        <Tree class="tree-class" :data="roleData" @on-select-change="onSelectChange"></Tree>
                    </Card>
                </Col>
                <Col span="18" class="table-frame">
                    <Card :bordered="false">
                        <Row class="table-head">
                            <Col span="8">
                                <Button type="text" v-if="currentRole.code!='199277'" @click="configRoleModel=true">{{currentRole.title}}</Button>
                            </Col>
                            <Col style="float:right">
                                <Button type="primary" @click="configMenu">Config Menu</Button>
                                <Button type="primary" @click="configPermission">Config Permission</Button>
                                <Button type="primary" v-if="access" @click="addUserModel=true">Add User</Button>
                                <Button type="primary" v-if="access" @click="addRoleModel=true">Add Role</Button>
                                <Button type="primary" v-if="access && userList.length == 0" @click="removeRole">Remove Role</Button>
                            </Col>
                        </Row>
                        <div class="table-content">
                            <Table  :columns="columnsList" :data="userList"></Table>
                        </div>
                    </Card>
                </Col>
            </Row>
        </Content>
        <Modal
                v-model="addUserModel"
                title="Add User"
                @on-ok="saveUser"
                @on-cancel="cancel">
            <Form :model="userForm" label-position="left" :label-width="100">
                <FormItem label="username">
                    <Input :maxlength="20" v-model="userForm.username"></Input>
                </FormItem>
                <FormItem label="age">
                    <Input number :maxlength="3" v-model="userForm.age"></Input>
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="addRoleModel"
                title="Add Role"
                @on-ok="addRole"
                @on-cancel="cancel">
            <Form :model="roleForm" label-position="right" :label-width="100">
                <FormItem label="Title">
                    <Input v-model="roleForm.title"></Input>
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="configRoleModel"
                title="Config Role"
                @on-ok="saveRole"
                @on-cancel="cancel">
            <Form :model="currentRole" label-position="left" :label-width="100">
                <FormItem label="title">
                    <Input :maxlength="20" v-model="currentRole.title"></Input>
                </FormItem>
                <FormItem label="code">
                    <Input :maxlength="20" v-model="currentRole.code"></Input>
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="configPermissionModel"
                title="Config Permission"
                @on-ok="savePermission"
                @on-cancel="cancel">
            <Collapse v-model="openMenu">
                <Panel v-for="menu in permissionsData" :key="menu.menuCode" name="menu.menuCode">
                    {{menu.menuName}}
                    <p slot="content">
                        <CheckboxGroup v-model="checkedPermission">
                            <Checkbox v-for="permission in menu.permissionList" :key="permission.permissionCode" label="permission.permissionCode">
                                <span>{{permission.permissionName}}</span>
                            </Checkbox>
                        </CheckboxGroup>
                    </p>
                </Panel>
            </Collapse>
        </Modal>
        <Modal
                v-model="configMenuModel"
                title="Config Menu"
                @on-ok="saveRoleMenu"
                @on-cancel="cancel">
            <Tree :data="menuTreeData"
                   @on-check-change="onCheckChange"
                   show-checkbox></Tree>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                roleData: [],
                userList:[],
                configMenuModel:false,
                configRoleModel:false,
                permissionsData: [],
                openMenu:"",
                checkedPermission:[],
                menuAuthDataList:[],
                selection:[],
                menuTreeData:[],
                columnsList:[
                    {title: 'username',key: 'username'},
                    {title: 'age',key: 'age'},
                    {title: 'Action',key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {type: 'primary',size: 'small'},
                                    style: {marginRight: '5px'},
                                    on: {click: () => {
                                            this.show(params.index)
                                        }
                                    }
                                }, 'View'),
                                h('Button', {
                                    props: {type: 'error', size: 'small'},
                                    on: {click: () => {
                                            this.deleteUser(params.index);
                                        }
                                    }
                                }, 'Delete')
                            ]);
                        }
                    }
                ],
                addUserModel:false,
                addRoleModel:false,
                configMenuModel:false,
                configPermissionModel:false,
                userForm: {
                    username: '',
                    age: '',
                },
                roleForm: {
                    code: '',
                    title: '',
                    parentCode:'',
                    expand: true
                },
                currentRole:{},
                pageQuery:{
                    page: 0,
                    size: 0,
                    filter: ""
                },
            };
        },
        mounted(){
            let vm = this;
        },
        computed:{
            access:function(){
                let vm = this;
                if(window.currentUser.roleCode != null){
                    return vm.currentRole.code != window.currentUser.currentRole.roleCode;
                }
                return false;
            }
        },
        methods:{
            configPermission(){
                let vm = this;
                vm.configPermissionModel = true
                vm.$http.get(vm.server_account+"/permissions/getUserAuthPermissions").then(function(response){
                    vm.permissionsData = response.data.data;
                    vm.openMenu = vm.permissionsData[0].menuCode
                });
            },
            onCheckChange(data){
                let vm = this;
                vm.selection = data;
            },
            configMenu(){
                let vm = this;
                vm.configMenuModel = true;
                vm.menuTreeData = [];
                let param = {
                    roleCode:vm.currentRole.code
                };
                vm.$http.get(vm.server_account+"/roleAndMenu/getMenuByCurrentRole",{params:param}).then(function(response){
                    vm.menuTreeData = response.data.data.children;
                });
            },
            onSelectChange(data){
                let vm = this;
                if(data.length != 0){
                    vm.currentRole = data[0];
                    vm.getUserList();
                }
            },
            getRoleList(){
                let vm = this;
                vm.$http.get(vm.server_account+"/role/getUserRoleList").then(function(response){
                    vm.roleData = [];
                    vm.roleData.push(response.data.data);
                    vm.currentRole = vm.roleData[0];
                    vm.getUserList();
                });
            },
            getUserList(){
                let vm = this;
                vm.pageQuery.filter = vm.currentRole.code;
                vm.$http.get(vm.server_account+"/accounts",{params:vm.pageQuery}).then(function(data){
                    vm.userList = data.data.data.aaData;
                });
            },
            saveUser(){
                let vm = this;
                if(vm.currentRole.code != window.currentUser.currentRole.roleCode){
                    vm.$http.post(vm.server_account+"/AccountAndRole/insertAccount/"+vm.currentRole.code,vm.userForm).then(function(data){
                        vm.getUserList();
                    });
                }else{
                    vm.$Message.warning('Can not do this!');
                }
            },
            removeRole(){
                let vm = this;
                vm.$Modal.confirm({
                    title: 'Confirm',
                    content: '<p>Do you want to delete this role?</p>',
                    onOk: () => {
                        vm.$http.get(vm.server_account+"/role/removeRole/"+vm.currentRole.code).then(function(data){
                            vm.getRoleList();
                        });
                    },
                    onCancel: () => {
                        vm.$Message.info('You canceled');
                    }
                });
            },
            saveRole(){
                let vm = this;
            },
            addRole(){
                let vm = this;
                let children = vm.currentRole.children || [];
                vm.roleForm.code = vm.currentRole.code+children.length;
                vm.roleForm.expand = true;
                vm.roleForm.parentCode = vm.currentRole.code;
                children.push(_.cloneDeep(vm.roleForm));
                vm.$set(vm.currentRole, 'children', children);
                vm.$http.post(vm.server_account+"/accounts/insertUserRole",vm.roleForm).then(function(data){

                });
                vm.roleForm = {
                    code: '',
                    title: '',
                    parentCode:'',
                    expand: true
                }
            },
            savePermission(){
                let vm = this;
            },
            saveRoleMenu(){
                let vm = this;
                let roleAndMenu;
                let roleAndMenuList = [];
                let parentCode = [];
                _.each(vm.selection,function (menu) {
                    if(menu.parentCode != "root-menu"){
                        if(parentCode.indexOf(menu.parentCode) == -1){
                            parentCode.push(menu.parentCode);
                            roleAndMenu = {
                                roleCode:vm.currentRole.code,
                                menuCode:menu.parentCode
                            };
                            roleAndMenuList.push(roleAndMenu);
                        }
                        roleAndMenu = {
                            roleCode:vm.currentRole.code,
                            menuCode:menu.code
                        };
                        roleAndMenuList.push(roleAndMenu);
                    }
                });
                if(roleAndMenuList.length>0){
                    roleAndMenu = {
                        roleCode:vm.currentRole.code,
                        menuCode:"root-menu"
                    };
                    roleAndMenuList.push(roleAndMenu);
                    vm.$http.post(vm.server_account+"/roleAndMenu/insertRoleAndMenu",roleAndMenuList).then(function(response){
                        console.log(response);
                    });
                }else{
                    vm.$Message.warning("Have no change");
                }
            },
            cancel(){
            },
            show (index) {
                this.$Modal.info({
                    title: 'User Info',
                    content: `Name：${this.userList[index].username}<br>Age：${this.userList[index].age}`
                })
            },
            deleteUser(index){
                let vm = this;
                let user = vm.userList[index];
                if(user.id != window.currentUser.userId){
                    vm.$Modal.confirm({
                        title: 'Confirm',
                        content: '<p>Do you want to delete this user?</p>',
                        onOk: () => {
                            vm.$http.delete(vm.server_account+"/accounts/"+user.id).then(function(data){
                                vm.userList.splice(index,1);
                                vm.$Message.info('Clicked ok');
                            });
                        },
                        onCancel: () => {
                            vm.$Message.info('You canceled');
                        }
                    });
                }else{
                    vm.$Message.warning('Don not allow do this');
                    vm.$Message.warning('Don not allow do this');
                }
            }
        },
        beforeRouteEnter(to, from, next) {
            next(function (vm) {
                vm.getRoleList();
            })
        }
    }
</script>
