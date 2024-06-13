package networking.multicastudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class UDPMulticastServer {
    private static Set<String> usedWords = new HashSet<>();
    private static String lastWord = "";

    public static void main(String[] argv) throws Exception {
        // 5100番ポートを監視するUDPソケットを生成
        DatagramSocket receiveSocket = new DatagramSocket(5100);

        // 受け付けるデータバッファとUDPパケットを作成
        byte receiveBuffer[] = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

        while (true) {
            // UDPパケットを受信
            receiveSocket.receive(receivePacket);

            // 受信したデータを標準出力へ出力
            String receivedWord = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println("クライアントから受信: " + receivedWord);

            // しりとりの単語がすでに使われたか、正しいかをチェック
            if (usedWords.contains(receivedWord) || 
                (!lastWord.isEmpty() && !receivedWord.startsWith(String.valueOf(lastWord.charAt(lastWord.length() - 1))))) {
                String response = "無効な単語: " + receivedWord + ". しりとり終了";
                byte[] responseBuffer = response.getBytes(StandardCharsets.UTF_8);
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
                receiveSocket.send(responsePacket);
                break;
            } else {
                usedWords.add(receivedWord);
                lastWord = receivedWord;
                String response = "有効な単語: " + receivedWord + ". 次の単語を入力してください";
                byte[] responseBuffer = response.getBytes(StandardCharsets.UTF_8);
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, receivePacket.getAddress(), receivePacket.getPort());
                receiveSocket.send(responsePacket);
            }
        }
        receiveSocket.close();
    }
}
