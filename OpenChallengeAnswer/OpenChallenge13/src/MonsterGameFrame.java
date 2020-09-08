import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MonsterGameFrame extends JFrame {	
	// 아바타로 사용할 문자열은 "@", 괴물로 사용할 문자열은 "M", 종료키는 'q', 괴물은 200ms 주기로 움직인다
	private JPanel gamePanel = new GamePanel("@", "M", 'q', 200); // 게임 패널, 컨텐트팬으로 사용한다.

	public MonsterGameFrame() {
		setTitle("Open Challenge 13");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(gamePanel); // GamePanel을 컨텐트팬으로 사용한다.
	
		setSize(300,300);
		setVisible(true);

		gamePanel.setFocusable(true);		
		gamePanel.requestFocus(); // GamePanel이 키를 입력받을 수 있도록 포커스를 설정한다.
	}

	// 게임이 진행되는 패널. 배치관리자를 null로 설정
	class GamePanel extends JPanel {
		private String avatarChar;
		private String monsterChar;
		private char quitChar;
		private long monsterDelay;
		private JLabel avatar;// 아바타를 위한 레이블
		private JLabel monster; // 괴물을 위한 레이블
		final int AVATAR_MOVE = 10; // 아바타가 한번에 움직이는 픽셀 수

		public GamePanel(String avatarChar, String monsterChar, char quitChar, long monsterDelay) {
			this.avatarChar = avatarChar;
			this.monsterChar = monsterChar;
			this.quitChar = quitChar;
			this.monsterDelay = monsterDelay;
			
			// 아바타와 괴물 레이블 생성
			avatar = new JLabel(avatarChar); 
			monster = new JLabel(monsterChar);
			
			setLayout(null); // 아타바 레이블과 괴물 레이블의 위치를 마음대로 지정할 수 있도록 null로 설정
			addKeyListener(new MyKeyListener()); // 키 리스너 등록
			
			// 아바타 레이블의 위치와 크기 설정 및 팬에 부착
			avatar.setLocation(50,50);
			avatar.setSize(15,15);
			avatar.setForeground(Color.RED);
			add(avatar);
			
			// 괴물 레이블의 위치와 크기 설정 및 팬에 부착
			monster.setLocation(200,5);
			monster.setSize(15,15);
			add(monster);
			
			// 괴물을 움직이는 스레드 생성 및 시작 
			MonsterThread th = new MonsterThread(monster, avatar, monsterDelay);
			th.start();
			
			// 이 패널을 마우스로 클릭하면 강제로 포커스를 이 패널로 가져와서 키 입력을 처리할 수 있게 한다.
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JPanel panel = (JPanel)e.getSource();
					panel.requestFocus();
				}
			});

		}
		
		// GamePanel에 등록된 키 리스너. 상, 하, 좌, 우 키와 'q' 키 처리
		class MyKeyListener extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == quitChar) // 종료 키는 유니코드 키
					System.exit(0); // 종료 키가 입력되면 프로그램 종료
				
				int keyCode = e.getKeyCode(); // 상, 하, 좌, 우 키는 유니코드 키가 아님
				
				// 키에 따라 아바타 레이블을 AVATAR_MOVE 픽셀 만큼 움직인다.
				switch(keyCode) {
				case KeyEvent.VK_UP: 
					avatar.setLocation(avatar.getX(), avatar.getY()-AVATAR_MOVE); 
					break;
				case KeyEvent.VK_DOWN: 
					avatar.setLocation(avatar.getX(), avatar.getY()+AVATAR_MOVE); 
					break;
				case KeyEvent.VK_LEFT: 
					avatar.setLocation(avatar.getX()-AVATAR_MOVE, avatar.getY()); 
					break;
				case KeyEvent.VK_RIGHT: 
					avatar.setLocation(avatar.getX()+AVATAR_MOVE, avatar.getY()); 
					break;
				}
				
				avatar.getParent().repaint(); // 아바타의 위치가 변경되었으므로 다시 그리기
				// 아바타가 있는 패널에는 이전의 위치에 있었던 아바타를 지워야 하기 때문에
				// 아바타의 부모 패널에게 다시그리기를 지시함
			}
		}
	}

	// 괴물 레이블을 움직이는 200ms 당 5 픽셀을 움직이면서 끊임없이 아바타를 추적하는 스레드
	// 생성자는 두 개의 레이블 컴포넌트를 전달받는다. 첫번째 from이 두번째 to 레이블을 추적한다.
	class MonsterThread extends Thread {
		private JLabel from; // 좇아가는 레이블. 괴물 레이블
		private JLabel to; // 도망가는 레이블. 아바타 레이블
		private long monsterDelay;
		private final int MONSTER_MOVE = 5; // from 레이블이 한번에 이동하는 거리
		
		public MonsterThread(JLabel from, JLabel to, long monsterDelay) {
			this.from = from;
			this.to = to;
			this.monsterDelay = monsterDelay;
		}
		
		@Override
		public void run() {
			int x=from.getX(),y=from.getY(); // 현재 괴물의 위치
			
			while(true) {
				if(to.getX() < from.getX()) // 아바타가 괴물의 왼쪽이 있는 경우 
					x = from.getX() - MONSTER_MOVE;
				else 						// 아바타가 괴물의 오른쪽이 있는 경우
					x = from.getX() + MONSTER_MOVE;
				
				if(to.getY() < from.getY()) // 아바타가 괴물의 위쪽이 있는 경우
					y = from.getY() - MONSTER_MOVE;
				else 						// 아바타가 괴물의 아래쪽이 있는 경우
					y = from.getY() + MONSTER_MOVE;			
				
				// 괴물의 위치 수정
				from.setLocation(x, y);
				
				// 괴물의 위치가 변경되었기 때문에 괴물을 포함하는 패널을 다시 그리도록 함
				from.getParent().repaint();
				
				// 200ms 동안 잠을 잔다.
				try {
					sleep(monsterDelay);
				}catch(InterruptedException e) {
					return;
				}
			}
		}
	}
	
	public static void main(String [] args) {
		new MonsterGameFrame();
	}
} 