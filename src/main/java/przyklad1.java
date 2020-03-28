import javax.xml.transform.Result;
import java.sql.*;

public class przyklad1 {


    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/shop?serverTimezone=CET";
        String user = "root";
        String password = "test";
        String query = "select USR_FIRSTNAME, USR_LASTNAME  from user;";
        try(Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                System.out.println(resultSet.getString("USR_FIRSTNAME"));
                System.out.println(resultSet.getString("USR_LASTNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
