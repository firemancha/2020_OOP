interface MyFunction {
	void print();
}

public class LambdaEx3 {
	public static void main(String[] args) {
		MyFunction f = () -> { // 람다식 작성
			System.out.println("Hello");
		};
		
		f.print(); // 람다식 호출
		
		f = () -> System.out.println("안녕"); // 람다식 작성
		
		f.print(); // 람다식 호출	
	}
}