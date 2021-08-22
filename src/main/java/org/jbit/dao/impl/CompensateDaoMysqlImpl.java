package org.jbit.dao.impl;

import org.jbit.dao.CompensateDao;
import org.jbit.dto.CompensateQueryDto;
import org.jbit.entity.Compensate;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * FacilityDao实现类
 * @author yh
 */
public class CompensateDaoMysqlImpl extends JdbcUtil implements CompensateDao {
    @Override
    public int insert(Compensate compensate) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO facilities(Fid,Fname,Fprice)" + "VALUES(?,?,?)";
        //处理结果
        Object[] params = {
                compensate.getCompensateId(),
                compensate.getCompensateName(),
                compensate.getCompensatePrice(),
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Compensate compensate) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE compensates SET Fname=?,Fprice=? WHERE Fid=?";
        //处理结果
        Object[] params = {
                compensate.getCompensateName(),
                compensate.getCompensatePrice(),
                compensate.getCompensateId(),
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
        String sql = "DELETE FROM compensates WHERE Fid=?";
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
        String sql = "DELETE FROM compensates WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public Compensate selectById(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Fid,Fname,Fprice FROM facilities WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        ResultSet rs = query(sql, params);
        Compensate compensate = null;
        if(rs.next()) {
            compensate = new Compensate(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
        }
        //释放资源
        close();
        //返回结果
        return compensate;
    }

    @Override
    public Compensate selectById(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Fid,Fname,Fprice FROM facilities WHERE Fid=?";
        //处理结果
        Object[] params = { id };
        ResultSet rs = query(sql, params);
        Compensate compensate = null;
        if(rs.next()) {
            compensate = new Compensate(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
        }
        //释放资源
        close();
        //返回结果
        return compensate;
    }

    @Override
    public List<Compensate> selectBySelective(Compensate compensate) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Fid,Fname,Fprice FROM facilities WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, compensate);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Compensate> compensates = new ArrayList<>();
        setResult(rs, compensates);
        //释放资源
        close();
        //返回结果
        return compensates;
    }

    @Override
    public List<Compensate> selectBySelective(CompensateQueryDto compensateQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Fid,Fname,Fprice FROM facilities WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, compensateQueryDto);
        //分页数据
        if(compensateQueryDto.getCurrentPage() != null && compensateQueryDto.getPageSize() != null) {
            sql.append(" LIMIT ?,?");
            params.add((compensateQueryDto.getCurrentPage() - 1) * compensateQueryDto.getPageSize());
            params.add(compensateQueryDto.getPageSize());
        }
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Compensate> compensates = new ArrayList<>();
        setResult(rs, compensates);
        //释放资源
        close();
        //返回结果
        return compensates;
    }

    @Override
    public int countBySelective(Compensate compensate) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM compensates WHERE 1=1");
        getSelective(sql, params, compensate);
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
    public int countBySelective(CompensateQueryDto compensateQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM facilities WHERE 1=1");
        getSelective(sql, params, compensateQueryDto);
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

    private void getSelective(StringBuilder sql, List params, Compensate compensate) {
        /**
         * 根据条件拼接查询参数
         */
        if(compensate.getCompensateId()!=null && !compensate.getCompensateId().equals("")) {
            sql.append(" AND Fid LIKE CONCAT('%',?,'%')");
            params.add(compensate.getCompensateId());
        }
        if(compensate.getCompensateName()!=null && !compensate.getCompensateName().equals("")) {
            sql.append(" AND Fname LIKE CONCAT('%',?,'%')");
            params.add(compensate.getCompensateName());
        }
        if(compensate.getCompensatePrice() != null) {
            sql.append(" AND Fprice LIKE CONCAT('%',?,'%')");
            params.add(compensate.getCompensatePrice());
        }
    }

    private void setResult(ResultSet rs, List<Compensate> compensates) throws SQLException {
        while (rs.next()) {
            Compensate obj = new Compensate(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
            compensates.add(obj);
        }
    }
}
