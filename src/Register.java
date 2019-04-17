import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6;
    JTextField tf1, tf2, tf3;
    JButton btn1, btn2;
    JPasswordField p1, p2;

    Register() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Register User");
        l1 = new JLabel("User Registration Form");
        l1.setForeground(Color.GRAY);
        l1.setFont(new Font("Sans-Serif", Font.BOLD, 22));
        l2 = new JLabel("Username");
        l3 = new JLabel("Create Password:");
        l4 = new JLabel("Confirm Password:");
        l5 = new JLabel("Address:");
        l6 = new JLabel("Contact:");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        p2 = new JPasswordField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        btn1 = new JButton("Register");
        btn2 = new JButton("Login");
        l1.setBounds(680, 300, 400, 30);
        l2.setBounds(650, 350, 200, 30);
        l3.setBounds(650, 390, 200, 30);
        l4.setBounds(650, 430, 200, 30);
        l5.setBounds(650, 470, 200, 30);
        l6.setBounds(650, 510, 200, 30);
        tf1.setBounds(850, 350, 200, 30);
        p1.setBounds(850, 390, 200, 30);
        p2.setBounds(850, 430, 200, 30);
        tf2.setBounds(850, 480, 200, 30);
        tf3.setBounds(850, 520, 200, 30);
        btn1.setBounds(850, 580, 100, 30);
        btn2.setBounds(960, 580, 80, 30);
        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(p1);
        add(l4);
        add(p2);
        add(l5);
        add(tf2);
        add(l6);
        add(tf3);
        add(btn1);
        add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            String username = tf1.getText();
            char[] password1 = p1.getPassword();
            char[] password2 = p2.getPassword();
            String pass1 = new String(password1);
            String pass2 = new String(password2);
            String address = tf2.getText();
            String contact = tf3.getText();

            if (pass1.length() <= 4 || pass2.length() <= 4) {
                JOptionPane.showMessageDialog(btn1, "Please enter a password with 5 or more characters", "Password Length Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (pass1.equalsIgnoreCase(pass2)) {
                    Database database = new Database();
                    User user = new User();

                    user.username = username;
                    user.password = pass1;
                    user.address = address;
                    user.contact = contact;

                    try {
                        System.out.println("called");
                        boolean success = database.addUser(user);
                        if (success) {
                            int input = JOptionPane.showOptionDialog(btn1, "User Registration Successful", "Registration Successful", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                            if (input == 0) {
                                setVisible(false);
                                dispose();
                                new Login();

                            }

                        } else {
                            JOptionPane.showMessageDialog(btn1, "User Registration Failed . Please Try again", "Registration Failed", JOptionPane.ERROR_MESSAGE);

                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(btn1, "Both Password doesn't Match", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            setVisible(false);
            dispose();
            new Login();
        }
    }

    public static void main(String args[]) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        new Register();

    }
}