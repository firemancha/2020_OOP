@FunctionalInterface
interface MyFunction<T> { // 제네릭 타입 T를 가진 함수형 인터페이스
	void print(T x); // 람다식으로 구현할 추상 메소드
}

public class LambdaEx5 {
	public static void main(String[] args) {
		MyFunction<String> f1 = (x) -> System.out.println(x.toString());
		f1.print("ABC"); // String 객체를 람다식에 넘겨준다.
		
		MyFunction<Integer> f2 = (x) -> System.out.println(x.toString());
		f2.print(Integer.valueOf(100));	// Integer 객체를 람다식에 넘겨준다.
	}
}