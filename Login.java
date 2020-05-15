package sa2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JToolBar;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.UIManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

	private static final Component Login = null;
	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	
	Login.setBounds(161, 390, 212, 41);
	frame.getContentPane().add(Login);
	
	JLabel lblLoginForm = new JLabel("LOGIN FORM");
	lblLoginForm.setForeground(new Color(47, 79, 79));
	lblLoginForm.setBackground(new Color(152, 251, 152));
	lblLoginForm.setFont(new Font("Tahoma", Font.BOLD, 20));
	lblLoginForm.setHorizontalAlignment(SwingConstants.CENTER);
	lblLoginForm.setBounds(38, 0, 432, 51);
	frame.getContentPane().add(lblLoginForm);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setBounds(100, 100, 620, 622);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		username = new JTextField();
	
		username.setBounds(169, 130, 204, 41);
		frame.getContentPane().add(username);
		username.setColumns(10);
	
		/* add action performed*/
		
		JLabel User = new JLabel("Username :");
		User.setFont(new Font("Tahoma", Font.PLAIN, 15));
		User.setHorizontalAlignment(SwingConstants.CENTER);
		User.setBounds(38, 130, 97, 41);
		frame.getContentPane().add(User);
		
		password = new JPasswordField();
		password.setBounds(169, 251, 204, 41);
		frame.getContentPane().add(password);
		
		JLabel Password = new JLabel("Password :");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setBounds(49, 250, 86, 41);
		frame.getContentPane().add(Password);
		
		
		JButton Login = new JButton("Login");
		Login.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
		String Username = username.getText();
			String Password = password.getText();
			
			System.out.print(username);
			System.out.print(password);
			
			JLabel alertusername = new JLabel("New label");
			alertusername.setBounds(226, 185, 56, 16);
			frame.getContentPane().add(alertusername);
			
			JLabel alertpassword = new JLabel("New label");
			alertpassword.setBounds(236, 305, 56, 16);
			frame.getContentPane().add(alertpassword);
			
			username.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					alertusername.setText("");
				}
			});
			
			password.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					alertpassword.setText("");
				}
			});
			
			if(Username.trim().length()==0 && Password.trim().length()==0) {
				alertusername.setText("Empty!!");
				alertpassword.setText("Empty!!");
			}
			
				else if(Username.trim().length()==0){
					JOptionPane.showMessageDialog(null, "Username is empty");
					alertusername.setText("Empty!!");
				}
			
				else if(Password.trim().length()==0){
					JOptionPane.showMessageDialog(null, "Validation is ok");
					alertpassword.setText("Empty!!");
				}
		
		
			else {
			
		connectdb db = new connectdb();
	
		
		try {
			
			Connection con = (Connection) db.db_connect();
			PreparedStatement stmt = 
			(PreparedStatement) con.prepareStatement("SELECT * FROM login WHERE username=? and password=?") ;
			stmt.setString(1, Username);
			stmt.setString(2, Password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null,"Connection to db ok");
				}

			else
				JOptionPane.showMessageDialog(null, "Nom d`utilisateur ou de mot de passe non valide");
			con.close();
		}catch(Exception e) {
			System.out.print(e);
		}
	
			}	
			}
			});
		
	}
}
		
		