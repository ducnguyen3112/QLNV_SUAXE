/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Xuli;
import java.sql.*;
/**
 *
 * @author StarScream
 */
public class KetNoiDB {
    public static Connection getConnection(){
        Connection con=null;
        String url="jdbc:sqlserver://localhost:1433;databaseName=QLNV_SUAXE";
        String user="sa";
        String passwd="123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con=DriverManager.getConnection(url, user, passwd);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return con;
    }
    public static void main(String[] args) {
        Connection con=getConnection();
        if(con!=null){
            System.out.println("Thanh cong");
        }
    }
}
