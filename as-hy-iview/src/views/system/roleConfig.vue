<style scoped>

</style>
<template>
    <div class="layout">
        <Content :style="{padding: '0px 16px 16px'}">
            <Breadcrumb :style="{margin: '16px 0'}">
                <BreadcrumbItem>Home</BreadcrumbItem>
                <BreadcrumbItem>Role</BreadcrumbItem>
            </Breadcrumb>
            <div>
                <Row>
                    <Col span="6">
                        <div style="background:#eee;padding: 20px 10px 20px 20px">
                            <Card :bordered="false">
                                <Tree :data="roleData" @on-select-change="onSelectChange"></Tree>
                            </Card>
                        </div>
                    </Col>
                    <Col span="18">
                        <div style="background:#eee;padding: 20px 20px 20px 10px">
                            <Card :bordered="false">
                                <Row style="padding-bottom: 20px">
                                    <Col span="8">
                                        {{currentRole.title}}
                                    </Col>
                                    <Col style="float:right">
                                        <Button type="primary" @click="configPermission">Config Permission</Button>
                                        <Button type="primary" v-if="access" @click="addUserModel=true">Add User</Button>
                                        <Button type="primary" v-if="access" @click="addRoleModel=true">Add Role</Button>
                                        <Button type="primary" v-if="access && userList.length == 0" @click="removeRole">Remove Role</Button>
                                    </Col>
                                </Row>
                                <Table :columns="columnsList" :data="userList"></Table>
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
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
                @on-ok="saveRole"
                @on-cancel="cancel">
            <Form :model="roleForm" label-position="right" :label-width="100">
                <FormItem label="Title">
                    <Input v-model="roleForm.title"></Input>
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="configPermissionModel"
                title="Config Permission"
                @on-ok="savePermission"
                @on-cancel="cancel">
            <Tree :data="permissionsData" show-checkbox></Tree>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                roleData: [],
                userList:[],
                permissionsData: [{
                    title: 'parent 1',
                    expand: true,
                    children: [{
                        title: 'parent 1-1',
                        expand: true,
                        children: [
                            {title: 'leaf 1-1-1'},
                            {title: 'leaf 1-1-2'}]
                    },{
                        title: 'parent 1-2',
                        expand: true,
                        children: [
                            {title: 'leaf 1-2-1'},
                            {title: 'leaf 1-2-1'}
                        ]
                    }
                    ]}
                ],
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
                    vm.$http.post(vm.server_account+"/accounts/insertAccount/"+vm.currentRole.code,vm.userForm).then(function(data){
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
            configPermission(){
                let vm = this;
                vm.configPermissionModel = true;
                // vm.$http.get(vm.server_account+"/permissions/getUserAuthPermissions").then(function(data){
                // });
            },
            savePermission(){
                let vm = this;
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
