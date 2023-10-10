/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    String insert_sql = "INSERT INTO HOCVIEN VALUES (?,?,?)";
    String update_sql = " UPDATE HOCVIEN SET Diem =? where mahv = ?";
    String delete_sql = "DELETE FROM HOCVIEN where mahv = ?";
    String select_all_sql = "SELECT * FROM HOCVIEN";
    String selectByIDSQL = "SELECT  * FROM HOCVIEN where mahv = ?";

    @Override
    public void insert(HocVien entity) {
        JDBCHelper.update(insert_sql,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JDBCHelper.update(update_sql,
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.update(delete_sql, id);
    }

    @Override
    public List selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public HocVien selectByID(Integer id) {
        List<HocVien> list = this.selectBySQL(selectByIDSQL, id);
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
       List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt("MaHV"));
                hv.setMaKH(rs.getInt("MaKH"));
                hv.setMaNH(rs.getString("MaNH"));
                hv.setDiem(rs.getDouble("Diem"));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

   public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "select * from HocVien where MaKH = ?";
        return this.selectBySQL(sql, maKH);
    }
}
