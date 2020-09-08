interface MyFunction {
	int calc(int x, int y);
}

public final class LambdaEx4 {
	public static void main(String[] args) {
		printMultiply(3, 4, (x,y)->x*y); // 람다식((x,y)->x*y)을 매개변수로 전달
	}
	
	static void printMultiply(int x, int y, MyFunction f) { // f로 (x,y)->x*y 람다식 전달받음
		System.out.println(f.calc(x, y));
	}
}