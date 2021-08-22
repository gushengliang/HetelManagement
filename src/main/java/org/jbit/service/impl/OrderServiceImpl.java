package org.jbit.service.impl;

import org.jbit.dao.OrderDao;
import org.jbit.dao.impl.OrderDaoMysqlImpl;
import org.jbit.dto.OrderQueryDto;

import org.jbit.entity.Order;
import org.jbit.service.OrderService;
import org.jbit.utils.PageUtil;

import java.util.List;

/**
 * @author jzt
 * @description
 * @date 2020/12/1
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoMysqlImpl();


    /**
     * 分页查询
     * @param orderQueryDto
     * @return
     * @throws Exception
     */
    @Override
    public PageUtil pageList(OrderQueryDto orderQueryDto) throws Exception {
        /*
         * 统计总记录数
         * 如果总记录数>0：
         *  （1）查询列表数据
         *  （2）封装分页数据
         */
        PageUtil pageUtil = null;
        //统计总记录数
        int total = orderDao.countBySelective(orderQueryDto);

        if(total > 0){
            List<Order> data = orderDao.selectBySelective(orderQueryDto);
            pageUtil = new PageUtil<>(
                    orderQueryDto.getCurrentPage(),
                    orderQueryDto.getPageSize(),
                    total,
                    data
            );
        }
        return pageUtil;
    }
    @Override
    public List<Order> ExeOrder(String CustomerId)throws Exception{
        return orderDao.ExeOrder(CustomerId);
    }

    @Override
    public List<Order> OverOrder(String customerId) throws Exception {
        return orderDao.OverOrder(customerId);
    }


    @Override
    public int save(Order order) throws Exception {
        return orderDao.update(order);
    }

    @Override
    public Order selectById(String id) throws Exception {

        return orderDao.selectById(id);
    }

    @Override
    public int create(Order order) throws Exception {
        return orderDao.insert(order);
    }
}
