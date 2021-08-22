package org.jbit.service.impl;

import org.jbit.dao.FacilityDao;
import org.jbit.dao.impl.FacilityDaoMysqlImpl;
import org.jbit.dto.FacilityQueryDto;
import org.jbit.entity.Facility;
import org.jbit.service.FacilityService;
import org.jbit.utils.PageUtil;

import java.util.List;

/**
 * 设施Service实现类
 * @author yh
 */
public class FacilityServiceImpl implements FacilityService {

    /**
     * 注入Dao
     */
    FacilityDao facilityDao = new FacilityDaoMysqlImpl();

    @Override
    public PageUtil pageList(FacilityQueryDto facilityQueryDto) throws Exception {
        /*
         * 统计总记录数
         * 如果总记录数>0：
         *  （1）查询列表数据
         *  （2）封装分页数据
         */
        PageUtil pageUtil = null;
        //统计总记录数
        int total = facilityDao.countBySelective(facilityQueryDto);
        if(total > 0) {
            List<Facility> data = facilityDao.selectBySelective(facilityQueryDto);
            pageUtil = new PageUtil<>(
                    facilityQueryDto.getCurrentPage(),
                    facilityQueryDto.getPageSize(),
                    total,
                    data
            );
        }
        return pageUtil;
    }

    @Override
    public int create(Facility facility) throws Exception {
        return facilityDao.insert(facility);
    }

    @Override
    public int save(Facility facility) throws Exception {
        return facilityDao.update(facility);
    }

    @Override
    public List<Facility> selectbyroomid(String roomId) throws Exception {
        return facilityDao.selectByRoomId(roomId);
    }
}
