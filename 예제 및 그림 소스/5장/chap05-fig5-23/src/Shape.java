public class Shape {
	protected String name;
	public void paint() {
		draw();
	}
	public void draw() {
		System.out.println("Shape");
	}
	public static void main(String [] args) {
		Shape a = new Shape();
		a.paint();
	}
}