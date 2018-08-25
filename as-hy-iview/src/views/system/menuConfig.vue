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
                                        <Button type="primary" v-if="access" @click="saveRoleAndMenu">Save Role And Menu</Button>
                                        <Button type="primary" v-if="access" @click="addMenuModel=true">Add Menu</Button>
                                        <Button type="primary" v-if="access && roleList.length == 0" @click="removeMenu">Remove Menu</Button>
                                    </Col>
                                </Row>
                                <Table
                                        :columns="columnsList"
                                        :data="roleList"
                                        @on-select="onSelectRole"></Table>
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
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
    </div>
</template>
<script>
    export default {
        data () {
            return {
                menuData: [],
                roleList:[],
                selection:[],
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
            onSelectRole(selection){
                let vm = this;
                vm.selection = selection;
            },
            onSelectChange(data){
                let vm = this;
                if(data.length != 0){
                    vm.currentMenu = data[0];
                    vm.getRoleList();
                }
            },
            getAllMenuList(){
                let vm = this;
                vm.$http.get(vm.server_account+"/menu/getMenuList").then(function(response){
                    vm.menuData = [];
                    vm.menuData.push(response.data.data);
                    vm.currentMenu = vm.menuData[0];
                    vm.getRoleList();
                });
            },
            getRoleList(){
                let vm = this;
                vm.$http.get(vm.server_account+"/role/getUserAuthRole").then(function(response){
                    vm.roleList = response.data.data;
                    vm.getRoleCurrent();
                });
            },
            getRoleCurrent(){
                let vm = this;
                let param = {
                    menuCode:vm.currentMenu.code
                };
                let roleListBack = _.cloneDeep(vm.roleList);
                vm.$http.get(vm.server_account+"/role/getRoleCurrent",{params:param}).then(function(response){
                    _.each(roleListBack,function(role){
                        role._checked = false;
                        _.each(response.data.data,function(data){
                            if(data.roleCode == role.code){
                                role._checked = true;
                            }
                        });
                    });
                    vm.roleList = roleListBack;
                });
            },
            saveRoleAndMenu(){
                let vm = this;
                let roleAndMenu;
                let roleAndMenuList = [];
                _.each(vm.selection,function (role) {
                    console.log(role);
                    roleAndMenu = {
                        roleCode:role.code,
                        menuCode:vm.currentMenu.code
                    };
                    roleAndMenuList.push(roleAndMenu);
                });
                vm.$http.post(vm.server_account+"/roleAndMenu/insertRoleAndMenu",roleAndMenuList).then(function(response){
                    console.log(response);
                });
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
                vm.getAllMenuList();
            })
        }
    }
</script>
