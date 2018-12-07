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
                <dialog-upload></dialog-upload>
                <img src="http://localhost:8086/util/test/hyd.png"/>
            </Card>
        </Content>
    </div>
</template>
<script>
    import dialogUpload from '../common/dialogUpload'
    export default {
        components: {
            "dialog-upload": dialogUpload,
        },
        data () {
            return {
                userInfo:{
                    username:"",
                    currentRole:"",
                },
                isShow:false
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
        },
        beforeRouteEnter(to, from, next) {
            next(function(vm){
                vm.getCurrentUser();
            });
        }
    }
</script>