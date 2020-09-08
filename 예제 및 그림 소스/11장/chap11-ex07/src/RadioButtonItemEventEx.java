import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class RadioButtonItemEventEx extends JFrame {
	private JRadioButton [] radio = new JRadioButton [3]; // 라디오버튼 배열
	private String [] text = {"사과", "배", "체리"}; // 라디오버튼의 문자열
	private ImageIcon [] image = { // 이미지 객체 배열
						new ImageIcon("images/apple.jpg"), 
						new ImageIcon("images/pear.jpg"),
						new ImageIcon("images/cherry.jpg")};
	private JLabel imageLabel = new JLabel(); // 이미지가 출력될 레이블
	
	public RadioButtonItemEventEx() {
		setTitle("라디오버튼 Item Event 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel radioPanel = new JPanel(); // 3개의 라디오버튼을 부착할 패널
		radioPanel.setBackground(Color.GRAY);
		
		ButtonGroup g = new ButtonGroup(); // 버튼 그룹 객체 생성
		for(int i=0; i<radio.length; i++) { // 3개의 라디오버튼에 대해
			radio[i] = new JRadioButton(text[i]); // 라디오버튼 생성
			g.add(radio[i]); // 버튼 그룹에 부착
			radioPanel.add(radio[i]); // 패널에 부착
			radio[i].addItemListener(new MyItemListener()); // 라디오버튼에 Item 리스너 등록
		}
		
		radio[2].setSelected(true); // 체리 라디오버튼을 선택 상태로 설정
		c.add(radioPanel, BorderLayout.NORTH); // 컨텐트팬의 NORTH에 라디오패널 부착
		c.add(imageLabel, BorderLayout.CENTER); // 컨텐트팬의 CENTER에 이미지 레이블 부착
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // 이미지의 중앙 정렬
		
		setSize(250,200);
		setVisible(true);
	}
	
	// Item 리스너 작성
	class MyItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.DESELECTED)  
				return; // 해제된 경우 그냥 리턴(잠깐 참고)
			if(radio[0].isSelected()) // 사과가 선택된 경우
				imageLabel.setIcon(image[0]);
			else if(radio[1].isSelected()) // 배가 선택된 경우
				imageLabel.setIcon(image[1]);
			else // 체리가 선택된 경우
				imageLabel.setIcon(image[2]);
		}
	}
	
	public static void main(String [] args) {
		new RadioButtonItemEventEx();
	}
}