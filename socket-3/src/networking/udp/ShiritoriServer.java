package networking.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class ShiritoriServer {
    public static void main(String[] args) {
        MulticastSocket socket = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new MulticastSocket(9876);
            InetAddress group = InetAddress.getByName("224.0.0.1"); // マルチキャストアドレス
            socket.joinGroup(group); // グループに参加

            byte[] receiveData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);

            System.out.println("しりとりのメッセージを待機中...");

            // しりとりのループ
            while (true) {
                socket.receive(packet); // パケットを受信
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("受信したメッセージ: " + message);

                // 次の単語を入力
                System.out.print("次の単語を入力してください: ");
                String nextMessage = scanner.nextLine();
                byte[] sendData = nextMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, group, 9876);
                socket.send(sendPacket);

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
