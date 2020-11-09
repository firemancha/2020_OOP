import java.util.Scanner;

public class Practice3_1 {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("소문자 알파벳 하나를 입력하시오>>");
		String s = scanner.next();
		if(s.length() != 1) {
			System.out.print("알파벳 하나만 입력해야 합니다!");
			scanner.close();
			return;
		}
		
		char c = s.charAt(0);
		System.out.println("입력문자: " + c);
		if (c < 'a' || c > 'z') {
			System.out.println("소문자 알파벳이 아닙니다.");
			scanner.close();
			return;
		}
		
        // (실습3-1) 이 위치에 중첩-for문으로 작성
		
		int max = (c - 'a') + 1;
		
		for(int i = 0; i < max; i++)
		{
			char temp = 'a';
			
			for(int j = 0; j < max - i; j++)
			{
				System.out.print((char)(temp + j) + " ");
			}
			
			System.out.println("");
		}
		
		scanner.close();
	}
}
