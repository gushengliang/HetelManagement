package org.jbit.dto;

import org.jbit.entity.Facility;

import java.io.Serializable;

/**
 * @author yh
 */
public class FacilityQueryDto extends Facility implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 页面大小
     */
    private Integer pageSize;

    public FacilityQueryDto() {

    }

    public FacilityQueryDto(String facilityId, String facilityName, Double facilityPrice, Integer currentPage, Integer pageSize) {
        super(facilityId, facilityName, facilityPrice);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
