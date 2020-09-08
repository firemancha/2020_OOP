import java.util.Scanner;

public class ChangeMoney {
	// 환산할 돈의 종류 상수 선언. 한글 변수 사용해보았음
	final static int 오만원 = 50000;
	final static int 만원 = 10000;
	final static int 천원 = 1000;
	final static int 오백원 = 500;
	final static int 백원 = 100;
	final static int 오십원 = 50;
	final static int 십원 = 10;
	final static int 일원 = 1;
	
	public static void main (String args[])  {
		int res, money;
		Scanner scanner = new Scanner(System.in);
		System.out.print("금액을 입력하시오>>");
		money = scanner.nextInt();
		
		res = money/오만원; // 오만원권 개수 계산
		money = money%오만원; // money 갱신
		if(res > 0) {
			System.out.println("오만원권 "+ res + "매"); // 오만원권 매수 표시
		}
		res = money/만원; // 만원권 개수 계산
		money = money%만원; // money 갱신
		if(res > 0) {
			System.out.println("만원권 "+ res + "매"); // 만원권 매수 표시
		}
		res = money/천원; // 천원권 개수 계산
		money = money%천원; // money 갱신
		if(res > 0) {
			System.out.println("천원권 "+ res + "매"); // 천원권 매수 표시
		}
		res = money/오백원; // 오백원 개수 계산
		money = money%오백원; // money 갱신
		if(res > 0) {
			System.out.println("오백원 "+ res + "개"); // 오백원 개수 표시
		} 
		res = money/백원; // 백원 개수 계산
		money = money%백원; // money 갱신		
		if(res > 0) {
			System.out.println("백원 "+ res + "개"); // 백원 개수 표시
		}
		res = money/오십원; // 오십원 개수 계산
		money = money%오십원; // money 갱신	
		if(res > 0) {
			System.out.println("오십원 "+ res + "개"); // 오십원 개수 표시
		}
		res = money/십원; // 십원 개수 계산
		money = money%십원; // money 갱신	
		if(res > 0) {
			System.out.println("십원 "+ res + "개"); // 십원 개수 표시
		}
		res = money/일원; // 일원 개수 계산
		money = money%일원; // money 갱신	
		if(res > 0) {
			System.out.println("일원 "+ res + "개"); // 일원 개수 표시
		}
		
		scanner.close();
	}
}
