package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 顾客类
 * @author yh
 *
 * @version 1.0,2020-11-21
 */
public class Customer implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户密码
     */
    private String customerPassword;
    /**
     * 客户电话
     */
    private String customerTelephoneNumber;

    public Customer() {

    }

    public Customer(String customerId, String customerName, String customerPassword, String customerTelephoneNumber){
        super();
        this.customerId =customerId;
        this.customerName =customerName;
        this.customerPassword =customerPassword;
        this.customerTelephoneNumber =customerTelephoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerTelephoneNumber() {
        return customerTelephoneNumber;
    }

    public void setCustomerTelephoneNumber(String customerTelephoneNumber) {
        this.customerTelephoneNumber = customerTelephoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPassword='" + customerPassword + '\'' +
                ", customerTelephoneNumber='" + customerTelephoneNumber + '\'' +
                '}';
    }
}
