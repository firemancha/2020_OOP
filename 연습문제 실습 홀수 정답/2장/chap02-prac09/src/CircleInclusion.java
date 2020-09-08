import java.util.Scanner;

public class CircleInclusion {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("원의 중심과 반지름 입력>>");
		double x = scanner.nextDouble();
		double y = scanner.nextDouble();
		double radius = scanner.nextDouble();
		
		System.out.print("점 입력>>");
		double x2 = scanner.nextDouble();
		double y2 = scanner.nextDouble();
		
		double distance = Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
		if(distance < radius)
			System.out.print("점 (" + x2 + ", " + y2 + ")는 원 안에 있다.");
		else
			System.out.print("점 (" + x2 + "," + y2 + ")는 원 밖에 있다.");

		scanner.close();
	}

}
