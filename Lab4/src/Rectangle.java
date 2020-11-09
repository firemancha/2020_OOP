
public class Rectangle {
	private int x, y, width, height;
	
	public Rectangle(int x, int y, int width, int height) {

		// 4개 인수값(사각형의 왼쪽 위 모서리 좌표(x, y), 넓이(width), 높이(height))을 
		//  해당 속성(필드)변수에 저장하는 코드 추가
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;		
	}
	
	public void show() {

      // 사각형의 위치와 크기를 출력하는 print문 추가	
		System.out.printf("(%d,%d)에서 크기가 %dx%d인 사각형\n", this.x, this.y, this.width, this.height);
	}
	
	public int square() { 
		return this.width * this.height; // 면적 구하여 리턴하도록 수정
	}
	
	public boolean contains(Rectangle r) {
		if((this.x < r.x) && (this.y < r.y) && (this.x + this.width > r.x + r.width) && (this.y + this.height > r.y + r.height))
			return true;
		else
			return false; 
          // 자신(this)이 사각형(r)을 포함하면 true, 아니면 false 리턴하도록 if-else문으로 완성 
	}
	
	public static void main(String args[]) {
		Rectangle r = new Rectangle(2, 2, 8, 7);
		Rectangle s = new Rectangle(5, 5, 6, 6);
		Rectangle t = new Rectangle(1, 1, 10, 10);

		r.show();  // 문자열 "(2,2)에서 크기가 8x7인 사각형" 이 출력되도록 생성자와 show() 메소드 완성
		System.out.println("s의 면적은 " + s.square());
		           // 문자열 "s의 면적은 36" 이 출력되도록 메소드 square() 메소드 완성
		if(t.contains(r)) System.out.println("t는 r을 포함합니다."); 
		           // t.contains(r) 는 true가 리턴되고,
		if(t.contains(s)) System.out.println("t는 s를 포함합니다.");
                   // t.contains(s) 는 false가 리턴되도록 contains() 메소드 완성
	}

}
