import java.sql.*;

public class ConnectToAccess_2008 {

    public static void main(String args[]) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:odbc:;DRIVER=Microsoft Access Driver (*.mdb);
                    DBQ=D:\\path_to_db\db.mdb;PWD=mypass","login","password");
            Statement command = conn.createStatement();
            ResultSet rs = command.executeQuery("select * FROM keyword");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }

            System.out.println("Connected To Access");

        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
