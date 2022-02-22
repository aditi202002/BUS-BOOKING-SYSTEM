package busBooking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class userHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Source;
	private JTextField Desti;
	private JTextField Date;
	private JLabel lblEnterSource;
	private JLabel lblEnterDesti;
	private JLabel lblEnterDate;
	private JLabel lblHome;
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox date;

	public userHome(String userSes) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 80, 900, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEnterSource = new JLabel("Source");
		lblEnterSource.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterSource.setBounds(160, 160, 326, 67);
		contentPane.add(lblEnterSource);

		String source[]={"Alibag","Mumbai","Nashik"};        
		c1 = new JComboBox(source);    
		c1.setBounds(420, 172, 250, 37);    
		contentPane.add(c1);        
		contentPane.setLayout(null);    
		contentPane.setSize(320,350);    
		contentPane.setVisible(true);     

		lblEnterDesti = new JLabel("Destination");
		lblEnterDesti.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterDesti.setBounds(160, 220, 326, 67);
		contentPane.add(lblEnterDesti);

		String destination[]={"Nagpur","Kolhapur"};        
		c2 = new JComboBox(destination);    
		c2.setBounds(420, 236,250, 37);    
		contentPane.add(c2);        
		contentPane.setLayout(null);    
		contentPane.setSize(320,350);    
		contentPane.setVisible(true);   

		lblEnterDate = new JLabel("Date");
		lblEnterDate.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterDate.setBounds(160, 280, 326, 67);
		contentPane.add(lblEnterDate);

		String dte[]={"2021-01-01","2021-01-02","2021-01-03"};        
		date = new JComboBox(dte);    
		date.setBounds(420, 300,250, 37);    
		contentPane.add(date);        
		contentPane.setLayout(null);    
		contentPane.setSize(320,350);    
		contentPane.setVisible(true);  

		lblHome = new JLabel("HOME PAGE");
		lblHome.setForeground(Color.RED);
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblHome.setBounds(290, 30, 326, 67);
		contentPane.add(lblHome);

		//Logout Button
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(btnNewButton, "Are you sure?");
				// JOptionPane.setRootFrame(null);
				if (a == JOptionPane.YES_OPTION) {
					dispose();
					login obj = new login();
					obj.setTitle("Bus-Login");
					obj.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(720, 40, 150, 25);
		contentPane.add(btnNewButton);

		//Change PW button
		JButton button = new JButton("Change Password\r\n");
		button.setBackground(UIManager.getColor("Button.disabledForeground"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePassword bo = new changePassword(userSes);
				bo.setTitle("Change Password");
				bo.setVisible(true);

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(720, 80, 150, 30);
		contentPane.add(button);

		//search button
		JButton btnSearch = new JButton("Search\r\n");
		btnSearch.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnSearch.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String src = c1.getItemAt(c1.getSelectedIndex()).toString();
				String des = c2.getItemAt(c2.getSelectedIndex()).toString();
				String bdate = date.getItemAt(date.getSelectedIndex()).toString();
				System.out.println("Source : "+ src); 
				System.out.println("Destination : " + des); 
				System.out.println("Booking Date : " + bdate); 
				busDetails bo = new busDetails(src,des,bdate,userSes);
				bo.setTitle("Bus Details");
				bo.setVisible(true);
				dispose();
				//}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnSearch.setBounds(280, 400, 250, 40);
		contentPane.add(btnSearch);


	}
}
