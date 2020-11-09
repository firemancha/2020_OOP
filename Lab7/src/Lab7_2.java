import java.util.*;

public class Lab7_2 {
	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private Scanner scanner = new Scanner(System.in);
	
	public Lab7_2() { }
	
	public void run() {
		System.out.println("** 포인트 관리 프로그램입니다 **");

		while(true) {
			System.out.print("이름과 포인트 입력>> ");
			String name = scanner.next();
			if(name.equals("그만"))
				break;
			int point = scanner.nextInt();
			
			addOrUpdate(name, point); // 이름으로 포인트 검색.
			                          //신규가 아니면 포인트 점수 누적
			                          // 이름과 누적 포인트 갱신
			
			printAll();  // HashMap에 저장된 모든 고객의 포인트 출력
		}
	}
	
	void addOrUpdate(String name, int point) {
		if(map.containsKey(name))
			map.put(name, point + map.get(name));
		else
			map.put(name, point);
	}
	
	
	void printAll() {
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext())
		{
			String name = it.next();
			int point = map.get(name);
			System.out.printf("(" + name + "," + point + ")");
		}
		System.out.println("");
	}
	
	
	public static void main(String[] args) {
		Lab7_2 man = new Lab7_2();
		man.run();
	}
}

