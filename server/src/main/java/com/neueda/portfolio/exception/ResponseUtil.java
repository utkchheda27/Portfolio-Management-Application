package com.neueda.portfolio.exception;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseUtil {

    public static Map<String, Object> createResponse(String status, Object data, String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        if (data != null) {
            response.put("data", data);
        }
        if (errorMessage != null) {
            response.put("error", errorMessage);
        }
        return response;
    }
}
