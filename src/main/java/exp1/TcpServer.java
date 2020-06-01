package exp1;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Sheva
 * @Date 2020/6/1
 */
public class TcpServer extends Thread {
    public static void main(String[] args){
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            //创建服务器
            ServerSocket server = new ServerSocket(1111);
            System.out.println("server start....");
            //监听客户端的连接
            socket = server.accept();
            //获取socket的输入输出流接收和发送信息
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            for (int i = 1; i <= 100; i++) {
                //接收客户端发送的信息
                String str = in.readLine();
                System.out.println("server receive：" + str);
            }

            //创建Socket对象，指定服务器端的IP与端口
            socket = new Socket(InetAddress.getLocalHost(), 1112);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //发送信息
            for (int i = 100; i > 0; i--) {
                out.write(String.valueOf(i) + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}