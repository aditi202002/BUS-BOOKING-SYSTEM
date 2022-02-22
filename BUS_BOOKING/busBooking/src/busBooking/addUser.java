package busBooking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class addUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userName;
	private JTextField passWord;
	private JTextField cpassWord;
	private JTextField name;
	private JTextField pnum;
	private JTextField email;
	private JLabel errlabel;
	private JLabel lblEnterUserName;
	private JLabel lblEnterPass;
	private JLabel lblEnterCPass;
	private JLabel lblEnterFname;
	private JLabel lblEnterPhno;
	private JLabel lblEnterEmail;
	private JLabel lblSignup;

	public addUser() {
		setBounds(210, 55, 800, 650);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		userName = new JTextField();
		userName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		userName.setBounds(373, 108, 250, 37);
		contentPane.add(userName);
		userName.setColumns(10);

		passWord = new JTextField();
		passWord.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passWord.setBounds(373, 165,250, 37);
		contentPane.add(passWord);
		passWord.setColumns(10);

		cpassWord = new JTextField();
		cpassWord.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cpassWord.setBounds(373, 230, 250, 37);
		contentPane.add(cpassWord);
		cpassWord.setColumns(10);

		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		name.setBounds(373, 296, 250, 37);
		contentPane.add(name);
		name.setColumns(10);

		pnum = new JTextField();
		pnum.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnum.setBounds(373, 360, 250, 37);
		contentPane.add(pnum);
		pnum.setColumns(10);

		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 25));
		email.setBounds(373, 426, 250, 37);
		contentPane.add(email);
		email.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validFlag = true;
				if(userName.getText().equals("") || passWord.getText().equals("") || name.getText().equals("") || pnum.getText().equals("") || email.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"All fileds are mandatory !");
					validFlag = false; 

				}else if(!cpassWord.getText().equals(passWord.getText())) {                   	   
					JOptionPane.showMessageDialog(null,"Password Mismatch!");
					validFlag = false;
				}

				if(validFlag == true)
				{
					String uname = userName.getText();
					String pswd = passWord.getText();
					String cpswd = cpassWord.getText();
					String fname = name.getText();
					String pno = pnum.getText();
					String mail = email.getText();
					try {
						System.out.println("Username : " + uname);
						System.out.println("Passworde : " + pswd);
						System.out.println("Confirm Password : " + cpswd);
						System.out.println("Name : " + fname);
						System.out.println("Phone No : " + pno);
						System.out.println("Email : " + mail);

						//Connect to the database
						connectDB connection = new connectDB();
						PreparedStatement st = (PreparedStatement) connection.getConnection()
								.prepareStatement("insert into Users(username,pass_word,phone_number,fname,email) values (?,?,?,?,?)");

						st.setString(1, uname);
						st.setString(2, pswd);
						st.setString(3, pno);
						st.setString(4, fname);
						st.setString(5, mail);
						st.executeUpdate();

						JOptionPane.showMessageDialog(null,"User successfully created!");                   
						dispose();
						login obj = new login();
						obj.setTitle("Bus-Login");
						obj.setVisible(true);

					} catch (SQLException sqlException) {
						sqlException.printStackTrace();
					}
				}

			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 29));
		btnSubmit.setBackground(new Color(240, 240, 240));
		btnSubmit.setBounds(250, 500, 170, 59);
		contentPane.add(btnSubmit);

		lblSignup = new JLabel("SIGN UP");
		lblSignup.setForeground(Color.RED);
		lblSignup.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblSignup.setBounds(270, 15, 326, 67);
		contentPane.add(lblSignup);

		lblEnterUserName = new JLabel("Enter Username :");
		lblEnterUserName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterUserName.setBounds(45, 90, 326, 67);
		contentPane.add(lblEnterUserName);

		lblEnterPass = new JLabel("Enter Password :");
		lblEnterPass.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterPass.setBounds(45, 150, 326, 67);
		contentPane.add(lblEnterPass);

		lblEnterCPass = new JLabel("Confrim Password :");
		lblEnterCPass.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterCPass.setBounds(45, 213, 326, 67);
		contentPane.add(lblEnterCPass);

		lblEnterFname = new JLabel("Enter Name :");
		lblEnterFname.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterFname.setBounds(45, 278, 326, 67);
		contentPane.add(lblEnterFname);

		lblEnterPhno = new JLabel("Enter Phone No. :");
		lblEnterPhno.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterPhno.setBounds(45, 340, 326, 67);
		contentPane.add(lblEnterPhno);

		lblEnterEmail = new JLabel("Enter Email :");
		lblEnterEmail.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEnterEmail.setBounds(45, 408, 326, 67);
		contentPane.add(lblEnterEmail);
		
		errlabel = new JLabel();
		errlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		errlabel.setForeground(Color.red);
		contentPane.add( errlabel);
		
		pnum.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent ke) {
	           if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
	        	   pnum.setEditable(true);
	              errlabel.setText("");
	           } else {
	        	   pnum.setEditable(false);
	              errlabel.setText("* Enter only numeric digits(0-9)");
	              errlabel.setBounds(370, 392, 600, 20);
	           }
	        }
	     });

	}
}
