package sa2;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class reserverForm extends ReservationInterface{

	JFrame frame;
	private JTextField txtAdulte;
	private JTextField txtEnfant;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JComboBox<String> comboGenre, comboPays, comboStatut;

	
	
	
	connectdb db = new connectdb();
	
	private JTextField txtCategorie;
	private JTextField txtIdClient;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserverForm window = new reserverForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					ReservationInterface.showTable();;
					clientInterface.showTable();
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public reserverForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1087, 757);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDateChooser dateDeb = new JDateChooser();
		dateDeb.setDateFormatString("YYYY-MM-DD");
		dateDeb.setBounds(160, 124, 176, 35);
		frame.getContentPane().add(dateDeb);
		
		JDateChooser dateFin = new JDateChooser();
		dateFin.setDateFormatString("YYYY-MM-DD");
		dateFin.setBounds(160, 204, 176, 35);
		frame.getContentPane().add(dateFin);
		
		JLabel lblArrivee = new JLabel("Arrivee");
		lblArrivee.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArrivee.setBounds(26, 124, 137, 28);
		frame.getContentPane().add(lblArrivee);
		
		JLabel lblDepart = new JLabel("Depart");
		lblDepart.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDepart.setBounds(26, 204, 137, 28);
		frame.getContentPane().add(lblDepart);
		
		JLabel lblAdulte = new JLabel("NbAdulte");
		lblAdulte.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdulte.setBounds(26, 271, 137, 28);
		frame.getContentPane().add(lblAdulte);
		
		JLabel lblEnfant = new JLabel("NbEnfant");
		lblEnfant.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnfant.setBounds(26, 333, 137, 28);
		frame.getContentPane().add(lblEnfant);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(623, 204, 137, 28);
		frame.getContentPane().add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrenom.setBounds(623, 271, 137, 28);
		frame.getContentPane().add(lblPrenom);
		
		JLabel lblSexe = new JLabel("Sexe");
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSexe.setBounds(623, 333, 137, 28);
		frame.getContentPane().add(lblSexe);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAge.setBounds(623, 413, 137, 28);
		frame.getContentPane().add(lblAge);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(623, 477, 137, 28);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPays = new JLabel("Pays");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPays.setBounds(623, 548, 137, 28);
		frame.getContentPane().add(lblPays);
		
		txtAdulte = new JTextField();
		txtAdulte.setBounds(160, 275, 176, 35);
		frame.getContentPane().add(txtAdulte);
		txtAdulte.setColumns(10);
		
		txtEnfant = new JTextField();
		txtEnfant.setColumns(10);
		txtEnfant.setBounds(160, 337, 176, 35);
		frame.getContentPane().add(txtEnfant);
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		txtNom.setBounds(718, 204, 176, 35);
		frame.getContentPane().add(txtNom);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(718, 269, 176, 35);
		frame.getContentPane().add(txtPrenom);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(718, 406, 176, 35);
		frame.getContentPane().add(txtAge);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(718, 475, 176, 35);
		frame.getContentPane().add(txtEmail);

		
		JLabel lblCategorie = new JLabel("Categorie");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCategorie.setBounds(26, 408, 137, 28);
		frame.getContentPane().add(lblCategorie);
		
		txtCategorie = new JTextField();
		txtCategorie.setColumns(10);
		txtCategorie.setBounds(160, 406, 176, 35);
		frame.getContentPane().add(txtCategorie);
		
		comboGenre = new JComboBox();
		comboGenre.addItem(" ");
		comboGenre.addItem("Female");
		comboGenre.addItem("Male");
		comboGenre.setBounds(718, 331, 169, 35);
		frame.getContentPane().add(comboGenre);
		
		comboPays = new JComboBox();
		comboPays.addItem("");
		comboPays.addItem("France");
		comboPays.addItem("Maurice");
		comboPays.addItem("Espagne");
		comboPays.addItem("Australie");
		comboPays.setBounds(718, 541, 176, 35);
		frame.getContentPane().add(comboPays);

		
		JLabel lblIdclient = new JLabel("Id_Client");
		lblIdclient.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdclient.setBounds(623, 131, 137, 28);
		frame.getContentPane().add(lblIdclient);
		
		txtIdClient = new JTextField();
		txtIdClient.setEditable(false);
		txtIdClient.setColumns(10);
		txtIdClient.setBounds(718, 128, 176, 35);
		frame.getContentPane().add(txtIdClient);
		
		JComboBox comboStatut = new JComboBox();
		comboStatut.addItem(" ");
		comboStatut.addItem("Occupé");
		comboStatut.addItem("Libre");
		comboStatut.addItem("Hors Service");
		comboStatut.setBounds(160, 481, 176, 35);
		frame.getContentPane().add(comboStatut);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStatut.setBounds(26, 477, 137, 28);
		frame.getContentPane().add(lblStatut);
		
		
///////////////////////////==============================================NEW==========================================/==///////////////////////////////////////////////		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String date_arrivee =((JTextField)dateDeb.getDateEditor().getUiComponent()).getText();
				String date_depart =((JTextField)dateFin.getDateEditor().getUiComponent()).getText();
				String adulte = txtAdulte.getText();
				String enfant = txtEnfant.getText();
				String id_categorie = txtCategorie.getText();
//				String id_client = txtIdClient.getText();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String age = txtAge.getText();
				String email = txtEmail.getText();
				String sexe =comboGenre.getSelectedItem().toString();
				String pays =comboPays.getSelectedItem().toString();

				
				
				if( nom.trim().length()==0 || prenom.trim().length()==0 || sexe.trim().length()==0 || age.trim().length()==0 || email.trim().length()==0 || pays.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
					
					}			
			
				
				else {
					
					try {
						Connection con;
						 con =(Connection) db.db_connect();
						 String id_client= null;
						PreparedStatement stmt = con.prepareStatement("INSERT INTO client ( nom, prenom, sexe, age, email, pays) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
						
						stmt.setString(1, nom);
						stmt.setString(2, prenom);
						stmt.setString(3, sexe);
						stmt.setString(4, age);
						stmt.setString(5, email);
						stmt.setString(6, pays);
						stmt.executeUpdate();
						clientInterface.showTable();
						
						ResultSet rs= stmt.getGeneratedKeys();
						if(rs.next()) {
							id_client=rs.getString(1);
							System.out.println(id_client);
						}				
						PreparedStatement stm = con.prepareStatement("INSERT INTO reservation (date_arrivee, date_depart, adulte, enfant, id_categorie, id_client) VALUES(?,?,?,?,?,?)");
						
						stm.setString(1, date_arrivee);
						stm.setString(2, date_depart);
						stm.setString(3, adulte);
						stm.setString(4, enfant);
						stm.setString(5, id_categorie);
						stm.setString(6, id_client);
						stmt.executeUpdate();
						ReservationInterface.showTable();
						JOptionPane.showMessageDialog(null,"Utilisateur Enregistrer");
						
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
					
					}
				}
					
		
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAjouter.setBounds(362, 619, 137, 35);
		frame.getContentPane().add(btnAjouter);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\re.jpg"));
		lblNewLabel.setBounds(0, 0, 1069, 710);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		
	
	}

	
		
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
	}
