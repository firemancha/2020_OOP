import javax.swing.*;

public class SimpleMenuFrame extends JFrame {
	public SimpleMenuFrame() {
		super("메뉴 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createMenu();
		setSize(300,200);
		setVisible(true);
	}
	private void createMenu() {
		JMenu fileMenu = new JMenu("파일");
		JMenu editMenu = new JMenu("편집");
		JMenu viewMenu = new JMenu("보기");
		viewMenu.add(new JMenuItem("화면확대"));
		viewMenu.add(new JMenuItem("쪽윤곽"));
		JMenu inputMenu = new JMenu("입력");
		JMenuBar mb = new JMenuBar();
		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(viewMenu);
		mb.add(inputMenu);		
		setJMenuBar(mb);		
	}
	static public void main(String[] arg) {
		new SimpleMenuFrame();
	}
}
