import javax.swing.*;
import java.awt.*;

public class TabbedPaneEx extends JFrame {
	public TabbedPaneEx() {
		setTitle("탭팬 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		JTabbedPane pane = createTabbedPane(); // 탭팬을 생성한다.
		c.add(pane, BorderLayout.CENTER); // 탭팬을 컨텐트팬에 부착한다.
		setSize(250,250);
		setVisible(true);
	}
	
	// 탭팬을 생성하고 3개의 탭을 생성하여 부착한다.
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(); // 탭팬 객체를 생성한다.
		pane.addTab("tab1", new JLabel(new ImageIcon("images/img1.jpg"))); // 첫 번째 탭
		pane.addTab("tab2", new JLabel(new ImageIcon("images/img2.jpg"))); // 두 번째 탭
		pane.addTab("tab3", new MyPanel()); // 세 번째 탭
		return pane;
	}
	
	// 3번째 탭의 컴포넌트로 사용될 패널
	class MyPanel extends JPanel {
		public MyPanel() {
			this.setBackground(Color.YELLOW);
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.fillRect(10,10,50,50);
			g.setColor(Color.BLUE);
			g.fillOval(10,70,50,50);
			g.setColor(Color.BLACK);
			g.drawString("tab 3에 들어가는 JPanel 입니다. ", 30, 50);
		}
	}

	public static void main(String [] args) {
		new TabbedPaneEx();
	}
}