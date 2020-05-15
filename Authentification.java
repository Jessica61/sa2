package sa2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Authentification {

	private JFrame frame;
	private JTextField txtUtilisateur;
	private JTextField txtMDP;
	public String privilege;//creer ses 2 variables 
	public String monNom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
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
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 522, 462);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUtilisateur.setForeground(Color.BLACK);
		lblUtilisateur.setBounds(42, 110, 126, 34);
		frame.getContentPane().add(lblUtilisateur);
		
		JLabel lblMDP = new JLabel("Mot de Passe");
		lblMDP.setForeground(Color.BLACK);
		lblMDP.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMDP.setBounds(42, 232, 126, 34);
		frame.getContentPane().add(lblMDP);
		
		txtUtilisateur = new JTextField();
		
		txtUtilisateur.setBounds(251, 115, 147, 28);
		frame.getContentPane().add(txtUtilisateur);
		txtUtilisateur.setColumns(10);
		
		txtMDP = new JTextField();
		
		txtMDP.setColumns(10);
		txtMDP.setBounds(251, 237, 147, 28);
		frame.getContentPane().add(txtMDP);
		
		
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username =txtUtilisateur.getText();
				String password=txtMDP.getText();
			
				//System.out.println(username);
				//System.out.println(password);
			
				JLabel alertUser = new JLabel("");
				alertUser.setBounds(303, 99, 56, 16);
				frame.getContentPane().add(alertUser);
				
				JLabel alertPassword = new JLabel("");
				alertPassword.setBounds(303, 160, 240, 26);
				frame.getContentPane().add(alertPassword);
				
				//////release alert message////////
				txtUtilisateur.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						alertUser.setText("");
					}
				});
				
				txtMDP.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						alertPassword.setText("");
					}
				});
		
		
		
//////////validation////////////
		
				if(username.trim().length()==0 && password.trim().length()==0) { //si les deux champs n'ont pas de caractere
					
					alertUser.setText("Le champ utilisateur est vide");
					alertPassword.setText("Le champ mot de passe est vide");
					JOptionPane.showMessageDialog(null, "Les deux champs sont vides");
					
				}else if(username.trim().length()==0) {
					alertUser.setText("Le champ utilisateur est vide");
				
				}else if(password.trim().length()==0) {
					alertPassword.setText("Le champ mot de passe est vide");
				
				}else {
					connectdb db = new connectdb();//variable db de type dbconnect
				
					try{
						
						Connection con = db.db_connect(); //variable db  de type connection fait appel a la mehode db_connect()
						PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE username=? && password=?");
						stmt.setString(1, username);
						stmt.setString(2, password);
						ResultSet rs = stmt.executeQuery();
						
		
						if(rs.next()) { 
							String role=rs.getString(5); //ligne de colonne dans la base de donnee
							privilege=role;
							
							String prenom=rs.getString(3);//pour avoir le prenom de la personne
							JOptionPane.showMessageDialog(null, "Bienvenue"+ prenom);
							monNom=prenom;
							
							frame.dispose();
							ApplicationGestion welcome = new ApplicationGestion(privilege , monNom);//c'est notre dashboard ApplicationGestion de type welcome
								welcome.setVisible(true);
							
						
						
						}else {
							JOptionPane.showMessageDialog(null, "n'existe pas dans la base de donnee");
						}
					
					
					
					
					}catch(Exception error) {
						System.out.print(error);
					}
				
				
				}
		
	
		
		
					}
				});	
		
		
		btnConnexion.setBounds(174, 321, 126, 42);
		frame.getContentPane().add(btnConnexion);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\back.jpg"));
		lblNewLabel.setBounds(0, 0, 504, 417);
		frame.getContentPane().add(lblNewLabel);
		
		
		
	}

}
