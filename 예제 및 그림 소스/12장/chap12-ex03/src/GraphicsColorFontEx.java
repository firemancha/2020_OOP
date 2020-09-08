import javax.swing.*;
import java.awt.*;

public class GraphicsColorFontEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public GraphicsColorFontEx() {
		setTitle("Color, Font 사용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel); // 생성한 panel 패널을 컨텐트팬으로 사용

		setSize(350, 470);
		setVisible(true);
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE); // 파란색 선택
			g.drawString("I Love Java.~~", 30,30); // (30,30) 위치에 문자열 출력
			g.setColor(new Color(255, 0, 0)); // 빨간색 선택
			g.setFont(new Font("Arial", Font.ITALIC, 30)); // Arial 폰트 선택
			g.drawString("How much?", 30, 60); // (30,60) 위치에 문자열 출력
			g.setColor(new Color(0x00ff00ff)); // 빨간색과 파란색을 섞은 색 선택
			for(int i=1; i<=5; i++) {
				g.setFont(new Font("Jokerman", Font.ITALIC, i*10));
				g.drawString("This much!!", 30, 60+i*60); 
			}
		}
	}
	
	public static void main(String [] args) {
		new GraphicsColorFontEx();
	}
}