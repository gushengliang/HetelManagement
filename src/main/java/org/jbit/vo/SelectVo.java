package org.jbit.vo;

import java.io.Serializable;

public class SelectVo<S, T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 职务编号
     */
    private String value;
    /**
     * 职务名称
     */
    private String text;
    /**
     * 响应数据
     */

    /**
     * 无参构造方法
     */
    public SelectVo() {
        super();
    }
    /**
     * 获取数据成功
     * @param
     */
    public SelectVo(String deptno, String dname) {
        super();
        this.value = deptno;
        this.text = dname;
    }
    /**
     * 获取数据失败
     * @param
     * @param
     */
    /*public SelectVo(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }*/

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
