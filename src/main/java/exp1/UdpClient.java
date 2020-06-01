package exp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Date：2020/6/1 9:33 上午
 * @author sheva
 */
public class UdpClient {
    public static void main(String[] args) {
        try {
            System.out.println("client start!!!");
            //创建socket，设置发送端端口号
            DatagramSocket clientSocket = new DatagramSocket(10003);
            //设置接收端口号
            DatagramSocket revSocket = new DatagramSocket(10005);
            DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
            //设置目的地址（本机）
            datagramPacket.setAddress(InetAddress.getLocalHost());
            //设置目的端口号
            datagramPacket.setPort(10004);
            for(int i = 1; i <= 100; i++){
                byte[] buf = String.valueOf(i).getBytes();
                //存入数据到packet
                datagramPacket.setData(buf);
                //发送数据
                clientSocket.send(datagramPacket);
                //接收数据
                byte[] buf1 = new byte[1024];
                DatagramPacket packet1 = new DatagramPacket(buf1, buf1.length);
                revSocket.receive(packet1);
                String receiveData = new String(packet1.getData(),0,packet1.getLength());
                System.out.println("client receive: " + receiveData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
