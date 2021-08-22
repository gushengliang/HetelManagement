package org.jbit.service.impl;

import org.jbit.dao.StaffDao;
import org.jbit.dao.impl.StaffDaoMysqlImpl;
import org.jbit.dto.StaffDto;
import org.jbit.dto.StaffQueryDto;
import org.jbit.entity.Position;
import org.jbit.entity.Staff;
import org.jbit.service.StaffService;
import org.jbit.utils.PageUtil;

import java.util.List;

/**
 * 员工业务层
 * @author yh
 * @version 1.0,2020-11-23
 */
public class StaffServiceImpl implements StaffService {

    /**
     * 注入Dao
     */
    private final StaffDao staffDao = new StaffDaoMysqlImpl();

    @Override
    public PageUtil pageList(StaffQueryDto staffQueryDto) throws Exception {
        /*
         * 统计总记录数
         * 如果总记录数>0：
         *  （1）查询列表数据
         *  （2）封装分页数据
         */
        PageUtil pageUtil = null;
        //统计总记录数
        int total = staffDao.countBySelective(staffQueryDto);
        if(total > 0){
            List<StaffDto> data = staffDao.selectBySelective(staffQueryDto);
            pageUtil= new PageUtil<>(
                    staffQueryDto.getCurrentPage(),
                    staffQueryDto.getPageSize(),
                    total,
                    data
            );
        }
        else {
            List<StaffDto> data = null;
            pageUtil= new PageUtil<>(
                    staffQueryDto.getCurrentPage(),
                    staffQueryDto.getPageSize(),
                    total,
                    data
            );
        }
        return pageUtil;
    }

    @Override
    public Staff selectById(String id) throws Exception {
        return staffDao.selectById(id);
    }

    @Override
    public int dismissById(String id) throws Exception {
        return staffDao.dismissById(id);
    }

    /*@Override
    public List<Position> getAllPositions() throws Exception {
        return staffDao.getAllPositions();
    }*/

    @Override
    public int create(Staff staff) throws Exception {
        return staffDao.insert(staff);
    }

    @Override
    public int save(Staff staff) throws Exception {
        return staffDao.update(staff);
    }

    @Override
    public int isExist(Staff staff) throws Exception {
        return staffDao.countBySelective(staff);
    }


}
