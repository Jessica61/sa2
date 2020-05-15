package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


public class chambreInterface extends JFrame {

	private JPanel contentPane;
	private JTextField txtNum;
	private JTextField txtTarif;
	private JComboBox <String>comboTypeChambre;
	
	connectdb db = new connectdb();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chambreInterface frame = new chambreInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public chambreInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumChambres = new JLabel("Numero de chambres:");
		lblNumChambres.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblNumChambres.setBounds(33, 130, 185, 27);
		contentPane.add(lblNumChambres);
		
		JLabel lblTarif = new JLabel("Tarif de la chambre:");
		lblTarif.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblTarif.setBounds(33, 212, 185, 27);
		contentPane.add(lblTarif);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		lblType.setBounds(33, 282, 128, 27);
		contentPane.add(lblType);
		
		txtNum = new JTextField();
		txtNum.setEditable(false);
		txtNum.setBounds(254, 134, 116, 22);
		contentPane.add(txtNum);
		txtNum.setColumns(10);
		
		txtTarif = new JTextField();
		txtTarif.setColumns(10);
		txtTarif.setBounds(254, 216, 116, 22);
		contentPane.add(txtTarif);

		
		
		/**Enregistrer une chambre*/
		JButton btnSave = new JButton("Enregistrer");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String tarif = txtTarif.getText();
				String types =comboTypeChambre.getSelectedItem().toString();
				
if( tarif.trim().length()==0 || types.trim().length()==0 ) {
					
					JOptionPane.showMessageDialog(null, "Remplissez tous les champs ");
					
					}			
			
				
				else {
					
					try {
						Connection con;
						 con = (Connection) db.db_connect();
						 String num_chambre= null;
						PreparedStatement stmt = con.prepareStatement("INSERT INTO categorie ( tarif, types) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
						
						stmt.setString(1, tarif);
						stmt.setString(2, types);
						stmt.executeUpdate();
						clientInterface.showTable();
						
						ResultSet rs= stmt.getGeneratedKeys();
						if(rs.next()) {
							num_chambre=rs.getString(1);
							System.out.println(num_chambre);
						}				
						
						//ReservationInterface.showTable();
						JOptionPane.showMessageDialog(null,"Chambre Enregistrer");
						
					}catch(Exception e1) {
						System.out.print(e1);
						
					}
					
					}
				}
			
		});
		btnSave.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnSave.setBounds(39, 378, 122, 36);
		contentPane.add(btnSave);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pour sortir du program/ on se sert de la method exit de la classe system
				System.exit(0);
			}
		});
		btnAnnuler.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnAnnuler.setBounds(270, 378, 122, 36);
		contentPane.add(btnAnnuler);
	
		//////code image////
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(80, 64, 783, 2);
		contentPane.add(separator);
		
		JLabel lblTitre = new JLabel("Gestion de Chambres");
		lblTitre.setForeground(new Color(0, 0, 255));
		lblTitre.setFont(new Font("Trebuchet MS", Font.BOLD, 29));
		lblTitre.setBounds(353, 37, 300, 27);
		contentPane.add(lblTitre);
		
		 comboTypeChambre = new JComboBox();
		 comboTypeChambre.addItem("");
		 comboTypeChambre.addItem("Simple");
		 comboTypeChambre.addItem("Double");
		 comboTypeChambre.addItem("Double confort");
		 comboTypeChambre.addItem("Suite");
		 comboTypeChambre.setEditable(false);
		comboTypeChambre.setBounds(254, 286, 116, 22);
		contentPane.add(comboTypeChambre);
		
			//////code image////
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\chambre.jpg"));
		label.setBounds(430, 64, 626, 402);
		contentPane.add(label);
	}
}
