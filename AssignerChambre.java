package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssignerChambre extends JFrame {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private JTextField txtIdReservation;
	private JTextField txtIdClient;
	private JTextField txtNomClient;
	private JTextField txtprenomClient;
	private JComboBox<String> roomAvailableList;
	private JButton btnAssignerCetteChambre;
	private JTable tblConcerner;
	private String idReserved, idClient, cnom, cprenom, idRoom;
	private JTextField txtIdChambre;
	private JTextField txtNumero;
	private JDateChooser dateArrivee;
	private JDateChooser dateDepart;
	 
	private JPanel contentPane;
	

	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	public void run() {
	try {
	AssignerChambre frame = new AssignerChambre("","","","");
	frame.setVisible(true);
	} catch (Exception e) {
	e.printStackTrace();
	}
	}
	});
	}

	/**
	 * Create the frame.
	 * @param string4 
	 * @param string3 
	 * @param string2 
	 * @param string 
	 */
	
	

	/**
	* Create the frame.
	*/
	public AssignerChambre(String idReserved,String idClient,String cnom,String cprenom) {
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 1033, 651);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	// System.out.println(idReserved);

	JPanel panel = new JPanel();
	panel.setBackground(Color.WHITE);
	panel.setBounds(12, 13, 991, 578);
	contentPane.add(panel);
	panel.setLayout(null);

	txtIdReservation = new JTextField();
	txtIdReservation.setText(idReserved);
	txtIdReservation.setBounds(134, 85, 136, 27);
	txtIdReservation.setFont(new Font("Tahoma", Font.BOLD, 11));
	panel.add(txtIdReservation);
	txtIdReservation.setColumns(10);

	txtIdClient = new JTextField();
	txtIdClient.setText(idClient);
	txtIdClient.setFont(new Font("Tahoma", Font.BOLD, 11));
	txtIdClient.setBounds(134, 123, 136, 27);
	panel.add(txtIdClient);
	txtIdClient.setColumns(10);

	txtNomClient = new JTextField();
	txtNomClient.setText(cnom);
	txtNomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
	txtNomClient.setBounds(134, 163, 136, 27);
	panel.add(txtNomClient);
	txtNomClient.setColumns(10);

	txtprenomClient = new JTextField();
	txtprenomClient.setText(cprenom);
	txtprenomClient.setFont(new Font("Tahoma", Font.BOLD, 11));
	txtprenomClient.setBounds(134, 207, 194, 27);
	panel.add(txtprenomClient);
	txtprenomClient.setColumns(10);

	JLabel lblIdRservation = new JLabel("ID R\u00E9servation :");
	lblIdRservation.setBounds(10, 78, 136, 39);
	lblIdRservation.setFont(new Font("Arial", Font.BOLD, 14));
	panel.add(lblIdRservation);

	JLabel lblIdClient = new JLabel("ID Client :");
	lblIdClient.setBounds(10, 128, 112, 14);
	lblIdClient.setFont(new Font("Arial", Font.BOLD, 14));
	panel.add(lblIdClient);

	JLabel lblNom = new JLabel("Nom :");
	lblNom.setBounds(12, 166, 112, 14);
	lblNom.setFont(new Font("Arial", Font.BOLD, 14));
	panel.add(lblNom);

	JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
	lblPrnom.setBounds(10, 207, 112, 14);
	lblPrnom.setFont(new Font("Arial", Font.BOLD, 14));
	panel.add(lblPrnom);
	connectdb db =new connectdb();

	
	
	
	
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setToolTipText("Supprimer");
		btnDelete.setBackground(new Color(72,138,153));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorder(null);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		//btnDelete.setIcon(new ImageIcon(RoomPanel.class.getResource("/loginSystem/img/delete.png")));
		btnDelete.addActionListener(new ActionListener() {
		private JLabel txtIdRef;
		private Object dateReserve;
		private JLabel txtAdult;
		private JLabel txtChild;
		private JComboBox<String> comboPlan;
		private JComboBox<String> comboStatus;

		public void actionPerformed(ActionEvent arg0) {
		String idReservation = txtIdRef.getText();
		//String dateSet = ((JTextField)dateReserve.getDateEditor().getUiComponent()).getText();
		String date_arrivee =((JTextField)dateArrivee.getDateEditor().getUiComponent()).getText();
		String date_depart =((JTextField)dateDepart.getDateEditor().getUiComponent()).getText();
		String adult =txtAdult.getText();
		String child =txtChild.getText();
		String plan =comboPlan.getSelectedItem().toString();
		String statut =comboStatus.getSelectedItem().toString();

		if ( idReservation.trim().length() == 0 || date_arrivee.trim().length() == 0||
		date_depart.trim().length() == 0|| adult.trim().length() == 0||
		child.trim().length()==0|| plan.trim().length()==0|| statut.trim().length()==0 ) {
		JOptionPane.showMessageDialog(null,"Veuillez d'abord sélectionner une réservation ");
		return;
		}else { 
		connectdb db= new connectdb();
		String id_reservation = txtIdRef.getText();

		try {
		Connection con;
		con =db.db_connect();

		PreparedStatement findIdClient=con.prepareStatement("Select id_client FROM reservation WHERE id_reservation=?");
		findIdClient.setString(1, id_reservation);
		ResultSet rs = findIdClient.executeQuery();
		if(rs.next()) {
		String id=rs.getString("id_client");
		// System.out.print(id);

		PreparedStatement stmt = con.prepareStatement("DELETE FROM reservation WHERE id_reservation=?");
		stmt.setString(1, id_reservation);
		stmt.executeUpdate();
		  refresh();

		PreparedStatement stmt2 = con.prepareStatement("DELETE FROM client WHERE id_client=?");
		stmt2.setString(1, id);
		stmt2.executeUpdate();
		JOptionPane.showMessageDialog(null,"Suppression effectuée");
		refresh();

		} 


		 
		} catch (ClassNotFoundException e) {

		JOptionPane.showMessageDialog(null,e );
		e.printStackTrace();
		} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,"Operation impossible car ce client a déja une/des chambre(s) de réserver"); 
		System.out.println(e);
		e.printStackTrace();
		}
		showTable();}
		}
		});

		btnDelete.setBounds(198, 478, 149, 30);
		getContentPane().add(btnDelete);




	
	



		roomAvailableList = new JComboBox<String>();
		roomAvailableList.setBounds(125, 347, 145, 27);
		try {
		roomAvailableList.removeAllItems();

		Connection con;
		con = db.db_connect();
		PreparedStatement stmt = con.prepareStatement("SELECT numero FROM chambres WHERE statut='libre' ");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		       {
		           this.roomAvailableList.addItem(rs.getString("numero"));
		       }
		con.close();
		rs.close();
		}catch(Exception e){}
		panel.add(roomAvailableList);

		btnAssignerCetteChambre = new JButton("Assigner Cette Chambre");
		btnAssignerCetteChambre.setBackground(new Color(72,138,153));
		btnAssignerCetteChambre.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		String idReservation =txtIdReservation.getText();
		// String idClient =txtIdClient.getText();
		String room =roomAvailableList.getSelectedItem().toString();
		try {
		Connection con;
		con = db.db_connect();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM chambres WHERE numero=?");
		stmt.setString(1, room);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {

		String add1 =rs.getString("num_chambre");
		idRoom=add1;
		}
		PreparedStatement stmt2 = con.prepareStatement("INSERT INTO concerner (num_chambre,id_reservation) VALUES(?,?)");
		stmt2.setString(1, idRoom);
		stmt2.setString(2, idReservation);
		stmt2.executeUpdate();
		showTable();
		refreshRoomList();

		}catch(Exception e) {System.out.print(e);};
		System.out.print(idRoom);

		}

		});
		btnAssignerCetteChambre.setBounds(91, 399, 179, 39);
		panel.add(btnAssignerCetteChambre);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(399, 32, 559, 443);
		panel.add(scrollPane);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		tblConcerner = new JTable() {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row,int column){  
		                 return false;  
		      }
		};
		tblConcerner.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblConcerner.setBackground(Color.LIGHT_GRAY);
		tblConcerner.setFont(new Font("Tahoma", Font.BOLD, 11));
		tblConcerner.setRowHeight(30);
		tblConcerner.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		try {
		// String idReservation =txtIdReservation.getText();
		int row=tblConcerner.getSelectedRow();
		String tableClick =(tblConcerner.getModel().getValueAt(row, 0).toString());

		Connection con;
		con = db.db_connect();
		PreparedStatement stmt = con.prepareStatement("SELECT concerner.num_chambre,concerner.id_reservation, numero FROM concerner INNER JOIN chambres ON concerner.num_chambre=chambres.num_chambre WHERE concerner.num_chambre='"+tableClick+ "'");
		ResultSet rs = stmt.executeQuery();



		if(rs.next()) {

		String add1 =rs.getString("num_chambre");
		txtIdChambre.setText(add1);

		String add2 =rs.getString("numero");
		txtNumero.setText(add2);

		}

		} catch(Exception ex) {
		JOptionPane.showMessageDialog(null, ex);
		}
		}
		});
		        

		tblConcerner.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tblConcerner);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(371, 11, 6, 616);
		panel.add(separator_1);

		txtIdChambre = new JTextField();
		txtIdChambre.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIdChambre.setColumns(10);
		txtIdChambre.setBounds(134, 245, 136, 27);
		panel.add(txtIdChambre);

		JLabel lblIdChambre = new JLabel("ID Chambre :");
		lblIdChambre.setFont(new Font("Arial", Font.BOLD, 14));
		lblIdChambre.setBounds(10, 251, 112, 14);
		panel.add(lblIdChambre);

		JLabel lblNumro = new JLabel("Num\u00E9ro :");
		lblNumro.setFont(new Font("Arial", Font.BOLD, 14));
		lblNumro.setBounds(10, 288, 112, 14);
		panel.add(lblNumro);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNumero.setColumns(10);

		txtNumero.setBounds(134, 286, 136, 27);
		panel.add(txtNumero);

		JButton btnDelete1 = new JButton("Retirer");
		btnDelete1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		String num = txtNumero.getText();
		String id = txtIdChambre.getText();

		if ( id.trim().length() == 0 ||num.trim().length() == 0 ) {
		JOptionPane.showMessageDialog(null,"Veuillez d'abord sélectionner une chambre ou réservation");
		}else { 
		connectdb db= new connectdb(); 


		try {
		Connection con;
		con = db.db_connect();
		PreparedStatement stmtUpdate = con.prepareStatement("UPDATE chambres SET Statut='Libre'WHERE num_chambre=?");
		stmtUpdate.setString(1, id);
		stmtUpdate.executeUpdate();

		PreparedStatement stmt = con.prepareStatement("DELETE FROM `concerner` WHERE num_chambre=?");
		stmt.setString(1, id);
		stmt.executeUpdate();
		JOptionPane.showMessageDialog(null,"Suppression effectuée");
		showTable();


		} catch (ClassNotFoundException e) {

		JOptionPane.showMessageDialog(null, e);
		e.printStackTrace();
		} catch (SQLException e) {
		JOptionPane.showMessageDialog(null,e); 
		e.printStackTrace();
		}

		showTable();}
		}
		});
		btnDelete1.setBackground(new Color(72, 138, 153));
		btnDelete1.setBounds(595, 505, 179, 39);
		panel.add(btnDelete1);
		showTable();

		}

		protected void refresh() {
		// TODO Auto-generated method stub
		
	}

		public void showTable(){
		connectdb db =new connectdb();
		try {
		String idReservation =txtIdReservation.getText();
		Connection con;
		con = db.db_connect();
		PreparedStatement stmt = con.prepareStatement
		("SELECT num_chambre as 'ID Chambre', id_reservation as 'ID Reservation' FROM concerner WHERE id_reservation=?");
		stmt.setString(1, idReservation);
		ResultSet rs = stmt.executeQuery();
		tblConcerner.setModel(DbUtils.resultSetToTableModel(rs));
		tblConcerner.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblConcerner.getColumnModel().getColumn(1).setPreferredWidth(50);


		}catch(Exception e){
		JOptionPane.showMessageDialog(null, e);
		}
		}//===END SHOWTABLE()===// 

		public void refreshRoomList() {
		try {
		roomAvailableList.removeAllItems();
		connectdb db =new connectdb();
		Connection con;
		con = db.db_connect();
		PreparedStatement stmt = con.prepareStatement("SELECT numero FROM chambres WHERE statut='libre' ");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		       {
		           this.roomAvailableList.addItem(rs.getString("numero"));
		       }
		con.close();
		rs.close();
		}catch(Exception e) {JOptionPane.showMessageDialog(null, e);}

		}
		public String getIdClient() {
		return idClient;
		}
		public void setIdClient(String idClient) {
		this.idClient = idClient;
		}
		public String getIdReservation() {
		return idReserved;
		}
		public void setIdReservation(String idReserved) {
		this.idReserved = idReserved;
		}
		public String getCNom() {
		return cnom;
		}
		public void setCNom(String cnom) {
		this.cnom = cnom;
		}
		public String getCPrenom() {
		return cprenom;
		}
		public void setCPrenom(String cprenom) {
		this.cprenom = cprenom;
		}
		

		
		
	}
	
	


