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
        btnBangLuong = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        btnDSNV = new javax.swing.JButton();
        btnChamCong = new javax.swing.JButton();
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
        pnThongKe = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

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
        jLabel2.setBounds(960, 55, 36, 30);

        lbTenDN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbTenDN.setForeground(new java.awt.Color(255, 153, 0));
        lbTenDN.setText("admin");
        pnMenu.add(lbTenDN);
        lbTenDN.setBounds(1000, 60, 160, 20);

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 204, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CHƯƠNG TRÌNH QUẢN LÍ NHÂN VIÊN TRUNG TÂM BẢO HÀNH XE MÁY");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnMenu.add(jLabel4);
        jLabel4.setBounds(160, 190, 820, 40);

        btnBangLuong.setBackground(new java.awt.Color(255, 204, 51));
        btnBangLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_payroll_110px_2.png"))); // NOI18N
        btnBangLuong.setText("BẢNG LƯƠNG");
        btnBangLuong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBangLuong.setPreferredSize(new java.awt.Dimension(150, 140));
        btnBangLuong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBangLuongMouseClicked(evt);
            }
        });
        pnMenu.add(btnBangLuong);
        btnBangLuong.setBounds(720, 290, 150, 140);

        btnTK.setBackground(new java.awt.Color(255, 204, 51));
        btnTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_pie_chart_110px.png"))); // NOI18N
        btnTK.setText("THÔNG KÊ");
        btnTK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTK.setPreferredSize(new java.awt.Dimension(150, 140));
        btnTK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnMenu.add(btnTK);
        btnTK.setBounds(380, 440, 150, 140);

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
        btnDSNV.setBounds(210, 290, 150, 140);

        btnChamCong.setBackground(new java.awt.Color(255, 204, 51));
        btnChamCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_test_passed_110px_3.png"))); // NOI18N
        btnChamCong.setText("CHẤM CÔNG");
        btnChamCong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChamCong.setPreferredSize(new java.awt.Dimension(150, 140));
        btnChamCong.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChamCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChamCongMouseClicked(evt);
            }
        });
        pnMenu.add(btnChamCong);
        btnChamCong.setBounds(550, 290, 150, 140);

        btnPCDV.setBackground(new java.awt.Color(255, 204, 51));
        btnPCDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_request_service_110px_1.png"))); // NOI18N
        btnPCDV.setText("PHÂN CÔNG DỊCH VỤ");
        btnPCDV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPCDV.setPreferredSize(new java.awt.Dimension(150, 140));
        btnPCDV.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPCDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPCDVActionPerformed(evt);
            }
        });
        pnMenu.add(btnPCDV);
        btnPCDV.setBounds(380, 290, 150, 140);

        btnCD.setBackground(new java.awt.Color(255, 204, 51));
        btnCD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_logout_rounded_left_110px_1.png"))); // NOI18N
        btnCD.setText("TUỲ CHỈNH");
        btnCD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCD.setPreferredSize(new java.awt.Dimension(150, 140));
        btnCD.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCDMouseClicked(evt);
            }
        });
        pnMenu.add(btnCD);
        btnCD.setBounds(550, 440, 150, 140);

        jLabel5.setBackground(new java.awt.Color(255, 204, 51));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/photo-1517999144091-3d9dca6d1e43.png"))); // NOI18N
        pnMenu.add(jLabel5);
        jLabel5.setBounds(0, -10, 1180, 620);

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
                .addContainerGap(31, Short.MAX_VALUE))
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
        btnQuayLai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnQuayLaiMouseClicked(evt);
            }
        });
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

        pnNV.add(jPanel5, java.awt.BorderLayout.WEST);

        jLayeredPane1.add(pnNV, "card3");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số xe đã sửa"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Lương thực lĩnh"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel6.setBackground(new java.awt.Color(69, 69, 69));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_statistics_100px_1.png"))); // NOI18N
        jLabel7.setText("THÔNG KÊ ");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Chọn năm:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Chọn tháng:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox1.setSelectedIndex(-1);

        jButton1.setText("XEM");

        jButton2.setText("QUAY LẠI");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SỐ LIỆU SỬA XE MỖI NHÂN VIÊN");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("SỐ LƯƠNG THỰC LĨNH MỖI NHÂN VIÊN");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tổng số xe đã sửa:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tổng tiền lương:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("00");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(168, 616, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("THỐNG KÊ SỐ LIỆU", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1163, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("THỐNG KÊ BIỂU ĐỒ", jPanel3);

        javax.swing.GroupLayout pnThongKeLayout = new javax.swing.GroupLayout(pnThongKe);
        pnThongKe.setLayout(pnThongKeLayout);
        pnThongKeLayout.setHorizontalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        pnThongKeLayout.setVerticalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Thống kê số liệu");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        jLayeredPane1.add(pnThongKe, "card4");

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
        tr.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText().toLowerCase().trim()));
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
        KetNoiDB.xoaNhanVien(tbDSNV);
        showDuLieu();
    }//GEN-LAST:event_btnXoaNVMouseClicked

    private void btnThemNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNVMouseClicked
       new ThemNV(this, rootPaneCheckingEnabled).setVisible(true);
             showDuLieu();
    }//GEN-LAST:event_btnThemNVMouseClicked

    private void btnQuayLaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuayLaiMouseClicked
        pnMenu.setVisible(true);
        pnNV.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiMouseClicked

    private void btnCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCDMouseClicked
        new TuyChinhFrom(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnCDMouseClicked

    private void btnChamCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChamCongMouseClicked
        // TODO add your handling code here:
        new ChamCongForm(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnChamCongMouseClicked

    private void btnBangLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBangLuongMouseClicked
        // TODO add your handling code here:
        new TTLForm(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_btnBangLuongMouseClicked

    private void btnPCDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPCDVActionPerformed
        new LeTanform().setVisible(true);
    }//GEN-LAST:event_btnPCDVActionPerformed
           
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
    private javax.swing.JButton btnBangLuong;
    private javax.swing.JButton btnCD;
    private javax.swing.JButton btnChamCong;
    private javax.swing.JButton btnDSNV;
    private javax.swing.JButton btnPCDV;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTTNV;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbTenDN;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnNV;
    private javax.swing.JPanel pnThongKe;
    private javax.swing.JTable tbDSNV;
    private javax.swing.JLabel txtTenAD;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
