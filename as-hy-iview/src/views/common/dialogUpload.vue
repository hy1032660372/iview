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
                attachmentList:[],
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
                vm.attachmentList = [];
            },
            ok () {
                let vm = this;

                if(vm.attachmentList.length == 0){
                    vm.$Message.warning('Please select Attachments');
                    return;
                }

                _.each(vm.attachmentList,function(file){
                    file.pathUrl = '/'+currentUser.userId;
                    file.status = 1;
                    file.fileType = '' ;
                });

                vm.$http.post(vm.server_util+ "/file/saveFileList",vm.attachmentList).then(function(response){
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
                vm.attachmentList.push(response.data);
            },
            onError(error, file, fileList){
                console.log(error);
            },
            onRemove(data){
                let vm = this;
                for(let i = 0; i < vm.attachmentList.length; i++){
                    if(vm.attachmentList[i].fileName == data.name){
                        vm.attachmentList.splice(i,1);
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