import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Log extends JFrame implements ActionListener {
    JLabel l1 = new JLabel("          Email Address");
    JTextField t1 = new JTextField(10);
    JLabel l2 = new JLabel("          Password");
    JTextField t2 = new JTextField(10);
    JLabel t3 = new JLabel();
    JButton b = new JButton("Login");
    String fno = "";
    String sno = "";

    public Log() {
        setLayout(new GridLayout(3, 3));
        setSize(500, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(t3);
        add(b);
        b.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = (t1.getText());
        String password = (t2.getText());

        Database database = new Database();
        User user = new User();

        try {
            user = database.getUser(username, password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if (user != null) {
            JOptionPane.showMessageDialog(null, "Login Successful");

        } else {
            t3.setText("          Username / Password Error");
            JOptionPane.showMessageDialog(null, "Username / Password Error","Username / Password Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        new Log();
    }
}