/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;
import Form.Xuli.KetNoiDB;
import java.awt.Image;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author StarScream
 */
public class TTNV extends javax.swing.JFrame {
    
    /** Creates new form TTNV */
    public TTNV() {
        initComponents();
       loadDB();
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);
        btnTaiAnh.setVisible(false);
        txtCMND.setEditable(false);
        cbChucVu.setEnabled(false);
        txtDanToc.setEditable(false);
        txtDiaChi.setEditable(false);
        txtMaNV.setEditable(false);
        txtQueQuan.setEditable(false);
        txtSDT.setEditable(false);
        txtTen.setEditable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
    }
    private void luuChinhSuaNV(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
          String date;
        String sql="update NHANVIEN set HoTen=?,NgaySinh=?,GioiTinh=?,SDT=?,DanToc=?,"
                + " QueQuan=?,CMND=?,DiaChi=?,TrangThai=?,ChucVu=? where MaNV= '"+AdminForm.maNV+"'";
        Connection con=KetNoiDB.getConnection();
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1, txtTen.getText());
            date = sdf1.format(dcNgaySinh.getDate());
            ps.setString(2, date);
            if (rbtnNam.isSelected()) {
                ps.setInt(3, 1);
            }else if (rbtnNu.isSelected()) {
                ps.setInt(3, 2);
            }else if (rbtnKhac.isSelected()) {
                 ps.setInt(3, 3);
            }
            ps.setString(4, txtSDT.getText());
            ps.setString(5, txtDanToc.getText());
            ps.setString(6,txtQueQuan.getText());
            ps.setString(7,txtCMND.getText());
            ps.setString(8,txtDiaChi.getText());
            if (rbtnDangLam.isSelected()) {
               ps.setInt(10, 1);
            }else if (rbtnDaNghi.isSelected()) {
                ps.setInt(10, 0);
            }
            ps.setInt(9, cbChucVu.getSelectedIndex());
            int i=ps.executeUpdate();
            if (i>0) {
                JOptionPane.showMessageDialog(this, "Chỉnh sửa thông tin nhân viên thành công.");
            }else{
                JOptionPane.showMessageDialog(this, "Lỗi!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TTNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void loadDB(){
        String sql="select MaNV,HoTen,CONVERT(varchar, NgaySinh, 105) as NgaySinh,"
                + " GioiTinh,ChucVu,TrangThai,TenCV,SDT,CMND,DanToc,DiaChi,QueQuan,"
                + "HinhAnh from NHANVIEN,CHUCVU where NHANVIEN.ChucVu=CHUCVU.MaCV" 
                + " and NHANVIEN.MaNV= '"+AdminForm.maNV+ "'";
        try {
           Connection con=KetNoiDB.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                txtMaNV.setText(rs.getString("MaNV"));
                dcNgaySinh.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(rs.getString("NgaySinh")));
                txtTen.setText(rs.getString("HoTen"));
                txtSDT.setText(rs.getString("SDT"));
                txtCMND.setText(rs.getString("CMND"));
                txtDanToc.setText(rs.getString("DanToc"));
                txtDiaChi.setText(rs.getString("DiaChi"));
                if (rs.getString("TenCV").equals("admin")) {
                    cbChucVu.setSelectedIndex(0);
                }else if(rs.getString("TenCV").equals("Lễ tân")){
                    cbChucVu.setSelectedIndex(1);
                }else if(rs.getString("TenCV").equals("Nhân viên bảo dưỡng")){
                    cbChucVu.setSelectedIndex(2);
                }
                txtQueQuan.setText(rs.getString("QueQuan"));
                byte[] img=rs.getBytes("HinhAnh");
                ImageIcon imageicon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbAnh.getWidth(), lbAnh.getHeight(), Image.SCALE_SMOOTH));
                lbAnh.setIcon(imageicon);
                switch (rs.getInt("GioiTinh")) {
                    case 1 -> rbtnNam.setSelected(true);
                    case 2 -> rbtnNu.setSelected(true);
                    default -> rbtnKhac.setSelected(true);
                }
                if (rs.getInt("TrangThai")==0) {
                    rbtnDaNghi.setSelected(true);
                }else if (rs.getInt("TrangThai")==1) {
                    rbtnDangLam.setSelected(true);
                }
                
          }
                rs.close();
                st.close();
                con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TTNV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TTNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgrGioiTinh = new javax.swing.ButtonGroup();
        btgrTrangThai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lbAnh = new javax.swing.JLabel();
        btnTaiAnh = new javax.swing.JButton();
        btnChinhSuaTT = new javax.swing.JButton();
        btnPhat = new javax.swing.JButton();
        btnHopDong = new javax.swing.JButton();
        lbTieude = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        btnQuayLai1 = new javax.swing.JButton();
        btnThuong = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pnTT = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rbtnNam = new javax.swing.JRadioButton();
        rbtnNu = new javax.swing.JRadioButton();
        rbtnKhac = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txtCMND = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDanToc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dcNgaySinh = new com.toedter.calendar.JDateChooser();
        cbChucVu = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        txtQueQuan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rbtnDangLam = new javax.swing.JRadioButton();
        rbtnDaNghi = new javax.swing.JRadioButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(232, 69, 69));
        jPanel1.setForeground(new java.awt.Color(51, 51, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(lbAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 160));

        btnTaiAnh.setBackground(new java.awt.Color(83, 53, 74));
        btnTaiAnh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnTaiAnh.setForeground(new java.awt.Color(255, 255, 255));
        btnTaiAnh.setText("Tải ảnh lên");
        jPanel1.add(btnTaiAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 120, 20));

        btnChinhSuaTT.setBackground(new java.awt.Color(83, 53, 74));
        btnChinhSuaTT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnChinhSuaTT.setForeground(new java.awt.Color(255, 255, 255));
        btnChinhSuaTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_change_user_50px.png"))); // NOI18N
        btnChinhSuaTT.setText("CHỈNH SỬA");
        btnChinhSuaTT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnChinhSuaTT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChinhSuaTTMouseClicked(evt);
            }
        });
        jPanel1.add(btnChinhSuaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 190, 50));

        btnPhat.setBackground(new java.awt.Color(83, 53, 74));
        btnPhat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPhat.setForeground(new java.awt.Color(255, 255, 255));
        btnPhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_soccer_yellow_card_50px.png"))); // NOI18N
        btnPhat.setText("XỬ PHẠT");
        btnPhat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPhatMouseClicked(evt);
            }
        });
        jPanel1.add(btnPhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 190, 50));

        btnHopDong.setBackground(new java.awt.Color(83, 53, 74));
        btnHopDong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnHopDong.setForeground(new java.awt.Color(255, 255, 255));
        btnHopDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_contract_50px.png"))); // NOI18N
        btnHopDong.setText("HỢP ĐỒNG");
        btnHopDong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHopDongMouseClicked(evt);
            }
        });
        jPanel1.add(btnHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 190, 50));
        jPanel1.add(lbTieude, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 50));

        txtMaNV.setBackground(new java.awt.Color(144, 55, 73));
        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtMaNV.setForeground(new java.awt.Color(255, 255, 255));
        txtMaNV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 170, 20));

        btnQuayLai1.setBackground(new java.awt.Color(83, 53, 74));
        btnQuayLai1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnQuayLai1.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_jog_reverse_50px.png"))); // NOI18N
        btnQuayLai1.setText("QUAY LẠI");
        btnQuayLai1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQuayLai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLai1MouseClicked(evt);
            }
        });
        jPanel1.add(btnQuayLai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 190, 50));

        btnThuong.setBackground(new java.awt.Color(83, 53, 74));
        btnThuong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnThuong.setForeground(new java.awt.Color(255, 255, 255));
        btnThuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_medal_first_place_50px.png"))); // NOI18N
        btnThuong.setText("KHEN THƯỞNG");
        btnThuong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThuongMouseClicked(evt);
            }
        });
        jPanel1.add(btnThuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 190, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 610));

        jPanel2.setLayout(new java.awt.CardLayout());

        pnTT.setBackground(new java.awt.Color(144, 55, 73));

        jPanel3.setBackground(new java.awt.Color(144, 55, 73));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN CÁ NHÂN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã nhân viên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Họ và tên:");

        txtTen.setBackground(new java.awt.Color(144, 55, 73));
        txtTen.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtTen.setForeground(new java.awt.Color(255, 255, 255));
        txtTen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày sinh:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Giới tính:");

        rbtnNam.setBackground(new java.awt.Color(144, 55, 73));
        btgrGioiTinh.add(rbtnNam);
        rbtnNam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnNam.setForeground(new java.awt.Color(255, 255, 255));
        rbtnNam.setText("Nam");
        rbtnNam.setEnabled(false);

        rbtnNu.setBackground(new java.awt.Color(144, 55, 73));
        btgrGioiTinh.add(rbtnNu);
        rbtnNu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnNu.setForeground(new java.awt.Color(255, 255, 255));
        rbtnNu.setText("Nữ");
        rbtnNu.setEnabled(false);

        rbtnKhac.setBackground(new java.awt.Color(144, 55, 73));
        btgrGioiTinh.add(rbtnKhac);
        rbtnKhac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnKhac.setForeground(new java.awt.Color(255, 255, 255));
        rbtnKhac.setText("Khác");
        rbtnKhac.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CMND/CCCD:");

        txtCMND.setBackground(new java.awt.Color(144, 55, 73));
        txtCMND.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtCMND.setForeground(new java.awt.Color(255, 255, 255));
        txtCMND.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Dân tộc:");

        txtDanToc.setBackground(new java.awt.Color(144, 55, 73));
        txtDanToc.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtDanToc.setForeground(new java.awt.Color(255, 255, 255));
        txtDanToc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("SDT:");

        txtSDT.setBackground(new java.awt.Color(144, 55, 73));
        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtSDT.setForeground(new java.awt.Color(255, 255, 255));
        txtSDT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Chức vụ:");

        dcNgaySinh.setDateFormatString("dd-MM-yyyy");
        dcNgaySinh.setEnabled(false);
        dcNgaySinh.setMaxSelectableDate(new java.util.Date(1104516077000L));
        dcNgaySinh.setMinSelectableDate(new java.util.Date(-5364683923000L));

        cbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Lễ Tân", "Nhân viên vảo dưỡng" }));
        cbChucVu.setSelectedIndex(-1);
        cbChucVu.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel8))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(cbChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, 190, Short.MAX_VALUE))
                        .addGap(179, 179, 179)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rbtnNam)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnNu)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnKhac))
                    .addComponent(jLabel5))
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(9, 9, 9)
                        .addComponent(txtDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(rbtnNam)
                                            .addComponent(rbtnNu)
                                            .addComponent(rbtnKhac)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addGap(24, 24, 24)))
                        .addContainerGap())))
        );

        jPanel7.setBackground(new java.awt.Color(144, 55, 73));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LIÊN HỆ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));

        txtQueQuan.setBackground(new java.awt.Color(144, 55, 73));
        txtQueQuan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtQueQuan.setForeground(new java.awt.Color(255, 255, 255));
        txtQueQuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quê quán:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Địa chỉ:");

        txtDiaChi.setBackground(new java.awt.Color(144, 55, 73));
        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtDiaChi.setForeground(new java.awt.Color(255, 255, 255));
        txtDiaChi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(txtQueQuan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Trạng thái:");

        rbtnDangLam.setBackground(new java.awt.Color(144, 55, 73));
        btgrTrangThai.add(rbtnDangLam);
        rbtnDangLam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnDangLam.setForeground(new java.awt.Color(255, 255, 255));
        rbtnDangLam.setText("Đang làm");
        rbtnDangLam.setEnabled(false);

        rbtnDaNghi.setBackground(new java.awt.Color(144, 55, 73));
        btgrTrangThai.add(rbtnDaNghi);
        rbtnDaNghi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtnDaNghi.setForeground(new java.awt.Color(255, 255, 255));
        rbtnDaNghi.setText("Đã nghỉ");
        rbtnDaNghi.setEnabled(false);
        rbtnDaNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDaNghiActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        btnHuy.setText("Huỷ");
        btnHuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHuyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnTTLayout = new javax.swing.GroupLayout(pnTT);
        pnTT.setLayout(pnTTLayout);
        pnTTLayout.setHorizontalGroup(
            pnTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTTLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(pnTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTTLayout.createSequentialGroup()
                        .addGroup(pnTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(93, Short.MAX_VALUE))
                    .addGroup(pnTTLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnDangLam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnDaNghi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
        );
        pnTTLayout.setVerticalGroup(
            pnTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTTLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnDangLam)
                    .addComponent(rbtnDaNghi)
                    .addComponent(jLabel11)
                    .addComponent(btnLuu)
                    .addComponent(btnHuy))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel2.add(pnTT, "card2");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, "card3");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6, "card4");

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 980, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChinhSuaTTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChinhSuaTTMouseClicked
       //chucNang.setPanelEnabled(pnTT, true);
       txtCMND.setEditable(true);
        cbChucVu.setEnabled(true);
        txtDanToc.setEditable(true);
        txtDiaChi.setEditable(true);
        txtQueQuan.setEditable(true);
        txtSDT.setEditable(true);
        txtTen.setEditable(true);
       dcNgaySinh.setEnabled(true);
        btnLuu.setVisible(true);
        btnHuy.setVisible(true);
        btnTaiAnh.setVisible(true);
        rbtnDaNghi.setEnabled(true);
        rbtnDangLam.setEnabled(true);
        rbtnKhac.setEnabled(true);
        rbtnNam.setEnabled(true);
        rbtnNu.setEnabled(true);
    }//GEN-LAST:event_btnChinhSuaTTMouseClicked

    private void btnHuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuyMouseClicked
        loadDB();
        //chucNang.setPanelEnabled(pnTT, false);
        btnLuu.setVisible(false);
        btnHuy.setVisible(false);
        txtCMND.setEditable(false);
        cbChucVu.setEnabled(false);
        txtDanToc.setEditable(false);
        txtDiaChi.setEditable(false);
        txtQueQuan.setEditable(false);
        txtSDT.setEditable(false);
        txtTen.setEditable(false);
        rbtnDaNghi.setEnabled(false);
        rbtnDangLam.setEnabled(false);
        rbtnKhac.setEnabled(false);
        rbtnNam.setEnabled(false);
        rbtnNu.setEnabled(false);
        
    }//GEN-LAST:event_btnHuyMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        if (txtTen.getText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống họ tên");
        }else if (txtTen.getText().matches("^[^0-9]{7,}$")) {
            JOptionPane.showMessageDialog(rootPane, "Họ tên không đúng xin kiểm tra lại.");
        }
        if (txtQueQuan.getText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống quê quán");
        }else if (txtQueQuan.getText().matches("^[^0-9]{7,}$")) {
            JOptionPane.showMessageDialog(rootPane, "Quê quán không đúng xin kiểm tra lại.");
        }
        if (txtDanToc.getText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống họ tên");
        }else if (txtDanToc.getText().matches("^[^1-9]{7,}$")) {
            JOptionPane.showMessageDialog(rootPane, "Dân tộc không đúng xin kiểm tra lại.");
        }
        if (txtDiaChi.getText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống địa chỉ");
        }
        if (txtCMND.getText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống CMND");
        }else if (txtCMND.getText().matches("^[0-9]{9,12}$")) {
            JOptionPane.showMessageDialog(rootPane, "CMND không đúng xin kiểm tra lại.");
        }
        if (txtSDT.getText()==null) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống số điện thoại");
        }else if (txtSDT.getText().matches("^[0]{1}[0-9]{9}$")) {
            JOptionPane.showMessageDialog(rootPane, "Số điện thoại không đúng xin kiểm tra lại.");
        }
        if (cbChucVu.getSelectedIndex()==-1) {
            JOptionPane.showMessageDialog(rootPane, "Xin hãy chọn một chức vụ");
        }
        luuChinhSuaNV();
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHopDongMouseClicked
        new HopDongForm().setVisible(true);
    }//GEN-LAST:event_btnHopDongMouseClicked

    private void btnPhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPhatMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnPhatMouseClicked

    private void rbtnDaNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDaNghiActionPerformed
        
    }//GEN-LAST:event_rbtnDaNghiActionPerformed

    private void btnQuayLai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLai1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuayLai1MouseClicked

    private void btnThuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThuongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThuongMouseClicked

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
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TTNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgrGioiTinh;
    private javax.swing.ButtonGroup btgrTrangThai;
    private javax.swing.JButton btnChinhSuaTT;
    private javax.swing.JButton btnHopDong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnPhat;
    private javax.swing.JButton btnQuayLai1;
    private javax.swing.JButton btnTaiAnh;
    private javax.swing.JButton btnThuong;
    private javax.swing.JComboBox<String> cbChucVu;
    private com.toedter.calendar.JDateChooser dcNgaySinh;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lbTieude;
    private javax.swing.JPanel pnTT;
    private javax.swing.JRadioButton rbtnDaNghi;
    private javax.swing.JRadioButton rbtnDangLam;
    private javax.swing.JRadioButton rbtnKhac;
    private javax.swing.JRadioButton rbtnNam;
    private javax.swing.JRadioButton rbtnNu;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDanToc;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
