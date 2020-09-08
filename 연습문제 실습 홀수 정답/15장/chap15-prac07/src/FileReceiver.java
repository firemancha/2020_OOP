import java.io.*;
import java.net.*;

public class FileReceiver {
	public static void main(String[] args) {
		BufferedInputStream fin = null;
		BufferedOutputStream fout = null;
		ServerSocket listener = null;
		Socket socket = null;
		try {
			listener = new ServerSocket(9999); // 서버 소켓 생성
			socket = listener.accept(); // 클라이언트로부터 연결 요청 대기
			System.out.println("연결됨");
			fin = new BufferedInputStream(socket.getInputStream()); // 클라이언트로부터의 입력 스트림
			int cmd;
			String fileName = null;
			long length=0;
			
			cmd = fin.read();
			if (cmd == Command.FILE_NAME) { // FILE_NAME 명령 수신
				int nameSize = receiveInt(fin); // 파일 이름 크기
				System.out.println("전송받은 파일 이름 길이 :" + nameSize);
				byte fname[] = new byte[nameSize];
				fin.read(fname); 
				fileName = new String(fname);
				System.out.println("전송받은 파일 이름:" + fileName);
				System.out.println("저장할 파일 이름:" + "copy_" + fileName);
				fout = new BufferedOutputStream(new FileOutputStream("copy_" + fileName));
			} else {
				System.out.println("명령 수신 오류" + cmd);
				socket.close(); // 클라이언트와 통신용 소켓 닫기
				listener.close(); // 서버 소켓 닫기
				return;
			}
			cmd = fin.read();
			if (cmd == Command.FILE_SIZE) { // FILE_SIZE 명령 수신
				int lenghLow = receiveInt(fin); // 파일 크기의 하위 4바이트
				int lenghHigh = receiveInt(fin); // 파일 크기의 상위 4바이트
				length = lenghHigh;
				length <<= 32; // 상위 바이트로 만들기 위해 32비트 쉬프트
				length += lenghLow; // 하위 4 바이트 더하기
				System.out.println("전송받은 파일 크기:" + length);
			} else {
				System.out.println("명령 수신 오류" + cmd);
				if (fout != null)
					fout.close();
				socket.close(); // 클라이언트와 통신용 소켓 닫기
				listener.close(); // 서버 소켓 닫기
				return;
			}
			cmd = fin.read();
			if (cmd == Command.SEND_BEGIN) {
				int numberToRead;
				while (length >0) {
					byte b[] = new byte[2048];
					if (length < b.length) 
						numberToRead = (int)length;
					else
						numberToRead = b.length;
					int numRead = fin.read(b, 0, numberToRead);
					if (numRead <= 0) { 
						if (length > 0) { // 더 이상 읽을 것이 없는데 아직 파일 크기만큼 못 읽은 경우
							System.out.println("전송 오류가 발생했습니다. 읽은 바이트: " + numRead + " 남은 바이트:" + length);
							break;
						}
					} else {
						System.out.print("."); // 진행 상태 표시 
						fout.write(b, 0, numRead);
						length -= numRead;
					}
				}
				cmd = fin.read();
				if (cmd == Command.SEND_END) { // SEND_END 명령
					System.out.println("\n파일 수신 성공. 현재 퐅더에 저장되었습니다.");
				} else {
					System.out.println("\n명령 수신 오류" + cmd);
				}
				if (fout != null)
					fout.close();
				socket.close(); // 클라이언트와 통신용 소켓 닫기
				listener.close(); // 서버 소켓 닫기
			}
		} catch (IOException e) {
			System.out.println("파일 수신 중 오류가 발생했습니다.");
		}
	}

	private static int receiveInt(BufferedInputStream fin) throws IOException {
		int value;
		value = fin.read();
		value |= fin.read() << 8;
		value |= fin.read() << 16;
		value |= fin.read() << 24;
		return value;
	}

}
