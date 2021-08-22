package org.jbit.entity;

import java.io.Serializable;

public class Compensate implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 理赔ID
     */
    private String compensateId;
    /**
     * 理赔
     */
    private String compensateName;
    /**
     * 赔偿价格
     */
    private Double compensatePrice;

    public Compensate() {

    }

    public Compensate(String compensateId, String compensateName, Double compensatePrice) {
        this.compensateId = compensateId;
        this.compensateName = compensateName;
        this.compensatePrice = compensatePrice;
    }

    public String getCompensateId() {
        return compensateId;
    }

    public void setCompensateId(String compensateId) {
        this.compensateId = compensateId;
    }

    public String getCompensateName() {
        return compensateName;
    }

    public void setCompensateName(String compensateName) {
        this.compensateName = compensateName;
    }

    public Double getCompensatePrice() {
        return compensatePrice;
    }

    public void setCompensatePrice(Double compensatePrice) {
        this.compensatePrice = compensatePrice;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "compensateId='" + compensateId + '\'' +
                ", compensateName='" + compensateName + '\'' +
                ", compensatePrice=" + compensatePrice +
                '}';
    }
}
