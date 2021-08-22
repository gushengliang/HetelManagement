package org.jbit.dto;

import org.jbit.entity.Compensate;

import java.io.Serializable;

/**
 * @author yh
 */
public class CompensateQueryDto extends Compensate implements Serializable {
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

    public CompensateQueryDto() {

    }

    public CompensateQueryDto(String compensateId, String compensateName, Double compensatePrice, Integer currentPage, Integer pageSize) {
        super(compensateId, compensateName, compensatePrice);
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
