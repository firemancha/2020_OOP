import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;

public class MenuAndFileDialogEx extends JFrame {
	private JLabel imageLabel = new JLabel();
	
	public MenuAndFileDialogEx() {
		setTitle("Menu와 JFileChooser 활용 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.add(imageLabel);
		createMenu();
		setSize(350,200);
		setVisible(true);
	}
	
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		
		// Open 메뉴아이템에 Action 리스너를 등록한다.
		openItem.addActionListener(new OpenActionListener());
		fileMenu.add(openItem);
		mb.add(fileMenu);
		setJMenuBar(mb);
	}
	
	// Open 메뉴아이템이 선택되면 호출되는 Action 리스너
	class OpenActionListener implements ActionListener {
		private JFileChooser chooser;
		
		public OpenActionListener() {
			chooser = new JFileChooser(); // 파일 다이얼로그 생성
		}
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images", // 파일 이름난에 출력될 문자열
					"jpg", "gif"); // 파일 필터로 사용되는 확장자. *.jpg. *.gif만 나열됨

			chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정
			int ret = chooser.showOpenDialog(null); // 파일 다이얼로그 출력
			if(ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소 버튼을 누른 경우
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", 
									"경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			// 사용자가 파일을 선택하고 "열기" 버튼을 누른 경우
			String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명 리턴
			imageLabel.setIcon(new ImageIcon(filePath)); // 이미지 출력
			pack(); // 이미지의 크기에 맞추어 프레임 크기 조절
		}
	}
	public static void main(String [] args) {
		new MenuAndFileDialogEx();
	}
}