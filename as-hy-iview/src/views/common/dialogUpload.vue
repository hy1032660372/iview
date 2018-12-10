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
                    :on-remove="onRemove"
                    :default-file-list="originFileList"
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
            vm.actionUrl = vm.server_util+ "/file/fileUpload";
        },
        methods: {
            uploadFile(){
                let vm = this;
                vm.isShow = true;
                vm.originFileList = [];
            },
            ok () {
                let vm = this;
                let fileList = [];
                let file;

                if(vm.originFileList.length == 0){
                    vm.$Message.warning('Please select Attachments');
                    return;
                }

                _.each(vm.originFileList,function(file){
                    file = {
                        id:file.response.data.id,
                        fileName:file.name,
                        pathUrl:file.response.data.pathUrl,
                        fileType:"test"
                    }
                    fileList.push(file);
                });
                vm.$http.post(vm.server_util+ "/file/saveFileList",fileList).then(function(response){
                    vm.onNotify();
                }).catch(function (error) {
                    vm.$Message.error('Error!');
                });
            },
            cancel () {
                let vm = this;
                this.$Message.info('Clicked cancel');
            },
            onSuccess(response, file, fileList){
                let vm = this;
                debugger
                vm.originFileList = fileList;
            },
            onError(error, file, fileList){
                console.log(error);
            },
            onRemove(data){
                let vm = this;
                console.log(vm.$refs);
                for(let i = 0; i < vm.originFileList.length; i++){
                    if(vm.originFileList[i].name == data.name){
                        vm.originFileList.splice(i,1);
                        break;
                    }
                }
            },
            onNotify(){
                this.$emit('onNotify','ok');
            }
        }
    }
</script>

<style scoped>

</style>