import java.io.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageDB extends JFrame implements ActionListener {
	private JMenuItem save, view, exit;
	private JFileChooser fc;
	private Statement stmt = null;
	private Connection conn = null;
	private JMenuBar menuBar;
	private JMenu menu;
	private int numberOfRecord;
	private JLabel imageLabel;
	private JLabel textLabel;
	private JButton nextButton;
	private ResultSet viewRS=null;
	private ImageIcon img=null;
	
	public ImageDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/photodb", "root","1234"); // JDBC 연결
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			ResultSet srs;
			srs = stmt.executeQuery("select count(*) from images"); // 레코드 개수를 얻어오는 쿼리
			srs.next();
			numberOfRecord = srs.getInt(1);
		} catch (ClassNotFoundException e) {
			handleError(e.getMessage());
		} catch (SQLException e) {
			handleError(e.getMessage());
		}
		
		setTitle("이미지 데이터 베이스"); // 프레임 타이틀
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 종료 버튼(X)을 클릭하면 프로그램 종료
		
		Container c = getContentPane();
		
		c.setLayout(new BorderLayout()); //BorderLayout 배치관리자의 사용
		fc = new JFileChooser(); // JFileChooser 객체 생성
		menuBar = new JMenuBar(); // JMenuBar 컴포넌트를 생성
		menu = new JMenu("메뉴");
		menuBar.add(menu); // 메뉴를 메뉴바에 붙인다

		// 서브 메뉴
		save = new JMenuItem("사진 저장");
		save.addActionListener(this); // save 메뉴에 대한 이벤트 리스너를 등록
		menu.add(save); // 메뉴 아이템을 메뉴에 붙인다
		view = new JMenuItem("모든 사진 보기");
		menu.add(view); // 메뉴 아이템을 메뉴에 붙인다
		view.addActionListener(this); // view 메뉴에 대한 이벤트 리스너를 등록
		menu = new JMenu("종료");
		exit = new JMenuItem("나가기");
		exit.addActionListener(this); // exit 메뉴에 대한 이벤트 리스너를 등록
		menu.add(exit); // 메뉴 아이템을 메뉴에 붙인다
		menuBar.add(menu); // 메뉴를 메뉴바에 붙인다
		setJMenuBar(menuBar); // 메뉴바를 프레임에 붙인다
		
		imageLabel = new JLabel(); // 이미지를 표시할 레이블 컴포넌트
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER); // 중앙정렬
		textLabel = new JLabel(); // 파일 이름을 표시할 레이블 컴포넌트
		textLabel.setHorizontalAlignment(SwingConstants.CENTER); // 중앙정렬
		nextButton = new JButton("다음 사진"); // 다음 사진 보기를 위한 버튼
		nextButton.addActionListener(this); // 버튼에 대한 이벤트 리스너를 등록
		JScrollPane spane = new JScrollPane(imageLabel); // 큰 사이즈의 이미지를 위해  ScrollPane에서 표시
		c.add(textLabel,BorderLayout.NORTH);
		c.add(spane,BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setSize(50,50); // 버튼 크기
		panel.add(nextButton);
		c.add(panel,BorderLayout.SOUTH);
		
		setSize(400, 400); // 폭 400 픽셀, 높이 400 픽셀의 크기로 프레임 크기 설정
		setVisible(true); // 프레임이 화면에 나타나도록 설정
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) { // 종료
			System.exit(0);
		} else if (e.getSource() == save) { // 사진을 DB에 추가
			int returnVal = fc.showOpenDialog(this); // 파일 열기 다이얼로그 출력
			if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기 버튼을 누른 경우
				insertImage(fc.getSelectedFile()); // 선택한 파일을 저장
			}
		}
		else if (e.getSource() == view) { // 사진 보기
			showPhotos();
		} else if (e.getSource() == nextButton) { // 다음 사진 버튼
			try {
				if (viewRS == null || !viewRS.next()) { // 볼 사진이 없는 경우
					imageLabel.setIcon(null);
					imageLabel.setText("사진 없음");
					textLabel.setText(null);
				} else {
					Blob b = viewRS.getBlob("FILE"); // DB에서 바이너리 데이터 얻어옴
					img = new ImageIcon(b.getBytes(1, (int) b.length())); // 바이너리 데이터를 이미지 포맷으로 변환
					imageLabel.setIcon(img);
					imageLabel.setText(null);
					textLabel.setText(viewRS.getString("FILENAME")); // 파일 이름을 textLabel에 출력
				}
			} catch (SQLException e1) {
				handleError(e1.getMessage());
			}
		}
	}
	
	private void showPhotos() {
		try {
			viewRS = stmt.executeQuery("select * from images"); // DB에서 모든 사진을 얻어옴
			if (viewRS.next()) { // 첫번째 사진 표시
				Blob b = viewRS.getBlob("FILE"); // DB에서 바이너리 데이터 얻어옴
				img = new ImageIcon(b.getBytes(1, (int) b.length())); // 바이너리 데이터를 이미지 포맷으로 변환
				imageLabel.setIcon(img);
				imageLabel.setText(null);
				textLabel.setText(viewRS.getString("FILENAME")); // 파일 이름을 textLabel에 출력
			} else { // DB에 사진이 없는 경우
				imageLabel.setIcon(null);
				imageLabel.setText("사진 없음");
				textLabel.setText(null);
			}
		} catch (SQLException e) {
			handleError(e.getMessage());
		}
	}

	private static void handleError(String string) {
		System.out.println(string);
		System.exit(1);
	}
	
	private void insertImage(File file) {
        try{
            FileInputStream fin = new FileInputStream(file); // 파입 입력 스트림 생성
            PreparedStatement pre = conn.prepareStatement("insert into images (ID, FILENAME, FILE) VALUES (?, ?, ?)");
            pre.setInt(1,numberOfRecord++);
            pre.setString(2,file.getName());
            pre.setBinaryStream(3,fin,(int)file.length());
            pre.executeUpdate(); // DB에 사진 저장
            pre.close(); 
        } catch (Exception e){
            handleError(e.getMessage());
        }
	}

	public static void main(String[] args) {
		ImageDB frame = new ImageDB();
	}
}
