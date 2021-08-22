package org.jbit.dao;

import org.jbit.dto.StaffDto;
import org.jbit.dto.StaffQueryDto;
import org.jbit.entity.Position;
import org.jbit.entity.Staff;

import java.util.List;


/**
 * 员工接口
 *
 * @author yh
 *
 * @version 1.0,2020-11-23
 */
public interface StaffDao extends BaseDao<Staff> {
    /**
     * 分页查询
     * @param staffQueryDto
     * @return Staff[]
     * @throws Exception
     */
    List<StaffDto> selectBySelective(StaffQueryDto staffQueryDto) throws Exception;
    /**
     * 查询符合条件的数据条数
     * @param staff
     * @return Staff[]
     * @throws Exception
     */
    int countBySelective(Staff staff) throws Exception;

    /**
     * 查询符合条件的数据条数
     * @param staffQueryDto
     * @return Staff[]
     * @throws Exception
     */
    int countBySelective(StaffQueryDto staffQueryDto) throws Exception;

    /**
     * 按Id开除员工
     * @param id
     * @return
     * @throws Exception
     */
    int dismissById(String id) throws Exception;
}
