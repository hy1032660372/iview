<style scoped>
    .layout .table-header-left{background-color: #f7f7f7;border-right: 1px solid #ddd;padding: 20px}
    .layout .table-header-right{background-color: #f7f7f7;padding: 20px}
    .layout .table-body{background-color: #fff;border: 1px solid #ddd;}
    .layout .table-body .table-left-item{background-color: #fff;padding: 10px;} 
    .layout .table-body .table-right{background-color: #fff;border: 1px solid #ddd;} 
    .layout .table-body .table-right-item{background-color: #fff;border-bottom: 1px solid #ddd;padding: 10px} 
</style>
<template>
    <div class="layout">
        <Row class="table-header">
            <Col span="12" class="table-header-left">Section Name</Col>
            <Col span="12" class="table-header-right">Product Name</Col>
        </Row>
        <Row class="table-body" v-for="site in siteList" :key="site.siteName">
            <Col span="12" class="table-left-item">{{site.siteName}}</Col>
            <Col span="12" class="table-right">
                <Col span="24" class="table-right-item" v-for="option in site.optionList" :key="option.optionName">
                    {{option.optionName}}
                </Col>
            </Col>
        </Row>
    </div>
</template>
<script>
    export default {
        props:{
            siteList:{
                type:Array
            }
        },
        data () {
            return {
                
            };
        },
        mounted(){
            let vm = this;
            console.log(vm.siteList);
        },
        methods: {
            
        }
    }
</script>