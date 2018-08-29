package com.pollux.frame.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pollux.frame.test.constant.RequestTypeEnum;
import com.pollux.frame.test.entity.TestParam;
import com.polluxframework.exception.SerializableException;
import com.polluxframework.web.controller.BaseController;
import com.polluxframework.web.entity.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 用于做单元测试时参数需要一个request或者session时使用
 *
 * @author zhumin0508
 * created in  2018/5/9 7:56
 * modified By:
 */
public class WebTestSingleUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebTestSingleUtils.class);

    private WebTestSingleUtils() {

    }

    static MockHttpServletRequest request;

    /**
     * 获取测试时需要使用的请求
     * request的是一个单例模式，测试过程这种整个request是同一个请求
     *
     * @return 返回一个request
     */
    public static HttpServletRequest getSingleRequest() {
        if (request == null) {
            request = new MockHttpServletRequest();
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(Integer.MAX_VALUE);
        }
        return request;
    }

    /**
     * 获取测试时需要使用的请求
     * request的是一个非单例模式，测试过程这种整个request不是同一个请求
     *
     * @return 返回一个request
     */
    public static HttpServletRequest getNoSingleRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(Integer.MAX_VALUE);
        return request;
    }

    /**
     * 设置session值
     *
     * @param name      session名
     * @param attribute session值
     */
    public static void setAttributeForSession(String name, Object attribute) {
        getSingleRequest();
        if (attribute instanceof Serializable) {
            HttpSession session = request.getSession();
            session.setAttribute(name, attribute);
        } else {
            throw new SerializableException();
        }
    }

    /**
     * 获取session
     *
     * @return 返回session
     */
    public static HttpSession getSession() {
        getSingleRequest();
        return request.getSession();
    }


    /**
     * 测试controller层的执行方法
     *
     * @param applicationContext 上下文跟
     * @param url        请求地址
     * @param type       请求方式
     * @param params     请求参数
     * @return 返回结果集
     * @throws Exception 请求结果异常
     */
    public static WebResponse doController(WebApplicationContext applicationContext, String url, RequestTypeEnum type, TestParam... params) throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        MockHttpServletRequestBuilder requestBuilder;
        switch (type) {
            case GET:
                requestBuilder = get(url);
                break;
            case POST:
                requestBuilder = post(url);
                break;
            default:
                requestBuilder = post(url);
        }
        if (params.length > 0) {
            for (TestParam param : params) {
                if (param.isNotNull()) {
                    requestBuilder.param(param.getName(), param.getValues());
                }
            }
        }

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        if (result != null) {
            String response = result.getResponse().getContentAsString();
            logger.debug("返回结果为：{}", response);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, WebResponse.class);
        } else {
            logger.warn("无返回结果，请注意确认是否正确!");
            return null;
        }
    }
}
