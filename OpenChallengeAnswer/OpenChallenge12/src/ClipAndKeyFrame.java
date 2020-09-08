import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClipAndKeyFrame extends JFrame {
	public ClipAndKeyFrame() {
		super("Open Challenge 12");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImagePanel p = new ImagePanel();
		setContentPane(p);
		
		setSize(400, 400);
		setVisible(true);
		
		p.setFocusable(true);
		p.requestFocus(); // 이미지 패널이 상,하,좌,우 키를 받게 하기 위해 포커스 설정
	}
	
	// 배경 이미지와 클리핑 영역을 가지고 상, 하, 좌, 우 키를 처리하는 패널
	class ImagePanel extends JPanel {
		private Image img =null; // 초기에 로딩된 이미지 없음
		private int clipX=0, clipY=0; // 클리핑 영역
		
		public ImagePanel() {
			img = new ImageIcon("images/bg.jpg").getImage(); // 패널의 배경 이미지
			
			// 이 패널을 마우스로 클릭하면 강제로 포커스를 이 패널로 가져와서 키 입력을 처리할 수 있게 한다.
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JPanel panel = (JPanel)e.getSource();
					panel.requestFocus();
				}
			});
			this.addKeyListener(new MyKeyListener()); // 클리핑 영역을 움직이는 키를 처리하는 리스너
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setClip(clipX, clipY, 50, 50); // 클리핑 영역 지정, 클리핑 영역 크기 20x20
			g.drawImage(img,0,0,getWidth(), getHeight(), this); // 배경 이미지를 패널 전체에 그리기
			g.drawString("자바",40,340); // 문자열 "자바" 그리기			
		}
		
		class MyKeyListener extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent e) {
				// 상, 하, 좌, 우 키는  유니코드 키가 아니므로 getKeyCode()로 키 값을 알아낸다.
				switch(e.getKeyCode()) { 
					case KeyEvent.VK_UP : 
						clipY -=10;
						if(clipY < 0) // 클립 영역이 패널 위로 넘어갈 때
							clipY = 0;
						break; // UP 키이면 클리핑 영역 위로 이동 
					case KeyEvent.VK_DOWN :
						clipY += 10; 
						if(clipY > getHeight()) // 클립 영역이 패널 아래쪽으로 넘어갈 때
							clipY -= 10; // 원상 복귀
						break; // DOWN 키이면 클리핑 영역 아래로 이동
					case KeyEvent.VK_LEFT : 
						clipX -=10;
						if(clipX < 0) // 클립 영역이 패널 왼쪽으로 넘어갈 때
							clipX = 0;
						break; // LEFT 키이면  클리핑 영역 왼쪽으로 이동
					case KeyEvent.VK_RIGHT :
						clipX += 10;
						if(clipX> getWidth()) // 클립 영역이 패널 오른쪽으로 넘어갈 때
							clipX -= 10; // 원상 복귀						
						break; // RIGHT 키이면   클리핑 영역 오른쪽으로 이동 
				}
				repaint(); // 클리핑 영역이 변경되었으므로 패널 다시 그리기(중요함)
			}
		}
	}
	public static void main(String[] args) {
		new ClipAndKeyFrame();
	}
}
