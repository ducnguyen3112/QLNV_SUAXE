/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLiDuLieu;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(KetNoiDB.class.getName()).log(Level.SEVERE, null, ex);
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
