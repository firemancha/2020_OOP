import java.util.Calendar;

public class GoodMorning {
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance(); // Calendar 객체 생성
		
		// now는 현재 시간 값을 가지고 있음
		
		int hourOfDay = now.get(Calendar.HOUR_OF_DAY);
		int minute = now.get(Calendar.MINUTE);		
		System.out.println("현재 시간은 " + hourOfDay + "시 " + minute + "분입니다.");
		
		if(hourOfDay > 4 && hourOfDay < 12) 
			System.out.println("Good Morning");
		else if(hourOfDay >= 12 && hourOfDay < 18) 
			System.out.println("Good Afternoon");
		else if(hourOfDay >= 18 && hourOfDay < 22) 
			System.out.println("Good Evening");
		else
			System.out.println("Good Night");			
	}
}
