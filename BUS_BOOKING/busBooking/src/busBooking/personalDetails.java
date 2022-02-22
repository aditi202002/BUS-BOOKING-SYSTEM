package busBooking;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class personalDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField PName;
	private JTextField PAge;
	private JLabel lblEnterPName;
	private JLabel lblEnterPAge;
	private JLabel lblPerDetails;
	private JLabel lblPassNo;
	private int _id;
	private String _name;
	private String _age;
	private ArrayList<personalDetails> pdetails = new ArrayList<personalDetails>();
	ArrayList<JTextField> listName = new ArrayList<JTextField>();
	ArrayList<JTextField> listAge = new ArrayList<JTextField>();

	public personalDetails(int pass_id,String pass_name,String pass_age)
	{
		this._id = pass_id;
		this._name = pass_name;
		this._age = pass_age;
	}

	public int getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getAge() {
		return _age;
	}

	public personalDetails(String bus_id,String pass_no,String username) {

		System.out.println("I am on personal details page & my bus id is :" + bus_id + "and pass number is " + pass_no);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 80, 900, 600);;
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		int offset = 80;
		for(int i = 1;i <= Integer.parseInt(pass_no);i++)
		{
			PName = new JTextField();
			PName.setFont(new Font("Tahoma", Font.PLAIN, 15));
			PName.setBounds(373, 80 + offset, 250, 35);
			PName.putClientProperty("name", String.valueOf(i));
			contentPane.add(PName);
			listName.add(PName);
			PName.setColumns(10);

			PAge = new JTextField();
			PAge.setFont(new Font("Tahoma", Font.PLAIN, 15));
			PAge.setBounds(373, 120 + offset,250,35);
			PAge.putClientProperty("age", String.valueOf(i));
			contentPane.add(PAge);
			listAge.add(PAge);
			PAge.setColumns(10);

			lblPassNo = new JLabel("Passenger" + i,lblPassNo.CENTER);
			lblPassNo.setOpaque(true);
			lblPassNo.setBackground(Color.LIGHT_GRAY);
			lblPassNo.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblPassNo.setBounds(267, 20 + offset, 326, 30);
			contentPane.add(lblPassNo);

			lblEnterPName = new JLabel("Enter Name");
			lblEnterPName.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEnterPName.setBounds(110,offset+80, 326, 30);
			contentPane.add(lblEnterPName);

			lblEnterPAge = new JLabel("Enter Age");
			lblEnterPAge.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEnterPAge.setBounds(110, offset+120, 326, 30);
			contentPane.add(lblEnterPAge);
			offset = offset + 150;
		}

		//Proceed button
		JButton btnNxt = new JButton("Proceed\r\n");
		btnNxt.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnNxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0;i < Integer.parseInt(pass_no);i++)
				{
					String name = listName.get(i).getText();
					String age = listAge.get(i).getText();
					System.out.println("----------------");
					System.out.println(name);
					System.out.println(age);
					personalDetails pd = new personalDetails(i,name,age);
					pdetails.add(pd);
				}
				paymentDetails bo = new paymentDetails(bus_id,pdetails,username);
				bo.setTitle("Payment Details");
				bo.setVisible(true);
				dispose();
			}
		});
		btnNxt.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnNxt.setBounds(310, 420, 250, 50);
		contentPane.add(btnNxt);

		lblPerDetails = new JLabel("PERSONAL DETAILS");
		lblPerDetails.setForeground(Color.RED);
		lblPerDetails.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblPerDetails.setBounds( 270, 20, 600, 60);
		contentPane.add(lblPerDetails);

	}
}

