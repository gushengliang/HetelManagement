package org.jbit.enums;

public enum StatusEnum {
	CREATE(0,"创建"),
	WAIT(1,"等待"),
	CHECK(2,"审核"),
	FINSH(3,"完成");
	/**
	 * 枚举值
	 */
	private int value;
	/**
	 * 枚举描述
	 */
	private String description;
	
	
	public int getValue() {
		return value;
	}
	
	public String getDescription() {
		return description;
	}


	/**
	 * 构造方法
	 * 用构造方法构造枚举值
	 */
	private StatusEnum(int value,String description){
		this.value=value;
		this.description=description;
	}
	/**
	 * 将值转换成描述
	 * @param value
	 * @return
	 */
	public static String valueToDescription(int value){
		for(StatusEnum status: StatusEnum.values()){
			if(status.getValue()==value){
				return status.getDescription();
			}
		}
		return null;
	}
}
