import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SliderChangeEx extends JFrame {
	private JLabel colorLabel;
	private JSlider [] sl = new JSlider [3]; // 3개의 슬라이더 배열 생성
	
	public SliderChangeEx() {
		setTitle("슬라이더와 ChangeEvent 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		colorLabel = new JLabel("       SLIDER EXAMPLE        ");
		
		// 슬라이더를 3개 생성하고 모양을 제어한다.
		for(int i=0; i<sl.length; i++) {
			// 0~255 사이의 값을 선택할 수 있는 슬라이더 생성. 초깃값은 128
			sl[i] = new JSlider(JSlider.HORIZONTAL, 0, 255, 128); 
			sl[i].setPaintLabels(true);
			sl[i].setPaintTicks(true);
			sl[i].setPaintTrack(true);
			sl[i].setMajorTickSpacing(50);
			sl[i].setMinorTickSpacing(10);
			
			// 슬라이더에 Change 리스너 등록
			sl[i].addChangeListener(new MyChangeListener());
			c.add(sl[i]); // 슬라이더를 컨텐트팬에 삽입
		}
		
		sl[0].setForeground(Color.RED); // s[0] 슬라이더는 RED 값 선택
		sl[1].setForeground(Color.GREEN); // s[1] 슬라이더는 GREEN 값 선택
		sl[2].setForeground(Color.BLUE); // s[0] 슬라이더는 BLUE 값 선택
		
		// 현재 3개의 슬라이더로부터 값을 얻어 초기 colorLabel의 배경색 설정
		int r = sl[0].getValue(); // 첫 번째 슬라이더로부터 R 성분 값 얻기
		int g = sl[1].getValue(); // 두 번째 슬라이더로부터 G 성분 값 얻기
		int b = sl[2].getValue(); // 세 번째 슬라이더로부터 B 성분 값 얻기
		colorLabel.setOpaque(true); // 배경색이 출력되도록 불투명성 설정
		colorLabel.setBackground(new Color(r,g,b)); // r,g,b 값으로 새로운 색 설정
		
		c.add(colorLabel);
		setSize(300,230);
		setVisible(true);
	}

	// Change 리스너 구현
	class MyChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) { // 슬라이더의 값이 변경할 때 호출
			// 3개의 슬라이더로부터 값을 얻어 colorLabel의 배경색 변경
			int r = sl[0].getValue(); // R 성분 값 얻기
			int g = sl[1].getValue(); // G 성분 값 얻기
			int b = sl[2].getValue(); // B 성분 값 얻기
			colorLabel.setBackground(new Color(r,g,b)); // r,g,b 값으로 새로운 색 설정
		}
	}
	public static void main(String [] args) {
		new SliderChangeEx();
	}
}