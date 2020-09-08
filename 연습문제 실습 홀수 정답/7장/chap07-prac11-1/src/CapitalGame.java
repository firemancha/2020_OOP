import java.util.Scanner;
import java.util.Vector;

public class CapitalGame {
	private Vector<Nation> store = new Vector<Nation>();
	private Scanner scanner = new Scanner(System.in);
	
	public CapitalGame() {
		// store에 9 개의 아이템을 입력하여 초기화
		store.add(new Nation("멕시코", "멕시코시티"));
		store.add(new Nation("스페인", "리스본"));
		store.add(new Nation("프랑스", "파리"));
		store.add(new Nation("영국", "런던"));
		store.add(new Nation("그리스", "아테네"));
		store.add(new Nation("독일", "베를린"));
		store.add(new Nation("일본", "동경"));
		store.add(new Nation("중국", "베이찡"));
		store.add(new Nation("러시아", "모스크바"));
	}
	
	private void error(String msg) {
		System.out.println(msg);
	}
	
	public void run() {
		System.out.println("**** 수도 맞추기 게임을 시작합니다. ****");
		while(true) {
			System.out.print("입력:1, 퀴즈:2, 종료:3>> ");
			int menu = scanner.nextInt();
			switch(menu) {
				case 1: input(); break;
				case 2: quiz(); break;
				case 3: finish(); return;
				default: 
					error("잘못된 입력입니다.");
			}
		}
		
	}

	private boolean contains(String country) {
		for(int i=0; i<store.size(); i++) {
			if(store.get(i).getCountry().equals(country)) { // 사용자가 입력한 나라가 이미 있다면
				return true;
			}
		}
		
		return false;

	}
	private void input() {
		int n = store.size();
		System.out.println("현재 " + n + "개 나라와 수도가 입력되어 있습니다.");
		n++;
		while(true) {
			System.out.print("나라와 수도 입력" + n + ">> ");
			String country = scanner.next(); // 사용자 입력 나라
			if(country.equals("그만")) {
				break;
			}
			String capital = scanner.next(); // 사용자 입력, 수도

			if(contains(country)) { // 사용자가 입력한 나라가 이미 있다면
				System.out.println(country + "는 이미 있습니다!");
				continue;
			}
				
			store.add(new Nation(country, capital));
			n++;
		}
	}

	private void quiz() {
		// 모든 키(나라)를 알아낸다.
		while(true) {
			// 나라 중에서 하나를 선택한다.
			int index = (int)(Math.random()*store.size()); // 랜덤한 위치 결정
			
			// 문제(나라)와 정답(수도)을 결정한다.
			Nation nation = store.get(index);
			String question = nation.getCountry();
			String answer = nation.getCapital();
			
			// 문제를 출력한다.
			System.out.print(question + "의 수도는? ");
			
			String capitalFromUser = scanner.next(); // 사용자의 입력
			if(capitalFromUser.equals("그만")) {
				break;
			}
			if(capitalFromUser.equals(answer))
				System.out.println("정답!!");
			else
				System.out.println("아닙니다!!");				
		}	
		
	}

	private void finish() {
		System.out.println("게임을 종료합니다.");	
	}

	public static void main(String[] args) {
		CapitalGame game = new CapitalGame();
		game.run();

	}

}
