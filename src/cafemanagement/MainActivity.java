/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafemanagement;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


/**
 *
 * @author PC
 */
public class MainActivity extends javax.swing.JFrame {

    /**
     * Creates new form MainActivity
     */
    
    public int id;
    
    Duan sp =new Duan();
    public MainActivity() {
        initComponents();
        sp.fillDuanJtable(jTable_duan, "");
        sp.fillDuan_CongviecJtable(jTable_duan_congviec, "");
        sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, "", null, "", "", "");
        sp.fillCongviec_thongbao(jTable_thongbao_deadline);
        sp.fillCongviecdeadline_now_thongbao(jTable_thongbao_dlnow);
        LocalDate now = java.time.LocalDate.now();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `congviec` WHERE NOT`Trangthai`='1'");
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("Ngayketthuc"));
            }
        } catch (Exception e) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println(now);
        combobox_nguoinhan();
        combobox_phongban();
//        jComboBox_ngnhan.removeAllItems();
        combobox_ngtiepnhan();
        combobox_duan();
        combobox_guiTB();
      
    }
    
    
    private void combobox_nguoinhan()
    {
        try
        {
            String labels[] = {};
            ArrayList<String> addngnhan = new ArrayList<String>(Arrays.asList(labels));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            jComboBox_ngnhan.removeAllItems();
            ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='admin'");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String tensp = rs.getString("Ten");
                addngnhan.add(tensp);
                jComboBox_ngnhan.addItem(tensp);      
                
            }

        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void combobox_duan()
    {
        
        try
        {   
            jComboBox_tenduan_tiendo.removeAllItems();
            jComboBox_tenduan_tiendo.addItem("Tất cả");
            String labels[] = {};
            ArrayList<String> addduan = new ArrayList<String>(Arrays.asList(labels));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            ps =con.prepareStatement("SELECT `Tenduan` FROM `duan`");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String tensp = rs.getString("Tenduan");
                addduan.add(tensp);
                jComboBox_tenduan_tiendo.addItem(tensp);                
            }

        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void combobox_phongban()
    {
        try
        {
            String labels1[] = {};
            ArrayList<String> addphongnhan = new ArrayList<String>(Arrays.asList(labels1));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            ps =con.prepareStatement("SELECT `thuocphong` FROM `users` WHERE `chucvu`='truongphong'");
            ResultSet rs = ps.executeQuery();
            jComboBox_ctcv_phongban.removeAllItems();
            while(rs.next())
            {
                String tensp = rs.getString("thuocphong");
                addphongnhan.add(tensp);
                jComboBox_ctcv_phongban.addItem(tensp);   
                jComboBox_phongban_tiendo.addItem(tensp);
                                jComboBox_admin_TB.addItem(tensp);
            }

        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void combobox_guiTB()
    {
        try
        {
            String labels1[] = {};
            ArrayList<String> addphongnhan = new ArrayList<String>(Arrays.asList(labels1));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='truongphong' OR `chucvu`='nhanvien' ");
            ResultSet rs = ps.executeQuery();
//            jComboBox_ctcv_phongban.removeAllItems();
            while(rs.next())
            {
                String tensp = rs.getString("Ten");
                addphongnhan.add(tensp);
                                jComboBox_admin_TB.addItem(tensp);
            }

        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void combobox_thuoccongviec(int id)
    {
         String loaicongviec = jComboBox_loaicongviec.getSelectedItem().toString();        
        try
        {
            String labels1[] = {};
            ArrayList<String> addphongnhan = new ArrayList<String>(Arrays.asList(labels1));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            if(loaicongviec.equals("congviecphu")){
            ps =con.prepareStatement("SELECT `Tencongviec` FROM `congviec` WHERE `Loaicongviec`='congviecchinh' AND `IDduan`=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            jComboBox_ctcv_thuoccv.removeAllItems();
            while(rs.next())
            {
                String tensp = rs.getString("Tencongviec");
                addphongnhan.add(tensp);
                jComboBox_ctcv_thuoccv.addItem(tensp);                
            }
            }else{
                jComboBox_ctcv_thuoccv.removeAllItems();
                jComboBox_ctcv_thuoccv.addItem("");
                jComboBox_ctcv_thuoccv.setSelectedItem("");
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
       
          
       }
    
    private void combobox_ngtiepnhan()
    {
        try
        {
            String labels1[] = {};
            String loaicongviec1 = jComboBox_loaicongviec.getSelectedItem().toString();
            String loaiphongban1 = jComboBox_ctcv_phongban.getSelectedItem().toString();
            ArrayList<String> addngtruongphong = new ArrayList<String>(Arrays.asList(labels1));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            if(loaicongviec1.equals("congviecchinh")){
            ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='truongphong' AND `thuocphong`=?");
            ps.setString(1, loaiphongban1);
            ResultSet rs = ps.executeQuery();
            jComboBox_ctcv_ngtiepnhan.removeAllItems();
            while(rs.next())
            {
                String tentruongphong = rs.getString("Ten");
                addngtruongphong.add(tentruongphong);
                jComboBox_ctcv_ngtiepnhan.addItem(tentruongphong); 
//                jComboBox_admin_TB.addItem(tentruongphong);
            }
            }else{
            ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='nhanvien' AND `thuocphong`=?");
            ps.setString(1, loaiphongban1);
            ResultSet rs = ps.executeQuery();
            jComboBox_ctcv_ngtiepnhan.removeAllItems();
            while(rs.next())
            {
                String tennhanvien = rs.getString("Ten");
                addngtruongphong.add(tennhanvien);
                jComboBox_ctcv_ngtiepnhan.addItem(tennhanvien);   
//                jComboBox_admin_TB.addItem(tennhanvien);
            }
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void combobox_ngtiepnhan_tiendo()
    {
        try
        {
            String labels1[] = {};
            String loaicongviec_tiendo = jComboBox_loaicv_tiendo.getSelectedItem().toString();
            String loaiphongban_tiendo = jComboBox_phongban_tiendo.getSelectedItem().toString();
            ArrayList<String> addngtiepnhan = new ArrayList<String>(Arrays.asList(labels1));
            Connection con = MyConnection.getConnection(); 
            PreparedStatement ps;
            if(loaicongviec_tiendo.equals("congviecchinh"))
            {
                if(loaiphongban_tiendo.equals("Tất cả")){
                    ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='truongphong'");
                    ResultSet rs = ps.executeQuery();
                    jComboBox_ngtiepnhan_tiendo.removeAllItems();
                    jComboBox_ngtiepnhan_tiendo.addItem("Tất cả");
                    while(rs.next())
                    {
                        String tentruongphong = rs.getString("Ten");
                        addngtiepnhan.add(tentruongphong);
                        jComboBox_ngtiepnhan_tiendo.addItem(tentruongphong);                
                    }                   
                }else{
                    ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='truongphong' AND `thuocphong`=?");
                    ps.setString(1, loaiphongban_tiendo);
                    ResultSet rs = ps.executeQuery();
                    jComboBox_ngtiepnhan_tiendo.removeAllItems();
                    jComboBox_ngtiepnhan_tiendo.addItem("Tất cả");
                    while(rs.next())
                    {
                        String tentruongphong = rs.getString("Ten");
                        addngtiepnhan.add(tentruongphong);
                        jComboBox_ngtiepnhan_tiendo.addItem(tentruongphong);                
                    }
                }
            }else{
                    if(loaicongviec_tiendo.equals("Tất cả"))
                    {
                        if(loaiphongban_tiendo.equals("Tất cả")){
                         ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `chucvu`='truongphong'OR `chucvu`='nhanvien' ");
                         ResultSet rs = ps.executeQuery();
                         jComboBox_ngtiepnhan_tiendo.removeAllItems();
                         jComboBox_ngtiepnhan_tiendo.addItem("Tất cả");
                         while(rs.next())
                         {
                             String tentruongphong = rs.getString("Ten");
                             addngtiepnhan.add(tentruongphong);
                             jComboBox_ngtiepnhan_tiendo.addItem(tentruongphong);                
                         }                   
                     }else{
                         ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `thuocphong`=?");
                         ps.setString(1, loaiphongban_tiendo);
                         ResultSet rs = ps.executeQuery();
                         jComboBox_ngtiepnhan_tiendo.removeAllItems();
                         jComboBox_ngtiepnhan_tiendo.addItem("Tất cả");
                         while(rs.next())
                         {
                             String tentruongphong = rs.getString("Ten");
                             addngtiepnhan.add(tentruongphong);
                             jComboBox_ngtiepnhan_tiendo.addItem(tentruongphong);                
                         }
                     } 
                    }else{
                        if(loaiphongban_tiendo.equals("Tất cả")){
                             ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE`chucvu`='nhanvien' ");
                             ResultSet rs = ps.executeQuery();
                             jComboBox_ngtiepnhan_tiendo.removeAllItems();
                             jComboBox_ngtiepnhan_tiendo.addItem("Tất cả");
                             while(rs.next())
                             {
                                 String tentruongphong = rs.getString("Ten");
                                 addngtiepnhan.add(tentruongphong);
                                 jComboBox_ngtiepnhan_tiendo.addItem(tentruongphong);                
                             }                   
                         }else{
                             ps =con.prepareStatement("SELECT `Ten` FROM `users` WHERE `thuocphong`=? AND `chucvu`='nhanvien'");
                             ps.setString(1, loaiphongban_tiendo);
                             ResultSet rs = ps.executeQuery();
                             jComboBox_ngtiepnhan_tiendo.removeAllItems();
                             jComboBox_ngtiepnhan_tiendo.addItem("Tất cả");
                             while(rs.next())
                             {
                                 String tentruongphong = rs.getString("Ten");
                                 addngtiepnhan.add(tentruongphong);
                                 jComboBox_ngtiepnhan_tiendo.addItem(tentruongphong);                
                             }
                         }
                    }
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_duan = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextfield_tenduan = new javax.swing.JTextField();
        jButton_addnewsp2 = new javax.swing.JButton();
        jButton_editsp2 = new javax.swing.JButton();
        jButton_resetsp2 = new javax.swing.JButton();
        jButton_deleteSP2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel_id_duan = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_ngnhan = new javax.swing.JComboBox<>();
        jTextField_ghichu = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextfield_muctieu = new javax.swing.JTextArea();
        jLabel37 = new javax.swing.JLabel();
        jLabel_trangthai = new javax.swing.JLabel();
        jDateChooser_st = new com.toedter.calendar.JDateChooser();
        jDateChooser_end = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jTextField_searchsp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_duan_congviec = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTree_congviec = new javax.swing.JTree();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox_ctcv_phongban = new javax.swing.JComboBox<>();
        jTextField_tencongviec = new javax.swing.JTextField();
        jComboBox_loaicongviec = new javax.swing.JComboBox<>();
        jComboBox_ctcv_ngtiepnhan = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_ctcv_muctieu = new javax.swing.JTextArea();
        jLabel_ctcv_id = new javax.swing.JLabel();
        jLabel_ctcv_trangthai = new javax.swing.JLabel();
        jComboBox_ctcv_thuoccv = new javax.swing.JComboBox<>();
        jButton_ctcv_submit = new javax.swing.JButton();
        jButton_ctcv_huy = new javax.swing.JButton();
        jDate_thongbao_bd = new com.toedter.calendar.JDateChooser();
        jDate_thongbao_kt = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jButton_cvda_add = new javax.swing.JButton();
        jButton_cvda_sua = new javax.swing.JButton();
        jButton_cvda_xoa = new javax.swing.JButton();
        jLabel_thaotac = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_cvda_tiendo = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jComboBox_tenduan_tiendo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox_loaicv_tiendo = new javax.swing.JComboBox<>();
        jComboBox_phongban_tiendo = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jComboBox_ngtiepnhan_tiendo = new javax.swing.JComboBox<>();
        jTextField_timkiem_tiendo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTree_congviec_tiendo = new javax.swing.JTree();
        jButton_buildchart = new javax.swing.JButton();
        id_duan = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable_thongbao_deadline = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable_thongbao_dlnow = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea_thongbao_muctieu = new javax.swing.JTextArea();
        jLabel_thongbao_id = new javax.swing.JLabel();
        jLabel_thongbao_trangthai = new javax.swing.JLabel();
        jButton_thongbao_nhacnho = new javax.swing.JButton();
        jButton_thongbao_canhbao = new javax.swing.JButton();
        jLabel_thongbao_tencv = new javax.swing.JLabel();
        jLabel_thongbao_loaicv = new javax.swing.JLabel();
        thongbao_thuoccv = new javax.swing.JLabel();
        thongbao_phongban = new javax.swing.JLabel();
        thongbao_ngtiepnhan = new javax.swing.JLabel();
        jDate_ctcv_st = new com.toedter.calendar.JDateChooser();
        jDate_ctcv_end = new com.toedter.calendar.JDateChooser();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox_admin_TB = new javax.swing.JComboBox<>();
        jButton_taoTB = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea_noidungTB = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable_duan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable_duan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên dự án", "Ngày bắt đầu", "Ngày kết thúc", "Mục tiêu", "Người nhận dự án", "Trạng thái hoàn thành", "Ghi chú"
            }
        ));
        jTable_duan.setRowHeight(30);
        jTable_duan.setRowMargin(3);
        jTable_duan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_duanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_duan);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("THAO TÁC TRÊN DỰ ÁN");

        jLabel26.setText("Tên dự án:");

        jLabel27.setText("Ngày bắt đầu:");

        jLabel28.setText("Ngày kết thúc:");

        jButton_addnewsp2.setText("Thêm");
        jButton_addnewsp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addnewspActionPerformed(evt);
            }
        });

        jButton_editsp2.setText("Sửa");
        jButton_editsp2.setEnabled(false);
        jButton_editsp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editspActionPerformed(evt);
            }
        });

        jButton_resetsp2.setText("Làm mới");
        jButton_resetsp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resetspActionPerformed(evt);
            }
        });

        jButton_deleteSP2.setText("Xóa");
        jButton_deleteSP2.setEnabled(false);
        jButton_deleteSP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteSPActionPerformed(evt);
            }
        });

        jLabel29.setText("ID:");

        jLabel10.setText("Mục tiêu:");

        jLabel11.setText("Chọn Người nhận dự án:");

        jLabel13.setText("Ghi chú");

        jTextField_ghichu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ghichuActionPerformed(evt);
            }
        });

        jTextfield_muctieu.setColumns(20);
        jTextfield_muctieu.setLineWrap(true);
        jTextfield_muctieu.setRows(5);
        jTextfield_muctieu.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextfield_muctieu);

        jLabel37.setText("Trạng thái:");

        jLabel_trangthai.setText("Trạng thái");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addComponent(jLabel25))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jButton_resetsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                            .addComponent(jButton_addnewsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_editsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_deleteSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel37)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel29)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextfield_tenduan)
                                    .addComponent(jLabel_id_duan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(jComboBox_ngnhan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_ghichu)
                                    .addComponent(jLabel_trangthai)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooser_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel25)
                .addGap(50, 50, 50)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel_id_duan, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextfield_tenduan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox_ngnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel_trangthai))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField_ghichu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_deleteSP2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_editsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addnewsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_resetsp2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChooser_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Tìm kiếm trong dự án:");

        jTextField_searchsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_searchspActionPerformed(evt);
            }
        });
        jTextField_searchsp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_searchspKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchspKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_searchspKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("THÔNG TIN DỰ ÁN");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(326, 326, 326))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(579, 579, 579)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_searchsp, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_searchsp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addGap(11, 11, 11)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dự án", jPanel5);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách dự án"));

        jTable_duan_congviec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ));
        jTable_duan_congviec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_duan_congviecMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_duan_congviec);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cây công việc"));

        jTree_congviec.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree_congviec.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree_congviec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree_congviecMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTree_congviec);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết công việc"));

        jLabel2.setText("id");

        jLabel3.setText("Tên công việc");

        jLabel4.setText("Loại công việc");

        jLabel5.setText("Thuộc công việc chính:");

        jLabel6.setText("Phòng ban");

        jLabel7.setText("Người tiếp nhận");

        jLabel8.setText("Ngày bắt đầu");

        jLabel9.setText("Ngày kết thúc");

        jLabel12.setText("Mục tiêu");

        jLabel14.setText("Trạng thái");

        jComboBox_ctcv_phongban.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_ctcv_phongban.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_ctcv_phongbanItemStateChanged(evt);
            }
        });

        jComboBox_loaicongviec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "congviecchinh", "congviecphu" }));
        jComboBox_loaicongviec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_loaicongviecItemStateChanged(evt);
            }
        });
        jComboBox_loaicongviec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_loaicongviecMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox_loaicongviecMouseExited(evt);
            }
        });
        jComboBox_loaicongviec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_loaicongviecActionPerformed(evt);
            }
        });

        jComboBox_ctcv_ngtiepnhan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextArea_ctcv_muctieu.setColumns(20);
        jTextArea_ctcv_muctieu.setLineWrap(true);
        jTextArea_ctcv_muctieu.setRows(5);
        jTextArea_ctcv_muctieu.setWrapStyleWord(true);
        jScrollPane6.setViewportView(jTextArea_ctcv_muctieu);

        jComboBox_ctcv_thuoccv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton_ctcv_submit.setText("HOÀN TẤT");
        jButton_ctcv_submit.setEnabled(false);
        jButton_ctcv_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ctcv_submitActionPerformed(evt);
            }
        });

        jButton_ctcv_huy.setText("HỦY");
        jButton_ctcv_huy.setEnabled(false);
        jButton_ctcv_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ctcv_huyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel2)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_ctcv_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_tencongviec)
                                .addComponent(jComboBox_loaicongviec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_ctcv_ngtiepnhan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6)
                                .addComponent(jLabel_ctcv_id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_ctcv_thuoccv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox_ctcv_phongban, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton_ctcv_huy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDate_thongbao_bd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDate_thongbao_kt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton_ctcv_submit)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_ctcv_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_tencongviec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox_loaicongviec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox_ctcv_thuoccv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox_ctcv_phongban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox_ctcv_ngtiepnhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jDate_thongbao_bd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jDate_thongbao_kt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_ctcv_trangthai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_ctcv_submit)
                    .addComponent(jButton_ctcv_huy))
                .addGap(48, 48, 48))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thao tác"));
        jPanel9.setEnabled(false);

        jButton_cvda_add.setText("THÊM CÔNG VIỆC");
        jButton_cvda_add.setEnabled(false);
        jButton_cvda_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cvda_addActionPerformed(evt);
            }
        });

        jButton_cvda_sua.setText("SỬA CÔNG VIỆC");
        jButton_cvda_sua.setEnabled(false);
        jButton_cvda_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cvda_suaActionPerformed(evt);
            }
        });

        jButton_cvda_xoa.setText("XÓA CÔNG VIỆC");
        jButton_cvda_xoa.setEnabled(false);
        jButton_cvda_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cvda_xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton_cvda_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_cvda_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_cvda_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton_cvda_add)
                .addGap(18, 18, 18)
                .addComponent(jButton_cvda_sua)
                .addGap(18, 18, 18)
                .addComponent(jButton_cvda_xoa)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(246, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 879, Short.MAX_VALUE)
                        .addComponent(jLabel_thaotac, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_thaotac, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phân công công việc", jPanel3);

        jTable_cvda_tiendo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên công viêcj", "Loại công việc", "Thuộc công việc", "Thuộc dự án", "Thuộc phòng ban", "Người tiếp nhận", "Ngày bắt đầu", "Ngày kết thúc", "Mục tiêu", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(jTable_cvda_tiendo);

        jLabel15.setText("Bộ lọc ");

        jComboBox_tenduan_tiendo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox_tenduan_tiendo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_tenduan_tiendoItemStateChanged(evt);
            }
        });
        jComboBox_tenduan_tiendo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jComboBox_tenduan_tiendoInputMethodTextChanged(evt);
            }
        });
        jComboBox_tenduan_tiendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_tenduan_tiendoActionPerformed(evt);
            }
        });

        jLabel16.setText("Tên dự án");

        jLabel17.setText("Loại công việc");

        jLabel19.setText("Phòng ban");

        jComboBox_loaicv_tiendo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "congviecchinh", "congviecphu" }));
        jComboBox_loaicv_tiendo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_loaicv_tiendoItemStateChanged(evt);
            }
        });
        jComboBox_loaicv_tiendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_loaicv_tiendoActionPerformed(evt);
            }
        });

        jComboBox_phongban_tiendo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox_phongban_tiendo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_phongban_tiendoItemStateChanged(evt);
            }
        });

        jLabel20.setText("Người tiếp nhận");

        jComboBox_ngtiepnhan_tiendo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox_ngtiepnhan_tiendo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_ngtiepnhan_tiendoItemStateChanged(evt);
            }
        });

        jTextField_timkiem_tiendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_timkiem_tiendoActionPerformed(evt);
            }
        });
        jTextField_timkiem_tiendo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_timkiem_tiendoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_timkiem_tiendoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_timkiem_tiendoKeyTyped(evt);
            }
        });

        jLabel21.setText("Tìm kiếm");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Cây công việc"));

        jTree_congviec_tiendo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree_congviec_tiendo.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree_congviec_tiendo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree_congviec_tiendoMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTree_congviec_tiendo);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jButton_buildchart.setText("Xem tiến độ dự án bằng biểu đồ");
        jButton_buildchart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buildchartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel21))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_tenduan_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_loaicv_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel19)
                                .addGap(31, 31, 31)
                                .addComponent(jComboBox_phongban_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_ngtiepnhan_tiendo, 0, 136, Short.MAX_VALUE)
                            .addComponent(jTextField_timkiem_tiendo))))
                .addGap(60, 60, 60)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_buildchart)
                    .addComponent(id_duan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jButton_buildchart, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(id_duan, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jComboBox_tenduan_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19)
                                    .addComponent(jComboBox_loaicv_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_phongban_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(jComboBox_ngtiepnhan_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField_timkiem_tiendo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Công việc và tiến độ", jPanel2);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Các công việc trễ dealine"));

        jTable_thongbao_deadline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên công viêcj", "Loại công việc", "Thuộc công việc", "Thuộc dự án", "Thuộc phòng ban", "Người tiếp nhận", "Ngày bắt đầu", "Ngày kết thúc", "Mục tiêu", "Trạng thái"
            }
        ));
        jTable_thongbao_deadline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_thongbao_deadlineMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable_thongbao_deadline);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10)
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Các công việc deadline hôm nay"));

        jTable_thongbao_dlnow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên công viêcj", "Loại công việc", "Thuộc công việc", "Thuộc dự án", "Thuộc phòng ban", "Người tiếp nhận", "Ngày bắt đầu", "Ngày kết thúc", "Mục tiêu", "Trạng thái"
            }
        ));
        jTable_thongbao_dlnow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_thongbao_dlnowMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable_thongbao_dlnow);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 694, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane11)
                    .addContainerGap()))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết công việc"));

        jLabel22.setText("id");

        jLabel23.setText("Tên công việc");

        jLabel24.setText("Loại công việc");

        jLabel30.setText("Thuộc công việc chính:");

        jLabel31.setText("Phòng ban");

        jLabel32.setText("Người tiếp nhận");

        jLabel33.setText("Ngày bắt đầu");

        jLabel34.setText("Ngày kết thúc");

        jLabel35.setText("Mục tiêu");

        jLabel36.setText("Trạng thái");

        jTextArea_thongbao_muctieu.setColumns(20);
        jTextArea_thongbao_muctieu.setLineWrap(true);
        jTextArea_thongbao_muctieu.setRows(5);
        jTextArea_thongbao_muctieu.setWrapStyleWord(true);
        jScrollPane8.setViewportView(jTextArea_thongbao_muctieu);

        jLabel_thongbao_id.setText("id");

        jButton_thongbao_nhacnho.setText("Nhắc nhở");
        jButton_thongbao_nhacnho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_thongbao_nhacnhoActionPerformed(evt);
            }
        });

        jButton_thongbao_canhbao.setText("Cảnh báo");
        jButton_thongbao_canhbao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_thongbao_canhbaoActionPerformed(evt);
            }
        });

        jLabel_thongbao_tencv.setText("tencongviec");

        jLabel_thongbao_loaicv.setText("loaicv");

        thongbao_thuoccv.setText("jLabel37");

        thongbao_phongban.setText("jLabel37");

        thongbao_ngtiepnhan.setText("jLabel37");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel30)
                            .addComponent(jLabel32)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel22)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_thongbao_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8)
                            .addComponent(jLabel_thongbao_id, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_thongbao_tencv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_thongbao_loaicv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thongbao_thuoccv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thongbao_phongban, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thongbao_ngtiepnhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDate_ctcv_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDate_ctcv_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton_thongbao_nhacnho)
                        .addGap(65, 65, 65)
                        .addComponent(jButton_thongbao_canhbao)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_thongbao_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel_thongbao_tencv))
                .addGap(24, 24, 24)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel_thongbao_loaicv))
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(thongbao_thuoccv))
                .addGap(34, 34, 34)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(thongbao_phongban))
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(thongbao_ngtiepnhan))
                .addGap(39, 39, 39)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jDate_ctcv_st, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(jDate_ctcv_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_thongbao_trangthai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_thongbao_nhacnho)
                    .addComponent(jButton_thongbao_canhbao))
                .addGap(279, 279, 279))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông báo"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Thông báo"
            }
        ));
        jScrollPane9.setViewportView(jTable1);

        jComboBox_admin_TB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        jComboBox_admin_TB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_admin_TBActionPerformed(evt);
            }
        });

        jButton_taoTB.setText("Tạo thông báo ");
        jButton_taoTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_taoTBActionPerformed(evt);
            }
        });

        jLabel38.setText("Gửi thông báo tới");

        jTextArea_noidungTB.setColumns(20);
        jTextArea_noidungTB.setRows(5);
        jScrollPane12.setViewportView(jTextArea_noidungTB);

        jLabel39.setText("Nội dung");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jComboBox_admin_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_taoTB)))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_admin_TB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_taoTB)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("Thông báo", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_searchspKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchspKeyTyped

    }//GEN-LAST:event_jTextField_searchspKeyTyped

    private void jTextField_searchspKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchspKeyReleased
        // TODO add your handling code here:
        jTable_duan.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc","Mục tiêu", "Người nhận dự án", "Trạng thái hoàn thành","Ghi chú"}));
        sp.fillDuanJtable(jTable_duan, jTextField_searchsp.getText().toString());
    }//GEN-LAST:event_jTextField_searchspKeyReleased

    private void jTextField_searchspKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchspKeyPressed
        jTable_duan.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc","Mục tiêu", "Người nhận dự án", "Trạng thái hoàn thành","Ghi chú"}));
        sp.fillDuanJtable(jTable_duan, jTextField_searchsp.getText().toString());
    }//GEN-LAST:event_jTextField_searchspKeyPressed

    private void jButton_deleteSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteSPActionPerformed
        int id = Integer.valueOf(jLabel_id_duan.getText());
        jButton_editsp2.setEnabled(false);
        jButton_deleteSP2.setEnabled(false);
        sp.insertUpdateDeleteProject('d', id, null, null, null,null,null,null);
        jTable_duan.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc","Mục tiêu", "Người nhận dự án", "Trạng thái hoàn thành","Ghi chú"}));
        sp.fillDuanJtable(jTable_duan, jTextField_searchsp.getText().toString());
        jButton_addnewsp2.setEnabled(true);
        jLabel_id_duan.setText("");
        jLabel_id_duan.setText("");
        jTextfield_muctieu.setText("");
        jTextField_ghichu.setText("");
        jTextfield_tenduan.setText("");
