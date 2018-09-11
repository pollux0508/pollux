package com.polluxframework.test.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhumin0508
 * @create: 2018/08/29 18:40
 * @modified By:
 * @description:
 */
public class TestParam {
    private String name;
    private List<String> values = new ArrayList<>(8);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getValues() {
        int size = this.values.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.values.get(i);
        }
        return result;
    }

    public void addParam(String value) {
        values.add(value);
    }

    public boolean isNotNull() {
        return StringUtils.isNotEmpty(this.name);
    }
}
