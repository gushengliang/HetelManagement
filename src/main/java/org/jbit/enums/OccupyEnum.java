package org.jbit.enums;

public enum OccupyEnum {
    /**
     * 两种状态
     */
    OFF(0,"空闲"),
    ON(1,"占用");
    /**
     * 枚举值
     */
    private int value;
    /**
     * 枚举描述
     */
    private String description;

    OccupyEnum(int value, String description){
        this.value=value;
        this.description=description;
    }

    public int getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }

    public static String getDescription(int value) {
        for(OccupyEnum occupy: OccupyEnum.values()) {
            if (occupy.getValue() == value) {
                return occupy.getDescription();
            }
        }
        return null;
    }
}
