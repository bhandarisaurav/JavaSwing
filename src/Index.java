import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.awt.Font.TRUETYPE_FONT;

public class Index extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1, btn2;
    JPasswordField p1;

    Index() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        String[] columns = {"ID", "Name", "Faculty", "Address", "Email"};

        Database database = new Database();
        ArrayList<Teacher> teachers = database.getTeachers();


        JTable table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        Object[] rowData = new Object[5];
        for (Teacher teacher : teachers) {

            rowData[0] = teacher.getId();
            rowData[1] = teacher.getName();
            rowData[2] = teacher.getFaculty();
            rowData[3] = teacher.getAddress();
            rowData[4] = teacher.getEmail();
            model.addRow(rowData);
        }
        table.setModel(model);


        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);


        JLabel lblHeading = new JLabel("          Teacher Index");
        lblHeading.setFont(new Font("Arial", Font.PLAIN, 24));

        getContentPane().setLayout(new BorderLayout());

        JPanel topHeader = new JPanel(new GridLayout(1, 5));

        JButton edit = new JButton("Edit Records");
        JButton delete = new JButton("Delete Records");
        JButton reload = new JButton("Reload");
        JButton add = new JButton("Add Records");

//        edit.setPreferredSize(new Dimension(40, 40));
        reload.setFont(new Font("Arial", Font.PLAIN, 18));
        edit.setFont(new Font("Arial", Font.PLAIN, 18));
        delete.setFont(new Font("Arial", Font.PLAIN, 18));
        add.setFont(new Font("Arial", Font.PLAIN, 18));


        topHeader.add(lblHeading);
        topHeader.add(reload);
        topHeader.add(add);


        topHeader.add(edit);
        topHeader.add(delete);

        getContentPane().add(topHeader, BorderLayout.BEFORE_FIRST_LINE);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String ID = JOptionPane.showInputDialog(null, "Enter the ID of the Record you want to delete?", null);
                try {
                    database.deleteTeachers(ID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                dispose();
                new Index();
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String ID = JOptionPane.showInputDialog(null, "Enter the ID of the Record you want to edit?", null);
                try {
                    new EDIT(ID);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
                dispose();
                new Index();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ADD();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        new Index();
    }
}