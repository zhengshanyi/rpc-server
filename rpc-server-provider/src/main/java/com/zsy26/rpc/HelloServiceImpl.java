package com.zsy26.rpc;

/**
 * @program: rpc-server
 * @description: HelloServiceImpl
 * @author: Mr.Zheng
 * @create: 2019-06-22 21:55
 **/
@RpcService(value = HelloService.class)
public class HelloServiceImpl implements HelloService {



    @Override
    public String sayHello(String content) {

        System.out.println("sayHelloContent"+content);
        content = "接收到请求";
        return content;
    }

    @Override
    public User saveUser(User user) {
        return user;
    }


}
