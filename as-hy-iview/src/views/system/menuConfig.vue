<style scoped>
    .layout{background: #fff;}
    .layout .tree-frame{background:#eee;padding: 10px 10px 10px 10px }
    .layout .tree-frame .tree-content{border-top: 2px solid #eee; }
    .layout .tree-frame .tree-content .page-frame{margin-top: 5px}
</style>
<template>
    <div class="layout">
        <Content>
            <Breadcrumb :style="{padding: '14px'}">
                <BreadcrumbItem>System</BreadcrumbItem>
                <BreadcrumbItem>Menu</BreadcrumbItem>
            </Breadcrumb>
            <Row>
                <Col span="24">
                    <div class="tree-frame">
                        <Card :bordered="false">
                            <Row style="padding-bottom: 20px">
                                <Col span="8">
                                    <Button type="text" v-if="currentMenu.code!='root-menu'" @click="configMenuModel=true">{{currentMenu.title}}</Button>
                                </Col>
                                <Col style="float:right">
                                    <Button type="primary" v-if="access" @click="addMenuModel=true">Add Menu</Button>
                                    <Button type="primary" v-if="access && permissionList.length == 0" @click="removeMenu">Remove Menu</Button>
                                </Col>
                            </Row>
                            <Row class="tree-content">
                                <Col span="8">
                                    <Tree :data="menuData" @on-select-change="onSelectChange"></Tree>
                                </Col>
                                <Col span="16">
                                    <Row>
                                        <Col span="12">
                                            <Input v-model="queryObject.queryData">
                                                <Button slot="append" icon="ios-search" @click="getPermissionList"></Button>
                                            </Input>
                                        </Col>
                                        <Col span="12">
                                            <Button type="primary" style="float:right" @click="addPermissionModel=true">Add Permission</Button>
                                        </Col>
                                    </Row>
                                    <Row>
                                        <Table border :columns="columns" :data="permissionList"></Table>
                                        <Col class="page-frame">
                                            <Page :total="totalRecord" size="small" show-total show-elevator show-sizer></Page>
                                        </Col>
                                    </Row>
                                </Col>
                            </Row>
                        </Card>
                    </div>
                </Col>
            </Row>
        </Content>
        <Modal
                v-model="addMenuModel"
                title="Add Menu"
                @on-ok="saveMenu"
                @on-cancel="cancel">
            <Form :model="menuForm" label-position="right" :label-width="100">
                <FormItem label="Title">
                    <Input v-model="menuForm.title" />
                </FormItem>
                <FormItem label="code">
                    <Input v-model="menuForm.code" />
                </FormItem>
                <FormItem label="menuUrl">
                    <Input v-model="menuForm.menuUrl" />
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="configMenuModel"
                title="config Role"
                @on-ok="saveMenu"
                @on-cancel="cancel">
            <Form :model="currentMenu" label-position="right" :label-width="100">
                <FormItem label="Title">
                    <Input v-model="currentMenu.title"/>
                </FormItem>
                <FormItem label="code">
                    <Input v-model="currentMenu.code"/>
                </FormItem>
                <FormItem label="menuUrl">
                    <Input v-model="currentMenu.menuUrl"/>
                </FormItem>
            </Form>
        </Modal>
        <Modal
                v-model="addPermissionModel"
                title="Add Permission"
                @on-ok="savePermission"
                @on-cancel="cancel">
            <Form :model="permissionForm" label-position="right" :label-width="100">
                <FormItem label="permissionName">
                    <Input v-model="permissionForm.permissionName"></Input>
                </FormItem>
                <FormItem label="permissionCode">
                    <Input v-model="permissionForm.permissionCode"></Input>
                </FormItem>
            </Form>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                menuData: [],
                permissionList:[],
                configMenuModel:false,
                addPermissionModel:false,
                columns: [{title: 'permissionName',key: 'permissionName',},
                    {title: 'permissionCode', key: 'permissionCode' },
                    {title: 'Action',key: 'action',　width: 150,　align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {type: 'error',size: 'small'},
                                    on: {click: () => {this.deletePermission(params.index)}}
                                }, 'Delete')
                            ]);
                        }
                    }
                ],
                columnsList:[
                    {type: 'selection',  width: 80,  align: 'center'},
                    {title: 'roleName',key: 'title'}
                ],
                addMenuModel:false,
                menuForm: {
                    code: '',
                    title: '',
                    menuUrl:'',
                    parentCode:'',
                    expand: true
                },
                permissionForm:{
                    permissionName:"",
                    permissionCode:""
                },
                currentMenu:{},
                permission:{},
                pageQuery:{
                    page: 0,
                    size: 10,
                    filter:""
                },
                queryObject:{
                    queryData:"",
                    menuCode:""
                },
                totalRecord:0,
            };
        },
        mounted(){
            let vm = this;
            vm.getPermissionList();
        },
        computed:{
            access:function(){
                let vm = this;
                if(currentUser){
                    return true;
                }
                return null;
            }
        },
        methods:{
            getPermissionList(){
                let vm = this;
                vm.queryObject.menuCode = vm.currentMenu.code;
                vm.pageQuery.filter = decodeURIComponent(vm.jquery.param(vm.queryObject),true);
                vm.$http.get(vm.server_account+"/permissions/getPermissionList",{params:vm.pageQuery}).then(function(response){
                    vm.permissionList = response.data.aaData;
                    vm.totalRecord = response.data.iTotalRecords;
                    vm.pageQuery.filter = ""
                });
            },
            savePermission(){
                let vm = this;
                vm.$http.post(vm.server_account+"/permissions/insertPermission",vm.menuForm).then(function(response){
                    console.log(response);
                });
            },
            onSelectChange(data){
                let vm = this;
                if(data.length != 0){
                    vm.currentMenu = data[0];
                }
                vm.getPermissionList();
            },
            getMenuByCurrentRole(){
                let vm = this;
                vm.$http.get(vm.server_account+"/roleAndMenu/getTreeMenuByCurrentRole").then(function(response){
                    vm.menuData = [];
                    vm.menuData.push(response.data.data);
                    vm.currentMenu = vm.menuData[0];
                });
            },
            removeMenu(){
                let vm = this;
                vm.$Modal.confirm({
                    title: 'Confirm',
                    content: '<p>Do you want to delete this role?</p>',
                    onOk: () => {
                        vm.$http.get(vm.server_account+"/menu/removeMenu/"+vm.currentMenu.code).then(function(data){

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
                vm.menuForm.expand = true;
                vm.menuForm.parentCode = vm.currentMenu.code;
                children.push(_.cloneDeep(vm.menuForm));
                vm.$set(vm.currentMenu, 'children', children);
                vm.$http.post(vm.server_account+"/menu/insertMenu",vm.menuForm).then(function(data){

                });
                vm.menuForm = {
                    code: '',
                    title: '',
                    parentCode:'',
                    expand: true
                }
            },
            cancel(){
            },
            deletePermission(index){
                let vm = this;
                let permission = vm.permissionList[index];
                vm.$Modal.confirm({
                    title: 'Confirm',
                    content: '<p>Do you want to delete this item?</p>',
                    onOk: () => {
                        vm.$http.delete(vm.server_account+"/permissions/"+permission.id).then(function(data){
                            vm.permissionList.splice(index,1);
                        });
                    },
                    onCancel: () => {
                        vm.$Message.info('You canceled');
                    }
                });
            }
        },
        beforeRouteEnter(to, from, next) {
            next(function (vm) {
                vm.getMenuByCurrentRole();
            })
        }
    }
</script>
