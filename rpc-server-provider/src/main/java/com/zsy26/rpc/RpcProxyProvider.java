package com.zsy26.rpc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: rpc-server
 * @description:
 * @author: Mr.Zheng
 * @create: 2019-06-22 22:01
 **/
public class RpcProxyProvider {

    ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * @Description: 监听端口 以及数据处理
     * @Param: [prot]
     * @return: void
     * @Author: Mr.Zheng
     * @Date: 2019/6/22
     */
    public void publisher(Object service ,int port) {

//        ServerSocket serverSocket = null;
//
//        try {
//            serverSocket = new ServerSocket(port);
//            while (true) {
//                Socket socket = serverSocket.accept();
//                //非异步 ， 线程会堵塞 效率不高 每一个线程一个socket
//                // OutputStream stream = socket.getOutputStream();
//                executorService.execute(new ProccessHandler(socket,service));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (serverSocket != null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}
