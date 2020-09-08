import javax.swing.*;
import java.awt.*;

public class GraphicsDrawPolygonEx extends JFrame {
	private MyPanel panel = new MyPanel();
	public GraphicsDrawPolygonEx() {
		setTitle("drawPolygon 사용  예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(200, 300);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			int []x = {80,40,80,120};
			int []y = {40,120,200,120};
			g.drawPolygon(x, y, 4);
		}	
	}
	
	public static void main(String [] args) {
		new GraphicsDrawPolygonEx();
	}
}