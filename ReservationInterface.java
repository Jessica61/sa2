package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;

public class ReservationInterface extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtAdulte;
	private JTextField txtChambre;
	private JComboBox<String> comboBoxstatut;
	private JTextField txtEnfant;
	private JTextField txtIdClient;
	private static JTable tableReser;
	private JDateChooser dateDeb;
	private JDateChooser dateFin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationInterface frame = new ReservationInterface();
					frame.setVisible(true);
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	connectdb db = new connectdb();
	private JTextField txtClient;
	protected String idClient;
	protected String idReserved;
	protected String cnom;
	protected String cprenom;

	
	public void refreshTable() {
		try {
			Connection con;
			 con = (Connection) db.db_connect();
			PreparedStatement reserStmt = con.prepareStatement("Select id_reservation, date_arrivee,  date_depart, adulte, enfant, statut, id_client, id_categorie from reservation");
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
	public ReservationInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1317, 813);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(97, 70, 769, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("Gestion de Reservation");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 29));
		lblNewLabel.setBounds(337, 41, 330, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblReservation = new JLabel("Debut de reservation:");
		lblReservation.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblReservation.setBounds(12, 188, 195, 29);
		contentPane.add(lblReservation);
		
		JLabel lblreservation = new JLabel("Id_reservation");
		lblreservation.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblreservation.setBounds(12, 131, 195, 29);
		contentPane.add(lblreservation);
		
		JLabel lblAdulte = new JLabel("Adulte :");
		lblAdulte.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblAdulte.setBounds(12, 308, 195, 29);
		contentPane.add(lblAdulte);
		
		JLabel lblChambre = new JLabel("Chambre:");
		lblChambre.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblChambre.setBounds(12, 543, 195, 29);
		contentPane.add(lblChambre);
		
		JLabel lblFinDeReservation = new JLabel("Fin de  reservation:");
		lblFinDeReservation.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblFinDeReservation.setBounds(12, 249, 195, 29);
		contentPane.add(lblFinDeReservation);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(206, 134, 158, 27);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtAdulte = new JTextField();
		txtAdulte.setColumns(10);
		txtAdulte.setBounds(206, 311, 202, 27);
		contentPane.add(txtAdulte);
		
		txtChambre = new JTextField();
		txtChambre.setEditable(false);
		txtChambre.setColumns(10);
		txtChambre.setBounds(206, 548, 202, 22);
		contentPane.add(txtChambre);
		
		
		JLabel lblEnfant = new JLabel("Enfant :");
		lblEnfant.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblEnfant.setBounds(12, 371, 195, 29);
		contentPane.add(lblEnfant);
		
		txtEnfant = new JTextField();
		txtEnfant.setColumns(10);
		txtEnfant.setBounds(206, 374, 202, 27);
		contentPane.add(txtEnfant);
		
//		JLabel lblIdClient = new JLabel("Id_client");
//		lblIdClient.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//		lblIdClient.setBounds(12, 488, 195, 29);
//		contentPane.add(lblIdClient);
//		
//		txtIdClient = new JTextField();
//		txtIdClient.setColumns(10);
//		txtIdClient.setBounds(206, 491, 202, 27);
//		contentPane.add(txtIdClient);
//		
		
		JDateChooser dateDeb = new JDateChooser();
		dateDeb.setDateFormatString("YYYY-MM-DD");
		dateDeb.setBounds(206, 188, 158, 29);
		contentPane.add(dateDeb);
		
		JDateChooser dateFin = new JDateChooser();
		dateFin.setDateFormatString("YYYY-MM-DD");
		dateFin.setBounds(206, 249, 158, 29);
		contentPane.add(dateFin);
		
		
		
		

		JButton button = new JButton("Voir/ Assigner Chambre");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			
			String id_reservation = txtId.getText();
			String statut =comboBoxstatut.getSelectedItem().toString();
			
			if (id_reservation().length()==0) {
				JOptionPane.showMessageDialog(null, "Cliquer sur une réservation d`abord s`il vou plait");
			}
			if(statut.equals("Non Confirmé")){
				JOptionPane.showMessageDialog(null,"Veuillez d'abord confirmer la réservation SVP");
				}else 

					try {
						
						Connection con;
						 con =(Connection) db.db_connect();
						PreparedStatement stmt = con.prepareStatement("SELECT client.id_client,reservation.id_reservation,nom,prenom FROM reservation INNER JOIN client on reservation.id_client=client.id_client WHERE  id_reservation=? ");
						stmt.setString(1, id_reservation);
						ResultSet rs = stmt.executeQuery();
		
						if(rs.next()) {
							String id_client =rs.getString(1);
							String id_reser =rs.getString(2);
							String nom =rs.getString(3);
							String prenom =rs.getString(4);

							idClient = rs.getString(1);
							idReserved = rs.getString(2);
							cnom = rs.getString(3);
							cprenom = rs.getString(4);

							AssignerChambre assign = new AssignerChambre(idReserved,idClient,cnom, cprenom);
							assign.setVisible(true);
							       
							}
							else
							JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe non valide");
							con.close();
							}catch(Exception e1){System.out.print(e1);}
	
			
			}

		
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 15));
		button.setBounds(962, 70, 254, 38);
		contentPane.add(button);
		
		
		
		
		/////////////////====================COMBOBOX===================//////////////////////////////////////
		///////////////////===============================================////////////////////////////////
		comboBoxstatut = new JComboBox();
		comboBoxstatut.addItem(" ");
		comboBoxstatut.addItem("Occupé");
		comboBoxstatut.addItem("Libre");
		comboBoxstatut.addItem("Hors Service");
		comboBoxstatut.setEditable(false);
		comboBoxstatut.setBounds(206, 432, 195, 29);
		contentPane.add(comboBoxstatut);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblStatut.setBounds(12, 430, 195, 29);
		contentPane.add(lblStatut);
		
		JLabel lblIdClient = new JLabel("Id Client:");
		lblIdClient.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblIdClient.setBounds(12, 484, 195, 29);
		contentPane.add(lblIdClient);
		
		txtClient = new JTextField();
		txtClient.setEditable(false);
		txtClient.setColumns(10);
		txtClient.setBounds(206, 489, 202, 27);
		contentPane.add(txtClient);
		
		
