package thread;

// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
public class Multiplication implements Runnable {
    int num = 0;

    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args) {
        // CountTenRunnable クラスのインスタンスを作成します。
        Multiplication[] m = new Multiplication[10];
        for (int i = 1; i < 10; i++) {
            m[i] = new Multiplication();
            m[i].setNum(i);
        }


        for (Multiplication ml : m) {
            // ct を実行する新しいスレッドを作成します。
            Thread th = new Thread(ml);
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

    public void setNum(int n) {
        this.num = n;
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for (int i = 1; i < 10; i++) {
                System.out.println( num+"*"+i+"="+num*i+" thread:i=" + i);

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000); // ミリ秒単位のスリープ時間
            }
        } catch (InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }
}
