<style scoped>

</style>
<template>
    <div class="layout">
        <Content :style="{padding: '0px 16px 16px'}">
            <Breadcrumb :style="{margin: '16px 0'}">
                <BreadcrumbItem>Home</BreadcrumbItem>
                <BreadcrumbItem>Person Information</BreadcrumbItem>
            </Breadcrumb>
            <Card>
                <div>current user:  {{userInfo.username}}</div>
                <div>current role:  {{userInfo.currentRole}}</div>
                <div><Button type="primary" @click="upload">Primary</Button></div>
            </Card>
        </Content>
        <Modal
                v-model="isShow"
                title="Common Modal dialog box title"
                @on-ok="ok"
                @on-cancel="cancel">
            <Upload
                    multiple
                    type="drag"
                    action="//jsonplaceholder.typicode.com/posts/">
                <div style="padding: 20px 0">
                    <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                    <p>Click or drag files here to upload</p>
                </div>
            </Upload>
        </Modal>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                userInfo:{
                    username:"",
                    currentRole:"",
                    isShow:false
                }
            };
        },
        mounted(){
            let vm = this;
        },
        methods:{
            getCurrentUser(){
                let vm = this;
                vm.$http.get(vm.server_account+"/accounts/current").then(function(data){
                    vm.userInfo = {
                        username:data.data.data.userName,
                        currentRole:data.data.data.currentRole.title
                    }
                }).catch(function (error) {
                    console.log(error);
                    vm.$Message.error('Error!');
                });
            },
            upload(){
                let vm = this;
                vm.isShow = true
            }
        },
        beforeRouteEnter(to, from, next) {
            next(function(vm){
                vm.getCurrentUser();
            });
        }
    }
</script>