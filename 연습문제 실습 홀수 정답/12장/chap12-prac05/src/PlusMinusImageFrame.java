import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlusMinusImageFrame extends JFrame {
	public PlusMinusImageFrame(){
		super("그래픽 이미지 확대 축소 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new MyPanel());
		setSize(300,300);
		setVisible(true);
		
		getContentPane().setFocusable(true);
		getContentPane().requestFocus();
	}
	
	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("images/apple.jpg");		
		private Image img = icon.getImage();
		private int width, height;
		
		public MyPanel() {
			// 이미지의 원본 크기 기억
			width = img.getWidth(this);
			height = img.getHeight(this);
			
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyChar() == '+') {
						// 그려질 이미지 크기 확대
						width = (int)(width*1.1);
						height = (int)(height*1.1);	
						repaint(); // 다시 그리기 지시
					}
					else if(e.getKeyChar() == '-') {
						// 그려질 이미지 크기 축소						
						width = (int)(width*0.9);
						height = (int)(height*0.9);
						repaint(); // 다시 그리기 지시						
					}
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 10, 10, width, height, this);
		}		
	}
	
	
	static public void main(String[] args) {
		new PlusMinusImageFrame();
	}
}
