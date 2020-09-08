class Point {
	private int x, y;
	public Point(int x, int y) { this.x = x; this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	protected void move(int x, int y) { this.x = x; this.y = y; }
}

public class Point3D extends Point {
	private int z;
	
	public Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}
	
	public int getZ() { return z; }
	public void moveUp() { z++; }
	public void moveDown() { z--; }
	public void move(int x, int y, int z) {
		move(x, y);
		this.z = z;
	}
	
	public String toString() {
		return "(" + getX() + "," + getY() + "," + getZ() + ")의 점";
	}
	
	public static void main(String[] args) {
		Point3D p = new Point3D(1,2,3); // 1, 2, 3은 각각 x, y, z축의 값.
		System.out.println(p.toString() + "입니다.");

		p.moveUp(); // z 축으로 위쪽 이동 
		System.out.println(p.toString() + "입니다.");

		p.moveDown(); // z 축으로 아래쪽 이동
		p.move(10, 10); // x, y 축으로 이동
		System.out.println(p.toString() + "입니다.");

		p.move(100, 200, 300); // x, y, z 축으로 이동
		System.out.println(p.toString() + "입니다.");		
	}
}
