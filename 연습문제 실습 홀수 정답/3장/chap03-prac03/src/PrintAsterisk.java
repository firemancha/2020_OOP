import java.util.Scanner;

public class PrintAsterisk {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("정수를 입력하시오>>");
		int n = scanner.nextInt();
		if (n <=0) {
			System.out.println("0보다 커야 합니다.");
			scanner.close();
			return;
		}
		
		for (int i=n; i>0; i--) { // n줄 출력
			for (int j=0; j<i; j++) { // i개의 별표를 한 줄에 출력
				System.out.print('*');
			}
			System.out.println(); // 다음 줄로 넘어가기
		}
		scanner.close();
	}
}