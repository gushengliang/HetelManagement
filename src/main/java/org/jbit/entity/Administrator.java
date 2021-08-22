package org.jbit.entity;

import java.io.Serializable;

/**
 * @description 管理员类
 *
 * @author yh
 * @version 1.0,2020-11-21
 */
public class Administrator implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 管理员Id
     */
    private String administratorId;
    /**
     * 管理员姓名
     */
    private String administratorName;
    /**
     * 管理员密码
     */
    private String administratorPassword;

    public Administrator() {

    }

    public Administrator(String administratorId, String administratorName, String administratorPassword) {
        this.administratorId = administratorId;
        this.administratorName = administratorName;
        this.administratorPassword = administratorPassword;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getAdministratorPassword() {
        return administratorPassword;
    }

    public void setAdministratorPassword(String administratorPassword) {
        this.administratorPassword = administratorPassword;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "administratorID='" + administratorId + '\'' +
                ", administratorName='" + administratorName + '\'' +
                ", administratorPassword='" + administratorPassword + '\'' +
                '}';
    }
}