jTable_duan_congviec.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc"}));
        combobox_nguoinhan();
        sp.fillDuan_CongviecJtable(jTable_duan_congviec, "");
        combobox_phongban();
        combobox_duan();
    }//GEN-LAST:event_jButton_deleteSPActionPerformed

    private void jButton_resetspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resetspActionPerformed
        // TODO add your handling code here:
        jButton_addnewsp2.setEnabled(true);
        jButton_editsp2.setEnabled(false);
        jButton_deleteSP2.setEnabled(false);
        jTable_duan.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc","Mục tiêu", "Người nhận dự án","Trạng thái hoàn thành","Ghi chú"}));
        sp.fillDuanJtable(jTable_duan, "");
        jLabel_id_duan.setText("");
        jTextfield_muctieu.setText("");
        jTextField_ghichu.setText("");
        jTextfield_tenduan.setText("");
        jTable_duan_congviec.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc"}));
        sp.fillDuan_CongviecJtable(jTable_duan_congviec, "");
        combobox_nguoinhan();
        combobox_phongban();
        combobox_duan();

    }//GEN-LAST:event_jButton_resetspActionPerformed

    private void jButton_editspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editspActionPerformed
        // TODO add your handling code here:
        String tenduan =jTextfield_tenduan.getText();
        String ngaybatdau =((JTextField)jDateChooser_st.getDateEditor().getUiComponent()).getText();
        System.out.println(ngaybatdau);
        String ngayketthuc =((JTextField)jDateChooser_end.getDateEditor().getUiComponent()).getText();
        System.out.println(ngayketthuc);
        String muctieu =jTextfield_muctieu.getText();
        String nguoinhan = (String)jComboBox_ngnhan.getSelectedItem();
        String phongban = (String)jComboBox_ctcv_phongban.getSelectedItem();
        String ghichu =jTextField_ghichu.getText();
        int id = Integer.valueOf(jLabel_id_duan.getText());
        Duan sp = new Duan();
        jButton_addnewsp2.setEnabled(true);
        sp.insertUpdateDeleteProject('u', id, tenduan, ngaybatdau, ngayketthuc,muctieu,nguoinhan,ghichu);
        jTextfield_muctieu.setText("");
        jTextField_ghichu.setText("");
        jTextfield_tenduan.setText("");
        jLabel_id_duan.setText("");
        jTable_duan.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc","Mục tiêu", "Người nhận dự án", "Trạng thái hoàn thành","Ghi chú"}));
        sp.fillDuanJtable(jTable_duan, jTextField_searchsp.getText().toString());
        jButton_editsp2.setEnabled(false);
        jButton_deleteSP2.setEnabled(false);
