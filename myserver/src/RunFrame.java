import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RunFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	private int width = 400;
	private int height = 400;
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height/3;
	private Welcome welcome;
	private JPanel panel;
	public RunFrame(String tcpPort,String udpPort) throws IOException{
		setSize(width, height);
		setLocation(x, y);
		welcome = new Welcome(tcpPort, udpPort);
		new Thread(welcome).start();
		
		panel = new JPanel();
		JButton button = new JButton("Quit");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				welcome.quit();
				System.exit(0);
			}
		});
		panel.add(button);
		add(panel);
	}
}
