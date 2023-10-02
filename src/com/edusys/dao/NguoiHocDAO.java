/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    String insert_sql = "INSERT INTO NGUOIHOC(MANH, HOTEN, NGAYSINH, GIOITINH, DIENTHOAI, EMAIL, GHICHU, MANV, NGAYDK)VALUES (?,?,?,?,?,?,?,?,?)";
    String update_sql = "UPDATE NGUOIHOC SET HOTEN = ?, NGAYSINH = ?, GIOITINH = ?, DIENTHOAI = ?, EMAIL = ?, GHICHU = ?, MANV = ?, NGAYDK = ? WHERE  (MANH = ?)";
    String delete_sql = "DELETE FROM NGUOIHOC WHERE  (MANH = ?)";
    String select_all_sql = "SELECT * FROM NGUOIHOC";
    String selectByIDSQL = "SELECT * FROM NGUOIHOC WHERE MANH = ?";

    @Override
    public void insert(NguoiHoc nh) {
        JDBCHelper.update(insert_sql,
                nh.getMaNH(),
                nh.getHoTen(),
                nh.getNgaySinh(),
                nh.isGioiTinh(),
                nh.getDienThoai(),
                nh.getEmail(),
                nh.getGhiChu(),
                nh.getMaNV(),
                nh.getNgayDK()
        );
    }

    @Override
    public void update(NguoiHoc nh) {
        JDBCHelper.update(update_sql,
                nh.getHoTen(),
                nh.getNgaySinh(),
                nh.isGioiTinh(),
                nh.getDienThoai(),
                nh.getEmail(),
                nh.getGhiChu(),
                nh.getMaNV(),
                nh.getNgayDK(),
                nh.getMaNH()
        );
    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(delete_sql, id);
    }

    @Override
    public List selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public NguoiHoc selectByID(String id) {
        List<NguoiHoc> listNH = this.selectBySQL(selectByIDSQL, id);
        if (listNH.isEmpty()) {
            return null;

        }
        return listNH.get(0);

    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> listNH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();

                nh.setMaNH(rs.getString("MANH"));
                nh.setHoTen(rs.getString("HOTEN"));
                nh.setNgaySinh(rs.getDate("NGAYSINH"));
                nh.setGioiTinh(rs.getBoolean("GIOITINH"));
                nh.setDienThoai(rs.getString("DIENTHOAI"));
                nh.setEmail(rs.getString("EMAIL"));
                nh.setGhiChu(rs.getString("GHICHU"));
                nh.setMaNV(rs.getString("MANV"));
                nh.setNgayDK(rs.getDate("NGAYDK"));
                listNH.add(nh);

            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return listNH;
    }

    public List<NguoiHoc> selectByKeyWord(String keyWord) { 
        String sql = "SELECT * FROM NGUOIHOC WHERE  HOTEN LIKE ?";
        return this.selectBySQL(sql, "%" + keyWord + "%");

    }
}
