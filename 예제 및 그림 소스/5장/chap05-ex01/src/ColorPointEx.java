class Point {
	private int x, y; // 한 점을 구성하는 x, y 좌표	
	public void set(int x, int y) {
		this.x = x; this.y = y;
	}
	public void showPoint() { // 점의 좌표 출력
		System.out.println("(" + x + "," + y + ")");
	}
}

class ColorPoint extends Point { // Point를 상속받은 ColorPoint 선언
	private String color; // 점의 색
	public void setColor(String color) {
		this.color = color;
	}
	public void showColorPoint() { // 컬러 점의 좌표 출력
		System.out.print(color);
		showPoint(); // Point 클래스의 showPoint() 호출 
	}
}

public class ColorPointEx {	
	public static void main(String [] args) {
		Point p = new Point(); // Point 객체 생성
		p.set(1, 2); // Point 클래스의 set() 호출
		p.showPoint();
		
		ColorPoint cp = new ColorPoint(); // ColorPoint 객체 생성
		cp.set(3, 4); // Point 클래스의 set() 호출
		cp.setColor("red"); // ColorPoint 클래스의 setColor() 호출
		cp.showColorPoint(); // 컬러와 좌표 출력
	}
}