package org.jbit.daoTest;

import org.jbit.dao.StaffDao;
import org.jbit.dao.impl.StaffDaoMysqlImpl;
import org.jbit.dto.StaffDto;
import org.jbit.dto.StaffQueryDto;
import org.jbit.entity.Position;
import org.jbit.entity.Staff;
import org.junit.Test;

import java.util.List;

public class staffDaoTest {
    StaffDao staffDao = new StaffDaoMysqlImpl();

    @Test
    public void insert() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId("20112102");
        staff.setStaffName("Mary");
        staff.setStaffPassword("123456");
        staff.setStaffPosition("前台");
        if(staffDao.insert(staff) > 0) {
            System.out.println("insert successfully.");
            System.out.println(staff.toString());
        }
        else {
            System.out.println("insert failed...");
        }
    }

    @Test
    public void update() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId("20112102");
        staff.setStaffName("Mary");
        staff.setStaffPassword("123456");
        staff.setStaffPosition("前台");
        if(staffDao.update(staff) > 0) {
            System.out.println("update successfully.");
            System.out.println(staff.toString());
        }
        else {
            System.out.println("update failed...");
        }
    }

    @Test
    public void delete() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId("20112102");
        if(staffDao.delete(staff.getStaffId()) > 0) {
            System.out.println("delete successfully.");
            //System.out.println(staff.toString());
        }
        else {
            System.out.println("delete failed...");
        }
    }

    @Test
    public void selectBySelective() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId("");
        staff.setStaffName("");
        staff.setStaffPassword("");
        staff.setStaffPosition("");
        List<Staff> staffs = staffDao.selectBySelective(staff);
        for(Staff staff1 : staffs) {
            System.out.println(staff1.toString());
        }
    }

    @Test
    public void selectBySelectiveDto() throws Exception {
        StaffQueryDto staffQueryDto = new StaffQueryDto();
//        staffQueryDto.setStaffID("");
//        staffQueryDto.setStaffName("");
//        staffQueryDto.setStaffPassword("");
//        staffQueryDto.setStaffPosition("");
        staffQueryDto.setCurrentPage(1);
        staffQueryDto.setPageSize(3);
        List<StaffDto> staffs = staffDao.selectBySelective(staffQueryDto);
        for(StaffDto staff1 : staffs) {
            System.out.println(staff1.toString());
        }
    }

    @Test
    public void countBySelective() throws Exception {
        Staff staff = new Staff();
        staff.setStaffId("");
        staff.setStaffName("");
        staff.setStaffPassword("");
        staff.setStaffPosition("");
        int count = staffDao.countBySelective(staff);
        System.out.println(count);
    }

    /*@Test
    public void getAllPositions() throws Exception {
        List<Position> positions = staffDao.getAllPositions();
        for(Position position : positions) {
            position.toString();
        }
    }*/
}
