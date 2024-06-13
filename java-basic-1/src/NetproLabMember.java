import java.math.BigInteger;
import java.util.Random;

public class NetproLabMember {
  public static final int years = 15;
  public static final int columns = 3;

  public static void main(String[] args) {
    int[][] members = new int[years][columns];
    Random random = new Random();
    double totalRatio = 1;
    for (int i = 0; i < years; i++) {
      // 学生の総数
      members[i][0] = 120 + (random.nextInt(20) - 10);
      // 女性の割合(%)
      members[i][1] = 20 + i;
      // 岩井研の人数
      members[i][2] = 10 + (random.nextInt(6) - 3);

      // 男性数を求める
      int men = members[i][0] - (int) (members[i][0] * ((float) members[i][1] / 100));

      // 女性の数
      int women = (int) (members[i][0] * ((float) members[i][1] / 100));

      // 総数から岩井研の人数を取り出す組み合わせ
      BigInteger cpsRatio = combination(members[i][0], members[i][2]);

      // 男性の中から岩井研のメンバーを取り出す組み合わせ
      BigInteger menRatio = combination(men, members[i][2]);

      // 岩井研の人数に男性しか入らない割合
      totalRatio *=  menRatio.doubleValue() / cpsRatio.doubleValue();
    }
    System.out.println(totalRatio);
  }

  // Combination
  public static final BigInteger combination(final int n, int r) {
    BigInteger numerator = factorial(n);
    BigInteger denominator = factorial(r).multiply(factorial(n - r));
    return numerator.divide(denominator);
  }

  private static BigInteger factorial(int num) {
    BigInteger fact = BigInteger.ONE;
    for (int i = 2; i <= num; i++) {
      fact = fact.multiply(BigInteger.valueOf(i));
    }
    return fact;
  }
}