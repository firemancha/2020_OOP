import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageLabelDraggingFrame extends JFrame {
	public ImageLabelDraggingFrame(){
		super("이미지 레이블 드래깅 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		ImageIcon icon = new ImageIcon("images/apple.jpg"); // 이미지 로딩		
		JLabel imageLabel = new JLabel(icon); // 이미지 레이블 만들기
	
		// 이미지 레이블의 크기와 위치 지정
		imageLabel.setSize(icon.getIconHeight(),icon.getIconWidth());
		imageLabel.setLocation(100,100);
		
		// 마우스 리스너와 마우스모션 리스너 객체 달기
		MyMouseListener  ml = new MyMouseListener();
		imageLabel.addMouseListener(ml);
		imageLabel.addMouseMotionListener(ml);

		c.add(imageLabel); // 컨텐트팬에 이미지 레이블 부착
		setSize(300,300);
		setVisible(true);
	}
	
	class MyMouseListener extends MouseAdapter {
		private Point startP = null; // 드래깅 시작 점
		
		@Override
		public void mousePressed(MouseEvent e) {
			startP = e.getPoint();
		}
		
		// 다음 mouseReleased()는 없어되 되는 코드임.
		// 하지만 실습문제 4를 풀기 위해서는 반드시  mouseReleased()를 구현하여야 함
		@Override
		public void mouseReleased(MouseEvent e) {
			Point endP = e.getPoint();
			Component la = (JComponent)(e.getSource());
			Point p = la.getLocation();
			la.setLocation(p.x + endP.x - startP.x, p.y + endP.y - startP.y);
			la.getParent().repaint(); // 움직인 la의 위치에 다시 그리기
		}	
		
		@Override
		public void mouseDragged(MouseEvent e) {
			Point endP = e.getPoint();
			Component la = (JComponent)(e.getSource());
			Point p = la.getLocation();
			la.setLocation(p.x + endP.x - startP.x, p.y + endP.y - startP.y);
			la.getParent().repaint(); // 움직인 la의 위치에 다시 그리기
		}	
	}
	
	static public void main(String[] args) {
		new ImageLabelDraggingFrame();
	}
}
