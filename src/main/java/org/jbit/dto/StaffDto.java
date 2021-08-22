package org.jbit.dto;

import org.jbit.entity.Staff;
import org.jbit.enums.OnJobEnum;

import java.io.Serializable;


/**
 * 员工Dto
 * @author yh
 * @version 1.0,2020-11-23
 */
public class StaffDto extends Staff implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 职务名称
     */
    private String positionName;
    /**
     * 在职状态描述
     */
    private String onJobDescription;

    public StaffDto() {

    }

    public StaffDto(String staffId, String staffName, String staffPassword, String staffPosition, Integer onJob) {
        super(staffId, staffName, staffPassword, staffPosition, onJob);
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getOnJobDescription() {
        return OnJobEnum.getDescription(this.getOnJob());
    }

    public void setOnJobDescription(String onJobDescription) {
        this.onJobDescription = onJobDescription;
    }
}
