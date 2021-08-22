package org.jbit.service;

import org.jbit.dto.CompensateQueryDto;
import org.jbit.entity.Compensate;
import org.jbit.utils.PageUtil;

/**
 * 设施Service
 * @author yh
 */
public interface CompensateService {
    /**
     * 分页查询
     * @param compensateQueryDto
     * @return 分页数据
     * @throws Exception
     */
    PageUtil pageList(CompensateQueryDto compensateQueryDto) throws Exception;

    /**
     * 添加设施信息
     * @param compensate
     * @return
     * @throws Exception
     */
    int create(Compensate compensate) throws Exception;

    /**
     * 更新设施信息
     * @param compensate
     * @return
     * @throws Exception
     */
    int save(Compensate compensate) throws Exception;
}
