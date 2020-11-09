import java.util.Scanner;



abstract class Converter {
	abstract protected double convert(double src, int dir); // 추상 메소드
	abstract protected String unit1String(); // 추상 메소드
	abstract protected String unit2String(); // 추상 메소드
	protected double ratio; // 비율
	
	public void run() {
		String src, dest;
		Scanner scanner = new Scanner(System.in);
		System.out.print("번호(1:" + unit1String() + "->" + unit2String() + ", 2:" + unit2String() + "->" + unit1String() + ")를 입력하세요>> ");
		int dir = scanner.nextInt();
		if(dir==1) { src=unit1String(); dest=unit2String(); }
		else { src=unit2String(); dest=unit1String(); }
		System.out.println(src + "을 " + dest + "로 바꿉니다.");
		System.out.print(src + "을 입력하세요>> ");
		double val = scanner.nextDouble();
		double res = convert(val, dir);
		System.out.println("변환 결과: " + res + dest + "입니다");
		scanner.close();
	}
}

class GvsWon extends Converter{
	public GvsWon(double ratio)
	{
		this.ratio = ratio;
	}
	
	@Override
	protected double convert(double src, int dir)
	{
		return (dir==1) ? src*ratio : src/ratio;
	}
	
	@Override
	protected String unit1String() {return "g";}
	
	@Override
	protected String unit2String() {return "원";}
}
 
class KmVsMile extends Converter {
	public KmVsMile(double ratio) {
		this.ratio = ratio;
	}
	
	@Override
	protected double convert(double src, int dir) {
		return (dir==1) ? src/ratio : src*ratio;
	}

	@Override
	protected String unit1String() { return "Km"; }

	@Override
	protected String unit2String() { return "mile"; }
	
	public static void main(String args[]) {
		/*KmVsMile kmVsMile = new KmVsMile(1.6); // 1마일은 1.6Km
		kmVsMile.run();*/

		GvsWon gvsWon = new  GvsWon(70000);
		gvsWon.run();
	}
}