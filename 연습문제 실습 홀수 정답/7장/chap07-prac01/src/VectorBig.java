import java.util.*;

public class VectorBig {
	
	public static void printBig(Vector<Integer> v) {  // 벡터 v의 정수 중 가장 큰 수 출력
		int big = v.get(0); // 맨 처음에 있는 수를 제일 큰 수로 초기화
		for(int i=1; i<v.size(); i++) {
			if(big < v.get(i))  // 더 큰 수 발견
				big = v.get(i); // big 변수 교체
		}
		
		System.out.println("가장 큰 수는 " + big);		
	}
	
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("정수(-1이 입력될 때까지)>> ");
		while(true) {
			int n = scanner.nextInt();
			if(n == -1) // 입력된 수가 -1이면
				break;
			v.add(n);
		}
		
		if(v.size() == 0) {
			System.out.print("수가 하나도 없음");
			scanner.close();
			return;
		}
		
		printBig(v); // 벡터 v의 정수 중 가장 큰 수 출력
		scanner.close();
	}

}
