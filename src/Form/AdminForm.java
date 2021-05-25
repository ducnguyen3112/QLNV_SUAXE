/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Form.Xuli.KetNoiDB;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

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
        //bieuDoTron();
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
    int SLNV;
    private void bieuDoGioiTinh(){
        DefaultPieDataset piedata=new DefaultPieDataset();
        String sql="select GioiTinh ,count(MaNV) as sl " +
                    "from NHANVIEN " +
                    "group by GioiTinh";
        int slNam=0,slNu=0,slKhac=0;
        int[] slGioiTinh=new int[4];
        int i=0;
        try{
        con=KetNoiDB.getConnection();
        st=con.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()){
            slGioiTinh[i]=rs.getInt("sl");
            i++;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        slNam=slGioiTinh[0];
        slNu=slGioiTinh[1];
        slKhac=slGioiTinh[2];
         SLNV=slNam+slNu+slKhac;
        double pcNam=(double)slNam/SLNV;
        double pcNu=(double)slNu/SLNV;
        double pcKhac=(double)slKhac/SLNV;
        piedata.setValue("Nam", pcNam);
        piedata.setValue("Nữ",pcNu);
        piedata.setValue("Khác",pcKhac);
        JFreeChart chart =ChartFactory.createPieChart3D("THỐNG KÊ GIỚI TÍNH", piedata, true, true, false);
        PiePlot3D p=(PiePlot3D)chart.getPlot();
        ChartPanel pnpie=new ChartPanel(chart);
        pnpie.setPreferredSize(pnTKGT.getSize());
        pnTKGT.setLayout(new java.awt.BorderLayout());
        pnTKGT.add(pnpie,BorderLayout.CENTER);
        pnTKGT.validate();
    }
    private void bieuDoChucVu(){
        DefaultPieDataset piedata=new DefaultPieDataset();
        String sql="select ChucVu ,count(MaNV) as sl " +
                    "from NHANVIEN " +
                    "group by ChucVu";
        int cvAD=0,cvLT=0,cvNV=0;
        int[] slCV=new int[4];
        int i=0;
        try{
        con=KetNoiDB.getConnection();
        st=con.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()){
            slCV[i]=rs.getInt("sl");
            i++;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        cvAD=slCV[0];
        cvLT=slCV[1];
        cvNV=slCV[2];
         SLNV=cvAD+cvLT+cvNV;
        double pcAD=(double)cvAD/SLNV;
        double pcLT=(double)cvLT/SLNV;
        double pcNV=(double)cvNV/SLNV;
        piedata.setValue("Admin", pcAD);
        piedata.setValue("Lễ tân",pcLT);
        piedata.setValue("Nhân viên bảo dưỡng",pcNV);
        JFreeChart chart =ChartFactory.createPieChart3D("THỐNG KÊ CHỨC VỤ", piedata, true, true, false);
        PiePlot3D p=(PiePlot3D)chart.getPlot();
        ChartPanel pnpie=new ChartPanel(chart);
        pnpie.setPreferredSize(pnTKCV.getSize());
        pnTKCV.setLayout(new java.awt.BorderLayout());
        pnTKCV.add(pnpie,BorderLayout.CENTER);
        pnTKCV.validate();
    }
    private void htTongNV(){
        lbTSNV.setText(String.valueOf(SLNV));
    }
    private void bangSoXeDaSua(){
        String nam=txtNamSX.getText();
        String thang=cbThangSX.getSelectedItem().toString();
        String sql="select NHANVIEN.MaNV,HoTen, count(BienSoXe) as sl " +
                    "from CT_SDDV,NHANVIEN " +
                    "where ct_sddv.MaNV=NHANVIEN.MaNV and month(NgayGioHT)="+thang+
                    " and year(NgayGioHT)="+nam+
                    " group by HoTen,NHANVIEN.MANV";
        DefaultTableModel model =(DefaultTableModel) tbSoXeDaSua.getModel();
        KetNoiDB.deleteTableData(model);
        try{
        con=KetNoiDB.getConnection();
        st=con.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()){
            Vector vector=new Vector();
            vector.add(rs.getString("MaNV"));
            vector.add(rs.getString("HoTen"));
            vector.add(rs.getString("sl"));
            model.addRow(vector);
        }
        tbSoXeDaSua.setModel(model);
        lbTongXeDaSua.setText(KetNoiDB.tinhTongCot(tbSoXeDaSua, 2));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    private void bangTKLuong(){
        String nam=txtNamL.getText();
        String thang=cbThangL.getSelectedItem().toString();
        String sql="select NHANVIEN.MaNV,HoTen,NgayCong,THUCLINH " +
                    "from NHANVIEN,THANHTOANLUONG,CONG " +
                    "where THANHTOANLUONG.MaNV=NHANVIEN.MaNV";
        DefaultTableModel model =(DefaultTableModel) tbSoXeDaSua.getModel();
        KetNoiDB.deleteTableData(model);
        try{
        con=KetNoiDB.getConnection();
        st=con.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()){
            Vector vector=new Vector();
            vector.add(rs.getString("MaNV"));
            vector.add(rs.getString("HoTen"));
            vector.add(rs.getString("NgayCong"));
            vector.add(rs.getString("THUCLINH"));
            model.addRow(vector);
        }
        tbLuong.setModel(model);
        lbTongLuong.setText(KetNoiDB.tinhTongCot(tbLuong, 3));
        }
        catch(Exception e){
            e.printStackTrace();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSoXeDaSua = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        lbTongXeDaSua = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNamSX = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbThangSX = new javax.swing.JComboBox<>();
        btXemSX = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbLuong = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        lbTongLuong = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNamL = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbThangL = new javax.swing.JComboBox<>();
        btXemL = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbTSNV = new javax.swing.JLabel();
        pnTKGT = new javax.swing.JPanel();
        pnTKCV = new javax.swing.JPanel();

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
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });
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
        jLabel5.setBounds(0, 0, 1170, 600);

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
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("THỐNG KÊ BIỂU ĐỒ", jPanel3);

        jPanel6.setBackground(new java.awt.Color(69, 69, 69));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_statistics_100px_1.png"))); // NOI18N
        jLabel7.setText("THÔNG KÊ ");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton2.setText("QUAY LẠI");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SỐ LIỆU SỬA XE MỖI NHÂN VIÊN");

        tbSoXeDaSua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số xe đã sửa"
            }
        ));
        tbSoXeDaSua.setCellSelectionEnabled(true);
        jScrollPane2.setViewportView(tbSoXeDaSua);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tổng số xe đã sửa:");

        lbTongXeDaSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTongXeDaSua.setText("00");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Năm:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Tháng:");

        cbThangSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        btXemSX.setText("XEM");
        btXemSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXemSXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongXeDaSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbThangSX, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btXemSX, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtNamSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(cbThangSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXemSX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongXeDaSua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("TIỀN LƯƠNG MỖI NHÂN VIÊN");

        tbLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày công", "Lương"
            }
        ));
        jScrollPane3.setViewportView(tbLuong);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Tổng lương:");

        lbTongLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTongLuong.setText("00");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Năm:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Tháng:");

        cbThangL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        btXemL.setText("XEM");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNamL, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbThangL, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btXemL, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtNamL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cbThangL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXemL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("TỔNG SỐ NHÂN VIÊN");

        lbTSNV.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbTSNV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTSNV.setText("jLabel10");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTSNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTSNV, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        pnTKGT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnTKGTLayout = new javax.swing.GroupLayout(pnTKGT);
        pnTKGT.setLayout(pnTKGTLayout);
        pnTKGTLayout.setHorizontalGroup(
            pnTKGTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );
        pnTKGTLayout.setVerticalGroup(
            pnTKGTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnTKCV.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnTKCVLayout = new javax.swing.GroupLayout(pnTKCV);
        pnTKCV.setLayout(pnTKCVLayout);
        pnTKCVLayout.setHorizontalGroup(
            pnTKCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnTKCVLayout.setVerticalGroup(
            pnTKCVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(600, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnTKGT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnTKCV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(120, 120, 120))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnTKCV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnTKGT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("THỐNG KÊ SỐ LIỆU", jPanel2);

        javax.swing.GroupLayout pnThongKeLayout = new javax.swing.GroupLayout(pnThongKe);
        pnThongKe.setLayout(pnThongKeLayout);
        pnThongKeLayout.setHorizontalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        pnThongKeLayout.setVerticalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        pnMenu.setVisible(false);
        pnThongKe.setVisible(true);
        bieuDoGioiTinh();
        bieuDoChucVu();
        htTongNV();
    }//GEN-LAST:event_btnTKActionPerformed

    private void btXemSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXemSXActionPerformed
        String nam=txtNamSX.getText();
        int namSX=Integer.parseInt(nam);
        if (nam.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Không được để trống năm");
            return;
        }else if(namSX<2021||namSX<2021) {
        System.out.println(namSX);
            JOptionPane.showMessageDialog(rootPane, "Giá trị không hợp lệ!Xin hãy nhập lại");
            return;
        }
        if (txtNamSX.getText().matches("^[a-z][A-Z]$")) {
            JOptionPane.showMessageDialog(rootPane, "Giá trị không hợp lệ!Xin hãy nhập lại");
            return;
        }
        bangSoXeDaSua();
    }//GEN-LAST:event_btXemSXActionPerformed
           
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
    private javax.swing.JButton btXemL;
    private javax.swing.JButton btXemSX;
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
    private javax.swing.JComboBox<String> cbThangL;
    private javax.swing.JComboBox<String> cbThangSX;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbTSNV;
    private javax.swing.JLabel lbTenDN;
    private javax.swing.JLabel lbTongLuong;
    private javax.swing.JLabel lbTongXeDaSua;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnNV;
    private javax.swing.JPanel pnTKCV;
    private javax.swing.JPanel pnTKGT;
    private javax.swing.JPanel pnThongKe;
    private javax.swing.JTable tbDSNV;
    private javax.swing.JTable tbLuong;
    private javax.swing.JTable tbSoXeDaSua;
    private javax.swing.JTextField txtNamL;
    private javax.swing.JTextField txtNamSX;
    private javax.swing.JLabel txtTenAD;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