jTable_duan_congviec.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc"}));
        sp.fillDuan_CongviecJtable(jTable_duan_congviec, "");
        combobox_nguoinhan();
        combobox_phongban();
        combobox_duan();

    }//GEN-LAST:event_jButton_editspActionPerformed

    private void jButton_addnewspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addnewspActionPerformed
        // TODO add your handling code here:
        String tenduan =jTextfield_tenduan.getText();
        String ngaybatdau =((JTextField)jDateChooser_st.getDateEditor().getUiComponent()).getText();
        System.out.println(ngaybatdau);
        String ngayketthuc =((JTextField)jDateChooser_end.getDateEditor().getUiComponent()).getText();
        System.out.println(ngayketthuc);
        String muctieu =jTextfield_muctieu.getText();
        String nguoinhan = (String)jComboBox_ngnhan.getSelectedItem();
        String phongban = (String)jComboBox_ctcv_phongban.getSelectedItem();
        String ghichu =jTextField_ghichu.getText();
        if(tenduan.equals("")||ngaybatdau.equals("")||ngayketthuc.equals("")||muctieu.equals("")||nguoinhan.equals("")||ghichu.equals("")){
            
            JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập đầy đủ để thêm");
        }else{
          Duan sp = new Duan();
        sp.insertUpdateDeleteProject('i', null, tenduan, ngaybatdau,ngayketthuc, muctieu,nguoinhan,ghichu);
        jTextfield_muctieu.setText("");
        jTextField_ghichu.setText("");
        jTextfield_tenduan.setText("");
        jTable_duan.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc","Mục tiêu", "Người nhận dự án","Trạng thái hoàn thành","Ghi chú"}));
        sp.fillDuanJtable(jTable_duan, jTextField_searchsp.getText().toString());
        jButton_editsp2.setEnabled(false);
        jButton_deleteSP2.setEnabled(false);
        jTable_duan_congviec.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên dự án","Ngày bắt đầu","Ngày kết thúc"}));
        sp.fillDuan_CongviecJtable(jTable_duan_congviec, "");
        combobox_nguoinhan();
        combobox_phongban();
        combobox_duan();
        }
        
        
        
    }//GEN-LAST:event_jButton_addnewspActionPerformed

    private void jTable_duanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_duanMouseClicked
        // TODO add your handling code here:
        jButton_addnewsp2.setEnabled(false);
        jButton_editsp2.setEnabled(true);
        jButton_deleteSP2.setEnabled(true);
        int rowIndex = jTable_duan.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_duan.getModel();
        String tc = model.getValueAt(rowIndex,0).toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `duan` WHERE `id`="+tc+"");
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String tenduan = rs.getString("Tenduan");
                String muctieu = rs.getString("muctieu");
                String trangthai = rs.getString("trangthai");
                String ghichu = rs.getString("ghichu");
                String nguoinhan = rs.getString("nguoinhanduan");
                Date date = rs.getDate("ngaybatdau");
                Date date1 = rs.getDate("ngayketthuc");
                jLabel_trangthai.setText(trangthai);
                jLabel_id_duan.setText(tc);
                jTextfield_tenduan.setText(tenduan);
                jTextfield_muctieu.setText(muctieu);
                jTextField_ghichu.setText(ghichu);                
                jDateChooser_st.setDate(date);
                jDateChooser_end.setDate(date1);
                jComboBox_ngnhan.setSelectedItem(nguoinhan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, null);
        }
        
        
        
    }//GEN-LAST:event_jTable_duanMouseClicked

    private void jTextField_searchspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchspActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_searchspActionPerformed

    private void jTable_duan_congviecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_duan_congviecMouseClicked
        // fillTree...
        jButton_cvda_add.setEnabled(true);
        int rowIndex = jTable_duan_congviec.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_duan_congviec.getModel();
        int id = (int) model.getValueAt(rowIndex,0);
        sp.TableNamesTest(id,jTree_congviec); 
        combobox_thuoccongviec(id);
    }//GEN-LAST:event_jTable_duan_congviecMouseClicked

    private void jTree_congviecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree_congviecMouseClicked
        int rowIndex = jTable_duan_congviec.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_duan_congviec.getModel();
        int idduan = (int) model.getValueAt(rowIndex,0);
        
        TreeSelectionModel smd = jTree_congviec.getSelectionModel();
        if(smd.getSelectionCount() > 0){
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree_congviec.getSelectionPath().getLastPathComponent();
            jTextField_tencongviec.setText(selectedNode.getUserObject().toString());
            
        }
        jButton_cvda_add.setEnabled(true);
        jButton_cvda_sua.setEnabled(true);
        jButton_cvda_xoa.setEnabled(true);
        String tencongviec = jTextField_tencongviec.getText();
        System.out.println(tencongviec);
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `congviec` WHERE `Tencongviec`=?");
            ps.setString(1, tencongviec);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String loaicongviec = rs.getString("Loaicongviec");
                String thuoccongviec = rs.getString("Thuoccongviec");
                String phongban = rs.getString("Phongban");
                String nguoitiepnhan = rs.getString("Nguoitiepnhan");
                String muctieu = rs.getString("Muctieu");
                String trangthai = rs.getString("Trangthai");
                Date date_st = rs.getDate("Ngaybatdau");
                Date date_end = rs.getDate("Ngayketthuc");
                System.out.println(String.valueOf(id));
                jLabel_ctcv_id.setText(String.valueOf(id));
                jComboBox_loaicongviec.setSelectedItem(loaicongviec);
                jComboBox_ctcv_thuoccv.setSelectedItem(thuoccongviec);
                jComboBox_ctcv_phongban.setSelectedItem(phongban);
                jComboBox_ctcv_ngtiepnhan.setSelectedItem(nguoitiepnhan);
                jTextArea_ctcv_muctieu.setText(muctieu);                
                jDate_ctcv_st.setDate(date_st);
                jDate_ctcv_end.setDate(date_end);
                jLabel_ctcv_trangthai.setText(trangthai);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, null);
        }
    }//GEN-LAST:event_jTree_congviecMouseClicked

    private void jComboBox_loaicongviecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_loaicongviecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_loaicongviecActionPerformed

    private void jComboBox_loaicongviecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_loaicongviecMouseClicked
        // TODO add your handling code here:
  
            
    }//GEN-LAST:event_jComboBox_loaicongviecMouseClicked

    private void jComboBox_loaicongviecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_loaicongviecMouseExited

    }//GEN-LAST:event_jComboBox_loaicongviecMouseExited

    private void jComboBox_loaicongviecItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_loaicongviecItemStateChanged
        combobox_ngtiepnhan();
        int rowIndex = jTable_duan_congviec.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_duan_congviec.getModel();
        int idduan = (int) model.getValueAt(rowIndex,0);
        combobox_thuoccongviec(idduan);


    }//GEN-LAST:event_jComboBox_loaicongviecItemStateChanged
    private void settrue(){
       jComboBox_ctcv_thuoccv.setEnabled(true); 
    }
    private void setfalse(){

       jComboBox_ctcv_thuoccv.setEnabled(false); 
    }
    private void jComboBox_ctcv_phongbanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_ctcv_phongbanItemStateChanged
        combobox_ngtiepnhan();
    }//GEN-LAST:event_jComboBox_ctcv_phongbanItemStateChanged

    private void jButton_cvda_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cvda_addActionPerformed
        // TODO add your handling code here:
        jButton_ctcv_submit.setEnabled(true);
        jButton_ctcv_huy.setEnabled(true);
        jButton_cvda_add.setEnabled(false);
        jLabel_ctcv_id.setText("");
        jLabel_thaotac.setText("1");
        jTextField_tencongviec.setText("");
        jTextArea_ctcv_muctieu.setText("");
        jLabel_ctcv_trangthai.setText("");
    }//GEN-LAST:event_jButton_cvda_addActionPerformed

    private void jButton_ctcv_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ctcv_submitActionPerformed
        // TODO add your handling code here:
        int rowIndex = jTable_duan_congviec.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_duan_congviec.getModel();
        int idduan = (int) model.getValueAt(rowIndex,0);
        String thaotac = jLabel_thaotac.getText();
        
        String tencongviec =jTextField_tencongviec.getText();
        String ngaybatdau =((JTextField)jDate_ctcv_st.getDateEditor().getUiComponent()).getText();
        String ngayketthuc =((JTextField)jDate_ctcv_end.getDateEditor().getUiComponent()).getText();
        String muctieu =jTextArea_ctcv_muctieu.getText();
        String nguoinhan = (String)jComboBox_ctcv_ngtiepnhan.getSelectedItem();
        String phongban = (String)jComboBox_ctcv_phongban.getSelectedItem();
        String thuoccongviec =(String) jComboBox_ctcv_thuoccv.getSelectedItem();
        String loaicongviec =(String) jComboBox_loaicongviec.getSelectedItem();
        if(thaotac.equals("1")){
          
        if(tencongviec.equals("")||ngaybatdau.equals("")||ngayketthuc.equals("")||muctieu.equals("")||nguoinhan.equals("")||phongban.equals("")||loaicongviec.equals("")){
            
            JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập đầy đủ để thêm");
        }else{
        sp.insertUpdateDeleteCV('i', null, tencongviec,loaicongviec,thuoccongviec,idduan,phongban,nguoinhan, ngaybatdau,ngayketthuc, muctieu);
        sp.TableNamesTest(idduan,jTree_congviec);
        jTextArea_ctcv_muctieu.setText("");
       
        jTextField_tencongviec.setText("");
        jButton_ctcv_submit.setEnabled(false);
        jButton_ctcv_huy.setEnabled(false);
        jButton_cvda_add.setEnabled(true);
         
        }
        }
        
        if(thaotac.equals("2")){
        String t = jLabel_ctcv_id.getText();
        int id = Integer.parseInt(t);  
        if(tencongviec.equals("")||ngaybatdau.equals("")||ngayketthuc.equals("")||muctieu.equals("")||nguoinhan.equals("")||phongban.equals("")||loaicongviec.equals("")){
            
            JOptionPane.showMessageDialog(rootPane, "Bạn phải nhập đầy đủ để sửa");
        }else{
        sp.insertUpdateDeleteCV('u', id, tencongviec,loaicongviec,thuoccongviec,idduan,phongban,nguoinhan, ngaybatdau,ngayketthuc, muctieu);
        sp.TableNamesTest(idduan,jTree_congviec);
        jTextArea_ctcv_muctieu.setText("");
        jTextField_tencongviec.setText("");
        jButton_ctcv_submit.setEnabled(false);
        jButton_ctcv_huy.setEnabled(false);
        jButton_cvda_add.setEnabled(true);
        jButton_cvda_sua.setEnabled(false);
         
        }
        }
    }//GEN-LAST:event_jButton_ctcv_submitActionPerformed

    private void jButton_ctcv_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ctcv_huyActionPerformed
        // TODO add your handling code here:
                jButton_ctcv_submit.setEnabled(false);
        jButton_ctcv_huy.setEnabled(false);
        jButton_cvda_add.setEnabled(true);
        jButton_cvda_sua.setEnabled(true);
        jButton_cvda_xoa.setEnabled(true);
    }//GEN-LAST:event_jButton_ctcv_huyActionPerformed

    private void jButton_cvda_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cvda_suaActionPerformed
        // TODO add your handling code here:
        jButton_ctcv_submit.setEnabled(true);
        jButton_ctcv_huy.setEnabled(true);
        jLabel_thaotac.setText("2");
        jButton_cvda_add.setEnabled(false);
        jButton_cvda_xoa.setEnabled(false);
        jButton_cvda_sua.setEnabled(false);
    }//GEN-LAST:event_jButton_cvda_suaActionPerformed

    private void jButton_cvda_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cvda_xoaActionPerformed
        // TODO add your handling code here:
       int rowIndex = jTable_duan_congviec.getSelectedRow();

       DefaultTableModel model =(DefaultTableModel) jTable_duan_congviec.getModel();
       int idduan = (int) model.getValueAt(rowIndex,0);
       String tencongviec = jTextField_tencongviec.getText();
       int id = Integer.valueOf(jLabel_ctcv_id.getText());
       String loaicongviec =(String) jComboBox_loaicongviec.getSelectedItem();

       int opt = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa công việc này? Việc này sẽ xóa các công việc con bên trong nếu có.","Delete",JOptionPane.YES_NO_OPTION);
       if(opt==0)
       {
                  sp.insertUpdateDeleteCV('d', id, null, null, null, null, null, null, null, null, null);
            Connection con = MyConnection.getConnection();
            PreparedStatement ps;
//            if(loaicongviec.equals("congviecchinh")){
//                int opt2 = JOptionPane.showConfirmDialog(null, "Bạn đang xóa một công việc chính, bạn  xóa những công việc con trong công việc chính này?","Delete",JOptionPane.YES_NO_OPTION);
//                if(opt2==0)
//                {
//                    sp.insertUpdateDeleteCV('d', id, null, null, null, null, null, null, null, null, null);
                    try 
                    {
                        ps = con.prepareStatement("SELECT id FROM `congviec` WHERE `Thuoccongviec`=?");
                        ps.setString(1, tencongviec);
                        ResultSet rs =ps.executeQuery();
                        while(rs.next())
                        {
                            sp.insertUpdateDeleteCV('d', rs.getInt("id"), null, null, null, null, null, null, null, null, null);
                        }
                    } catch (SQLException e) 
                    {
                        JOptionPane.showMessageDialog(rootPane, null);
                    }
                }
