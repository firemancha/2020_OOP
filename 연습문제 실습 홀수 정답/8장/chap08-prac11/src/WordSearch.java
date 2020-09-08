import java.io.*;
import java.util.*;

public class WordSearch {
	private Vector<String> wordVector = new Vector<String>();
	
	public WordSearch() { }
	
	private boolean readFile() { // words.txt 파일 읽기
		try {
			Scanner fScanner = new Scanner(new FileInputStream("words.txt"));
			while(fScanner.hasNext()) 
				wordVector.add(fScanner.nextLine()); // 한 라인에 하나의 단어
			fScanner.close();
		}catch(FileNotFoundException e) { 
			System.out.println("프로젝트 폴더 밑의 words.txt 파일이 없습니다");
			return false;
		} 
		System.out.println("프로젝트 폴더 밑의 words.txt 파일을 읽었습니다...");
		return true;
	}
	
	private void processQuery() { // 단어 검색
		Scanner scanner = new Scanner(System.in);
		while(true) {
			boolean found = false;
			System.out.print("단어>>");
			String searchWord = scanner.nextLine(); // 검색할 단어 입력
			if(searchWord.equals("그만")) { 
				break; // 검색 종료
			}
			
			// 벡터에서 검색
			for(int i=0; i<wordVector.size(); i++) {
				String word = wordVector.get(i); // 벡터 내의 문자열
				if(word.length() < searchWord.length()) // 벡터의 문자열이 검색 문자열보다 짧은 경우 
					continue;
				
				// 벡터문자열의 앞부분을 검색 문자열 크기만큼 잘라내기
				String frontPart = word.substring(0, searchWord.length());
				
				if(searchWord.equals(frontPart)) { // 검색 문자열과 잘라낸 앞 부분 비교
					System.out.println(word); // 벡터에서 발견한 단어 출력
					found = true;
				}
			}
			if(!found) // 한 단어도 발견못한 상태
				System.out.println("발견할 수 없음");
		}
		scanner.close();

	}
	
	public void run() {
		boolean res = readFile(); // res가 false이면  단어 파일 읽기 실패
		if(res == true)
			processQuery();
		System.out.println("종료합니다...");
	}
	
	public static void main(String[] args) {
		WordSearch ws = new WordSearch();
		ws.run();
	}
}