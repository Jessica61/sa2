package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.SystemColor;

public class clientInterface extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomC;
	private JTextField txtPrenomC;
	private JTextField txtage;
	private JTextField txtEmail;
	private JComboBox<String> comboSex, comboPays;
	private JTextField txtIdClient;
	private static JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientInterface frame = new clientInterface();
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
			PreparedStatement reserStmt = con.prepareStatement("Select id_client, nom,  prenom, sexe, age, email, pays from client");
			ResultSet rs = reserStmt.executeQuery();
		
	reserStmt.close();
	rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public clientInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 818);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomC = new JLabel("Nom:");
		lblNomC.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNomC.setBounds(26, 203, 94, 24);
		contentPane.add(lblNomC);
		
		JLabel lblsex = new JLabel("Sexe:");
		lblsex.setForeground(SystemColor.text);
		lblsex.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblsex.setBounds(26, 311, 94, 24);
		contentPane.add(lblsex);
		
		txtNomC = new JTextField();
		txtNomC.setBounds(123, 196, 187, 31);
		contentPane.add(txtNomC);
		txtNomC.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Prenom:");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNewLabel.setBounds(26, 254, 116, 24);
		contentPane.add(lblNewLabel);
		
		txtPrenomC = new JTextField();
		txtPrenomC.setColumns(10);
		txtPrenomC.setBounds(123, 255, 187, 31);
		contentPane.add(txtPrenomC);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setForeground(SystemColor.textHighlightText);
		lblAge.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblAge.setBounds(26, 430, 73, 20);
		contentPane.add(lblAge);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(123, 430, 187, 31);
		contentPane.add(txtage);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(SystemColor.textHighlightText);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblEmail.setBounds(26, 367, 73, 24);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(123, 367, 187, 36);
		contentPane.add(txtEmail);
		
		JLabel lblPays = new JLabel("Pays:");
		lblPays.setForeground(SystemColor.textHighlightText);
		lblPays.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblPays.setBounds(26, 501, 73, 20);
		contentPane.add(lblPays);
		
		comboPays = new JComboBox();
		comboPays.addItem("");
		comboPays.addItem("France");
		comboPays.addItem("Maurice");
		comboPays.addItem("Espagne");
		comboPays.addItem("Australie");

		comboPays.setEditable(false);
		comboPays.setBounds(123, 485, 187, 36);
		contentPane.add(comboPays);
		
		
		

		JLabel lblTitre = new JLabel("Gestion de Client");
		lblTitre.setForeground(Color.BLUE);
		lblTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 29));
		lblTitre.setBounds(271, 63, 316, 31);
		contentPane.add(lblTitre);
		
	  comboSex = new JComboBox();
	  comboSex.addItem("");
	  comboSex.addItem("Female");
	  comboSex.addItem("Male");
	  
	  comboSex.setBounds(123, 311, 187, 36);
	  comboSex.setEditable(false);
      contentPane.add(comboSex);
		
		JLabel lblIdClient = new JLabel("Id_Client:");
		lblIdClient.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblIdClient.setBounds(26, 145, 94, 24);
		contentPane.add(lblIdClient);
		
		txtIdClient = new JTextField();
		txtIdClient.setEditable(false);
		txtIdClient.setColumns(10);
		txtIdClient.setBounds(123, 138, 187, 31);
		contentPane.add(txtIdClient);
		
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			////collecter des infos depuis le textfield//////
				
				String nom= txtNomC.getText();
				String prenom = txtPrenomC.getText();
				String sexe = comboSex.getSelectedItem().toString();
				String age = txtage.getText();
				String email = txtEmail.getText();
				String pays =comboPays.getSelectedItem().toString();


					
				if( nom.trim().length()==0 || prenom.trim().length()==0 || sexe.trim().length()==0 || age.trim().length()==0  || email.trim().length()==0 || pays.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
					
					}			
			
				
				else {
					
					try {
						Connection con;
						 con =(Connection) db.db_connect();		
						 
						PreparedStatement stmt = con.prepareStatement("INSERT INTO client (nom, prenom, sexe, age, email, pays) VALUES(?,?,?,?,?,?)");
						
						stmt.setString(1, nom);
						stmt.setString(2, prenom);
						stmt.setString(3, sexe);
						stmt.setString(4, age);
						stmt.setString(5, email);
						stmt.setString(6, pays);
						stmt.execute();
						showTable();
						JOptionPane.showMessageDialog(null,"Utilisateur Enregistrer");
						
						
//						ResultSet rs= stmt.getGeneratedKeys();
//						if(rs.next()) {
//							id_client=rs.getString(6);
//							System.out.println(id_client);
//						}			
						
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
					
					}
				}
					
				});		
				
				
		btnEnregistrer.setBackground(SystemColor.controlHighlight);
		btnEnregistrer.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnEnregistrer.setBounds(12, 630, 116, 36);
		contentPane.add(btnEnregistrer);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String id_client =txtIdClient.getText();
				String nom = txtNomC.getText();
				String prenom = txtPrenomC.getText();
				String sexe = comboSex.getSelectedItem().toString();
				String age = txtage.getText();
				String email = txtEmail.getText();
				String pays = comboPays.getSelectedItem().toString();

				
