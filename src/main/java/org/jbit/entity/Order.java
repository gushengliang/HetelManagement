package org.jbit.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 订单类
 * @author yh
 *
 * @version 1.0,2020-11-21
 */

public class Order implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 顾客
     */
    private Customer customer;
    /**
     * 预定房间
     */
    private Room room;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 开房时间
     */
    private Date beginTime;

    /**
     *结束时间
     */
    private Date overTime;

    public Order() {

    }

    public Order(Customer customer,Room room){
        super();
        this.customer=customer;
        this.room=room;
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("mmss");
        String time=sdf.format(date);
        /**
         * 订单号由顾客id加房间id加时间组成
         */
        orderId = this.customer.getCustomerId()+this.room.getRoomId()+time;
    }
    public Order(Customer customer, Room room, String orderId, Date beginTime,Date overTime){
        this.customer=customer;
        this.room=room;
        this.orderId=orderId;
        this.beginTime=beginTime;
        this.overTime=overTime;
    }
    public Order(Customer customer){
        this.customer=customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", room=" + room +
                ", orderId='" + orderId + '\'' +
                ", beginTime=" + beginTime +
                ", overTime=" + overTime +
                '}';
    }
}
