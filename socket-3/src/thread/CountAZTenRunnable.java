package thread;

// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
public class CountAZTenRunnable implements Runnable {
    String character = "null";

    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args) {
        // CountTenRunnable クラスのインスタンスを作成します。
        CountAZTenRunnable[] c = new CountAZTenRunnable[26];
        for (int i = 0; i < 26; i++) {
            c[i] = new CountAZTenRunnable();
            c[i].setChar((char) (97 + i) + "");
        }


        for (CountAZTenRunnable ch : c) {
            // ct を実行する新しいスレッドを作成します。
            Thread th = new Thread(ch);
            // スレッドを開始します。これにより、CountTenRunnable の run メソッドが呼び出されます。
            th.start();
        }

        // この try-catch ブロックは、0 から 9 までの値を 500 ミリ秒間隔で出力するループを実行します。
        try {

            for (int i = 0; i < 10; i++) {
                System.out.println("main:i=" + i);

                // メインスレッドを 500 ミリ秒間一時停止します。
                Thread.sleep(500); // ミリ秒単位のスリープ時間
            }
        } catch (InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }

    public void setChar(String character) {
        this.character = character;
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println( character+" thread:i=" + i);

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000); // ミリ秒単位のスリープ時間
            }
        } catch (InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }
}