//            }
//       }
       sp.TableNamesTest(idduan, jTree_congviec);
              jLabel_ctcv_id.setText("");
              jTextField_tencongviec.setText("");
              jTextArea_ctcv_muctieu.setText("");
              jLabel_ctcv_trangthai.setText("");
              jButton_cvda_sua.setEnabled(false);
              jButton_cvda_xoa.setEnabled(false);
              
    }//GEN-LAST:event_jButton_cvda_xoaActionPerformed

    private void jTree_congviec_tiendoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree_congviec_tiendoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree_congviec_tiendoMouseClicked

    private void jTextField_timkiem_tiendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_timkiem_tiendoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_timkiem_tiendoActionPerformed

    private void jButton_thongbao_canhbaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_thongbao_canhbaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_thongbao_canhbaoActionPerformed

    private void jComboBox_admin_TBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_admin_TBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_admin_TBActionPerformed

    private void jComboBox_loaicv_tiendoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_loaicv_tiendoItemStateChanged
        // TODO add your handling code here:
        combobox_ngtiepnhan_tiendo();        
//        String tenduan3 = jComboBox_tenduan_tiendo.getSelectedItem().toString();
//        Connection con = MyConnection.getConnection();
//        PreparedStatement ps;
//        String loaicongviec3 = jComboBox_loaicv_tiendo.getSelectedItem().toString();
//        String phongban3 = jComboBox_phongban_tiendo.getSelectedItem().toString();
//        String ngtiepnhan3 = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
//        String valuetosearch3 = jTextField_timkiem_tiendo.getText();
//        if(loaicongviec3.equals("Tất cả")){
//            loaicongviec3 = "";
//        }
//        if(phongban3.equals("Tất cả")){
//            phongban3 = "";
//        }
//        if(ngtiepnhan3.equals("Tất cả")){
//            ngtiepnhan3 = "";
//        }
//        if (tenduan3.equals("Tất cả")){
//            jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
//            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch3, null, loaicongviec3, phongban3, ngtiepnhan3);
//        }else{
//            try {
//            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
//            ps.setString(1, tenduan3);
//            ResultSet rs =ps.executeQuery();
//            if(rs.next()){
//                int id = rs.getInt("id");
//                jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
//                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch3, id, loaicongviec3, phongban3, ngtiepnhan3);
//            }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(rootPane, null);
//            }
//
//        }
    }//GEN-LAST:event_jComboBox_loaicv_tiendoItemStateChanged

    private void jComboBox_phongban_tiendoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_phongban_tiendoItemStateChanged
           combobox_ngtiepnhan_tiendo();
