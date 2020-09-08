import java.util.Scanner;

class Book {
	String title, author;
	public Book(String title, String author) { // 积己磊
		this.title = title;
		this.author = author;
	}
}

public class BookArray {
	public static void main(String[] args) {
		Book [] book = new Book[2]; // Book 硅凯 急攫
		
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<book.length; i++) { // book.length = 2
			System.out.print("力格>>");
			String title = scanner.nextLine();
			System.out.print("历磊>>");
			String author = scanner.nextLine();
			book[i] = new Book(title, author); // 硅凯 盔家 按眉 积己
		}
		
		for(int i=0; i<book.length; i++)
			System.out.print("(" + book[i].title + ", " + book[i].author + ")");

		scanner.close();
	}
}