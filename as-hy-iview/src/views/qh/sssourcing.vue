<template>
    <div class="layout">
        <Row type="flex" justify="center">
            <Col span="16">
                <Row class="breadcrumb">
                    <Col span="24">
                        <Breadcrumb>
                            <Breadcrumb-item href="/"><Icon type="home"></Icon></Breadcrumb-item>
                            <Breadcrumb-item >source</Breadcrumb-item>
                        </Breadcrumb>
                    </Col>
                </Row>
                <Row class="content-head">
                    <Col span="12">
                        QuotationName
                    </Col>
                    <Col span="12">
                        <Dropdown style="margin-left: 20px;float:right">
                            <Button type="ghost">
                                Action
                                <Icon type="arrow-down-b"></Icon>
                            </Button>
                            <Dropdown-menu slot="list">
                                <Dropdown-item>驴打滚</Dropdown-item>
                                <Dropdown-item>炸酱面</Dropdown-item>
                                <Dropdown-item disabled>豆汁儿</Dropdown-item>
                                <Dropdown-item>冰糖葫芦</Dropdown-item>
                                <Dropdown-item divided>北京烤鸭</Dropdown-item>
                            </Dropdown-menu>
                        </Dropdown>
                    </Col>
                </Row>
                <Row class="cell-row">
                    <Col span="24">
                        <Collapse v-model="value1">
                            <Panel name="1" class="coll-panl">
                                <span>Quotation Summary</span>
                                <span slot="content" style="pading:0">
                                    <Row class="panl-content">
                                        <Col v-for="cell in cellList" :key="cell.title" span="6" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    {{cell.title}}
                                                </div>
                                                <div class="content">
                                                    {{cell.content}}
                                                </div>
                                            </span>
                                        </Col>
                                    </Row>
                                    <Row class="panl-content">
                                        <Col span="24" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    General Remarks (AM)
                                                </div>
                                                <div class="content">
                                                    aaa
                                                </div>
                                            </span>
                                        </Col>
                                    </Row>
                                    <Row class="panl-content" style="border-top: 0px solid #f3f3f3">
                                        <Col span="24" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    General Remarks (SS)
                                                </div>
                                                <div class="content">
                                                    aaa
                                                </div>
                                            </span>
                                        </Col>
                                    </Row>
                                </span>
                            </Panel>
                        </Collapse>
                    </Col>
                </Row>
                <Row class="cell-row">
                    <Col span="24">
                        <Collapse v-model="value1">
                            <Panel name="2" class="coll-panl">
                                <span>Site View HQ</span>
                                <span slot="content" style="pading:0">
                                    <Row class="panl-content">
                                        <Col v-for="cell in cellList2" :key="cell.title" span="6" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    {{cell.title}}
                                                </div>
                                                <div class="content">
                                                    {{cell.content}}
                                                </div>
                                            </span>
                                        </Col>
                                        <Col span="12" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    General Remarks (AM)
                                                </div>
                                                <div class="content">
                                                    aaa
                                                </div>
                                            </span>
                                        </Col>
                                    </Row>
                                    <Row class="panl-content">Site Address (A - End)</Row>
                                    <Row class="panl-content">
                                        <Col span="12" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    General Remarks (AM)
                                                </div>
                                                <div class="content">
                                                    aaa
                                                </div>
                                            </span>
                                        </Col>
                                        <Col span="12" class="content-cell">
                                            <span class="data-cell">
                                                <div class="title">
                                                    General Remarks (AM)
                                                </div>
                                                <div class="content">
                                                    aaa
                                                </div>
                                            </span>
                                        </Col>
                                    </Row>
                                </span>
                            </Panel>
                        </Collapse>
                        <Row class="site-frame">
                            <Row class="site-content">
                                <Col span="18">
                                    aaa
                                </Col>
                                <Col span="6">
                                    <Button style="margin: 10px;float:right" @click="addOption">ADD OPTION</Button>
                                </Col>
                            </Row>
                            <Row class="site-content">
                                <Col span="18">
                                    <Col span="3">#1 Option 1</Col>
                                    <Col span="21">
                                        <Radio-group :model.sync="checkedProductLine" type="button" size="large" on-change="getChecked">
                                            <Radio v-for="button in buttons" :key="button.buttonName" :value="button.buttonName">{{button.buttonName}}</Radio>
                                        </Radio-group>
                                    </Col>
                                </Col>
                                <Col span="6">
                                    <Dropdown style="margin: 10px;float:right">
                                        <Button type="ghost">
                                            Action
                                            <Icon type="arrow-down-b"></Icon>
                                        </Button>
                                        <Dropdown-menu slot="list">
                                            <Dropdown-item>驴打滚</Dropdown-item>
                                            <Dropdown-item>炸酱面</Dropdown-item>
                                            <Dropdown-item disabled>豆汁儿</Dropdown-item>
                                            <Dropdown-item>冰糖葫芦</Dropdown-item>
                                            <Dropdown-item divided>北京烤鸭</Dropdown-item>
                                        </Dropdown-menu>
                                    </Dropdown>
                                </Col>
                            </Row>
                            <site-mian :siteList="siteList"></site-mian>
                            <site-other></site-other>
                        </Row>
                    </Col>
                </Row>
            </Col>
        </Row>
    </div>
