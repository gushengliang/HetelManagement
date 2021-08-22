package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 评价表
 * @author yh
 *
 * @version 1.0,2020-11-21
 */
public class Evaluate implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 订单
     */
    private Order order;
    /**
     * 评价内容
     */
    private String evaluateContent;
    /**
     * 评价等级
     */
    private int evaluateLevel;

    public Evaluate() {

    }

    public Evaluate(Order order,String evaluateContent,int evaluateLevel){
        super();
        this.evaluateContent =evaluateContent;
        this.evaluateLevel =evaluateLevel;
        this.order=order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(int evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "order=" + order +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", evaluateLevel=" + evaluateLevel +
                '}';
    }
}
