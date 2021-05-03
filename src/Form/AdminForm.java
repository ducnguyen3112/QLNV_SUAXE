/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Form.Xuli.KetNoiDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author StarScream
 */
public class AdminForm extends javax.swing.JFrame {
    /** Creates new form AdminForm */
    public static String maNV="";
    public AdminForm() {
        initComponents();
        setSize(1168, 650);
        lbTenDN.setText(LoginForm.ten);
        setLocationRelativeTo(null);
    }
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement ps;
    public  void  showDuLieu()
    {
        try {
            tbDSNV.removeAll();
            String[] arr = {"Mã NV","Họ Tên","Ngày Sinh","Giới Tính","Chức Vụ","Trạng Thái"}; 
            
            DefaultTableModel model = new DefaultTableModel(arr,0);
            con = KetNoiDB.getConnection();
            String sql = "select MaNV,HoTen,CONVERT(varchar, NgaySinh, 105) as NgaySinh,"
                + " GioiTinh,ChucVu,TrangThai"
                + " from NHANVIEN";
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
            while(rs.next())
            {
                Vector vector = new Vector();
                vector.add(rs.getString("MaNV"));
                vector.add(rs.getString("HoTen"));
                vector.add(rs.getString("NgaySinh"));
                switch (rs.getInt("GioiTinh")) {
                    case 0 -> vector.add("Nam");
                    case 1 -> vector.add("Nữ");
                    case 2 -> vector.add("khác");
                }
                switch (rs.getInt("ChucVu")) {
                    case 0 -> vector.add("admin");
                    case 1 -> vector.add("Lễ tân");
                    case 2 -> vector.add("Nhân viên bảo dưỡng");
                    default -> {
                    }
                }
                switch(rs.getInt("TrangThai")){
                    case 0 ->vector.add("Đã nghỉ");
                    case 1 -> vector.add("Đang làm việc");
                }
                model.addRow(vector);
            }
            tbDSNV.setModel(model);
           // rs.close();
            //st.close();
           // con.close();
        } catch (SQLException e) {
        }
    }
//    public void loadDB(){
//        String sql="select MaNV,HoTen,CONVERT(varchar, NgaySinh, 105) as NgaySinh,"
//                + " GioiTinh,ChucVu,TrangThai,TenCV"
//                + " from NHANVIEN,CHUCVU where NHANVIEN.ChucVu=CHUCVU.MaCV";
//        
//        //DefaultTableModel model=(DefaultTableModel) tbDsnv.getModel();
//        tbDSNV.setDefaultEditor(Object.class, null);
//        DefaultTableModel model=(DefaultTableModel) tbDSNV.getModel();
//        
//        try {
//            con=KetNoiDB.getConnection();
//            st=con.createStatement();
//            rs=st.executeQuery(sql);
//            Vector data;
//            while (rs.next()) {                
//                data=new Vector();
//                maNV=rs.getString("MaNV");
//                data.addElement(maNV);
//                data.addElement(rs.getString("HoTen"));
//                data.addElement(rs.getString("NgaySinh"));
//                switch (rs.getInt("GioiTinh")) {
//                    case 0 -> data.addElement("Nam");
//                    case 1 -> data.addElement("Nữ");
//                    case 2 -> data.addElement("Khác");
//                }
//                data.addElement(rs.getString("TenCV"));
//                switch(rs.getInt("TrangThai")){
//                    case 0 ->data.addElement("Đã nghỉ");
//                    case 1 -> data.addElement("Đang làm việc");
//                }
//                model.addRow(data);
//            }
//            rs.close();
//            st.close();
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        pnMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbTenDN = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnQLND = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        btnDSNV = new javax.swing.JButton();
        btnPCDV = new javax.swing.JButton();
        btnCD = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        pnNV = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDSNV = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        btnTTNV = new javax.swing.JButton();
        btnThemNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        txtTenAD = new javax.swing.JLabel();
        btnQuayLai1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(getPreferredSize());

        jLayeredPane1.setLayout(new java.awt.CardLayout());

        pnMenu.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Chào!");
        pnMenu.add(jLabel2);
        jLabel2.setBounds(960, 60, 36, 15);

