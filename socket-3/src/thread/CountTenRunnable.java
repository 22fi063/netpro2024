package thread;

// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
public class CountTenRunnable implements Runnable {

    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args) {
        // 2つの文字を初期化します。
        char c1 = 97; // ASCII値 97 は 'a' です
        char c2 = (char) (c1 + 1); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c3 = (char) (c1 + 2); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c4 = (char) (c1 + 3); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c5 = (char) (c1 + 4); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c6 = (char) (c1 + 5); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c7 = (char) (c1 + 6); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c8 = (char) (c1 + 7); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c9 = (char) (c1 + 8); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c10 = (char) (c1 + 9); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c11 = (char) (c1 + 10); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c12 = (char) (c1 + 11); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c13 = (char) (c1 + 12); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c14 = (char) (c1 + 13); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c15 = (char) (c1 + 14); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c16 = (char) (c1 + 15); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c17 = (char) (c1 + 16); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c18 = (char) (c1 + 17); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c19 = (char) (c1 + 18); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c20 = (char) (c1 + 19); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c21 = (char) (c1 + 20); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c22 = (char) (c1 + 21); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c23 = (char) (c1 + 22); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c24 = (char) (c1 + 23); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c25 = (char) (c1 + 24); // c1 に 1 を足すと ASCII値 98 になり、'b' になります
        char c26 = (char) (c1 + 25); // c1 に 1 を足すと ASCII値 98 になり、'b' になります

        // 初期化した文字をコンソールに出力します。
        System.out.println(c1); // 出力: a
        System.out.println(c2); // 出力: b

        // CountTenRunnable クラスのインスタンスを作成します。
        CountTenRunnable ct = new CountTenRunnable();

        // ct を実行する新しいスレッドを作成します。
        Thread th = new Thread(ct);
        CountAZTenRunnable ct1 = new CountAZTenRunnable(); // CountTenBクラスのインスタンスとして

        ct1.setChar(c1);

        CountAZTenRunnable ct2 = new CountAZTenRunnable(); // CountTenBクラスのインスタンスとして

        ct2.setChar(c2);
        // スレッドを開始します。これにより、CountTenRunnable の run メソッドが呼び出されます。
        th.start();

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

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("runnable thread:i=" + i);

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000); // ミリ秒単位のスリープ時間
            }
        } catch (InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }
}
