import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyLabel extends JLabel {
	private int barSize = 0; // 현재 그려져야할 바의 크기
	private int maxBarSize; // 바의 최대 크
	
	public MyLabel(int maxBarSize) { 
		this.maxBarSize = maxBarSize;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		int width = (int) (((double)(this.getWidth()))/maxBarSize*barSize);
		if(width==0) return; // 크기가 0이기 때문에 바를 그릴 필요 없음
		g.fillRect(0, 0, width, this.getHeight());
	}
	
	synchronized public void fill() {
		if(barSize == maxBarSize) {
			try {
				wait(); // 바의 크기가 최대이면, ComsumerThread에 의해 바의 크기가 줄어들때까지 대기
			} catch (InterruptedException e) { return; }
		}
		barSize++;
		repaint(); // 바의 크기가 변했으니 다시 그리기
		notify(); // 기다리는 ConsumerThread 스레드 깨우기
	}
	synchronized public void consume() {
		if(barSize == 0) {
			try {
				wait(); // 바의 크기가 0이면 바의 크기가 0보다 커질 때까지 대기
			} catch (InterruptedException e) { return; }
		}
		barSize--;
		repaint(); // 바의 크기가 변했으니 다시 그리기
		notify(); // 기다리는 이벤트 스레드 깨우기
	}
}

class ConsumerThread extends Thread {
	private MyLabel bar;
	
	public ConsumerThread(MyLabel bar) {
		this.bar = bar;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				sleep(200);
				bar.consume(); // 0.2초마다 바를 1씩 줄인다.
			} catch (InterruptedException e) { return; }
		}
	}
}

public class TabAndThreadEx extends JFrame {
	private MyLabel bar = new MyLabel(100); // 바의 최대 크기를 100으로 설정
	
	public TabAndThreadEx(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20,  50);
		bar.setSize(300, 20); // 300x20 크기의 바
		c.add(bar);

		// 컨텐트팬에 키 이벤트 핸들러 등록
		c.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				bar.fill(); // 키를 누를 때마다 바가 1씩 증가한다.
			}
		});
		setSize(350,200);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus(); // 컨텐트팬에게 키 처리권 부여
		ConsumerThread th = new ConsumerThread(bar); // 스레드 생성
		th.start(); // 스레드 시작
	}

	public static void main(String[] args) {
		new TabAndThreadEx("아무키나 빨리 눌러 바 채우기");
	}
}