import java.io.File;

public class FileDelete {

	public static void main(String[] args) {
		File dir = new File("c:\\temp\\");
		File [] files = dir.listFiles(); // 디렉토리의 파일 리스트
		
		System.out.println(dir.getPath() + "디렉터리의 .txt 파일을 모두 삭제합니다....");
		int count = 0;
		for(int i=0; i<files.length; i++) {
			if(!files[i].isFile()) // 파일이 아니면 다음으로
				continue;
			
			String name = files[i].getName();
			int index = name.lastIndexOf('.'); // 파일명 문자열에서 제일 마지막에 있는 '.' 의 인덱스
			if(index == -1) // 찾을 수 없음
				continue;
			
			String ext = name.substring(index); // ext = ".txt" 
			if(ext.equals(".txt")) {
				System.out.println(files[i].getPath() + " 삭제");
				files[i].delete();
				count++;
			}
		}
		System.out.println("총 "+ count + "개의 .txt 파일을 삭제하였습니다.");
	}

}
