class Circle {
	int radius;
	public Circle(int radius) {
		this.radius = radius;
	}
	public double getArea() {
		return 3.14*radius*radius;
	}
}

public class ReferencePassing {

	public static void main(String[] args) {
		Circle pizza = new Circle(10);

		increase(pizza);
		
		System.out.println(pizza.radius);
	}

	static void increase(Circle m) {
		m.radius++;
	}
}
