package org.jbit.dao.impl;

import org.jbit.dao.RoomDao;
import org.jbit.dto.RoomDto;
import org.jbit.dto.RoomQueryDto;
import org.jbit.entity.Facility;
import org.jbit.entity.Room;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李宇杰
 * @description 房间实现类
 * @date 2020/11/23 0023
 */



public class RoomDaoMysqlImpl extends JdbcUtil implements RoomDao {

    @Override
    public int insert(Room obj) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO room(Rid,Rname,Rstate,Rtype,Rprice)" + "VALUES(?,?,0,?,?)";
        //处理结果
        Object[] params = {
                obj.getRoomId(),
                obj.getRoomName(),
                obj.getRoomType(),
                obj.getRoomPrice()
        };
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int update(Room obj) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE room SET Rname=?, Rtype=?, Rprice=?, Rstate=? WHERE Rid=?";
        //处理结果
        Object[] params = {
                obj.getRoomName(),
                obj.getRoomType(),
                obj.getRoomPrice(),
                obj.getRoomState(),
                obj.getRoomId(),
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
        String sql = "DELETE FROM room WHERE Rid=?";
        //处理结果
        Object[] params = {id};
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
        String sql = "DELETE room WHERE Rid=?";
        //处理结果
        Object[] params = {id};
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public Room selectById(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Rid,Rname,Rstate,Rtype,Rprice FROM room WHERE Rid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        Room room=null;
        while (rs.next()){
            room = new Room(
                    rs.getString("Rid"),
                    rs.getString("Rname"),
                    rs.getString("Rtype"),
                    rs.getString("Rprice"),
                    rs.getInt("Rstate")
            );
        }

        //释放资源
        close();
        //返回结果
        return room;
    }

    @Override
    public Room selectById(Integer id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT Rid,Rname,Rstate,Rtype,Rprice FROM room WHERE Rid=?";
        //处理结果
        Object[] params = {id};
        ResultSet rs = query(sql, params);
        Room room=null;
        while (rs.next()){
            room = new Room(
                    rs.getString("Rid"),
                    rs.getString("Rname"),
                    rs.getString("Rtype"),
                    rs.getString("Rprice"),
                    rs.getInt("Rstate")
            );
        }
        //释放资源
        close();
        //返回结果
        return room;
    }

    /*@Override
    public List<Room> selectBySelective(RoomQueryDto roomQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Rid,Rname,Rstate,Rtype,Rprice FROM room WHERE 1=1");
        //根据条件拼接查询参数
        getSelectiveDto(sql, params, roomQueryDto);
        if (roomQueryDto.getCurrentPage() != null && roomQueryDto.getPageSize() != null) {
            sql.append(" LIMIT ?,?");
            //起始记录=（当前页-1）*页面大小
            params.add((roomQueryDto.getCurrentPage() - 1) * roomQueryDto.getPageSize());
            params.add(roomQueryDto.getPageSize());
        }
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Room> rooms = new ArrayList<Room>();
        getResultSet(rs, rooms);
        //释放资源
        close();
        //返回结果
        return rooms;
    }*/

    @Override
    public List<RoomDto> selectBySelective(RoomQueryDto roomQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Rid,Rname,Rstate,Rtype,Rprice FROM room WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, roomQueryDto);
        if (roomQueryDto.getCurrentPage() != null && roomQueryDto.getPageSize() != null) {
            sql.append(" LIMIT ?,?");
            //起始记录=（当前页-1）*页面大小
            params.add((roomQueryDto.getCurrentPage() - 1) * roomQueryDto.getPageSize());
            params.add(roomQueryDto.getPageSize());
        }
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<RoomDto> rooms = new ArrayList<>();
        getResultSetDto(rs, rooms);
        //释放资源
        close();
        //返回结果
        return rooms;
    }

