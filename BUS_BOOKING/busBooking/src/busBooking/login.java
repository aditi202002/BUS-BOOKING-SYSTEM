package busBooking;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JLabel lblbbs;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login() {
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 80, 1000, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblbbs = new JLabel("BUS BOOKING SYSTEM");
		lblbbs.setForeground(Color.RED);
		lblbbs.setFont(new Font("Times New Roman", Font.BOLD, 46));
		lblbbs.setBounds(242, 13, 600, 93);
		contentPane.add(lblbbs);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(430, 85, 273, 93);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		textField.setBounds(481, 170, 281, 68);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
		passwordField.setBounds(481, 286, 281, 68);
		contentPane.add(passwordField);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBackground(Color.BLACK);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblUsername.setBounds(275, 166, 193, 52);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblPassword.setBounds(275, 286, 193, 52);
		contentPane.add(lblPassword);

		btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnNewButton.setBounds(545, 392, 162, 73);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
				String password = passwordField.getText();
				try {
					//Connect to the database
					connectDB connection = new connectDB();
					PreparedStatement st = (PreparedStatement) connection.getConnection()
							.prepareStatement("Select username, pass_word from Users where username=? and pass_word=?");

					st.setString(1, userName);
					st.setString(2, password);
					ResultSet rs = st.executeQuery();

					if (rs.next()) {
						dispose();
						userHome ah = new userHome(userName);
						ah.setTitle("Welcome");
						ah.setVisible(true);
						JOptionPane.showMessageDialog(null,"You have successfully logged in");
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Username & Password");
					}
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				}
			}
		});

		contentPane.add(btnNewButton);

		JButton button = new JButton("Add User\r\n");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser bo = new addUser();
				bo.setTitle("Add User");
				bo.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 26));
		button.setBounds(300, 392, 182, 73);;
		contentPane.add(button);
	}
}