public class Hello {
	public static void main(String[] args) {
		for(int i=0; i<args.length; i++) { // 명령행 인자의 개수만큼 반복
			String s =  args[i];
			System.out.println(s); // 명령행 인자 출력
		}
	}
}
