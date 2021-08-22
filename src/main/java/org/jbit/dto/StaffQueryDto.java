package org.jbit.dto;

import org.jbit.entity.Staff;

import java.io.Serializable;

/**
 * @author yh
 */
public class StaffQueryDto extends Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 页面大小
     */
    private Integer pageSize;

    public StaffQueryDto() {

    }

    public StaffQueryDto(String staffId, String staffName, String staffPassword, String staffPosition) {
        super(staffId, staffName, staffPassword, staffPosition);
    }

    public StaffQueryDto(String staffId, String staffName, String staffPassword, String staffPosition, Integer currentPage, Integer pageSize) {
        super(staffId, staffName, staffPassword, staffPosition);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public StaffQueryDto(String staffId, String staffName, String staffPassword, String staffPosition, Integer onJob, Integer currentPage, Integer pageSize) {
        super(staffId, staffName, staffPassword, staffPosition, onJob);
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
