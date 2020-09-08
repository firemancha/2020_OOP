import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileSender {
	public static void main(String[] args) {
		FileInputStream fin = null; // BufferedInputStream
		File f = null;
		BufferedOutputStream fout = null;
		Scanner scanner  = new Scanner(System.in);
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9999); // 클라이언트 소켓 생성
			System.out.print("보낼 파일 이름을 입력하세요>>");
			String fileName = scanner.next();
			f = new File(fileName);
			long length = f.length();
			fin = new FileInputStream(f); // 키보드로부터의 입력 스트림
			fout = new BufferedOutputStream(socket.getOutputStream()); // 서버로의 출력 스트림
			
			byte[] fname = f.getName().getBytes(); // 경로를 제외한 파일 이름
			int nameSize = fname.length;
			fout.write(Command.FILE_NAME); // FILE_NAME 명령 전송
			sentInt(fout, nameSize);  // 파일 이름 크기 전송
			fout.write(fname); // 실제 파일 이름 전송
			
			fout.write(Command.FILE_SIZE); // FILE_SIZE 명령 전송
			sentInt(fout, (int)length);
			sentInt(fout, (int)(length >>> 32));
			
			fout.write(Command.SEND_BEGIN); // SEND_BEGIN 명령 전송
			int count = 0;
			byte b[] = new byte[512];
			while (length > 0) {
				int numRead = fin.read(b,0,b.length);
				if (numRead <= 0) { 
					if (length > 0) { // 더 이상 읽을 것이 없는데 아직 파일 크기만큼 못 읽은 경우
						System.out.println("전송 오류가 발생했습니다. 읽은 바이트: " + count + " 남은 바이트:" + length);					
						break;
					} 
				} else {
					fout.write(b, 0, numRead);
					length -= numRead;
					fout.flush();
					count += numRead;
				}
			}
			fout.write(Command.SEND_END); // SEND_END 명령 전송
			fout.flush();
			fin.close();
			socket.close(); // 클라이언트 소켓 닫기
			System.out.println("파일 전송이 완료되었습니다.");
		} catch (IOException e) {
			System.out.println("파일 전송 중 오류가 발생했습니다.");
		}
	}

	private static void sentInt(BufferedOutputStream fout, int value) throws IOException {
		fout.write(value & 0x000000ff);
		fout.write((value & 0x0000ff00)>>8);
		fout.write((value & 0x00ff0000)>>16);
		fout.write((value & 0xff000000)>>24);
	}
}
