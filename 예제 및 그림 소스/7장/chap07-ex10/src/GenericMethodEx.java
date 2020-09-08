class GStack<T> { // 제네릭 스택 선언. 제네릭 타입 T
	int tos; 
	Object [] stck; // 스택에 요소를 저장할 공간 배열
	public GStack() {
		tos = 0; 
		stck = new Object [10];
	}
	public void push(T item) {
		if(tos == 10) // 스택이 꽉 차서 더 이상 요소를 삽입할 수 없음
			return;  
		stck[tos] = item;
		tos++;
	}
	@SuppressWarnings("unchecked")
	public T pop() {
		if(tos == 0) // 스택이 비어 있어 꺼낼 요소가 없음
			return null;
		tos--; 
		return (T)stck[tos]; // 타입 매개 변수 타입으로 캐스팅 
	}
}
public class GenericMethodEx {
	public static <T> GStack<T> reverse(GStack<T> a) {// T가 타입 매개 변수인 제네릭 메소드
		GStack<T> s = new GStack<T>(); // 스택 a를 반대로 저장할 목적 GStack 생성
		while (true) {
			T tmp;
			tmp = a.pop(); // 원래 스택에서 요소 하나를 꺼냄
			if (tmp == null) // 스택이 비었음
				break; // 거꾸로 만드는 작업 종료
			else
				s.push(tmp); // 새 스택에 요소를 삽입
		}
		return s; // 새 스택을 리턴
	}

	public static void main(String[] args) {
		GStack<Double> gs = new GStack<Double>(); // Double 타입의 GStack 생성

		for (int i=0; i<5; i++) { // 5개의 요소를 스택에 push
			gs.push(new Double(i));
		}
		gs = reverse(gs);	
		for (int i=0; i<5; i++) {
			System.out.println(gs.pop());
		}
	}
}