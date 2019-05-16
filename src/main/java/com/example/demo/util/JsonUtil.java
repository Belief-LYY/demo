package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.TimeZone;

public class JsonUtil {
    private static ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = LogManager.getLogger();

    static {
        mapper.setTimeZone(TimeZone.getDefault());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    // 对象转Json字符串
    public static String toJson(Object target) {
        try {
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // Json字符串转对象
    public static <T> T fromJson(String target, Class<T> type) {
        if (target == null) {
            return null;
        }
        try {
            return mapper.readValue(target, type);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // 对象转Json byte[]
    public static byte[] toBytes(Object target) {
        try {
            return mapper.writeValueAsBytes(target);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // Json byte[]转对象
    public static <T> T fromBytes(byte[] target, Class<T> type) {
        try {
            return mapper.readValue(target, type);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    // 从request读取Json字符串转对象
    public static <T> T fromRequest(HttpServletRequest request, Class<T> type) {
        byte[] buffer = new byte[request.getContentLength()];
        try {
            InputStream inputStream = request.getInputStream();
            inputStream.read(buffer, 0, buffer.length);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return fromBytes(buffer, type);
    }

    // 从request读取普通字符串
    public static String fromRequest(HttpServletRequest request) {
        byte[] buffer = new byte[request.getContentLength()];
        try {
            InputStream inputStream = request.getInputStream();
            inputStream.read(buffer, 0, buffer.length);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new String(buffer);
    }

    // 从request读取Json字符串转参数Map
    public static Map<?, ?> getParameters(HttpServletRequest request) {
        return fromRequest(request, Map.class);
    }

    // 发送对象转Json字符串到response
    public static void sendJson(HttpServletResponse response, Object target) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(toBytes(target));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // 发送普通字符串到response
    public static void send(HttpServletResponse response, String target) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(target.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
