import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamblingWithThread extends JFrame {
	public GamblingWithThread() {
		super("스레드를 가진 겜블링");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GamePanel()); // GamePanel을 컨텐트팬으로 등록한다. 

		setSize(300,220);
		setVisible(true);		
	}
	
	// 컨텐트팬을 사용되는 패널 클래스
	class GamePanel extends JPanel {
		private JLabel [] label = new JLabel [3]; // 3 개의 수를 표현하는 레이블 배열
		private JLabel result = new JLabel("마우스를 클릭할 때 마다 게임합니다."); // 결과를 출력하는 레이블
		private GamblingThread thread = new GamblingThread(label, result); // 게임 스레드
			
		public GamePanel() {
			setLayout(null); // 배치관리자를 사용하지 않고 절대 위치에 컴포넌트를 배치한다.
			
			// 3 개의 레이블을 생성하여 컨테이너에 단다.
			for(int i=0; i<label.length; i++) {
				label[i] = new JLabel("0"); // 레이블 생성
				label[i].setSize(60, 30); // 레이블 크기
				label[i].setLocation(30+80*i, 50); // 레이블 위치
				label[i].setHorizontalAlignment(JLabel.CENTER); // 레이블에 출력되는 수를 센터링
				label[i].setOpaque(true); // 레이블에 배경색 설정이 가능하도록 한다.
				label[i].setBackground(Color.MAGENTA); // 레이블의 배경색을 설정한다.
				label[i].setForeground(Color.YELLOW); //레이블의 글자 색을 설정한다.				
				label[i].setFont(new Font("Tahoma", Font.ITALIC, 30)); // 레이블 글자의 폰트를 설정한다.	
				add(label[i]); // 레이블을 부착한다.
			}
			
			// 결과를 출력할 레이블을 생성하고 컨테이너에 부착한다.
			result.setHorizontalAlignment(JLabel.CENTER); // 레이블에 출력되는 수를 센터링
			result.setSize(250, 20);
			result.setLocation(30, 120);
			add(result);
			thread.start();
			
			// 마우스 리스너를 구현한다.
			addMouseListener(new MouseAdapter() { // MouseAdapter 이용
				@Override
				public void mousePressed(MouseEvent e) {
					if(thread.isReady()) // 스레드가 게임 중이면 그냥 리턴
						thread.startGambling();
				}
			});
		}
	}
	
	class GamblingThread extends Thread {
		private JLabel [] label; //게임 숫자를 출력하는 레이블
		private JLabel result; // 결과를 출력하는 레이블
		private long delay = 200; // 지연 시간의 초깃값 = 200
		private boolean gambling = false; // 게임을 할 것인지. 초깃값 = false
		
		public GamblingThread(JLabel [] label, JLabel result) {
			this.label = label;
			this.result = result;
		}
		
		boolean isReady() {
			return !gambling; // 게임 중이면 준비되지 않았음
		}
		
		synchronized public void waitForGambling() {
			if(!gambling) // 마우스 클릭에 의해 gambling이 true가 아니면 기다림
				try {
					this.wait();
				} catch (InterruptedException e) { return; }
		}
		
		synchronized public void startGambling() {
			gambling = true; // 마우스 클릭으로 스레드가 게임을 진행하도록 지시
			this.notify(); // 기다리는 스레드를 깨움
		}		
		
		@Override
		public void run() {
			while(true) {
				waitForGambling(); // 마우스 클릭에 의해 게임 진행 지시를 기다림
				try {
					int x1 = (int)(Math.random()*5); // 0~4까지의 랜덤수
					int x2 = (int)(Math.random()*5); // 0~4까지의 랜덤수
					int x3 = (int)(Math.random()*5); // 0~4까지의 랜덤수
					
					// 첫번째 수 조정
					label[0].setForeground(Color.BLUE); // 글자가 바뀌는 것을 가시화하기 위해
					sleep(delay);
					label[0].setForeground(Color.YELLOW);
					label[0].setText(Integer.toString(x1));
					
					// 두번째 수 조정					
					label[1].setForeground(Color.BLUE); // 글자가 바뀌는 것을 가시화하기 위해
					sleep(delay);
					label[1].setForeground(Color.YELLOW);					
					label[1].setText(Integer.toString(x2));
					
					// 세번째 수 조정
					label[2].setForeground(Color.BLUE); // 글자가 바뀌는 것을 가시화하기 위해
					sleep(delay);
					label[2].setForeground(Color.YELLOW);					
					label[2].setText(Integer.toString(x3));	
					
					// 게임이 성공하였는지 판별
					if(x1 == x2 && x2 == x3)
						result.setText("축하합니다!!");
					else 
						result.setText("아쉽군요");
					gambling = false; // 이제 다음 게임이 가능하도록 설정
				} catch (InterruptedException e) { return; }
			}
		}	
	}
	
	public static void main(String[] args) {
		new GamblingWithThread();
	}
}

