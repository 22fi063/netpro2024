import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class HeikinCKadai {
	public static final int N = 100;
	Kamoku[] kamoku = new Kamoku[N];
	static String kamokuname = "数学";

	public static void main(String[] args) {
		HeikinCKadai heikinc = new HeikinCKadai(kamokuname);
		heikinc.initalizeScores();
		heikinc.printAverage();
		heikinc.gokakusha();

	}

	HeikinCKadai(String s) {
		this.kamokuname = s;

	}

	void initalizeScores() {
		Random r = new Random();

		for (int i = 0; i < N; i++) {
			int score = r.nextInt(N + 1);
			kamoku[i] = new Kamoku(score);

		}

	}

	void printAverage() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += kamoku[i].getScore();

		}
		System.out.println("平均点は" + sum / N);

	}

	void gokakusha() {
		Kamoku[] passedStudents = Arrays.stream(kamoku)
				.filter(k -> k.getScore() >= 80)
				.toArray(Kamoku[]::new);

		if (passedStudents.length > 0) {
			// 点数で合格者をソート
			Arrays.sort(passedStudents, Comparator.comparingInt(Kamoku::getScore));

			System.out.println("以下合格者一覧（80点以上）:");
			for (Kamoku student : passedStudents) {
				System.out.println("学生番号: " + student.getStudentID() + ", 点数: " + student.getScore());
			}
		} else {
			System.out.println("80点以上の合格者はいません。");
		}
	}// student idと点数をソートしてだせ。＞＝８０以上

	class Kamoku {
		private int score;
		private static int counter = 1;
		private int studentID;

		Kamoku(int score) {
			this.score = score;
			this.studentID = counter++;
		}

		int getScore() {
			return score;
		}

		int getStudentID() {
			return studentID;
		}
	}
}