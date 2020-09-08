class Shape { // 슈퍼 클래스
	public Shape next; // 그림 5-22의 코드를 위해 필요한 부분
	public Shape() { next = null; } // 그림 5-22의 코드를 위해 필요한 부분
	
	public void draw() {
		System.out.println("Shape");
	}
}

class Line extends Shape {
	@Override
	public void draw() { // 메소드 오버라이딩
		System.out.println("Line");
	}
}

class Rect extends Shape {
	@Override
	public void draw() { // 메소드 오버라이딩
		System.out.println("Rect");
	}
}

class Circle extends Shape {
	@Override
	public void draw() { // 메소드 오버라이딩
		System.out.println("Circle");
	}
}

public class MethodOverridingEx {
	static void paint(Shape p) { // Shape을 상속받은 모든 객체들이 매개변수 p로 넘어올 수 있음
		p.draw(); // p가 가리키는 객체 내에 오버라이딩한 draw() 호출. 동적 바인딩
	}
	
	public static void main(String[] args) {
		Line line = new Line();

		// 다음 호출들은 모두 paint() 메소드 내에서 Shape에 대한 레퍼런스 p로 업캐스팅됨
		paint(line); // Line의 draw() 실행. "Line" 출력		
		paint(new Shape()); // Shape의 draw() 실행. "Shape" 출력	
		paint(new Line()); // Line에 오버라이딩한 draw() 실행, "Line" 출력
		paint(new Rect()); // Rect에 오버라이딩한 draw() 실행, "Rect" 출력
		paint(new Circle()); // Circle에 오버라이딩한 draw() 실행, "Circle" 출력
	}
}