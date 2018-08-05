<style scoped>

</style>
<template>
    <div class="layout">
        <Content :style="{padding: '0px 16px 16px'}">
            <Breadcrumb :style="{margin: '16px 0'}">
                <BreadcrumbItem>SYSTEM</BreadcrumbItem>
                <BreadcrumbItem>USER</BreadcrumbItem>
            </Breadcrumb>
            <div>
                <Card style="height: 100%">
                    <Row style="margin-bottom: 10px;">
                        <Col span="6">
                            <Button type="primary" @click="addUser">New User</Button>
                        </Col>
                        <Col span="6" offset="12">
                            <Input v-model="pageQuery.filter">
                                <Button slot="append" icon="ios-search"></Button>
                            </Input>
                        </Col>
                    </Row>
                    <div style="height: 100%;">
                        <Table border :columns="columns1" :data="userList"></Table>
                        <Row style="margin-top: 10px;">
                            <Col span="12" offset="8">
                                <Page :total="40" size="small" show-elevator show-sizer />
                            </Col>
                        </Row>
                    </div>
                </Card>
            </div>
        </Content>
        <Modal
                width="750"
                v-model="addUserModal"
                title="New User"
                @on-ok="save('formItem')"
                @on-cancel="cancel">
            <Form ref="formItem" :model="formItem" :rules="ruleCustom" :label-width="80" >
                <FormItem label="Username" prop="username">
                    <Input v-model="formItem.username" placeholder="Enter username..."></Input>
                </FormItem>
                <FormItem label="Password" prop="password">
                    <Input v-model="formItem.password" placeholder="Enter password..."></Input>
                </FormItem>
                <FormItem label="Confirm" prop="passwdCheck">
                    <Input type="password" v-model="formItem.passwdCheck" placeholder="Confirm password..."></Input>
                </FormItem>
                <FormItem label="Age" prop="age">
                    <Input v-model="formItem.age" placeholder="Enter age..."></Input>
                </FormItem>
                <FormItem label="Sex" prop="sex">
                    <RadioGroup v-model="formItem.radio">
                        <Radio label="male">Male</Radio>
                        <Radio label="female">Female</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="Roles" prop="role">
                    <CheckboxGroup v-model="formItem.checkbox">
                        <Checkbox v-for="role in roleList" :key="role.id" :label="role.id">{{role.roleName}}</Checkbox>
                    </CheckboxGroup>
                </FormItem>
                <FormItem label="Active" prop="active">
                    <i-switch v-model="formItem.switch" size="large">
                        <span slot="open">On</span>
                        <span slot="close">Off</span>
                    </i-switch>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter your password'));
                } else {
                    if (this.formItem.passwdCheck !== '') {
                        // 对第二个密码框单独验证
                        this.$refs.formItem.validateField('passwdCheck');
                    }
                    callback();
                }
            };
            const validatePassCheck = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('Please enter your password again'));
                } else if (value !== this.formItem.password) {
                    callback(new Error('The two input passwords do not match!'));
                } else {
                    callback();
                }
            };
            return {
                ruleCustom: {
                    username:[
                        { required: true, trigger: 'blur' }
                    ],
                    password: [
                        { required: true, validator: validatePass, trigger: 'blur' }
                    ],
                    passwdCheck: [
                        { required: true, validator: validatePassCheck, trigger: 'blur' }
                    ],
                    age: [
                        { required: true, trigger: 'blur' }
                    ]
                },
                formItem: {
                    username: '',
                    password:'',
                    age: '',
                    userRole: 'male',
                    checkbox: [],
                    switch: true,
                },
                addUserModal: false,
                columns1: [{
                        title: 'username',
                        key: 'username'
                    },{
                        title: 'Age',
                        key: 'age'
                    },{
                    title: 'Action',
                    key: 'action',
                    width: 150,
                    align: 'center',
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.show(params.index)
                                    }
                                }
                            }, 'View'),
                            h('Button', {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                        this.remove(params.index)
                                    }
                                }
                            }, 'Delete')
                        ]);
                    }
                }],
                userList: [],
                roleList:[],
                pageQuery:{
                    page: 0,
                    size: 0,
                    filter: ""
                }
            }
        },
        mounted(){
            let vm = this;
            vm.getUser();
        },
        methods:{
            getUser(){
                let vm = this;
                vm.$http.get(vm.server_account+"/accounts",{params:vm.pageQuery}).then(function(data){
                    vm.userList = data.data.data.aaData;
                });
            },
            show (index) {
                this.$Modal.info({
                    title: 'User Info',
                    content: `Name：${this.userList[index].username}<br>Age：${this.userList[index].age}`
                })
            },
            addUser(){
                let vm = this;
                vm.addUserModal = true;
                vm.getUserRole();
            },
            save (formItem) {
                let vm = this;
                vm.$refs[formItem].validate((valid) => {
                    if (valid) {
                        vm.$http.post(vm.server_account+"/accounts/insertAccount",vm.formItem).then(function(data){
                            vm.userList = data.data.data.aaData;
                        });
                        vm.$Message.success('Success!');
                    } else {
                        vm.$Message.error('Fail!');
                    }
                });
            },
            cancel () {
                this.$Message.info('Clicked cancel');
            },
            getUserRole(){
                let vm = this;
                vm.$http.get(vm.server_account+"/accounts/getUserRoleList").then(function(data){
                    vm.roleList = data.data.data;
                });
            }
        }
    }
</script>