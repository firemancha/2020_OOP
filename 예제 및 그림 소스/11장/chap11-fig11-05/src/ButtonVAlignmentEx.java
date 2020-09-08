import javax.swing.*;
import java.awt.*;

public class ButtonVAlignmentEx extends JFrame {
	public ButtonVAlignmentEx() {
		setTitle("버튼 정렬");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		// 3 개의 이미지를 파일로부터 읽어들인다.
		ImageIcon normalIcon = new ImageIcon("images/normalIcon.gif");// normalIcon용 이미지
		
		// normalIcon을 가진 버튼 컴포넌트 생성
		JButton btn = new JButton("call~~", normalIcon);
		btn.setVerticalAlignment(SwingConstants.TOP);
		c.add(btn);
		
		setSize(250,150);
		setVisible(true);
	}
	
	public static void main(String [] args) {
		new ButtonVAlignmentEx();
	}
} 
