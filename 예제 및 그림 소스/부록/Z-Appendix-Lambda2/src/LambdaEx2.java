interface MyFunction { // 함수형 인터페이스
	int calc(int x); // 람다식으로 구현할 추상 메소드 
}

public class LambdaEx2 {
	public static void main(String[] args) {
		MyFunction square = x -> x * x;
		System.out.println(square.calc(2));
	}
}
