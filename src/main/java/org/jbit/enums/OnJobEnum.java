package org.jbit.enums;

public enum OnJobEnum {
	/**
	 * 两种状态
	 */
	OFF(0,"离职"),
	ON(1,"在职");
	/**
	 * 枚举值
	 */
	private int value;
	/**
	 * 枚举描述
	 */
	private String description;

	OnJobEnum(int value, String description){
		this.value=value;
		this.description=description;
	}
	
	public int getValue(){
		return value;
	}
	
	public String getDescription(){
		return description;
	}
	
	public static String getDescription(int value){
		for(OnJobEnum onJob: OnJobEnum.values()){
			if(onJob.getValue()==value){
				return onJob.getDescription();
			}
		}
		return null;
	}

}
