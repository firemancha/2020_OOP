import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BlueLabelFrame extends JFrame {
	public BlueLabelFrame(){
		super("Blue Label 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		// BlueLabel을 테스트하기 위해 두 개의 레이블을 만든다.
		BlueLabel blueLabel1 = new BlueLabel("hello");
		blueLabel1.setBackground(Color.RED); // 배경색을 빨간색으로 일부러 설정해본다. 반영되지 않는다.
		blueLabel1.setForeground(Color.YELLOW);
		c.add(blueLabel1);
		
		BlueLabel2 blueLabel2 = new BlueLabel2("Big Hello");
		blueLabel2.setOpaque(true);
		blueLabel2.setFont(new Font("TimesRoman", Font.ITALIC, 50));
		blueLabel2.setBackground(Color.RED); // 배경색을 빨간색으로 일부러 설정해본다. 반영되지 않는다.
		blueLabel2.setForeground(Color.MAGENTA);
		c.add(blueLabel2);
		
		setSize(300,200);
		setVisible(true);
	}
	
	static public void main(String[] args) {
		new BlueLabelFrame();
	}
}

// JLabel을 상속받아 만든 항상 푸른바탕의 레이블
// paintComponent()를 이용하는 방법
class BlueLabel extends JLabel {
	public BlueLabel(ImageIcon icon) {
		super(icon);
	}
	public BlueLabel(String text) {
		super(text);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		super.paintComponent(g); // 본래 JLabel이 그리는 코드를 그대로 호출
	}
}

//JLabel을 상속받아 만든 항상 푸른바탕의 레이블
//setBackground()를 오버라이딩 하는 방법
class BlueLabel2 extends JLabel {
	public BlueLabel2(ImageIcon icon) {
		super(icon);
	}
	public BlueLabel2(String text) {
		super(text);
	}	
	
	@Override
	public void setBackground(Color c) {
		super.setBackground(Color.BLUE);
		return;
	}
}
