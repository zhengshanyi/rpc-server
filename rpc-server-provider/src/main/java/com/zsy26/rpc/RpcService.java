package com.zsy26.rpc;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: rpc-server
 * @description:
 * @author: Mr.Zheng
 * @create: 2019-06-24 20:13
 **/
//添加自定义注解


@Target(ElementType.TYPE) //作用范围 类 、接口
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    Class<?> value();  //拿到服务的接口
}