if( nom.trim().length()==0 || prenom.trim().length()==0 || sexe.trim().length()==0 || age.trim().length()==0  || email.trim().length()==0 || pays.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
			} else 
				
			{
				try {
					Connection con;
					 con =(Connection) db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM client WHERE id_client=?");
					stmt.setString(1, id_client);
					stmt.execute();
					showTable();
					
					JOptionPane.showMessageDialog(null,"Ligne effacer avec succées");
					
				}catch(Exception e1) {
				}
			}
			}
		});
		btnSupprimer.setBackground(SystemColor.controlHighlight);
		btnSupprimer.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnSupprimer.setBounds(177, 630, 116, 35);
		contentPane.add(btnSupprimer);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(68, 92, 755, 2);
		contentPane.add(separator_2);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnModifier.setBackground(SystemColor.controlHighlight);
		btnModifier.setBounds(12, 695, 116, 36);
		contentPane.add(btnModifier);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showTable();
				txtNomC.setText(" ");
				txtPrenomC.setText(" ");
				txtage.setText(" ");
				txtEmail.setText(" ");
//				comboSex.setToolTipText(" ");
//				comboPays.setToolTipText(" ");
			}
		});
		
		btnActualiser.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnActualiser.setBackground(SystemColor.controlHighlight);
		btnActualiser.setBounds(177, 695, 116, 36);
		contentPane.add(btnActualiser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(351, 123, 702, 606);
		contentPane.add(scrollPane);
		
		
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int row = table.getSelectedRow();
					System.out.print(row); //////getSelectedRow() donne l`indice de chaque ranger
					
					String Clicktable = (table.getModel().getValueAt(row, 0).toString());
//					System.out.print(Clicktable);
					
					
					Connection con;
					con = (Connection) db.db_connect();
					PreparedStatement callInfo = con.prepareStatement("SELECT * FROM client WHERE id_client='"+Clicktable+"'"); //////faire la concatenation avec le nom du variable clicktable
					ResultSet rs = callInfo.executeQuery();
					if(rs.next()) {
						
						String data1 = rs.getString("id_client");
						String data2 = rs.getString("nom");
						String data3 = rs.getString("prenom");
						String data4 = rs.getString("sexe");
						String data5 = rs.getString("age");
						String data6 = rs.getString("email");
						String data7 = rs.getString("pays");

//						System.out.print(data2);
//			
						txtIdClient.setText(data1);
						txtNomC.setText(data2);
						txtPrenomC.setText(data3);
						txtage.setText(data5);
						txtEmail.setText(data6);
						comboSex.setSelectedItem(data4);
						comboPays.setSelectedItem(data7);

					}
					
					
				}catch(Exception e1) {
					System.out.print(e1);
				}
				
			}
			
		});
		
		
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\cli.jpg"));
		label.setBounds(0, 0, 1082, 771);
		contentPane.add(label);
		
		

	
	}
		
		
		
		
////////////////////////////////////++++++++++++++++++++la fonction showTable+++++++++++++++++++++++++++++++///////////////////////////////////////////
		
	public static void showTable() {

		connectdb db = new connectdb();

		try {
			Connection con;
			con = (Connection) db.db_connect();
			PreparedStatement userStmt = con.prepareStatement("SELECT id_client AS '#', nom as 'Nom', prenom as 'Prenom', sexe as 'Sexe', age as 'Age', email as 'Email', pays as 'Pays' FROM client ");	
//////////////pour renomer le nom de colonne dans ma table en referant aux noms donnees dans la base de donnees SAMIRA======

			ResultSet rs = userStmt.executeQuery();///////////l`execution du query se fait ici...
			table.setModel(DbUtils.resultSetToTableModel(rs));///////on passe en parametre le resultset 
			
		}catch (Exception e) {
			System.out.print(e);
			}
		}
	
	
	
	
	
	
	
	

	
	
	
}
