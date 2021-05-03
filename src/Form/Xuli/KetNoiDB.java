/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Xuli;
import Form.ThemNV;
import java.sql.*;
import java.text.SimpleDateFormat;
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
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return con;
    }
    public static String taoMaNV(int cv){
        String sql="select MAX(maNV)\n" +
        "from NHANVIEN where ChucVu= "+cv;
        String m="";
        Connection con=KetNoiDB.getConnection();
        String t2="";
        String t1="";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                m=rs.getString(1);
            }
            if (m==null) {
                if (cv==0) {
                    m="ad0";
                }else if (cv==1) {
                    m="lt0";
                }else if (cv==2) {
                    m="nv0";
                }
            }else{
            t1=m.substring(0, 2);
            t2=m.substring(2);
            int i=Integer.parseInt(t2);
            i++;
            m=t1+Integer.toString(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    public static void main(String[] args) {
        Connection con=getConnection();
        String t=taoMaNV(0);
      
        if(con!=null){
            System.out.println("Thanh cong");
            System.out.println(t);
        }
        java.util.Date date=new java.util.Date();
        SimpleDateFormat datefm=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(date);
    }
}