//		////////////////////=========================SAVE==================/////////////
//		////////////////////////////================================////////////////////
//		JButton btnEnregistre = new JButton("Enregistrer");
//		btnEnregistre.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
		
		
//				////collecter des infos depuis le textfield//////
//				
//				String date_arrivee =((JTextField)dateDeb.getDateEditor().getUiComponent()).getText();
//				String date_depart =((JTextField)dateFin.getDateEditor().getUiComponent()).getText();
//				String adulte = txtAdulte.getText();
//				String enfant = txtEnfant.getText();
//				String id_categorie = txtChambre.getText();
//				String statut =comboBoxstatut.getSelectedItem().toString();
//
//
//					
//				if( date_arrivee.trim().length()==0 || date_depart.trim().length()==0 || adulte.trim().length()==0 || enfant.trim().length()==0  || statut.trim().length()==0 || id_categorie.trim().length()==0 ) {
//					
//					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
//					
//					}			
//			
//				
//				else {
//					
//					try {
//						Connection con;
//						 con =(Connection) db.db_connect();		
//						 String id_client = null;
//						 
//						PreparedStatement stmt = con.prepareStatement("INSERT INTO reservation (date_arrivee, date_depart, adulte, enfant, statut, id_client, id_categorie) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
//						
//						stmt.setString(1, date_arrivee);
//						stmt.setString(2, date_depart);
//						stmt.setString(3, adulte);
//						stmt.setString(4, enfant);
//						stmt.setString(5, statut);
//						stmt.setString(6, id_client);
//						stmt.setString(7, id_categorie);
//						stmt.execute();
//						showTable();
//						JOptionPane.showMessageDialog(null,"Utilisateur Enregistrer");
//						
//						
//						ResultSet rs= stmt.getGeneratedKeys();
//						if(rs.next()) {
//							id_client=rs.getString(6);
//							System.out.println(id_client);
//						}			
//						
//					}catch(Exception e1) {
//						System.out.print(e1);
//						
//					}
//					
//					}
//				}
//					
//				});		
//				
//				
//			
//		
//		btnEnregistre.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
//		btnEnregistre.setBounds(12, 626, 131, 29);
//		contentPane.add(btnEnregistre);
		
		
		//////////////===========================================///////////////////////////////
		//////////////=======================DELETE============////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id_reservation =txtId.getText();
				String date_arrivee =((JTextField)dateDeb.getDateEditor().getUiComponent()).getText();
				String date_depart =((JTextField)dateFin.getDateEditor().getUiComponent()).getText();
				String adulte = txtAdulte.getText();
				String enfant = txtEnfant.getText();
				String id_categorie = txtChambre.getText();

				
