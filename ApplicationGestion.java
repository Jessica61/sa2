package sa2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ApplicationGestion extends JFrame {

	private JPanel contentPane;
	public String privilege;//creer ses 2 variables 
	public String monNom;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationGestion frame = new ApplicationGestion("role", "nom");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param monNom 
	 * @param privilege 
	 */
	public ApplicationGestion(String privilege, String monNom) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestion = new JLabel("GESTION");
		lblGestion.setBackground(new Color(204, 255, 204));
		lblGestion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblGestion.setBounds(0, 0, 756, 56);
		contentPane.add(lblGestion);
		
		/////==================POUR ACCEDER A LA CHAMBRE////////
		JButton btnChambres = new JButton("Chambres");
		btnChambres.setBounds(12, 79, 134, 45);
		btnChambres.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnChambres.setBounds(39, 68, 122, 38);
		btnChambres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			chambreInterface chambre = new chambreInterface();
			chambre.setVisible(true);
			}
			});
		
		//////////================POUR ACCEDER AU FORMULAIRE DE RESERVATION======/////
		JButton btnReservation = new JButton("Reservation");
		btnReservation.setBounds(12, 168, 134, 45);
		btnReservation.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//quand je clique sur reservation elle va sur la page reservation
			ReservationInterface reservation = new ReservationInterface();
			reservation.setVisible(true);
			}
			});
		btnReservation.setBounds(39, 152, 122, 38);
		
		//////////================POUR ACCEDER A L`UTILISATEUR======/////

		JButton btnUtilisateur = new JButton("Utilisateur");
		btnUtilisateur.setBounds(12, 259, 134, 45);
		btnUtilisateur.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnUtilisateur.setBounds(39, 228, 122, 38);
		btnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			UserInterface user = new UserInterface();
			user.setVisible(true);
			}
			});
		
		//////////================POUR ACCEDER AU CLIENT======/////

		JButton btnClients = new JButton("Clients");
		btnClients.setBounds(12, 350, 134, 45);
		btnClients.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnClients.setBounds(39, 302, 122, 38);
		btnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			clientInterface client = new clientInterface();
			client.setVisible(true);
			}
		});
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//pour sortir du program/ on se sert de la method exit de la classe system
				System.exit(0);
			}
		});
		btnQuitter.setBounds(367, 405, 134, 45);
		contentPane.add(btnQuitter);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Eclipse\\sa2\\img\\user.jpg"));
		label.setBounds(0, 0, 756, 575);
		contentPane.add(label);
		
		
		System.out.print(privilege);
		System.out.print(monNom);

		
		try { 
			if (privilege.equals("Administrateur")) {
				contentPane.add(btnChambres);
				contentPane.add(btnReservation);
				contentPane.add(btnClients);
				contentPane.add(btnUtilisateur);
				
			}else if (privilege.equals("Utilisateur")) {
				contentPane.add(btnChambres);
				contentPane.add(btnReservation);
				contentPane.add(btnClients);
			}
		}catch(Exception e) {
			System.out.print(e);
		}
		
	}
}
