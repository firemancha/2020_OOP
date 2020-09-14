
import java.util.Scanner;

public class JavaGrammer2_3 {

	public static void main(String[] args) {
		
	// (실습2-3) 아래의 조건에 맞게 문장들을 완성한다.
		
		// (1번문제) 나이가 20-29살 범위에 있으면 true, 아니면 false를 출력
		Scanner scanner = new Scanner(System.in);		
		System.out.print("나이를 입력하세요(예: 21): ");
		int age = scanner.nextInt(); 		// 정수 입력

		//System.out.println("나이가 20-29살 입니다: " + 20 <= age < 30 );
		System.out.println("(1번답) 나이가 20-29살 입니다: " + "?" );
		System.out.println("\n-----------------------------------------------\n");

		
		// (2번문제) 나이가 20-29살 범위에 있으면 "예", 아니면 "아니오"를 출력하도록
		//          아래의 문장(변수 answer값을 설정하는 문장)을 조건연산자 사용 표현식으로 완성하라.
		System.out.print("나이를 입력하세요(예: 21): ");
		age = scanner.nextInt(); 
        String answer = "?";             // 조건연산자 표현식 사용
		System.out.println("(2번답) 나이가 20-29살 입니다: " + answer );
		System.out.println("\n-----------------------------------------------\n");


		// (3번문제) 냉장고 8개 센서 중에 문이 열려 있는지를감지하는 센서의 값이
		//          flag변수 비트 5에 기록되는데, 열려 있으면 1, 닫혀 있으면
		//          0이 저장된다.
		System.out.print("냉장고 센서 값을 이진수로 입력하세요(예: 00100010): ");
		byte flag = scanner.nextByte(2); 		// 정수 입력
		if((flag & 0b00000000) == 0) 
			System.out.println("(3번답) 냉장고 문이 닫혀 있음");
		else 
			System.out.println("(3번답) 냉장고 문이 열려 있음");	
		System.out.println("\n-----------------------------------------------\n");


		// (4번문제) 나이가 20-29살 범위에 있으면 "예", 아니면 "아니오"를 출력하도록
		//          아래의 문장(변수 answer값을 설정하는 문장)을 if-else문으로 완성하라.
		System.out.print("나이를 입력하세요(예: 21): ");
		age = scanner.nextInt(); 
        answer = "?";             // if-else문 사용
		System.out.println("(4번답) 나이가 20-29살 입니다: " + answer );
		System.out.println("\n-----------------------------------------------\n");


		// (5번문제) 운전면허 필기시험 점수, 면허정지 후 재시험여부,  면허취소 후 재시험여부 로
		//          합격이 결정되도록 규정이 바뀌었다. 
		//          일반적인 경우, 70점 이상이면 "합격"
		//          단, 면허정지 후 재시험인 경우, 80점 이상이 "합격"
		//          단, 면허취소 후 재시험인 경우, 90점 이상이 "합격"
		//          나머지 경우는 "불합격" 이 출력된다.
		//          switch문을 사용해서 다음 문장들을 완성하라.

		System.out.print("점수를 입력하세요(0~100): ");
		int score = scanner.nextInt(); 

		System.out.print("면허상태를 입력하세요(1~3) (1:정상,2:면허정지,3:면허취소): ");
		int status = scanner.nextInt(); 

         switch(score) {	// 이 부분을 완성
         
         	default: 
                     System.out.println("(5번답) ???");
        
         }
		
		scanner.close();
	}

}
