import javax.swing.*;
import java.awt.*;

public class ToolTipEx extends JFrame {
	private Container contentPane;
	
	public ToolTipEx() {
		setTitle("툴팁 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = getContentPane();
		createToolBar();
		setSize(400,150);
		setVisible(true);
	}
	
	// 툴바를 생성하여 컨텐트팬의 NORTH에 부착한다.
	private void createToolBar() {
		JToolBar bar = new JToolBar("Kitae Menu"); // 툴바 생성
		bar.setBackground(Color.LIGHT_GRAY);
		
		JButton newBtn = new JButton("New");
		newBtn.setToolTipText("파일을 생성합니다."); // New 버튼에 툴팁 달기
		bar.add(newBtn);
		
		JButton openBtn = new JButton(new ImageIcon("images/open.jpg"));
		openBtn.setToolTipText("파일을 엽니다."); // 열기 버튼에 툴팁 달기
		bar.add(openBtn);
		bar.addSeparator();
		
		JButton saveBtn = new JButton(new ImageIcon("images/save.jpg"));
		saveBtn.setToolTipText("파일을 저장합니다."); // 저장 버튼에 툴팁 달기
		bar.add(saveBtn);		
		bar.add(new JLabel("search"));
		
		JTextField tf = new JTextField("text field");
		tf.setToolTipText("찾고자하는 문자열을 입력하세요"); // 텍스트필드에 툴팁 달기
		bar.add(tf);
		contentPane.add(bar, BorderLayout.NORTH);
	}
	
	public static void main(String [] args) {
		new ToolTipEx();
	}
}