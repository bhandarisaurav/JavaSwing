import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

public class ADD {
    public ADD() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 2, 15, 15);
//        gbc.weighty = 0.01;                 // allows vertical dispersion
        JLabel j1 = new JLabel("Name :");
        JLabel j2 = new JLabel("Faculty :");
        JLabel j3 = new JLabel("Address :");
        JLabel j4 = new JLabel("Email :");

        JButton add = new JButton("ADD");

        JTextField t1 = new JTextField(16);
        JTextField t2 = new JTextField(16);
        JTextField t3 = new JTextField(16);
        JTextField t4 = new JTextField(16);

        j1.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        j2.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        j3.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        j4.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        add.setFont(new Font("Sans-Serif", Font.BOLD, 15));


        addComponents(j1, t1, panel, gbc);
        addComponents(j2, t2, panel, gbc);
        addComponents(j3, t3, panel, gbc);
        addComponents(j4, t4, panel, gbc);
        addComponent(add, panel, gbc);


        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(panel);
        f.setSize(600, 400);
        f.setVisible(true);
        f.setTitle("Add Teacher's Data");

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Teacher teacher = new Teacher();
                Database database = new Database();
                String email = t4.getText();

                teacher.setName(t1.getText());
                teacher.setFaculty(t2.getText());
                teacher.setAddress(t1.getText());
                teacher.setEmail(email);


                boolean results = true;
                try {
                    InternetAddress emailAddress = new InternetAddress(email);
                    emailAddress.validate();
                } catch (AddressException ex) {
                    results = false;
                }

                if (!results) {
                    JOptionPane.showMessageDialog(add, "Please Enter a valid Email!!!", "Email Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        boolean result = database.addTeacher(teacher);
                        if (result) {
                            int input = JOptionPane.showOptionDialog(null, "Data Added Successfully", "Addition Successful", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                            if (input == 0) {
                                f.setVisible(false);
                                f.dispose();
                                new Index();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Data Addition Failed . Please Try again", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                            f.setVisible(false);
                            f.dispose();
                            new Index();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void addComponent(JButton add, JPanel p, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(add, gbc);

    }

    private void addComponents(JLabel label, JTextField tf, JPanel p,
                               GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        p.add(label, gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        p.add(tf, gbc);
    }

    public static void main(String[] args) {
        new ADD();
    }
}