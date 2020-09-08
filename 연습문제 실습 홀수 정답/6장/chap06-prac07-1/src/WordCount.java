import java.util.*;

public class WordCount {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print(">>");
			String s = scanner.nextLine();
			if(s.equals("그만")) {
				System.out.println("종료합니다...");	
				break;
			}
			StringTokenizer st = new StringTokenizer(s, " ");	
			System.out.println("어절 개수는 " + st.countTokens());
		}
		
		scanner.close();
	}
}
