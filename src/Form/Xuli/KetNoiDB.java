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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author StarScream
 */
public class KetNoiDB {

    public static Connection getConnection() {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLNV_SUAXE";
        String user = "sa";
        String passwd = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, passwd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static String taoMaNV(int cv) {
        String sql = "select MAX(maNV)\n"
                + "from NHANVIEN where ChucVu= " + cv;
        String m = "";
        Connection con = KetNoiDB.getConnection();
        String t2 = "";
        String t1 = "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = rs.getString(1);
            }
            if (m == null) {
                if (cv == 0) {
                    m = "ad0";
                } else if (cv == 1) {
                    m = "lt0";
                } else if (cv == 2) {
                    m = "nv0";
                }
            } else {
                t1 = m.substring(0, 2);
                t2 = m.substring(2);
                int i = Integer.parseInt(t2);
                i++;
                m = t1 + Integer.toString(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public static void xoaNhanVien(JTable table) {
        Connection con = getConnection();
        int op = JOptionPane.showConfirmDialog(null, "Bạn có Chắc Chắn Muốn Xóa ?");
        if (op == 0) {
            int r = table.getSelectedRow();
            String data = table.getModel().getValueAt(r, 0).toString();
            String sql1 = "select * from CT_SDDV where MaNV = '" + data + "'";
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql1);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(table, "Nhân viên " + data + " đang "
                            + "làm việc không thể xoá!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(KetNoiDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql2 = "select * from XE where NVTN = '" + data + "'";
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql2);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(table, "Nhân viên " + data + " đang "
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

                JOptionPane.showMessageDialog(null, "Bạn Đã Xoa Nhân Viên có Tên: " + data + "  ra khỏi danh sách");
            } catch (HeadlessException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void deleteTableData(DefaultTableModel model) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static String tinhTongCot(JTable tb, int cl) {
        int total = 0;
        for (int i = 0; i < tb.getRowCount(); i++) {
            total = total + Integer.parseInt(tb.getValueAt(i, cl).toString());
        }
        String tong = String.valueOf(total);
        return tong;
    }

    public static void main(String[] args) {
        String ngay="31-12-2000";
           try {
            java.util.Date hanHD=new SimpleDateFormat("dd-mm-yyyy").parse(ngay);
               System.out.println(hanHD);
        } catch (ParseException ex) {
            
        }
            //        String text;
//        Scanner sc=new Scanner(System.in);
//        text=sc.nextLine();
//        if (text.matches("^[a-zA-Z]%")) {
//            System.out.println(text);
//        }else{
//            System.out.println("khong");
//            System.out.println(text);
//        }
       
        
        
        
    }

}
