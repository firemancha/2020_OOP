import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AudioEx extends JFrame {
    private JButton btns[] = { new JButton("play"), new JButton("stop"),
                                new JButton("play again")};
    private Clip clip;

    public AudioEx() {
    	setTitle("오디오 제어");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        MyActionListener al = new MyActionListener();
        for(int i=0; i<btns.length; i++) {
            c.add(btns[i]);
            btns[i].addActionListener(al);
        }
        setVisible(true);
        loadAudio("audio/애국가1절.wav");
    }

    private void loadAudio(String pathName) {
        try {
            clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
            File audioFile = new File(pathName); // 오디오 파일의 경로명
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 파일로부터
            clip.open(audioStream); // 재생할 오디오 스트림 열기
        }
        catch (LineUnavailableException e) { e.printStackTrace(); }
        catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }

    }
    
    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "play": clip.start(); // 오디오 재생 시작
                    break;
                case "stop": clip.stop(); // 오디오 재생 중단
                    break;
                case "play again":
                    clip.setFramePosition(0); // 재생 위치를 첫 프레임으로 변경
                    clip.start(); // 오디오 재생 시작
                    break;
            }
        }
    }
    
    public static void main(String [] args) {
        new AudioEx();
    }
}