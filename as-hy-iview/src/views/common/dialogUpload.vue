<template>
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
</template>

<script>
    export default {
        name: "dialogUpload.vue",
        data () {
            return {
                isShow:false,
            }
        },
        methods: {
            ok () {
                this.$Message.info('Clicked ok');
            },
            cancel () {
                this.$Message.info('Clicked cancel');
            }
        }
    }
</script>

<style scoped>

</style>