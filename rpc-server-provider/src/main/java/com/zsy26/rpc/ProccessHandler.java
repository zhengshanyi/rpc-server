package com.zsy26.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @program: rpc-server
 * @description:
 * @author: Mr.Zheng
 * @create: 2019-06-22 22:15
 **/
public class ProccessHandler implements Runnable {


    private Socket socket;

    private Map<String,Object> handlerMap;

    public ProccessHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }


    /**
     * @Description: 获取socket 传递的信息
     * @Param: []
     * @return: void
     * @Author: Mr.Zheng
     * @Date: 2019/6/22
     */
    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //流中应该有什么信息?
            //类 方法 参数
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //从返回参数 构建实例
            Object result = invoke(rpcRequest);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取请求中的类名 在hadlermap中 获取实例
        Object service = handlerMap.get(rpcRequest.getClassName());
        if (service == null){
            throw new RuntimeException("the "+rpcRequest.getClassName()+" bean not found");
        }
        //获取请求参数
        Object[] args = rpcRequest.getParamters();
        //获得每个参数
        Class<?>[] types = new Class[args.length];
        for (int i = 0, l = args.length; i < l; i++) {
            types[i] = args[i].getClass();
        }
        //获取类
        String className = rpcRequest.getClassName();
        Class clazz = Class.forName(className);
        //获取方法
        String methodName = rpcRequest.getMethodName();
        Method method = clazz.getMethod(methodName, types);
        Object result=method.invoke(service,args);//HelloServiceImpl 进行反射调用
        return result;

    }
}
