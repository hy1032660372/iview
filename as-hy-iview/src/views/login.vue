<style scoped>
    
</style>
<template>
    <div class="layout">
        <Header>&nbsp;</Header>
        <Content>
            <Row>
                <Col span="8">&nbsp;</Col>
                <Col span="8">
                    <div>
                        <Form ref="formInline" :model="formInline" :rules="ruleInline">
                            <FormItem prop="user">
                                <Input type="text" v-model="formInline.user" placeholder="Username">
                                    <Icon type="ios-person-outline" slot="prepend"></Icon>
                                </Input>
                            </FormItem>
                            <FormItem prop="password">
                                <Input type="password" v-model="formInline.password" placeholder="Password">
                                    <Icon type="ios-locked-outline" slot="prepend"></Icon>
                                </Input>
                            </FormItem>
                            <FormItem>
                                <Button type="primary" @click="handleSubmit('formInline')">Signin</Button>
                            </FormItem>
                        </Form>
                    </div>
                </Col>
                <Col span="8">&nbsp;</Col>
            </Row>
        </Content>
        <Footer>&nbsp;</Footer>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                formInline: {
                    user: '',
                    password: ''
                },
                ruleInline: {
                    user: [
                        { required: true, message: 'Please fill in the user name', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'Please fill in the password.', trigger: 'blur' },
                        { type: 'string', min: 6, message: 'The password length cannot be less than 6 bits', trigger: 'blur' }
                    ]
                },
            }
        },
        methods: {
            handleSubmit(name) {
                let vm = this;
                let data = {
                    client_id:"browser",
                    grant_type:"password",
                    scope:"ui",
                    username:vm.formInline.user,
                    password:vm.formInline.password
                };
                vm.$refs[name].validate((valid) => {
                    if (valid) {
                        let paramsStr = "client_id="+data.client_id
                                    +"&grant_type="+data.grant_type
                                    +"&scope="+data.scope
                                    +"&username="+data.username
                                    +"&password="+data.password;
                        vm.$http.post(vm.server_auth+"/oauth/token",paramsStr).then(function(response){
                                //save token
                                setCookie('token', response.data.access_token)
                                setCookie('refresh_token', response.data.refresh_token)
                                sessionStorage.setItem("token_key", response.data.access_token);
                                vm.$router.push("/home/index");
                            }
                        ).catch(function (error) {
                            console.log(error);
                        });
                        vm.$Message.success('Success!');
                    } else {
                        vm.$Message.error('Fail!');
                    }
                })
            }
        }
    }
</script>