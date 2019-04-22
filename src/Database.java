//import java.sql.*;
//
//public class Database {
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_test", "root", "");
//        Statement st = con.createStatement();
//        ResultSet rs = st.executeQuery("select * from emp");
//        System.out.println("ID\tName\tAddress");
//        while (rs.next()) {
//            System.out.println(rs.getString("id") + "\t" + rs.getString("name") + "\t" + rs.getString("address"));
//        }
//        con.close();
//    }
//}

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Database {
    Connection con = null;
    private String DB_URL = "jdbc:mysql://localhost:3306/swing";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";

    /**
     * @return Connection
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }


    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Database Database = new Database();

        if (Database.getConnection() != null) {
            System.out.println("Connection Established");
        } else {
            System.out.println("Opps!!! Some Error Occurred!!! \n");
        }
        System.out.println("new SimpleDateFormat(\"yyyy-MM-dd\").format(new Date()) = " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public User getUser(String username, String password) throws SQLException {
        User user = null;
        String query = "select * from user where username=? and password=?";
        Connection connection = new Database().getConnection();
        PreparedStatement pstm = connection.prepareStatement(query);
        try {
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public boolean addUser(User user) throws SQLException {
        boolean flag = true;
        String query = "INSERT into user (`username`, `password`, `address`, `contact`) values (?,?,?,?)";
        Connection connection = new Database().getConnection();
        PreparedStatement pstm = connection.prepareStatement(query);
        try {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getContact());
            pstm.setString(4, user.getAddress());
            if (pstm.execute()) {
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<Teacher> getTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        String query = "select * from teacher";
        Connection connection = new Database().getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Teacher teacher;
        ResultSet rs;
        try {
            rs = Objects.requireNonNull(pstm).executeQuery();
            while(rs.next()){

                teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("faculty"),
                        rs.getString("address"),
                        rs.getString("email")
                );

                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("teacher = " + teachers);
        return teachers;
    }
}