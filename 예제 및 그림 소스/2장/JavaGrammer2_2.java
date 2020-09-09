
import java.util.Scanner;

public class JavaGrammer2_2 {

	public static void main(String[] args) {
		
	// (실습2-2) 아래의 조건에 맞게 문장들을 완성한다.
		
		// (1번문제) 콘솔창에 다음 println()문을 수정하여 오른쪽 문자열 출력:  안녕은 'Hello\Hi'라고 해!
		System.out.println("(1번답) ?");
		System.out.println("\n-----------------------------------------------\n");

		
		// (2번문제) Scanner 사용법
		System.out.println("아래 2번째 줄의 프롬프트(입력 문자열)에서 이름, 학부, 학번, 나이, 학년을 빈칸으로 분리하여 입력하세요.");
		System.out.println("  (예) 홍길동 소프트웨어학부 20201234 20 1");
		System.out.println("(입력 문자열) ? ");
		Scanner scanner = new Scanner(System.in);
		
		String name = ""; // 이름 읽기
		System.out.print("(2번답) 이름은 " + name + ", ");
		
		String department = ""; // 학부 읽기
		System.out.print("학부는 " + department + ", ");
		
		long id = 0; // 학번 읽기
		System.out.print("학번은 " + id + ", ");

		int age = 0; // 나이 읽기
		System.out.print("나이는 " + age + "살, ");
		
		boolean freshman = false; // 신입생 여부 (true 혹은 false)
		System.out.println("신입생 여부는 " + freshman + "입니다.");
		System.out.println("\n-----------------------------------------------\n");
				

		// (3번문제) 연산자(/, %) 사용법		
		System.out.print("시간(단위:분)을 입력하세요(예: 11000): ");
		int time = scanner.nextInt(); 		// 정수 입력
		int minute = 0; 	// 60으로 나눈 나머지는 분
		int hour = 0; 		// 60으로 나눈 몫을 다시 24로 나눈 나머지는 시간
		int day = 0; 		// 60으로 나눈 몫을 다시 24로 나눈 몫은 일
		
		// (출력예) 11000분은 7일 15시간 20분입니다.
		System.out.print("(3번답) " + time + "분은 ");
		System.out.print(day + "일, ");
		System.out.print(hour + "시간, ");
		System.out.println(minute + "분입니다.");
		System.out.println("\n-----------------------------------------------\n");

		scanner.close();

		
		// (4번문제) 연산자(/, %) 사용법		
		// 대입 연산자(*=, %=)를 사용하도록 아래 3개 문장의 표현식을 변경
		int a=3, b=3, c=3;
		a = a * c; 		// a = a*c = 9
		b = b * 18 / a; // b = b*18/a = 6
		c = c % 2; 		// c = c%2 = 1
		System.out.println("(4-1번답) a=" + a + ", b=" + b + ", c=" + c);
		System.out.println("\n-----------------------------------------------\n");

		int d=7;
		// 증감 연산자를 사용하여, 실행하면 4개 문장 옆의 코멘트에 있는 값을 가지도록  표현식을 변경
		a = d; 		// a=7, d=6
		System.out.println("(4-2번답) a=" + a + ", d=" + d);
		a = d; 	    // a=7, d=7
		System.out.println("a=" + a + ", d=" + d);
		a = d; 	    // a=6, d=6
		System.out.println("a=" + a + ", d=" + d);
		a = d; 	    // a=6, d=7
		System.out.println("a=" + a + ", d=" + d);

	}

}
