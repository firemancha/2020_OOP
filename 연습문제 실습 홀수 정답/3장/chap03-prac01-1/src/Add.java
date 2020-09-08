/* 
  (1)의 정답은
  0부터 시작하여 99까지의 짝수들의 합을 구하는 코드.
  실행 결과 2450 출력
*/ 
public class Add {

	public static void main(String[] args) {
		int sum=0, i=0;
		while (i < 100) {
			sum = sum + i;
			i += 2;
		}
		System.out.println(sum);
	}

}
