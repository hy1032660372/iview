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
                <div>Person Information</div>
            </Card>
        </Content>
    </div>
</template>
<script>
    export default {
        data () {
            return {

            };
        },
        mounted(){
            let vm = this;
        },
        beforeRouteEnter(to, from, next) {
            next();
        }
    }
</script>