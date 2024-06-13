class Renshu {

    // xを2倍にして返す関数
    public int doubleValue(int x) {
        return x * 2;
    }

    public int sumUpToN(int n) {
        int r = 0;
        for (int i = 1; i <= n; i++) {
            r += i;
        }
        return r;
    }

    public int sumFromPtoQ(int p, int q) {
        return sumUpToN(q) - sumUpToN(p - 1);
    }

    public int sumFromArrayIndex(int[] a, int index) {
        if (a.length <= index||0>index) {
            return -1;
        }
        int r = 0;
        for (int i = index; i < a.length; i++) {
            r += a[i];
        }
        return r;
    }

    public int selectMaxValue(int[] a) {
        int r = a[0];
        for (int i = 1; i < a.length; i++) {
            if (r < a[i]) {
                r = a[i];
            }
        }
        return r;
    }

    public int selectMaxIndex(int[] a) {
        int r = 0;
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
                r = i;
            }
        }
        return r;
    }

    public void swapArrayElements(int[] p, int i, int j) {
        int a = p[i];
        int b = p[j];
        p[i] = b;
        p[j] = a;
    }

    public boolean swapTwoArrays(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            int p = a[i];
            int q = b[i];
            a[i] = q;
            b[i] = p;
        }
        return true;
    }

    // ここに続きを実装していく。
}
