public class ThreeSixNine {
	public static void main (String args[])  {
		String str[] = {" 박수 짝", " 박수 짝짝"};
		int res, num, numberOf369 = 0;

		for (int i=1; i<100; i++) {
			num = i;
			for (res = num % 10; num > 0; res = num % 10) {
				if (res == 3 || res == 6 || res == 9) numberOf369++; // 정수에 3,6,9중 하나가 있는 경우  numberOf369 증가
				num = num / 10;
			}
			if (numberOf369 >0) // 수에 3,6,9가 하나 이상 있는 경우
				System.out.println(i + str[numberOf369-1]); 
			numberOf369 = 0;
		}
	}
}
