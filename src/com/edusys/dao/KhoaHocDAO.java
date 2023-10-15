package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    String insert_sql = "INSERT INTO KhoaHoc ( MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?,?,?,?,?,?,?)";
    String update_sql = " UPDATE KhoaHoc SET MaCD =?, HocPhi =?, ThoiLuong =?, NgayKG =?, GhiChu =?, MaNV =?, NgayTao =? where MaKH = ?";
    String delete_sql = "DELETE FROM KhoaHoc where MaKH = ?";
    String select_all_sql = "select * from KhoaHoc";
    String selectByIDSQL = "select * from KhoaHoc where MaKH = ?";

    @Override
    public void insert(KhoaHoc kh) {
        JDBCHelper.update(insert_sql,
                
                kh.getMaCD(),
                kh.getHocPhi(),
                kh.getThoiLuong(),
                kh.getNgayKG(),
                kh.getGhiChu(),
                kh.getMaNV(),
                kh.getNgayTao()
        );
    }

    @Override
    public void update(KhoaHoc kh) {
        JDBCHelper.update(update_sql,
                kh.getMaCD(),
                kh.getHocPhi(),
                kh.getThoiLuong(),
                kh.getNgayKG(),
                kh.getGhiChu(),
                kh.getMaNV(),
                kh.getNgayTao(),
                kh.getMaKH()
        );
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.update(delete_sql, id);
    }

    @Override
    public List selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    public KhoaHoc selectByID(Integer id) {
        List<KhoaHoc> listKH = this.selectBySQL(selectByIDSQL, id);
        if (listKH.isEmpty()) {
            return null;
        }
        return listKH.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
         List<KhoaHoc> listKH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                KhoaHoc kh = new KhoaHoc();
                
                kh.setMaKH(rs.getInt("MAKH"));
                kh.setMaCD(rs.getString("MACD"));
                kh.setHocPhi(rs.getDouble("HOCPHI"));
                kh.setThoiLuong(rs.getInt("THOILUONG"));
                kh.setNgayKG(rs.getDate("NGAYKG"));
                kh.setGhiChu(rs.getString("GHICHU"));
                kh.setMaNV(rs.getString("MANV"));
                kh.setNgayTao(rs.getDate("NGAYTAO"));
                listKH.add(kh);
            }
            rs.getStatement().getConnection().close();
            return listKH;
            
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<KhoaHoc> selectByChuyenDe(String maCD) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
        return this.selectBySQL(sql, maCD);
    }

 
      public List<Integer> selectYears() {
        String sql = """
                    SELECT DISTINCT YEAR(NgayKG) AS Year
                    FROM KhoaHoc 
                    ORDER BY Year DESC
                     """;

        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

 

}
