import java.io.*;
import java.util.*;

public class Lab8_1 {
	private File targetFile = null;
	Vector<String> lineVector = new Vector<String>();
	
	public Lab8_1() { }
	
	private void readFile(String fileName) {
		targetFile = new File(fileName);
		try {
			Scanner fScanner = new Scanner(new FileReader(targetFile));
			while(fScanner.hasNext()) { // 파일을 라인 단위로 모두 읽기
				String line = fScanner.nextLine(); // 한 라인 읽고
				lineVector.add(line); // 한 라인을 벡터에 저장
			}			
			fScanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	private Vector<Integer> searchWord(String word) { // word가 포함된 라인 번호들을 벡터로 리턴
		Vector<Integer> noVector = new Vector<Integer>();

		// lineVector에 저장되어 있는 파일 라인들 중 word를 포함하고 있는 
		//  모든 라인들을 찾아서 해당 라인 번호들을 noVector변수에 추가한다.
		for(int i = 0; i < lineVector.size(); i++)
		{
			if(lineVector.get(i).contains(word))
				noVector.add(i);
		}
		return noVector;
	}

	private void printLines(Vector<Integer> noVector) {

		// noVector에 들어 있는 정수 데이터 각각에 대하여 우선 정수 값을 출력하고
		//  이어서 컬렉션변수 lineVector의 해당 인덱스 위치에 있는 라인 문자열을 출력한다.
		for(int i = 0; i< noVector.size(); i++)
		{
			System.out.println((noVector.get(i) + 1) + ": " + lineVector.get(noVector.get(i)));
		}
	}
	
	public void run() {
		System.out.println("전체 경로명이 아닌 파일 이름만 입력하는 경우, 파일은 프로젝트 폴더에 있어야 합니다.");
		System.out.print("대상 파일명 입력>> ");
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();

		readFile(fileName); // 파일을 라인 단위로 벡터로 읽어들임
		
		while(true) {
			System.out.print("검색할 단어나 문장>> ");
			String line = scanner.nextLine();
			if(line.equals("그만"))
				break; // 프로그램 종료
			Vector<Integer> noVector = searchWord(line); // line의 단어를 포함하는모든 라인 번호 리턴
			printLines(noVector);
		}
		scanner.close();
		System.out.println("프로그램을 종료합니다.");
	}
	
	public static void main(String[] args) {
		Lab8_1 ws = new Lab8_1();
		ws.run();
	}
}
