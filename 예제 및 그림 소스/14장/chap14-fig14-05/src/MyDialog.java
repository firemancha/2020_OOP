import java.awt.*;
import javax.swing.*;

public class MyDialog {

	public static void main(String[] args) {
		JDialog dialog = new JDialog(); // 다이얼로그 생성
		dialog.setTitle("나의 다이얼로그"); // 타이틀 달기
		dialog.add(new JButton("click!")); // 다이얼로그에 버튼 삽입
		dialog.setSize(300,300); // 다이얼로그 크기 설정
		dialog.setVisible(true); // 다이얼로그 화면 출력

	}

}
