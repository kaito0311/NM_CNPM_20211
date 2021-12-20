package ConnectDB;
import java.sql.Connection;
import ChangeUser.CreatePass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
 
public class StatementExecuteQueryExample {
 
    public static void main(String[] args) throws SQLException {
        String sqlSelect = "SELECT * FROM [User].Password WHERE UserID = 1";
        try (
                Connection con = ConnectToDB.openConnection();
                Statement st = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sqlSelect);
            ) {
 
            while (rs.next()) {
            	String enterPass = "thangquoc";
            	String passHash = rs.getString("passwordHash");
            	String passSalt = rs.getString("passwordSalt");
            	byte[] salt = Base64.getDecoder().decode(passSalt);
            	String checkHash = CreatePass.get_SHA_512_SecurePassword(enterPass, salt);
            	if (checkHash.equals(passHash)) {
            		showUserInfo(rs);
            	} else {
            		System.out.println("Mat khau sai");
            	}
                
            }
 
//            System.out.println("\n=== Move to previous row ===");
//            while (rs.previous()) {
//                showUserInfo(rs);
//            }
//             
//            System.out.println("\n=== Move to last row ===");
//            rs.last();
//            showUserInfo(rs);
//             
//            System.out.println("\n=== Move to first row ===");
//            rs.first();
//            showUserInfo(rs);
        }
    }
     
    private static void showUserInfo(ResultSet rs) throws SQLException {
        System.out.println("UserID: " + rs.getString("UserID"));
        System.out.println("passwordHash: " + rs.getString("passwordHash"));
        System.out.println("passwordSalt: " + rs.getString("passwordSalt"));
        System.out.println("ModifiedDate: " + rs.getString("ModifiedDate"));
        System.out.println("---");
    }
}