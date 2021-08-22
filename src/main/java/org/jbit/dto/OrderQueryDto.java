package org.jbit.dto;
/**
 * @author jzt
 * @description
 * @date 2020/12/1
 */
import org.jbit.entity.Customer;
import org.jbit.entity.Order;
import org.jbit.entity.Room;


import java.io.Serializable;
import java.util.Date;

public class OrderQueryDto extends Order implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 页面大小
     */
    private Integer pageSize;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public OrderQueryDto() { }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public OrderQueryDto(Customer customer, Room room, String orderId, Date beginTime,Date overTime,Integer currentPage,Integer pageSize){
        super(customer, room, orderId, beginTime,overTime);
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
    public OrderQueryDto(Customer customer,Integer currentPage,Integer pageSize){
        super(customer);
        this.setRoom(new Room());
        this.setOrderId("");
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}
