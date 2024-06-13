public class XmasTreeKadai {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            for (int j = 14; j > 0; j--) {
                if (i < j) {
                    if ((j + i) % 2 == 1) {
                        System.out.print(" ");
                    } else {
                        System.out.print("+");
                    }
                } else if (i < 14) {
                    System.out.print("**");
                } else {
                    if (j == 1 ) {
                        System.out.print("**");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }
}
