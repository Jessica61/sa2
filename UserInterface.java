package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class UserInterface extends JFrame {

	private JPanel contentPane;
	private static JTable Usertable;
	
	private JTextField txtId;
	private JTextField txtPrenom;
	private JTextField txtNom;
	private JTextField txtUtilisateur;
	private JTextField txtRole;
	private JTextField txtEmail;
	private JTextField txtMDP;
	private static JTable tableUser;
	private JComboBox<String> comboBox, comboTypes;
	private JTextField txtSearch;
	public String filterCriteria;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	connectdb db = new connectdb();
	
	public void refreshTable() {
		try {
			Connection con;
			 con = (Connection) db.db_connect();
			PreparedStatement userStmt = con.prepareStatement("Select id,prenom,nom,username,role,email,password from user");
			ResultSet rs = userStmt.executeQuery();
			tableUser.setModel(DbUtils.resultSetToTableModel(rs));
	userStmt.close();
	rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public UserInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1113, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("Gestion Utilisateur");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUser.setBounds(272, 13, 178, 47);
		contentPane.add(lblUser);
		
		JLabel lblId = new JLabel("id");
		lblId.setBounds(58, 83, 135, 22);
		contentPane.add(lblId);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setBounds(58, 135, 135, 22);
		contentPane.add(lblPrenom);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(58, 183, 135, 22);
		contentPane.add(lblNom);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d`utilisateur");
		lblNomDutilisateur.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNomDutilisateur.setForeground(SystemColor.textHighlightText);
		lblNomDutilisateur.setBounds(58, 235, 135, 22);
		contentPane.add(lblNomDutilisateur);
		
		
		
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEmail.setForeground(SystemColor.textHighlightText);
		lblEmail.setBounds(58, 287, 135, 22);
		contentPane.add(lblEmail);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setForeground(SystemColor.textHighlightText);
		lblMotDePasse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMotDePasse.setBounds(58, 337, 135, 22);
		contentPane.add(lblMotDePasse);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		txtId.setBounds(204, 83, 116, 22);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(204, 135, 116, 22);
		contentPane.add(txtPrenom);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(204, 183, 116, 22);
		contentPane.add(txtNom);
		
		txtUtilisateur = new JTextField();
		txtUtilisateur.setColumns(10);
		txtUtilisateur.setBounds(205, 235, 116, 22);
		contentPane.add(txtUtilisateur);
		
		
		

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(205, 287, 116, 22);
		contentPane.add(txtEmail);
		
		txtMDP = new JTextField();
		txtMDP.setColumns(10);
		txtMDP.setBounds(204, 337, 116, 22);
		contentPane.add(txtMDP);
		
		comboBox = new JComboBox();
		comboBox.addItem("Administrateur");
		comboBox.addItem("Utilisateur");
		comboBox.setBounds(37, 415, 294, 22);
		comboBox.setEditable(false);
		contentPane.add(comboBox);
	
		
		
		
		////////================REFRESH========================//////////////////
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			showTable();
			txtPrenom.setText(" ");
			txtNom.setText(" ");
			txtUtilisateur.setText(" ");
			txtEmail.setText(" ");
			txtMDP.setText(" ");
			
				
			}
		});
				
				
		btnActualiser.setBounds(195, 484, 125, 33);
		contentPane.add(btnActualiser);
		
		
		
	//////////////==========================MODIFY============////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id =txtId.getText();
				String prenom =txtPrenom.getText();
				String nom =txtNom.getText();
				String username =txtUtilisateur.getText();
				String email =txtEmail.getText();
				String password =txtMDP.getText();
				String value =comboBox.getSelectedItem().toString();
				
	if(prenom.trim().length()==0 || nom.trim().length()==0 || username.trim().length()==0|| email.trim().length()==0|| password.trim().length()==0) {
					
					JOptionPane.showMessageDialog(null, "Empty txt fields");
				} else {
					
					try {
						Connection con;
						con = (Connection) db.db_connect();
						PreparedStatement stmt = con.prepareStatement("UPDATE user set prenom=?,nom=?,username=?,role=?,email=?,password=? where id=?");
						stmt.setString(1, prenom);
						stmt.setString(2, nom);
						stmt.setString(3, username);
						stmt.setString(4, value);
						stmt.setString(5, email);
						stmt.setString(6, password);
						stmt.setString(7, id);
						stmt.execute();
						JOptionPane.showMessageDialog(null,"Table Modifier");
						showTable();
 
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
				} 
				
			}
		});
		btnModifier.setBounds(37, 484, 125, 33);
		contentPane.add(btnModifier);
		
		
		
		/////////////========================DELETE=================/////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id =txtId.getText();
				String prenom =txtPrenom.getText();
				String nom =txtNom.getText();
				String username =txtUtilisateur.getText();
				String email =txtEmail.getText();
				String password =txtMDP.getText();
				String value =comboBox.getSelectedItem().toString();
				
if(prenom.trim().length()==0 || nom.trim().length()==0 || username.trim().length()==0|| email.trim().length()==0|| password.trim().length()==0) {
					
					JOptionPane.showMessageDialog(null, "Empty txt fields");
				} else {
				

				try {
					Connection con;
					 con = (Connection) db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM user  WHERE id=?");
					stmt.setString(1, id);
					stmt.execute();
					JOptionPane.showMessageDialog(null,"utilisateur effacee");
					showTable();
				}catch(Exception e1) {
					
				}	
			}
		}
	});
		
		btnSupprimer.setBounds(195, 558, 125, 33);
		contentPane.add(btnSupprimer);
		
		
		
		
		//////////////======================BOUTON AJOUTER================//////////////
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String prenom =txtPrenom.getText();
				String nom =txtNom.getText();
				String username =txtUtilisateur.getText();
				String email =txtEmail.getText();
				String password =txtMDP.getText();
				String value =comboBox.getSelectedItem().toString();
				
				if(prenom.trim().length()==0 || nom.trim().length()==0 || username.trim().length()==0|| email.trim().length()==0|| password.trim().length()==0) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs");
				} else {
					
				
				
			
			try {
				Connection con;
				 con =(Connection) db.db_connect();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO user(prenom,nom,username,role,email,password) VALUES(?,?,?,?,?,?)");
				
				stmt.setString(1, prenom);
				stmt.setString(2, nom);
				stmt.setString(3, username);
				stmt.setString(4, value);
				stmt.setString(5, email);
				stmt.setString(6, password);
				stmt.execute();
				JOptionPane.showMessageDialog(null,"utilisateur ajoutee");
				showTable();
				
			}catch(Exception e1) {
				System.out.print(e1);
				
			}
			
			}
		}
			
		});		
		
		btnAjouter.setBounds(128, 604, 125, 33);
		contentPane.add(btnAjouter);
		
		
		//////////////============RECHERCHER=============/////////
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(37, 558, 125, 33);
		contentPane.add(btnRechercher);
		
		
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(455, 135, 601, 466);
		contentPane.add(scrollPane);
		
		tableUser= new JTable() {
			//////////////////step 1 make rows not editable///////
			public boolean isCellEditable(int row, int column) {
				return false; ////on ne veut pas que les row et colonne soit modifier donc on retourne faux
			}
				
			
		};
		scrollPane.setViewportView(tableUser);
		
	
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				connectdb db = new connectdb();
				try {
					String searchObject=txtSearch.getText();
					Connection con;
					con = (Connection) db.db_connect();
					PreparedStatement stmt = con.prepareStatement("SELECT id AS '#', nom as 'Nom', prenom as 'Prenom', username as 'Username', email as 'Email' FROM user WHERE "+filterCriteria+" LIKE ?");
					stmt.setString(1, "%"+ searchObject + "%");
					
					stmt.setString(1, txtSearch.getText());
				ResultSet rs = stmt.executeQuery();
				
				tableUser.setModel(DbUtils.resultSetToTableModel(rs));///////relier au base de donnees
				}catch(Exception e) {
				System.out.println(e);
				
			

				}
			}
		}
	);
				
		txtSearch.setBounds(538, 13, 142, 33);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		
		
		////////==================FILTER===============//////////////////////////
		comboTypes = new JComboBox();
		
		comboTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Object selected = comboTypes.getSelectedItem();
				if(selected.toString().equals("Nom"))
					filterCriteria="nom";
				
				if(selected.toString().equals("Prenom"))
					filterCriteria="prenom";
				
				if(selected.toString().equals("Email"))
					filterCriteria="email";
			}
		});
		
				
		comboTypes.setBounds(739, 13, 181, 36);
		contentPane.add(comboTypes);
		
		comboTypes.setModel(new DefaultComboBoxModel<String>(new String[] {"","Nom","Prenom","Email"}));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\user.jpg"));
		label.setBounds(0, 0, 1095, 653);
		contentPane.add(label);
		
		
		
		///////////step 2 make row clickable//////
	tableUser.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			try {
				int row = tableUser.getSelectedRow();
				System.out.print(row); //////getSelectedRow() donne l`indice de chaque ranger
				
				String Clicktable = (tableUser.getModel().getValueAt(row, 0).toString());
		////		System.out.print(tableClick);
				
				
				Connection con;
				con = (Connection) db.db_connect();
				PreparedStatement callInfo = con.prepareStatement("SELECT * FROM user WHERE id='"+Clicktable+"'"); //////faire la concatenation avec le nom du variable clicktable
				ResultSet rs = callInfo.executeQuery();
				if(rs.next()) {
					
					String data1 = rs.getString("id");
					String data2 = rs.getString("nom");
					String data3 = rs.getString("prenom");
					String data4 = rs.getString("username");
					String data5 = rs.getString("role");
					String data6 = rs.getString("email");
					String data7 = rs.getString("password");
					
					
					System.out.print(data1);
					System.out.print(data2);
					System.out.print(data3);
					System.out.print(data4);
					System.out.print(data5);
					System.out.print(data6);
					System.out.print(data7);
					System.out.println("_______________________________");

					txtId.setText(data1);
					txtPrenom.setText(data2);
					txtNom.setText(data3);
					txtUtilisateur.setText(data4);
					txtRole.setText(data5);
					txtEmail.setText(data6);
					txtMDP.setText(data7);
					comboBox.setSelectedItem(data5);
					
				}
				
				
			}catch(Exception e1) {
				System.out.print(e1);
			}
			
		}
	});
	
	}	
	
	
		public static void showTable() {
			
			connectdb db = new connectdb();
			
			try {
				Connection con;
				con = (Connection) db.db_connect();
				PreparedStatement userStmt = con.prepareStatement("SELECT id AS '#', nom as 'Nom', prenom as 'Prenom', username as 'Username', email as 'Email' FROM user");
			//////////////pour renomer le nom de colonne dans ma base de donnees
			ResultSet rs = userStmt.executeQuery();///////////l`execution du query se fait ici...
			tableUser.setModel(DbUtils.resultSetToTableModel(rs));///////on passe en parametre le resultsetj
			
			}catch (Exception e) {
				System.out.print(e);
			}
			
		
		
}
			
		
			
	}
			
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
