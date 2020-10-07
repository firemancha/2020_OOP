import java.util.Scanner;

class Phone {
	private String name;
	private String tel;
	public Phone(String name, String tel) {
		this.name = name; this.tel = tel;
	}
	public String getName() { return name; }
	public String getTel() { return tel; }
}

public class PhoneBook {
	private Scanner scanner;
	private Phone [] pArray;
	public PhoneBook() { 
		scanner = new Scanner(System.in);
	}
	
	void read() {
		System.out.print("인원수>>");
		int n = scanner.nextInt();
		
		pArray = new Phone[n]; 
		      // 위 문장을 n명을 저장할 수 있는 크기의  배열(pArray) 생성문으로 수정
		
		for(int i=0; i<pArray.length; i++) {
			System.out.print("이름과 전화번호(이름과 번호는 빈 칸없이 입력)>>");			
			String name = scanner.next();
			String tel = scanner.next();
			
			// 이 위치에 i-번째 사람의 이름과 전화번호를 저장하는 Phone객체 생성하고,
			//   pArray[i]에 저장하는 문장 작성
			Phone temp = new Phone(name, tel);
			pArray[i] = temp;
		}
		
		System.out.println("저장되었습니다...");		
	}
	
	String search(String name) {
		
		// 이 위치에 for-if문을 사용하여 Phone객체배열변수(pArray) 안에
		// 이름(name)이 저장되어 있으면 해당 전화번호를 리턴(getTel()사용)하는
		// 문장을 구현
		for(int i = 0; i < pArray.length; i++)
		{
			//String temp = pArray[i].getName();
			if(name.equals(pArray[i].getName()))
			{
				return pArray[i].getTel();
			}
		}
		return null;
	}
	
	void run() {
		read();
		while(true) {
			System.out.print("검색할 이름>>");
			String name = scanner.next();
			if(name.equals("그만"))
				break;
			String tel = search(name);
			if(tel == null)
				System.out.println(name + " 이 없습니다.");
			else
				System.out.println(name + "의 번호는 " + tel + " 입니다.");				
		}	
		
		scanner.close();
	}
	
	public static void main(String[] args) {
		PhoneBook pb = new PhoneBook();
		pb.run();
	}
}
