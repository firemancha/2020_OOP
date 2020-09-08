import java.io.File;
import java.util.Scanner;

public class FileExplorer {
	private File currentDir = null;
	private File subFiles [] = null;
	
	public FileExplorer(String dirName) {
		currentDir = new File(dirName);
	}

	private void showSubDirtories() {
		System.out.println("\t[" + currentDir.getPath() + "]");
		subFiles = currentDir.listFiles();
		for(File f : subFiles) {
			System.out.print(((f.isFile())?"file":"dir"));
			System.out.printf("%-15s", "\t\t" + f.length() + "바이트");
			System.out.println("\t\t" + f.getName());
		}		
	}
	
	private boolean contains(String filename) {
		for(File f : subFiles) {
			if(f.getName().equalsIgnoreCase(filename))
				return true;
		}
		return false;
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("***** 파일 탐색기입니다. *****");
		showSubDirtories();
		
		while(true) {
			System.out.print(">>");
			String command = scanner.nextLine().trim(); // 한 라인을 읽고 앞뒤에 입력한 빈칸 지우기
			if(command.equals("그만"))
				break; // 프로그램 종료
			if(command.equals("..")) {
				String s = currentDir.getParent();
				if(s == null) // s가 null이면, currentDir가 현재 최상위 디렉터리라서 부모 디렉터리가 없음
					continue;
				else { 					
					currentDir = new File(currentDir.getParent());
					showSubDirtories();
				}

			}
			else { // command는 서브디렉토리이름
				if(contains(command)) { // 현재 디렉터리의 파일이나 서브디렉토리라면
					if(new File(currentDir, command).isDirectory()) { // 디렉터리라면
						currentDir = new File(currentDir, command);
						showSubDirtories();
					}
					else { // 파일인 경우
						System.out.println("\t디렉터리가 아닙니다.!");
					}
				}
			}
		}
		
		scanner.close();
	}
	
	public static void main(String[] args) {
		FileExplorer fe = new FileExplorer("c:\\");
		fe.run();
	}

}
