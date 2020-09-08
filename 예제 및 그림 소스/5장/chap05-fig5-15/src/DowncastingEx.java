class Person {
	String name;
	String id;
	public Person(String name) {
		this.name = name;
	}
}
class Student extends Person {
	String grade;
	String department;
	public Student(String name) {
		super(name);
	}
}
public class DowncastingEx {
	public static void main(String[] args) {
		Person p = new Student("이재문"); // 업캐스팅
		Student s;
		
		s = (Student)p; // 다운캐스팅
		
		System.out.println(s.name); // 오류 없음
		s.grade = "A"; // 오류 없음
	}
}