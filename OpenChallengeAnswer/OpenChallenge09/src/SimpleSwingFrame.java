import java.awt.*;
import javax.swing.*;

public class SimpleSwingFrame extends JFrame{
	public SimpleSwingFrame() {
		super("Open Challenge 9"); // 타이틀
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout()); // 컨텐트팬이 디폴트로 BorderLayout이기 때문에 이 라인을 없어도 됨
		
		// 두 개의 패널을 붙인다.
		c.add(new NorthPanel(), BorderLayout.NORTH); // NorthPanel을 프레임의 NORTH 영역에 부착한다.
		c.add(new CenterPanel(), BorderLayout.CENTER); // CenterPanel을 프레임의 CENTER 영역에 부착한다.
		setSize(300,300);
		setVisible(true);
	}
	
	// 프레임의 NORTH 영역에 부착되는 패널 클래스
	class NorthPanel extends JPanel {
		public NorthPanel() {
			setBackground(Color.LIGHT_GRAY); // 배경색 설정
			setLayout(new FlowLayout());
			add(new JButton("Open"));
			add(new JButton("Read"));
			add(new JButton("Close"));	
		}
	}

	// 프레임의 CENTER 영역에 부착되는 패널 클래스
	class CenterPanel extends JPanel {
		public CenterPanel() {
			setLayout(null); // 배치관리자 삭제, 절대 위치에 컴포넌트 삽입
			
			JLabel a = new JLabel("Hello");
			a.setSize(100,20);
			a.setLocation(100, 10); // 문자열의 위치 지정
			
			JLabel b = new JLabel("Java");
			b.setSize(100,20);
			b.setLocation(20, 150); // 문자열의 위치 지정
			
			JLabel c = new JLabel("Love");			
			c.setSize(100,20);
			c.setLocation(200, 120); // 문자열의 위치 지정

			// 3 개의 문자열 부착
			add(a);
			add(b);
			add(c);				
		}		
	}
	
	static public void main(String[] arg) {
		new SimpleSwingFrame();
	}
}
