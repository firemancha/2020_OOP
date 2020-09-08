import javax.swing.*;
import java.awt.*;

public class GraphicsDrawOvalEx extends JFrame {
	private MyPanel panel = new MyPanel();

	public GraphicsDrawOvalEx() {
		setTitle("drawOval 사용  예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(200, 170);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.RED);
			g.drawOval(20,20, 80, 80);
		}	
	}
	
	public static void main(String [] args) {
		new GraphicsDrawOvalEx();
	}
} 