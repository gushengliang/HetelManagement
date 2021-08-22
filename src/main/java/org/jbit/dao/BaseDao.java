package org.jbit.dao;

import java.util.List;

/**
 * 泛型接口
 * @param <E> 泛型
 * @author yh
 *
 * @version 1.0,2020-11-23
 */
public interface BaseDao<E> {
    /**
     * 插入
     * @param obj
     * @return 影响的行数
     * @throws Exception
     */
    int insert(E obj) throws Exception;

    /**
     * 修改
     * @param obj
     * @return 影响的行数
     * @throws Exception
     */
    int update(E obj) throws Exception;

    /**
     * 删除（主键为String类型）
     * @param id 主键
     * @return 影响的行数
     * @throws Exception
     */
    int delete(String id) throws Exception;

    /**
     * 删除（主键为Integer类型）
     * @param id 主键
     * @return 影响的行数
     * @throws Exception
     */
    int delete(Integer id) throws Exception;

    /**
     * 按id选择（id为String类型）
     * @param id 主键
     * @return 返回的结果
     * @throws Exception
     */
    E selectById(String id) throws Exception;

    /**
     * 按id选择（id为Integer类型）
     * @param id 主键
     * @return 返回的结果
     * @throws Exception
     */
    E selectById(Integer id) throws Exception;

    /**
     * 按条件查询
     * @param obj
     * @return 结果集
     * @throws Exception
     */
    List<E> selectBySelective(E obj) throws Exception;
}