//           String tenduan = jComboBox_tenduan_tiendo.getSelectedItem().toString();
//        Connection con = MyConnection.getConnection();
//        PreparedStatement ps;
//        String loaicongviec = jComboBox_loaicv_tiendo.getSelectedItem().toString();
//        String phongban = jComboBox_phongban_tiendo.getSelectedItem().toString();
//        String ngtiepnhan = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
//        String valuetosearch = jTextField_timkiem_tiendo.getText();
//        if(loaicongviec.equals("Tất cả")){
//            loaicongviec = "";
//        }
//        if(phongban.equals("Tất cả")){
//            phongban = "";
//        }
//        if(ngtiepnhan.equals("Tất cả")){
//            ngtiepnhan = "";
//        }
//        if (tenduan.equals("Tất cả")){
//            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, null, loaicongviec, phongban, ngtiepnhan);
//        }else{
//            try {
//            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
//            ps.setString(1, tenduan);
//            ResultSet rs =ps.executeQuery();
//            if(rs.next()){
//                int id = rs.getInt("id");
//                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, id, loaicongviec, phongban, ngtiepnhan);
//            }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(rootPane, null);
//            }
//
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_phongban_tiendoItemStateChanged

    private void jComboBox_tenduan_tiendoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_tenduan_tiendoItemStateChanged
        // TODO add your handling code here:
