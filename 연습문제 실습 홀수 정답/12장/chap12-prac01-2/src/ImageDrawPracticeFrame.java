import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageDrawPracticeFrame extends JFrame {
	public ImageDrawPracticeFrame(){
		super("이미지 그리기 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new MyPanel());
		setSize(300,300);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("images/back.jpg");
		private Image img = icon.getImage();
		private JButton button = new JButton("Hide/Show");
		private boolean showFlag = true;
		
		public MyPanel() {
			setLayout(new FlowLayout());
			add(button);
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showFlag = !showFlag; // true와 false의 토글
					MyPanel.this.repaint(); 
					// repaint()는 다시 paintComponent()가 호출되게 하여, 
					// showFlag가 true이면 그리고 false이면 그리지 않도록 함
				}				
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(showFlag) // true이면 그리고 false이면 그리지 않음
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);			
		}
	}
	
	static public void main(String[] args) {
		new ImageDrawPracticeFrame();
	}
}
