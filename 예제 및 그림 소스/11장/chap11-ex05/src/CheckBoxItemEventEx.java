import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CheckBoxItemEventEx extends JFrame {
	private JCheckBox [] fruits = new JCheckBox [3]; // 체크박스 배열
	private String [] names = {"사과", "배", "체리"}; // 체크박스 문자열로 사용할 문자열 배열
	private JLabel sumLabel; // 계산 합을 출력할 레이블
	
	public CheckBoxItemEventEx() {
		setTitle("체크박스와 ItemEvent 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("사과 100원, 배 500원, 체리 20000원"));
		
		// 3개의 체크박스를 생성. 컨텐트팬에 삽입하고 Item 리스너 등록
		MyItemListener listener = new MyItemListener();
		for(int i=0; i<fruits.length; i++) {
			fruits[i] = new JCheckBox(names[i]); // names[]의 문자열로 체크박스 생성
			fruits[i].setBorderPainted(true); // 체크박스 외곽선 보이도록 설정
			c.add(fruits[i]); // 컨텐트팬에 체크박스 삽입
			fruits[i].addItemListener(listener); // 체크박스에 Item 리스너 등록
		}
		
		sumLabel = new JLabel("현재 0 원 입니다."); // 가격 합을 출력하는 레이블 생성
		c.add(sumLabel);
		
		setSize(250,200);
		setVisible(true);
	}
	
	// Item 리스너 구현
	class MyItemListener implements ItemListener {
		private int sum = 0; // 가격의 합

		// 체크박스의 선택 상태가 변하면 itemStateChanged()가 호출됨
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) { // 선택 경우
				if(e.getItem() == fruits[0]) // 사과 체크박스 
					sum += 100;
				else if(e.getItem() == fruits[1]) // 배 체크박스
					sum += 500;
				else // 체리 체크박스
					sum += 20000;
			}
			else { // 해제 경우
				if(e.getItem() == fruits[0]) // 사과 체크박스 
					sum -= 100;
				else if(e.getItem() == fruits[1]) // 배 체크박스
					sum -= 500;
				else // 체리 체크박스
					sum -= 20000;
			}
			sumLabel.setText("현재 "+ sum + "원 입니다."); // 합 출력
		}
	}
	public static void main(String [] args) {
		new CheckBoxItemEventEx();
	}
} 