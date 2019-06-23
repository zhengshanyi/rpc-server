package com.zsy26.rpc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        HelloService helloService=new HelloServiceImpl();
        RpcProxyProvider provider = new RpcProxyProvider();
        provider.publisher(helloService,8080);

    }
}
