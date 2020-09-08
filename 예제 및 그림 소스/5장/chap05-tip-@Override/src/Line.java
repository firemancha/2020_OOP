class Shape {
	public void draw() { }
}
public class Line extends Shape {
	@Override // 다음 오버라이딩이 정확한지 컴파일러에게 확인하도록 지시
	public void drow() { } // 컴파일 오류.
}