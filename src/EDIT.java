import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

public class EDIT {

    EDIT(String id) throws SQLException {

        System.out.println("id = " + id);

        Database database = new Database();
        Teacher teacher;
        teacher = database.getTeacher(id);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 2, 15, 15);
//        gbc.weighty = 0.01;                 // allows vertical dispersion

        JLabel j1 = new JLabel("Name :");
        JLabel j2 = new JLabel("Faculty :");
        JLabel j3 = new JLabel("Address :");
        JLabel j4 = new JLabel("Email :");
        JButton edit = new JButton("EDIT");

        JTextField t1 = new JTextField(16);
        JTextField t2 = new JTextField(16);
        JTextField t3 = new JTextField(16);
        JTextField t4 = new JTextField(16);

        t1.setText(teacher.getName());
        t2.setText(teacher.getFaculty());
        t3.setText(teacher.getAddress());
        t4.setText(teacher.getEmail());

        j1.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        j2.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        j3.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        j4.setFont(new Font("Sans-Serif", Font.BOLD, 15));
        edit.setFont(new Font("Sans-Serif", Font.BOLD, 15));


        addComponents(j1, t1, panel, gbc);
        addComponents(j2, t2, panel, gbc);
        addComponents(j3, t3, panel, gbc);
        addComponents(j4, t4, panel, gbc);
        addComponent(edit, panel, gbc);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(panel);
        f.setSize(600, 400);
        f.setVisible(true);
        f.setTitle("Edit Teacher's Data");

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt)  {

                Teacher teacher = new Teacher();
                Database database = new Database();
                String email = t4.getText();

                teacher.setId(Integer.parseInt(id));
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
                    JOptionPane.showMessageDialog(edit, "Please Enter a valid Email!!!", "Email Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean result = false;
                    try {
                        result = database.editTeacher(teacher);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if (result) {
                        int input = JOptionPane.showOptionDialog(edit, "Data Edited Successfully", "Edit Successful", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
                        if (input == 0) {
                            f.setVisible(false);
                            f.dispose();
                            new Index();
                        }

                    } else {
                        JOptionPane.showMessageDialog(edit, "Data Edit Failed . Please Try again", "Edit Failed", JOptionPane.ERROR_MESSAGE);
                        f.setVisible(false);
                        f.dispose();
                        new Index();
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

    public static void main(String[] args) throws SQLException {
        new EDIT("");
    }
}