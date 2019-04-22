import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Index extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JButton btn1, btn2;
    JPasswordField p1;

    public Index() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        String[] columns = {"ID", "Name", "Faculty", "Address","Email"};

        Database database = new Database();
        ArrayList<Teacher> teachers = database.getTeachers();


        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        Object[] rowData = new  Object[5];
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

        JLabel lblHeading = new JLabel("Teacher Index");
        lblHeading.setFont(new Font("Arial", Font.PLAIN,24));

        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(lblHeading,BorderLayout.PAGE_START);
        getContentPane().add(scrollPane,BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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