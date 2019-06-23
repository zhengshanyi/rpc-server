package com.zsy26.rpc;


/**
 * @program: rpc-server
 * @description: User
 * @author: Mr.Zheng
 * @create: 2019-06-22 21:36
 **/

public interface HelloService {


    String sayHello(String content);



    User saveUser(User user);


}
