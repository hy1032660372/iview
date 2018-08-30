<style scoped>
    .layout{
        background: #fff;
    }
    .layout .tree-frame{
        background:#eee;
        padding: 10px 10px 10px 10px
    }
    .layout .tree-frame .tree-content{
        border-top: 2px solid #eee;
    }
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
                                    <Button type="primary" v-if="access && roleList.length == 0" @click="removeMenu">Remove Menu</Button>
                                </Col>
                            </Row>
                            <Row class="tree-content">
                                <Tree :data="menuData" @on-select-change="onSelectChange"></Tree>
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
                    <Input v-model="menuForm.title"></Input>
                </FormItem>
                <FormItem label="code">
                    <Input v-model="menuForm.code"></Input>
                </FormItem>
                <FormItem label="menuUrl">
                    <Input v-model="menuForm.menuUrl"></Input>
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
                    <Input v-model="currentMenu.title"></Input>
                </FormItem>
                <FormItem label="code">
                    <Input v-model="currentMenu.code"></Input>
                </FormItem>
                <FormItem label="menuUrl">
                    <Input v-model="currentMenu.menuUrl"></Input>
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
                roleList:[],
                selection:[],
                configMenuModel:false,
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
                    {type: 'selection',  width: 80,  align: 'center'},
                    {title: 'roleName',key: 'title'}
                ],
                addMenuModel:false,
                userForm: {
                    username: '',
                    age: '',
                },
                menuForm: {
                    code: '',
                    title: '',
                    menuUrl:'',
                    parentCode:'',
                    expand: true
                },
                currentMenu:{},
                permission:{},
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
                if(currentUser){
                    return true;
                }
                return null;
            }
        },
        methods:{
            saveMenu(){
                let vm = this;

            },
            savePermission(){
                let vm = this;
                vm.$http.post(vm.server_account+"/permissions/insertPermission",vm.permission).then(function(response){
                    vm.roleList = response.data.data;
                    vm.getRoleCurrent();
                });
            },
            onSelectChange(data){
                let vm = this;
                if(data.length != 0){
                    vm.currentMenu = data[0];

                }
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
            configPermission(){
                let vm = this;
                vm.configPermission = true;
                vm.$http.get(vm.server_account+"/accounts/getCustomPermissions").then(function(data){

                });
            },
            cancel(){
            },
            show (index) {
                this.$Modal.info({
                    title: 'User Info',
                    content: `Name：${this.roleList[index].username}<br>Age：${this.roleList[index].age}`
                })
            },
            deleteUser(index){
                let vm = this;
                let user = vm.roleList[index];
                if(user.id != currentUser.userId){
                    vm.$Modal.confirm({
                        title: 'Confirm',
                        content: '<p>Do you want to delete this user?</p>',
                        onOk: () => {
                            vm.$http.delete(vm.server_account+"/accounts/"+user.id).then(function(data){
                                vm.roleList.splice(index,1);
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
                vm.getMenuByCurrentRole();
            })
        }
    }
</script>
