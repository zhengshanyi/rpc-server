package com.zsy26.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: rpc-server
 * @description:
 * @author: Mr.Zheng
 * @create: 2019-06-24 20:50
 **/
public class RpcServer implements ApplicationContextAware ,InitializingBean {

    private int port;

    private Map<String,Object> handlerMap = new HashMap<>();

    public RpcServer(int port) {
        this.port = port;
    }

    ExecutorService executorService = Executors.newCachedThreadPool();


    //bean 初始化后执行的方法
    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                //非异步 ， 线程会堵塞 效率不高 每一个线程一个socket
                // OutputStream stream = socket.getOutputStream();
                executorService.execute(new ProccessHandler(socket,handlerMap));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取所有被pcService 注解扫描的类
        Map<String, Object> serviceBeans = applicationContext.getBeansWithAnnotation(RpcService.class);
        //遍历bean  获取每个bean上的rpcService 注解
        if (!serviceBeans.isEmpty()){
            for (Object serviceBean : serviceBeans.values()){
                //拿到bean上的注解
                RpcService service = serviceBean.getClass().getAnnotation(RpcService.class);
                String serviceName = service.value().getName();
                handlerMap.put(serviceName,serviceBean);
            }

        }

    }
}
