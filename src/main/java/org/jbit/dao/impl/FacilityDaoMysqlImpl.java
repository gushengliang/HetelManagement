package org.jbit.dao.impl;

import org.jbit.dao.FacilityDao;
import org.jbit.dto.FacilityQueryDto;
import org.jbit.entity.Facility;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * FacilityDao实现类
 * @author yh
 */
public class FacilityDaoMysqlImpl extends JdbcUtil implements FacilityDao {
    @Override
    public int insert(Facility facility) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO facilities(Fid,Fname,Fprice)" + "VALUES(?,?,?)";
        //处理结果
        Object[] params = {
                facility.getFacilityId(),
                facility.getFacilityName(),
                facility.getFacilityPrice(),
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Facility facility) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE facilities SET Fname=?,Fprice=? WHERE Fid=?";
        //处理结果
        Object[] params = {
                facility.getFacilityName(),
                facility.getFacilityPrice(),
                facility.getFacilityId(),
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int delete(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "DELETE FROM facilities WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int delete(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "DELETE FROM facilities WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public Facility selectById(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Fid,Fname,Fprice FROM facilities WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        ResultSet rs = query(sql, params);
        Facility facility = null;
        if(rs.next()) {
            facility = new Facility(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
        }
        //释放资源
        close();
        //返回结果
        return facility;
    }

    @Override
    public Facility selectById(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Fid,Fname,Fprice FROM facilities WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        ResultSet rs = query(sql, params);
        Facility facility = null;
        if(rs.next()) {
            facility = new Facility(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
        }
        //释放资源
        close();
        //返回结果
        return facility;
    }

    @Override
    public List<Facility> selectBySelective(Facility facility) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Fid,Fname,Fprice FROM facilities WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, facility);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Facility> facilities = new ArrayList<>();
        setResult(rs, facilities);
        //释放资源
        close();
        //返回结果
        return facilities;
    }

    @Override
    public List<Facility> selectBySelective(FacilityQueryDto facilityQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Fid,Fname,Fprice FROM facilities WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, facilityQueryDto);
        //分页数据
        if(facilityQueryDto.getCurrentPage() != null && facilityQueryDto.getPageSize() != null) {
            sql.append(" LIMIT ?,?");
            params.add((facilityQueryDto.getCurrentPage() - 1) * facilityQueryDto.getPageSize());
            params.add(facilityQueryDto.getPageSize());
        }
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Facility> facilities = new ArrayList<>();
        setResult(rs, facilities);
        //释放资源
        close();
        //返回结果
        return facilities;
    }

    @Override
    public int countBySelective(Facility facility) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM facilities WHERE 1=1");
        getSelective(sql, params, facility);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        //释放资源
        close();
        //返回结果
        return count;
    }

    @Override
    public int countBySelective(FacilityQueryDto facilityQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM facilities WHERE 1=1");
        getSelective(sql, params, facilityQueryDto);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        //释放资源
        close();
        //返回结果
        return count;
    }

    @Override
    public List<Facility> selectByRoomId(String roomId) throws Exception{
        List<Facility> facilities = new ArrayList<>();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = new String("SELECT * FROM facilities,rlinkf WHERE Rid=? and facilities.Fid=rlinkf.Fid");
        //处理结果
        Object[] params = { roomId };
        ResultSet rs = query(sql, params);
        Facility facility = null;
        while(rs.next()) {
            facility = new Facility(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
            facilities.add(facility);
        }
        return facilities;
    }

    private void getSelective(StringBuilder sql, List params, Facility facility) {
        /**
         * 根据条件拼接查询参数
         */
        if(facility.getFacilityId()!=null && !facility.getFacilityId().equals("")) {
            sql.append(" AND Fid LIKE CONCAT('%',?,'%')");
            params.add(facility.getFacilityId());
        }
        if(facility.getFacilityName()!=null && !facility.getFacilityName().equals("")) {
            sql.append(" AND Fname LIKE CONCAT('%',?,'%')");
            params.add(facility.getFacilityName());
        }
        if(facility.getFacilityPrice() != null) {
            sql.append(" AND Fprice LIKE CONCAT('%',?,'%')");
            params.add(facility.getFacilityPrice());
        }
    }

    private void setResult(ResultSet rs, List<Facility> facilities) throws SQLException {
        while (rs.next()) {
            Facility obj = new Facility(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
            facilities.add(obj);
        }
    }
}
