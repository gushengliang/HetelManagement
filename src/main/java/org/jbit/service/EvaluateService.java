package org.jbit.service;

import org.jbit.entity.Evaluate;
import org.jbit.entity.Order;

import java.util.List;

public interface EvaluateService {

    /**
     * 更新评价表信息
     * @param evaluate
     * @return
     * @throws Exception
     */
    int save(Evaluate evaluate) throws Exception;

    /**
     * 按id查询订单
     * @param id
     * @return
     * @throws Exception
     */
    Order selectById(String id) throws Exception;

    /**
     * 创建订单
     * @param evaluate
     * @return
     */
    int create(Evaluate evaluate) throws Exception;
    /**
     * 查询订单是否存在
     * @param id
     * @return
     * @throws Exception
     */
    boolean countById(String id) throws Exception;
    /**
     * 根据房间号查询评论
     * @param roomId
     * @return
     * @throws Exception
     */
    List<Evaluate> selectByRoomId(String roomId) throws Exception;

}
