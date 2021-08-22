package org.jbit.service;

import org.jbit.dto.FacilityQueryDto;
import org.jbit.entity.Facility;
import org.jbit.utils.PageUtil;

import java.util.List;

/**
 * 设施Service
 * @author yh
 */
public interface FacilityService {
    /**
     * 分页查询
     * @param facilityQueryDto
     * @return 分页数据
     * @throws Exception
     */
    PageUtil pageList(FacilityQueryDto facilityQueryDto) throws Exception;

    /**
     * 添加设施信息
     * @param facility
     * @return
     * @throws Exception
     */
    int create(Facility facility) throws Exception;

    /**
     * 更新设施信息
     * @param facility
     * @return
     * @throws Exception
     */
    int save(Facility facility) throws Exception;

    List<Facility> selectbyroomid(String roomId) throws Exception;
}
