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
                <div>current user:{{userInfo.username}}</div>
                <div>current role:{{userInfo.currentRole.title}}</div>
            </Card>
        </Content>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                userInfo:{}
            };
        },
        mounted(){
            let vm = this;
            vm.userInfo = window.currentUser;
        },
        methods:{
        },
        beforeRouteEnter(to, from, next) {
            next();
        }
    }
</script>