package exp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author Sheva
 * @Date 2020/6/1
 */
public class UdpServer {

    public static void main(String[] args) {
        try {
            System.out.println("server start!!!");
            //设置接收端端口
            DatagramSocket server = new DatagramSocket(10004);
            //设置发送端口号
            DatagramSocket sendSocket = new DatagramSocket(10006);
            DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
            //设置目的地址（本机）
            datagramPacket.setAddress(InetAddress.getLocalHost());
            //设置目的端口号
            datagramPacket.setPort(10005);
            for(int i = 100; i >= 1; i--){
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                //阻塞等待输入
                server.receive(packet);
                String receiveData = new String(packet.getData(),0,packet.getLength());
                System.out.println("server receive: " + receiveData);
                byte[] buf1 = String.valueOf(i).getBytes();
                //存入数据到packet
                datagramPacket.setData(buf1);
                //发送数据
                sendSocket.send(datagramPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
