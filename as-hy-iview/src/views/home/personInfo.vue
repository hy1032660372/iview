<style scoped>
    .img{
        width: 300px;
    }
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
                <img class="img" :src="imgUrl"/>
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
                isShow:false,
                imgUrl:""
            };
        },
        mounted(){
            let vm = this;
            vm.urlPre = "http://localhost:8086/util";
            vm.$http.get(vm.server_util+"/image/a3f5683baa254803a3e2f30256fc1815").then(function(response){
                vm.imgUrl = vm.urlPre + "/images/"+response.data.data.pathUrl;
            }).catch(function (error) {
                console.log(error);
            });
            //vm.imgUrl = "http://localhost:8086/util/images/hyd.png";
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