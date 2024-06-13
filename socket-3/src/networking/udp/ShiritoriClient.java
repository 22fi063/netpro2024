package networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ShiritoriClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new DatagramSocket();
            InetAddress group = InetAddress.getByName("224.0.0.1"); // マルチキャストアドレス

            // 最初の単語を入力
            System.out.print("最初の単語を入力してください: ");
            String message = scanner.nextLine();
            byte[] sendData = message.getBytes();

            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, group, 9876);
            socket.send(packet);

            System.out.println("メッセージを送信しました: " + message);

            // しりとりのループ
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("受信したメッセージ: " + receivedMessage);

                // 次の単語を入力
                System.out.print("次の単語を入力してください: ");
                String nextMessage = scanner.nextLine();
                sendData = nextMessage.getBytes();
                packet = new DatagramPacket(sendData, sendData.length, group, 9876);
                socket.send(packet);

                System.out.println("メッセージを送信しました: " + nextMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            scanner.close();
        }
    }
}
