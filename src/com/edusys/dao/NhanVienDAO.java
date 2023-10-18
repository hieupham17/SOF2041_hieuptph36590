/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JDBCHelper;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author admin
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    String insert_sql = "INSERT INTO NHANVIEN(MaNV, MatKhau, HoTen, VaiTro) values(?,?,?,?) ";
    String update_sql = "UPDATE NHANVIEN SET MATKHAU = ?, HOTEN = ?, VAITRO = ? WHERE  (MANV = ?)";
    String delete_sql = "DELETE FROM NHANVIEN WHERE  (MANV = ?)";
    String select_all_sql = "SELECT * FROM NHANVIEN";
    String selectByIDSQL = "SELECT * FROM NHANVIEN WHERE MaNV = ?";

    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.update(insert_sql,
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.isVaiTro());
    }

    @Override
    public void update(NhanVien nv) {
        JDBCHelper.update(update_sql, nv.getHoTen(), nv.getMatKhau(), nv.isVaiTro(), nv.getMaNV());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(delete_sql, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> listNV = this.selectBySQL(selectByIDSQL, id);
        if (listNV.isEmpty()) {
            return null;
        }
        return listNV.get(0);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> listNV = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MANV"));
                nv.setHoTen(rs.getString("HOTEN"));
                nv.setMatKhau(rs.getString("MATKHAU"));
                nv.setVaiTro(rs.getBoolean("VAITRO"));
                listNV.add(nv);
            }
            rs.getStatement().getConnection().close();
            return listNV;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
