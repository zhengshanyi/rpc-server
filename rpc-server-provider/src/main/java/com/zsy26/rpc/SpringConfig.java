package com.zsy26.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rpc-server
 * @description:
 * @author: Mr.Zheng
 * @create: 2019-06-24 20:51
 **/

@Configuration
@ComponentScan("com.zsy26.rpc")
public class SpringConfig {

    @Bean("rpcServer")
    public RpcServer rpcServer(){
        return new RpcServer(8080);
    }


}
