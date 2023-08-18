package com.alinazim.ms.product.util;

public enum CommonUtil {
    COMMON_UTIL;

    public String getCacheKey(String projectName, String input) {
        return String.format("ms-%s-%s ", projectName, input);
    }
}
