import java.util.Scanner;

public class StackApp {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("총 스택 저장 공간의 크기 입력 >> ");
		int n = scanner.nextInt();
		
		StringStack ss = new StringStack(n); // n개의 문자열이 저장 가능한 스택 생성
		
		while(true) {
			System.out.print("문자열 입력 >> ");
			String str = scanner.next();
			if(str.equals("그만"))
				break;
			boolean res = ss.push(str); // 스택에 저장
			if(res == false) {
				System.out.println("스택이 꽉 차서 푸시 불가!");
			}
		}
		
		System.out.print("스택에 저장된 모든 문자열 팝 : ");
		int len = ss.length(); // 현재 스택에 저장된 문자열 개수
		for(int i=0; i<len; i++) { // 스택에 저장된 개수만큼 루프
			System.out.print(ss.pop() + " "); // 스택에서 팝하여 출력
		}

		scanner.close();
	}
}
