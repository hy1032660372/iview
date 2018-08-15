<style scoped>

</style>
<template>
    <div class="layout">
        <Content :style="{padding: '0px 16px 16px'}">
            <Breadcrumb :style="{margin: '16px 0'}">
                <BreadcrumbItem>Home</BreadcrumbItem>
                <BreadcrumbItem>Menu</BreadcrumbItem>
            </Breadcrumb>
            <div>
                <Row>
                    <Col span="6">
                        <div style="background:#eee;padding: 20px 10px 20px 20px">
                            <Card :bordered="false">
                                <Tree :data="menuData" @on-select-change="onSelectChange"></Tree>
                            </Card>
                        </div>
                    </Col>
                    <Col span="18">
                        <div style="background:#eee;padding: 20px 20px 20px 10px">
                            <Card :bordered="false">
                                <Row style="padding-bottom: 20px">
                                    <Col span="8">
                                        {{currentMenu.title}}
                                    </Col>
                                    <Col style="float:right">
                                        <Button type="primary" @click="configPermission">Config Permission</Button>
                                        <Button type="primary" v-if="sccess" @click="addUserModel=true">Add User</Button>
                                        <Button type="primary" v-if="sccess" @click="addMenuModel=true">Add Menu</Button>
                                        <Button type="primary" v-if="sccess && userList.length == 0" @click="removeMenu">Remove Role</Button>
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
                v-model="addMenuModel"
                title="Add Menu"
                @on-ok="saveMenu"
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
                menuData: [],
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
                addMenuModel:false,
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
                currentMenu:{},
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
            sccess:function(){
                let vm = this;
                if(currentUser){
                    return true;
                }
                return null;
            }
        },
        methods:{
            onSelectChange(data){
                let vm = this;
                if(data.length != 0){
                    vm.currentMenu = data[0];
                    vm.getUserList();
                }
            },
            getMenuList(){
                let vm = this;
                vm.$http.get(vm.server_account+"/menu/getMenuList").then(function(response){
                    vm.menuData = [];
                    vm.menuData.push(response.data.data);
                    vm.currentMenu = vm.menuData[0];
                    vm.getUserList();
                });
            },
            getUserList(){
                let vm = this;
                console.log("aaaaaaaaaa");
            },
            saveUser(){
                let vm = this;
                if(vm.currentMenu.code != currentMenu.currentMenu.roleCode){
                    console.log("save menu permission");
                }else{
                    vm.$Message.warning('Can not do this!');
                }
            },
            removeMenu(){
                let vm = this;
                vm.$Modal.confirm({
                    title: 'Confirm',
                    content: '<p>Do you want to delete this role?</p>',
                    onOk: () => {
                        vm.$http.get(vm.server_account+"/menu/removeMenu/"+vm.currentMenu.code).then(function(data){
                            vm.getRoleList();
                        });
                    },
                    onCancel: () => {
                        vm.$Message.info('You canceled');
                    }
                });
            },
            saveMenu(){
                let vm = this;
                let children = vm.currentMenu.children || [];
                vm.roleForm.code = vm.currentMenu.code+children.length;
                vm.roleForm.expand = true;
                vm.roleForm.parentCode = vm.currentMenu.code;
                children.push(_.cloneDeep(vm.roleForm));
                vm.$set(vm.currentMenu, 'children', children);
                vm.$http.post(vm.server_account+"/menu/insertMenu",vm.roleForm).then(function(data){
                    console.log(data);
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
                vm.configPermission = true;
                vm.$http.get(vm.server_account+"/accounts/getCustomPermissions").then(function(data){
                    console.log(data);
                });
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
                if(user.id != currentUser.userId){
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
                vm.getMenuList();
            })
        }
    }
</script>
