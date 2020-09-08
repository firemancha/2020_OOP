import java.util.Scanner;

public class MutipleOfThree {
	public static void main (String[] args) {
		int intArray[] = new int[10];
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("양의 정수 10개를 입력하시오>>");
		for (int i=0; i<intArray.length; i++) 
			intArray[i] = scanner.nextInt();
		
		System.out.print("3의 배수는 ");
		for (int i=0; i<intArray.length; i++)
			if (intArray[i] % 3 == 0) // 3으로 나누어 나머지가 0이면 3의 배수
				System.out.print(intArray[i] + " ");
		
		scanner.close();
	}
}
