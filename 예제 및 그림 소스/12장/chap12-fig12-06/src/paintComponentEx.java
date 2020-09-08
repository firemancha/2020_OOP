import javax.swing.*;
import java.awt.*;

public class paintComponentEx extends JFrame {
	public paintComponentEx() {
		setTitle("새로운 버튼 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());		
		MyButton b = new MyButton("New Button");
		b.setOpaque(true);
		b.setBackground(Color.CYAN);
		c.add(b);
		setSize(250,200);
		setVisible(true);
	}

	// JButton을 상속받아 새로운 버튼을 구현한다. 이 버튼은 기존 버튼에 항상 버튼의 내접원을 가진다.
	class MyButton extends JButton {
		public MyButton(String s) {
			super(s); // JButton의 생성자 호출
		}
		
		// 새로운 버튼을 그린다.
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // JButton에게 기본 버튼 모양을 그리도록 한다.
			
			// JButton의 기본 모양 위에 추가적으로 빨간색으로 버튼 크기만한 타원을 그린다.
			g.setColor(Color.RED);
			g.drawOval(0,0,this.getWidth()-1, this.getHeight()-1);
		}	
	}
	
	public static void main(String [] args) {
		new paintComponentEx();
	}
} 