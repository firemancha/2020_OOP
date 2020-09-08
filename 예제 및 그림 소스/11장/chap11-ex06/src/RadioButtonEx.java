import javax.swing.*;
import java.awt.*;

public class RadioButtonEx extends JFrame {
	public RadioButtonEx() {
		setTitle("라디오버튼 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 이미지 라디오버튼을 만들기 위해 2개의 이미지 객체 생성
		ImageIcon cherryIcon = new ImageIcon("images/cherry.jpg"); // 해제 상태를 나태는 이미지
		ImageIcon selectedCherryIcon = new ImageIcon("images/selectedCherry.jpg"); // 선택 상태를 나타내는 이미지
		
		// 버튼 그룹 객체 생성
		ButtonGroup g = new ButtonGroup();
		
		// 라디오버튼 3개 생성
		JRadioButton apple = new JRadioButton("사과");
		JRadioButton pear = new JRadioButton("배", true);
		JRadioButton cherry = new JRadioButton("체리", cherryIcon); // 이미지 라디오버튼
		
		cherry.setBorderPainted(true); // 이미지 라디오버튼의 외곽선 출력
		cherry.setSelectedIcon(selectedCherryIcon); // 선택 상태 이미지 등록
		
		// 버튼 그룹에 3개의 라디오버튼 삽입
		g.add(apple);
		g.add(pear);
		g.add(cherry);

		// 컨텐트팬에 3개의 라디오버튼 삽입
		c.add(apple);
		c.add(pear);
		c.add(cherry);

		setSize(250,150);
		setVisible(true);
	}
	
	public static void main(String [] args) {
		new RadioButtonEx();
	}
}