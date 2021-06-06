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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author StarScream
 */
public class PhatForm extends javax.swing.JDialog {

    /** Creates new form PhatForm */
    private String MaNVPhat = "";
    private String MaPhat = "";
    private HashMap ListPhat;

    public PhatForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        MaNVPhat = AdminForm.maNV;
        loadDaTa();
        setLocationRelativeTo(null);

    }

    public void loadPhat() {
        CBPhat.removeAllItems();
        ListPhat = new HashMap();
        ListPhat.clear();
        String sql = "select MaLoi,TenLoi from PHAT";
        Connection ketNoi = KetNoiDB.getConnection();
        try {
            Statement st = ketNoi.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ListPhat.put(rs.getString("MaLoi"), rs.getString("TenLoi"));
                CBPhat.addItem(rs.getString("TenLoi"));
            }
            CBPhat.addItem("Thêm loại phạt");
            st.close();
            rs.close();
            ketNoi.close();
        } catch (Exception e) {
        }

    }

    public void loadDaTa() {
        txtMaNV.setText(MaNVPhat);
        txtTen.setText(PhanCongForm.getTenNhanVien(MaNVPhat));
        loadPhat();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        moTa = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ngayPhat = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jTienPhat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CBPhat = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("XỬ PHẠT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 54));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã nhân viên:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 360, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tiền phạt");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        txtTen.setEditable(false);
        txtTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txtTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 360, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Họ và tên:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nội dung xử phạt:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        moTa.setColumns(20);
        moTa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moTa.setRows(5);
        jScrollPane1.setViewportView(moTa);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 360, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Lưu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 70, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Huỷ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 70, -1));

        ngayPhat.setDateFormatString("dd/MM/yyyy");
        ngayPhat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(ngayPhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 260, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Ngày xử phạt:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jTienPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTienPhatActionPerformed(evt);
            }
        });
        getContentPane().add(jTienPhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 260, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tên xử phạt:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        CBPhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBPhatActionPerformed(evt);
            }
        });
        getContentPane().add(CBPhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, 260, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTienPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTienPhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTienPhatActionPerformed

    public void themPhat(String s) {
        String sql = "insert into PHAT(MaLoi,TenLoi) values(?,?)";
        Connection ketNoi = KetNoiDB.getConnection();
        Random rd = new Random();
        MaPhat = "Phat" + (String.valueOf(rd.nextInt(1000)));
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, MaPhat);
            ps.setString(2, s);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi!Không thành công");
            }
            ps.close();
            ketNoi.close();
        } catch (Exception e) {
        }

    }

    public String traVeMaPhaṭ̣(String s) {
        for (Object i : ListPhat.keySet()) {
            if (s.equals(ListPhat.get(i))) {
                return (String) i;
            }

        }
        return null;
    }

    public void luuDuLieu() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf1.format(ngayPhat.getDate());
        String soTien = jTienPhat.getText();
        if (ChamCongForm.kiemTraNhapSo(soTien)) {
            int Tien = Integer.parseInt(soTien);
            String sql = "INSERT INTO CTPHAT(MaNV,MaLoi,CTPL,SoTien,NgayPhat)  VALUES(?,?,?,?,?)";
            Connection ketNoi = KetNoiDB.getConnection();
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ps.setString(1, MaNVPhat);
                ps.setString(2, MaPhat);
                ps.setString(3, moTa.getText());
                ps.setInt(4, Tien);
                ps.setString(5, date);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Hoàn thành phạt");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thất bại");
                      this.dispose();
                }
                ps.close();
                ketNoi.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
            this.dispose();   
        } else {
            JOptionPane.showMessageDialog(rootPane, "Nhập vào phải là số nguyên");
        }

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        luuDuLieu();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CBPhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBPhatActionPerformed
        // TODO add your handling code here:
        if (CBPhat.getSelectedItem().equals("Thêm loại phạt")) {
            String inputPhat = JOptionPane.showInputDialog("Thêm hình phạt");
            if (inputPhat != null) {
                themPhat(inputPhat);
                loadPhat();
            }
        } else {
            MaPhat = traVeMaPhaṭ̣((String) CBPhat.getSelectedItem());
        }
    }//GEN-LAST:event_CBPhatActionPerformed

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
            java.util.logging.Logger.getLogger(PhatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhatForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PhatForm dialog = new PhatForm(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> CBPhat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTienPhat;
    private javax.swing.JTextArea moTa;
    private com.toedter.calendar.JDateChooser ngayPhat;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
