package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 员工类
 *
 * @author yh
 * @version 1.0,2020-11-21
 */
public class Staff implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 员工编号
     */
    private String staffId;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 员工密码
     */
    private String staffPassword;
    /**
     * 员工职务
     */
    private String staffPosition;
    /**
     * 员工在职状态
     */
    private Integer onJob;

    /**
     * 无参构造方法
     */
    public Staff() {
        super();
    }

    /**
     * 有参构造方法
     * @param staffId
     * @param staffName
     * @param staffPassword
     * @param staffPosition
     */
    public Staff(String staffId, String staffName, String staffPassword, String staffPosition) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffPassword = staffPassword;
        this.staffPosition = staffPosition;
    }

    /**
     * 有参构造方法2
     * @param staffId
     * @param staffName
     * @param staffPassword
     * @param staffPosition
     * @param onJob
     */
    public Staff(String staffId, String staffName, String staffPassword, String staffPosition, Integer onJob) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffPassword = staffPassword;
        this.staffPosition = staffPosition;
        this.onJob = onJob;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffPosition() {
        return staffPosition;
    }

    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition;
    }

    public Integer getOnJob() {
        return onJob;
    }

    public void setOnJob(Integer onJob) {
        this.onJob = onJob;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffPassword='" + staffPassword + '\'' +
                ", staffPosition='" + staffPosition + '\'' +
                ", onJob=" + onJob +
                '}';
    }
}