if( date_arrivee.trim().length()==0 || date_depart.trim().length()==0 || adulte.trim().length()==0 || enfant.trim().length()==0  || id_categorie.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
			} else 
				
			{
				try {
					Connection con;
					 con =(Connection) db.db_connect();
					PreparedStatement stmt = con.prepareStatement("DELETE FROM reservation WHERE id_reservation=?");
					stmt.setString(1, id_reservation);
					stmt.execute();
					showTable();
					
					JOptionPane.showMessageDialog(null,"Form cancel");
					
				}catch(Exception e1) {
				}
			}
		}
		});
		btnSupprimer.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnSupprimer.setBounds(206, 626, 131, 29);
		contentPane.add(btnSupprimer);
		
		
		
	
		
		///////////////////////======================================MODIFIER=========================/////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String id_reservation =txtId.getText();
				String date_arrivee =((JTextField)dateDeb.getDateEditor().getUiComponent()).getText();
				String date_depart =((JTextField)dateFin.getDateEditor().getUiComponent()).getText();
				String adulte =txtAdulte.getText();
				String enfant =txtEnfant.getText();
				String id_categorie =txtChambre.getText();
				String statut =comboBoxstatut.getSelectedItem().toString();
				
	if(date_arrivee.trim().length()==0 || date_depart.trim().length()==0 || adulte.trim().length()==0|| enfant.trim().length()==0 || id_categorie.trim().length()==0){
					
					JOptionPane.showMessageDialog(null, "Empty txt fields");
				} else {
					
					try {
						Connection con;
						con = (Connection) db.db_connect();
						PreparedStatement stmt = con.prepareStatement("UPDATE reservation set date_arrivee=?,date_depart=?,adulte=?,enfant=?,statut=?,id_categorie=? where id_reservation=?");
						stmt.setString(1, date_arrivee);
						stmt.setString(2, date_depart);
						stmt.setString(3, adulte);
						stmt.setString(4, enfant);
						stmt.setString(5, statut);
						stmt.setString(6, id_categorie);
						stmt.setString(7, id_reservation);
						stmt.execute();
						showTable();
						JOptionPane.showMessageDialog(null,"Table Modifier");
						
 
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
				} 
				
			}
		});
		btnModifier.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnModifier.setBounds(12, 626, 131, 29);
		contentPane.add(btnModifier);
		
		
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(440, 196, 835, 427);
		contentPane.add(scrollPane);
		
		
		
		//////////////////////====================Pour configurer notre Table============================================//////////////////////////////////
		tableReser = new JTable();
		tableReser.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		
		
		tableReser.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				
				try {
					int row = tableReser.getSelectedRow();
					System.out.print(row); //////getSelectedRow() donne l`indice de chaque ranger
					
					String Clicktable = (tableReser.getModel().getValueAt(row, 0).toString());
//					System.out.print(Clicktable);
					
					
					Connection con;
					con = (Connection) db.db_connect();
					PreparedStatement callInfo = con.prepareStatement("SELECT * FROM reservation WHERE id_reservation='"+Clicktable+"'"); //////faire la concatenation avec le nom du variable clicktable
					ResultSet rs = callInfo.executeQuery();
					if(rs.next()) {
						
						String data1 = rs.getString("id_reservation");
						String data2 = rs.getString("date_arrivee");
						String data3 = rs.getString("date_depart");
						String data4 = rs.getString("adulte");
						String data5 = rs.getString("enfant");
						String data6 = rs.getString("statut");
						String data7 = rs.getString("id_client");
						String data8 = rs.getString("id_categorie");

//						System.out.print(data2);
//			
						txtId.setText(data1);
						((JTextField)dateDeb.getDateEditor().getUiComponent()).setText(data2);
						((JTextField)dateFin.getDateEditor().getUiComponent()).setText(data3);
						txtAdulte.setText(data4);
						txtEnfant.setText(data5);
						txtChambre.setText(data8);
						txtIdClient.setText(data7);
						comboBoxstatut.setSelectedItem(data6);
//						
					}
					
					
				}catch(Exception e1) {
					System.out.print(e1);
				}
				
			}
			
		});
		scrollPane.setViewportView(tableReser);
		
	
		
		
		
		////////////////////=========================ACTUALISER++++++++++++=============////////////////////////
		JButton btn = new JButton("Actualiser");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				showTable();
				((JTextField)dateDeb.getDateEditor().getUiComponent()).setText(" ");
				((JTextField)dateFin.getDateEditor().getUiComponent()).setText(" ");
				txtAdulte.setText(" ");
				txtEnfant.setText(" ");
				txtChambre.setText(" ");

				
			}
		});
		btn.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btn.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btn.setBounds(962, 131, 254, 40);
		contentPane.add(btn);
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					reserverForm window = new reserverForm();
					window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			}
		});
		btnNouveau.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNouveau.setBounds(119, 686, 131, 25);
		contentPane.add(btnNouveau);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\r.jpg"));
		label.setBounds(0, 0, 1299, 766);
		contentPane.add(label);
		
		
		
		
	}
	
	
	
	protected String id_reservation() {
		// TODO Auto-generated method stub
		return null;
	}



	////////////////////////////////////++++++++++++++++++++la fonction showTable+++++++++++++++++++++++++++++++///////////////////////////////////////////
	
	public static void showTable() {
		
		connectdb db = new connectdb();
		
		try {
			Connection con;
			con = (Connection) db.db_connect();
			PreparedStatement userStmt = con.prepareStatement("SELECT id_reservation AS '#', date_arrivee as 'Arrivee', date_depart as 'Depart', adulte as 'Adulte', enfant as 'Enfant', statut as 'Statut', id_client as 'Id_Client', id_categorie as 'IdChambre' FROM reservation ");	
		//////////////pour renomer le nom de colonne dans ma table en referant aux noms donnees dans la base de donnees SAMIRA======
		ResultSet rs = userStmt.executeQuery();///////////l`execution du query se fait ici...
		tableReser.setModel(DbUtils.resultSetToTableModel(rs));///////on passe en parametre le resultset 
		
		}catch (Exception e) {
			System.out.print(e);
		}
		
	
	
}
}











