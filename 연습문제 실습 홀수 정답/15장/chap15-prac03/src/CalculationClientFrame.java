import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class CalculationClientFrame extends JFrame {
	private JTextField startTf = new JTextField(7);
	private JTextField operatorTf = new JTextField(3);
	private JTextField endTf = new JTextField(7);
	private JTextField resTf = new JTextField(7);
	private JButton calcBtn = new JButton("계산");
	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	
	public CalculationClientFrame() {
		super("계산 클라이언트");
		setSize(410, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임 종료 버튼(X)을 클릭하면 프로그램 종료
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(startTf);
		c.add(operatorTf);
		c.add(endTf);
		c.add(new JLabel(" = "));
		c.add(resTf);
		c.add(calcBtn);
		
		setVisible(true);
	
		setupConnection();
		
		calcBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String startText = startTf.getText().trim();
					String operatorText = operatorTf.getText().trim();
					String endText = endTf.getText().trim();					
					if(startText.length() == 0 || operatorText.length() == 0 || endText.length() == 0)
						return; // 입력되지 않았음
					
					out.write(startText+"\n");
					out.write(operatorText+"\n");
					out.write(endText+"\n");
					out.flush();
					
					String result = in.readLine();
					resTf.setText(result);
				} catch (IOException e) {
					System.out.println("클라이언트 : 서버로부터 연결 종료");
					return;
					// e.printStackTrace();
				}
				
			}
			
		});
	}
	
	public void setupConnection() {
		try {
			socket = new Socket("localhost", 9998);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new CalculationClientFrame();
	}

}