        lbTenDN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTenDN.setForeground(new java.awt.Color(255, 153, 0));
        lbTenDN.setText("admin");
        pnMenu.add(lbTenDN);
        lbTenDN.setBounds(1000, 50, 160, 30);

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 204, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CHƯƠNG TRÌNH QUẢN LÍ NHÂN VIÊN TRUNG TÂM BẢO HÀNH XE MÁY");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnMenu.add(jLabel4);
        jLabel4.setBounds(160, 190, 820, 40);

        btnQLND.setBackground(new java.awt.Color(255, 204, 51));
        btnQLND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_people_110px.png"))); // NOI18N
        btnQLND.setText("QUẢN LÍ NGƯỜI DÙNG");
        btnQLND.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnQLND.setPreferredSize(new java.awt.Dimension(150, 140));
        btnQLND.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnMenu.add(btnQLND);
        btnQLND.setBounds(490, 290, 150, 140);

        btnTK.setBackground(new java.awt.Color(255, 204, 51));
        btnTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_pie_chart_110px.png"))); // NOI18N
        btnTK.setText("THÔNG KÊ");
        btnTK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTK.setPreferredSize(new java.awt.Dimension(150, 140));
        btnTK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnMenu.add(btnTK);
        btnTK.setBounds(650, 290, 150, 140);

