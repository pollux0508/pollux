package com.polluxframework.test.constant;

public enum RequestTypeEnum {
    /**
     * Post方式
     */
    POST(0),
    /**
     * get方式
     */
    GET(1);

    private Integer type;

    RequestTypeEnum(Integer type){
        this.type=type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
