import java.io.*;
import java.util.Scanner;

public class UpperCharacterUsingScanner {

	public static void main(String[] args) {
		try {
			File f = new File("c:\\windows\\system.ini");
			Scanner scanner = new Scanner(new FileReader(f));
			while(scanner.hasNext()) {
				String line = scanner.nextLine();
				line = line.toUpperCase();
				System.out.println(line);
			}
			scanner.close();
		}
		catch(IOException e) {
			System.out.println("파일 읽기 오류");
		}

	}

}
