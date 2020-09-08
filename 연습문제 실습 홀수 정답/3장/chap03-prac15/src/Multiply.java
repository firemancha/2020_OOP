import java.util.InputMismatchException;
import java.util.Scanner;

public class Multiply {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print("곱하고자 하는 두 수 입력>>");
			try {
				int n = scanner.nextInt();
				int m = scanner.nextInt();
				System.out.print(n + "x" + m + "=" + n*m);
				break;
			} catch(InputMismatchException e) {
				System.out.println("실수는 입력하면 안됩니다.");
				scanner.nextLine(); // <Enter> 키까지 읽어서 버린다.
				continue;
			}
		} while(true);

		scanner.close();
	}
}
