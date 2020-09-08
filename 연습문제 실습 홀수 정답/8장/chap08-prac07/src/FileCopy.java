import java.io.*;

public class FileCopy {
	public static void main(String[] args) {
		BufferedInputStream srcStream = null;
		BufferedOutputStream destStream = null;
		File srcFile = new File("a.jpg");
		File destFile = new File("b.jpg");
		try {
			srcStream = new BufferedInputStream(new FileInputStream(srcFile)); // 버퍼 입력 스트림에 연결
			destStream = new BufferedOutputStream(new FileOutputStream(destFile)); // 버퍼 출력 스트림에 연결
			
			long tenPercent = srcFile.length() / 10; // 파일의 10% 크기
			long progress = 0; // 파일 크기 10% 될 때까지 읽은 누적 바이트 수
			
			System.out.println("a.jpg를 b.jpg로 복사합니다. \n10%마다 *를 출력합니다.");
			byte[] buf = new byte[1024]; // 한 번 읽는 단위
			int numRead = 0; // 읽은 바이트 수 
			while (true) {
				numRead = srcStream.read(buf, 0, buf.length);
				if(numRead == -1) { // 파일 끝에 도달함
					if(progress > 0) { // 지난번에 읽었지만 10%에 도달하지 않아 *가 출력되지 않은 경우
						System.out.print("*");
					}
					break; // 파일 끝에 도달함
				}
				if (numRead > 0)
					destStream.write(buf, 0, numRead);
				
				progress += numRead; // 파일 크기 10% 될 때까지 읽은 누적 바이트 수
				if (progress >= tenPercent) { // 10% 만큼 읽었다면
					System.out.print("*");
					progress = 0; // * 출력하고 progress를 0으로 리셋
				}
			}
           	srcStream.close();
           	destStream.close();
		} catch (IOException e) {
			System.out.println("입출력 오류가 발생했습니다.");
		}
	}
}
