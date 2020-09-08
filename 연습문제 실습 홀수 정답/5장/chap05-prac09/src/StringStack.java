public class StringStack implements Stack {
	private String[] element; // 스택의 저장 메모리
	private int tos; // index, top of stack
	
	public StringStack(int capacity) {
		element = new String[capacity];
		tos = -1;
	}
	
	// Stack 인터페이스에 선언된 다음 3개의 메소드 오버라이딩 구현
	@Override
	public int length() { // 현재 스택에 저장된 개수 리턴
		return tos+1;
	}
	
	@Override
	public int capacity() { // 현재 스택에 저장된 개수 리턴
		return element.length;
	}
	
	@Override
	public String pop() {
		if(tos == -1) // 스택이 비었음
			return null;
		String s = element[tos]; // 톱에 있는 값
		tos--; // 스택 포인터 감소
		return s;
	}

	@Override
	public boolean push(String str) {
		if(tos == element.length-1)
			return false; // 스택이 다 찼음
		else {
			tos++;
			element[tos] = str; // 요소 스택에 저장 후 스택 포인터 증가
			return true;
		}
	}
}