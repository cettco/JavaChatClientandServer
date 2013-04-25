import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class RegisterFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	private int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height/3;
	private JTextField usernameField;
	private JTextField pwdField;
	private JTextField comfirmField;
	private JButton registerbButton;
	private ButtonGroup group;
	private JRadioButton man;
	private JRadioButton woman;
	public RegisterFrame(){
		setSize(400, 400);
		setLocation(x, y);
		setLayout(null);
		setResizable(false);
		setTitle("Register");
		usernameField = new JTextField();
		pwdField = new JTextField();
		comfirmField = new JTextField();
		usernameField.setBounds(180, 100, 150, 30);
		pwdField.setBounds(180, 150, 150, 30);
		comfirmField.setBounds(180,200,150,30);
		add(usernameField);
		add(pwdField);
		add(comfirmField);
		
		JLabel l1 = new JLabel("UserName:");
		JLabel l2 = new JLabel("PassWord:");
		JLabel l3 = new JLabel("Comfirm:");
		l1.setBounds(50, 100, 100, 30);
		l2.setBounds(50, 150, 100, 30);
		l3.setBounds(50, 200, 100, 30);
		add(l1);
		add(l2);
		add(l3);
		
		registerbButton = new JButton("Register");
		registerbButton.setBounds(200, 250, 100, 30);
		registerbButton.addActionListener(new registerAction());
		add(registerbButton);
		
		man = new JRadioButton("Man");
		man.setBounds(50, 250, 80, 30);
		woman = new JRadioButton("Woman");
		woman.setBounds(50, 290, 80, 30);
		group = new ButtonGroup();
		group.add(man);
		group.add(woman);
		add(man);
		add(woman);
	}
	class registerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String username = usernameField.getText();
			String password = pwdField.getText();
			String confirmpwd = comfirmField.getText();
			if(password.equals(confirmpwd))
			{			    
			    TCPRegister reg = new TCPRegister();
				reg.process(username, password);
			}
			else{
				JOptionPane.showMessageDialog(null,"The passwords you input are not the same.");
			}
		}
		
	}
}
