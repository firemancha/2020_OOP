import javax.swing.*;
import java.awt.*;

public class CheckBoxEx extends JFrame {
	public CheckBoxEx() {
		setTitle("체크박스 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 이미지 체크박스에 사용할 2개의 이미지 객체 생성
		ImageIcon cherryIcon = new ImageIcon("images/cherry.jpg"); // 해제 상태 이미지
		ImageIcon selectedCherryIcon = new ImageIcon(
				"images/selectedCherry.jpg"); // 선택 상태 이미지
		
		// 3개의 체크박스 생성
		JCheckBox apple = new JCheckBox("사과");
		JCheckBox pear = new JCheckBox("배", true);
		JCheckBox cherry = new JCheckBox("체리", cherryIcon); 

		cherry.setBorderPainted(true); // 체크박스 외곽선이 보이도록 설정
		cherry.setSelectedIcon(selectedCherryIcon); // 선택 상태 이미지 등록
		
		// 컨텐트팬에 3개의 체크박스 부착
		c.add(apple);
		c.add(pear);
		c.add(cherry);

		setSize(250,150);
		setVisible(true);
	}
	
	public static void main(String [] args) {
		new CheckBoxEx();
	}
}