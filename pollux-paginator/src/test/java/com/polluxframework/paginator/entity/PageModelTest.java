package com.polluxframework.paginator.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class PageModelTest {

    @Test
    public void testToString() throws JsonProcessingException {
        PageModel<PageBounds> pageModel = new PageModel<>();
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