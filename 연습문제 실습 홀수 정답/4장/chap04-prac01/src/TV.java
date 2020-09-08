
public class TV {
	private String manufacturer;
	private int year;
	private int size;
		
	public TV(String manufacturer, int year, int size) {
		this.manufacturer = manufacturer;
		this.year = year;		
		this.size = size;
	}
	
	public void show() {
		System.out.print(this.manufacturer + "에서 만든 ");
		System.out.print(this.year + "년형 ");
		System.out.println(this.size + "인치 TV");
	}
	
	public static void main(String [] args) {
		TV myTV = new TV("LG", 2017, 32); // LG에서 만든 2017년 65인치
		myTV.show();
	}
}

