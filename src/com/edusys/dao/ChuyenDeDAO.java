package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    String insert_sql = "INSERT INTO CHUYENDE(MaCD, TENCD, HOCPHI, THOILUONG, HINH, MOTA) values(?,?,?,?,?,?) ";
    String update_sql = "UPDATE CHUYENDE SET TENCD = ?, HOCPHI = ?, THOILUONG = ?, HINH = ?, MOTA = ?  WHERE  (MACD = ?)";
    String delete_sql = "DELETE FROM CHUYENDE WHERE  (MACD = ?)";
    String select_all_sql = "SELECT * FROM CHUYENDE";
    String selectByIDSQL = "SELECT * FROM CHUYENDE WHERE MACD = ?";

    @Override
    public void insert(ChuyenDe cd) {
        JDBCHelper.update(insert_sql,
                cd.getMaCD(),
                cd.getTenChuyenDe(),
                cd.getHocPhi(),
                cd.getThoiLuong(),
                cd.getHinh(),
                cd.getMoTa()
        );
    }

    @Override
    public void update(ChuyenDe cd) {
        JDBCHelper.update(update_sql,
                cd.getTenChuyenDe(),
                cd.getHocPhi(),
                cd.getThoiLuong(),
                cd.getHinh(),
                cd.getMoTa(),
                cd.getMaCD()
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
    public ChuyenDe selectByID(String id) {
        List<ChuyenDe> listNV = this.selectBySQL(selectByIDSQL, id);
        if (listNV.isEmpty()) {
            return null;
        }
        return listNV.get(0);
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
         List<ChuyenDe> listCD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {                
                ChuyenDe cd = new ChuyenDe();
                
                cd.setMaCD(rs.getString("MACD"));
                cd.setTenChuyenDe(rs.getString("TENCD"));
                cd.setHocPhi(rs.getDouble("HOCPHI"));
                cd.setThoiLuong(rs.getInt("THOILUONG"));
                cd.setHinh(rs.getString("HINH"));
                cd.setMoTa(rs.getString("MOTA"));
                listCD.add(cd);
            }
            rs.getStatement().getConnection().close();
            return listCD;
            
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
   

}
