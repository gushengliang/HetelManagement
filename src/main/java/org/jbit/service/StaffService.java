package org.jbit.service;

import org.jbit.dto.StaffQueryDto;
import org.jbit.entity.Position;
import org.jbit.entity.Staff;
import org.jbit.utils.PageUtil;

import java.util.List;

public interface StaffService {

    /**
     * 分页查询
     * @param staffQueryDto
     * @return 分页数据
     * @throws Exception
     */
    PageUtil pageList(StaffQueryDto staffQueryDto) throws Exception;

    /**
     * 按ID查询员工
     * @param id
     * @return
     * @throws Exception
     */
    Staff selectById(String id) throws Exception;

    /**
     * 按Id开除员工
     * @param id
     * @return
     * @throws Exception
     */
    int dismissById(String id) throws Exception;

    /**
     * 获取职务列表
     * @return
     * @throws Exception
     */
    /*List<Position> getAllPositions() throws Exception;*/

    /**
     * 添加员工信息
     * @param staff
     * @return
     * @throws Exception
     */
    int create(Staff staff) throws Exception;

    /**
     * 更新员工信息
     * @param staff
     * @return
     * @throws Exception
     */
    int save(Staff staff) throws Exception;

    /**
     * 检验员工是否存在
     * @param staff
     * @return
     * @throws Exception
     */
    int isExist(Staff staff) throws Exception;
}
