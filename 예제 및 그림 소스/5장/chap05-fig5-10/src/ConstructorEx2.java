class A {
	public A(int x) {
		System.out.println("持失切A");
	}
}

class B extends A {
	public B() {
		System.out.println("持失切B");
	}
}

public class ConstructorEx2 {
	public static void main(String[] args) {
		B b;
		b = new B();
	}
}