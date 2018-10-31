package com.polluxframework.paginator.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class PageModelTest {

    @Test
    public void testToString() throws JsonProcessingException {
        System.out.println(Boolean.valueOf(""));
        System.out.println(Boolean.valueOf("1"));
        System.out.println(Boolean.valueOf("0"));
        System.out.println(Boolean.valueOf("f"));
        System.out.println(Boolean.valueOf("true"));
        System.out.println(Boolean.valueOf("True"));
        System.out.println(Boolean.valueOf("TRue"));
        System.out.println(Boolean.valueOf("TRUe"));
        System.out.println(Boolean.valueOf("TRUE"));
        System.out.println(Boolean.valueOf("false"));

        PageList<PageBounds> pageModel = new PageList<>();
        System.out.println(pageModel);

        ObjectMapper objectMapper = new ObjectMapper();
        String str= objectMapper.writeValueAsString(pageModel);
        System.out.println(str);

        PageBounds pageBounds = new PageBounds(0,10);
        pageModel.add(pageBounds);

        System.out.println(pageModel);

        str= objectMapper.writeValueAsString(pageModel);
        System.out.println(str);
    }
}