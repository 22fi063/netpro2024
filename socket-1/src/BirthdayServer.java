import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BirthdayServer {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.nextLine();

            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("localhostの" + port + "番ポートで待機します");

                while (true) {
                    try (Socket clientSocket = serverSocket.accept();
                            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

                        System.out.println("クライアントと接続しました。");

                        BirthdayGift gift = (BirthdayGift) ois.readObject();
                        String receivedMessage = gift.getMessage();
                        String receivedGift = gift.getGift();
                        System.out.println("受信したメッセージ: " + receivedMessage);
                        System.out.println("受信したプレゼント: " + receivedGift);

                        BirthdayGift response = new BirthdayGift();
                        response.setMessage("サーバーからの返答: ありがとう！\n" + receivedGift + "を受け取りました。");
                        response.setGift("🎉 " + receivedGift + " 🎉");

                        oos.writeObject(response);
                        oos.flush();
                    } catch (Exception e) {
                        System.err.println("クライアントとの通信中にエラーが発生しました: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("サーバーの起動に失敗しました: " + e.getMessage());
        }
    }
}