        btnDSNV.setBackground(new java.awt.Color(255, 204, 51));
        btnDSNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_send_hot_list_110px_1.png"))); // NOI18N
        btnDSNV.setText("DANH SÁCH NHÂN VIÊN");
        btnDSNV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDSNV.setPreferredSize(new java.awt.Dimension(150, 140));
        btnDSNV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDSNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDSNVMouseClicked(evt);
            }
        });
        pnMenu.add(btnDSNV);
        btnDSNV.setBounds(170, 290, 150, 140);

        btnPCDV.setBackground(new java.awt.Color(255, 204, 51));
        btnPCDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_request_service_110px_1.png"))); // NOI18N
        btnPCDV.setText("PHÂN CÔNG DỊCH VỤ");
        btnPCDV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPCDV.setPreferredSize(new java.awt.Dimension(150, 140));
        btnPCDV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnMenu.add(btnPCDV);
        btnPCDV.setBounds(330, 290, 150, 140);

        btnCD.setBackground(new java.awt.Color(255, 204, 51));
        btnCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_settings_110px_1.png"))); // NOI18N
        btnCD.setText("CÀI ĐẶT");
        btnCD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCD.setPreferredSize(new java.awt.Dimension(150, 140));
        btnCD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnMenu.add(btnCD);
        btnCD.setBounds(810, 290, 150, 140);

        jLabel5.setBackground(new java.awt.Color(255, 204, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/photo-1517999144091-3d9dca6d1e43.png"))); // NOI18N
        pnMenu.add(jLabel5);
        jLabel5.setBounds(0, 0, 1168, 604);

        jLayeredPane1.add(pnMenu, "card2");

        pnNV.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        tbDSNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Chức vụ", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tbDSNV);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnNV.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(0, 0, 32));
        jPanel5.setName(""); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 604));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_male_user_110px_1.png"))); // NOI18N
        jLabel1.setText("Aministrator");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 200, -1));

        btnQuayLai.setBackground(new java.awt.Color(0, 0, 36));
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_jog_reverse_50px.png"))); // NOI18N
        btnQuayLai.setText("QUAY LẠI");
        btnQuayLai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuayLai.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel5.add(btnQuayLai, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 200, -1));

        btnTTNV.setBackground(new java.awt.Color(0, 0, 36));
        btnTTNV.setForeground(new java.awt.Color(255, 255, 255));
        btnTTNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_profile_50px.png"))); // NOI18N
        btnTTNV.setText("THÔNG TIN NHÂN VIÊN");
        btnTTNV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTTNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btnTTNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTTNVMouseClicked(evt);
            }
        });
        jPanel5.add(btnTTNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 200, -1));

        btnThemNV.setBackground(new java.awt.Color(0, 0, 36));
        btnThemNV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_add_user_group_woman_man_50px.png"))); // NOI18N
        btnThemNV.setText("THÊM NHÂN VIÊN");
        btnThemNV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThemNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btnThemNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNVMouseClicked(evt);
            }
        });
        jPanel5.add(btnThemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 200, -1));

        btnXoaNV.setBackground(new java.awt.Color(0, 0, 36));
        btnXoaNV.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_denied_50px.png"))); // NOI18N
        btnXoaNV.setText("XOÁ NHÂN VIÊN");
        btnXoaNV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoaNV.setPreferredSize(new java.awt.Dimension(200, 50));
        btnXoaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaNVMouseClicked(evt);
            }
        });
        jPanel5.add(btnXoaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 200, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tìm kiếm:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 200, -1));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel5.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 200, -1));

        txtTenAD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtTenAD.setForeground(new java.awt.Color(255, 255, 255));
        txtTenAD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTenAD.setText("hello");
        jPanel5.add(txtTenAD, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 20));

        btnQuayLai1.setBackground(new java.awt.Color(0, 0, 36));
        btnQuayLai1.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_jog_reverse_50px.png"))); // NOI18N
        btnQuayLai1.setText("LÀM MỚI");
        btnQuayLai1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuayLai1.setPreferredSize(new java.awt.Dimension(200, 50));
        btnQuayLai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLai1MouseClicked(evt);
            }
        });
        jPanel5.add(btnQuayLai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 200, -1));

        pnNV.add(jPanel5, java.awt.BorderLayout.WEST);

        jLayeredPane1.add(pnNV, "card3");

        getContentPane().add(jLayeredPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnDSNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDSNVMouseClicked
       
        pnMenu.setVisible(false);
        pnNV.setVisible(true);
        showDuLieu();
    }//GEN-LAST:event_btnDSNVMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel model=(DefaultTableModel) tbDSNV.getModel();
        TableRowSorter<DefaultTableModel>  tr=new TableRowSorter<>(model);
        tbDSNV.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText().trim()));
    }//GEN-LAST:event_txtTimKiemKeyReleased
     
    private void btnTTNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTTNVMouseClicked
        int i=tbDSNV.getSelectedRow();
        if (i<0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một nhân viên để xem thông tin");
        }else{
            maNV=tbDSNV.getValueAt(i, 0).toString();
            new TTNV().setVisible(true);
            
        }
        
    }//GEN-LAST:event_btnTTNVMouseClicked

    private void btnXoaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaNVMouseClicked
        int res = JOptionPane.showConfirmDialog(null, "Bạn có Chắc Chắn Muốn Xóa ?");
        
        if(res == 0)
        {
        int position = tbDSNV.getSelectedRow();
        
        String data = tbDSNV.getModel().getValueAt(position, 0).toString();
        String data2 = tbDSNV.getModel().getValueAt(position, 1).toString();
        
        con = KetNoiDB.getConnection();
        try {
            
            String sql3 = "DELETE FROM dbo.[HOPDONG] WHERE MaNV = ?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setString(1, data);
            ps3.executeUpdate();
            
            String sql2 = "DELETE FROM dbo.[TAIKHOAN] WHERE MaNV = ?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setString(1, data);
            ps2.executeUpdate();
            
            
            String sql = "DELETE FROM dbo.[NHANVIEN] WHERE MaNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Bạn Đã Xoa Nhân Viên có Tên: "+ data2 +"  ra khỏi danh sách");
            showDuLieu();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_btnXoaNVMouseClicked

    private void btnThemNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNVMouseClicked
        ThemNV Themnv = new ThemNV();
        Themnv.setVisible(true);
    }//GEN-LAST:event_btnThemNVMouseClicked

    private void btnQuayLai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLai1MouseClicked
        showDuLieu();
    }//GEN-LAST:event_btnQuayLai1MouseClicked
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCD;
    private javax.swing.JButton btnDSNV;
    private javax.swing.JButton btnPCDV;
    private javax.swing.JButton btnQLND;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnQuayLai1;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTTNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTenDN;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnNV;
    private javax.swing.JTable tbDSNV;
    private javax.swing.JLabel txtTenAD;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
