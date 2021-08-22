package org.jbit.service.impl;

import org.jbit.dao.CompensateDao;
import org.jbit.dao.impl.CompensateDaoMysqlImpl;
import org.jbit.dto.CompensateQueryDto;
import org.jbit.entity.Compensate;
import org.jbit.service.CompensateService;
import org.jbit.utils.PageUtil;

import java.util.List;

/**
 * 设施Service实现类
 * @author yh
 */
public class CompensateServiceImpl implements CompensateService {

    /**
     * 注入Dao
     */
    CompensateDao compensateDao = new CompensateDaoMysqlImpl();

    @Override
    public PageUtil pageList(CompensateQueryDto compensateQueryDto) throws Exception {
        /*
         * 统计总记录数
         * 如果总记录数>0：
         *  （1）查询列表数据
         *  （2）封装分页数据
         */
        PageUtil pageUtil = null;
        //统计总记录数
        int total = compensateDao.countBySelective(compensateQueryDto);
        if(total > 0) {
            List<Compensate> data = compensateDao.selectBySelective(compensateQueryDto);
            pageUtil = new PageUtil<>(
                    compensateQueryDto.getCurrentPage(),
                    compensateQueryDto.getPageSize(),
                    total,
                    data
            );
        }
        return pageUtil;
    }

    @Override
    public int create(Compensate compensate) throws Exception {
        return compensateDao.insert(compensate);
    }

    @Override
    public int save(Compensate compensate) throws Exception {
        return compensateDao.update(compensate);
    }
}
