import java.io.*;
import java.sql.*;

public class AddRecord {
	public static void main (String[] args) {
		Connection conn;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root","1234"); // JDBC 연결
			System.out.println("DB 연결 완료");
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			int id = 0;
			stmt.executeUpdate("insert into book (id, title, publisher, author) values("+ id++ +",'Harry Potter','Bloomsbury','J. K. Rowling');"); // 레코드 추가
			stmt.executeUpdate("insert into book (id, title, publisher, author) values("+ id++ +",'The Lord of the Rings','Allen & Unwin','J. R. R. Tolkein');"); // 레코드 추가
			stmt.executeUpdate("insert into book (id, title, publisher, author) values("+ id++ +",'Pride and Prejudice','T. Egerton Kingdom','Jane Austen');"); // 레코드 추가
			printTable(stmt);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
	}

	// 레코드의 각 열의 값 화면에 출력
	private static void printTable(Statement stmt) {
		ResultSet srs;
		System.out.printf("%4s|%-30s|%-30s|%-10s\n", "id","title", "publisher", "author");
		try {
			srs = stmt.executeQuery("select * from book");
			while (srs.next()) {
				System.out.printf("%4s|%-30s|%-30s|%-10s\n", new String(srs.getString("id")), srs.getString("title"), srs.getString("publisher"), srs.getString("author"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
	}
}
