package org.jbit.serverText;

import org.jbit.dto.RoomQueryDto;
import org.jbit.service.RoomService;
import org.jbit.service.impl.RoomServiceImpl;
import org.jbit.utils.PageUtil;
import org.junit.Test;

public class Roomserver {
    @Test
    public void getdata(){
        RoomService roomservice=new RoomServiceImpl();
        RoomQueryDto roomQueryDto = new RoomQueryDto();
        PageUtil pageUtil = null;
        try {
            pageUtil = roomservice.pageList(roomQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(pageUtil.getData());
    }
}