//        combobox_ngtiepnhan_tiendo();

        String tenduan = jComboBox_tenduan_tiendo.getSelectedItem().toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
//        jComboBox_loaicv_tiendo.setSelectedItem("Tất cả");
//        jComboBox_phongban_tiendo.setSelectedItem("Tất cả");
//        jComboBox_ngtiepnhan_tiendo.setSelectedItem("Tất cả");
        String loaicongviec = jComboBox_loaicv_tiendo.getSelectedItem().toString();
        String phongban = jComboBox_phongban_tiendo.getSelectedItem().toString();
        String ngtiepnhan = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
        String valuetosearch = jTextField_timkiem_tiendo.getText();
        System.out.println(loaicongviec+" "+phongban);
        if(loaicongviec.equals("Tất cả")){
            loaicongviec = "";
        }
        if(phongban.equals("Tất cả")){
            phongban = "";
        }
        if(ngtiepnhan.equals("Tất cả")){
            ngtiepnhan = "";
        }
        if (tenduan.equals("Tất cả")){
            jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, null, loaicongviec, phongban, ngtiepnhan);            
        }else{
            try {
            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
            ps.setString(1, tenduan);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
                id_duan.setText(String.valueOf(id));
                jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, id, loaicongviec, phongban, ngtiepnhan);
                sp.TableNamesTest(id, jTree_congviec_tiendo);
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, null);
            }

        }
    }//GEN-LAST:event_jComboBox_tenduan_tiendoItemStateChanged
    
    private void jComboBox_ngtiepnhan_tiendoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_ngtiepnhan_tiendoItemStateChanged
        // TODO add your handling code here:
        String tenduan = jComboBox_tenduan_tiendo.getSelectedItem().toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        String loaicongviec = jComboBox_loaicv_tiendo.getSelectedItem().toString();
        String phongban = jComboBox_phongban_tiendo.getSelectedItem().toString();
        String ngtiepnhan = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
        String valuetosearch = jTextField_timkiem_tiendo.getText();
        if(loaicongviec.equals("Tất cả")){
            loaicongviec = "";
        }
        if(phongban.equals("Tất cả")){
            phongban = "";
        }
        if(ngtiepnhan.equals("Tất cả")){
            ngtiepnhan = "";
        }
        if (tenduan.equals("Tất cả")){
            jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, null, loaicongviec, phongban, ngtiepnhan);
        }else{
            try {
            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
            ps.setString(1, tenduan);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
                jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, id, loaicongviec, phongban, ngtiepnhan);
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, null);
            }

        }
    }//GEN-LAST:event_jComboBox_ngtiepnhan_tiendoItemStateChanged

    private void jComboBox_loaicv_tiendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_loaicv_tiendoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_loaicv_tiendoActionPerformed

    private void jTextField_timkiem_tiendoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_timkiem_tiendoKeyPressed
        // TODO add your handling code here:
        String tenduan = jComboBox_tenduan_tiendo.getSelectedItem().toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
