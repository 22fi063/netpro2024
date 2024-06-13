package networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ABCClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            System.out.print("メッセージを入力してください: ");
            String message = scanner.nextLine();
            byte[] sendData = message.getBytes();
            byte[] receiveData = new byte[1024];

            // 送信パケットを作成
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            socket.send(sendPacket);

            // 返信パケットを受信
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String responseMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("返信: " + responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
