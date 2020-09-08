public class ThreadMainEx {
	public static void main(String [] args) {
		long id = Thread.currentThread().getId(); // 스레드 ID 얻기
		String name = Thread.currentThread().getName(); // 스레드 이름 얻기
		int priority = Thread.currentThread().getPriority(); // 스레드 우선순위 값 얻기
		Thread.State s = Thread.currentThread().getState(); // 스레드 상태 값 얻기
		
		System.out.println("현재 스레드 이름 = " + name);		
		System.out.println("현재 스레드 ID = " + id);
		System.out.println("현재 스레드 우선순위 값 = " + priority);	
		System.out.println("현재 스레드 상태 = " + s);
	}
}