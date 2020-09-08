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

public class UsingOverride {
	public static void main(String [] args) {
		Shape start, last, obj;
		
		// 링크드 리스트로 도형 생성하여 연결
		start = new Line(); // Line 객체 연결
		last = start;
		obj = new Rect();
		last.next = obj; // Rect 객체 연결
		last = obj;
		obj = new Line(); // Line 객체 연결
		last.next = obj;
		last = obj;
		obj = new Circle(); // Circle 객체 연결
		last.next = obj;
		
		// 모든 도형 출력
		Shape p = start;
		while(p != null) {
			p.draw();
			p = p.next;
		}
	}
}