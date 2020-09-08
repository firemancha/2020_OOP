import javax.swing.*;
import java.awt.*;

public class ListEx extends JFrame {
	private String [] fruits= {"apple", "banana", "kiwi", "mango", "pear", 
							"peach", "berry", "strawberry", "blackberry"};
	private ImageIcon [] images = { new ImageIcon("images/icon1.png"),
									new ImageIcon("images/icon2.png"),
									new ImageIcon("images/icon3.png"),
									new ImageIcon("images/icon4.png") };
	public ListEx() {
		setTitle("리스트 만들기 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		JList<String> strList = new JList<String>(fruits); // 문자열 리스트 생성
		c.add(strList);
	
		JList<ImageIcon> imageList = new JList<ImageIcon>(); // 이미지 리스트 생성
		imageList.setListData(images);
		c.add(imageList);
		
		JList<String> scrollList = new JList<String>(fruits); // 문자열 리스트 생성
		c.add(new JScrollPane(scrollList)); // 리스트를 JScrollPane에 부착

		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String [] args) {
		new ListEx();
	}
}