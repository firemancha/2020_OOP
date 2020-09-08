import javax.swing.*;
import java.awt.*;

public class ToolTipDelayEx extends JFrame {
	public ToolTipDelayEx() {
		setTitle("툴팁 지연 시간 제어 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 체리 이미지 레이블 생성
		JLabel cherryLabel = new JLabel(new ImageIcon("images/cherry.jpg"));
		cherryLabel.setToolTipText("체리 이미지 어때요"); // 툴팁 달기
		
		// 사과 이미지 레이블 생성
		JLabel appleLabel = new JLabel(new ImageIcon("images/apple.jpg"));
		appleLabel.setToolTipText("사과 이미지 어때요"); // 툴팁 달기
		
		c.add(cherryLabel);
		c.add(appleLabel);
		
		// ToolTipManager 객체를 얻기
		ToolTipManager m = ToolTipManager.sharedInstance();
		m.setInitialDelay(0); // 초기 툴팁 출력 지연 시간을 0초로 설정
		m.setDismissDelay(10000); // 툴팁 지속 시간을 10초로 설정
		
		setSize(400,220);
		setVisible(true);
	}
	
	public static void main(String [] args) {
		new ToolTipDelayEx();
	}
} 