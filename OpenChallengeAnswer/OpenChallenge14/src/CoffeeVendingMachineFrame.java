import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CoffeeVendingMachineFrame extends JFrame {
	// 커피 시뮬레이션 패널
	private CoffeeMachinePanel coffeeMachinePanel = new CoffeeMachinePanel();

	public CoffeeVendingMachineFrame() {
		setTitle("Open Challenge 14");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
	 	c.add(new TitlePanel(), BorderLayout.NORTH); // "Welcome, Hot Coffee !!" 출력하는 패널
		c.add(coffeeMachinePanel, BorderLayout.CENTER); // 커피 자판기 시뮬레이션, 재료 통을 보여주는 패널
		c.add(new ButtonPanel(), BorderLayout.SOUTH); //3 종류의 커피 선택 버튼과 "Reset" 버튼을 가진 패널
		
		setSize(450,400);
		setResizable(false); // 사용자가 프레임의 크기 조절할 수 없도록 설정
		setVisible(true);
	}

	// 컨텐트팬의 NORTH에 부착되는 패널로서 "Welcome, Hot Coffee !!"를 출력하기 위한 목적
	class TitlePanel extends JPanel {
		private JLabel titleMsg = new JLabel("Welcome, Hot Coffee !!");
		public TitlePanel() {
			titleMsg.setHorizontalAlignment(JLabel.CENTER); // 레이블의 문자열을 중앙에 정렬
			setBackground(Color.MAGENTA);			
			add(titleMsg);
		}
	}
	
	// 컨텐트팬의 SOUTH에 부착되는 패널로서 3 종류의 커피 선택 버튼과 "Reset" 버튼을 달기 위한 목적
	class ButtonPanel extends JPanel {
		private JButton [] coffeeButtons = new JButton[4]; // 3 개의 커피 선택 버튼과 "Reset" 버튼
		private String [] names = {"Black Coffee", "Sugar Coffee", "Dabang Coffee", "Reset"}; //버튼 문자열 배열
		
		public ButtonPanel() {
			for(int i=0; i<coffeeButtons.length; i++) { // 버튼 배열의 개수(4)만큼 루프
				coffeeButtons[i] = new JButton(names[i]); // 버튼 컴포넌트 생성
				add(coffeeButtons[i]); // 버튼 달기
				
				// 모든 버튼에 Action 리스너 등록
				coffeeButtons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) { // 
						// e.getActionCommand()는 클릭된 버튼의 문자열 정보 리턴
						coffeeMachinePanel.operate(e.getActionCommand());				
					}
				});
			}
		}
	}
	
	
	// 커피 자판기가 시뮬레이션하고 모든 재료통을 보여주는  패널. 배치관리자를 null로 설정
	class CoffeeMachinePanel extends JPanel {
		private BoxLabel [] boxes = new BoxLabel[5]; // 5 개의 재료통을 표현하는 레이블 컴포넌트 배열
		private JLabel coffeeImageLabel; // 커피가 나왔을 때 이를 보여주는커피잔 이미지 레이블
		
		 // BoxLabel[]에 대한 인덱스
		private final int CUP = 0;
		private final int COFFEE = 1;
		private final int WATER = 2;
		private final int SUGAR = 3;
		private final int CREAM = 4;
		
		// 각 재료통의 이름 문자열, 레이블로 만들어 통 밑에 출력함
		private String [] boxNames = {"Cup", "Coffee", "Water", "Sugar", "Cream"};
		
		// 커피잔의 이미지 객체
		private ImageIcon coffeeIcon = new ImageIcon("images/coffee.jpg");
		
		public CoffeeMachinePanel() {
			setLayout(null); // 컴포넌트의 위치를 마음대로 지정할 수 있도록 null로 설정
			
			for(int i=0; i<boxes.length; i++) {
				boxes[i] = new BoxLabel(); // 재료통을 표시하는 레이블 컴포넌트 생성
				boxes[i].setLocation(30+80*i, 10); // 레이블의 위치 설정
				boxes[i].setSize(40, 100); // 레이블의 크기 설정
				
				JLabel text = new JLabel(boxNames[i]); // 재료통 밑에 출력할 문자열
				text.setLocation(30+80*i, 120);
				text.setSize(50, 30);
				
				add(boxes[i]);
				add(text);
			}
			
			coffeeImageLabel = new JLabel(); // 커피잔의 이미지를 출력할 이미지 레이블			
			coffeeImageLabel.setLocation(180, 200);
			coffeeImageLabel.setSize(coffeeIcon.getIconWidth(), coffeeIcon.getIconHeight());
			
			add(coffeeImageLabel);
		}
		
		public void operate(String cmd) { // 자판기 시뮬레이터의 핵심 처리 루틴
			if(cmd.equals("Black Coffee")) { // 눌러진 버튼이 "Black Coffee" 이면
				if(boxes[CUP].isEmpty() || boxes[COFFEE].isEmpty() || boxes[WATER].isEmpty()) {
					error("부족한 것이 있습니다. 채워주세요."); // 경고창을 출력한다.
					return;
				}
				else {
					boxes[CUP].consume(); // 컵을 하나 줄인다.
					boxes[COFFEE].consume(); // 커피 양을 하나 줄인다.
					boxes[WATER].consume(); // 물 양을 하나 줄인다.
				}
			}
			else if(cmd.equals("Sugar Coffee")) { // 눌러진 버튼이 "Sugar Coffee" 이면
				if(boxes[CUP].isEmpty() || boxes[COFFEE].isEmpty() || boxes[WATER].isEmpty() ||
						boxes[SUGAR].isEmpty()) {
					error("부족한 것이 있습니다. 채워주세요."); // 경고창을 출력한다.
					return;
				}		
				else {
					boxes[CUP].consume();  // 컵을 하나 줄인다.
					boxes[COFFEE].consume(); // 커피 양을 하나 줄인다.
					boxes[WATER].consume(); // 물 양을 하나 줄인다.
					boxes[SUGAR].consume();	// 설탕 양을 하나 줄인다.				
				}
			}
			else if(cmd.equals("Dabang Coffee")) { // 눌러진 버튼이 "Dabang Coffee" 이면
				if(boxes[CUP].isEmpty() || boxes[COFFEE].isEmpty() || boxes[WATER].isEmpty() ||
						boxes[SUGAR].isEmpty() || boxes[CREAM].isEmpty()) {
					error("부족한 것이 있습니다. 채워주세요."); // 경고창을 출력한다.
					return;
				}
				else {
					boxes[CUP].consume(); // 컵을 하나 줄인다.
					boxes[COFFEE].consume(); // 커피 양을 하나 줄인다.
					boxes[WATER].consume(); // 물 양을 하나 줄인다.
					boxes[SUGAR].consume();	// 설탕 양을 하나 줄인다.	
					boxes[CREAM].consume();	// 크림 양을 하나 줄인다.	
				}
			}
			else { // // 눌러진 버튼이 "reset" 이면
				boxes[CUP].reset(); // 컵을 초기 상태와 같이 가득 채운다.
				boxes[COFFEE].reset(); // 커피를 초기 상태와 같이 가득 채운다.
				boxes[WATER].reset(); // 물을 초기 상태와 같이 가득 채운다.
				boxes[SUGAR].reset(); // 설탕을 초기 상태와 같이 가득 채운다.	
				boxes[CREAM].reset(); // 크림을 초기 상태와 같이 가득 채운다.
				repaint(); // boxes[]의 레이블 컴포넌트에 변화가 생겼으므로 부모에게 다시 그리도록 한다.
				return;
			}

			repaint(); // boxes[]의 레이블 컴포넌트에 변화가 생겼으므로 부모에게 다시 그리도록 한다.
			
			// 성공적으로 커피가 나오게 되었으므로 커피잔을 출력한다.
			this.coffeeImageLabel.setIcon(coffeeIcon);
			
			// 커피가 나왔음을 알려주는 메시지 창을 출력한다.
			JOptionPane.showMessageDialog(CoffeeMachinePanel.this, "뜨거워요, 즐거운 하루~~", "커피나왔습니다.", 
					JOptionPane.INFORMATION_MESSAGE);
			
			// 커피잔 이미지를 지운다.
			coffeeImageLabel.setIcon(null);

		}
			
		public void error(String msg) { // 경고창을 출력하는 메소드
			JOptionPane.showMessageDialog(CoffeeMachinePanel.this, msg, "커피 자판기 경고", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

	// 재료통을 묘사하는 레이블 클래스
	class BoxLabel extends JLabel {
		private final int MAXSIZE = 10; // 통 크기
		private int currentSize; // 현재 통에 들어 있는 재료의 양
		
		public BoxLabel() {
			currentSize = MAXSIZE; // 초기에는 통에 재료가 가득채워져 있도록 설정				
		}
		
		boolean consume() { // 재료를 하나 소비한다.
			if(currentSize > 0) {
				currentSize--;
				return true;
			}
			else
				return false;
		}
		
		void reset() { // 통을 초기 상태의 양으로 설정한다. 
			currentSize = MAXSIZE; 
		}				
	
		boolean isEmpty() { return currentSize == 0; }
		
		// 통에 남아 있는 재료의 양을 보여주기 위해 작성됨
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// LIGHT_GRAY 색으로 남아 있는 양을 보여 주기위해 그린다.
			g.setColor(Color.LIGHT_GRAY);
			int y = this.getHeight() - (currentSize*this.getHeight() / MAXSIZE);
			g.fillRect(0, y, this.getWidth(), this.getHeight() - y);
			
			// GRAY 색으로 통의 외곽선을 그린다.			
			g.setColor(Color.GRAY);
			g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);			
		}
	}
	
	static public void main(String [] args) {
		new CoffeeVendingMachineFrame();
	}
}
 
