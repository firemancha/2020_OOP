import java.io.*;

public class FileReadHangulFail {
	public static void main(String[] args) {
		InputStreamReader in = null;
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("c:\\Temp\\hangul.txt"); // 파일로부터 바이트 입력 스트림 생성
			in = new InputStreamReader(fin, "US-ASCII");

			int c;

			System.out.println("인코딩 문자 집합은 " + in.getEncoding()); // 문자집합 출력
			while ((c = in.read()) != -1) { // 문자 단위로 읽는다.
				System.out.print((char)c);
			}
			in.close();
			fin.close();
		} catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}