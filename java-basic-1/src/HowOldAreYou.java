
// C言語では、#include に相当する
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HowOldAreYou {

	public static void main(String[] args) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int birthYear;

		try {
			while (true) {
				System.out.println("何歳ですか？(qまたはeで終了)");
				String key = reader.readLine();

				if (key.equalsIgnoreCase("q") || key.equalsIgnoreCase("e")) {
					break;
				}

				try {
					int age = Integer.parseInt(key);

					if (age <= 0 || age >= 120) {
						System.out.println("正しい年齢を入力してください。");
						continue;
					}

					birthYear = 2024 - age;
					String era = getEra(birthYear);

					System.out.println("2030年時点でのあなたの年齢は " + (age + 6) + " 歳です。");
					System.out.println("あなたが生まれた年は " + era + toJapaneseYear(birthYear) + " 年です。");
				} catch (NumberFormatException e) {
					System.out.println("正しい数字を入力してください。");
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static String getEra(int year) {
		if (year >= 2019) {
			return "令和";
		} else if (year >= 1989) {
			return "平成";
		} else if (year >= 1926) {
			return "昭和";
		} else if (year >= 1912) {
			return "大正";
		} else {
			return "明治";
		}
	}

    public static String toJapaneseYear(int year) {
        int eraYear;
        String era;

        if (year >= 2019) {
            era = "令和";
            eraYear = year - 2018;
        } else if (year >= 1989) {
            era = "平成";
            eraYear = year - 1988;
        } else if (year >= 1926) {
            era = "昭和";
            eraYear = year - 1925;
        } else if (year >= 1912) {
            era = "大正";
            eraYear = year - 1911;
        } else {
            era = "明治";
            eraYear = year - 1867;
        }

        return era + eraYear;
    }
}

// 課題 キーボードから数字を打ち込む
// その結果、 あなたは、???歳ですね、と画面に表示させる。
// その後、あなたは10年後、????歳ですね、と画面に表示させる。
