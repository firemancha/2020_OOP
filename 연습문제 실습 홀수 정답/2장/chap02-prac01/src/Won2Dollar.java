import java.util.Scanner;

public class Won2Dollar {

	public static void main(String[] args) {
		final double rate = 1100.0;
		Scanner scanner = new Scanner(System.in);
		System.out.print("원화를 입력하세요(단위 원)>>");
		int won = scanner.nextInt();
		double dollar = won/rate;
		System.out.printf(won + "원은 $" + dollar + "입니다.");
		scanner.close();
	}

}
