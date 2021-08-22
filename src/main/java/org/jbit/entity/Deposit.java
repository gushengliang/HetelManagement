package org.jbit.entity;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * @description 房间费用类
 * @author yh
 *
 * @version 1.0,2020-11-21
 */
public class Deposit implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 订单
     */
    private Order order;
    /**
     * 房间价格
     */
    private float depositPrice;
    /**
     * 费用状态
     */
    private String depositState;

    public Deposit() {

    }

    public Deposit(Order order,float depositPrice,String depositState){
        super();
        this.order=order;
        this.depositPrice =depositPrice;
        this.depositState =depositState;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getDepositPrice() {
        return depositPrice;
    }

    public void setDepositPrice(float depositPrice) {
        this.depositPrice = depositPrice;
    }

    public String getDepositState() {
        return depositState;
    }

    public void setDepositState(String depositState) {
        this.depositState = depositState;
    }

    public void count(){
        int day=(int)(this.order.getOverTime().getTime()-this.order.getBeginTime().getTime()) / (1000 * 60 * 60 * 24);
        if(day<0){
            day=0;
        }
        this.depositPrice=day*Float.parseFloat(this.order.getRoom().getRoomPrice());
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "order=" + order +
                ", depositPrice=" + depositPrice +
                ", depositState='" + depositState + '\'' +
                '}';
    }
}
