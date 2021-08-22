package org.jbit.service;

import org.jbit.dto.OrderQueryDto;
import org.jbit.entity.Order;
import org.jbit.utils.PageUtil;

import java.util.List;

public interface OrderService {
    /**
     * 分页查询
     * @param orderQueryDto
     * @return 分页数据
     * @throws Exception
     */
    PageUtil pageList(OrderQueryDto orderQueryDto) throws Exception;


    /**
     * 更新订单信息
     * @param order
     * @return
     * @throws Exception
     */
    int save(Order order) throws Exception;

    /**
     * 按id查询订单
     * @param id
     * @return
     * @throws Exception
     */
    Order selectById(String id) throws Exception;

    /**
     * 创建订单
     * @param order
     * @return
     */
    int create(Order order) throws Exception;


    /**
     * 查询正在进行的订单
     * @param customerId
     * @return
     * @throws Exception
     */

    List<Order> ExeOrder(String customerId) throws Exception;

    /**
     * 查询已经结束的订单
     * @param customerId
     * @return
     * @throws Exception
     */
    List<Order> OverOrder(String customerId) throws Exception;
}
