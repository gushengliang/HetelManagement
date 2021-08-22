package org.jbit.daoTest;

import org.jbit.dao.RoomDao;
import org.jbit.dao.impl.RoomDaoMysqlImpl;
import org.jbit.dto.RoomQueryDto;
import org.jbit.entity.Facility;
import org.junit.Test;

import java.util.List;

public class RoomDaoMysqlimplTest {
    RoomDao roomDao = new RoomDaoMysqlImpl();

    @Test
    public void getroom() throws Exception {
        RoomQueryDto roomQueryDto=new RoomQueryDto();
        RoomDaoMysqlImpl room=new RoomDaoMysqlImpl();
        int t= room.countBySelective(roomQueryDto);
        System.out.println(t+"");
    }

    @Test
    public void getFacilities() throws Exception {
        String roomId = "001";
        List<Facility> facilities = roomDao.getFacilities(roomId);
        for(Facility facility : facilities) {
            System.out.println(facility.toString());
        }
    }

}
