package busBooking;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class paymentDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPerDetails;
	private JLabel lblPayType;
	private JLabel lblTotalFare;
	private JLabel lblCrdNo;
	private JLabel lblcvv;
	private JLabel errlabel;
	private JTextField Crdno;
	private JTextField Cvv;
	private float basefare;
	private int PCount;
	private String UId = "";
	private String Bdate = "";
	private String bk_id = "";
	private int BSeats;
	
	
    
	public paymentDetails(String busId,ArrayList pdetails,String username) {

		PCount = pdetails.size();
		try {

			//Connect to the database
			connectDB connection = new connectDB();
			PreparedStatement st = (PreparedStatement) connection.getConnection()
					.prepareStatement("Select u_id from Users where username=? ");

			st.setString(1, username);     

			ResultSet rs = st.executeQuery();           
			if (rs.next() == false) 
			{ 
				System.out.println("No Username found !!"); 
			} 
			else 
			{ 
				String userID = rs.getString("u_id");
				UId = userID;

			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}


		try {
			//Connect to the database
			connectDB connection = new connectDB();
			PreparedStatement st = (PreparedStatement) connection.getConnection()
					.prepareStatement("Select base_fare,bus_date,booked_seats from Bus where b_id=? ");

			st.setString(1, busId);     

			ResultSet rs = st.executeQuery();

			if (rs.next() == false) 
			{ 
				System.out.println("No Fare found !!"); 
			} 
			else 
			{ 
				String fare = rs.getString("base_fare");
				String bdate = rs.getString("bus_date");
				String bookseats = rs.getString("booked_seats");
				System.out.println("Fare : " + fare);
				basefare = Float.parseFloat(fare);

				System.out.println("Date : " + bdate);
				Bdate = bdate;

				System.out.println("Booked Seats : " + bookseats);
				BSeats = Integer.parseInt(bookseats);

			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		String TotalFare = Float.toString(basefare*PCount);
		System.out.println("TotalFare : " + (basefare*PCount));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 80, 900, 600);;
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Pay Now button
		JButton btnPaynow = new JButton("Pay Now\r\n");
		btnPaynow.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnPaynow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean validFlag = true;
				String CardNo = Crdno.getText();
				String cvv = Cvv.getText();

				if(Crdno.getText().equals("") || Cvv.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null,"All fields are mandatory !" );
					validFlag = false;
				}

				if(validFlag == true)
					try {
						//Connect to the database
						String dbURL = "jdbc:sqlserver://localhost;user=sa;password=12345;databaseName=bus_management";
						String user = "sa";
						String pass = "12345";
						Connection con  = DriverManager.getConnection(dbURL, user, pass);

						PreparedStatement st = (PreparedStatement) con
								.prepareStatement("insert into Booking_Details(u_id,b_id,date_of_travel,no_of_seats,total_fare,card_no,cvv) values (?,?,?,?,?,?,?)");

						st.setString(1, UId);  
						st.setString(2, busId);
						st.setString(3, Bdate);
						st.setString(4, Integer.toString(PCount));
						st.setString(5, TotalFare);
						st.setString(6, CardNo);
						st.setString(7, cvv);
						st.executeUpdate();

						st = (PreparedStatement) con
								.prepareStatement("SELECT IDENT_CURRENT('Booking_Details')  as bk_id");

						ResultSet rs = st.executeQuery();


						if (rs.next() == false) 
						{ 
							System.out.println("No buses found !!"); 
						} 
						else 
						{ 
							bk_id = rs.getString("bk_id");                     	
						}

						Iterator<personalDetails> iterator = pdetails.iterator();
						// while loop
						while (iterator.hasNext()) {
							personalDetails temp = iterator.next();
							System.out.println("id= " + temp.getId());
							System.out.println("name= " + temp.getName().toString());
							System.out.println("age= " + temp.getAge().toString());

							st = (PreparedStatement) con
									.prepareStatement("insert into Ticket_Details(BK_id,Bk_run,fname,age,seat_no) values (?,?,?,?,?)");

							st.setString(1, bk_id);  
							st.setString(2, Integer.toString(temp.getId()+1));
							st.setString(3, temp.getName());  
							st.setString(4, temp.getAge());  
							st.setString(5, Integer.toString(BSeats));

							st.executeUpdate();   
							BSeats = BSeats + 1;
						}

						st = (PreparedStatement) con
								.prepareStatement("update Bus set booked_seats = ? where b_id = ?");

						st.setString(1, Integer.toString(BSeats));
						st.setString(2,busId);
						st.executeUpdate(); 
						
						String res;
						res = "*** Payment Sucessful ***";
						res = res + "\n\n Your Booking Id is " + bk_id;
						res = res + "\n\n Your Seat No. is " + BSeats;
						JOptionPane.showMessageDialog(null,res,"",JOptionPane.PLAIN_MESSAGE);
						
						//JOptionPane.showMessageDialog(null,"<html><b>** Payment Sucessfull **</b><br> Your Booking Id is bk_id</html>");
						dispose();
						login obj = new login();
						obj.setTitle("Bus-Login");
						obj.setVisible(true);    
						dispose();


					} catch (SQLException sqlException) 
				{
						sqlException.printStackTrace();
				}
			}
		});
		btnPaynow.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnPaynow.setBounds(300, 450, 250, 40);
		contentPane.add(btnPaynow);

		lblPerDetails = new JLabel("PAYMENT DETAILS");
		lblPerDetails.setForeground(Color.RED);
		lblPerDetails.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblPerDetails.setBounds( 275, 30, 600, 60);
		contentPane.add(lblPerDetails);

		lblTotalFare = new JLabel("Total Fare :" + TotalFare,lblTotalFare.CENTER);
		lblTotalFare.setOpaque(true);
		lblTotalFare.setBackground(Color.LIGHT_GRAY);
		lblTotalFare.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTotalFare.setBounds(278, 120, 300, 50);
		contentPane.add( lblTotalFare);

		lblPayType = new JLabel("Enter Card Details :");
		lblPayType.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblPayType.setBounds(150, 230, 500, 40);
		contentPane.add( lblPayType);

		lblCrdNo = new JLabel("Card Number");
		lblCrdNo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCrdNo.setBounds(150, 300, 500, 50);
		contentPane.add( lblCrdNo);

		lblcvv = new JLabel("CVV");
		lblcvv.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblcvv.setBounds(150, 370, 500, 50);
		contentPane.add( lblcvv);

		Crdno = new JTextField();
		Crdno.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Crdno.setBounds(360, 310, 250, 37);
		contentPane.add(Crdno);

		Cvv = new JTextField();
		Cvv.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Cvv.setBounds(360, 380, 250, 37);
		contentPane.add(Cvv);
		
		errlabel = new JLabel();
		errlabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		errlabel.setForeground(Color.red);
		contentPane.add( errlabel);
		
		Crdno.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent ke) {
	           if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
	        	  Crdno.setEditable(true);
	              errlabel.setText("");
	           } else {
	        	  Crdno.setEditable(false);
	              errlabel.setText("* Enter only numeric digits(0-9)");
	              errlabel.setBounds(370, 350, 600, 20);
	           }
	        }
	     });

	}
}