//        jComboBox_loaicv_tiendo.setSelectedItem("Tất cả");
//        jComboBox_phongban_tiendo.setSelectedItem("Tất cả");
//        jComboBox_ngtiepnhan_tiendo.setSelectedItem("Tất cả");
        String loaicongviec = jComboBox_loaicv_tiendo.getSelectedItem().toString();
        String phongban = jComboBox_phongban_tiendo.getSelectedItem().toString();
        String ngtiepnhan = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
        String valuetosearch = jTextField_timkiem_tiendo.getText();
        System.out.println(loaicongviec+" "+phongban);
        if(loaicongviec.equals("Tất cả")){
            loaicongviec = "";
        }
        if(phongban.equals("Tất cả")){
            phongban = "";
        }
        if(ngtiepnhan.equals("Tất cả")){
            ngtiepnhan = "";
        }
        if (tenduan.equals("Tất cả")){
            jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, null, loaicongviec, phongban, ngtiepnhan);
        }else{
            try {
            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
            ps.setString(1, tenduan);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                System.out.println(id);
                jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, id, loaicongviec, phongban, ngtiepnhan);
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, null);
            }

        }
    }//GEN-LAST:event_jTextField_timkiem_tiendoKeyPressed

    private void jTextField_timkiem_tiendoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_timkiem_tiendoKeyReleased
        // TODO add your handling code here:
        String tenduan = jComboBox_tenduan_tiendo.getSelectedItem().toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
//        jComboBox_loaicv_tiendo.setSelectedItem("Tất cả");
//        jComboBox_phongban_tiendo.setSelectedItem("Tất cả");
//        jComboBox_ngtiepnhan_tiendo.setSelectedItem("Tất cả");
        String loaicongviec = jComboBox_loaicv_tiendo.getSelectedItem().toString();
        String phongban = jComboBox_phongban_tiendo.getSelectedItem().toString();
        String ngtiepnhan = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
        String valuetosearch = jTextField_timkiem_tiendo.getText();
        System.out.println(loaicongviec+" "+phongban);
        if(loaicongviec.equals("Tất cả")){
            loaicongviec = "";
        }
        if(phongban.equals("Tất cả")){
            phongban = "";
        }
        if(ngtiepnhan.equals("Tất cả")){
            ngtiepnhan = "";
        }
        if (tenduan.equals("Tất cả")){
            jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, null, loaicongviec, phongban, ngtiepnhan);
        }else{
            try {
            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
            ps.setString(1, tenduan);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                System.out.println(id);
                jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, id, loaicongviec, phongban, ngtiepnhan);
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, null);
            }

        }
    }//GEN-LAST:event_jTextField_timkiem_tiendoKeyReleased

    private void jTextField_timkiem_tiendoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_timkiem_tiendoKeyTyped
        // TODO add your handling code here:
        String tenduan = jComboBox_tenduan_tiendo.getSelectedItem().toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