    @Override
    public List<Room> selectBySelective(Room room) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT Rid,Rname,Rstate,Rtype,Rprice FROM room WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, room);
        //处理结果
        ResultSet rs = query(sql.toString(), params.toArray());
        List<Room> rooms = new ArrayList<Room>();
        getResultSet(rs, rooms);
        //释放资源
        close();
        //返回结果
        return rooms;
    }

    @Override
    /**
     *按条件统计数据库中数据条数
     */
    public int countBySelective(Room room) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM room WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, room);
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
    public int countBySelective(RoomQueryDto roomQueryDto) throws Exception {
        //定义参数数组
        List params = new ArrayList();
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        StringBuilder sql = new StringBuilder("SELECT count(1) count FROM room WHERE 1=1");
        //根据条件拼接查询参数
        getSelective(sql, params, roomQueryDto);
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
    public int occupyById(String id) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE room SET Rstate=1 WHERE Rid=?";
        Object[] params = { id };
        //处理结果
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int handleState(Room room) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "UPDATE room SET Rstate=? WHERE Rid=?";
        Object[] params = {room.getRoomState(), room.getRoomId()};
        //处理结果
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public List<Facility> getFacilities(String roomId) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "SELECT a.Fid,Fname,Fprice FROM facilities a, rlinkf b WHERE a.Fid=b.Fid AND b.Rid=?";
        Object[] params = {roomId};
        //处理结果
        ResultSet rs = query(sql, params);
        List<Facility> facilities = new ArrayList<>();
        /**
         * 处理结果
         */
        while (rs.next()) {
            Facility facility = new Facility(
                    rs.getString("Fid"),
                    rs.getString("Fname"),
                    rs.getDouble("Fprice")
            );
            facilities.add(facility);
        }
        //释放资源
        close();
        //返回结果
        return facilities;
    }

    @Override
    public int deleteFacilities(String roomId) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "DELETE FROM rlinkf WHERE Rid=?";
        //处理结果
        Object[] params = {roomId};
        int rows = update(sql, params);
        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public int addFacilities(String roomId, Facility facility) throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        //发送sql
        String sql = "INSERT INTO rlinkf(Rid,Fid) VALUES(?,?)";
        //处理结果
        Object[] params = {
                roomId,
                facility.getFacilityId()
        };
        int rows = update(sql, params);

        //释放资源
        close();
        //返回结果
        return rows;
    }

    @Override
    public List<Facility> editFacilities(List<Facility> facilities) throws Exception {
        return null;
    }


    private void getSelective(StringBuilder sql, List params, Room room) {
        /**
         * 根据条件拼接查询参数
         */
        if (room.getRoomId() != null && !room.getRoomId().equals("")) {
            sql.append(" AND Rid LIKE CONCAT('%',?,'%')");
            params.add(room.getRoomId());
        }
        if (room.getRoomType() != null && !room.getRoomType().equals("")) {
            sql.append(" AND Rtype LIKE CONCAT('%',?,'%')");
            params.add(room.getRoomType());
        }
        if(room.getRoomPrice()!=null && !room.getRoomPrice().equals("")) {
            sql.append(" AND Rprice =?");
            params.add(room.getRoomPrice());
        }
        if(room.getRoomName()!=null && !room.getRoomName().equals("")){
            sql.append(" AND Rname LIKE CONCAT('%',?,'%')");
            params.add(room.getRoomName());
        }
        if(room.getRoomState()!=null) {
            sql.append(" AND Rstate=?");
            params.add(room.getRoomState());
        }
    }

    /*private void getSelectiveDto(StringBuilder sql, List params, RoomQueryDto roomQueryDto) {
        *//**
         * 根据条件拼接查询参数
         *//*
        if (roomQueryDto.getRoomId() != null && !roomQueryDto.getRoomId().equals("")) {
            sql.append(" AND Rid LIKE CONCAT('%',?,'%')");
            params.add(roomQueryDto.getRoomId());
        }
        if (roomQueryDto.getRoomType() != null && !roomQueryDto.getRoomType().equals("")) {
            sql.append(" AND Rtype LIKE CONCAT('%',?,'%')");
            params.add(roomQueryDto.getRoomType());
        }
        if(roomQueryDto.getRoomPrice() != null && !roomQueryDto.getRoomPrice().equals("")) {
            sql.append(" AND Rprice=?");
            params.add(roomQueryDto.getRoomPrice());
        }
        if(roomQueryDto.getRoomName()!=null && !roomQueryDto.getRoomName().equals("")){
            sql.append(" AND Rname LIKE CONCAT('%',?,'%')");
            params.add(roomQueryDto.getRoomName());
        }
        if(roomQueryDto.getRoomState()!=null) {
            sql.append(" AND Rstate=?");
            params.add(roomQueryDto.getRoomState());
        }
    }*/

    private void getResultSet(ResultSet rs, List rooms) throws SQLException {
        /**
         * 处理结果
         */
        while (rs.next()) {
            Room room = new Room(
                    rs.getString("Rid"),
                    rs.getString("Rname"),
                    rs.getString("Rtype"),
                    rs.getString("Rprice"),
                    rs.getInt("Rstate")
            );
            rooms.add(room);
        }
    }

    private void getResultSetDto(ResultSet rs, List rooms) throws SQLException {
        /**
         * 处理结果
         */
        while (rs.next()) {
            RoomDto room = new RoomDto(
                    rs.getString("Rid"),
                    rs.getString("Rname"),
                    rs.getString("Rtype"),
                    rs.getString("Rprice"),
                    rs.getInt("Rstate")
            );
            rooms.add(room);
        }
    }
}
