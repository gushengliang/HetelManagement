package org.jbit.dao.impl;

import org.jbit.dao.PositionDao;
import org.jbit.entity.Position;
import org.jbit.utils.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * PositionDao实现类
 * @author yh
 */
public class PositionDaoMysqlImpl extends JdbcUtil implements PositionDao {

    @Override
    public List<Position> getAllPositions() throws Exception {
        //获取连接
        getConnection("db_mysql.properties");
        // 发送sql
        String sql ="SELECT Pid,Pname FROM position";
        ResultSet rs = query(sql);
        //处理结果
        List<Position> positions = new ArrayList<>();
        while (rs.next()) {
            Position position = new Position(
                    rs.getInt("Pid"),
                    rs.getString("Pname")
            );
            positions.add(position);
        }

        //释放资源
        close();
        //返回结果
        return positions;
    }

    @Override
    public int insert(Object obj) throws Exception {
        return 0;
    }

    @Override
    public int update(Object obj) throws Exception {
        return 0;
    }

    @Override
    public int delete(String id) throws Exception {
        return 0;
    }

    @Override
    public int delete(Integer id) throws Exception {
        return 0;
    }

    @Override
    public Object selectById(String id) throws Exception {
        return null;
    }

    @Override
    public Object selectById(Integer id) throws Exception {
        return null;
    }

    @Override
    public List selectBySelective(Object obj) throws Exception {
        return null;
    }
}
