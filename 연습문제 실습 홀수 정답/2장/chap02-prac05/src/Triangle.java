import java.util.Scanner;

public class Triangle {
	public static void main (String args[])  {
		Scanner scanner = new Scanner(System.in);
		System.out.print("정수 3개를 입력하시오>>");
		int l1 = scanner.nextInt();
		int l2 = scanner.nextInt();
		int l3 = scanner.nextInt();
		if ((l1+12)<l3 || (l1+l3)<l2 ||(l2+l3)<l1) // 두변의 합이 다른 한변보다 작은 경우
			System.out.println("삼각형을 만들 수 없습니다.");
		else
			System.out.println("삼각형이 됩니다");
		
		scanner.close();
	}
}
