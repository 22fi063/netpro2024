package networking.multicastudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPMulticastClient {
    public static void main(String[] argv) throws Exception {
        // ブロードキャストアドレスとポート（ネットワーク設定に合わせて調整）
        InetSocketAddress remoteAddress = new InetSocketAddress("192.168.1.255", 5100);
        
        // DatagramSocketインスタンスを生成
        DatagramSocket socket = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 送信するデータを入力
            System.out.print("しりとりの単語を入力してください: ");
            String str = scanner.nextLine();
            byte[] sendBuffer = str.getBytes(StandardCharsets.UTF_8);

            // データとリモートアドレスを含むUDPパケットを作成
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, remoteAddress);

            // UDPパケットを送信
            socket.send(sendPacket);

            // 受信バッファとパケットを作成
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            // サーバーからの応答を受信
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println("サーバーからの応答: " + response);

            // サーバーが終了を示唆する場合にループを終了
            if (response.contains("しりとり終了")) {
                System.out.println("しりとりが終了しました。");
                break;
            }
        }
        scanner.close();
        socket.close();
    }
}
