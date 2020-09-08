import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RandomThread extends Thread {
	private Container contentPane;
	private boolean flag=false; // 스레드의 종료 명령을 표시하는 플래그. true : 종료 지시
	
	public RandomThread(Container contentPane) { 
		this.contentPane = contentPane;
	}
	
	public void finish() { // 스레드 종료 명령을 flag에 표시
		flag = true;
	} 
	
	@Override
	public void run() {
		while(true) { 
			int x = ((int)(Math.random()*contentPane.getWidth()));
			int y = ((int)(Math.random()*contentPane.getHeight()));
			JLabel label = new JLabel("java"); //새 레이블 생성
			label.setSize(80, 30); // 레이블의 크기 80x30
			label.setLocation(x, y); // 레이블을 컨텐트팬 내의 임의의 위치로 설정
			contentPane.add(label); // 레이블을 컨텐트팬에 추가
			contentPane.repaint(); // 컨텐트팬을 다시 그려 추가된 레이블이 보이게 함
			try {
				Thread.sleep(300); // 0.3초 동안 잠을 잔다.
				if(flag==true) {
					contentPane.removeAll(); // 컨텐트팬에 있는 모든 레이블 제거
					label = new JLabel("finish"); 
					label.setSize(80, 30); // “Java” 레이블의 크기 80x30
					label.setLocation(100, 100); // 레이블을 컨텐트팬 내의 임의의 위치로 설정
					label.setForeground(Color.RED);
					contentPane.add(label); // “finish” 레이블 달기
					contentPane.repaint(); // 컨텐트팬을 다시 그려 추가된 레이블이 보이게 함
					return; // 스레드 종료
				}
			}
			catch(InterruptedException e) { return; }
		}
	}
}

public class ThreadFinishFlagEx extends JFrame {
	private RandomThread th; // 스레드 레퍼런스
	
	public ThreadFinishFlagEx() {
		setTitle("ThreadFinishFlagEx 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		c.addMouseListener(new MouseAdapter() { // 마우스 리스너 등록
			@Override
			public void mousePressed(MouseEvent e) {
				th.finish(); // RandomThread 스레드 종료 명령
			}
		});
		setSize(300,200);
		setVisible(true);

		th = new RandomThread(c); // 스레드 생성, 스레드에 컨텐트팬 전달
		th.start(); // 스레드 시작
	}

	public static void main(String[] args) {
		new ThreadFinishFlagEx();
	}
}