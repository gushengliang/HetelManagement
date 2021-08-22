package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 设施类
 *
 * @author yh
 * @version 1.0,2020-11-21
 */
public class Facility implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 设施ID
     */
    private String facilityId;
    /**
     * 设施名称
     */
    private String facilityName;
    /**
     * 设施价格
     */
    private Double facilityPrice;

    public Facility() {

    }

    public Facility(String facilityId, String facilityName, Double facilityPrice) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityPrice = facilityPrice;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Double getFacilityPrice() {
        return facilityPrice;
    }

    public void setFacilityPrice(Double facilityPrice) {
        this.facilityPrice = facilityPrice;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facilityId='" + facilityId + '\'' +
                ", facilityName='" + facilityName + '\'' +
                ", facilityPrice=" + facilityPrice +
                '}';
    }
}
