import java.util.Scanner;

public class StringRotate {

	public static void main(String[] args) {
		System.out.println("문자열을 입력하세요. 빈 칸이나 있어도 되고 영어 한글 모두 됩니다.");
		Scanner scanner = new Scanner(System.in);
		String text = scanner.nextLine();

		// (실습6-2) 이 위치에 실습 구현내용을 채운다.
		StringBuffer sb = new StringBuffer(text);
		for(int i = 0; i < text.length(); i++)
		{
			sb.append(Character.toString(text.charAt(i)));
			sb.delete(0, 1);
			System.out.println(sb);
		}
	}
}