//        jComboBox_loaicv_tiendo.setSelectedItem("Tất cả");
//        jComboBox_phongban_tiendo.setSelectedItem("Tất cả");
//        jComboBox_ngtiepnhan_tiendo.setSelectedItem("Tất cả");
        String loaicongviec = jComboBox_loaicv_tiendo.getSelectedItem().toString();
        String phongban = jComboBox_phongban_tiendo.getSelectedItem().toString();
        String ngtiepnhan = jComboBox_ngtiepnhan_tiendo.getSelectedItem().toString();
        String valuetosearch = jTextField_timkiem_tiendo.getText();
        System.out.println(loaicongviec+" "+phongban);
        if(loaicongviec.equals("Tất cả")){
            loaicongviec = "";
        }
        if(phongban.equals("Tất cả")){
            phongban = "";
        }
        if(ngtiepnhan.equals("Tất cả")){
            ngtiepnhan = "";
        }
        if (tenduan.equals("Tất cả")){
            jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
            sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, null, loaicongviec, phongban, ngtiepnhan);
        }else{
            try {
            ps = con.prepareStatement("SELECT `id` FROM `duan` WHERE `Tenduan`=?");
            ps.setString(1, tenduan);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                System.out.println(id);
                jTable_cvda_tiendo.setModel(new DefaultTableModel(null,new Object[]{"ID","Tên công việc","Loại công việc","Thuộc công việc","Thuộc dự án", "Thuộc phòng ban","Người tiếp nhận","Ngày bắt đầu","Ngày kết thúc","Mục tiêu","Trạng thái"}));
                sp.fillCongviec_tiendoJtable(jTable_cvda_tiendo, valuetosearch, id, loaicongviec, phongban, ngtiepnhan);
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, null);
            }

        }
    }//GEN-LAST:event_jTextField_timkiem_tiendoKeyTyped

    private void jButton_buildchartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buildchartActionPerformed
        try {
            GanttDemo2 demo = new GanttDemo2("Lịch trình dự án",id);

        } catch (SQLException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }//GEN-LAST:event_jButton_buildchartActionPerformed

    private void jTextField_ghichuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ghichuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ghichuActionPerformed

    private void jComboBox_tenduan_tiendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_tenduan_tiendoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_tenduan_tiendoActionPerformed

    private void jTable_thongbao_deadlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_thongbao_deadlineMouseClicked
        // TODO add your handling code here:
        int rowIndex = jTable_thongbao_deadline.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_thongbao_deadline.getModel();
        String tc = model.getValueAt(rowIndex,0).toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `congviec` WHERE `id`="+tc+"");
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String tencv = rs.getString("Tencongviec");
                String loaicv = rs.getString("Loaicongviec");
                String thuoccv = rs.getString("Thuoccongviec");
                String ghichu = rs.getString("IDduan");
                String phongban = rs.getString("Phongban");
                String muctieu = rs.getString("Muctieu");
                String ngnhan = rs.getString("Nguoitiepnhan");
                String Daxacnhan = rs.getString("Daxacnhan");
                Date date = rs.getDate("Ngaybatdau");
                Date date1 = rs.getDate("Ngayketthuc");
                String trangthai = rs.getString("Trangthai");
                jLabel_thongbao_id.setText(String.valueOf(id));
                jLabel_thongbao_tencv.setText(tencv);
                jLabel_thongbao_loaicv.setText(loaicv);
                thongbao_thuoccv.setText(thuoccv);
                thongbao_phongban.setText(phongban);
                thongbao_ngtiepnhan.setText(ngnhan);
                jDate_thongbao_bd.setDate(date);
                jDate_thongbao_kt.setDate(date1);
                jTextArea_thongbao_muctieu.setText(muctieu);
                jLabel_thongbao_trangthai.setText(trangthai);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, null);
        }
        
        
        
    }//GEN-LAST:event_jTable_thongbao_deadlineMouseClicked

    private void jTable_thongbao_dlnowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_thongbao_dlnowMouseClicked
        // TODO add your handling code here:
        int rowIndex = jTable_thongbao_dlnow.getSelectedRow();
        DefaultTableModel model =(DefaultTableModel) jTable_thongbao_dlnow.getModel();
        String tc = model.getValueAt(rowIndex,0).toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `congviec` WHERE `id`="+tc+"");
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String tencv = rs.getString("Tencongviec");
                String loaicv = rs.getString("Loaicongviec");
                String thuoccv = rs.getString("Thuoccongviec");
                String ghichu = rs.getString("IDduan");
                String phongban = rs.getString("Phongban");
                String muctieu = rs.getString("Muctieu");
                String ngnhan = rs.getString("Nguoitiepnhan");
                String Daxacnhan = rs.getString("Daxacnhan");
                Date date = rs.getDate("Ngaybatdau");
                Date date1 = rs.getDate("Ngayketthuc");
                String trangthai = rs.getString("Trangthai");
                jLabel_thongbao_id.setText(String.valueOf(id));
                jLabel_thongbao_tencv.setText(tencv);
                jLabel_thongbao_loaicv.setText(loaicv);
                thongbao_thuoccv.setText(thuoccv);
                thongbao_phongban.setText(phongban);
                thongbao_ngtiepnhan.setText(ngnhan);
                jDate_thongbao_bd.setDate(date);
                jDate_thongbao_kt.setDate(date1);
                jTextArea_thongbao_muctieu.setText(muctieu);
                jLabel_thongbao_trangthai.setText(trangthai);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, null);
        }
    }//GEN-LAST:event_jTable_thongbao_dlnowMouseClicked

    private void jButton_thongbao_nhacnhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_thongbao_nhacnhoActionPerformed
         String ngnhan = thongbao_ngtiepnhan.getText();
         String cv = jLabel_thongbao_tencv.getText();
         LocalDate now = java.time.LocalDate.now(); 
            String datenow=now.toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO `thongbao`(`nguoigui`, `nguoinhan`, `thoigian`, `noidung`) VALUES (?,?,?,?)");
            ps.setString(1, "admin");
            ps.setString(2, ngnhan);
            ps.setString(3, datenow);
            ps.setString(4, "Nhac nho thoi han cong viec: "+cv);
            if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_thongbao_nhacnhoActionPerformed

    private void jButton_taoTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_taoTBActionPerformed
        // TODO add your handling code here:
        String ngnhan = jComboBox_admin_TB.getSelectedItem().toString();
         String noidung = jTextArea_noidungTB.getText();
//         if(noidung.trim().equals("Tất cả"))
//         {noidung = "All";}
////         String noidung2="abc";
         LocalDate now = java.time.LocalDate.now(); 
            String datenow=now.toString();
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO `thongbao`(`nguoigui`, `nguoinhan`, `thoigian`, `noidung`) VALUES (?,?,?,?)");
            ps.setString(1, "admin");
            ps.setString(2, ngnhan);
            ps.setString(3, datenow);
            ps.setString(4, noidung);
            if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, null);
        }
    }//GEN-LAST:event_jButton_taoTBActionPerformed

    private void jComboBox_tenduan_tiendoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jComboBox_tenduan_tiendoInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_tenduan_tiendoInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainActivity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainActivity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel id_duan;
    private javax.swing.JButton jButton_addnewsp2;
    private javax.swing.JButton jButton_buildchart;
    private javax.swing.JButton jButton_ctcv_huy;
    private javax.swing.JButton jButton_ctcv_submit;
    private javax.swing.JButton jButton_cvda_add;
    private javax.swing.JButton jButton_cvda_sua;
    private javax.swing.JButton jButton_cvda_xoa;
    private javax.swing.JButton jButton_deleteSP2;
    private javax.swing.JButton jButton_editsp2;
    private javax.swing.JButton jButton_resetsp2;
    private javax.swing.JButton jButton_taoTB;
    private javax.swing.JButton jButton_thongbao_canhbao;
    private javax.swing.JButton jButton_thongbao_nhacnho;
    private javax.swing.JComboBox<String> jComboBox_admin_TB;
    private javax.swing.JComboBox<String> jComboBox_ctcv_ngtiepnhan;
    private javax.swing.JComboBox<String> jComboBox_ctcv_phongban;
    private javax.swing.JComboBox<String> jComboBox_ctcv_thuoccv;
    private javax.swing.JComboBox<String> jComboBox_loaicongviec;
    private javax.swing.JComboBox<String> jComboBox_loaicv_tiendo;
    private javax.swing.JComboBox<String> jComboBox_ngnhan;
    private javax.swing.JComboBox<String> jComboBox_ngtiepnhan_tiendo;
    private javax.swing.JComboBox<String> jComboBox_phongban_tiendo;
    private javax.swing.JComboBox<String> jComboBox_tenduan_tiendo;
    private com.toedter.calendar.JDateChooser jDateChooser_end;
    private com.toedter.calendar.JDateChooser jDateChooser_st;
    private com.toedter.calendar.JDateChooser jDate_ctcv_end;
    private com.toedter.calendar.JDateChooser jDate_ctcv_st;
    private com.toedter.calendar.JDateChooser jDate_thongbao_bd;
    private com.toedter.calendar.JDateChooser jDate_thongbao_kt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_ctcv_id;
    private javax.swing.JLabel jLabel_ctcv_trangthai;
    private javax.swing.JLabel jLabel_id_duan;
    private javax.swing.JLabel jLabel_thaotac;
    private javax.swing.JLabel jLabel_thongbao_id;
    private javax.swing.JLabel jLabel_thongbao_loaicv;
    private javax.swing.JLabel jLabel_thongbao_tencv;
    private javax.swing.JLabel jLabel_thongbao_trangthai;
    private javax.swing.JLabel jLabel_trangthai;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_cvda_tiendo;
    public javax.swing.JTable jTable_duan;
    private javax.swing.JTable jTable_duan_congviec;
    private javax.swing.JTable jTable_thongbao_deadline;
    private javax.swing.JTable jTable_thongbao_dlnow;
    private javax.swing.JTextArea jTextArea_ctcv_muctieu;
    private javax.swing.JTextArea jTextArea_noidungTB;
    private javax.swing.JTextArea jTextArea_thongbao_muctieu;
    private javax.swing.JTextField jTextField_ghichu;
    private javax.swing.JTextField jTextField_searchsp;
    private javax.swing.JTextField jTextField_tencongviec;
    private javax.swing.JTextField jTextField_timkiem_tiendo;
    private javax.swing.JTextArea jTextfield_muctieu;
    private javax.swing.JTextField jTextfield_tenduan;
    private javax.swing.JTree jTree_congviec;
    private javax.swing.JTree jTree_congviec_tiendo;
    private javax.swing.JLabel thongbao_ngtiepnhan;
    private javax.swing.JLabel thongbao_phongban;
    private javax.swing.JLabel thongbao_thuoccv;
    // End of variables declaration//GEN-END:variables
}
