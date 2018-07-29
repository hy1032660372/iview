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
                <Card>
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
                    <div style="height: 600px;">
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
                @on-ok="ok"
                @on-cancel="cancel">
            <Form :model="formItem" :label-width="80">
                <FormItem label="Username">
                    <Input v-model="formItem.username" placeholder="Enter username..."></Input>
                </FormItem>
                <FormItem label="Age">
                    <Input v-model="formItem.age" placeholder="Enter age..."></Input>
                </FormItem>
                <FormItem label="Radio">
                    <RadioGroup v-model="formItem.radio">
                        <Radio label="male">Male</Radio>
                        <Radio label="female">Female</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="Checkbox">
                    <CheckboxGroup v-model="formItem.checkbox">
                        <Checkbox v-for="role in roleList" :key="role.id" :label="role.id">{{role.roleName}}</Checkbox>
                    </CheckboxGroup>
                </FormItem>
                <FormItem label="Switch">
                    <i-switch v-model="formItem.switch" size="large">
                        <span slot="open">On</span>
                        <span slot="close">Off</span>
                    </i-switch>
                </FormItem>
                <FormItem>
                    <Button type="primary">Submit</Button>
                    <Button style="margin-left: 8px">Cancel</Button>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                formItem: {
                    username: '',
                    age: '',
                    userRole: 'male',
                    checkbox: [],
                    switch: true,
                    date: '',
                    time: '',
                    slider: [20, 50],
                    textarea: ''
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
            vm.GetUser();
        },
        methods:{
            GetUser(){
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
            ok () {
                this.$Message.info('Clicked ok');
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