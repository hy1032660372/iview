<template>
    <div>
        <Button type="primary" @click="uploadFile">Upload</Button>
        <Modal
                v-model="isShow"
                title="Common Modal dialog box title"
                @on-ok="ok"
                @on-cancel="cancel">
            <Upload
                    multiple
                    type="drag"
                    name="file"
                    :headers="headers"
                    :on-success="onSuccess"
                    :on-error="onError"
                    :data="extData"
                    :action="actionUrl">
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
        //name: "dialogUpload.vue",
        data () {
            return {
                isShow:false,
                actionUrl:"",
                originFileList:[],
                extData:{},
            }
        },
        computed:{
            headers(){
                let vm = this;
                let token = vm.$cookies.get("iView-token");
                return {Authorization: 'bearer '+ token}
            }
        },
        mounted(){
            let vm= this;
            vm.actionUrl = vm.server_util+ "/upload/fileUpload";
        },
        methods: {
            uploadFile(){
                let vm = this;
                vm.isShow = true
            },
            ok () {
                let vm = this;
                let fileList = [];
                let file;
                _.each(vm.originFileList,function(file){
                    file = {
                        id:file.response.data.id,
                        fileName:file.name,
                        pathUrl:file.response.data.pathUrl,
                        fileType:"test"
                    }
                    fileList.push(file);
                });
                vm.$http.post(vm.server_util+ "/upload/saveFileList",fileList).then(function(response){
                    console.log(response);
                }).catch(function (error) {
                    console.log(error);
                    vm.$Message.error('Error!');
                });
                this.$Message.info('Clicked ok');
            },
            cancel () {
                console.log(window.server_util);
                this.$Message.info('Clicked cancel');
            },
            onSuccess(response, file, fileList){
                let vm = this;
                vm.originFileList = fileList;
            },
            onError(error, file, fileList){
                console.log(error);
            },
        }
    }
</script>

<style scoped>

</style>