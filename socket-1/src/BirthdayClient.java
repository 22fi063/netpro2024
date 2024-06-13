import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BirthdayClient {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("ポートを入力してください(5000など) → ");
            int port = scanner.nextInt();
            scanner.nextLine();

            try (Socket socket = new Socket("localhost", port);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                System.out.println("サーバに接続されました");

                while (true) {
                    System.out.print("メッセージを入力してください (例: ハッピーバースデー) → ");
                    String message = scanner.nextLine();
                    if (message.equalsIgnoreCase("quit") || message.equalsIgnoreCase("exit")) {
                        break;
                    }

                    System.out.print("プレゼントの内容を入力してください (例: ケーキ) → ");
                    String gift = scanner.nextLine();

                    BirthdayGift present = new BirthdayGift();
                    present.setMessage(message);
                    present.setGift(gift);

                    oos.writeObject(present);
                    oos.flush();

                    BirthdayGift response = (BirthdayGift) ois.readObject();
                    String responseMessage = response.getMessage();
                    String responseGift = response.getGift();
                    System.out.println("サーバからのメッセージ: " + responseMessage);
                    System.out.println("返送されたプレゼント: " + responseGift);
                }
            }
        } catch (Exception e) {
            System.err.println("クライアントでエラーが発生しました: " + e.getMessage());
        }
    }
}
