<style scoped>
    .img{
        width: 240px;
        height: 200px;
        margin-top: 20px;
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
                <dialog-upload @onNotify="notify"></dialog-upload>
                <!--<img class="img" :src="imgUrl"/>-->
                <!--<img src="http://localhost:8086/util/showImage"/>-->
                <Row :gutter="16">
                    <Col :span="5" v-for="image in images" :key="image.id" >
                        <img class="img" :src="image.pathUrl"/>
                        <Button @click="downLoadPic(image.id)">downLoadPic</Button>
                        <Button @click="deletePic(image.id)">deletePic</Button>
                    </Col>
                </Row>
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
                imgUrl:"",
                images:[]
            };
        },
        mounted(){
            let vm = this;
            vm.getImages();
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
            getImages(){
                let vm = this;
                vm.$http.get(vm.server_util+"/image/getAllImage").then(function(response){
                    vm.images = [];
                    _.each(response.data,function(param){
                        param.pathUrl = vm.urlPre + "/images" + param.pathUrl+"/"+param.fileName;
                        vm.images.push(param);
                    });
                }).catch(function (error) {
                    console.log(error);
                });
                //vm.imgUrl = "http://localhost:8086/util/images/hyd.png";
            },
            notify(message){
                let vm = this;
                if("ok" == message){
                    vm.getImages();
                }
            },
            deletePic(attachmentId){
                let vm = this;
                vm.$http.get(vm.server_util+"/file/deleteFile/"+attachmentId).then(function(response){
                    for(let i = 0; i < vm.images.length; i++){
                        if(vm.images[i].id == attachmentId){
                            vm.images.splice(i,1)
                        }
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            },
            downLoadPic(attachmentId){
                let vm = this;
                let token = vm.$cookies.get("iView-token");
                window.open(vm.urlPre+"/common/fileDownLoad?token="+token+"&fileId="+attachmentId,'_blank');
            }
        },
        beforeRouteEnter(to, from, next) {
            next(function(vm){
                vm.getCurrentUser();
            });
        }
    }
</script>