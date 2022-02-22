package busBooking;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class changePassword extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JLabel lblEnterNewPassword;

    public changePassword(String name) {
        setBounds(230, 300, 600, 234);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textField.setBounds(320, 55, 200, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnok = new JButton(" OK ");
        btnok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pstr = textField.getText();
                try {
                    System.out.println("update password name " + name);
                    System.out.println("update password");

                  ///Connect to the database
                	connectDB connection = new connectDB();
                    PreparedStatement st = (PreparedStatement) connection.getConnection()
                        .prepareStatement("Update Users set pass_word=? where username=?");

                    st.setString(1, pstr);
                    st.setString(2, name);
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Password has been successfully changed");
                    dispose();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
        btnok.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnok.setBackground(new Color(240, 240, 240));
        btnok.setBounds(220, 127, 200, 30);
        contentPane.add(btnok);

        lblEnterNewPassword = new JLabel("Enter New Password :");
        lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblEnterNewPassword.setBounds(60, 50, 500, 30);
        contentPane.add(lblEnterNewPassword);
    }
}