</template>
<script>
    import siteMian from "./siteMain.vue";
    import siteOther from "./siteOther.vue";
    export default {
        components:{
            "site-mian":siteMian,
            "site-other":siteOther
        },
        data () {
            return {
                value1:[1],
                cellList:[
                    {title:"title1",content:"content1"},
                    {title:"title2",content:"content2"},
                    {title:"title3",content:"content3"},
                    {title:"title4",content:"content4"},
                    {title:"title5",content:"content5"},
                    {title:"title6",content:"content6"}
                ],
                cellList2:[
                    {title:"title1",content:"content1"},
                    {title:"title2",content:"content2"},
                    {title:"title3",content:"content3"},
                    {title:"title4",content:"content4"},
                ],
                productLine:[
                    {buttonName:'summary'},
                    {buttonName:'lancpe'},
                    {buttonName:'wincpe'},
                    {buttonName:'other'},
                ],
                checkedProductLine:"",
                siteList:[{siteName:"site1",optionList:[{optionName:"optionName11"},{optionName:"optionName12"}]},
                          {siteName:"site2",optionList:[{optionName:"optionName21"},{optionName:"optionName22"}]},
                          {siteName:"site3",optionList:[{optionName:"optionName31"},{optionName:"optionName32"}]}],
                visible: false,
            }
        },
        mounted(){
            let vm = this;
            vm.$Message.info("aaaaaaaa");
            vm.checkedProductLine = vm.productLine[0];
        },
        methods: {
            addOption(){
                let vm = this;
                vm.buttons.push({buttonName:'other'+(vm.buttons.length+1)});
            },
            getChecked(data){
                let vm = this;
                vm.checkedProductLine = data;
                console.log(data);
            }
        },
        beforeRouteEnter(to, from, next) {
            next();
        }
    }
</script>
<style>
    .layout{
        background: #f3f3f3;
    }
    .content-content{
        position: absolute;
        top: 25%;
        width: 100%;
    }
    .layout-nav-right{
        float: right;
    }

    .layout-nav-left{
        float: left;
    }

    .commn-head{
        height: 50px;
        background: #0064cd;
        color: #f3f3f3;
        z-index: 9999
    }
    .coll-head{
        background: #fff;
    }
    .breadcrumb{
        margin-top: 10px;
    }
    .content-head{
        margin-top: 10px;
        font-size: 24px;
        background: #f3f3f3;
    }
    .coll-panl{background: #fff;}
    .main-content .cell-row{margin-top: 10px;}
    .main-content .cell-row .coll-panl .ivu-collapse-content{padding: 0;}
    .main-content .cell-row .coll-panl .ivu-collapse-content>.ivu-collapse-content-box{padding: 0;}
    .main-content .cell-row .coll-panl .panl-content{border-top: 2px solid #f3f3f3}
    .main-content .cell-row .coll-panl .panl-content .content-cell{background: #f3f3f3;padding:0 2px 2px 0;height: 70px;}
    .main-content .cell-row .coll-panl .panl-content .content-cell .data-cell{background: #fff;float: left;width: 100%;height: 100%;}
    .main-content .cell-row .coll-panl .panl-content .content-cell .data-cell .title{margin: 10px 0 0 10px}
    .main-content .cell-row .coll-panl .panl-content .content-cell .data-cell .content{margin: 10px 0 0 10px}
    .main-content .cell-row .site-frame{border: 2px solid #f3f3f3; background: #fff; margin-top: 10px}
    .main-content .cell-row .site-frame .site-content{border-bottom: 2px solid #f3f3f3; background: #fff;padding: 10px}
    .layout .layout-nav-right .ivu-menu-submenu-title span>i {margin:0;}
</style>