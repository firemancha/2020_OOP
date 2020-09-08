import java.awt.*;
import javax.swing.*;

public class ImageDrawPracticeFrame extends JFrame {
	public ImageDrawPracticeFrame(){
		super("이미지 그리기 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new MyPanel());
		setSize(300,300);
		setVisible(true);
		getContentPane().addMouseListener(new java.awt.event.MouseAdapter() {
			int n = 10;
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(e.getButton() == java.awt.event.MouseEvent.BUTTON1)
					n+=5;
				else
					n-=5;
				changeFontRecursive((java.awt.Container)e.getSource(), 
							new java.awt.Font("Gothic", java.awt.Font.PLAIN, n));	
			}
			private void changeFontRecursive(java.awt.Container root, java.awt.Font font) {
			    for (java.awt.Component c : root.getComponents()) {
			       	c.setFont(font);
				if (c instanceof java.awt.Container) {
			       		changeFontRecursive((java.awt.Container) c, font);
				}
			    }
			}
		});	

	}
	
	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("images/back.jpg");
		private Image img = icon.getImage();
		
		public MyPanel() {
			setLayout(new FlowLayout());
			add(new JButton("Hide/Show"));
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
	
	static public void main(String[] args) {
		new ImageDrawPracticeFrame();
	}
}
