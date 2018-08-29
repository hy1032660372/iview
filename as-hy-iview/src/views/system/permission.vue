<style scoped>
    .layout{
        background: #fff;
    }
    .layout .tree-frame{
        background:#eee;
        padding: 10px 10px 10px 10px
    }
</style>
<template>
    <div class="layout">
        <Content>
            <Breadcrumb :style="{padding: '14px'}">
                <BreadcrumbItem>System</BreadcrumbItem>
                <BreadcrumbItem>permission</BreadcrumbItem>
            </Breadcrumb>
            <Row>
                <Col span="24">
                    <div class="tree-frame">
                        <Card :bordered="false">
                            <Row style="padding-bottom: 20px">
                                <Col span="8">
                                    <Button type="text">aaaa</Button>
                                </Col>
                                <Col style="float:right">
                                    <Button type="primary" @click="addPermissionModel=true">Add Permission</Button>
                                </Col>
                            </Row>
                            <Row>
                                <Col span="6">
                                    <Input v-model="query.filter">
                                        <Button slot="append" icon="ios-search"></Button>
                                    </Input>
                                </Col>
                                <Col span="24">
                                    <Table border :columns="columns" :data="permissionList"></Table>
                                </Col>
                            </Row>
                        </Card>
                    </div>
                </Col>
            </Row>
        </Content>
        <Modal
                v-model="addPermissionModel"
                title="Add Permission"
                @on-ok="savePermission"
                @on-cancel="cancel">
            <Form :model="menuForm" label-position="right" :label-width="100">
                <FormItem label="permissionName">
                    <Input v-model="menuForm.permissionName"></Input>
                </FormItem>
                <FormItem label="permissionCode">
                    <Input v-model="menuForm.permissionCode"></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                columns: [
                    {
                        title: 'permissionName',
                        key: 'permissionName',
                    },
                    {
                        title: 'permissionCode',
                        key: 'permissionCode'
                    },
                    {
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
                    }
                ],
                menuForm:{
                    permissionName:"",
                    permissionCode:""
                },
                permissionList:[],
                addPermissionModel:false,
                query:{
                    page:0,
                    size:10,
                    filter:''
                }
            };
        },
        mounted(){
            let vm = this;
            vm.getPermissionList();
        },
        methods:{
            getPermissionList(){
                let vm = this;
                vm.$http.get(vm.server_account+"/permissions/getPermissionList",{params:vm.query}).then(function(response){
                    vm.permissionList = response.data.aaData;
                });
            },
            savePermission(){
                let vm = this;
                vm.$http.post(vm.server_account+"/permissions/insertPermission",vm.menuForm).then(function(response){
                    console.log(response);
                });
            },
            cancel(){
                let vm = this;
            },
            show (index){
                this.$Modal.info({
                    title: 'User Info',
                    content: `Name：${this.permissionList[index].name}<br>Age：${this.permissionList[index].age}`
                })
            },
            remove (index) {
                this.data6.splice(index, 1);
            },
        },
        beforeRouteEnter(to, from, next) {
            next();
        }
    }
</script>