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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author StarScream
 */
public class TTLForm extends javax.swing.JDialog {

    /** Creates new form TTLForm */
    private int namDuocChon, thangDuocChon;
    private HashMap NVvoiMaCong = new HashMap();
    private HashMap MaCongvoiNgayLam = new HashMap();

    public TTLForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        LocalDate localDate = LocalDate.now();
        if (localDate.getMonthValue() == 1 )
        {
            int tamp = localDate.getYear() - 1;
            chonNam.setValue(tamp);
            chonThang.setMonth(11);
        }
        chonNam.setValue(localDate.getYear());
        chonThang.setMonth(localDate.getMonthValue() - 2);
        namDuocChon = chonNam.getValue();
        thangDuocChon = chonThang.getMonth() + 1; 
        layDuLieuCong();
        hienThiLuong();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        chonNam = new com.toedter.components.JSpinField();
        chonThang = new com.toedter.calendar.JMonthChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bangLuong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(2, 28, 30));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BẢNG LƯƠNG");
        jLabel1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(44, 120, 115));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 80, 30));

        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 410, 70, 30));

        chonNam.setMaximum(9999);
        chonNam.setMinimum(1900);
        jPanel1.add(chonNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 90, 30));

        chonThang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel1.add(chonThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 100, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Chọn tháng:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 80, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Chọn năm:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 70, 30));

        bangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Mã công", "Thực lĩnh", "Đã thanh toán"
            }
        ));
        bangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bangLuongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bangLuong);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 820, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 440, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 90, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void layDuLieuCong() {
        String sql = "select MaCong,MaNV,NgayCong from CONG where (Thang = " + thangDuocChon + "and Nam =" + namDuocChon + ")";
        Connection ketNoi = KetNoiDB.getConnection();
        try {
            Statement st = ketNoi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            NVvoiMaCong.clear();
            MaCongvoiNgayLam.clear();
            while (rs.next()) {
                NVvoiMaCong.put(rs.getString("MaNV"), rs.getString("MaCong"));
                MaCongvoiNgayLam.put(rs.getString("MaCong"), rs.getInt("NgayCong"));
            }
            rs.close();
            st.close();
            ketNoi.close();
        } catch (SQLException e) {
        }

    }

    public static int traVeHeSoLuong(String maNv) {
        String sql = "select HSL from HOPDONG where MaNV = '" + maNv + "'";
        Connection ketNoi = KetNoiDB.getConnection();
        int tamp = 0;
        try {
            Statement st = ketNoi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tamp = rs.getInt("HSL");
            }
            rs.close();
            st.close();
            ketNoi.close();
        } catch (SQLException e) {
        }
        return tamp;
    }

    public static String traVeMaHD(String maNv) {
        String sql = "select MaHD from HOPDONG where MaNV = '" + maNv + "'";
        Connection ketNoi = KetNoiDB.getConnection();
        String tamp = null;
        try {
            Statement st = ketNoi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tamp = rs.getString("MaHD");
            }
            rs.close();
            st.close();
            ketNoi.close();
        } catch (SQLException e) {
        }
        return tamp;

    }

    public static int traVeTienThuong(String maNv) {
        String sql = "select SoTien from CTTHUONG where MaNV = '" + maNv + "'";
        Connection ketNoi = KetNoiDB.getConnection();
        int tamp = 0;
        try {
            Statement st = ketNoi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tamp += rs.getInt("SoTien");
            }
            rs.close();
            st.close();
            ketNoi.close();
        } catch (SQLException e) {
        }
        return tamp;
    }

    public static int traVeTienPhat(String maNv) {
        String sql = "select SoTien from CTPHAT where MaNV = '" + maNv + "'";
        Connection ketNoi = KetNoiDB.getConnection();
        int tamp = 0;
        try {
            Statement st = ketNoi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tamp += rs.getInt("SoTien");
            }
            rs.close();
            st.close();
            ketNoi.close();
        } catch (SQLException e) {
        }
        return tamp;
    }

    public void hienThiLuong() {
        layDuLieuCong();
        Connection ketNoi = KetNoiDB.getConnection();
        DefaultTableModel model = (DefaultTableModel) bangLuong.getModel();
        model.setRowCount(0);
        int ktHienThi = 0;
        try {
            for (Object i : NVvoiMaCong.keySet()) {
                String sql = "select MaNV,ThucLinh,TrangThai from THANHTOANLUONG where MaTTL = 'TTL" + NVvoiMaCong.get(i) + "'";
                Statement st = ketNoi.createStatement();
                ResultSet rs = st.executeQuery(sql);
                Vector tamp;
                while (rs.next()) {
                    tamp = new Vector();
                    String maNv = rs.getString("MaNV");
                    tamp.addElement(maNv);
                    tamp.addElement(PhanCongForm.getTenNhanVien(maNv));
                    tamp.addElement(NVvoiMaCong.get(i));
                    tamp.addElement(rs.getString("ThucLinh"));
                    switch (rs.getInt("TrangThai")) {
                        case 0:
                            tamp.addElement("Chưa thanh toán");
                            break;
                        case 1:
                            tamp.addElement("Đã thanh toán");
                        default:
                            break;
                    }
                    model.addRow(tamp);
                    ktHienThi = 1;
                 
                }
                st.close();
                rs.close();
            }
            ketNoi.close();
        } catch (Exception e) {
        }
       if(ktHienThi == 0 ){
           JOptionPane.showMessageDialog(rootPane, "No data");
       } 
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        namDuocChon = chonNam.getValue();
        thangDuocChon = chonThang.getMonth() + 1;
        hienThiLuong();
         LocalDate localDate = LocalDate.now();
            if(thangDuocChon <= localDate.getMonthValue()){
                hienThiLuong();
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Vượt quá ngày của hiện tại");
            }

    }//GEN-LAST:event_jButton1ActionPerformed
    public void thanhToanLuong(String maCong) {
        String sql = "UPDATE THANHTOANLUONG SET TrangThai = ? where MaTTL = ?";
        Connection ketNoi = KetNoiDB.getConnection();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setString(2, "TTL" + maCong);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (Exception e) {
        }

    }
    private void bangLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bangLuongMouseClicked
        // TODO add your handling code here:
        int index = bangLuong.getSelectedRow();
        String check = (String) bangLuong.getValueAt(index, 4);
        if (check.equals("Chưa thanh toán")) {
            int ktra = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc trả thanh toán lương không ??");
            if (ktra == 0) {
                check = (String) bangLuong.getValueAt(index, 2);
                thanhToanLuong(check);
                hienThiLuong();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nhân viên đã được thanh toán");
        }
    }//GEN-LAST:event_bangLuongMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TTLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTLForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TTLForm dialog = new TTLForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangLuong;
    private com.toedter.components.JSpinField chonNam;
    private com.toedter.calendar.JMonthChooser chonThang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
