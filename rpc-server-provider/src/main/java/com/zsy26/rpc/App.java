package com.zsy26.rpc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ){
//        HelloService helloService=new HelloServiceImpl();
//        RpcProxyProvider provider = new RpcProxyProvider();
//        provider.publisher(helloService,8080);
//        RpcServer rpcServer = new RpcServer(8080);

        //修改启动类 通过注解启动
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) context).start();

    }
}
