<style scoped>
    .layout{
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        border-radius: 4px;
        overflow: hidden;
    }
    .layout-header-bar{
        background: #fff;
        box-shadow: 0 1px 1px rgba(0,0,0,.1);
    }
</style>
<template>
    <div class="layout">
        <Content :style="{padding: '0px 16px 16px'}">
            <Breadcrumb :style="{margin: '16px 0'}">
                <BreadcrumbItem>SYSTEM</BreadcrumbItem>
                <BreadcrumbItem>ROLES</BreadcrumbItem>
            </Breadcrumb>
            <div>
                <Card>
                    <div style="height: 600px;">
                        <Col span="6">
                            <div style="background:#eee;padding: 20px">
                                <Card :bordered="false">
                                    <Tree :data="data1"></Tree>
                                </Card>
                            </div>
                        </Col>
                        <Col span="18">
                            <Row style="margin-bottom: 10px;">
                                <Col span="6">
                                    <Button type="primary" @click="addRole">New Role</Button>
                                </Col>
                                <Col span="6" offset="12">
                                    <Input v-model="pageQuery.filter">
                                    <Button slot="append" icon="ios-search"></Button>
                                    </Input>
                                </Col>
                            </Row>
                            <Table border :columns="RoleTableColumns" :data="userList"></Table>
                            <Row style="margin-top: 10px;">
                                <Col span="12" offset="8">
                                    <Page :total="40" size="small" show-elevator show-sizer />
                                </Col>
                            </Row>
                        </Col>
                    </div>
                </Card>
            </div>
        </Content>
        <Modal
                width="750"
                v-model="addRoleModal"
                title="New Role"
                @on-ok="ok"
                @on-cancel="cancel">
            <Form :model="formItem" :label-width="80">
                <FormItem label="RoleName" >
                    <Input v-model="formItem.roleName" placeholder="Enter roleName..."></Input>
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
                    roleName: '',
                },
                addRoleModal: false,
                RoleTableColumns: [{
                    title: 'roleName',
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
                },
                data1: [
                    {
                        title: 'parent 1',
                        expand: true,
                        children: [
                            {
                                title: 'parent 1-1',
                                expand: true,
                                children: [
                                    {
                                        title: 'leaf 1-1-1'
                                    },
                                    {
                                        title: 'leaf 1-1-2'
                                    }
                                ]
                            },
                            {
                                title: 'parent 1-2',
                                expand: true,
                                children: [
                                    {
                                        title: 'leaf 1-2-1'
                                    },
                                    {
                                        title: 'leaf 1-2-1'
                                    }
                                ]
                            }
                        ]
                    }
                ]
            }
        },
        mounted(){
            let vm = this;
            vm.getRoleList();
        },
        methods:{
            getRoleList(){
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
            addRole(){
                let vm = this;
                vm.addRoleModal = true;
            },
            ok () {
                this.$Message.info('Clicked ok');
            },
            cancel () {
                this.$Message.info('Clicked cancel');
            },
        }
    }
</script>