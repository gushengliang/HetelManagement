package org.jbit.dao;

import org.jbit.dto.OrderQueryDto;
import org.jbit.entity.Order;

import java.util.List;

/**
 * 订单接口
 *
 * @author yh
 *
 * @version 1.0,2020-11-23
 */
public interface OrderDao extends BaseDao<Order> {

    int countBySelective(OrderQueryDto orderQueryDto) throws Exception;

    List<Order> ExeOrder(String customerId) throws Exception;

    List<Order> OverOrder(String customerId) throws Exception;
}
