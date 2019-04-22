import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1, btn2;
    JPasswordField p1;

    public Login() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login Page");
        l1 = new JLabel("User Registration Form");
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Sans-Serif", Font.BOLD, 40));
        l2 = new JLabel("Username");
        l2.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        l3 = new JLabel("Password:");
        l3.setFont(new Font("Sans-Serif", Font.BOLD, 20));
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Login");
        btn2 = new JButton("Register");
        btn1.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        btn2.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        l1.setBounds(680, 250, 800, 50);
        l2.setBounds(680, 350, 200, 35);
        l3.setBounds(680, 390, 200, 35);
        tf1.setBounds(850, 350, 200, 40);
        p1.setBounds(850, 390, 200, 40);
        btn1.setBounds(850, 450, 80, 40);
        btn2.setBounds(940, 450, 100, 40);
        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(p1);
        add(btn1);
        add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            String username = (tf1.getText());
            char[] pass = p1.getPassword();
            String password = new String(pass);

            Database database = new Database();
            User user = new User();

            try {
                user = database.getUser(username, password);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (user != null) {
                int input = JOptionPane.showOptionDialog(btn1, "Login Successful", "Login Successful", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                if (input == 0) {
                    setVisible(false);
                    dispose();
                    new Index();
                }
            } else {
                JOptionPane.showMessageDialog(btn1, "Username / Password Error", "Username / Password Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            setVisible(false);
            dispose();
            new Register();
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        new Login();
    }
}