package org.jbit.dao.impl;

import org.jbit.dao.StaffDao;
import org.jbit.dto.StaffDto;
import org.jbit.dto.StaffQueryDto;
import org.jbit.entity.Staff;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 员工实现类
 *
 * @author yh
 *
 * @version 1.0,2020-11-23
 */
public class StaffDaoMysqlImpl extends JdbcUtil implements StaffDao {

    @Override
    public int insert(Staff obj) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO staff(Sid,Sname,Spassword,Sposition)" + "VALUES(?,?,?,?)";
        //处理结果
        Object[] params = {
                obj.getStaffId(),
                obj.getStaffName(),
                obj.getStaffPassword(),
                obj.getStaffPosition()
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Staff obj) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE staff SET Sname=?, Spassword=?, Sposition=? WHERE Sid=?";
        //处理结果
        Object[] params = {
                obj.getStaffName(),
                obj.getStaffPassword(),
                obj.getStaffPosition(),
                obj.getStaffId()
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
        String sql = "DELETE FROM staff WHERE Sid=?";
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
        String sql = "DELETE staff WHERE Sid=?";
        //处理结果
        Object[] params = { id };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public Staff selectById(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Sid,Sname,Spassword,Sposition,onjob FROM staff WHERE Sid=?";
        //处理结果
        Object[] params = { id };
        ResultSet rs = query(sql, params);
        Staff staff = null;
        if(rs.next()) {
            staff = new Staff(
                    rs.getString("Sid"),
                    rs.getString("Sname"),
                    rs.getString("Spassword"),
                    rs.getString("Sposition"),
                    rs.getInt("onJob")
            );
        }
        //释放资源
        close();
        //返回结果
        return staff;
    }

    @Override
    public Staff selectById(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Sid,Sname,Spassword,Sposition,onjob FROM staff WHERE Sid=?";
        //处理结果
        Object[] params = { id };
        ResultSet rs = query(sql, params);
        Staff staff = null;
        if(rs.next()) {
            staff = new Staff(
                    rs.getString("Sid"),
                    rs.getString("Sname"),
                    rs.getString("Spassword"),
                    rs.getString("Sposition"),
                    rs.getInt("onJob")
            );
        }
        //释放资源
        close();
        //返回结果
        return staff;
    }

    @Override
    public List<StaffDto> selectBySelective(StaffQueryDto  staffQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        //StringBuilder sql = new StringBuilder("SELECT Sid, Sname, Spassword, Sposition, onjob FROM staff WHERE 1=1");
        StringBuilder sql = new StringBuilder("SELECT a.*, b.Pname FROM staff a, position b WHERE a.Sposition=b.Pid");
        //根据条件拼接查询参数
        getSelective(sql, params, staffQueryDto);
        if(staffQueryDto.getCurrentPage() != null && staffQueryDto.getPageSize() != null) {
            sql.append(" LIMIT ?,?");
            //起始记录=（当前页-1）*页面大小
            params.add((staffQueryDto.getCurrentPage() - 1) * staffQueryDto.getPageSize());
            params.add(staffQueryDto.getPageSize());
        }
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<StaffDto> staffs = new ArrayList<>();
        setDtoResult(rs, staffs);
        //释放资源
        close();
        //返回结果
        return staffs;
    }

    @Override
    public List<Staff> selectBySelective(Staff staff) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Sid,Sname,Spassword,Sposition,onjob FROM staff WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, staff);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Staff> staffs = new ArrayList<>();
        setResult(rs, staffs);
        //释放资源
        close();
        //返回结果
        return staffs;
    }

    @Override
    public int countBySelective(Staff staff) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM staff WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, staff);
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
    public int countBySelective(StaffQueryDto staffQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM staff WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, staffQueryDto);
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
    public int dismissById(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE staff SET onjob=0 WHERE Sid=?";
        Object[] params = { id };
        //处理结果
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    private void getSelective(StringBuilder sql, List params, Staff staff) {
        /**
         * 根据条件拼接查询参数
         */
        if(staff.getStaffId()!=null && !staff.getStaffId().equals("")) {
            sql.append(" AND Sid LIKE CONCAT('%',?,'%')");
            params.add(staff.getStaffId());
        }
        if(staff.getStaffName()!=null && !staff.getStaffName().equals("")) {
            sql.append(" AND Sname LIKE CONCAT('%',?,'%')");
            params.add(staff.getStaffName());
        }
        if(staff.getStaffPassword()!=null && !staff.getStaffPassword().equals("")) {
            sql.append(" AND Spassword LIKE CONCAT('%',?,'%')");
            params.add(staff.getStaffPassword());
        }
        if(staff.getStaffPosition()!=null && !staff.getStaffPosition().equals("")) {
            sql.append(" AND Sposition LIKE CONCAT('%',?,'%')");
            params.add(staff.getStaffPosition());
        }
        if (staff.getOnJob() != null) {
            sql.append(" AND onjob=?");
            params.add(staff.getOnJob());
        }
    }

    private void setResult(ResultSet rs, List<Staff> staffs) throws SQLException {
        /**
         * 处理结果
         */
        while(rs.next()){
            Staff staff = new Staff(
                    rs.getString("Sid"),
                    rs.getString("Sname"),
                    rs.getString("Spassword"),
                    rs.getString("Sposition"),
                    rs.getInt("onjob")
            );
            staffs.add(staff);
        }
    }

    private void setDtoResult(ResultSet rs, List<StaffDto> staffs) throws SQLException {
        /**
         * 处理结果
         */
        while(rs.next()){
            StaffDto staffDto = new StaffDto();
            staffDto.setStaffId(rs.getString("Sid"));
            staffDto.setStaffName(rs.getString("Sname"));
            //staffDto.setStaffPassword(rs.getString("Spassword"));
            staffDto.setStaffPosition(rs.getString("Sposition"));
            staffDto.setPositionName(rs.getString("Pname"));
            staffDto.setOnJob(rs.getInt("onjob"));
            staffs.add(staffDto);
        }
    }


}
