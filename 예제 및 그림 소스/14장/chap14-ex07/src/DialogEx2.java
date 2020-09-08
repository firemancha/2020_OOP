import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyModalDialog extends JDialog {
	private JTextField tf = new JTextField(10);
	private JButton okButton = new JButton("OK");
	
	public MyModalDialog(JFrame frame, String title) {
		super(frame, title, true); // true는 모달 타입을 만들도록 지시
		setLayout(new FlowLayout());
		add(tf);
		add(okButton);
		setSize(200, 100);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 다이얼로그를 닫는다. 라인 44에서 리턴하게 한다..
			}
		});
	}
	// 텍스트필드 창에 사용자가 입력한 문자열을 리턴한다.
	// 입력된 내용이 없으면 null을 리턴한다.
	public String getInput() { 
		if(tf.getText().length() == 0) return null;
		else return tf.getText();
	}
}

public class DialogEx2 extends JFrame{
	private MyModalDialog dialog; // 다이얼로그의 레퍼런스
	
	public DialogEx2() {
		super("DialogEx2 예제 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btn  = new JButton("Show Modal Dialog");

		// 모달 다이얼로그 생성
		dialog = new MyModalDialog(this, "Test Modal Dialog");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true); // 모달 다이얼로그 작동 시작
				
				// 다이얼로그로부터 사용자가 입력한 문자열을 받아 온다.
				String text = dialog.getInput();
				
				if(text == null) return; // 입력한 문자열이 없는 경우
				JButton btn = (JButton)e.getSource();
				btn.setText(text); // 입력한 문자열로 이 버튼의 문자열을 변경한다.
			}
		});
		getContentPane().add(btn);
		setSize(250,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new DialogEx2();
	}
}