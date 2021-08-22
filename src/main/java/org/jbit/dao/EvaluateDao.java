package org.jbit.dao;

import org.jbit.entity.Evaluate;

import java.util.List;

/**
 * 评价表接口
 *
 * @author jzt
 *
 * @version 1.0,2020-12-4
 */
public interface EvaluateDao extends BaseDao<Evaluate>{

    /**
     * 获取评论列表
     * @return
     * @throws Exception
     */
    List<Evaluate> getAllEvaluate() throws Exception;

    /**
     * 查询是否存在
     * @param id
     * @return
     * @throws Exception
     */
    boolean countById(String id) throws Exception;

    /**
     *根据房间号查询
     * @param roomId
     * @return
     * @throws Exception
     */
    List<Evaluate> selectByRoomId(String roomId)throws Exception;
}
