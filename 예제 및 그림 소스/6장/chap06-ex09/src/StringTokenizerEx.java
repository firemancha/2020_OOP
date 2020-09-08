import java.util.StringTokenizer;
public class StringTokenizerEx {
	public static void main(String[] args) {
		StringTokenizer st = new StringTokenizer("홍길동/장화/홍련/콩쥐/팥쥐", "/"); // 구분자로 '/' 사용
		while (st.hasMoreTokens()) // 토큰이 있는 동안
			System.out.println(st.nextToken());
	}
}