package com.zsy26.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: rpc-server
 * @description:
 * @author: Mr.Zheng
 * @create: 2019-06-22 22:39
 **/

@Data
public class RpcRequest implements Serializable {

    private String className;

    private String methodName;

    private Object[] paramters;
}
