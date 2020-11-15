interface Shape {
	final double PI = 3.14;
	void draw(); // ������ �׸��� �߻� �޼ҵ�
	double getArea(); // ������ ������ �����ϴ� �߻� �޼ҵ�
	default public void redraw() { // ����Ʈ �޼ҵ�
		System.out.print("--- �ٽ� �׸��ϴ�. ");
		draw();	
	}
}

class Circle implements Shape {
	private int radius; // ������
	
	public Circle(int radius) { 
		this.radius = radius;
	}
	
	@Override
	public void draw() {
		System.out.println("�������� " + radius + "�� ���Դϴ�.");
	}
	
	@Override
	public double getArea() { 
		return PI*radius*radius;
	}
}

class Oval implements Shape {
	private int width;
	private int height;
	
	public Oval(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void draw()
	{
		System.out.println(width + "x" + height + "�� �����ϴ� Ÿ���Դϴ�.");
	}
	
	@Override
	public double getArea()
	{
		return PI*width*height;
	}
}

class Rect implements Shape {
	private int width;
	private int height;
	
	public Rect(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void draw()
	{
		System.out.println(width + "x" + height + "ũ���� �簢�� �Դϴ�.");
	}
	
	@Override
	public double getArea()
	{
		return width*height;
	}
}

public class Shapes {
	static public void main(String [] args) {
		Shape [] list = new Shape[3]; // Shape�� ��ӹ��� Ŭ���� ��ü�� ���۷��� �迭
		list[0] = new Circle(10); // �������� 10�� �� ��ü
		/*
		list[0].redraw();
		System.out.println("������ " + list[0].getArea());
		*/
		 
	    ///* (�׽�Ʈ��)
		   list[1] = new Oval(20, 30); // 20x30 �簢���� �����ϴ� Ÿ��
		   list[2] = new Rect(10, 40); // 10x40 ũ���� �簢��

		   for(int i=0; i<list.length; i++) list[i].redraw();
		   for(int i=0; i<list.length; i++) System.out.println("������ " + list[i].getArea());
		//*/
	}
}