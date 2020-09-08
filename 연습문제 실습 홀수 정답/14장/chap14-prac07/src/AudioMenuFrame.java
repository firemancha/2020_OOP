import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AudioMenuFrame extends JFrame {
	private JFileChooser chooser = null;
	private Clip clip = null;
	private AudioInputStream audioStream = null;
	private JLabel msgLabel = new JLabel("오디오 파일을 선택하세요");
	
	public AudioMenuFrame() {
		super("오디오 파일을 찾아 연주/종료 제어");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		msgLabel.setFont(new Font("Gothic", Font.PLAIN, 15));
		msgLabel.setHorizontalAlignment(JLabel.CENTER);
		c.add(msgLabel);
		
		createMenu();

		setSize(450,150);
		setVisible(true);
	}
	
	private void createMenu() {
		JMenu audioMenu = new JMenu("오디오");
		JMenuItem playItem = new JMenuItem("연주");
		audioMenu.add(playItem);

		JMenuItem stopItem = new JMenuItem("종료");
		audioMenu.add(stopItem);
	
		JMenuBar mb = new JMenuBar();
		mb.add(audioMenu);
		setJMenuBar(mb);
		
		playItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = chooseFile();
				if(filePath == null)
					return; // 파일이 선택되지 않았음
				
				if(clip != null && clip.isActive())
					clip.close();

				playAudio(filePath);
				msgLabel.setText(new File(filePath).getName() + " 를 연주하고 있습니다.");
			}
		});
		
		stopItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(clip != null && clip.isActive()) {
					clip.close();
					msgLabel.setText("연주를 종료합니다.");					
				}
			}
		});

	}
	
	private String chooseFile() {
		if(chooser == null) // 처음이면, 아니면 이전에 만든 chooser 이용
			chooser= new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Audio Files(wav,au, mid, rmf)", "wav", "au", "mid",  "rmf");
	    chooser.setFileFilter(filter);
		int ret = chooser.showOpenDialog(AudioMenuFrame.this);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(AudioMenuFrame.this, "파일을 선택하지 않았습니다", 
						"경고", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		String filePath = chooser.getSelectedFile().getPath();
		return filePath;
	}
	
	private void playAudio(String pathName) {
		try {
			File audioFile = new File(pathName); // 오디오 파일의 경로명
			audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 파일로부터
			
			clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
			clip.addLineListener(new MyLineListener());
			clip.open(audioStream); // 재생할 오디오 스트림 열기
			clip.start(); // 재생 시작
		}
		catch (LineUnavailableException e) { e.printStackTrace(); }
		catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
	}
	
	class MyLineListener implements LineListener {
		@Override
		public void update(LineEvent e) {
			if (e.getType() == LineEvent.Type.STOP) { // clip.stop()이 호출되거나 재생이 끝났을 때
				try {
					audioStream.close(); // 현재 연주되는 오디오 스트림 닫기
					msgLabel.setText("연주를 종료하였습니다.");			
				} catch (IOException e1) {
					e1.printStackTrace();		
				}
            }
		}
	}
	static public void main(String[] arg) {
		new AudioMenuFrame();
	}
	
	
}

