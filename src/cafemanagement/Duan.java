package cafemanagement;

import java.awt.Container;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author PC
 */
public class Duan {
    public void insertUpdateDeleteProject(char operation, Integer id, String Tenduan , String ngaybatdau, String ngayketthuc, String muctieu, String nguoinhan, String ghichu )
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        if(operation=='i'){
            try {
                ps =con.prepareStatement("INSERT INTO `duan`( `Tenduan`, `ngaybatdau`, `ngayketthuc`, `muctieu`, `nguoinhanduan`,`trangthai`, `ghichu`) VALUES (?,?,?,?,?,?,?)");
                ps.setString(1, Tenduan);
                ps.setString(2, ngaybatdau);
                ps.setString(3, ngayketthuc);
                ps.setString(4, muctieu);
                ps.setString(5, nguoinhan);
                ps.setString(6, "");
                ps.setString(7, ghichu);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        if(operation=='u') //u for update
        {
            try {
                ps =con.prepareStatement("UPDATE `duan` SET `Tenduan` = ?, `ngaybatdau` = ?, `ngayketthuc` = ?,`muctieu` = ?,`nguoinhanduan` = ?,`ghichu` = ? WHERE `id` = ?");
                ps.setString(1, Tenduan);
                ps.setString(2, ngaybatdau);
                ps.setString(3, ngayketthuc);
                ps.setString(4, muctieu);
                ps.setString(5, nguoinhan);
                ps.setString(6, ghichu);
                ps.setInt(7, id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(operation=='d') //d for delete
        {
            try {
                ps =con.prepareStatement("DELETE FROM `duan` WHERE `id` = ?");            
                ps.setInt(1, id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Xoá thành công");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }           
    }
    public void insertUpdateDeleteCV(char operation, Integer id, String Tencongviec ,String loaicongviec, String thuoccongviec, Integer idduan, String phongban, String ngtiepnhan, String ngaybatdau, String ngayketthuc, String muctieu )
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        if(operation=='i'){
            try {
                ps =con.prepareStatement("INSERT INTO `congviec`( `Tencongviec`,`Loaicongviec`,`Thuoccongviec`,`IDduan`,`Phongban`,`Nguoitiepnhan`, `Ngaybatdau`, `Ngayketthuc`, `Muctieu`,`Trangthai`,`Daxacnhan`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, Tencongviec);
                ps.setString(2, loaicongviec);
                ps.setString(3, thuoccongviec);
                ps.setInt(4, idduan);
                ps.setString(5, phongban);
                ps.setString(6, ngtiepnhan);
                ps.setString(7, ngaybatdau);
                ps.setString(8, ngayketthuc);
                ps.setString(9, muctieu);
                ps.setString(10, "0");
                ps.setString(11, "0");
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        if(operation=='u') //u for update
        {
            try {
                ps =con.prepareStatement("UPDATE `congviec` SET `Tencongviec` = ?,`Loaicongviec`=?,`Thuoccongviec`=?,`IDduan`=?,`Phongban`=?,`Nguoitiepnhan`=?, `Ngaybatdau` = ?, `Ngayketthuc` = ?,`Muctieu` = ? WHERE `id` = ?");
                ps.setString(1, Tencongviec);
                ps.setString(2, loaicongviec);
                ps.setString(3, thuoccongviec);
                ps.setInt(4, idduan);
                ps.setString(5, phongban);
                ps.setString(6, ngtiepnhan);
                ps.setString(7, ngaybatdau);
                ps.setString(8, ngayketthuc);
                ps.setString(9, muctieu);
                ps.setInt(10, id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(operation=='d') //d for delete
        {
            try {
                ps =con.prepareStatement("DELETE FROM `congviec` WHERE `id` = ?");            
                ps.setInt(1, id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Xoá thành công");
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }

   
    public void fillDuanJtable(JTable table, String valueToSearch)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `duan` WHERE CONCAT(`Tenduan`,`ngaybatdau`,`ngayketthuc`,`muctieu`,`nguoinhanduan`,`trangthai`,`ghichu`)LIKE ?");
            ps.setString(1, "%"+valueToSearch+"%");
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next()){
                row = new Object[8];
                row[0] =rs.getInt(1);
                row[1] =rs.getString(2);
                row[2] =rs.getString(3);
                row[3] =rs.getString(4);
                row[4] =rs.getString(5);
                row[5] =rs.getString(6);
                row[6] =rs.getString(7);
                row[7] =rs.getString(8);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void fillCongviec_tiendoJtable(JTable table, String valueToSearch,Integer idduan,String loaicongviec,String phongban,String nguoitiepnhan)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            String sql = "SELECT * FROM `congviec` WHERE CONCAT(`Tencongviec`,`Loaicongviec`,`Phongban`,`Nguoitiepnhan`,`IDduan`)LIKE ?";
            if(idduan!=null){
                sql=sql+" AND `IDduan`=?";
                if(loaicongviec.length()>0){
                   sql=sql+" AND `Loaicongviec`=?"; 
                   if(phongban.length()>0){
                        sql=sql+" AND `Phongban`=?";
                        if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, loaicongviec);
                            ps.setString(4, phongban);
                            ps.setString(5, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, loaicongviec);
                            ps.setString(4, phongban);
                        }
                     }else{
                       if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, loaicongviec);
                            ps.setString(4, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, loaicongviec);
                        }
                   }
                }else{
                    if(phongban.length()>0){
                        sql=sql+" AND `Phongban`=?";
                        if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, phongban);
                            ps.setString(4, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, phongban);
                        }
                     }else{
                       if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                            ps.setString(3, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setInt(2, idduan);
                        }
                   }
                }
            }else{
                if(loaicongviec.length()>0){
                   sql=sql+" AND `Loaicongviec`=?"; 
                   if(phongban.length()>0){
                        sql=sql+" AND `Phongban`=?";
                        if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, loaicongviec);
                            ps.setString(3, phongban);
                            ps.setString(4, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, loaicongviec);
                            ps.setString(3, phongban);
                        }
                     }else{
                       if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, loaicongviec);
                            ps.setString(3, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, loaicongviec);
                        }
                   }
                }else{
                    if(phongban.length()>0){
                        sql=sql+" AND `Phongban`=?";
                        if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, phongban);
                            ps.setString(3, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, phongban);
                        }
                     }else{
                       if(nguoitiepnhan.length()>0){
                            sql=sql+" AND `Nguoitiepnhan`=?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                            ps.setString(2, nguoitiepnhan);
                        }else{
                            ps = con.prepareStatement(sql);
                            ps.setString(1, "%"+valueToSearch+"%");
                        }
                   }
                }
            }
            System.out.println(sql);
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next()){
                row = new Object[11];
                row[0] =rs.getInt(1);
                row[1] =rs.getString(2);
                row[2] =rs.getString(3);
                row[3] =rs.getString(4);
                row[4] =rs.getInt(5);
                row[5] =rs.getString(6);
                row[6] =rs.getString(7);
                row[7] =rs.getString(8);
                row[8] =rs.getString(9);
                row[9] =rs.getString(10);
                row[10] =rs.getString(11);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void fillCongviec_thongbao(JTable table) 
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `congviec` WHERE NOT`Trangthai`='1'");
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            LocalDate now = java.time.LocalDate.now(); 
            String datenow=now.toString();
            while(rs.next()){
                String ngayketthuc = rs.getString("Ngayketthuc");
                if(SSdate(datenow, ngayketthuc)==true){
                    
                    row = new Object[11];
                    row[0] =rs.getInt(1);
                    row[1] =rs.getString(2);
                    row[2] =rs.getString(3);
                    row[3] =rs.getString(4);
                    row[4] =rs.getInt(5);
                    row[5] =rs.getString(6);
                    row[6] =rs.getString(7);
                    row[7] =rs.getString(8);
                    row[8] =rs.getString(9);
                    row[9] =rs.getString(10);
                    row[10] =rs.getString(11);
                    model.addRow(row);
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static boolean SSdate(String date1,String date2) {
        String year1 = date1.substring(0, 4);
        String month1 = date1.substring(5, 7);
        String day1 = date1.substring(8, 10);
        String year2 = date2.substring(0, 4);
        String month2 = date2.substring(5, 7);
        String day2 = date2.substring(8, 10);
        int year11=Integer.parseInt(year1);
        int month11=Integer.parseInt(month1);
        int day11=Integer.parseInt(day1);
        int year22=Integer.parseInt(year2);
        int month22=Integer.parseInt(month2);
        int day22=Integer.parseInt(day2);
        boolean result=false;
        if(year11>year22){ 
            result=true;
        }else{
            if(month11>month22){
                result=true;
            }else
                if((day11>day22)&&(month11==month22)){
                    result=true;
                }
        }
        return result;
    }
    public void fillCongviecdeadline_now_thongbao(JTable table) 
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM `congviec` WHERE NOT`Trangthai`='1'");
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            LocalDate now = java.time.LocalDate.now(); 
            String datenow=now.toString();
            while(rs.next()){
                String ngayketthuc = rs.getString("Ngayketthuc");
                if(SSdatenow(datenow, ngayketthuc)==true){
                    
                    row = new Object[11];
                    row[0] =rs.getInt(1);
                    row[1] =rs.getString(2);
                    row[2] =rs.getString(3);
                    row[3] =rs.getString(4);
                    row[4] =rs.getInt(5);
                    row[5] =rs.getString(6);
                    row[6] =rs.getString(7);
                    row[7] =rs.getString(8);
                    row[8] =rs.getString(9);
                    row[9] =rs.getString(10);
                    row[10] =rs.getString(11);
                    model.addRow(row);
                } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static boolean SSdatenow(String date1,String date2) {
        String year1 = date1.substring(0, 4);
        String month1 = date1.substring(5, 7);
        String day1 = date1.substring(8, 10);
        String year2 = date2.substring(0, 4);
        String month2 = date2.substring(5, 7);
        String day2 = date2.substring(8, 10);
        int year11=Integer.parseInt(year1);
        int month11=Integer.parseInt(month1);
        int day11=Integer.parseInt(day1);
        int year22=Integer.parseInt(year2);
        int month22=Integer.parseInt(month2);
        int day22=Integer.parseInt(day2);
        boolean result=false;
        if(year11==year22){ 
                if((day11==day22)&&(month11==month22)){
                    result=true;
                }
        }
        return result;
    }
    public void fillDuan_CongviecJtable(JTable table, String valueToSearch)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT `id`,`Tenduan`,`ngaybatdau`,`ngayketthuc` FROM `duan` WHERE CONCAT(`Tenduan`)LIKE ? AND NOT `trangthai`='hoanthanh'");
            ps.setString(1, "%"+valueToSearch+"%");
            ResultSet rs =ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while(rs.next()){
                row = new Object[4];
                row[0] =rs.getInt(1);
                row[1] =rs.getString(2);
                row[2] =rs.getString(3);
                row[3] =rs.getString(4);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getTenDuanbyid(int id, JTextField t)
    {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT `Tenduan` FROM `duan` WHERE `id`=?");
            ps.setInt(1, id);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
            String ten = rs.getString("Tenduan");
            t.setText(ten); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void TableNamesTest( int idduan, JTree tree) { 
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        ArrayList<String> List1 = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT `Tencongviec` FROM `congviec` WHERE `IDduan`=? AND `Loaicongviec`='congviecchinh'");
            ps.setInt(1, idduan);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                String tencongviecchinh = rs.getString("Tencongviec");
                List1.add(tencongviecchinh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] employee = List1.toArray(new String[List1.size()]); 
        String [][] danhSachCVP = new String[11][11];
        int j=0;
        for (int i=0; i<List1.size();i++){
            try {
            ps = con.prepareStatement("SELECT `Tencongviec` FROM `congviec` WHERE `Loaicongviec`='congviecphu' AND `Thuoccongviec`=?");
            ps.setString(1, List1.get(i));
            ResultSet rs =ps.executeQuery();

            ArrayList<String> listCongViecPhu = new ArrayList<>();
                
            while(rs.next()){
                String tencongviecphu = rs.getString(1);
                listCongViecPhu.add(tencongviecphu);
               //mang gom quang vs do
            }
            for ( j=0; j<listCongViecPhu.size();j++){
                danhSachCVP[j][i] = listCongViecPhu.get(j);
            }
            danhSachCVP[10][i]=String.valueOf(j);
            

        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        String tenduan="";
        try {
            ps = con.prepareStatement("SELECT `Tenduan` FROM `duan` WHERE `id`=?");
            ps.setInt(1, idduan);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
            tenduan = rs.getString("Tenduan");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Duan.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(tenduan);
        for(int i = 0; i < List1.size(); i++){
            
            String name_c = employee[i];
                        System.out.println(name_c);
            DefaultMutableTreeNode row = new DefaultMutableTreeNode(name_c);
            j=0;
            int k = Integer.parseInt(danhSachCVP[10][i]);
           // columns 
            String name_p = danhSachCVP[j][i];
            System.out.println(name_p);
            while(j<k)
            {
                String name_p2 = danhSachCVP[j][i];
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(name_p2);
                // add data to the row
                row.add(node);
                j++;
            }         
            // add the row to the root
            root.add(row);
        }
        DefaultTreeModel model = new DefaultTreeModel(root);
        tree.setModel(model);  
    }
    
    private Container getContentPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
