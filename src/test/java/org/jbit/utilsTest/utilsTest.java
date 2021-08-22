package org.jbit.utilsTest;

import org.jbit.entity.Position;
import org.jbit.utils.JdbcUtil;
import org.junit.Test;

import java.sql.ResultSet;

public class utilsTest {
    JdbcUtil jdbcUtil = new JdbcUtil();

    @Test
    public void getConnection() throws Exception {
        jdbcUtil.getConnection("db_mysql.properties");
        System.out.println();
    }

    @Test
    public void query() throws Exception {
        jdbcUtil.getConnection("db_mysql.properties");
        ResultSet rs = jdbcUtil.query("SELECT Pid,Pname FROM position");
        System.out.println(rs);
        while (rs.next()) {
            Position position = new Position(
                    rs.getInt("Pid"),
                    rs.getString("Pname")
            );
            System.out.println(position.toString());
            //positions.add(position);
        }
    }
}
