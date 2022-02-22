package busBooking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class busDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBusDetails;
	private JLabel lblBusAvail;
	private JLabel lblBusToFrom;
	private JLabel lblPassNo;  
	private JTextField PassNo;
	private String bus_id;
	private JLabel lblrad1;

	public busDetails(String source,String destination,String date,String username) {

		int count = 1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 80, 900, 600);;
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBusDetails = new JLabel("",lblBusDetails.CENTER);
		lblBusDetails.setText("BUS DETAILS");
		lblBusDetails.setForeground(Color.RED);
		lblBusDetails.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblBusDetails.setBounds( 290, 20, 326, 67);
		contentPane.add(lblBusDetails);

		lblPassNo = new JLabel("Number of Passengers");
		lblPassNo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPassNo.setBounds(147, 407, 400, 35);
		contentPane.add(lblPassNo );

		String Passengers[]={"1","2"};        
		JComboBox pass_no = new JComboBox(Passengers);    
		pass_no.setBounds(373, 410,250, 37);
		pass_no.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(pass_no);        
		contentPane.setLayout(null);    
		contentPane.setSize(320,350);    
		contentPane.setVisible(true);  

		lblBusToFrom = new JLabel(source + " - " + destination,lblBusToFrom.CENTER);
		lblBusToFrom.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblBusToFrom.setBounds(290, 100, 326, 35);
		contentPane.add( lblBusToFrom);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JRadioButton btn = (JRadioButton) e.getSource();
				System.out.println("Selected Button = " + btn.getText());
				bus_id = btn.getName();
			}
		};


		try {
			System.out.println("Source : " + source);
			System.out.println("Destination : " + destination);
			System.out.println("Date : " + date);

			//Connect to the database
			connectDB connection = new connectDB();
			PreparedStatement st = (PreparedStatement) connection.getConnection()
					.prepareStatement("Select b_id,(max_seats-booked_seats) as available_seats,AC_OR_nonAC,Seater_OR_Sleeper,base_fare,bus_time from Bus where fromCity=? and toCity=? and bus_date=? ");

			st.setString(1, source);
			st.setString(2, destination);
			st.setString(3, date);


			ResultSet rs = st.executeQuery();
			ButtonGroup btn_grp = new ButtonGroup();
			int offset = 80;

			if (rs.next() == false) 
			{ 
				System.out.println("No buses found !!"); 
				JOptionPane.showMessageDialog(null,"No buses found !!");
			} 
			else 
			{ 
				do 
				{ 
					String bid = rs.getString("b_id");
					String aseats = rs.getString("available_seats");
					String ac = rs.getString("AC_OR_nonAC");
					String sleep = rs.getString("Seater_OR_Sleeper");
					String fare = rs.getString("base_fare");
					String btime = rs.getString("bus_time");
					System.out.println(count + ", "+ bid + ", "  + "Available Seats: " + aseats + ", " + ac + ", " + sleep + ", " + fare + ", " + btime);


					JRadioButton r1=new JRadioButton();
					r1.setText("Time: " + btime + "  " + "Fare: "  + fare);
					r1.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
					r1.setBounds(130, offset+110, 800, 40);
					r1.setName(bid);
					r1.addActionListener(listener);
					btn_grp.add(r1);
					contentPane.add(r1);   

					lblrad1 = new JLabel();
					lblrad1.setText("Available Seats:" + aseats + "   " + "Type: "  + ac + " " + sleep);
					lblrad1.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
					lblrad1.setBounds(152, offset+150, 800, 20);
					contentPane.add(lblrad1 );
					offset = offset + 120;
					count++;
				} while (rs.next()); 


				lblBusAvail = new JLabel((count-1) + " Bus Available",lblBusAvail.CENTER);
				lblBusAvail .setFont(new Font("Tahoma", Font.PLAIN, 32));
				lblBusAvail.setOpaque(true);
				lblBusAvail.setBackground(Color.LIGHT_GRAY);
				lblBusAvail .setBounds(290, 140, 300, 35);
				contentPane.add(lblBusAvail );

				//Next button
				JButton btnNxt = new JButton("Next\r\n");
				btnNxt.setBackground(UIManager.getColor("Button.disabledForeground"));
				btnNxt.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//String pass_no = PassNo.getText();
						String nopass = pass_no.getItemAt(pass_no.getSelectedIndex()).toString();
						personalDetails bo = new personalDetails(bus_id,nopass,username);
						bo.setTitle("Personal Details");
						bo.setVisible(true);
						dispose();
					}
				});
				btnNxt.setFont(new Font("Tahoma", Font.PLAIN, 35));
				btnNxt.setBounds(280, 480, 250, 40);
				contentPane.add(btnNxt);
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}