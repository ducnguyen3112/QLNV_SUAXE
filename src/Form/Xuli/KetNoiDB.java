/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Xuli;
import Form.AdminForm;
import Form.ThemNV;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
    public static void xoaNhanVien(JTable table){
        Connection con=getConnection();
        int op=JOptionPane.showConfirmDialog(null, "Bạn có Chắc Chắn Muốn Xóa ?");
        if(op == 0){
            int r=table.getSelectedRow();
            String data= table.getModel().getValueAt(r, 0).toString();
            String sql1="select * from CT_SDDV where MaNV = '"+data+"'";
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql1);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(table, "Nhân viên "+data+" đang "
                            + "làm việc không thể xoá!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(KetNoiDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql2="select * from XE where NVTN = '"+data+"'";
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql2);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(table, "Nhân viên "+data+" đang "
                            + "làm việc không thể xoá!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(KetNoiDB.class.getName()).log(Level.SEVERE, null, ex);
            }
             try {
            String sql3 = "DELETE FROM dbo.[HOPDONG] WHERE MaNV = ?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setString(1, data);
            ps3.executeUpdate();
            
            String sql4 = "DELETE FROM dbo.[TAIKHOAN] WHERE MaNV = ?";
            PreparedStatement ps4 = con.prepareStatement(sql4);
            ps4.setString(1, data);
            ps4.executeUpdate();
            String sql5 = "DELETE FROM dbo.[NHANVIEN] WHERE MaNV = ?";
            PreparedStatement ps5 = con.prepareStatement(sql5);
            ps5.setString(1, data);
            ps5.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Bạn Đã Xoa Nhân Viên có Tên: "+ data+"  ra khỏi danh sách");
             }catch(HeadlessException | SQLException ex){
                 ex.printStackTrace();
             }
        }
        
//    int res = JOptionPane.showConfirmDialog(null, "Bạn có Chắc Chắn Muốn Xóa ?");
//        if(res == 0)
//        {
//        int position = table.getSelectedRow();
//        
//        String data = table.getModel().getValueAt(position, 0).toString();
//        String data2 = table.getModel().getValueAt(position, 1).toString();
//        con = KetNoiDB.getConnection();
//        String sql="select * from CT_SDDV where MaNV = '"+data+"'";
//        String sql0="select * from XE where MaNV = '"+data+"'";
//            try {
//                st=con.createStatement();
//                rs=st.executeQuery(sql0);
//                if (rs.next()) {
//                    JOptionPane.showMessageDialog(this, "Nhân viên "+data+" đang "
//                            + "làm việc không thể xoá!");
//                    rs.close();
//                    st.close();
//                    con.close();
//                }
//                        } catch (SQLException ex) {
//                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                st=con.createStatement();
//                rs=st.executeQuery(sql);
//                if (rs.next()) {
//                    JOptionPane.showMessageDialog(this, "Nhân viên "+data+" đang "
//                            + "làm việc không thể xoá!");
//                    rs.close();
//                    st.close();
//                    con.close();
//                }else{
//                    try {
//            String sql3 = "DELETE FROM dbo.[HOPDONG] WHERE MaNV = ?";
//            PreparedStatement ps3 = con.prepareStatement(sql3);
//            ps3.setString(1, data);
//            ps3.executeUpdate();
//            
//            String sql2 = "DELETE FROM dbo.[TAIKHOAN] WHERE MaNV = ?";
//            PreparedStatement ps2 = con.prepareStatement(sql2);
//            ps2.setString(1, data);
//            ps2.executeUpdate();
//            String sql1 = "DELETE FROM dbo.[NHANVIEN] WHERE MaNV = ?";
//            PreparedStatement ps = con.prepareStatement(sql1);
//            ps.setString(1, data);
//            ps.executeUpdate();
//            
//            JOptionPane.showMessageDialog(null, "Bạn Đã Xoa Nhân Viên có Tên: "+ data2 +"  ra khỏi danh sách");
//            showDuLieu();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//        }
    }
    public static void main(String[] args) {
        String text;
        Scanner sc=new Scanner(System.in);
        text=sc.nextLine();
        if (text.matches("^[0]{1}[0-9]{9}")) {
            System.out.println(text);
        }else{
            System.out.println("khong");
            System.out.println(text);
        }
    }
}
