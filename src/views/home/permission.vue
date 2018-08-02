<style scoped>

</style>
<template>
    <div class="layout">
        <Content :style="{padding: '0px 16px 16px'}">
            <Breadcrumb :style="{margin: '16px 0'}">
                <BreadcrumbItem>Home</BreadcrumbItem>
                <BreadcrumbItem>Components</BreadcrumbItem>
            </Breadcrumb>
            <div style="height: 600px;">
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
                                        <Button type="primary" @click="configPermissionModel=true">Config Permission</Button>
                                        <Button type="primary" @click="addUserModel=true">Add User</Button>
                                        <Button type="primary" @click="addRoleModel=true">Add Role</Button>
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
            <Form :model="formLeft" label-position="left" :label-width="100">
                <FormItem label="username">
                    <Input v-model="formLeft.username"></Input>
                </FormItem>
                <FormItem label="age">
                    <Input v-model="formLeft.age"></Input>
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="addRoleModel"
                title="Add Role"
                @on-ok="saveRole"
                @on-cancel="cancel">
            <Form :model="formRight" label-position="right" :label-width="100">
                <FormItem label="Title">
                    <Input v-model="formRight.title"></Input>
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
                    {title: 'role',key: 'role'}
                ],
                addUserModel:false,
                addRoleModel:false,
                configPermissionModel:false,
                formLeft: {
                    username: '',
                    age: '',
                    role: ''
                },
                formRight: {
                    code: '',
                    title: '',
                    expand: true,
                    children:[]
                },
                currentRole:{},
                pageQuery:{
                    page: 0,
                    size: 0,
                    filter: ""
                }
            };
        },
        mounted(){
            let vm = this;
            vm.getRoleList();
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
                vm.$http.get(vm.server_account+"/accounts/getUserRoleList").then(function(response){
                    vm.roleData = [];
                    vm.roleData.push(response.data.data);
                    vm.currentRole = vm.roleData[0];
                    vm.getUserList();
                });
            },
            getUserList(){
                let vm = this;
                vm.pageQuery.filter = vm.currentRole.id
                vm.$http.get(vm.server_account+"/accounts",{params:vm.pageQuery}).then(function(data){
                    vm.userList = data.data.data.aaData;
                    console.log(vm.userList);
                });
            },
            saveUser(){
                let vm = this;
                vm.formLeft.role = vm.currentRole.code;
                vm.getUserList();
            },
            saveRole(){
                let vm = this;
                let children = vm.currentRole.children || [];
                vm.formRight.code = vm.currentRole.code+children.length;
                vm.formRight.expand = true;
                children.push(_.cloneDeep(vm.formRight));
                vm.$set(vm.currentRole, 'children', children);
            },
            savePermission(){
                let vm = this;
            },
            cancel(){
            }
        },
    }
</script>
