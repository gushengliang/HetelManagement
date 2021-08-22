package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 财务类
 *
 * @author ysw
 * @version 1.0,2020-11-28
 */
public class Finace implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 订单
     */
    private Order order;
    /**
     * 日期
     */
    private String finaceDay;
    /**
     * 月份
     */
    private int finaceMonth;
    /**
     * 年
     */
    private int finaceYear;
    /**
     * 日营业额
     */
    private double finaceAmount1;
    /**
     * 月营业额
     */
    private double finaceAmount2;
    /**
     * 年营业额
     */
    private double finaceAmount3;

    public Finace() {

    }

    public Finace(Order order,String finaceDay, int finaceMonth, int finaceYear,double finaceAmount1,double finaceAmount2,double finaceAmount3) {
        this.order=order;
        this.finaceDay =finaceDay;
        this.finaceMonth = finaceMonth;
        this.finaceYear = finaceYear;
        this.finaceAmount1=finaceAmount1;
        this.finaceAmount2=finaceAmount2;
        this.finaceAmount3=finaceAmount3;
    }

    public Order getOrder(){return order;}

    public void setOrder(){this.order=order;}

    public String getFinaceDay() {
        return finaceDay;
    }

    public void setFinaceDay(String finaceDay) {
        this.finaceDay = finaceDay;
    }

    public int getFinaceMonth() {
        return finaceMonth;
    }

    public void setFinaceMonth(int finaceMonth) {
        this.finaceMonth = finaceMonth;
    }

    public int getFinaceYear() {
        return finaceYear;
    }

    public void setFinaceYear(int finaceYear) {
        this.finaceYear = finaceYear;
    }
    
    public double getFinaceAmount1() {
    	return finaceAmount1;
    }
    
    public void setFinaceAmount1(double finaceAmount1) {
    	this.finaceAmount1=finaceAmount1;
    }
    public double getFinaceAmount2() {
    	return finaceAmount2;
    }
    
    public void setFinaceAmount2(double finaceAmount2) {
    	this.finaceAmount2=finaceAmount2;
    }
    
    public double getFinaceAmount3() {
    	return finaceAmount1;
    }
    
    public void setFinaceAmount3(double finaceAmount3) {
    	this.finaceAmount3=finaceAmount3;
    }
    
    @Override
    public String toString() {
        return "Fiance{" +
                "orde='"+order+'\''+
                "finaceDay='" + finaceDay + '\'' +
                ", finacemonth='" + finaceMonth + '\'' +
                ", finaceYear=" + finaceYear +'\''+
                ",finaceAmount1="+finaceAmount1+'\''+
                ",finaceAmount2="+finaceAmount2+'\''+
                ",finaceAmount3="+finaceAmount3+'\''+
                '}';
    }
}
